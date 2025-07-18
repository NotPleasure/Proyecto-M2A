package Modelo;

import ConexionHuellasCuencanas.ConexionHuellasCuencanas;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    //Insertar un usuario:
    public boolean insertar(Usuario u) {
        String sql = "INSERT INTO usuario (id_persona) VALUES (?)";
        try ( Connection con = ConexionHuellasCuencanas.conectar();  PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, u.getIdPersona());

            int affectedRows = ps.executeUpdate();
            return affectedRows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    //Bucar usuarios principales:
    public List<Usuario> buscarUsuarios(String texto) {
        List<Usuario> lista = new ArrayList<>();

        String sql = "SELECT * FROM persona WHERE rol_id = 2 AND ("
                + "CAST(id_persona AS TEXT) LIKE ? OR "
                + "LOWER(usuario) LIKE ? OR "
                + "LOWER(correo) LIKE ? OR "
                + "LOWER(nombres) LIKE ? OR "
                + "LOWER(apellidos) LIKE ? OR "
                + "cedula LIKE ?)";

        try ( Connection con = ConexionHuellasCuencanas.conectar();  PreparedStatement ps = con.prepareStatement(sql)) {

            String filtro = "%" + texto.toLowerCase() + "%";

            ps.setString(1, filtro);
            ps.setString(2, filtro);
            ps.setString(3, filtro);
            ps.setString(4, filtro);
            ps.setString(5, filtro);
            ps.setString(6, filtro);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Usuario u = new Usuario();
                u.setIdPersona(rs.getInt("id_persona"));
                u.setUsuario(rs.getString("usuario"));
                u.setCorreo(rs.getString("correo"));
                u.setNombres(rs.getString("nombres"));
                u.setApellidos(rs.getString("apellidos"));
                u.setNacionalidad(rs.getString("nacionalidad"));
                u.setGenero(rs.getString("genero"));
                u.setFechaNacimiento(rs.getDate("fecha_nacimiento") != null
                        ? rs.getDate("fecha_nacimiento").toLocalDate() : null);
                u.setRolId(rs.getInt("rol_id"));
                u.setCedula(rs.getString("cedula"));
                lista.add(u);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    //Actualizar el usuario principal:
    public boolean actualizar(Usuario u) {
        String sql = "UPDATE persona SET "
                + "usuario          = ?, "
                + "correo           = ?, "
                + "contrasena       = ?, "
                + "nacionalidad     = ?, "
                + "genero           = ?, "
                + "nombres          = ?, "
                + "apellidos        = ?, "
                + "fecha_nacimiento = ?, "
                + "cedula           = ?, "
                + "icono            = ?, "
                + "sobre_mi         = ? "
                + "WHERE id_persona = ?";
        try ( Connection con = ConexionHuellasCuencanas.conectar();  PreparedStatement ps = con.prepareStatement(sql)) {

            int i = 1;
            ps.setString(i++, u.getUsuario());
            ps.setString(i++, u.getCorreo());
            ps.setString(i++, u.getContrasena());
            ps.setString(i++, u.getNacionalidad());
            ps.setString(i++, u.getGenero());

            if (u.getNombres() != null && !u.getNombres().isEmpty()) {
                ps.setString(i++, u.getNombres());
            } else {
                ps.setNull(i++, Types.VARCHAR);
            }

            if (u.getApellidos() != null && !u.getApellidos().isEmpty()) {
                ps.setString(i++, u.getApellidos());
            } else {
                ps.setNull(i++, Types.VARCHAR);
            }

            if (u.getFechaNacimiento() != null) {
                ps.setDate(i++, Date.valueOf(u.getFechaNacimiento()));
            } else {
                ps.setNull(i++, Types.DATE);
            }

            if (u.getCedula() != null && !u.getCedula().isEmpty()) {
                ps.setString(i++, u.getCedula());
            } else {
                ps.setNull(i++, Types.VARCHAR);
            }

            if (u.getIcono() != null) {
                ps.setBytes(i++, u.getIcono());
            } else {
                ps.setNull(i++, Types.BINARY);
            }

            if (u.getSobreMi() != null && !u.getSobreMi().isEmpty()) {
                ps.setString(i++, u.getSobreMi());
            } else {
                ps.setNull(i++, Types.CLOB);
            }

            ps.setInt(i, u.getIdPersona());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    //Eliminar por completo el usuario principal:
    public boolean eliminarCompleto(int idPersona) {
        String sqlUsuario = "DELETE FROM usuario WHERE id_persona = ?";
        String sqlPersona = "DELETE FROM persona WHERE id_persona = ?";
        try ( Connection con = ConexionHuellasCuencanas.conectar()) {
            con.setAutoCommit(false);

            try ( PreparedStatement ps1 = con.prepareStatement(sqlUsuario);  PreparedStatement ps2 = con.prepareStatement(sqlPersona)) {

                ps1.setInt(1, idPersona);
                ps2.setInt(1, idPersona);

                ps1.executeUpdate();
                ps2.executeUpdate();

                con.commit();
                return true;

            } catch (SQLException e) {
                con.rollback();
                e.printStackTrace();
                return false;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    //Filtrar todos los usuarios principales:
    public List<Usuario> listarTodos() {
        List<Usuario> lista = new ArrayList<>();
        String sql = "SELECT u.id_persona, "
                + "p.usuario, "
                + "p.correo, "
                + "p.nombres, "
                + "p.apellidos, "
                + "p.nacionalidad, "
                + "p.genero, "
                + "p.fecha_nacimiento, "
                + "p.rol_id, "
                + "p.cedula "
                + "FROM usuario u "
                + "JOIN persona p ON u.id_persona = p.id_persona";

        try ( Connection con = ConexionHuellasCuencanas.conectar();  PreparedStatement ps = con.prepareStatement(sql);  ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Usuario u = new Usuario();
                u.setIdPersona(rs.getInt("id_persona"));
                u.setUsuario(rs.getString("usuario"));
                u.setCorreo(rs.getString("correo"));
                u.setNombres(rs.getString("nombres"));
                u.setApellidos(rs.getString("apellidos"));
                u.setNacionalidad(rs.getString("nacionalidad"));
                u.setGenero(rs.getString("genero"));

                java.sql.Date sqlDate = rs.getDate("fecha_nacimiento");
                if (sqlDate != null) {
                    u.setFechaNacimiento(sqlDate.toLocalDate());
                } else {
                    u.setFechaNacimiento(null);
                }

                u.setRolId(rs.getInt("rol_id"));
                u.setCedula(rs.getString("cedula"));
                lista.add(u);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    //Obtener los usuarios por id:
    public Usuario obtenerPorId(int idPersona) {
        String sql = "SELECT * FROM persona WHERE id_persona = ?";
        try ( Connection con = ConexionHuellasCuencanas.conectar();  PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idPersona);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Usuario u = new Usuario();
                u.setIdPersona(rs.getInt("id_persona"));
                u.setUsuario(rs.getString("usuario"));
                u.setCorreo(rs.getString("correo"));
                u.setContrasena(rs.getString("contrasena"));
                u.setNombres(rs.getString("nombres"));
                u.setApellidos(rs.getString("apellidos"));
                u.setNacionalidad(rs.getString("nacionalidad"));
                u.setGenero(rs.getString("genero"));

                java.sql.Date fechaSql = rs.getDate("fecha_nacimiento");
                if (fechaSql != null) {
                    u.setFechaNacimiento(fechaSql.toLocalDate());
                }
                u.setIcono(rs.getBytes("icono"));

                u.setRolId(rs.getInt("rol_id"));
                u.setCedula(rs.getString("cedula"));
                u.setSobreMi(rs.getString("sobre_mi"));

                return u;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    //Obtener los usuarios por rol:
    public List<Usuario> listarPorRol(int rolId) {
        List<Usuario> lista = new ArrayList<>();
        String sql = "SELECT * FROM persona WHERE rol_id = ?";
        try ( Connection con = ConexionHuellasCuencanas.conectar();  PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, rolId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Usuario u = new Usuario();
                u.setIdPersona(rs.getInt("id_persona"));
                u.setUsuario(rs.getString("usuario"));
                u.setCorreo(rs.getString("correo"));
                u.setNombres(rs.getString("nombres"));
                u.setApellidos(rs.getString("apellidos"));
                u.setNacionalidad(rs.getString("nacionalidad"));
                u.setGenero(rs.getString("genero"));
                u.setFechaNacimiento(rs.getDate("fecha_nacimiento") != null
                        ? rs.getDate("fecha_nacimiento").toLocalDate() : null);
                u.setRolId(rs.getInt("rol_id"));
                u.setCedula(rs.getString("cedula"));
                lista.add(u);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public boolean eliminarPorId(int idPersona) {
        String sql = "DELETE FROM usuario WHERE id_persona = ?";
        try ( Connection con = ConexionHuellasCuencanas.conectar();  PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idPersona);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
          public boolean existePorId(int idPersona) {
        String sql = "SELECT 1 FROM usuario WHERE id_persona = ?";
        try ( Connection con = ConexionHuellasCuencanas.conectar();  PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idPersona);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
          }
}
