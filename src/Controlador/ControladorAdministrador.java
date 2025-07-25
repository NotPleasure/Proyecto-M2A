package Controlador;

import Design.Imagen.ImagenUtils;
import Design.RoundedFormattedTextField;
import Modelo.Administrador;
import Modelo.AdministradorDAO;
import Modelo.Persona;
import Modelo.PersonaDAO;
import Modelo.RolDAO;
import Vista.Ventana_FormularioAdmin;
import Vista.Ventana_Admin;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Optional;
import javax.swing.JOptionPane;
import org.mindrot.jbcrypt.BCrypt;
import raven.glasspanepopup.GlassPanePopup;

public class ControladorAdministrador implements ActionListener {

    private Ventana_FormularioAdmin vista;
    private Ventana_Admin ventanaAdministradores;
    private PersonaDAO personaDAO;
    private byte[] iconoBytes;

    public ControladorAdministrador(Ventana_FormularioAdmin vista, Ventana_Admin ventanaAdministradores) {
        this.vista = vista;
        this.ventanaAdministradores = ventanaAdministradores;
        this.personaDAO = new PersonaDAO();

        vista.getNacionalidades().addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                String seleccion = e.getItem().toString();
                boolean esEcuatoriana = seleccion.equalsIgnoreCase("Ecuatoriana");
                vista.getTxtCedula().setEnabled(esEcuatoriana);
                if (!esEcuatoriana) {
                    vista.getTxtCedula().setText("");
                }
            }
        });

        vista.getAceptar().addActionListener(this);
        vista.getLimpiar().addActionListener(e -> cargarImagen());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Botón presionado");

        if (e.getSource() == vista.getAceptar()) {
            registrarAdministrador();
        }
    }

    private void cargarImagen() {
        iconoBytes = ImagenUtils.seleccionarYConvertirImagen(vista.getLblFotoPerfil());
        if (iconoBytes != null) {
            ImagenUtils.cargarDesdeBytes(iconoBytes, vista.getLblFotoPerfil());
        }
    }

    private void registrarAdministrador() {
        try {
            String cedula = vista.getTxtCedula().getText().trim();
            String nombres = vista.getNombres().getText().trim();
            String apellidos = vista.getApellidos().getText().trim();
            RoundedFormattedTextField fechaField = vista.getFecha();
            String fechaTextoCrudo = fechaField.getText().trim().replace("/", "").replace("_", "");
            String genero = Optional.ofNullable(vista.getGeneros().getSelectedItem())
                    .map(Object::toString)
                    .orElse("");
            String sobreMi = vista.getTxtSobreMi().getText().trim();
            String usuario = vista.getTxtNombredeusuario().getText().trim();
            String correo = vista.getCorreoElectronico().getText().trim();
            String contrasena = new String(vista.getContrasenia().getPassword());

            if (usuario.isEmpty() || contrasena.isEmpty() || correo.isEmpty()
                    || vista.getNacionalidades().getSelectedItem() == null || genero.isEmpty()) {
                JOptionPane.showMessageDialog(vista, "Complete todos los campos obligatorios");
                return;
            }

            String nacionalidad = vista.getNacionalidades().getSelectedItem().toString().trim();
            if (nacionalidad.equalsIgnoreCase("Ecuatoriana") && cedula.isEmpty()) {
                JOptionPane.showMessageDialog(vista, "Por favor, ingrese la cédula.");
                return;
            }

            if (nacionalidad.equalsIgnoreCase("Ecuatoriana") && !validarCedulaEcuatoriana(cedula)) {
                JOptionPane.showMessageDialog(vista, "Cédula inválida. Ingrese una cédula ecuatoriana correcta.");
                return;
            }

            if (nacionalidad.equalsIgnoreCase("Ecuatoriana") && personaDAO.existeCedula(cedula)) {
                JOptionPane.showMessageDialog(vista, "La cédula ya está registrada.");
                return;
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

            if (!contrasena.matches("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[#\\$&\\-.])[A-Za-z\\d#\\$&\\-.]{8,}$")) {
                JOptionPane.showMessageDialog(vista,
                        "La contraseña debe tener:\n- Mínimo 8 caracteres\n- Letras y números\n- Al menos uno de estos: # $ & - .");
                return;
            }

            if (!correo.matches("^[\\w._%+-]+@[\\w.-]+\\.[A-Za-z]{2,}$")) {
                JOptionPane.showMessageDialog(vista, "Correo electrónico inválido");
                return;
            }

            if (personaDAO.existeCorreo(correo)) {
                JOptionPane.showMessageDialog(vista, "El correo ya está registrado.");
                return;
            }

            if (!nombres.isEmpty() && !nombres.matches("[\\p{L} ]+")) {
                JOptionPane.showMessageDialog(vista, "Nombres solo deben contener letras y espacios");
                return;
            }
            if (!apellidos.isEmpty() && !apellidos.matches("[\\p{L} ]+")) {
                JOptionPane.showMessageDialog(vista, "Apellidos solo deben contener letras y espacios");
                return;
            }

            if (iconoBytes != null && iconoBytes.length > 2 * 1024 * 1024) {
                JOptionPane.showMessageDialog(vista, "La imagen es demasiado grande (Máx: 2MB)");
                return;
            }

            if (nacionalidad.equalsIgnoreCase("Seleccione Nacionalidad") || nacionalidad.isEmpty()) {
                JOptionPane.showMessageDialog(vista, "Por favor, seleccione una nacionalidad válida.");
                return;
            }

            Persona persona = new Persona();
            persona.setCedula(cedula.isEmpty() ? null : cedula);
            persona.setNombres(nombres.isEmpty() ? null : nombres);
            persona.setApellidos(apellidos.isEmpty() ? null : apellidos);
            persona.setFechaNacimiento(fechaNacimiento);
            persona.setGenero(genero);
            persona.setNacionalidad(nacionalidad);
            persona.setSobreMi(sobreMi.isEmpty() ? null : sobreMi);
            persona.setIcono(iconoBytes);
            persona.setUsuario(usuario);
            persona.setCorreo(correo);
            persona.setContrasena(BCrypt.hashpw(contrasena, BCrypt.gensalt()));

            RolDAO rolDAO = new RolDAO();
            int rolId = rolDAO.obtenerIdRolPorNombre("ADMIN");
            persona.setRolId(rolId);

            if (personaDAO.insertar(persona)) {
                Administrador admin = new Administrador();
                admin.setIdPersona(persona.getIdPersona());
                admin.setCodigoAdmin(generarCodigoAdmin());
                AdministradorDAO adminDAO = new AdministradorDAO();

                if (adminDAO.insertar(admin)) {
                    JOptionPane.showMessageDialog(vista, "Administrador registrado exitosamente!");
                    ventanaAdministradores.recargarTabla();
                    GlassPanePopup.closePopupLast();
                } else {
                    JOptionPane.showMessageDialog(vista, "Error al registrar administrador en la tabla administrador");
                }
            } else {
                JOptionPane.showMessageDialog(vista, "Error al registrar persona");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(vista, "Error inesperado:\n" + ex.toString());
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

    //Generar código para el admin:
    public String generarCodigoAdmin() {
        int randomNum = (int) (Math.random() * 9000) + 1000;
        return "ADM" + randomNum;
    }
}
