package Modelo;

import ConexionHuellasCuencanas.ConexionHuellasCuencanas;
import java.sql.*;
import java.util.*;

public class FavoritoDAO {

    // Agrega un favorito genérico (evita duplicados)
    public boolean agregarFavorito(int idPersona, int idLugar) throws SQLException {
        String sql = "INSERT INTO favorito (id_persona, id_lugar_interes) VALUES (?, ?) ON CONFLICT DO NOTHING";
        try ( Connection conn = ConexionHuellasCuencanas.conectar();  PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idPersona);
            stmt.setInt(2, idLugar);
            return stmt.executeUpdate() > 0;
        }
    }

    // Elimina un favorito
    public boolean quitarFavorito(int idPersona, int idLugar) throws SQLException {
        String sql = "DELETE FROM favorito WHERE id_persona = ? AND id_lugar_interes = ?";
        try ( Connection conn = ConexionHuellasCuencanas.conectar();  PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idPersona);
            stmt.setInt(2, idLugar);
            return stmt.executeUpdate() > 0;
        }
    }

    // Verifica si un lugar es favorito para el usuario
    public boolean esFavorito(int idPersona, int idLugar) throws SQLException {
        String sql = "SELECT 1 FROM favorito WHERE id_persona = ? AND id_lugar_interes = ?";
        try ( Connection conn = ConexionHuellasCuencanas.conectar();  PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idPersona);
            stmt.setInt(2, idLugar);
            try ( ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }
        }
    }

    // Obtiene IDs de iglesias favoritas de un usuario
    public List<Integer> obtenerFavoritosIglesias(int idPersona) throws SQLException {
        String sql
                = "SELECT id_lugar_interes "
                + "FROM favorito "
                + "WHERE id_persona = ? "
                + "AND id_lugar_interes IN (SELECT lugar_interes_id FROM iglesia)";

        List<Integer> favoritos = new ArrayList<>();

        try ( Connection conn = ConexionHuellasCuencanas.conectar();  PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idPersona);
            try ( ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int idLugar = rs.getInt("id_lugar_interes");
                    System.out.println("Favorito Iglesia ID encontrado: " + idLugar);
                    favoritos.add(idLugar);
                }
            }
        }
        return favoritos;
    }

    //Obtener IDs de los museos favoritos de un usuario: 
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

    //Obtener IDs de los parques favoritos de un usuario: 
    public List<Integer> obtenerFavoritosParques(int idPersona) throws SQLException {
        String sql
                = "SELECT f.id_lugar_interes "
                + "FROM favorito f "
                + "JOIN parque p ON f.id_lugar_interes = p.lugar_interes_id "
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

    //Contar los favoritos de un usuario:
    public int contarFavoritos(int idPersona) throws SQLException {
        String sql = "SELECT COUNT(*) FROM favorito WHERE id_persona = ?";
        try ( Connection conn = ConexionHuellasCuencanas.conectar();  PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idPersona);
            try ( ResultSet rs = ps.executeQuery()) {
                rs.next();
                return rs.getInt(1);
            }
        }
    }

    //Contar los favoritos por mes:
    public int contarFavoritosMes(int idPersona) throws SQLException {
        String sql
                = "SELECT COUNT(*) "
                + "FROM favorito "
                + "WHERE id_persona = ? "
                + "  AND DATE_TRUNC('month', fecha_agregado) = DATE_TRUNC('month', CURRENT_DATE)";
        try ( Connection conn = ConexionHuellasCuencanas.conectar();  PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idPersona);
            try ( ResultSet rs = ps.executeQuery()) {
                rs.next();
                return rs.getInt(1);
            }
        }
    }
    // Cuentar iglesias favoritas de un usuario

    public int contarFavoritosIglesias(int idPersona) throws SQLException {
        String sql
                = "SELECT COUNT(*) "
                + "FROM favorito f "
                + "JOIN iglesia i ON f.id_lugar_interes = i.lugar_interes_id "
                + "WHERE f.id_persona = ?";
        try ( Connection conn = ConexionHuellasCuencanas.conectar();  PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idPersona);
            try ( ResultSet rs = ps.executeQuery()) {
                rs.next();
                return rs.getInt(1);
            }
        }
    }

// Cuentar museos favoritos de un usuario
    public int contarFavoritosMuseos(int idPersona) throws SQLException {
        String sql
                = "SELECT COUNT(*) "
                + "FROM favorito f "
                + "JOIN museo m ON f.id_lugar_interes = m.lugar_interes_id "
                + "WHERE f.id_persona = ?";
        try ( Connection conn = ConexionHuellasCuencanas.conectar();  PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idPersona);
            try ( ResultSet rs = ps.executeQuery()) {
                rs.next();
                return rs.getInt(1);
            }
        }
    }

// Cuentar parques favoritos de un usuario
    public int contarFavoritosParques(int idPersona) throws SQLException {
        String sql
                = "SELECT COUNT(*) "
                + "FROM favorito f "
                + "JOIN parque p ON f.id_lugar_interes = p.lugar_interes_id "
                + "WHERE f.id_persona = ?";
        try ( Connection conn = ConexionHuellasCuencanas.conectar();  PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idPersona);
            try ( ResultSet rs = ps.executeQuery()) {
                rs.next();
                return rs.getInt(1);
            }
        }
    }

}
