/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.sql.*;
import ConexionHuellasCuencanas.ConexionHuellasCuencanas;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author USER
 */
public class RolDAO {

    public int obtenerIdRolPorNombre(String nombreRol) {
        int idRol = -1;
        String sql = "SELECT id FROM rol WHERE nombre = ?";

        try ( Connection con = ConexionHuellasCuencanas.conectar();  PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, nombreRol);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                idRol = rs.getInt("id");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return idRol;
    }
    public List<Rol> listarRoles() {
        List<Rol> lista = new ArrayList<>();
        String sql = "SELECT * FROM rol ORDER BY nombre";
        try (Connection con = ConexionHuellasCuencanas.conectar();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                lista.add(new Rol(rs.getInt("id"), rs.getString("nombre")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public boolean insertarRol(String nombre) {
        String sql = "INSERT INTO rol(nombre) VALUES(?)";
        try (Connection con = ConexionHuellasCuencanas.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, nombre);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean actualizarRol(int id, String nombre) {
        String sql = "UPDATE rol SET nombre=? WHERE id=?";
        try (Connection con = ConexionHuellasCuencanas.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, nombre);
            ps.setInt(2, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean eliminarRol(int id) {
        String sql = "DELETE FROM rol WHERE id=?";
        try (Connection con = ConexionHuellasCuencanas.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}


