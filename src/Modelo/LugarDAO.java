/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ConexionHuellasCuencanas.ConexionHuellasCuencanas;

public class LugarDAO {

    public static int insertar(LugarInteres lugar) throws SQLException {
        String sql = "INSERT INTO lugares_interes(nombre) VALUES (?) RETURNING lugar_interes_id";
        try ( Connection con = ConexionHuellasCuencanas.conectar();  PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, lugar.getNombre());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("lugar_interes_id");
            } else {
                throw new SQLException("No se pudo obtener el ID del lugar.");
            }
        }
    }

    public static boolean existeNombre(String nombre) throws SQLException {
        String sql = "SELECT 1 FROM lugares_interes WHERE LOWER(nombre) = LOWER(?) LIMIT 1";
        try ( Connection con = ConexionHuellasCuencanas.conectar();  PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, nombre.trim());
            ResultSet rs = ps.executeQuery();
            return rs.next();
        }
    }

}
