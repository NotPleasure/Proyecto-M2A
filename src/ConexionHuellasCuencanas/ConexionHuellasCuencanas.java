/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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