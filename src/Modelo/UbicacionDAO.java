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

public class UbicacionDAO {

    public static void insertar(Connection con, UbicacionLugar ubicacion) throws SQLException {
        String sql = "INSERT INTO ubicacion_lugar(lugar_interes_id, direccion, latitud, longitud) VALUES (?, ?, ?, ?)";
        try ( PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, ubicacion.getLugarInteresId());
            ps.setString(2, ubicacion.getDireccion());
            ps.setDouble(3, ubicacion.getLatitud());
            ps.setDouble(4, ubicacion.getLongitud());
            ps.executeUpdate();
        }
    }
}
