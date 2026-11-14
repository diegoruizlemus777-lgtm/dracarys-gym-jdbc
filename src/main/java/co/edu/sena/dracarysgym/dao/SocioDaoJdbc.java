package co.edu.sena.dracarysgym.dao;

import co.edu.sena.dracarysgym.config.ConexionMySql;
import co.edu.sena.dracarysgym.model.Socio;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/** Implementación JDBC con PreparedStatement para evitar concatenar entradas en SQL. */
public class SocioDaoJdbc implements SocioDao {
    private static final String COLUMNAS = "id, documento, nombre, apellido, correo, telefono, plan, activo";

    @Override
    public int insertar(Socio socio) throws SQLException {
        String sql = "INSERT INTO socios (documento, nombre, apellido, correo, telefono, plan, activo) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conexion = ConexionMySql.abrir();
             PreparedStatement sentencia = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            asignarDatos(sentencia, socio);
            sentencia.executeUpdate();
            try (ResultSet claves = sentencia.getGeneratedKeys()) {
                if (claves.next()) return claves.getInt(1);
            }
            throw new SQLException("No fue posible recuperar el ID generado.");
        }
    }

    @Override
    public List<Socio> listar() throws SQLException {
        String sql = "SELECT " + COLUMNAS + " FROM socios ORDER BY id DESC";
        try (Connection conexion = ConexionMySql.abrir();
             PreparedStatement sentencia = conexion.prepareStatement(sql);
             ResultSet filas = sentencia.executeQuery()) {
            return convertirLista(filas);
        }
    }

    @Override
    public List<Socio> buscar(String texto) throws SQLException {
        String sql = "SELECT " + COLUMNAS + " FROM socios WHERE documento LIKE ? "
                   + "OR nombre LIKE ? OR apellido LIKE ? ORDER BY id DESC";
        try (Connection conexion = ConexionMySql.abrir();
             PreparedStatement sentencia = conexion.prepareStatement(sql)) {
            String patron = "%" + texto.trim() + "%";
            for (int i = 1; i <= 3; i++) sentencia.setString(i, patron);
            try (ResultSet filas = sentencia.executeQuery()) {
                return convertirLista(filas);
            }
        }
    }

    @Override
    public boolean actualizar(Socio socio) throws SQLException {
        String sql = "UPDATE socios SET documento = ?, nombre = ?, apellido = ?, "
                   + "correo = ?, telefono = ?, plan = ?, activo = ? WHERE id = ?";
        try (Connection conexion = ConexionMySql.abrir();
             PreparedStatement sentencia = conexion.prepareStatement(sql)) {
            asignarDatos(sentencia, socio);
            sentencia.setInt(8, socio.getId());
            return sentencia.executeUpdate() > 0;
        }
    }

    @Override
    public boolean eliminar(int id) throws SQLException {
        String sql = "DELETE FROM socios WHERE id = ?";
        try (Connection conexion = ConexionMySql.abrir();
             PreparedStatement sentencia = conexion.prepareStatement(sql)) {
            sentencia.setInt(1, id);
            return sentencia.executeUpdate() > 0;
        }
    }

    private void asignarDatos(PreparedStatement sentencia, Socio socio) throws SQLException {
        sentencia.setString(1, socio.getDocumento());
        sentencia.setString(2, socio.getNombre());
        sentencia.setString(3, socio.getApellido());
        sentencia.setString(4, socio.getCorreo());
        sentencia.setString(5, socio.getTelefono());
        sentencia.setString(6, socio.getPlan());
        sentencia.setBoolean(7, socio.isActivo());
    }

    private List<Socio> convertirLista(ResultSet filas) throws SQLException {
        List<Socio> socios = new ArrayList<>();
        while (filas.next()) {
            socios.add(new Socio(filas.getInt("id"), filas.getString("documento"),
                filas.getString("nombre"), filas.getString("apellido"),
                filas.getString("correo"), filas.getString("telefono"),
                filas.getString("plan"), filas.getBoolean("activo")));
        }
        return socios;
    }
}
