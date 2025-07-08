package Controlador;

import Animations.Animator;
import Modelo.Persona;
import Modelo.PersonaDAO;
import Modelo.RolDAO;
import Modelo.SuperUsuarioDAO;
import Vista.Ventana_Registro_SuperUsuario;
import Vista.Login;
import org.mindrot.jbcrypt.BCrypt;
import jakarta.mail.*;
import jakarta.mail.internet.*;
import java.util.Properties;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControladorSuperUsuario implements ActionListener {

    private Ventana_Registro_SuperUsuario vista;
    private PersonaDAO personaDAO;
    private SuperUsuarioDAO superUsuarioDAO;

    public ControladorSuperUsuario(Ventana_Registro_SuperUsuario vista) {
        this.vista = vista;
        this.personaDAO = new PersonaDAO();
        this.superUsuarioDAO = new SuperUsuarioDAO();
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
        registrarSuperUsuario(false);
    }

    public void registrarSuperUsuario(boolean redireccionar) {

        System.out.println("registrarSuperUsuario llamado");
        System.out.println("getTxtDireccion: " + vista.getTxtDireccion().getText());
        System.out.println("getTxtNombreNegocio: " + vista.getTxtNombredeNegocio().getText());

        String nacionalidad = vista.getjComboBox2().getSelectedItem() != null
                ? vista.getjComboBox2().getSelectedItem().toString().trim()
                : "";

        if (vista.getTxtNombredeusuario2().getText().trim().isEmpty()
                || vista.getTxtCorreo1().getText().trim().isEmpty()
                || vista.getjComboBox2().getSelectedItem() == null
                || vista.getjComboBox3().getSelectedItem() == null
                || vista.getCargo().getSelectedItem() == null
                || vista.getTxtNombredeNegocio().getText().trim().isEmpty()
                || vista.getTxtDireccion().getText().trim().isEmpty()
                || vista.getDescripcion().getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(vista, "Por favor, rellena todos los campos.");
            return;
        }

        String cedula = vista.getTxtCedula().getText().trim();
        System.out.println("Cédula capturada: " + cedula);
        String usuario = vista.getTxtNombredeusuario2().getText().trim();
        String correo = vista.getTxtCorreo1().getText().trim();
        String genero = vista.getjComboBox3().getSelectedItem().toString().trim();
        String cargo = vista.getCargo().getSelectedItem().toString().trim();
        String nombreNegocio = vista.getTxtDireccion().getText().trim();
        String direccion = vista.getTxtNombredeNegocio().getText().trim();

        String descripcion = vista.getDescripcion().getText().trim();

        if (nacionalidad.equalsIgnoreCase("Ecuatoriana")) {
            if (!validarCedulaEcuatoriana(cedula)) {
                JOptionPane.showMessageDialog(vista,
                        "Cédula inválida. Ingrese una cédula ecuatoriana correcta.");
                return;
            }
            if (personaDAO.existeCedula(cedula)) {
                JOptionPane.showMessageDialog(vista,
                        "Ya existe una persona con esa cédula.");
                return;
            }
        } else {
            cedula = null;
        }

        if (usuario.length() < 3 || !usuario.matches("^[A-Za-z]+$")) {
            JOptionPane.showMessageDialog(vista, "El nombre de usuario debe tener al menos 3 letras sin números ni espacios.");
            return;
        }

        if (personaDAO.existeUsuario(usuario)) {
            JOptionPane.showMessageDialog(vista, "Ese nombre de usuario ya está registrado.");
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

        if (nacionalidad.equalsIgnoreCase("Seleccione nacionalidad")
                || genero.equalsIgnoreCase("Seleccione género")
                || cargo.equalsIgnoreCase("Seleccione cargo")) {

            JOptionPane.showMessageDialog(vista, "Debe seleccionar nacionalidad, género y cargo válidos.");
            return;
        }

        enviarCorreoSuperUsuario(
                correo,
                usuario,
                cedula,
                direccion,
                nombreNegocio,
                descripcion,
                cargo
        );

        JOptionPane.showMessageDialog(vista, "Solicitud enviada correctamente. Pronto nos pondremos en contacto contigo.");
        Animator.fadeOut(vista, () -> {
            Login login = new Login();
            Animator.fadeIn(login);
            vista.dispose();
        });
    }

    private boolean validarCedulaEcuatoriana(String cedula) {
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

        int verificador = (10 - (suma % 10)) % 10;
        return verificador == Character.getNumericValue(cedula.charAt(9));
    }

    public void enviarCorreoSuperUsuario(String correoDestino, String usuario, String cedula, String direccion, String nombreNegocio, String descripcion, String cargo) {
        final String remitente = "davidnasqui@gmail.com";
        final String password = "lusn qioq wjbu ejcj";

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(remitente, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(remitente));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("davidnasqui@gmail.com"));
            message.setSubject("Nuevo Superusuario Registrado");

            String contenido = "Se ha registrado un nuevo superusuario con los siguientes datos:\n\n"
                    + "Usuario: " + usuario + "\n"
                    + "Cédula: " + (cedula == null ? "No aplica" : cedula) + "\n"
                    + "Correo: " + correoDestino + "\n"
                    + "Dirección: " + direccion + "\n"
                    + "Nombre del negocio: " + nombreNegocio + "\n"
                    + "Descripción: " + descripcion + "\n"
                    + "Cargo: " + cargo + "\n\n"
                    + "Por favor, asigna la contraseña y notifica al usuario.";

            message.setText(contenido);

            Transport.send(message);
            System.out.println("Correo enviado correctamente.");
        } catch (MessagingException e) {
            e.printStackTrace();
            System.err.println("Error al enviar el correo.");
        }
    }

}
