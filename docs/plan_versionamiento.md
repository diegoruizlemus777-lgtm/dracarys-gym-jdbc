# Versionamiento del módulo

- Herramienta local: **Git**.
- Repositorio remoto propuesto: **GitHub** (pendiente de publicación por el aprendiz).
- Rama principal: `main`.
- Flujo recomendado: rama `feature/descripcion` → cambios y pruebas → commit → integración en `main`.
- Etiqueta inicial sugerida: `v1.0.0` una vez validado el módulo con MySQL.
- Convención para commits: `feat:`, `fix:`, `docs:`, `test:` y `chore:`.

## Ejemplo de comandos

```bash
git status
git checkout -b feature/consulta-socios
git add .
git commit -m "feat: implementar consulta de socios"
git checkout main
git merge feature/consulta-socios
# Cree primero el repositorio vacío en GitHub:
git remote add origin https://github.com/TU_USUARIO/dracarys-gym-jdbc.git
git push -u origin main
```

**Precaución:** nunca incluir contraseñas, archivos `.env` ni datos personales reales en el repositorio.
