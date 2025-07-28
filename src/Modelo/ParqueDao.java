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
public class ParqueDao {

    public static void insertar(Connection con, Parque parque) throws SQLException {
        String sql = "INSERT INTO parque(lugar_interes_id, entidad_gestora, superficie) VALUES (?, ?, ?)";
        try ( PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, parque.getLugarInteresId());
            ps.setString(2, parque.getEntidad_gestora());
            ps.setFloat(3, parque.getSuperficie());
            ps.executeUpdate();
        }
    }

    public List<ParqueVista> obtenerParquesVista() throws SQLException {
        List<ParqueVista> lista = new ArrayList<>();

        String sql = "SELECT li.lugar_interes_id, li.nombre, p.entidad_gestora, p.superficie, img.imagen "
                + "FROM lugares_interes li "
                + "JOIN parque p ON p.lugar_interes_id = li.lugar_interes_id "
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
                String entidad_gestora = rs.getString("entidad_gestora");
                float superficie = rs.getFloat("superficie");
                byte[] imagen = rs.getBytes("imagen");

                lista.add(new ParqueVista(id, nombre, superficie, entidad_gestora, imagen));
            }
        }

        return lista;
    }

    public ParqueDetalleVista obtenerDetalleParque(int lugarInteresId) throws SQLException {

        ParqueDetalleVista detalle = null;
        String sql = "SELECT "
                + "li.lugar_interes_id, "
                + "li.nombre, "
                + " p.entidad_gestora, "
                + "p.superficie, "
                + "ul.latitud, "
                + "ul.longitud, "
                + "ul.direccion, "
                + "ih.descripcion, "
                + "img1.imagen AS imagen1, "
                + "img2.imagen AS imagen2, "
                + "img3.imagen AS imagen3 "
                + "FROM lugares_interes li "
                + "JOIN parque p ON p.lugar_interes_id = li.lugar_interes_id "
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
                    detalle = new ParqueDetalleVista(
                            rs.getInt("lugar_interes_id"),
                            rs.getString("nombre"),
                            rs.getString("entidad_gestora"),
                            rs.getFloat("superficie"),
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

    //Listar todos los parques:
    public List<Parque> listarTodas() throws SQLException {
        String sql = "SELECT lugar_interes_id, entidad_gestora, superficie FROM parque";
        List<Parque> lista = new ArrayList<>();

        try ( Connection conn = ConexionHuellasCuencanas.conectar();  PreparedStatement ps = conn.prepareStatement(sql);  ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Parque p = new Parque(
                        rs.getInt("lugar_interes_id"),
                        rs.getString("entidad_gestora"),
                        rs.getFloat("superficie")
                );
                lista.add(p);
            }
        }

        return lista;
    }

    //Buscar los parques por nombres:
    public List<ParqueVista> buscarSoloTexto(String texto) throws SQLException {
        String sql
                = "SELECT li.lugar_interes_id AS id, li.nombre, p.entidad_gestora, p.superficie "
                + "FROM lugares_interes li "
                + "JOIN parque p ON p.lugar_interes_id = li.lugar_interes_id "
                + "WHERE LOWER(li.nombre) LIKE ?";
        List<ParqueVista> lista = new ArrayList<>();
        String patron = "%" + texto.toLowerCase() + "%";

        try ( Connection conn = ConexionHuellasCuencanas.conectar();  PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, patron);
            try ( ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    ParqueVista pv = new ParqueVista(
                            rs.getInt("id"),
                            rs.getString("nombre"),
                            rs.getFloat("superficie"),
                            rs.getString("entidad_gestora"),
                            null
                    );

                    lista.add(pv);
                }
            }
        }

        return lista;
    }

    //Eliminar por completo un parque: 
    public boolean eliminarLugarInteresCompleto(int lugarInteresId) throws SQLException {
        String sql = "DELETE FROM lugares_interes WHERE lugar_interes_id = ?";
        try ( Connection conn = ConexionHuellasCuencanas.conectar();  PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, lugarInteresId);
            return ps.executeUpdate() > 0;
        }
    }

    //Actualizar completo:
    public boolean actualizarCompleto(
            int lugarInteresId,
            String nombre,
            String direccion,
            double latitud,
            double longitud,
            String descripcion,
            String entidadGestora,
            float superficie,
            byte[] img1,
            byte[] img2,
            byte[] img3
    ) throws SQLException {
        String sqlLugar = "UPDATE lugares_interes        SET nombre        = ? WHERE lugar_interes_id = ?";
        String sqlUbic = "UPDATE ubicacion_lugar       SET direccion     = ?, latitud = ?, longitud = ? WHERE lugar_interes_id = ?";
        String sqlHist = "UPDATE informacion_historica SET descripcion   = ? WHERE lugar_interes_id = ?";
        String sqlParque = "UPDATE parque               SET entidad_gestora = ?, superficie = ? WHERE lugar_interes_id = ?";
        String sqlDelImgs = "DELETE FROM imagenes_lugar   WHERE lugar_interes_id = ?";
        String sqlInsImg = "INSERT INTO imagenes_lugar(lugar_interes_id, imagen, tamano) VALUES (?, ?, ?)";

        try ( Connection conn = ConexionHuellasCuencanas.conectar()) {
            conn.setAutoCommit(false);
            try (
                     PreparedStatement psl = conn.prepareStatement(sqlLugar);  PreparedStatement psu = conn.prepareStatement(sqlUbic);  PreparedStatement psh = conn.prepareStatement(sqlHist);  PreparedStatement psp = conn.prepareStatement(sqlParque);  PreparedStatement psd = conn.prepareStatement(sqlDelImgs);  PreparedStatement psi2 = conn.prepareStatement(sqlInsImg)) {
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

                psp.setString(1, entidadGestora);
                psp.setFloat(2, superficie);
                psp.setInt(3, lugarInteresId);
                psp.executeUpdate();

                psd.setInt(1, lugarInteresId);
                psd.executeUpdate();

                for (byte[] img : new byte[][]{img1, img2, img3}) {
                    if (img != null) {
                        psi2.setInt(1, lugarInteresId);
                        psi2.setBytes(2, img);
                        psi2.setInt(3, img.length);
                        psi2.executeUpdate();
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

    

    //Listar favoritos por id:
    public ParqueVista obtenerPorId(int lugarInteresId) throws SQLException {
        String sql
                = "SELECT li.lugar_interes_id, li.nombre, p.entidad_gestora, p.superficie, img.imagen "
                + "FROM parque p "
                + "JOIN lugares_interes li ON p.lugar_interes_id = li.lugar_interes_id "
                + "LEFT JOIN LATERAL ( "
                + "    SELECT imagen FROM imagenes_lugar il "
                + "    WHERE il.lugar_interes_id = li.lugar_interes_id "
                + "    ORDER BY il.imagen_lugar_id LIMIT 1 "
                + ") img ON true "
                + "WHERE p.lugar_interes_id = ?";

        try ( Connection conn = ConexionHuellasCuencanas.conectar();  PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, lugarInteresId);
            try ( ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String nombre = rs.getString("nombre");
                    String entidadGestora = rs.getString("entidad_gestora");
                    double superficieDb = rs.getDouble("superficie");
                    byte[] imgBytes = rs.getBytes("imagen");

                    float superficie = (float) superficieDb;

                    return new ParqueVista(
                            lugarInteresId,
                            nombre,
                            superficie,
                            entidadGestora,
                            imgBytes
                    );
                }
                return null;
            }
        }
    }

}
