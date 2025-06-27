/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Conexion;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.UUID;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

public class TokenDAO {
    // Genera token y lo guarda en BD, devuelve el token
   private static final String URL = "jdbc:postgresql://localhost:5432/Prueba";
    private static final String USER = "postgres";
    private static final String PASS = "a";

    /**
     */
    public static String generarToken(String email) throws SQLException {
        String token = UUID.randomUUID().toString().replace("-", "");
        String sql = "INSERT INTO password_reset (user_email, token) VALUES (?, ?)";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, email);
            ps.setString(2, token);
            ps.executeUpdate();
        }

        return token;
    }

    /**
     */
    public static boolean validarToken(String token) throws SQLException {
        String sql = "SELECT created_at FROM password_reset WHERE token = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, token);
            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) {
                    return false;  // no existe
                }
                Timestamp fecha = rs.getTimestamp("created_at");
                return fecha.toInstant()
                            .isAfter(Instant.now().minus(1, ChronoUnit.HOURS));
            }
        }
    }
}
