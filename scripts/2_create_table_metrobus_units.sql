CREATE TABLE arkon_exam.dbo.metrobus_units (
	id int IDENTITY(1,1) NOT NULL,
	vehicle_id int NOT NULL,
	date_updated datetime NULL,
	latitude decimal(9,6) NOT NULL,
	longitude decimal(9,6) NOT NULL,
	id_town int NULL,
	CONSTRAINT metrobus_units_PK PRIMARY KEY (id),
	CONSTRAINT towns_FK FOREIGN KEY (id_town)
    REFERENCES towns(id)
) 