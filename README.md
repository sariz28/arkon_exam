## API Spring Boot con GraphQL



Esta API porporciona datos abiertos de la Ciudad de
México correspondientes a las ubicaciones de las unidades del metrobús


### Tecnologías 

Se implementaron las siguientes tecnologías:


**Procesamiento y almacenamiento de datos:**

* Python 3.0 (GeoPandas)
* Sql Server 15.0.4102.2-4

**API:**


* Spring Boot 2.4.2
* GraphQL 5.0.2
* Gradle 6.3
* Swagger 2:2.5.0


### Procesamiento y almacenamiento de datos

Para cumplir con el requerimiento de obtener la alcaldía correspondiente a cada posición de las unidades de metrobus y almacenar la información en una base de datos, se realizaron dos ETLs y se describe el pipeline de datos en el siguiente diagrama:

<img src="/diagramas/data_process.png" weight="600" width="800">


### API 

En el siguiente diagrama se muestra la arquitectura de la solución propuesta


<img src="/diagramas/api.png" width="800"  weight="600">


### Compilación y Despliegue

Para la compilación se implemento gradle, ejecutar la siguiente instrucción:

```
./gradlew clean build
```
Despues de compilar el código, esta instrucción en automático ejecutan las pruebas unitarias de la capa Controller y Repository.

Podremos encontrar el código compilado (arkon_exam-0.0.1-SNAPSHOT.jar) en la carpeta **/build/libs**, para el despliegue de la API, ejecutar la siguiente instrucción:

```
java -jar arkon_exam-0.0.1-SNAPSHOT.jar
```

### Ejecutando las pruebas unitarias 

Para correr las pruebas unitarias, ejecutar el siguiente comando:

```
./gradlew test
```
 Al finalizar gradle genera archivos de resultados de prueba HTML: directorio **/build/reports/tests/**
 
 
 
### Docker build

```sh
docker build -t arkon_exam .
```

### Docker run

```sh
docker run --name arkon_exam -p 192.168.100.12:19000:19000 arkon_exam
```

### Swagger

Se implementó swagger para la documentación de la API, para acceder a  Swagger UI:

```
http://localhost:19000/arkon-exam/swagger-ui.html#
```

<img src="/diagramas/swagger.png" width="800"  weight="600">


### Acerca de los servicios

Se exponen dos servicios en la API:


* **Metrobus unit location Service:** Provee información de las ubicaciones de las unidades del metrobús permitiendo filtrar por fecha, alcandía o el id de la unidad


Dado que la API implementa Graphql, las consultas que se pueden realizar son las siguientes:

| Consulta| descripción|
| ----- | ---- |
|**allMbUnitLocations**| Retorna una lista de todas las ubicaciones de las unidades de metrobus|
|**mbUnitLocationsById** (vehicleId):| Retorna una lista de las ubicaciones de las unidades de metrobus dado un vehicleId|
|**mbUnitLocationsByTow**(townId)|  Retorna una lista de las ubicaciones de las unidades de metrobus de una determinada alcaldía|
|**mbUnitLocationsByFilter**(filter:{vehicleId date townId})| Esta consulta retorna una lista dependiendo del filtro especificado|



**URL de acceso**

```
Método POST  http://localhost:19000/arkon-exam/mb-unit-location
```


**Resquest**



```
{
	mbUnitLocationsByFilter(filter:{vehicleId: 170 
                                 date: "2021-01-27 18:00:02" 
                                 townId:5}){
                                       vehicleId
                                        dateUpdated
                                       latitude
                                       longitude
                                       town{
                                          name
                                       }
                                 }
}
```

**Response**

```
{
  "errors": [],
  "data": {
    "mbUnitLocationsByFilter": [
      {
        "vehicleId": 449,
        "dateUpdated": "2021-01-28T18:00:02-06:00",
        "latitude": 19.4035,
        "longitude": -99.170403,
        "town": {
          "name": "Cuauhtémoc"
        }
      }
    ]
  },
  "extensions": null,
  "dataPresent": true
}
```


* **Towns Service:** Provee una lista de alcaldías disponibles



Consultas que se pueden realizar a través de este servicio son las siguientes:



| Consulta| descripción|
| ----- | ---- |
|**allTowns**| Retorna una lista de todas las alcaldías de la CMDX
|**town(id:Int)**| Retorna una alcaldía dado un id



**URL de acceso**

```
Método POST  http://localhost:19000/arkon-exam/towns
```


**Resquest**



```
{
 allTowns{
	id
    name
   }
}
```

**Response**

```
{
  {
  "errors": [],
  "data": {
    "allTowns": [
      {
        "id": 2,
        "name": "Azcapotzalco"
      },
      {
        "id": 3,
        "name": "Coyoacán"
      },
      {
        "id": 4,
        "name": "Cuajimalpa de Morelos"
      }...
    ]
  },
  "extensions": null,
  "dataPresent": true
}
```

