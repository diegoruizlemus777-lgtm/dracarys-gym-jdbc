# Registro de pruebas funcionales — Dracarys Gym (Java/JDBC)

**Evidencia:** GA7-220501096-AA2-EV01  
**Aprendiz:** Diego Fernando Ruiz Lemus

## Entorno de comprobación

En Windows 11 se comprobó la ejecución con Java Temurin 21.0.13, Apache Maven 3.10.0 y MySQL Server 8.0 mediante JDBC. El comando `mvn clean compile` finalizó con `BUILD SUCCESS` y compiló siete archivos Java. La aplicación Swing se abrió, mostró los socios existentes y, según las pruebas realizadas por el aprendiz durante la sesión de configuración, las cuatro operaciones CRUD funcionaron.

Los estados siguientes reflejan las comprobaciones comunicadas por el aprendiz y las capturas compartidas; **no sustituyen un registro automatizado de pruebas**.

| Nº | Prueba | Resultado observado o estado |
|---|---|---|
| 1 | Crear la base y la tabla con `database/01_crear_base.sql` | Base utilizada por la aplicación; esquema consultado desde Workbench |
| 2 | Iniciar la aplicación con variables `DB_*` válidas | Realizada: ventana Swing abierta y datos consultados |
| 3 | Registrar un socio con documento nuevo | Realizada satisfactoriamente, según confirmación del aprendiz |
| 4 | Consultar y buscar socios | Realizada satisfactoriamente; la aplicación mostró registros |
| 5 | Seleccionar un socio y actualizar su plan | Realizada satisfactoriamente, según confirmación del aprendiz |
| 6 | Seleccionar y eliminar un socio | Realizada satisfactoriamente, según confirmación del aprendiz |
| 7 | Intentar registrar un documento duplicado | Pendiente de verificación específica |
| 8 | Cancelar la confirmación de eliminación | Pendiente de verificación específica |
| 9 | Introducir un documento con letras | Pendiente de verificación específica |
| 10 | Desconectar MySQL y consultar | Pendiente de verificación específica |

## Procedimiento de reproducción

1. Ejecutar `database/01_crear_base.sql` en MySQL Workbench.
2. Establecer `DB_HOST`, `DB_PORT`, `DB_NAME`, `DB_USER` y `DB_PASSWORD` en la terminal desde la que se ejecutará Maven.
3. Ejecutar `mvn clean compile exec:java`.
4. Registrar un socio ficticio, buscarlo, actualizar uno de sus campos y eliminarlo.
5. Consultar `SELECT * FROM socios;` en Workbench para observar el resultado persistido.

Para la demostración del módulo se recomienda utilizar únicamente datos ficticios. Las capturas pueden añadirse como anexos si el instructor las solicita.
