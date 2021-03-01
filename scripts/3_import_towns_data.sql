BULK INSERT dbo.towns
FROM '/home/sara/Documentos/arkon_exam/data/towns/processed/28012021/db/data.csv'
WITH
 (
 	FIRSTROW = 2,
 	DATAFILETYPE = 'char',
    FIELDTERMINATOR = ',' ,  --CSV field delimiter
    ROWTERMINATOR = '0x0a',--Files are generated with this row terminator in Google Bigquery
    TABLOCK

)