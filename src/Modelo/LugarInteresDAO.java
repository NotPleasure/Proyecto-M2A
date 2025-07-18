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

    public int contarHorariosParques() {
        return 0;
    }

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

}
