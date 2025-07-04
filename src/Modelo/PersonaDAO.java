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
        RolDAO rolDAO = new RolDAO();
        int rolId = rolDAO.obtenerIdRolPorNombre("USUARIO_PRINCIPAL");
        if (rolId == -1) {
            JOptionPane.showMessageDialog(null, "No existe el rol USUARIO_PRINCIPAL.");
            return false;
        }

        String sql = "INSERT INTO persona "
                + "(cedula, usuario, correo, contraseña, "
                + "provincia, canton, genero, nombres, "
                + "apellidos, fecha_nacimiento, sobre_mi, rol_id) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try ( Connection con = ConexionHuellasCuencanas.conectar();  PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, p.getCedula());
            ps.setString(2, p.getUsuario());
            ps.setString(3, p.getCorreo());
            ps.setString(4, p.getContraseña());
            ps.setString(5, p.getProvincia());
            ps.setString(6, p.getCanton());
            ps.setString(7, p.getGenero());
            ps.setString(8, p.getNombres());
            ps.setString(9, p.getApellidos());
            if (p.getFechaNacimiento() != null) {
                ps.setDate(10, Date.valueOf(p.getFechaNacimiento()));
            } else {
                ps.setNull(10, Types.DATE);
            }
            ps.setString(11, p.getSobreMi());
            ps.setInt(12, rolId);

            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Error al insertar Persona:\n" + e.getMessage(),
                    "Error SQL",
                    JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return false;
        }
    }

    public boolean existeCedula(String cedula) {
        String sql = "SELECT 1 FROM persona WHERE cedula = ?";
        try ( Connection con = ConexionHuellasCuencanas.conectar();  PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, cedula);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

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

    public boolean validarLogin(String usuario, String contraseña) {
        String sql = "SELECT contraseña FROM persona WHERE usuario = ?";
        try ( Connection con = ConexionHuellasCuencanas.conectar();  PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, usuario);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String hashGuardado = rs.getString("contraseña");
                return BCrypt.checkpw(contraseña, hashGuardado);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

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
        String sql = "SELECT * FROM persona";
        try ( Connection con = ConexionHuellasCuencanas.conectar();  PreparedStatement ps = con.prepareStatement(sql);  ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Persona p = new Persona();
                p.setCedula(rs.getString("cedula"));
                p.setUsuario(rs.getString("usuario"));
                p.setCorreo(rs.getString("correo"));
                p.setContraseña(rs.getString("contraseña"));
                p.setProvincia(rs.getString("provincia"));
                p.setCanton(rs.getString("canton"));
                p.setGenero(rs.getString("genero"));
                p.setNombres(rs.getString("nombres"));
                p.setApellidos(rs.getString("apellidos"));
                p.setFechaNacimiento(rs.getDate("fecha_nacimiento") != null
                        ? rs.getDate("fecha_nacimiento").toLocalDate() : null);
                p.setSobreMi(rs.getString("sobre_mi"));
                p.setRolId(rs.getInt("rol_id"));
                lista.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public Persona leer(String cedula) {
        String sql = "SELECT * FROM persona WHERE cedula = ?";
        try ( Connection con = ConexionHuellasCuencanas.conectar();  PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, cedula);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Persona p = new Persona();
                p.setCedula(rs.getString("cedula"));
                p.setUsuario(rs.getString("usuario"));
                p.setCorreo(rs.getString("correo"));
                p.setContraseña(rs.getString("contraseña"));
                p.setProvincia(rs.getString("provincia"));
                p.setCanton(rs.getString("canton"));
                p.setGenero(rs.getString("genero"));
                p.setNombres(rs.getString("nombres"));
                p.setApellidos(rs.getString("apellidos"));
                p.setFechaNacimiento(rs.getDate("fecha_nacimiento") != null
                        ? rs.getDate("fecha_nacimiento").toLocalDate() : null);
                p.setSobreMi(rs.getString("sobre_mi"));
                p.setRolId(rs.getInt("rol_id"));
                return p;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean actualizar(Persona p) {
        String sql = "UPDATE persona SET usuario = ?, correo = ?, provincia = ?, canton = ?, genero = ?, nombres = ?, apellidos = ?, fecha_nacimiento = ?, sobre_mi = ?, rol_id = ? WHERE cedula = ?";
        try ( Connection con = ConexionHuellasCuencanas.conectar();  PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, p.getUsuario());
            ps.setString(2, p.getCorreo());
            ps.setString(3, p.getProvincia());
            ps.setString(4, p.getCanton());
            ps.setString(5, p.getGenero());
            ps.setString(6, p.getNombres());
            ps.setString(7, p.getApellidos());
            if (p.getFechaNacimiento() != null) {
                ps.setDate(8, Date.valueOf(p.getFechaNacimiento()));
            } else {
                ps.setNull(8, Types.DATE);
            }
            ps.setString(9, p.getSobreMi());
            ps.setInt(10, p.getRolId());
            ps.setString(11, p.getCedula());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean eliminar(String cedula) {
        String sql = "DELETE FROM persona WHERE cedula = ?";
        try ( Connection con = ConexionHuellasCuencanas.conectar();  PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, cedula);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
