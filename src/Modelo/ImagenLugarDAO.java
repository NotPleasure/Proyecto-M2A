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

public class ImagenLugarDAO {

   public static void insertar(Connection con, ImagenLugar imagen) throws SQLException {
        String sql = "INSERT INTO imagenes_lugar(lugar_interes_id, imagen, tamano) VALUES (?, ?, ?)";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, imagen.getLugarInteresId());
            ps.setBytes(2, imagen.getImagen());
            ps.setInt(3, imagen.getTamano());
            ps.executeUpdate();
        }
    }
}
