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

public class InformacionHistoricaDAO {

    public static void insertar(Connection con, InformacionHistorica info) throws SQLException {
        String sql = "INSERT INTO informacion_historica(lugar_interes_id, descripcion) VALUES (?, ?)";
        try ( PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, info.getLugarInteresId());
            ps.setString(2, info.getDescripcion());
            ps.executeUpdate();
        }
    }
}
