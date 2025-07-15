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
public class ParqueDao {
    public static void insertar(Connection con, Parque   parque) throws SQLException {
        String sql = "INSERT INTO parque(lugar_interes_id, entidad_gestora, superficie) VALUES (?, ?, ?)";
        try ( PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, parque.getLugarInteresId());
            ps.setString(2, parque.getEntidad_gestora());
            ps.setFloat(3, parque.getSuperficie());
            ps.executeUpdate();
        }
    }
}
