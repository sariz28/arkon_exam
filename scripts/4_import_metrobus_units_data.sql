BULK INSERT dbo.metrobus_units_locations
FROM '/home/sara/Documentos/arkon_exam/data/metrobus_units_locations/processed/28012021/db/data.csv'
WITH
 (
 	FIRSTROW = 2,
    FIELDTERMINATOR = ',' ,  --CSV field delimiter
    ROWTERMINATOR = '0x0a',--Files are generated with this row terminator in Google Bigquery
    TABLOCK

)