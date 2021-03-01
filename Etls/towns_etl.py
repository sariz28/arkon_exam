from shapely.geometry import Polygon
import json
import pandas as pd

DATA_PATH = '/home/sara/Documentos/arkon_exam/data/towns/'
DATA_VERSION = '28012021'

TOWNS_COLUMNS = ['id',
                 'name',
                 'geo_shape']


def parse_request(s):
    polygon_sj = json.loads(s)
    polygons_array = []
    for coordinates_array in polygon_sj['coordinates'][0]:
        polygons_array.append((coordinates_array[0], coordinates_array[1]))
    polygon = Polygon(polygons_array)
    return polygon


def format_town(town_df):
    town_df = town_df.drop(columns=['id'])
    town_df = town_df.rename(columns={'nomgeo': 'name',
                                      'cve_mun': 'id'})
    town_df = town_df.sort_values(by=['id'])
    town_df = town_df[TOWNS_COLUMNS]

    return town_df


if __name__ == '__main__':
    towns_df = pd.read_csv('{}/raw/{}/alcaldias.csv'.format(DATA_PATH, DATA_VERSION))
    towns_df = format_town(towns_df)
    towns_db_df = towns_df[['id', 'name']]

    towns_db_df.to_csv('{}/processed/{}/db/data.csv'.format(DATA_PATH, DATA_VERSION),
                       index=False,
                       encoding='utf-16')
    towns_df.to_parquet('{}/processed/{}/parquet/data.parquet'.format(DATA_PATH, DATA_VERSION),
                        engine='pyarrow')
