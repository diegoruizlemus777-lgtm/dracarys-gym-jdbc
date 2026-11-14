# Versionamiento del módulo Dracarys Gym

**Evidencia:** GA7-220501096-AA2-EV01  
**Aprendiz:** Diego Fernando Ruiz Lemus

## Herramientas utilizadas

- **Git:** control de versiones local del código fuente.
- **GitHub:** alojamiento del repositorio remoto público.
- **Rama principal:** `main`.
- **Repositorio publicado:** https://github.com/diegoruizlemus777-lgtm/dracarys-gym-jdbc

El proyecto fue inicializado con Git y publicado en GitHub. Durante la preparación de la evidencia se verificó la rama `main` y el estado local sin cambios pendientes (`working tree clean`). La publicación del proyecto se corroboró posteriormente en GitHub.

## Organización y mantenimiento recomendado

Para cambios posteriores se propone crear ramas con el prefijo `feature/`, verificar las funcionalidades afectadas y registrar los cambios con un commit antes de integrarlos en `main`. Se recomienda usar prefijos como `feat:`, `fix:`, `docs:`, `test:` y `chore:`. No se afirma que esta estrategia de ramas se haya utilizado en cada modificación del módulo.

## Comandos de consulta y actualización

```bash
git status
git branch
git remote -v
git add .
git commit -m "docs: actualizar documentación de la evidencia"
git push origin main
```

El enlace remoto ya está registrado en la entrega; **no es necesario ejecutar de nuevo `git init` ni `git remote add origin` si la conexión existe**. Una etiqueta como `v1.0.0` podría crearse posteriormente, pero no se declara como creada.

**Seguridad:** no subir contraseñas, archivos `.env` ni datos personales reales. El directorio `target/` contiene archivos generados y está excluido por `.gitignore`.
