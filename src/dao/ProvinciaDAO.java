/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import ConexionHuellasCuencanas.ConexionHuellasCuencanas;
import model.Provincia;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProvinciaDAO {
    
   // Método para obtener el próximo ID disponible
    private int obtenerProximoId() {
        String sql = "SELECT COALESCE(MAX(id), 0) + 1 FROM provincias";
        
        try (Connection conn = ConexionHuellasCuencanas.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            if (rs.next()) {
                return rs.getInt(1);
            }
            
        } catch (SQLException e) {
            System.err.println("Error al obtener próximo ID: " + e.getMessage());
        }
        return 1; // Si hay error, empezar desde 1
    }
    
    // CREATE - Crear nueva provincia
    public boolean crear(Provincia provincia) {
        String sql = "INSERT INTO provincias (id, nombre) VALUES (?, ?)";
        
        try (Connection conn = ConexionHuellasCuencanas.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            // Obtener el próximo ID disponible
            int nuevoId = obtenerProximoId();
            
            stmt.setInt(1, nuevoId);
            stmt.setString(2, provincia.getNombre());
            
            int filasAfectadas = stmt.executeUpdate();
            
            if (filasAfectadas > 0) {
                provincia.setId(nuevoId);
                return true;
            }
            
        } catch (SQLException e) {
            System.err.println("Error al crear provincia: " + e.getMessage());
        }
        return false;
    }
    
    // READ - Leer provincia por ID
    public Provincia leer(int id) {
        String sql = "SELECT * FROM provincias WHERE id = ?";
        
        try (Connection conn = ConexionHuellasCuencanas.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return new Provincia(
                    rs.getInt("id"),
                    rs.getString("nombre")
                );
            }
            
        } catch (SQLException e) {
            System.err.println("Error al leer provincia: " + e.getMessage());
        }
        return null;
    }
    
    // READ - Buscar provincia por nombre
    public Provincia leerPorNombre(String nombre) {
        String sql = "SELECT * FROM provincias WHERE LOWER(nombre) = LOWER(?)";
        
        try (Connection conn = ConexionHuellasCuencanas.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, nombre);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return new Provincia(
                    rs.getInt("id"),
                    rs.getString("nombre")
                );
            }
            
        } catch (SQLException e) {
            System.err.println("Error al buscar provincia por nombre: " + e.getMessage());
        }
        return null;
    }
    
    // UPDATE - Actualizar provincia
    public boolean actualizar(Provincia provincia) {
        String sql = "UPDATE provincias SET nombre = ? WHERE id = ?";
        
        try (Connection conn = ConexionHuellasCuencanas.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, provincia.getNombre());
            stmt.setInt(2, provincia.getId());
            
            int filasAfectadas = stmt.executeUpdate();
            return filasAfectadas > 0;
            
        } catch (SQLException e) {
            System.err.println("Error al actualizar provincia: " + e.getMessage());
            return false;
        }
    }
    
    // DELETE - Eliminar provincia
    public boolean eliminar(int id) {
        String sql = "DELETE FROM provincias WHERE id = ?";
        
        try (Connection conn = ConexionHuellasCuencanas.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            
            int filasAfectadas = stmt.executeUpdate();
            return filasAfectadas > 0;
            
        } catch (SQLException e) {
            System.err.println("Error al eliminar provincia: " + e.getMessage());
            return false;
        }
    }
    
    // READ ALL - Listar todas las provincias
    public List<Provincia> listarTodas() {
        List<Provincia> provincias = new ArrayList<>();
        String sql = "SELECT * FROM provincias ORDER BY nombre";
        
        try (Connection conn = ConexionHuellasCuencanas.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Provincia provincia = new Provincia(
                    rs.getInt("id"),
                    rs.getString("nombre")
                );
                provincias.add(provincia);
            }
            
        } catch (SQLException e) {
            System.err.println("Error al listar provincias: " + e.getMessage());
        }
        return provincias;
    }
    
    // Método auxiliar para verificar si existe una provincia por ID
    public boolean existe(int id) {
        return leer(id) != null;
    }
    
    // Método auxiliar para verificar si existe una provincia por nombre
    public boolean existePorNombre(String nombre) {
        return leerPorNombre(nombre) != null;
    }
    
    // Método para obtener el total de provincias
    public int contarProvincias() {
        String sql = "SELECT COUNT(*) FROM provincias";
        
        try (Connection conn = ConexionHuellasCuencanas.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            if (rs.next()) {
                return rs.getInt(1);
            }
            
        } catch (SQLException e) {
            System.err.println("Error al contar provincias: " + e.getMessage());
        }
        return 0;
    }
}
