package co.edu.sena.dracarysgym.dao;

import co.edu.sena.dracarysgym.model.Socio;
import java.sql.SQLException;
import java.util.List;

/** Contrato de acceso a datos (CRUD). */
public interface SocioDao {
    int insertar(Socio socio) throws SQLException;
    List<Socio> listar() throws SQLException;
    List<Socio> buscar(String texto) throws SQLException;
    boolean actualizar(Socio socio) throws SQLException;
    boolean eliminar(int id) throws SQLException;
}
