
package ConexionHuellasCuencanas;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionHuellasCuencanas {

    private static final String URL = "jdbc:postgresql://localhost:5432/HuellasCuencanas";
    private static final String USUARIO = "postgres";
    private static final String CONTRASENA = "a";

    public static Connection conectar() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, CONTRASENA);
    }
    

}