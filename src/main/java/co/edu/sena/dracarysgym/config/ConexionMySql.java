package co.edu.sena.dracarysgym.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/** Centraliza la conexión JDBC. Las credenciales no se guardan en el código. */
public final class ConexionMySql {
    private ConexionMySql() { }

    public static Connection abrir() throws SQLException {
        String host = System.getenv().getOrDefault("DB_HOST", "localhost");
        String puerto = System.getenv().getOrDefault("DB_PORT", "3306");
        String nombre = System.getenv().getOrDefault("DB_NAME", "dracarys_gym");
        String usuario = System.getenv().getOrDefault("DB_USER", "root");
        String clave = System.getenv().getOrDefault("DB_PASSWORD", "");
        String url = "jdbc:mysql://" + host + ":" + puerto + "/" + nombre
                + "?useUnicode=true&characterEncoding=UTF-8&connectionTimeZone=UTC";
        return DriverManager.getConnection(url, usuario, clave);
    }
}
