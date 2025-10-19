# Ejercicio: Consolidación de Logs de Aplicaciones Móviles

Una empresa de desarrollo de aplicaciones móviles necesita un sistema para procesar logs desde dos fuentes diferentes: logs de errores capturados por un servicio de crash reporting y logs de eventos de usuario capturados por un SDK de analytics.
Historias de Usuario


### HU-01: Cargar y parsear archivo de crashes
Como analista de calidad, Quiero que el sistema lea un archivo CSV con logs de crashes de aplicaciones, Para poder procesar toda la información de errores reportados.

A tener en cuenta:
- El archivo de entrada crashes.csv usa ; como separador de campos
- Los campos del archivo son: timestamp, appName, errorCode, errorMessage, stackTrace, deviceModel, osVersion
- El campo errorMessage y stackTrace pueden contener caracteres especiales y saltos de línea, encerrados entre comillas dobles (")


### HU-02: Cargar y parsear archivo de eventos de usuario
Como especialista en analytics, Quiero que el sistema lea un archivo CSV con logs de eventos de usuario, 
Para complementar el análisis con información de comportamiento de aplicaciones.

A tener en cuenta:
- El archivo de entrada events.csv usa ; como separador de campos
- Los campos del archivo son: timestamp, appName, eventType, userId, sessionId, eventData
- El campo eventData puede contener caracteres especiales encerrados entre comillas dobles


### HU-03: Procesar y consolidar datos de ambas fuentes
Como ingeniero de datos, Quiero que el sistema procese los logs de crashes y eventos, 
Para generar un análisis consolidado con métricas por aplicación.

- Se deben combinar ambos archivos en una estructura de datos unificada
- Se debe calcular, para cada aplicación:
- Total de crashes ocurridos
- Total de eventos capturados
- Tasa de error (crashes / eventos totales, expresada como porcentaje)
- Se debe generar un archivo analysis_report.json con la información consolidada usando Jackson.
- Se debe usar Jackson ObjectMapper para serializar a JSON
- El JSON debe ser formateado (pretty-printed) y fácil de leer



## Justificación y explicación de elecciones tomadas:

El proyecto está estructurado siguiendo una arquitectura limpia de tres capas.

### `models`
* **Responsabilidad**: 
  * Contiene los objetos básicos y claves que representas los datos del dominio. 
  * No tiene lógica, solo estado.
* **Clases**:
  * `RegistroLog`: Clase base abstracta con los campos comunes (`timestamp`, `appName`).
  * `Crash` y `Event`: Heredan de `RegistroLog` y añaden sus campos específicos.
  * `Informe`: Almacena los resultados del análisis para una aplicación (`totalCrashes`, `totalEventos`, `tasaError`).

### `service`
* **Responsabilidad**: 
  * Capa de Lógica de Negocio y Contratos. 
  * Orquesta las operaciones y define las interfaces que desacoplan la aplicación.
* **Clases/Interfaces**:
    * `DataService` (Interfaz): 
      * Define el **contrato** para la obtención de datos (`findAllCrashes`, `findAllEvents`). 
      * La capa de servicio depende de esta abstracción, no de una implementación concreta. Esto permite cambiar la fuente de datos (a una base de datos, por ejemplo) sin modificar la lógica de negocio.
    * `InformeService`: 
      * Contiene la lógica de negocio principal. 
      * Es un **servicio sin estado** que recibe los datos brutos, los procesa para calcular las métricas y devuelve el resultado consolidado.

### `data`

* **Responsabilidad**: 
  * Capa de Acceso a Datos (Data Access Layer). 
  * Se encarga de la persistencia, es decir, de leer y escribir datos desde y hacia fuentes externas (en este caso, ficheros).
* **Clases**:
    * `CsvDataReader`: Implementación concreta que sabe cómo leer y parsear los ficheros CSV. Cumple el contrato definido por la interfaz `DataService`.
    * `JsonWriter`: Clase responsable únicamente de tomar los datos del informe y escribirlos en un fichero JSON.



