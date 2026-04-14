import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    // Datos de tu conexión local
    private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=Mi_Inventario;encrypt=true;trustServerCertificate=true;";
    private static final String USER = "user1_abd"; // usuario
    private static final String PASS = "user1_abd"; // contraseña

    public static Connection getConexion() {
        try {
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (SQLException e) {
            System.out.println("Error al conectar: " + e.getMessage());
            return null;
        }
    }
}