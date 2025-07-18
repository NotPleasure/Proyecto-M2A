package Controlador;

import Modelo.Persona;
import Modelo.PersonaDAO;
import Modelo.RolDAO;
import Modelo.SuperUsuario;
import Modelo.SuperUsuarioDAO;
import Vista.Ventana_FormularioSuperUsuario;
import Vista.Ventana_SuperUsuario;
import Design.Imagen.ImagenUtils;
import Design.RoundedFormattedTextField;
import Email.EmailService;
import jakarta.mail.MessagingException;
import org.mindrot.jbcrypt.BCrypt;
import raven.glasspanepopup.GlassPanePopup;

import javax.swing.*;
import java.awt.event.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Optional;

public class ControladorInsertarSuperUsuario implements ActionListener {

    private final Ventana_FormularioSuperUsuario vistaForm;
    private final Ventana_SuperUsuario ventanaPadre;
    private final PersonaDAO personaDAO;
    private final SuperUsuarioDAO superusuarioDAO;
    private byte[] iconoBytes;

    public ControladorInsertarSuperUsuario(Ventana_SuperUsuario ventanaPadre,
            Ventana_FormularioSuperUsuario vistaForm) {
        this.vistaForm = vistaForm;
        this.ventanaPadre = ventanaPadre;
        this.personaDAO = new PersonaDAO();
        this.superusuarioDAO = new SuperUsuarioDAO();

        vistaForm.getNacionalidades().addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                boolean esEc = e.getItem().toString().equalsIgnoreCase("Ecuatoriana");
                vistaForm.getTxtCedula().setEnabled(esEc);
                if (!esEc) {
                    vistaForm.getTxtCedula().setText("");
                }
            }
        });
        vistaForm.getAceptar().addActionListener(this);
        vistaForm.getLimpiar().addActionListener(evt -> cargarImagen());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vistaForm.getAceptar()) {
            registrarSuperusuario();
        }
    }

    private void cargarImagen() {
        iconoBytes = ImagenUtils.seleccionarYConvertirImagen(vistaForm.getLblFotoPerfil());
        if (iconoBytes != null) {
            ImagenUtils.cargarDesdeBytes(iconoBytes, vistaForm.getLblFotoPerfil());
        }
    }

    private void registrarSuperusuario() {
        try {
            String plainPass = new String(vistaForm.getContrasenia().getPassword());

            String cedula = vistaForm.getTxtCedula().getText().trim();
            String usuario = vistaForm.getTxtNombredeusuario().getText().trim();
            String correo = vistaForm.getCorreoElectronico().getText().trim();
            String nacionalidad = Optional.ofNullable(vistaForm.getNacionalidades().getSelectedItem())
                    .map(Object::toString).orElse("");
            String genero = Optional.ofNullable(vistaForm.getGeneros().getSelectedItem())
                    .map(Object::toString).orElse("");
            String sobreMi = vistaForm.getTxtSobreMi().getText().trim();
            String nombres = vistaForm.getNombres().getText().trim();
            String apellidos = vistaForm.getApellidos().getText().trim();
            String fechaRaw = vistaForm.getFecha().getText()
                    .trim().replace("/", "").replace("_", "");

            if (usuario.isEmpty() || plainPass.isEmpty() || correo.isEmpty()
                    || nacionalidad.isEmpty() || genero.isEmpty()) {
                JOptionPane.showMessageDialog(vistaForm, "Complete todos los campos obligatorios");
                return;
            }
            if (nacionalidad.equalsIgnoreCase("Ecuatoriana") && cedula.isEmpty()) {
                JOptionPane.showMessageDialog(vistaForm, "Ingrese cédula para nacionalidad ecuatoriana");
                return;
            }
            if (nacionalidad.equalsIgnoreCase("Ecuatoriana") && !validarCedulaEcuatoriana(cedula)) {
                JOptionPane.showMessageDialog(vistaForm, "Cédula inválida");
                return;
            }
            if (nacionalidad.equalsIgnoreCase("Ecuatoriana") && personaDAO.existeCedula(cedula)) {
                JOptionPane.showMessageDialog(vistaForm, "Cédula ya registrada");
                return;
            }
            LocalDate fechaNac = null;
            if (!fechaRaw.isEmpty()) {
                if (!fechaRaw.matches("\\d{8}")) {
                    JOptionPane.showMessageDialog(vistaForm, "Formato fecha inválido (DDMMAAAA)");
                    return;
                }
                try {
                    SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy");
                    fechaNac = sdf.parse(fechaRaw)
                            .toInstant()
                            .atZone(ZoneId.systemDefault())
                            .toLocalDate();
                } catch (ParseException ex) {
                    JOptionPane.showMessageDialog(vistaForm, "Fecha inválida: " + ex.getMessage());
                    return;
                }
            }
            if (!plainPass.matches("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[#\\$&\\-.])[A-Za-z\\d#\\$&\\-.]{8,}$")) {
                JOptionPane.showMessageDialog(vistaForm,
                        "La contraseña debe tener mínimo 8 caracteres,\nletras, números y uno de # $ & - .");
                return;
            }
            if (!correo.matches("^[\\w._%+-]+@[\\w.-]+\\.[A-Za-z]{2,}$")) {
                JOptionPane.showMessageDialog(vistaForm, "Correo inválido");
                return;
            }
            if (personaDAO.existeCorreo(correo)) {
                JOptionPane.showMessageDialog(vistaForm, "Correo ya registrado");
                return;
            }
            if (!nombres.isEmpty() && !nombres.matches("[\\p{L} ]+")) {
                JOptionPane.showMessageDialog(vistaForm, "Nombres solo letras y espacios");
                return;
            }
            if (!apellidos.isEmpty() && !apellidos.matches("[\\p{L} ]+")) {
                JOptionPane.showMessageDialog(vistaForm, "Apellidos solo letras y espacios");
                return;
            }
            if (iconoBytes != null && iconoBytes.length > 2 * 1024 * 1024) {
                JOptionPane.showMessageDialog(vistaForm, "Imagen demasiado grande (Max 2MB)");
                return;
            }

            Persona persona = new Persona();
            persona.setCedula(cedula.isEmpty() ? null : cedula);
            persona.setUsuario(usuario);
            persona.setCorreo(correo);
            persona.setNacionalidad(nacionalidad);
            persona.setGenero(genero);
            persona.setNombres(nombres.isEmpty() ? null : nombres);
            persona.setApellidos(apellidos.isEmpty() ? null : apellidos);
            persona.setFechaNacimiento(fechaNac);
            persona.setSobreMi(sobreMi.isEmpty() ? null : sobreMi);
            persona.setIcono(iconoBytes);

            RolDAO rolDAO = new RolDAO();
            int rolId = rolDAO.obtenerIdRolPorNombre("SUPERUSUARIO");
            persona.setRolId(rolId);

            String hashedPass = BCrypt.hashpw(plainPass, BCrypt.gensalt());
            persona.setContrasena(hashedPass);

            if (!personaDAO.insertar(persona)) {
                JOptionPane.showMessageDialog(vistaForm, "Error al registrar persona");
                return;
            }

            SuperUsuario su = new SuperUsuario();
            su.setIdPersona(persona.getIdPersona());

            if (superusuarioDAO.insertar(su)) {
                try {
                    EmailService.sendCredentialsEmail(
                            persona.getCorreo(),
                            persona.getUsuario(),
                            plainPass
                    );
                    JOptionPane.showMessageDialog(vistaForm,
                            "Superusuario creado y correo enviado correctamente.");
                } catch (MessagingException me) {
                    me.printStackTrace();
                    JOptionPane.showMessageDialog(vistaForm,
                            "Superusuario creado, pero no se pudo enviar el correo.");
                }

                ventanaPadre.recargarTabla();
                GlassPanePopup.closePopupLast();
            } else {
                JOptionPane.showMessageDialog(vistaForm,
                        "Error al registrar en tabla superusuario");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(vistaForm,
                    "Error inesperado:\n" + ex.toString());
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
