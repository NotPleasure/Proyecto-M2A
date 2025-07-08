/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Animations.Animator;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import Modelo.Persona;
import Modelo.PersonaDAO;
import Vista.Registro;
import ConexionHuellasCuencanas.Seguridad;
import Modelo.RolDAO;
import Modelo.Usuario;
import Modelo.UsuarioDAO;
import Vista.Login;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author USER
 */
public class ControladorRegistro implements ActionListener {

    private Registro vista;
    private PersonaDAO personaDAO;

    public ControladorRegistro(Registro vista) {
        this.vista = vista;
        this.personaDAO = new PersonaDAO();

        vista.getjComboBox2().addItemListener(e -> {
            if (e.getStateChange() == java.awt.event.ItemEvent.SELECTED) {
                String seleccion = e.getItem().toString();
                boolean esEcuador = seleccion.equalsIgnoreCase("Ecuatoriana");
                vista.getTxtCedula().setEnabled(esEcuador);
                if (!esEcuador) {
                    vista.getTxtCedula().setText("");
                }
            }
        });

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        registrarPersona(false);
    }

    public void registrarPersona(boolean redireccionar) {
        if (vista.getTxtNombredeusuario2().getText().trim().isEmpty()
                || vista.getTxtCorreo1().getText().trim().isEmpty()
                || vista.getTxtContraseña().getPassword().length == 0
                || vista.getTxtConfirmarContraseña().getPassword().length == 0
                || vista.getjComboBox2().getSelectedItem() == null
                || vista.getjComboBox3().getSelectedItem() == null) {
            JOptionPane.showMessageDialog(vista, "Por favor, rellena todos los campos.");
            return;
        }

        String cedula = vista.getTxtCedula().getText().trim();
        String usuario = vista.getTxtNombredeusuario2().getText().trim();
        String correo = vista.getTxtCorreo1().getText().trim();
        char[] passwordChars = vista.getTxtContraseña().getPassword();
        char[] confirmarChars = vista.getTxtConfirmarContraseña().getPassword();

        String password = new String(passwordChars).trim();
        String confirmar = new String(confirmarChars).trim();
        String genero = vista.getjComboBox3().getSelectedItem().toString().trim();

        if (usuario.isEmpty() || usuario.equals("Nombre de usuario:")) {
            JOptionPane.showMessageDialog(vista, "Por favor, rellena todos los campos.");
            return;
        }

        String nacionalidad = vista.getjComboBox2().getSelectedItem().toString().trim();
        if (nacionalidad.equalsIgnoreCase("Ecuatoriana") && vista.getTxtCedula().getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(vista, "Por favor, ingrese la cédula.");
            return;
        }
        if (nacionalidad.equalsIgnoreCase("Ecuatoriana")) {
            if (!validarCedulaEcuatoriana(cedula)) {
                JOptionPane.showMessageDialog(vista, "Cédula inválida. Ingrese una cédula ecuatoriana correcta.");
                return;
            }
            if (personaDAO.existeCedula(cedula)) {
                JOptionPane.showMessageDialog(vista, "Ya existe una persona con esa cédula.");
                return;
            }
        } else {
            cedula = null;
        }
        if (usuario.isEmpty() || usuario.length() < 3) {
            JOptionPane.showMessageDialog(vista, "El nombre de usuario debe tener al menos 3 caracteres.");
            return;
        }
        if (!usuario.matches("^[A-Za-z]+$")) {
            JOptionPane.showMessageDialog(vista, "El nombre de usuario solo puede contener letras sin espacios, números o tildes.");
            return;
        }
        if (personaDAO.existeUsuario(usuario)) {
            JOptionPane.showMessageDialog(vista, "Ese nombre de usuario ya está registrado.");
            return;
        }
        if (!password.equals(confirmar)) {
            JOptionPane.showMessageDialog(vista, "Las contraseñas no coinciden.");
            return;
        }
        if (!password.matches("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[#\\$&\\-.])[A-Za-z\\d#\\$&\\-.]{8,}$")) {
            JOptionPane.showMessageDialog(vista, "La contraseña debe tener al menos 8 caracteres, incluir letras, números y alguno de estos signos: # $ & - .");
            return;
        }
        if (!correo.matches("^[\\w._%+-]+@[\\w.-]+\\.[A-Za-z]{2,}$")) {
            JOptionPane.showMessageDialog(vista, "Correo electrónico inválido.");
            return;
        }
        if (personaDAO.existeCorreo(correo)) {
            JOptionPane.showMessageDialog(vista, "Ese correo ya está registrado.");
            return;
        }

        if (nacionalidad.equalsIgnoreCase("Seleccione nacionalidad")) {
            JOptionPane.showMessageDialog(vista, "Por favor, seleccione una nacionalidad válida.");
            return;
        }

        if (genero.equalsIgnoreCase("Seleccione género")) {
            JOptionPane.showMessageDialog(vista, "Por favor, seleccione un género válido.");
            return;
        }

        Persona p = new Persona();
        p.setCedula(cedula);
        p.setUsuario(usuario);
        p.setCorreo(correo);
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        p.setContrasena(hashedPassword);
        p.setNacionalidad(nacionalidad);
        p.setGenero(genero);
        p.setNombres(null);
        p.setApellidos(null);
        p.setFechaNacimiento(null);
        p.setSobreMi(null);
        p.setIcono(null);

        RolDAO rolDAO = new RolDAO();
        int rolId = rolDAO.obtenerIdRolPorNombre("USUARIO_PRINCIPAL");
        p.setRolId(rolId);

        if (personaDAO.insertar(p)) {
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            Usuario u = new Usuario();
            u.setIdPersona(p.getIdPersona());
            if (usuarioDAO.insertar(u)) {
                JOptionPane.showMessageDialog(vista, "Registro exitoso.");

                Animator.fadeOut(vista, () -> {
                    Login login = new Login();
                    Animator.fadeIn(login);
                    vista.dispose();
                });
            } else {
                JOptionPane.showMessageDialog(vista, "Error al registrar usuario.");
            }
        } else {
            JOptionPane.showMessageDialog(vista, "Error al registrar persona.");
        }
    }

    public boolean validarCedulaEcuatoriana(String cedula) {
        if (cedula == null || !cedula.matches("\\d{10}")) {
            return false;
        }

        int provincia = Integer.parseInt(cedula.substring(0, 2));
        if (provincia < 1 || provincia > 24) {
            return false;
        }

        int tercerDigito = Character.getNumericValue(cedula.charAt(2));
        if (tercerDigito >= 6) {
            return false;
        }

        int suma = 0;
        for (int i = 0; i < 9; i++) {
            int digito = Character.getNumericValue(cedula.charAt(i));
            if (i % 2 == 0) {
                digito *= 2;
                if (digito > 9) {
                    digito -= 9;
                }
            }
            suma += digito;
        }

        int ultimoDigito = Character.getNumericValue(cedula.charAt(9));
        int verificador = (10 - (suma % 10)) % 10;

        return verificador == ultimoDigito;
    }
}
