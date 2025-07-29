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

        String sql = "SELECT n.id, n.nombre, n.hora_apertura, n.hora_cierre, img.imagen "
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

    public Restaurante obtenerDetalleRestaurante(int lugarInteresId) throws SQLException {
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
    
     //Listar los museos:
    public List<Restaurante> listarTodas() throws SQLException {
        String sql = "SELECT r.id, r.id_negocio, n.hora_apertura, n.hora_cierre FROM restaurante r JOIN negocio n ON r.id_negocio = n.id ";
        List<Restaurante> lista = new ArrayList<>();

        try ( Connection conn = ConexionHuellasCuencanas.conectar();  PreparedStatement ps = conn.prepareStatement(sql);  ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Restaurante m = new Restaurante();
                m.setId_negocio(rs.getInt("id_negocio"));
                m.setHora_apertura(rs.getTime("hora_apertura").toLocalTime());
                m.setHora_cierre(rs.getTime("hora_cierre").toLocalTime());           
                lista.add(m);
            }
        }

        return lista;
    }

    //Buscar museos por nombre:
    public List<Restaurante> buscarSoloTexto(String texto) throws SQLException {
        String sql
                = "SELECT n.id AS id, n.nombre, n.hora_apertura, n.hora_cierre "
                + "FROM negoicio n"
                + "JOIN restaurante r ON r.id_negocio = n.id "
                + "WHERE LOWER(n.nombre) LIKE ?";
        List<Restaurante> lista = new ArrayList<>();
        String patron = "%" + texto.toLowerCase() + "%";

        try ( Connection conn = ConexionHuellasCuencanas.conectar();  PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, patron);
            try ( ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Restaurante mv = new Restaurante(
                            rs.getInt("id"),
                            rs.getString("nombre"),
                            rs.getTime("hora_apertura").toLocalTime(),
                            rs.getTime("hora_cierre").toLocalTime(),
                            null
                    );
                    lista.add(mv);
                }
            }
        }

        return lista;
    }

    //Eliminar museos por completo:
    public boolean eliminarNegociosCompleto(int negocioID) throws SQLException {
        String sql = "DELETE FROM negocios WHERE id = ?";
        try ( Connection conn = ConexionHuellasCuencanas.conectar();  PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, negocioID);
            return ps.executeUpdate() > 0;
        }
    }

    //Actualizar Parque:
    public boolean actualizarCompleto(
            int capacidad,
           String tipo_cocina,
            String categoria,
            String especialidad,
            int negocioid,
            String nombre,
            String direccion,
            double latitud,
            double longitud,
            String descripcion,
            LocalTime horaApertura,
            LocalTime horaCierre,
            byte[] img1,
            byte[] img2,
            byte[] img3
    ) throws SQLException {
        String sqlNegocio = "UPDATE  negocio      SET  nombre = ?, descripcion = ?,hora_apertura = ?, hora_cierre = ?, capacidad = ? WHERE id = ?";
        String sqlUbic = "UPDATE ubicacion_negocio     SET direccion    = ?, latitud = ?, longitud = ? WHERE id_lugar_negocio = ?";
        String sqlRest = "UPDATE restaurante       SET especialidad = ?, categoria = ?, tipo_cocina = ? WHERE  id_negocio = ?";
        String sqlDelImgs = "DELETE FROM imagen_negocio   WHERE id_negocio = ?";
        String sqlInsImg = "INSERT INTO imagen_negocio(id, imagen, tamano) VALUES (?, ?, ?)";

        try ( Connection conn = ConexionHuellasCuencanas.conectar()) {
            conn.setAutoCommit(false);
            try (
                     PreparedStatement psl = conn.prepareStatement(sqlNegocio);  PreparedStatement psu = conn.prepareStatement(sqlUbic);  PreparedStatement psm = conn.prepareStatement(sqlRest);  PreparedStatement psd = conn.prepareStatement(sqlDelImgs);  PreparedStatement psi2 = conn.prepareStatement(sqlInsImg)) {
                psl.setString(1, nombre);
                psl.setString(2, descripcion);
                psl.setTime(3,  java.sql.Time.valueOf(horaApertura));
                psl.setTime(4,  java.sql.Time.valueOf(horaCierre));
                psl.setInt(5, capacidad);
                psl.setInt(6, negocioid);
                psl.executeUpdate();

                psu.setString(1, direccion);
                psu.setDouble(2, latitud);
                psu.setDouble(3, longitud);
                psu.setInt(4, negocioid);
                psu.executeUpdate();
                
                psm.setString(1, especialidad);
                psm.setString(2, categoria);
                psm.setString(3, tipo_cocina);
                psm.setInt(4, negocioid);
                psm.executeUpdate();

                psd.setInt(1, negocioid);
                psd.executeUpdate();

                int count = 0;
                for (byte[] img : new byte[][]{img1, img2, img3}) {
                    if (img != null) {
                        psi2.setInt(1, negocioid);
                        psi2.setBytes(2, img);
                        psi2.setInt(3, img.length);
                        psi2.executeUpdate();
                        count++;
                    }
                }

                conn.commit();
                return true;
            } catch (SQLException e) {
                conn.rollback();
                throw e;
            }
        }
    }
}
