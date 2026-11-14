# DRACARYS GYM — módulo de socios con Java y JDBC

**SENA · Análisis y Desarrollo de Software · GA7-220501096-AA2-EV01**

Proyecto académico: módulo de escritorio para gestionar socios del gimnasio mediante operaciones **CRUD** (insertar, consultar, actualizar y eliminar) sobre **MySQL**, utilizando **JDBC** y `PreparedStatement`.

## Requisitos

- JDK 17 o superior.
- MySQL Server 8 o superior y MySQL Workbench (opcional).
- Apache Maven 3.8 o superior.
- Conexión a internet la primera vez que Maven descargue el conector JDBC.

## 1. Crear base de datos

Ejecute `database/01_crear_base.sql` en MySQL Workbench. El archivo `database/02_datos_demo_opcional.sql` incluye ejemplos ficticios, pero **no es obligatorio**.

## 2. Configurar conexión

La aplicación utiliza variables de entorno; no requiere modificar el código fuente. Valores por defecto: `DB_HOST=localhost`, `DB_PORT=3306`, `DB_NAME=dracarys_gym`, `DB_USER=root`, `DB_PASSWORD=` vacío. Configure la contraseña real del usuario MySQL local.

Ejemplo en PowerShell (Windows), **solo en la terminal actual**:

```powershell
$env:DB_HOST="localhost"
$env:DB_PORT="3306"
$env:DB_NAME="dracarys_gym"
$env:DB_USER="root"
$env:DB_PASSWORD="TU_CONTRASENA_LOCAL"
mvn clean compile exec:java
```

Ejemplo en Bash/Linux:

```bash
export DB_USER=root
export DB_PASSWORD='TU_CONTRASENA_LOCAL'
mvn clean compile exec:java
```

No publique las credenciales. La aplicación usa JDBC a través del controlador `mysql-connector-j` definido en `pom.xml`.

## 3. Uso

1. **Insertar:** llene los campos y pulse `Insertar`.
2. **Consultar:** use `Mostrar todos` o busque por nombre, apellido o documento.
3. **Actualizar:** seleccione una fila, modifique campos y pulse `Actualizar`.
4. **Eliminar:** seleccione una fila, pulse `Eliminar` y confirme.
5. **Limpiar:** borra el formulario y la selección actual.

El campo documento debe tener de 5 a 20 dígitos y no puede duplicarse. Nombre y apellido son obligatorios. Plan permite Mensual, Trimestral o Anual.

## 4. Organización

```text
src/main/java/co/edu/sena/dracarysgym/
  App.java                 # entrada
  config/ConexionMySql.java
  model/Socio.java
  dao/SocioDao.java
  dao/SocioDaoJdbc.java    # SQL parametrizado
  service/SocioService.java
  ui/SocioFrame.java       # interfaz Swing
database/01_crear_base.sql
database/02_datos_demo_opcional.sql
docs/artefactos_y_trazabilidad.md
docs/plan_versionamiento.md
docs/pruebas_manuales.md
```

## 5. Estándares de código

Clases en `PascalCase` (`SocioService`), métodos y variables en `camelCase` (`cargarSocios`), paquetes en minúsculas (`co.edu.sena.dracarysgym.dao`), constantes en `UPPER_SNAKE_CASE` (`COLUMNAS`). Cada consulta usa `PreparedStatement`; los recursos JDBC se cierran mediante `try-with-resources`. La eliminación pide confirmación.

## 6. Versionamiento

La carpeta del proyecto incluye un repositorio Git inicializado con un commit. Para publicarlo en GitHub consulte `docs/plan_versionamiento.md`. Complete el archivo `../ENLACE_REPOSITORIO.txt` con el enlace **real** después de publicar.

## 7. Alcance y límites

Esta entrega constituye un **módulo de práctica Java/JDBC independiente**, basado en el dominio de Dracarys Gym. **No** se afirma que ya esté integrado con la aplicación web existente en Node.js ni con sus tablas reales. No implementa autenticación ni permisos de usuarios: por ello debe ejecutarse en un entorno académico controlado y sin datos personales reales. La eliminación es física en la tabla de demostración.

**Validación realizada:** compilación del código Java con `javac` (sin conectividad a MySQL). **Pendiente por el aprendiz:** comprobar ejecución contra MySQL y documentar pruebas realizadas.
