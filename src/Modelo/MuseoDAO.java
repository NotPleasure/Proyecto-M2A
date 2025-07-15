/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Time;

/**
 *
 * @author MeiRen
 */
public class MuseoDAO {
      public static void insertar(Connection con, Museo museo) throws SQLException {
        String sql = "INSERT INTO iglesia(lugar_interes_id, hora_apertura, hora_cierre) VALUES (?, ?, ?)";
        try ( PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, museo.getLugarInteresId());
            ps.setTime(2, Time.valueOf(museo.getHoraApertura()));
            ps.setTime(3, Time.valueOf(museo.getHoraCierre()));
            ps.executeUpdate();
        }
    }
}
