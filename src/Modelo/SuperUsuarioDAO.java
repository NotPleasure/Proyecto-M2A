package Modelo;

import ConexionHuellasCuencanas.ConexionHuellasCuencanas;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SuperUsuarioDAO {

    public boolean insertar(SuperUsuario su) {
        String sql = "INSERT INTO superusuario (id_persona, direccion, nombre_negocio, descripcion, cargo) VALUES (?, ?, ?, ?, ?)";
        try ( Connection con = ConexionHuellasCuencanas.conectar();  PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, su.getIdPersona());
            ps.setString(2, su.getDireccion());
            ps.setString(3, su.getNombreNegocio());
            ps.setString(4, su.getDescripcion());
            ps.setString(5, su.getCargo());

            int affectedRows = ps.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Error al insertar superusuario, no se insertó fila.");
            }

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public SuperUsuario leer(int idPersona) {
        String sql = "SELECT su.direccion, su.nombre_negocio, su.descripcion, su.cargo, "
                + "p.* FROM superusuario su "
                + "JOIN persona p ON su.id_persona = p.id_persona "
                + "WHERE p.id_persona = ?";
        try ( Connection con = ConexionHuellasCuencanas.conectar();  PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idPersona);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                SuperUsuario su = new SuperUsuario();
                su.setIdPersona(rs.getInt("id_persona"));
                su.setCedula(rs.getString("cedula"));
                su.setUsuario(rs.getString("usuario"));
                su.setCorreo(rs.getString("correo"));
                su.setContrasena(rs.getString("contrasena"));
                su.setNacionalidad(rs.getString("nacionalidad"));
                su.setGenero(rs.getString("genero"));
                su.setNombres(rs.getString("nombres"));
                su.setApellidos(rs.getString("apellidos"));
                Date fecha = rs.getDate("fecha_nacimiento");
                su.setFechaNacimiento(fecha != null ? fecha.toLocalDate() : null);
                su.setIcono(rs.getBytes("icono"));
                su.setSobreMi(rs.getString("sobre_mi"));
                su.setRolId(rs.getInt("rol_id"));

                su.setDireccion(rs.getString("direccion"));
                su.setNombreNegocio(rs.getString("nombre_negocio"));
                su.setDescripcion(rs.getString("descripcion"));
                su.setCargo(rs.getString("cargo"));
                return su;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean actualizar(SuperUsuario su) {
        PersonaDAO personaDAO = new PersonaDAO();
        if (!personaDAO.actualizar(su)) {
            return false;
        }

        String sql = "UPDATE superusuario SET direccion = ?, nombre_negocio = ?, descripcion = ?, cargo = ? WHERE id_persona = ?";
        try ( Connection con = ConexionHuellasCuencanas.conectar();  PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, su.getDireccion());
            ps.setString(2, su.getNombreNegocio());
            ps.setString(3, su.getDescripcion());
            ps.setString(4, su.getCargo());
            ps.setInt(5, su.getIdPersona());

            int rows = ps.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean eliminar(int idPersona) {
        String sql = "DELETE FROM superusuario WHERE id_persona = ?";
        try ( Connection con = ConexionHuellasCuencanas.conectar();  PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idPersona);
            int rows = ps.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<SuperUsuario> listarTodos() {
        List<SuperUsuario> lista = new ArrayList<>();
        String sql = "SELECT su.direccion, su.nombre_negocio, su.descripcion, su.cargo, p.* FROM superusuario su "
                + "JOIN persona p ON su.id_persona = p.id_persona";

        try ( Connection con = ConexionHuellasCuencanas.conectar();  PreparedStatement ps = con.prepareStatement(sql);  ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                SuperUsuario su = new SuperUsuario();
                su.setIdPersona(rs.getInt("id_persona"));
                su.setCedula(rs.getString("cedula"));
                su.setUsuario(rs.getString("usuario"));
                su.setCorreo(rs.getString("correo"));
                su.setContrasena(rs.getString("contrasena"));
                su.setNacionalidad(rs.getString("nacionalidad"));
                su.setGenero(rs.getString("genero"));
                su.setNombres(rs.getString("nombres"));
                su.setApellidos(rs.getString("apellidos"));
                Date fecha = rs.getDate("fecha_nacimiento");
                su.setFechaNacimiento(fecha != null ? fecha.toLocalDate() : null);
                su.setIcono(rs.getBytes("icono"));
                su.setSobreMi(rs.getString("sobre_mi"));
                su.setRolId(rs.getInt("rol_id"));

                su.setDireccion(rs.getString("direccion"));
                su.setNombreNegocio(rs.getString("nombre_negocio"));
                su.setDescripcion(rs.getString("descripcion"));
                su.setCargo(rs.getString("cargo"));
                lista.add(su);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }
}
