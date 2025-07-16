/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ConexionHuellasCuencanas;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import ConexionHuellasCuencanas.ConexionHuellasCuencanas;

public class MainTest {
    public static void main(String[] args) {
        try (Connection con = ConexionHuellasCuencanas.conectar();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM lugares_interes")) {

            while (rs.next()) {
                System.out.println("Lugar: " + rs.getString("nombre"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
