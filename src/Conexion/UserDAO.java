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

public class UserDAO {

    private static final String URL = "jdbc:postgresql://localhost:5432/Prueba";
    private static final String USER = "postgres";
    private static final String PASS = "a";

    /**
     * Comprueba si existe un usuario con ese correo.
     */
    
    public static boolean actualizarContrasenia(String email, String nuevaContrasenia) throws SQLException {
        String sql = "UPDATE usuarios SET contrasenia = ? WHERE email = ?";
        try (Connection conn = ConexionPostgres.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, nuevaContrasenia);
            ps.setString(2, email);
            int filas = ps.executeUpdate();
            return filas > 0;
        }
    }
      public static boolean existeEmail(String email) throws SQLException {
        String sql = "SELECT 1 FROM usuarios WHERE email = ? LIMIT 1";
        try (Connection conn = ConexionPostgres.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next(); 
            }
        }
    }
    
    
}