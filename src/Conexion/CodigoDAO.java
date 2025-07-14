/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

/**
 *
 * @author USER
 */
public class CodigoDAO {
    public static boolean validarCodigo(String email, String codigo) throws SQLException {
        String sql = "SELECT COUNT(*) FROM password_reset WHERE user_email = ? AND codigo = ?";
        
        try (Connection conn = ConexionPostgres.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            
            
            ps.setString(1, email);
            ps.setString(2, codigo);
            
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    int count = rs.getInt(1);
                    return count > 0;
                }
            }
        }
        return false;
    }

    public static void guardarCodigo(String email, String codigo) throws SQLException {
        String sqlDelete = "DELETE FROM password_reset WHERE user_email = ?";
        String sqlInsert = "INSERT INTO password_reset (user_email, codigo) VALUES (?, ?)";
        try (Connection conn = ConexionPostgres.conectar()) {
            try (PreparedStatement psDelete = conn.prepareStatement(sqlDelete)) {
                psDelete.setString(1, email);
                psDelete.executeUpdate();
            }
            try (PreparedStatement psInsert = conn.prepareStatement(sqlInsert)) {
                psInsert.setString(1, email);
                psInsert.setString(2, codigo);
                psInsert.executeUpdate();
            }
        }
    }
    public static String generarCodigo() {
        int numero = (int)(Math.random() * 900000) + 100000;  // código de 6 dígitos
        return String.valueOf(numero);
    }

}
        