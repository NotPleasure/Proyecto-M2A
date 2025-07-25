/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 *
 * @author MeiRens
 */
public class NegociosDAO {
      public static int insertar(Connection con, Negocios negocio) throws SQLException {
        String sql = "INSERT INTO negocio(nombre,descripcion,hora_apertura,hora_cierre,capacidad) VALUES (?,?,?,?,?) RETURNING id";
        try ( PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, negocio.getNombre());
            ps.setString(2, negocio.getDescripcion());
            ps.setString(3, String.valueOf(negocio.getHora_apertura()));
            ps.setString(4, String.valueOf(negocio.getHora_cierre()));
            ps.setInt(5, negocio.getCapacidad());
            try ( ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("id");
                } else {
                    throw new SQLException("No se pudo obtener el ID del negocio.");
                }
            }
        }
    }

    public static boolean existeNombre(Connection con, String nombre) throws SQLException {
        String sql = "SELECT 1 FROM negocio WHERE LOWER(nombre) = LOWER(?) LIMIT 1";
        try ( PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, nombre.trim());
            try ( ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        }
    }
}
