/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import ConexionHuellasCuencanas.Seguridad;
import java.sql.*;
import java.time.LocalDate;
import ConexionHuellasCuencanas.ConexionHuellasCuencanas;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author USER
 */
public class PersonaDAO {

    public boolean insertar(Persona p) {
        String sql = "INSERT INTO persona (cedula, usuario, correo, contrasena, nacionalidad, genero, nombres, apellidos, fecha_nacimiento, icono, sobre_mi, rol_id) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) RETURNING id_persona";

        try ( Connection conn = ConexionHuellasCuencanas.conectar();  PreparedStatement ps = conn.prepareStatement(sql)) {
            System.out.println("Usuario seteado: " + p.getUsuario());

            ps.setString(1, p.getCedula());
            ps.setString(2, p.getUsuario());
            ps.setString(3, p.getCorreo());
            ps.setString(4, p.getContrasena());
            ps.setString(5, p.getNacionalidad());
            ps.setString(6, p.getGenero());
            ps.setString(7, p.getNombres());
            ps.setString(8, p.getApellidos());
            if (p.getFechaNacimiento() != null) {
                ps.setDate(9, java.sql.Date.valueOf(p.getFechaNacimiento()));
            } else {
                ps.setDate(9, null);
            }
            ps.setBytes(10, p.getIcono());
            ps.setString(11, p.getSobreMi());
            ps.setInt(12, p.getRolId());

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int idGenerado = rs.getInt(1);
                p.setIdPersona(idGenerado);
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    //Validar si existe la cédula:
    public boolean existeCedula(String cedula) {
        if (cedula == null || cedula.isBlank()) {
            return false;
        }
        String sql = "SELECT 1 FROM persona WHERE cedula = ?";
        try ( Connection con = ConexionHuellasCuencanas.conectar();  PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, cedula);
            try ( ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    //Validar si existe un usuario:
    public boolean existeUsuario(String usuario) {
        String sql = "SELECT 1 FROM persona WHERE usuario = ?";
        try ( Connection con = ConexionHuellasCuencanas.conectar();  PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, usuario);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    //Validar si existe un correo.
    public boolean existeCorreo(String correo) {
        String sql = "SELECT 1 FROM persona WHERE correo = ?";
        try ( Connection con = ConexionHuellasCuencanas.conectar();  PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, correo);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Persona obtenerPersonaPorCredenciales(String usuario, String contraseña) {
        String sql
                = "SELECT p.id_persona, p.cedula, p.usuario, p.correo, "
                + "p.contrasena, p.nacionalidad, p.genero, p.nombres, "
                + "p.apellidos, p.fecha_nacimiento, p.sobre_mi, p.icono, p.rol_id "
                + "FROM persona p "
                + "WHERE p.usuario = ? "
                + "ORDER BY p.id_persona";

        try ( Connection conn = ConexionHuellasCuencanas.conectar();  PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, usuario);
            try ( ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String hash = rs.getString("contrasena");
                    if (!BCrypt.checkpw(contraseña, hash)) {
                        return null;
                    }

                    Persona p = new Persona();
                    p.setIdPersona(rs.getInt("id_persona"));
                    p.setCedula(rs.getString("cedula"));
                    p.setUsuario(rs.getString("usuario"));
                    p.setCorreo(rs.getString("correo"));
                    p.setContrasena(hash);
                    p.setNacionalidad(rs.getString("nacionalidad"));
                    p.setGenero(rs.getString("genero"));
                    p.setNombres(rs.getString("nombres"));
                    p.setApellidos(rs.getString("apellidos"));

                    Date fecha = rs.getDate("fecha_nacimiento");
                    p.setFechaNacimiento(fecha != null ? fecha.toLocalDate() : null);

                    p.setSobreMi(rs.getString("sobre_mi"));
                    p.setIcono(rs.getBytes("icono"));
                    p.setRolId(rs.getInt("rol_id"));

                    return p;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    //Para actualizar contraseña:
    public boolean actualizarContrasenia(String email, String nuevaContraseniaHash) {
        String sql = "UPDATE persona SET contraseña = ? WHERE correo = ?";
        try ( Connection con = ConexionHuellasCuencanas.conectar();  PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, nuevaContraseniaHash);
            ps.setString(2, email);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    //CRUD:
    public List<Persona> listarTodas() {
        List<Persona> lista = new ArrayList<>();

        String sql
                = "SELECT p.id_persona, p.cedula, p.usuario, p.correo, "
                + "p.contrasena, p.nacionalidad, p.genero, p.nombres, "
                + "p.apellidos, p.fecha_nacimiento, p.sobre_mi, p.icono, p.rol_id "
                + "FROM persona p "
                + "ORDER BY p.id_persona";

        try ( Connection con = ConexionHuellasCuencanas.conectar();  PreparedStatement ps = con.prepareStatement(sql);  ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Persona p = new Persona();
                p.setIdPersona(rs.getInt("id_persona"));
                p.setCedula(rs.getString("cedula"));
                p.setUsuario(rs.getString("usuario"));
                p.setCorreo(rs.getString("correo"));
                p.setContrasena(rs.getString("contrasena"));
                p.setNacionalidad(rs.getString("nacionalidad"));
                p.setGenero(rs.getString("genero"));
                p.setNombres(rs.getString("nombres"));
                p.setApellidos(rs.getString("apellidos"));
                Date fecha = rs.getDate("fecha_nacimiento");
                p.setFechaNacimiento(fecha != null
                        ? fecha.toLocalDate()
                        : null);
                p.setSobreMi(rs.getString("sobre_mi"));
                p.setIcono(rs.getBytes("icono"));
                p.setRolId(rs.getInt("rol_id"));

                lista.add(p);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    //Eliminar Persona:
    public boolean eliminar(int idPersona) {
        String sql = "DELETE FROM persona WHERE id_persona = ?";
        try ( Connection con = ConexionHuellasCuencanas.conectar();  PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idPersona);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    //Actualizar Persona:
    public boolean actualizar(Persona p) {
        String sql = "UPDATE persona SET "
                + "cedula = ?, usuario = ?, correo = ?, contrasena = ?, "
                + "nacionalidad = ?, genero = ?, nombres = ?, apellidos = ?, "
                + "fecha_nacimiento = ?, icono = ?, sobre_mi = ?, rol_id = ? "
                + "WHERE id_persona = ?";

        try ( Connection con = ConexionHuellasCuencanas.conectar();  PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, p.getCedula());
            ps.setString(2, p.getUsuario());
            ps.setString(3, p.getCorreo());
            ps.setString(4, p.getContrasena());
            ps.setString(5, p.getNacionalidad());
            ps.setString(6, p.getGenero());
            ps.setString(7, p.getNombres());
            ps.setString(8, p.getApellidos());

            if (p.getFechaNacimiento() != null) {
                ps.setDate(9, Date.valueOf(p.getFechaNacimiento()));
            } else {
                ps.setNull(9, Types.DATE);
            }

            if (p.getIcono() != null) {
                ps.setBytes(10, p.getIcono());
            } else {
                ps.setNull(10, Types.BINARY);
            }

            ps.setString(11, p.getSobreMi());
            ps.setInt(12, p.getRolId());
            ps.setInt(13, p.getIdPersona());

            int rows = ps.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    //Validar el Login:
    public boolean validarLogin(String usuario, String contraseña) {
        Persona p = obtenerPersonaPorCredenciales(usuario, contraseña);
        return p != null;
    }

    //Contar los usuarios:
    public int contarUsuarios(String condicionRol) {
        int total = 0;
        String sql = "SELECT COUNT(*) FROM persona";

        if (condicionRol != null && !condicionRol.isEmpty()) {
            sql += " WHERE " + condicionRol;
        }

        try ( Connection conn = ConexionHuellasCuencanas.conectar();  PreparedStatement ps = conn.prepareStatement(sql);  ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                total = rs.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return total;
    }

    //Buscar los usuarios:
    public List<Persona> buscarPorUsuarioONombre(String texto) {
        List<Persona> lista = new ArrayList<>();
        String sql = "SELECT p.id_persona, p.cedula, p.usuario, p.correo, "
                + "p.contrasena, p.nacionalidad, p.genero, p.nombres, "
                + "p.apellidos, p.fecha_nacimiento, p.sobre_mi, p.icono, p.rol_id "
                + "FROM persona p "
                + "WHERE p.usuario ILIKE ? "
                + "   OR p.nombres ILIKE ? "
                + "   OR p.apellidos ILIKE ? "
                + "ORDER BY p.id_persona";

        try ( Connection con = ConexionHuellasCuencanas.conectar();  PreparedStatement ps = con.prepareStatement(sql)) {

            String patron = "%" + texto + "%";
            ps.setString(1, patron);
            ps.setString(2, patron);
            ps.setString(3, patron);

            try ( ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Persona p = new Persona();
                    p.setIdPersona(rs.getInt("id_persona"));
                    p.setCedula(rs.getString("cedula"));
                    p.setUsuario(rs.getString("usuario"));
                    p.setCorreo(rs.getString("correo"));
                    p.setContrasena(rs.getString("contrasena"));
                    p.setNacionalidad(rs.getString("nacionalidad"));
                    p.setGenero(rs.getString("genero"));
                    p.setNombres(rs.getString("nombres"));
                    p.setApellidos(rs.getString("apellidos"));

                    Date fecha = rs.getDate("fecha_nacimiento");
                    p.setFechaNacimiento(fecha != null
                            ? fecha.toLocalDate()
                            : null);

                    p.setSobreMi(rs.getString("sobre_mi"));
                    p.setIcono(rs.getBytes("icono"));
                    p.setRolId(rs.getInt("rol_id"));

                    lista.add(p);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    //Actualizar el Rol:
    public boolean actualizarRol(int idPersona, int nuevoRolId) {
        String sql = "UPDATE persona SET rol_id = ? WHERE id_persona = ?";
        try ( Connection con = ConexionHuellasCuencanas.conectar();  PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, nuevoRolId);
            ps.setInt(2, idPersona);

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    //Leer a la persona por id:
    public Persona leerPorId(int idPersona) {
        String sql = "SELECT p.id_persona, p.cedula, p.usuario, p.correo, "
                + "p.contrasena, p.nacionalidad, p.genero, p.nombres, "
                + "p.apellidos, p.fecha_nacimiento, p.sobre_mi, p.icono, p.rol_id "
                + "FROM persona p WHERE p.id_persona = ?";

        try ( Connection con = ConexionHuellasCuencanas.conectar();  PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idPersona);
            try ( ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Persona p = new Persona();
                    p.setIdPersona(rs.getInt("id_persona"));
                    p.setCedula(rs.getString("cedula"));
                    p.setUsuario(rs.getString("usuario"));
                    p.setCorreo(rs.getString("correo"));
                    p.setContrasena(rs.getString("contrasena"));
                    p.setNacionalidad(rs.getString("nacionalidad"));
                    p.setGenero(rs.getString("genero"));
                    p.setNombres(rs.getString("nombres"));
                    p.setApellidos(rs.getString("apellidos"));
                    Date fecha = rs.getDate("fecha_nacimiento");
                    p.setFechaNacimiento(fecha != null ? fecha.toLocalDate() : null);
                    p.setSobreMi(rs.getString("sobre_mi"));
                    p.setIcono(rs.getBytes("icono"));
                    p.setRolId(rs.getInt("rol_id"));
                    return p;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
