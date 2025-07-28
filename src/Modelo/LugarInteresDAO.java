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
import java.util.ArrayList;
import java.util.List;

public class LugarInteresDAO {

    public static int insertar(Connection con, LugarInteres lugar) throws SQLException {
        String sql = "INSERT INTO lugares_interes(nombre) VALUES (?) RETURNING lugar_interes_id";
        try ( PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, lugar.getNombre());
            try ( ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("lugar_interes_id");
                } else {
                    throw new SQLException("No se pudo obtener el ID del lugar.");
                }
            }
        }
    }

    public static boolean existeNombre(Connection con, String nombre) throws SQLException {
        String sql = "SELECT 1 FROM lugares_interes WHERE LOWER(nombre) = LOWER(?) LIMIT 1";
        try ( PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, nombre.trim());
            try ( ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        }
    }

    //Contar todos los lugares que hay:
    public int contarLugares() {
        String sql = "SELECT COUNT(*) FROM lugares_interes";
        try ( Connection conn = ConexionHuellasCuencanas.conectar();  PreparedStatement ps = conn.prepareStatement(sql);  ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    //Contar todas las imágenes que hay:
    public int contarImagenes() {
        String sql = "SELECT COUNT(*) FROM imagenes_lugar";
        try ( Connection conn = ConexionHuellasCuencanas.conectar();  PreparedStatement ps = conn.prepareStatement(sql);  ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    //Contar todas las decripciones históricas:  
    public int contarDescripciones() {
        String sql = "SELECT COUNT(*) FROM informacion_historica";
        try ( Connection conn = ConexionHuellasCuencanas.conectar();  PreparedStatement ps = conn.prepareStatement(sql);  ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    //Contar todas las iglesias:
    public int contarIglesias() {
        String sql = "SELECT COUNT(*) FROM iglesia";
        try ( Connection conn = ConexionHuellasCuencanas.conectar();  PreparedStatement ps = conn.prepareStatement(sql);  ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    //Contar todos los museos:
    public int contarMuseos() {
        String sql = "SELECT COUNT(*) FROM museo";
        try ( Connection conn = ConexionHuellasCuencanas.conectar();  PreparedStatement ps = conn.prepareStatement(sql);  ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    //Contar todos los parques:
    public int contarParques() {
        String sql = "SELECT COUNT(*) FROM parque";
        try ( Connection conn = ConexionHuellasCuencanas.conectar();  PreparedStatement ps = conn.prepareStatement(sql);  ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int contarHorariosIglesias() {
        int total = 0;
        String sql = "SELECT COUNT(*) FROM iglesia WHERE hora_apertura IS NOT NULL AND hora_cierre IS NOT NULL";
        try ( Connection conn = ConexionHuellasCuencanas.conectar();  PreparedStatement ps = conn.prepareStatement(sql);  ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                total = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return total;
    }

    public int contarHorariosMuseos() {
        int total = 0;
        String sql = "SELECT COUNT(*) FROM museo WHERE hora_apertura IS NOT NULL AND hora_cierre IS NOT NULL";
        try ( Connection conn = ConexionHuellasCuencanas.conectar();  PreparedStatement ps = conn.prepareStatement(sql);  ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                total = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return total;
    }

    //Contar los horarios de los parques (Siempre será 0 porque no hay horarios para parques):
    public int contarHorariosParques() {
        return 0;
    }

    //Contar los registros históricos existentes:
    public int contarRegistrosHistoricos() {
        int total = 0;
        String sql = "SELECT COUNT(*) FROM informacion_historica";
        try ( Connection conn = ConexionHuellasCuencanas.conectar();  PreparedStatement ps = conn.prepareStatement(sql);  ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                total = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return total;
    }

    //Cargar las iglesias para el User:
    public List<IglesiaVistaUser> obtenerIglesias() {
        List<IglesiaVistaUser> lista = new ArrayList<>();

        String sql = "SELECT li.lugar_interes_id, li.nombre, ul.direccion "
                + "FROM iglesia i "
                + "JOIN lugares_interes li ON i.lugar_interes_id = li.lugar_interes_id "
                + "LEFT JOIN ubicacion_lugar ul ON li.lugar_interes_id = ul.lugar_interes_id "
                + "ORDER BY li.lugar_interes_id DESC LIMIT 5";

        try ( Connection conn = ConexionHuellasCuencanas.conectar();  PreparedStatement ps = conn.prepareStatement(sql);  ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("lugar_interes_id");
                String nombre = rs.getString("nombre");
                String direccion = rs.getString("direccion");

                byte[] imagenPrincipal = null;
                String sqlImg = "SELECT imagen FROM imagenes_lugar WHERE lugar_interes_id = ? LIMIT 1";
                try ( PreparedStatement psImg = conn.prepareStatement(sqlImg)) {
                    psImg.setInt(1, id);
                    ResultSet rsImg = psImg.executeQuery();
                    if (rsImg.next()) {
                        imagenPrincipal = rsImg.getBytes("imagen");
                    }
                }

                lista.add(new IglesiaVistaUser(id, nombre, direccion, imagenPrincipal));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    //Mostrar los parques al User: 
    public List<ParqueVistaUser> obtenerParques() {
        List<ParqueVistaUser> lista = new ArrayList<>();

        String sql = "SELECT li.lugar_interes_id, li.nombre, ul.direccion "
                + "FROM parque p "
                + "JOIN lugares_interes li ON p.lugar_interes_id = li.lugar_interes_id "
                + "LEFT JOIN ubicacion_lugar ul ON li.lugar_interes_id = ul.lugar_interes_id "
                + "ORDER BY li.lugar_interes_id DESC LIMIT 5";

        try ( Connection conn = ConexionHuellasCuencanas.conectar();  PreparedStatement ps = conn.prepareStatement(sql);  ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("lugar_interes_id");
                String nombre = rs.getString("nombre");
                String direccion = rs.getString("direccion");

                byte[] imagenPrincipal = null;
                String sqlImg = "SELECT imagen FROM imagenes_lugar WHERE lugar_interes_id = ? LIMIT 1";
                try ( PreparedStatement psImg = conn.prepareStatement(sqlImg)) {
                    psImg.setInt(1, id);
                    ResultSet rsImg = psImg.executeQuery();
                    if (rsImg.next()) {
                        imagenPrincipal = rsImg.getBytes("imagen");
                    }
                }

                lista.add(new ParqueVistaUser(id, nombre, direccion, imagenPrincipal));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    //Mostrar los museos para el user:
    public List<MuseoVistaUser> obtenerMuseos() {
        List<MuseoVistaUser> lista = new ArrayList<>();

        String sql = "SELECT li.lugar_interes_id, li.nombre, ul.direccion "
                + "FROM museo m "
                + "JOIN lugares_interes li ON m.lugar_interes_id = li.lugar_interes_id "
                + "LEFT JOIN ubicacion_lugar ul ON li.lugar_interes_id = ul.lugar_interes_id "
                + "ORDER BY li.lugar_interes_id DESC LIMIT 5";

        try ( Connection conn = ConexionHuellasCuencanas.conectar();  PreparedStatement ps = conn.prepareStatement(sql);  ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("lugar_interes_id");
                String nombre = rs.getString("nombre");
                String direccion = rs.getString("direccion");

                byte[] imagenPrincipal = null;
                String sqlImg = "SELECT imagen FROM imagenes_lugar WHERE lugar_interes_id = ? LIMIT 1";
                try ( PreparedStatement psImg = conn.prepareStatement(sqlImg)) {
                    psImg.setInt(1, id);
                    ResultSet rsImg = psImg.executeQuery();
                    if (rsImg.next()) {
                        imagenPrincipal = rsImg.getBytes("imagen");
                    }
                }

                lista.add(new MuseoVistaUser(id, nombre, direccion, imagenPrincipal));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    //Cargar los lugares existentes:
    public List<LugarInteresVista> obtenerLugares() throws SQLException {
        String sql = "SELECT lugar_interes_id, nombre FROM lugares_interes";
        try ( Connection c = ConexionHuellasCuencanas.conectar();  PreparedStatement ps = c.prepareStatement(sql);  ResultSet rs = ps.executeQuery()) {
            List<LugarInteresVista> lista = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt("lugar_interes_id");
                String nm = rs.getString("nombre");
                lista.add(new LugarInteresVista(id, nm));
            }
            return lista;
        }
    }
}
