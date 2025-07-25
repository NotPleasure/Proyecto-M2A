/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import ConexionHuellasCuencanas.ConexionHuellasCuencanas;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author MeiRens
 */
public class RestauranteDAO {

    public RestauranteDAO() {
    }

    public static void insertar(Connection con, Restaurante restaurante) throws SQLException {
        String sql = "INSERT INTO restaurante(id_negocio, especialidad, categoria,tipo_cocina) VALUES (?,?, ?, ?)";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, restaurante.getId_negocio());
            ps.setString(2, restaurante.getEspecialidad());
            ps.setString(3, restaurante.getCategoria());
            ps.setString(4, restaurante.getTipo_cocina());
            ps.executeUpdate();
        }

    }

    public List<Restaurante> obtenerRestaurantesVista() throws SQLException {
        List<Restaurante> lista = new ArrayList<>();

        String sql = "SELECT n.id, n.nombre, n .hora_apertura, n.hora_cierre, img.imagen "
                + "FROM negocio n "
                + "JOIN restaurante r ON r.id_negocio = n.id "
                + "JOIN imagen_negocio img ON img.id_negocio = n.id  "
                + "WHERE img.id_negocio = ( "
                + "    SELECT MIN(id) "
                + "    FROM imagen_negocio "
                + "    WHERE id_negocio = n.id "
                + ")";

        try (Connection conn = ConexionHuellasCuencanas.conectar(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                LocalTime horaApertura = rs.getTime("hora_apertura").toLocalTime();
                LocalTime horaCierre = rs.getTime("hora_cierre").toLocalTime();
                byte[] imagen = rs.getBytes("imagen");

                lista.add(new Restaurante(id, nombre, horaApertura, horaCierre, imagen));
            }
        }

        return lista;
    }

    public Restaurante obtenerDetalleMuseo(int lugarInteresId) throws SQLException {
        Restaurante detalle = null;

        String sql = "SELECT "
                + "n.id, "
                + "n.nombre, "
                + "n.hora_apertura, "
                + "n.hora_cierre, "
                + "n.capacidad, "
                + "ul.latitud, "
                + "ul.longitud, "
                + "ul.direccion, "
                + "r.especialidad, "
                + "r.categoria, "
                + "r.tipo_cocina, "
                + "img1.imagen AS imagen1, "
                + "img2.imagen AS imagen2, "
                + "img3.imagen AS imagen3 "
                + "FROM negocio n"
                + "JOIN restaurante r ON r.id_negocio = n.id "
                + "LEFT JOIN ubicacion_negocio ul ON ul.id_lugar_negocio = n.id "
                + "LEFT JOIN imagen_negocio img1 ON img1.id_negocio = n.id AND img1.id_negocio = ("
                + "    SELECT id FROM imagen_negocio WHERE id_negocio = n.id ORDER BY id ASC LIMIT 1"
                + ") "
                + "LEFT JOIN imagen_negocio img2 ON img2.id_negocio = n.id AND img2.id_negocio = ("
                + "    SELECT id FROM imagen_negocio WHERE id_negocio = n.id ORDER BY id ASC OFFSET 1 LIMIT 1"
                + ") "
                + "LEFT JOIN imagen_negocio img3 ON img3.id_negocio = n.id  AND img3.id_negocio = ("
                + "    SELECT id FROM imagen_negocio WHERE id_negocio = n.id ORDER BY id ASC OFFSET 2 LIMIT 1"
                + ") "
                + "WHERE n.id = ?";

        try (Connection conn = ConexionHuellasCuencanas.conectar(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, lugarInteresId);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    detalle = new Restaurante(
                            rs.getString("capacidad"),
                            rs.getString("categoria"),
                            rs.getString("tipo_cocina"),
                            rs.getBytes("imagen1"),
                            rs.getBytes("imagen2"),
                            rs.getBytes("imagen3"),
                            rs.getDouble("latitud"),
                            rs.getDouble("longitud"),
                            rs.getString("direccion"),
                            rs.getInt("id"),
                            rs.getString("nombre"),
                            rs.getString("descripcion"),
                            rs.getTime("hora_apertura") != null ? rs.getTime("hora_apertura").toLocalTime() : null,
                            rs.getTime("hora_cierre") != null ? rs.getTime("hora_cierre").toLocalTime() : null,
                            rs.getInt("capacidad")
                    );
                }
            }
        }

        return detalle;
    }
}
