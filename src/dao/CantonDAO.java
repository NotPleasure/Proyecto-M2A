/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import ConexionHuellasCuencanas.ConexionHuellasCuencanas;
import model.Canton;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CantonDAO {

    private Connection conn;

    public CantonDAO() {
        try {
            conn = ConexionHuellasCuencanas.conectar();
        } catch (SQLException e) {
            System.err.println("Error de conexión: " + e.getMessage());
        }
    }

    public boolean crear(Canton canton) {
        String sql = "INSERT INTO cantones (nombre, provincia_id) VALUES (?, ?)";
        try ( PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, canton.getNombre());
            stmt.setInt(2, canton.getProvinciaId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al crear cantón: " + e.getMessage());
            return false;
        }
    }

    public boolean actualizar(Canton canton) {
        String sql = "UPDATE cantones SET nombre = ?, provincia_id = ? WHERE id = ?";
        try ( PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, canton.getNombre());
            stmt.setInt(2, canton.getProvinciaId());
            stmt.setInt(3, canton.getId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al actualizar cantón: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminar(int id) {
        String sql = "DELETE FROM cantones WHERE id = ?";
        try ( PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al eliminar cantón: " + e.getMessage());
            return false;
        }
    }

    public Canton leer(int id) {
        String sql = "SELECT * FROM cantones WHERE id = ?";
        try ( PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Canton(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getInt("provincia_id")
                );
            }
        } catch (SQLException e) {
            System.err.println("Error al leer cantón: " + e.getMessage());
        }
        return null;
    }

    public Canton leerPorNombre(String nombre) {
        String sql = "SELECT * FROM cantones WHERE LOWER(nombre) = LOWER(?)";
        try ( PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nombre);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Canton(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getInt("provincia_id")
                );
            }
        } catch (SQLException e) {
            System.err.println("Error al leer por nombre: " + e.getMessage());
        }
        return null;
    }

    public List<Canton> listarTodos() {
        List<Canton> lista = new ArrayList<>();
        String sql = "SELECT * FROM cantones ORDER BY id";
        try ( Statement stmt = conn.createStatement();  ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Canton c = new Canton(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getInt("provincia_id")
                );
                lista.add(c);
            }
        } catch (SQLException e) {
            System.err.println("Error al listar cantones: " + e.getMessage());
        }
        return lista;
    }

    public List<Canton> listarPorProvincia(int provinciaId) {
        List<Canton> lista = new ArrayList<>();
        String sql = "SELECT * FROM cantones WHERE provincia_id = ? ORDER BY nombre";
        try ( PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, provinciaId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Canton c = new Canton(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getInt("provincia_id")
                );
                lista.add(c);
            }
        } catch (SQLException e) {
            System.err.println("Error al listar cantones por provincia: " + e.getMessage());
        }
        return lista;
    }

    public boolean existePorNombreYProvincia(String nombre, int provinciaId) {
        String sql = "SELECT 1 FROM cantones WHERE LOWER(nombre) = LOWER(?) AND provincia_id = ?";
        try ( PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nombre);
            stmt.setInt(2, provinciaId);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            System.err.println("Error al verificar existencia: " + e.getMessage());
            return false;
        }
    }
}
