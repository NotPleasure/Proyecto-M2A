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

public class LugarInteresDAO {

    public static int insertar(Connection con, LugarInteres lugar) throws SQLException {
        String sql = "INSERT INTO lugares_interes(nombre) VALUES (?) RETURNING lugar_interes_id";
        try ( PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, lugar.getNombre());
            try ( ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("lugar_interes_id");
                } else {
                    throw new SQLException("No se pudo obtener el ID del lugar.");
                }
            }
        }
    }

    public static boolean existeNombre(Connection con, String nombre) throws SQLException {
        String sql = "SELECT 1 FROM lugares_interes WHERE LOWER(nombre) = LOWER(?) LIMIT 1";
        try ( PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, nombre.trim());
            try ( ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        }
    }
}
