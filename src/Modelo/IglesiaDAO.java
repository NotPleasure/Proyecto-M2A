/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;

import ConexionHuellasCuencanas.ConexionHuellasCuencanas;

public class IglesiaDAO {

    public static void insertar(Connection con, Iglesia iglesia) throws SQLException {
        String sql = "INSERT INTO iglesia(lugar_interes_id, hora_apertura, hora_cierre) VALUES (?, ?, ?)";
        try ( PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, iglesia.getLugarInteresId());
            ps.setTime(2, Time.valueOf(iglesia.getHoraApertura()));
            ps.setTime(3, Time.valueOf(iglesia.getHoraCierre()));
            ps.executeUpdate();
        }
    }
}
