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

        String sql = "SELECT "
                + "li.lugar_interes_id, "
                + "li.nombre, "
                + "i.hora_apertura, "
                + "i.hora_cierre, "
                + "ul.latitud, "
                + "ul.longitud, "
                + "ul.direccion, "
                + "ih.descripcion, "
                + "img1.imagen AS imagen1, "
                + "img2.imagen AS imagen2, "
                + "img3.imagen AS imagen3 "
                + "FROM lugares_interes li "
                + "JOIN iglesia i ON i.lugar_interes_id = li.lugar_interes_id "
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

    //Devuelve la lista “plana” de Iglesias (entidad pura), para uso genérico
    public List<Iglesia> listarTodas() throws SQLException {
        String sql = "SELECT id, lugar_interes_id, hora_apertura, hora_cierre FROM iglesia";
        List<Iglesia> lista = new ArrayList<>();

        try ( Connection conn = ConexionHuellasCuencanas.conectar();  PreparedStatement ps = conn.prepareStatement(sql);  ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Iglesia ig = new Iglesia(
                        rs.getInt("lugar_interes_id"),
                        rs.getTime("hora_apertura").toLocalTime(),
                        rs.getTime("hora_cierre").toLocalTime()
                );
                lista.add(ig);
            }
        }
        return lista;
    }

    //Buscar iglesias por nombre:
    public List<IglesiaVista> buscarSoloTexto(String texto) throws SQLException {
        String sql
                = "SELECT li.lugar_interes_id AS id, li.nombre, i.hora_apertura, i.hora_cierre "
                + "FROM lugares_interes li "
                + "JOIN iglesia i ON i.lugar_interes_id = li.lugar_interes_id "
                + "WHERE LOWER(li.nombre) LIKE ?";
        List<IglesiaVista> lista = new ArrayList<>();
        String patron = "%" + texto.toLowerCase() + "%";

        try ( Connection conn = ConexionHuellasCuencanas.conectar();  PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, patron);
            try ( ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(new IglesiaVista(
                            rs.getInt("id"),
                            rs.getString("nombre"),
                            rs.getTime("hora_apertura").toLocalTime(),
                            rs.getTime("hora_cierre").toLocalTime(),
                            null
                    ));
                }
            }
        }
        return lista;
    }

    //Eliminar por completo iglesias:
    public boolean eliminarLugarInteresCompleto(int lugarInteresId) throws SQLException {
        String sql = "DELETE FROM lugares_interes WHERE lugar_interes_id = ?";
        try ( Connection conn = ConexionHuellasCuencanas.conectar();  PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, lugarInteresId);
            return ps.executeUpdate() > 0;
        }
    }

    //Actualizar por completo:
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
        String sqlLugar = "UPDATE lugares_interes SET nombre = ? WHERE lugar_interes_id = ?";
        String sqlUbic = "UPDATE ubicacion_lugar SET direccion = ?, latitud = ?, longitud = ? WHERE lugar_interes_id = ?";
        String sqlHist = "UPDATE informacion_historica SET descripcion = ? WHERE lugar_interes_id = ?";
        String sqlIgl = "UPDATE iglesia SET hora_apertura = ?, hora_cierre = ? WHERE lugar_interes_id = ?";
        String sqlDelImgs = "DELETE FROM imagenes_lugar WHERE lugar_interes_id = ?";
        String sqlInsImg = "INSERT INTO imagenes_lugar(lugar_interes_id, imagen, tamano) VALUES (?, ?, ?)";

        try ( Connection conn = ConexionHuellasCuencanas.conectar()) {
            conn.setAutoCommit(false);
            try (
                     PreparedStatement psl = conn.prepareStatement(sqlLugar);  PreparedStatement psu = conn.prepareStatement(sqlUbic);  PreparedStatement psh = conn.prepareStatement(sqlHist);  PreparedStatement psi = conn.prepareStatement(sqlIgl);  PreparedStatement psd = conn.prepareStatement(sqlDelImgs);  PreparedStatement psi2 = conn.prepareStatement(sqlInsImg)) {
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

                psi.setTime(1, Time.valueOf(horaApertura));
                psi.setTime(2, Time.valueOf(horaCierre));
                psi.setInt(3, lugarInteresId);
                psi.executeUpdate();

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
                return count > 0;
            } catch (SQLException e) {
                conn.rollback();
                throw e;
            }
        }
    }

    //Obtener iglesias favoritas:
    public List<IglesiaVista> obtenerIglesiasFavoritas(int idUsuario) throws SQLException {
        List<IglesiaVista> lista = new ArrayList<>();
        String sql
                = "SELECT li.lugar_interes_id AS id, "
                + "       li.nombre, "
                + "       i.hora_apertura, "
                + "       i.hora_cierre, "
                + "       ( "
                + "         SELECT imagen "
                + "         FROM imagenes_lugar il "
                + "         WHERE il.lugar_interes_id = li.lugar_interes_id "
                + "         ORDER BY il.imagen_lugar_id "
                + "         LIMIT 1"
                + "       ) AS imagen_principal "
                + "FROM lugares_interes li "
                + "JOIN iglesia i ON li.lugar_interes_id = i.lugar_interes_id "
                + "JOIN favorito f  ON li.lugar_interes_id = f.id_lugar_interes "
                + "WHERE f.id_persona = ?;";

        try ( Connection conn = ConexionHuellasCuencanas.conectar();  PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idUsuario);

            try ( ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(new IglesiaVista(
                            rs.getInt("id"),
                            rs.getString("nombre"),
                            rs.getTime("hora_apertura") != null
                            ? rs.getTime("hora_apertura").toLocalTime()
                            : null,
                            rs.getTime("hora_cierre") != null
                            ? rs.getTime("hora_cierre").toLocalTime()
                            : null,
                            rs.getBytes("imagen_principal")
                    ));
                }
            }
        }

        return lista;
    }

    //Obtener iglesia por id (Para favoritos): 
    public IglesiaVista obtenerPorId(int lugarInteresId) throws SQLException {
        String sql
                = "SELECT li.lugar_interes_id, li.nombre, i.hora_apertura, i.hora_cierre, img.imagen "
                + "FROM iglesia i "
                + "JOIN lugares_interes li ON i.lugar_interes_id = li.lugar_interes_id "
                + "LEFT JOIN LATERAL ( "
                + "    SELECT imagen "
                + "    FROM imagenes_lugar il "
                + "    WHERE il.lugar_interes_id = li.lugar_interes_id "
                + "    ORDER BY il.imagen_lugar_id LIMIT 1 "
                + ") img ON true "
                + "WHERE i.lugar_interes_id = ?";
        try ( Connection conn = ConexionHuellasCuencanas.conectar();  PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, lugarInteresId);
            try ( ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String nombre = rs.getString("nombre");
                    Time ha = rs.getTime("hora_apertura");
                    Time hc = rs.getTime("hora_cierre");
                    byte[] img = rs.getBytes("imagen");
                    LocalTime apertura = ha != null ? ha.toLocalTime() : null;
                    LocalTime cierre = hc != null ? hc.toLocalTime() : null;
                    return new IglesiaVista(lugarInteresId, nombre, apertura, cierre, img);
                }
                return null;
            }
        }
    }
}
