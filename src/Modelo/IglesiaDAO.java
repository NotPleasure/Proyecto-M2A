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
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

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

    public List<IglesiaVista> obtenerIglesiasVista() throws SQLException {
        List<IglesiaVista> lista = new ArrayList<>();

        String sql = "SELECT li.lugar_interes_id, li.nombre, i.hora_apertura, i.hora_cierre, img.imagen "
                + "FROM lugares_interes li "
                + "JOIN iglesia i ON i.lugar_interes_id = li.lugar_interes_id "
                + "JOIN imagenes_lugar img ON img.lugar_interes_id = li.lugar_interes_id "
                + "WHERE img.imagen_lugar_id = ( "
                + "    SELECT MIN(imagen_lugar_id) "
                + "    FROM imagenes_lugar "
                + "    WHERE lugar_interes_id = li.lugar_interes_id "
                + ")";

        try ( Connection conn = ConexionHuellasCuencanas.conectar();  PreparedStatement ps = conn.prepareStatement(sql);  ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("lugar_interes_id");
                String nombre = rs.getString("nombre");
                LocalTime horaApertura = rs.getTime("hora_apertura").toLocalTime();
                LocalTime horaCierre = rs.getTime("hora_cierre").toLocalTime();
                byte[] imagen = rs.getBytes("imagen");

                lista.add(new IglesiaVista(id, nombre, horaApertura, horaCierre, imagen));
            }
        }

        return lista;
    }

    public IglesiaDetalleVista obtenerDetalleIglesia(int lugarInteresId) throws SQLException {
    IglesiaDetalleVista detalle = null;

    String sql = "SELECT " +
            "li.lugar_interes_id, " +
            "li.nombre, " +
            "i.hora_apertura, " +
            "i.hora_cierre, " +
            "ul.latitud, " +
            "ul.longitud, " +
            "ul.direccion, " +
            "ih.descripcion, " +
            "img1.imagen AS imagen1, " +
            "img2.imagen AS imagen2, " +
            "img3.imagen AS imagen3 " +
            "FROM lugares_interes li " +
            "JOIN iglesia i ON i.lugar_interes_id = li.lugar_interes_id " +
            "LEFT JOIN ubicacion_lugar ul ON ul.lugar_interes_id = li.lugar_interes_id " +
            "LEFT JOIN informacion_historica ih ON ih.lugar_interes_id = li.lugar_interes_id " +
            "LEFT JOIN imagenes_lugar img1 ON img1.lugar_interes_id = li.lugar_interes_id AND img1.imagen_lugar_id = (" +
            "    SELECT imagen_lugar_id FROM imagenes_lugar WHERE lugar_interes_id = li.lugar_interes_id ORDER BY imagen_lugar_id ASC LIMIT 1" +
            ") " +
            "LEFT JOIN imagenes_lugar img2 ON img2.lugar_interes_id = li.lugar_interes_id AND img2.imagen_lugar_id = (" +
            "    SELECT imagen_lugar_id FROM imagenes_lugar WHERE lugar_interes_id = li.lugar_interes_id ORDER BY imagen_lugar_id ASC OFFSET 1 LIMIT 1" +
            ") " +
            "LEFT JOIN imagenes_lugar img3 ON img3.lugar_interes_id = li.lugar_interes_id AND img3.imagen_lugar_id = (" +
            "    SELECT imagen_lugar_id FROM imagenes_lugar WHERE lugar_interes_id = li.lugar_interes_id ORDER BY imagen_lugar_id ASC OFFSET 2 LIMIT 1" +
            ") " +
            "WHERE li.lugar_interes_id = ?";

    try (Connection conn = ConexionHuellasCuencanas.conectar();
         PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setInt(1, lugarInteresId);

        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                detalle = new IglesiaDetalleVista(
                        rs.getInt("lugar_interes_id"),
                        rs.getString("nombre"),
                        rs.getTime("hora_apertura") != null ? rs.getTime("hora_apertura").toLocalTime() : null,
                        rs.getTime("hora_cierre") != null ? rs.getTime("hora_cierre").toLocalTime() : null,
                        rs.getDouble("latitud"),
                        rs.getDouble("longitud"),
                        rs.getString("direccion"),
                        rs.getString("descripcion"),
                        rs.getBytes("imagen1"),
                        rs.getBytes("imagen2"),
                        rs.getBytes("imagen3")
                );
            }
        }
    }

    return detalle;
}

}
