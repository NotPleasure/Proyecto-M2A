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
 * @author MeiRen
 */
public class MuseoDAO {

    public MuseoDAO() {
    }

    //Insertar una iglesia:
    public static void insertar(Connection con, Museo museo) throws SQLException {
        String sql = "INSERT INTO museo(lugar_interes_id, hora_apertura, hora_cierre) VALUES (?, ?, ?)";
        try ( PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, museo.getLugarInteresId());
            ps.setTime(2, Time.valueOf(museo.getHoraApertura()));
            ps.setTime(3, Time.valueOf(museo.getHoraCierre()));
            ps.executeUpdate();
        }

    }

    //Obtener la vista de la iglesia:
    public List<MuseoVista> obtenerMuseosVista() throws SQLException {
        List<MuseoVista> lista = new ArrayList<>();

        String sql = "SELECT li.lugar_interes_id, li.nombre, m .hora_apertura, m.hora_cierre, img.imagen "
                + "FROM lugares_interes li "
                + "JOIN museo m ON m .lugar_interes_id = li.lugar_interes_id "
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

                lista.add(new MuseoVista(id, nombre, horaApertura, horaCierre, imagen));
            }
        }

        return lista;
    }

    //Obtener todos los datos de la iglesia:
    public MuseoDetalleVista obtenerDetalleMuseo(int lugarInteresId) throws SQLException {
        MuseoDetalleVista detalle = null;

        String sql = "SELECT "
                + "li.lugar_interes_id, "
                + "li.nombre, "
                + "m.hora_apertura, "
                + "m.hora_cierre, "
                + "ul.latitud, "
                + "ul.longitud, "
                + "ul.direccion, "
                + "ih.descripcion, "
                + "img1.imagen AS imagen1, "
                + "img2.imagen AS imagen2, "
                + "img3.imagen AS imagen3 "
                + "FROM lugares_interes li "
                + "JOIN museo m ON m.lugar_interes_id = li.lugar_interes_id "
                + "LEFT JOIN ubicacion_lugar ul ON ul.lugar_interes_id = li.lugar_interes_id "
                + "LEFT JOIN informacion_historica ih ON ih.lugar_interes_id = li.lugar_interes_id "
                + "LEFT JOIN imagenes_lugar img1 ON img1.lugar_interes_id = li.lugar_interes_id AND img1.imagen_lugar_id = ("
                + "    SELECT imagen_lugar_id FROM imagenes_lugar WHERE lugar_interes_id = li.lugar_interes_id ORDER BY imagen_lugar_id ASC LIMIT 1"
                + ") "
                + "LEFT JOIN imagenes_lugar img2 ON img2.lugar_interes_id = li.lugar_interes_id AND img2.imagen_lugar_id = ("
                + "    SELECT imagen_lugar_id FROM imagenes_lugar WHERE lugar_interes_id = li.lugar_interes_id ORDER BY imagen_lugar_id ASC OFFSET 1 LIMIT 1"
                + ") "
                + "LEFT JOIN imagenes_lugar img3 ON img3.lugar_interes_id = li.lugar_interes_id AND img3.imagen_lugar_id = ("
                + "    SELECT imagen_lugar_id FROM imagenes_lugar WHERE lugar_interes_id = li.lugar_interes_id ORDER BY imagen_lugar_id ASC OFFSET 2 LIMIT 1"
                + ") "
                + "WHERE li.lugar_interes_id = ?";

        try ( Connection conn = ConexionHuellasCuencanas.conectar();  PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, lugarInteresId);

            try ( ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    detalle = new MuseoDetalleVista(
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

    //Listar los museos:
    public List<Museo> listarTodas() throws SQLException {
        String sql = "SELECT id, lugar_interes_id, hora_apertura, hora_cierre FROM museo";
        List<Museo> lista = new ArrayList<>();

        try ( Connection conn = ConexionHuellasCuencanas.conectar();  PreparedStatement ps = conn.prepareStatement(sql);  ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Museo m = new Museo(
                        rs.getInt("lugar_interes_id"),
                        rs.getTime("hora_apertura").toLocalTime(),
                        rs.getTime("hora_cierre").toLocalTime()
                );
                lista.add(m);
            }
        }

        return lista;
    }

    
     //Actualizar Museo:
    public boolean actualizarCompleto(
            int lugarInteresId,
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
        String sqlLugar = "UPDATE lugares_interes      SET nombre       = ? WHERE lugar_interes_id = ?";
        String sqlUbic = "UPDATE ubicacion_lugar     SET direccion    = ?, latitud = ?, longitud = ? WHERE lugar_interes_id = ?";
        String sqlHist = "UPDATE informacion_historica SET descripcion = ? WHERE lugar_interes_id = ?";
        String sqlMus = "UPDATE museo                SET hora_apertura = ?, hora_cierre = ? WHERE lugar_interes_id = ?";
        String sqlDelImgs = "DELETE FROM imagenes_lugar   WHERE lugar_interes_id = ?";
        String sqlInsImg = "INSERT INTO imagenes_lugar(lugar_interes_id, imagen, tamano) VALUES (?, ?, ?)";

        try ( Connection conn = ConexionHuellasCuencanas.conectar()) {
            conn.setAutoCommit(false);
            try (
                     PreparedStatement psl = conn.prepareStatement(sqlLugar);  PreparedStatement psu = conn.prepareStatement(sqlUbic);  PreparedStatement psh = conn.prepareStatement(sqlHist);  PreparedStatement psm = conn.prepareStatement(sqlMus);  PreparedStatement psd = conn.prepareStatement(sqlDelImgs);  PreparedStatement psi2 = conn.prepareStatement(sqlInsImg)) {
                psl.setString(1, nombre);
                psl.setInt(2, lugarInteresId);
                psl.executeUpdate();

                psu.setString(1, direccion);
                psu.setDouble(2, latitud);
                psu.setDouble(3, longitud);
                psu.setInt(4, lugarInteresId);
                psu.executeUpdate();

                psh.setString(1, descripcion);
                psh.setInt(2, lugarInteresId);
                psh.executeUpdate();

                psm.setTime(1, java.sql.Time.valueOf(horaApertura));
                psm.setTime(2, java.sql.Time.valueOf(horaCierre));
                psm.setInt(3, lugarInteresId);
                psm.executeUpdate();

                psd.setInt(1, lugarInteresId);
                psd.executeUpdate();

                int count = 0;
                for (byte[] img : new byte[][]{img1, img2, img3}) {
                    if (img != null) {
                        psi2.setInt(1, lugarInteresId);
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
    //Buscar museos por nombre:
    public List<MuseoVista> buscarSoloTexto(String texto) throws SQLException {
        String sql
                = "SELECT li.lugar_interes_id AS id, li.nombre, m.hora_apertura, m.hora_cierre "
                + "FROM lugares_interes li "
                + "JOIN museo m ON m.lugar_interes_id = li.lugar_interes_id "
                + "WHERE LOWER(li.nombre) LIKE ?";
        List<MuseoVista> lista = new ArrayList<>();
        String patron = "%" + texto.toLowerCase() + "%";

        try ( Connection conn = ConexionHuellasCuencanas.conectar();  PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, patron);
            try ( ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    MuseoVista mv = new MuseoVista(
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
    public boolean eliminarLugarInteresCompleto(int lugarInteresId) throws SQLException {
        String sql = "DELETE FROM lugares_interes WHERE lugar_interes_id = ?";
        try ( Connection conn = ConexionHuellasCuencanas.conectar();  PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, lugarInteresId);
            return ps.executeUpdate() > 0;
        }
    }

    //Obtener museo por id:
    public MuseoVista obtenerPorId(int lugarInteresId) throws SQLException {
        String sql
                = "SELECT li.lugar_interes_id, li.nombre, m.hora_apertura, m.hora_cierre, img.imagen "
                + "FROM museo m "
                + "JOIN lugares_interes li ON m.lugar_interes_id = li.lugar_interes_id "
                + "LEFT JOIN LATERAL ( "
                + "    SELECT imagen "
                + "    FROM imagenes_lugar il "
                + "    WHERE il.lugar_interes_id = li.lugar_interes_id "
                + "    ORDER BY il.imagen_lugar_id LIMIT 1 "
                + ") img ON true "
                + "WHERE m.lugar_interes_id = ?";

        try ( Connection conn = ConexionHuellasCuencanas.conectar();  PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, lugarInteresId);

            try ( ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String nombre = rs.getString("nombre");
                    Time ha = rs.getTime("hora_apertura");
                    Time hc = rs.getTime("hora_cierre");
                    byte[] imgBytes = rs.getBytes("imagen");

                    LocalTime apertura = (ha != null) ? ha.toLocalTime() : null;
                    LocalTime cierre = (hc != null) ? hc.toLocalTime() : null;

                    return new MuseoVista(
                            lugarInteresId,
                            nombre,
                            apertura,
                            cierre,
                            imgBytes
                    );
                }
                return null;
            }
        }
    }

    //Obtener favoritos de un usuario:
    public List<Integer> obtenerFavoritosMuseos(int idPersona) throws SQLException {
        String sql
                = "SELECT f.id_lugar_interes "
                + "FROM favorito f "
                + "JOIN museo m ON f.id_lugar_interes = m.lugar_interes_id "
                + "WHERE f.id_persona = ?";
        List<Integer> favoritos = new ArrayList<>();
        try ( Connection conn = ConexionHuellasCuencanas.conectar();  PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idPersona);
            try ( ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    favoritos.add(rs.getInt("id_lugar_interes"));
                }
            }
        }
        return favoritos;
    }

}
