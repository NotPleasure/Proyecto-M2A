package Controlador;

import Design.Imagen.ImagenUtils;
import Design.RoundedFormattedTextField;
import Modelo.SuperUsuario;
import Modelo.SuperUsuarioDAO;
import Modelo.Usuario;
import Vista.Ventana_Actualizar_SuperUsuario;
import Vista.Ventana_SuperUsuario;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Optional;
import javax.swing.JOptionPane;
import org.mindrot.jbcrypt.BCrypt;
import raven.glasspanepopup.GlassPanePopup;

public class ControladorActualizarSuperUsuario {

    private Ventana_Actualizar_SuperUsuario vista;
    private SuperUsuario usuario;
    private SuperUsuarioDAO usuarioDAO;
    private byte[] iconoBytes;
    private Ventana_SuperUsuario ventanaSuperUsuarios;

    public ControladorActualizarSuperUsuario(
            Ventana_Actualizar_SuperUsuario vista,
            SuperUsuario usuario,
            Ventana_SuperUsuario ventanaSuperUsuarios) {

        this.vista = vista;
        this.usuario = usuario;
        this.usuarioDAO = new SuperUsuarioDAO();
        this.ventanaSuperUsuarios = ventanaSuperUsuarios;

        vista.cargarDatosSuperUsuario(usuario);

        final boolean tieneCedulaOriginal = usuario.getCedula() != null && !usuario.getCedula().trim().isEmpty();

        if (tieneCedulaOriginal) {
            vista.getTxtCedula().setEnabled(false);
            vista.getTxtCedula().setText(usuario.getCedula());
        } else {
            String nacionalidadActual = vista.getNacionalidades().getSelectedItem().toString();
            vista.getTxtCedula().setEnabled(nacionalidadActual.equalsIgnoreCase("Ecuatoriana"));
        }

        vista.getNacionalidades().addItemListener(e -> {
            if (e.getStateChange() == java.awt.event.ItemEvent.SELECTED) {
                String nuevaNacionalidad = e.getItem().toString();
                boolean esEcuatoriana = nuevaNacionalidad.equalsIgnoreCase("Ecuatoriana");

                if (tieneCedulaOriginal) {
                    vista.getTxtCedula().setEnabled(false);

                    if (esEcuatoriana) {
                        vista.getTxtCedula().setText(usuario.getCedula());
                    } else {
                        vista.getTxtCedula().setText("");
                    }
                } else {
                    vista.getTxtCedula().setEnabled(esEcuatoriana);
                    if (!esEcuatoriana) {
                        vista.getTxtCedula().setText("");
                    }
                }
            }
        });

        vista.getAceptar().addActionListener(e -> actualizarUsuario());
        vista.getLimpiar().addActionListener(e -> cargarImagen());
        vista.getCancelar().addActionListener(e -> GlassPanePopup.closePopupLast());
    }

    private void cargarImagen() {
        iconoBytes = ImagenUtils.seleccionarYConvertirImagen(vista.getLblFotoPerfil());
    }

    private void actualizarUsuario() {
        try {
            String nombres = vista.getNombres().getText().trim();
            String apellidos = vista.getApellidos().getText().trim();
            String correo = vista.getCorreoElectronico().getText().trim();
            String usuarioNombre = vista.getTxtNombredeusuario().getText().trim();
            String nacionalidad = Optional.ofNullable(vista.getNacionalidades().getSelectedItem())
                    .map(Object::toString).orElse("");
            String genero = Optional.ofNullable(vista.getGeneros().getSelectedItem())
                    .map(Object::toString).orElse("");
            String sobreMi = vista.getTxtSobreMi().getText().trim();
            RoundedFormattedTextField fechaField = vista.getFecha();
            String fechaTextoCrudo = fechaField.getText().trim().replace("/", "").replace("_", "");
            String contrasena = new String(vista.getContrasenia().getPassword()).trim();
            String cedulaStr = vista.getTxtCedula().getText().trim();

            if (correo.isEmpty() || usuarioNombre.isEmpty()) {
                JOptionPane.showMessageDialog(vista, "Usuario y correo son obligatorios");
                return;
            }

            if (nacionalidad.equalsIgnoreCase("Ecuatoriana")) {
                if (cedulaStr.isEmpty()) {
                    JOptionPane.showMessageDialog(vista, "Por favor, ingrese la cédula.");
                    return;
                }
                if (!validarCedulaEcuatoriana(cedulaStr)) {
                    JOptionPane.showMessageDialog(vista, "Cédula inválida. Ingrese una cédula ecuatoriana correcta.");
                    return;
                }
            }

            LocalDate fechaNacimiento = null;

            if (!fechaTextoCrudo.isEmpty()) {
                if (!fechaTextoCrudo.matches("\\d{8}")) {
                    JOptionPane.showMessageDialog(vista, "Formato de fecha inválido. Use DDMMAAAA");
                    return;
                }
                try {
                    SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy");
                    java.util.Date parsed = sdf.parse(fechaTextoCrudo);
                    fechaNacimiento = parsed.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                } catch (ParseException ex) {
                    JOptionPane.showMessageDialog(vista, "Fecha inválida: " + ex.getMessage());
                    return;
                }
            }

            usuario.setNombres(nombres);
            usuario.setApellidos(apellidos);
            usuario.setCorreo(correo);
            usuario.setUsuario(usuarioNombre);
            usuario.setNacionalidad(nacionalidad);
            usuario.setGenero(genero);
            usuario.setFechaNacimiento(fechaNacimiento);
            usuario.setSobreMi(sobreMi);

            if (nacionalidad.equalsIgnoreCase("Ecuatoriana")) {
                usuario.setCedula(cedulaStr);
            } else {
                usuario.setCedula(null);
            }

            if (iconoBytes != null) {
                usuario.setIcono(iconoBytes);

                if (nacionalidad.equalsIgnoreCase("Ecuatoriana") && cedulaStr.isEmpty()) {
                    JOptionPane.showMessageDialog(vista, "Por favor, ingrese la cédula.");
                    return;
                }

                if (nacionalidad.equalsIgnoreCase("Ecuatoriana") && !validarCedulaEcuatoriana(cedulaStr)) {
                    JOptionPane.showMessageDialog(vista, "Cédula inválida. Ingrese una cédula ecuatoriana correcta.");
                    return;
                }
            }

            if (!contrasena.isBlank()) {
                if (!contrasena.matches("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[#\\$&\\-.])[A-Za-z\\d#\\$&\\-.]{8,}$")) {
                    JOptionPane.showMessageDialog(vista, "Contraseña insegura. Debe tener:\n- Letras y números\n- 1 símbolo\n- Mínimo 8 caracteres");
                    return;
                }
                usuario.setContrasena(BCrypt.hashpw(contrasena, BCrypt.gensalt()));
            }

            boolean actualizado = usuarioDAO.actualizar(usuario);
            if (actualizado) {
                JOptionPane.showMessageDialog(vista, "Usuario actualizado correctamente.");
                ventanaSuperUsuarios.recargarTabla();
                GlassPanePopup.closePopupLast();
            } else {
                JOptionPane.showMessageDialog(vista, "Error al actualizar usuario.");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(vista, "Error inesperado:\n" + ex.getMessage());
        }
    }

    private boolean validarCedulaEcuatoriana(String cedula) {
        if (cedula == null || !cedula.matches("\\d{10}")) {
            return false;
        }
        int provincia = Integer.parseInt(cedula.substring(0, 2));
        if (provincia < 1 || provincia > 24) {
            return false;
        }
        int tercer = Character.getNumericValue(cedula.charAt(2));
        if (tercer >= 6) {
            return false;
        }
        int suma = 0;
        for (int i = 0; i < 9; i++) {
            int d = Character.getNumericValue(cedula.charAt(i));
            if (i % 2 == 0) {
                d *= 2;
                if (d > 9) {
                    d -= 9;
                }
            }
            suma += d;
        }
        int verificador = (10 - (suma % 10)) % 10;
        return verificador == Character.getNumericValue(cedula.charAt(9));
    }
}
