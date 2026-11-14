# Lista de comprobación funcional

Antes de la entrega, ejecutar las siguientes pruebas en un equipo con Java, Maven y MySQL configurados. **No se afirma que estén ejecutadas en una base MySQL real**.

| Nº | Procedimiento | Resultado esperado |
|---|---|---|
| 1 | Ejecutar `01_crear_base.sql` | Se crean base y tabla |
| 2 | Abrir la app con variables `DB_*` válidas | Se muestra ventana y consulta inicial |
| 3 | Registrar un socio con documento único | Se inserta y aparece en tabla |
| 4 | Registrar nuevamente el mismo documento | MySQL rechaza duplicado (UNIQUE) |
| 5 | Buscar por nombre o documento | Se muestran coincidencias |
| 6 | Seleccionar y cambiar el plan de un socio | Se actualiza el registro |
| 7 | Seleccionar y eliminar, confirmando | Se borra el registro |
| 8 | Cancelar confirmación de eliminar | El registro permanece |
| 9 | Registrar documento con letras | Se muestra mensaje de validación |
| 10 | Desconectar MySQL y consultar | Se muestra error de conexión manejado |
