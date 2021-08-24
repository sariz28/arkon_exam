from shapely.geometry import Polygon
import geopandas as gpd
import json
import pandas as pd
import numpy as np

DATA_PATH = '/home/sara/Documentos/arkon_exam/data'
DATA_VERSION = '28012021'

TRANSPORT_UNITS_COLUMNS = ['id',
                           'vehicle_id',
                           'date_updated',
                           'latitude',
                           'longitude',
                           'id_town']


def parse_request(s):
    polygon_sj = json.loads(s)
    polygons_array = []
    for coordinates_array in polygon_sj['coordinates'][0]:
        polygons_array.append((coordinates_array[0], coordinates_array[1]))
    polygon = Polygon(polygons_array)
    return polygon


if __name__ == '__main__':
    towns_df = pd.read_parquet('{}/towns/processed/{}/parquet/data.parquet'.format(DATA_PATH, DATA_VERSION),
                               engine='pyarrow')
    towns_df['geo_shape'] = towns_df['geo_shape'].apply(parse_request)

    transport_units_df = pd.read_csv('{}/metrobus_units_locations/raw/{}/metrobus_data.csv'.format(DATA_PATH, DATA_VERSION))
    transport_units_df = transport_units_df.rename(columns={'position_latitude': 'latitude',
                                                            'position_longitude': 'longitude'
                                                            })
    transport_units_geo_df = gpd.GeoDataFrame(transport_units_df,
                                              geometry=gpd.points_from_xy(transport_units_df.longitude,
                                                                          transport_units_df.latitude))
    polygons_dict = dict((row["name"], row["geo_shape"]) for index, row in towns_df.iterrows())
    polygons_series = gpd.GeoSeries(polygons_dict)

    transport_units_geo_df = transport_units_geo_df.assign(**{key: transport_units_geo_df.within(geom)
                                                              for key, geom in polygons_series.items()})

    conditions = [(transport_units_geo_df['Azcapotzalco'] == True),
                  (transport_units_geo_df['Coyoacán'] == True),
                  (transport_units_geo_df['Cuajimalpa de Morelos'] == True),
                  (transport_units_geo_df['Gustavo A. Madero'] == True),
                  (transport_units_geo_df['Iztacalco'] == True),
                  (transport_units_geo_df['Iztapalapa'] == True),
                  (transport_units_geo_df['La Magdalena Contreras'] == True),
                  (transport_units_geo_df['Milpa Alta'] == True),
                  (transport_units_geo_df['Álvaro Obregón'] == True),
                  (transport_units_geo_df['Tláhuac'] == True),
                  (transport_units_geo_df['Tlalpan'] == True),
                  (transport_units_geo_df['Xochimilco'] == True),
                  (transport_units_geo_df['Benito Juárez'] == True),
                  (transport_units_geo_df['Cuauhtémoc'] == True),
                  (transport_units_geo_df['Miguel Hidalgo'] == True),
                  (transport_units_geo_df['Venustiano Carranza'] == True)
                  ]

    cve_mun_list = [i for i in towns_df['id']]
    towns_name_list = [i for i in towns_df['name']]
    transport_units_geo_df['id_town'] = np.select(conditions, cve_mun_list)
    transport_units_geo_df = transport_units_geo_df.drop(columns=towns_name_list)
    transport_units_geo_df['id'] = np.NaN

    transport_units_geo_df = transport_units_geo_df[TRANSPORT_UNITS_COLUMNS]
    transport_units_db_df = transport_units_geo_df[transport_units_geo_df['id_town'] != 0]

    transport_units_db_df.to_csv('{}/metrobus_units_locations/processed/{}/db/data.csv'.format(DATA_PATH, DATA_VERSION),
                                 index=False,
                                 encoding='utf-16')

    transport_units_geo_df = transport_units_geo_df.drop(columns=['id'])
    transport_units_geo_df.to_parquet('{}/metrobus_units_locations/processed/{}/parquet/data.parquet'.format(DATA_PATH,
                                                                                                   DATA_VERSION),
                                      engine='pyarrow')
