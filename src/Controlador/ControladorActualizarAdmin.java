package Controlador;

import Design.Imagen.ImagenUtils;
import Design.RoundedFormattedTextField;
import Modelo.Administrador;
import Modelo.AdministradorDAO;
import Vista.Ventana_Actualizar_Admin;
import Vista.Ventana_Admin;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Optional;
import javax.swing.JOptionPane;
import org.mindrot.jbcrypt.BCrypt;
import raven.glasspanepopup.GlassPanePopup;

public class ControladorActualizarAdmin {

    private Vista.Ventana_Actualizar_Admin vista;

    private Administrador admin;
    private AdministradorDAO adminDAO;
    private byte[] iconoBytes;
    private Vista.Ventana_Admin ventanaAdmin;

    public ControladorActualizarAdmin(
            Vista.Ventana_Actualizar_Admin vista,
            Administrador admin,
            Vista.Ventana_Admin ventanaAdmin) {
        this.vista = vista;
        this.admin = admin;
        this.adminDAO = new AdministradorDAO();
        this.ventanaAdmin = ventanaAdmin;

        vista.cargarDatosAdministrador(admin);
        final boolean tieneCedulaOriginal = admin.getCedula() != null && !admin.getCedula().trim().isEmpty();
        if (tieneCedulaOriginal) {
            vista.getTxtCedula().setEnabled(false);
            vista.getTxtCedula().setText(admin.getCedula());
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
                        vista.getTxtCedula().setText(admin.getCedula());
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

        vista.getAceptar().addActionListener(e -> actualizarAdministrador());
        vista.getLimpiar().addActionListener(e -> cargarImagen());
        vista.getCancelar().addActionListener(e -> GlassPanePopup.closePopupLast());
    }

    private void cargarImagen() {
        iconoBytes = ImagenUtils.seleccionarYConvertirImagen(vista.getLblFotoPerfil());
    }

    private void actualizarAdministrador() {
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

            if (nacionalidad.equalsIgnoreCase("Seleccione Nacionalidad") || nacionalidad.isEmpty()) {
                JOptionPane.showMessageDialog(vista, "Por favor, seleccione una nacionalidad válida.");
                return;
            }

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

            admin.setNombres(nombres);
            admin.setApellidos(apellidos);
            admin.setCorreo(correo);
            admin.setUsuario(usuarioNombre);
            admin.setNacionalidad(nacionalidad);
            admin.setGenero(genero);
            admin.setFechaNacimiento(fechaNacimiento);
            admin.setSobreMi(sobreMi);
            admin.setCedula(nacionalidad.equalsIgnoreCase("Ecuatoriana") ? cedulaStr : null);
            if (iconoBytes != null) {
                admin.setIcono(iconoBytes);
            }
            if (!contrasena.isBlank()) {
                if (!contrasena.matches("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[#\\$&\\-.])[A-Za-z\\d#\\$&\\-.]{8,}$")) {
                    JOptionPane.showMessageDialog(vista, "Contraseña insegura. Debe tener:\n- Letras y números\n- 1 símbolo\n- Mínimo 8 caracteres");
                    return;
                }
                admin.setContrasena(BCrypt.hashpw(contrasena, BCrypt.gensalt()));
            }

            boolean actualizado = adminDAO.actualizar(admin);
            if (actualizado) {
                JOptionPane.showMessageDialog(vista, "Administrador actualizado correctamente.");
                ventanaAdmin.recargarTabla();
                GlassPanePopup.closePopupLast();
            } else {
                JOptionPane.showMessageDialog(vista, "Error al actualizar administrador.");
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
