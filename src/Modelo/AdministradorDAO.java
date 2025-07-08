package Modelo;

import ConexionHuellasCuencanas.ConexionHuellasCuencanas;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.mindrot.jbcrypt.BCrypt;

public class AdministradorDAO {

    public Administrador leer(int idPersona) {
        String sql = "SELECT a.codigo_admin, a.id_persona, "
                + "p.usuario, p.correo, p.nombres, p.apellidos "
                + "FROM administrador a "
                + "JOIN persona p ON a.id_persona = p.id_persona "
                + "WHERE a.id_persona = ?";

        try ( Connection con = ConexionHuellasCuencanas.conectar();  PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idPersona);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Administrador admin = new Administrador();
                admin.setCodigoAdmin(rs.getString("codigo_admin"));
                admin.setIdPersona(rs.getInt("id_persona"));
                admin.setUsuario(rs.getString("usuario"));
                admin.setCorreo(rs.getString("correo"));
                admin.setNombres(rs.getString("nombres"));
                admin.setApellidos(rs.getString("apellidos"));
                return admin;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean insertar(Administrador admin) {
        String sql = "INSERT INTO administrador (id_persona, codigo_admin) VALUES (?, ?)";
        try ( Connection con = ConexionHuellasCuencanas.conectar();  PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, admin.getIdPersona());
            ps.setString(2, admin.getCodigoAdmin());

            int affectedRows = ps.executeUpdate();
            return affectedRows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean actualizar(Administrador admin) {
        String sqlPersona = "UPDATE persona SET usuario = ?, correo = ?, nombres = ?, apellidos = ?, "
                + "nacionalidad = ?, genero = ?, fecha_nacimiento = ?, cedula = ?, sobre_mi = ? "
                + (admin.getContrasena() != null ? ", contrasena = ?" : "")
                + (admin.getIcono() != null ? ", icono = ?" : "")
                + " WHERE id_persona = ?";

        try ( Connection con = ConexionHuellasCuencanas.conectar()) {
            con.setAutoCommit(false);

            try ( PreparedStatement ps = con.prepareStatement(sqlPersona)) {
                int index = 1;
                ps.setString(index++, admin.getUsuario());
                ps.setString(index++, admin.getCorreo());
                ps.setString(index++, admin.getNombres());
                ps.setString(index++, admin.getApellidos());
                ps.setString(index++, admin.getNacionalidad());
                ps.setString(index++, admin.getGenero());
                if (admin.getFechaNacimiento() != null) {
                    ps.setDate(index++, Date.valueOf(admin.getFechaNacimiento()));
                } else {
                    ps.setNull(index++, java.sql.Types.DATE);
                }
                if (admin.getCedula() != null) {
                    ps.setString(index++, admin.getCedula());
                } else {
                    ps.setNull(index++, java.sql.Types.VARCHAR);
                }
                ps.setString(index++, admin.getSobreMi());

                if (admin.getContrasena() != null) {
                    ps.setString(index++, admin.getContrasena());
                }
                if (admin.getIcono() != null) {
                    ps.setBytes(index++, admin.getIcono());
                }

                ps.setInt(index, admin.getIdPersona());
                ps.executeUpdate();
            }

            con.commit();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean eliminarCompleto(int idPersona) {
        String sqlAdministrador = "DELETE FROM administrador WHERE id_persona = ?";
        String sqlPersona = "DELETE FROM persona WHERE id_persona = ?";

        try ( Connection con = ConexionHuellasCuencanas.conectar()) {
            con.setAutoCommit(false);

            try (
                     PreparedStatement ps1 = con.prepareStatement(sqlAdministrador);  PreparedStatement ps2 = con.prepareStatement(sqlPersona)) {
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

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Administrador> listarTodos() {
        List<Administrador> lista = new ArrayList<>();
        String sql = "SELECT a.codigo_admin, p.id_persona, p.usuario, p.correo, p.nombres, p.apellidos, "
                + "p.nacionalidad, p.genero, p.fecha_nacimiento, p.rol_id, p.cedula "
                + "FROM administrador a "
                + "JOIN persona p ON a.id_persona = p.id_persona "
                + "WHERE p.rol_id = 1";

        try ( Connection con = ConexionHuellasCuencanas.conectar();  PreparedStatement ps = con.prepareStatement(sql);  ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Administrador admin = new Administrador();

                admin.setIdPersona(rs.getInt("id_persona"));
                admin.setUsuario(rs.getString("usuario"));
                admin.setCorreo(rs.getString("correo"));
                admin.setNombres(rs.getString("nombres"));
                admin.setApellidos(rs.getString("apellidos"));
                admin.setNacionalidad(rs.getString("nacionalidad"));
                admin.setGenero(rs.getString("genero"));
                admin.setFechaNacimiento(rs.getDate("fecha_nacimiento") != null
                        ? rs.getDate("fecha_nacimiento").toLocalDate()
                        : null);
                admin.setRolId(rs.getInt("rol_id"));
                admin.setCedula(rs.getString("cedula"));

                admin.setCodigoAdmin(rs.getString("codigo_admin"));

                lista.add(admin);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    public boolean validarCredenciales(String usuario, String clave) {
        String sql = "SELECT * FROM persona p INNER JOIN administrador a ON p.id_persona = a.id_persona WHERE p.usuario = ?";
        try ( Connection con = ConexionHuellasCuencanas.conectar();  PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, usuario);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String hash = rs.getString("contrasena");
                return BCrypt.checkpw(clave, hash);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public String obtenerUsuarioAdmin() {
        String sql = "SELECT usuario FROM persona WHERE rol_id = 1 LIMIT 1";
        try ( Connection con = ConexionHuellasCuencanas.conectar();  PreparedStatement pst = con.prepareStatement(sql);  ResultSet rs = pst.executeQuery()) {

            if (rs.next()) {
                return rs.getString("usuario");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "";
    }

    public List<Administrador> buscarUsuarios(String texto) {
        List<Administrador> lista = new ArrayList<>();
        String sql = "SELECT a.codigo_admin, p.id_persona, p.usuario, p.correo, p.nombres, p.apellidos, "
                + "p.nacionalidad, p.genero, p.fecha_nacimiento, p.rol_id, p.cedula "
                + "FROM administrador a "
                + "JOIN persona p ON a.id_persona = p.id_persona "
                + "WHERE p.rol_id = 1 AND ("
                + "LOWER(p.usuario) LIKE ? OR LOWER(p.nombres) LIKE ? OR LOWER(p.apellidos) LIKE ?)";

        try ( Connection con = ConexionHuellasCuencanas.conectar();  PreparedStatement ps = con.prepareStatement(sql)) {

            String filtro = "%" + texto.toLowerCase() + "%";
            ps.setString(1, filtro);
            ps.setString(2, filtro);
            ps.setString(3, filtro);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Administrador admin = new Administrador();
                admin.setCodigoAdmin(rs.getString("codigo_admin"));
                admin.setIdPersona(rs.getInt("id_persona"));
                admin.setUsuario(rs.getString("usuario"));
                admin.setCorreo(rs.getString("correo"));
                admin.setNombres(rs.getString("nombres"));
                admin.setApellidos(rs.getString("apellidos"));
                admin.setNacionalidad(rs.getString("nacionalidad"));
                admin.setGenero(rs.getString("genero"));

                java.sql.Date fecha = rs.getDate("fecha_nacimiento");
                if (fecha != null) {
                    admin.setFechaNacimiento(fecha.toLocalDate());
                }

                admin.setRolId(rs.getInt("rol_id"));
                admin.setCedula(rs.getString("cedula"));

                lista.add(admin);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    public Administrador obtenerPorId(int idPersona) {
        String sql = "SELECT * FROM persona JOIN administrador USING (id_persona) WHERE id_persona = ?";
        try ( Connection con = ConexionHuellasCuencanas.conectar();  PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idPersona);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Administrador admin = new Administrador();
                admin.setIdPersona(rs.getInt("id_persona"));
                admin.setUsuario(rs.getString("usuario"));
                admin.setCorreo(rs.getString("correo"));
                admin.setContrasena(rs.getString("contrasena"));
                admin.setNombres(rs.getString("nombres"));
                admin.setApellidos(rs.getString("apellidos"));
                admin.setNacionalidad(rs.getString("nacionalidad"));
                admin.setGenero(rs.getString("genero"));

                java.sql.Date fechaSql = rs.getDate("fecha_nacimiento");
                if (fechaSql != null) {
                    admin.setFechaNacimiento(fechaSql.toLocalDate());
                }

                admin.setIcono(rs.getBytes("icono"));
                admin.setRolId(rs.getInt("rol_id"));
                admin.setCedula(rs.getString("cedula"));
                admin.setSobreMi(rs.getString("sobre_mi"));

                return admin;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
