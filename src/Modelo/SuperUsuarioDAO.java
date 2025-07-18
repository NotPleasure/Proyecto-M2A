package Modelo;

import ConexionHuellasCuencanas.ConexionHuellasCuencanas;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SuperUsuarioDAO {

    // Insertar un superusuario:
    public boolean insertar(SuperUsuario su) {
        String sql = "INSERT INTO superusuario (id_persona) VALUES (?)";
        try ( Connection con = ConexionHuellasCuencanas.conectar();  PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, su.getIdPersona());
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Buscar superusuarios por texto:
    public List<SuperUsuario> buscarSuperusuarios(String texto) {
        List<SuperUsuario> lista = new ArrayList<>();
        String sql
                = "SELECT p.* "
                + "FROM persona p "
                + "JOIN superusuario su ON p.id_persona = su.id_persona "
                + "WHERE p.rol_id = 3 AND ("
                + "CAST(p.id_persona AS TEXT) LIKE ? OR "
                + "LOWER(p.usuario) LIKE ? OR "
                + "LOWER(p.correo) LIKE ? OR "
                + "LOWER(p.nombres) LIKE ? OR "
                + "LOWER(p.apellidos) LIKE ? OR "
                + "p.cedula LIKE ?)";
        try ( Connection con = ConexionHuellasCuencanas.conectar();  PreparedStatement ps = con.prepareStatement(sql)) {

            String filtro = "%" + texto.toLowerCase() + "%";
            for (int i = 1; i <= 6; i++) {
                ps.setString(i, filtro);
            }

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                SuperUsuario su = new SuperUsuario();
                su.setIdPersona(rs.getInt("id_persona"));
                su.setUsuario(rs.getString("usuario"));
                su.setCorreo(rs.getString("correo"));
                su.setNombres(rs.getString("nombres"));
                su.setApellidos(rs.getString("apellidos"));
                su.setNacionalidad(rs.getString("nacionalidad"));
                su.setGenero(rs.getString("genero"));
                Date sqlDate = rs.getDate("fecha_nacimiento");
                if (sqlDate != null) {
                    su.setFechaNacimiento(sqlDate.toLocalDate());
                }
                su.setRolId(rs.getInt("rol_id"));
                su.setCedula(rs.getString("cedula"));
                su.setIcono(rs.getBytes("icono"));
                su.setSobreMi(rs.getString("sobre_mi"));
                lista.add(su);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    // Listar todos los superusuarios:
    public List<SuperUsuario> listarTodos() {
        List<SuperUsuario> lista = new ArrayList<>();
        String sql
                = "SELECT p.* "
                + "FROM superusuario su "
                + " JOIN persona p ON su.id_persona = p.id_persona";
        try ( Connection con = ConexionHuellasCuencanas.conectar();  PreparedStatement ps = con.prepareStatement(sql);  ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                SuperUsuario su = new SuperUsuario();
                su.setIdPersona(rs.getInt("id_persona"));
                su.setUsuario(rs.getString("usuario"));
                su.setCorreo(rs.getString("correo"));
                su.setNombres(rs.getString("nombres"));
                su.setApellidos(rs.getString("apellidos"));
                su.setNacionalidad(rs.getString("nacionalidad"));
                su.setGenero(rs.getString("genero"));
                Date sqlDate = rs.getDate("fecha_nacimiento");
                if (sqlDate != null) {
                    su.setFechaNacimiento(sqlDate.toLocalDate());
                }
                su.setRolId(rs.getInt("rol_id"));
                su.setCedula(rs.getString("cedula"));
                su.setIcono(rs.getBytes("icono"));
                su.setSobreMi(rs.getString("sobre_mi"));
                lista.add(su);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    // Obtener un superusuario por id:
    public SuperUsuario obtenerPorId(int idPersona) {
        String sql
                = "SELECT p.* "
                + "FROM persona p "
                + "JOIN superusuario su ON p.id_persona = su.id_persona "
                + "WHERE p.id_persona = ?";
        try ( Connection con = ConexionHuellasCuencanas.conectar();  PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idPersona);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                SuperUsuario su = new SuperUsuario();
                su.setIdPersona(rs.getInt("id_persona"));
                su.setUsuario(rs.getString("usuario"));
                su.setCorreo(rs.getString("correo"));
                su.setContrasena(rs.getString("contrasena"));
                su.setNombres(rs.getString("nombres"));
                su.setApellidos(rs.getString("apellidos"));
                su.setNacionalidad(rs.getString("nacionalidad"));
                su.setGenero(rs.getString("genero"));
                Date sqlDate = rs.getDate("fecha_nacimiento");
                if (sqlDate != null) {
                    su.setFechaNacimiento(sqlDate.toLocalDate());
                }
                su.setIcono(rs.getBytes("icono"));
                su.setRolId(rs.getInt("rol_id"));
                su.setCedula(rs.getString("cedula"));
                su.setSobreMi(rs.getString("sobre_mi"));
                return su;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Eliminar superusuario:
    public boolean eliminarCompleto(int idPersona) {
        String sqlSuper = "DELETE FROM superusuario WHERE id_persona = ?";
        String sqlUsuario = "DELETE FROM usuario      WHERE id_persona = ?";
        String sqlAdministrador = "DELETE FROM administrador WHERE id_persona = ?";

        String sqlPersona = "DELETE FROM persona      WHERE id_persona = ?";

        try ( Connection con = ConexionHuellasCuencanas.conectar()) {
            con.setAutoCommit(false);

            try (
                     PreparedStatement ps1 = con.prepareStatement(sqlSuper);  PreparedStatement ps2 = con.prepareStatement(sqlUsuario);  PreparedStatement ps3 = con.prepareStatement(sqlAdministrador);  PreparedStatement ps4 = con.prepareStatement(sqlPersona)) {
                ps1.setInt(1, idPersona);
                ps1.executeUpdate();

                ps2.setInt(1, idPersona);
                ps2.executeUpdate();

                ps3.setInt(1, idPersona);
                ps3.executeUpdate();

                ps4.setInt(1, idPersona);
                ps4.executeUpdate();

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

    //Actualizar un Superusuario:
    public boolean actualizar(SuperUsuario su) {
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
            ps.setString(i++, su.getUsuario());
            ps.setString(i++, su.getCorreo());
            ps.setString(i++, su.getContrasena());
            ps.setString(i++, su.getNacionalidad());
            ps.setString(i++, su.getGenero());

            if (su.getNombres() != null && !su.getNombres().isEmpty()) {
                ps.setString(i++, su.getNombres());
            } else {
                ps.setNull(i++, Types.VARCHAR);
            }

            if (su.getApellidos() != null && !su.getApellidos().isEmpty()) {
                ps.setString(i++, su.getApellidos());
            } else {
                ps.setNull(i++, Types.VARCHAR);
            }

            if (su.getFechaNacimiento() != null) {
                ps.setDate(i++, Date.valueOf(su.getFechaNacimiento()));
            } else {
                ps.setNull(i++, Types.DATE);
            }

            if (su.getCedula() != null && !su.getCedula().isEmpty()) {
                ps.setString(i++, su.getCedula());
            } else {
                ps.setNull(i++, Types.VARCHAR);
            }

            if (su.getIcono() != null) {
                ps.setBytes(i++, su.getIcono());
            } else {
                ps.setNull(i++, Types.BINARY);
            }

            if (su.getSobreMi() != null && !su.getSobreMi().isEmpty()) {
                ps.setString(i++, su.getSobreMi());
            } else {
                ps.setNull(i++, Types.CLOB);
            }

            ps.setInt(i, su.getIdPersona());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    //Para que al momento de que se cambie el rol, se "actualice en la tabla":
    public boolean existePorId(int idPersona) {
        String sql = "SELECT 1 FROM superusuario WHERE id_persona = ?";
        try ( Connection con = ConexionHuellasCuencanas.conectar();  PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idPersona);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean eliminarPorId(int idPersona) {
        String sql = "DELETE FROM superusuario WHERE id_persona = ?";
        try ( Connection con = ConexionHuellasCuencanas.conectar();  PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idPersona);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
