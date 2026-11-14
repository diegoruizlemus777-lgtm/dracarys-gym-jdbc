package co.edu.sena.dracarysgym.service;

import co.edu.sena.dracarysgym.dao.SocioDao;
import co.edu.sena.dracarysgym.model.Socio;
import java.sql.SQLException;
import java.util.List;

/** Validaciones y reglas mínimas para la gestión de socios. */
public class SocioService {
    private final SocioDao socioDao;

    public SocioService(SocioDao socioDao) { this.socioDao = socioDao; }

    public int registrar(Socio socio) throws SQLException {
        validar(socio);
        return socioDao.insertar(socio);
    }

    public List<Socio> listar() throws SQLException { return socioDao.listar(); }

    public List<Socio> buscar(String texto) throws SQLException {
        return texto == null || texto.isBlank() ? listar() : socioDao.buscar(texto);
    }

    public boolean actualizar(Socio socio) throws SQLException {
        if (socio.getId() == null || socio.getId() <= 0) {
            throw new IllegalArgumentException("Seleccione un socio antes de actualizar.");
        }
        validar(socio);
        return socioDao.actualizar(socio);
    }

    public boolean eliminar(Integer id) throws SQLException {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Seleccione un socio antes de eliminar.");
        }
        return socioDao.eliminar(id);
    }

    private void validar(Socio socio) {
        if (socio.getDocumento() == null || !socio.getDocumento().matches("[0-9]{5,20}")) {
            throw new IllegalArgumentException("El documento debe contener entre 5 y 20 dígitos.");
        }
        if (vacio(socio.getNombre()) || vacio(socio.getApellido())) {
            throw new IllegalArgumentException("Nombre y apellido son obligatorios.");
        }
        if (socio.getNombre().length() > 100 || socio.getApellido().length() > 100) {
            throw new IllegalArgumentException("Nombre o apellido supera los 100 caracteres.");
        }
        if (socio.getCorreo() != null && !socio.getCorreo().isBlank()
                && !socio.getCorreo().matches("^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$")) {
            throw new IllegalArgumentException("El correo electrónico no tiene un formato válido.");
        }
        if (socio.getPlan() == null || !List.of("Mensual", "Trimestral", "Anual").contains(socio.getPlan())) {
            throw new IllegalArgumentException("Seleccione un plan válido.");
        }
    }

    private boolean vacio(String texto) { return texto == null || texto.isBlank(); }
}
