# DRACARYS GYM — módulo de socios con Java y JDBC

**SENA · Análisis y Desarrollo de Software · GA7-220501096-AA2-EV01**  
**Aprendiz:** Diego Fernando Ruiz Lemus

Módulo académico de escritorio para administrar socios del gimnasio mediante las operaciones CRUD (insertar, consultar, actualizar y eliminar) con Java Swing, MySQL y JDBC. Las consultas de datos se implementan mediante `PreparedStatement`.

## Requisitos

- JDK 17 o superior (comprobado con Temurin 21.0.13).
- MySQL Server 8 o superior; Workbench es opcional para la gestión de la base.
- Apache Maven (comprobado con Maven 3.10.0).
- Acceso a internet para la descarga inicial de dependencias Maven.

## 1. Preparar la base de datos

Ejecutar `database/01_crear_base.sql` en MySQL Workbench. El script `database/02_datos_demo_opcional.sql` contiene socios ficticios y es opcional; no ejecutarlo repetidamente porque los documentos son únicos.

Es recomendable crear un usuario MySQL exclusivo para la aplicación, con permisos `SELECT`, `INSERT`, `UPDATE` y `DELETE` sobre `dracarys_gym`. En la comprobación local se utilizó `dracarys_app`. La aplicación no crea usuarios MySQL automáticamente.

## 2. Configurar la conexión

Las credenciales se leen de variables de entorno en `ConexionMySql.java`. Si no se definen, el código usa `localhost`, puerto `3306`, base `dracarys_gym`, usuario `root` y contraseña vacía como valores predeterminados. **Para la ejecución comprobada se configuró el usuario `dracarys_app`; no se requiere modificar el código Java.**

PowerShell (Windows), en la terminal desde la que se ejecutará Maven:

```powershell
$env:DB_HOST = "localhost"
$env:DB_PORT = "3306"
$env:DB_NAME = "dracarys_gym"
$env:DB_USER = "dracarys_app"
$env:DB_PASSWORD = Read-Host "Contraseña del usuario MySQL"
mvn clean compile exec:java
```

Escribir la contraseña cuando se solicite, sin comillas. `Read-Host` evita introducirla literalmente en el comando, aunque su captura no queda oculta en pantalla. No compartir las credenciales ni publicarlas en GitHub. Si se utiliza otro usuario de MySQL con permisos equivalentes, ajustar `DB_USER` en consecuencia.

## 3. Funciones

1. **Insertar:** diligenciar el formulario y seleccionar `Insertar`.
2. **Consultar:** seleccionar `Mostrar todos` o buscar por nombre, apellido o documento.
3. **Actualizar:** seleccionar una fila, cambiar los campos y seleccionar `Actualizar`.
4. **Eliminar:** seleccionar una fila, seleccionar `Eliminar` y confirmar.
5. **Limpiar:** reiniciar el formulario y la selección.

El documento debe tener entre 5 y 20 dígitos y ser único. El nombre y el apellido son obligatorios. Los planes disponibles son Mensual, Trimestral y Anual.

## 4. Organización de archivos

```text
src/main/java/co/edu/sena/dracarysgym/
  App.java
  config/ConexionMySql.java
  model/Socio.java
  dao/SocioDao.java
  dao/SocioDaoJdbc.java
  service/SocioService.java
  ui/SocioFrame.java
database/01_crear_base.sql
database/02_datos_demo_opcional.sql
docs/artefactos_y_trazabilidad.md
docs/plan_versionamiento.md
docs/pruebas_manuales.md
pom.xml
ENLACE_REPOSITORIO.txt
```

## 5. Convenciones de código

Clases en `PascalCase`, métodos y variables en `camelCase`, paquetes en minúsculas y constantes en `UPPER_SNAKE_CASE`. El acceso a datos utiliza consultas SQL parametrizadas y cierre de recursos con `try-with-resources`.

## 6. Versionamiento y repositorio

El proyecto se gestiona con Git y fue publicado en GitHub, rama principal `main`:

https://github.com/diegoruizlemus777-lgtm/dracarys-gym-jdbc

El archivo `ENLACE_REPOSITORIO.txt` también contiene el enlace. Véase `docs/plan_versionamiento.md` para el estado del repositorio y las recomendaciones de mantenimiento.

## 7. Comprobaciones realizadas

- Compilación Java con Maven: `BUILD SUCCESS` (siete archivos fuente).
- Ejecución de la interfaz Swing y consulta de socios mediante JDBC: comprobadas.
- Operaciones de insertar, consultar, actualizar y eliminar: verificadas en el equipo del aprendiz, de acuerdo con su confirmación durante las pruebas.
- Pruebas adicionales de duplicados, cancelación de eliminación, documento inválido y desconexión: no se registraron como ejecutadas.

Consultar `docs/pruebas_manuales.md` para el detalle de las verificaciones.

## 8. Alcance

Este módulo Java/JDBC es una práctica académica independiente del proyecto web Dracarys Gym desarrollado en HTML, CSS, JavaScript, Node.js y MySQL; no está integrado automáticamente con esa versión. No incorpora autenticación ni autorización de operadores y debe utilizarse en un entorno controlado con datos ficticios. La eliminación de socios es física en la base de demostración.
