## API Spring Boot con GraphQL



Esta API porporciona datos abiertos de la Ciudad de
México correspondientes a la ubicación de las unidades del metrobús mediante los servicios:


### Tecnologías 

Se implemetaron las siguientes tecnologías:


** Procesamiento y almacenamiento de datos:**

* Python 3.0 (GeoPandas)
* Sql Server 15.0.4102.2-4

** API:**


* Spring Boot 2.4.2
* GraphQL 5.0.2
* Gradle 6.3


### Procesamiento y almacenamiento de datos

Para cumplir con el requerimiento de obtener la alcaldía correspondiente a cada posición de las unidades de metrobus y almacenar la información en una base de datos, se realizaron dos ETLs y se describe el pipeline de datos en el siguiente diagrama:

<img src="data_process.png" width="800">


### API 

En el siguiente diagrama se muestra la arquitectura de la solución propuesta


<img src="api.png" width="800">


### Compilación y Despliegue

Para la compilación se implemento gradle, ejecutar la siguiente instrucción:

```
./gradlew clean build
```
Despues de compilar el código, esta instrucción en automático ejecutara las pruebas unitarias de la capa Controller y Repository.

Podremos encontrar el codigo compilado (arkon_exam-0.0.1-SNAPSHOT.jar) en la carppeta **/build/libs**, para el despliegue de la API, ejecutar la siguiente instrucción:

```
java -jar arkon_exam-0.0.1-SNAPSHOT.jar
```

### Ejecutando las pruebas unitarias 

Para correr las pruebas unitarias, ejecutar el siguiente comando:

```
./gradlew test
```
 Al finalizar gradle genera archivos de resultados de prueba HTML: directorio /build/reports/tests/

### Acerca de los servicios

Se exponen dos servicios en la API:


**Metrobus unit location Service** Provee información de las ubicaciones de las unidades del metrobús permitiendo filtrar por fecha, alcandía o el id de la unidad|

Dado que la API implementa Graphql, las consultas que se pueden realizar son las siguientes:

* **allMbUnitLocations**: Retorna una lista de todas las ubicaciones de las unidades de metrobus


* **mbUnitLocationsById**: (vehicleId): Retorna una lista de las ubicaciones de las unidades de metrobus dado un vehicleId


* **mbUnitLocationsByTow**(townId):  Retorna una lista de las ubicaciones de las unidades de metrobus de una determinada alcaldía


* **mbUnitLocationsByFilter**(filter:{vehicleId date townId}): Esta consulta retorna una lista dependiendo del filtro especificado


* **Towns Service**: Provee una lista de alcaldías disponibles

Consultas que se pueden realizar a través de este servicio son las siguientes:

* allTowns: retorna una lista de todas las alcaldías de la CMDX
* town(id:Int): retorna una alcaldía dado un id


| TITULO1| TITULO2|
| ----- | ---- |
| CONTENIDO COLUMNA 1 | CONTENIDO COLUMNA 2 |
