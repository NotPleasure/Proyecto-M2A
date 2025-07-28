package Controlador;

import Animations.Animator;
import ConexionHuellasCuencanas.Seguridad;
import Modelo.Persona;
import Modelo.Administrador;
import Modelo.Usuario;
import Modelo.SuperUsuario;
import Modelo.PersonaDAO;
import Vista.Admin_Panel;
import Vista.Login;
import Vista.Ventana_Principal;
import Vista.SuperUsuario_Panel;
import Vista.Ventana_VistaSuperUsuario;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import utils.Session;

/**
 * Controlador para el login que abre la ventana adecuada según la subclase de
 * Persona.
 */
public class ControladorLogin implements ActionListener {

    private final Login vista;
    private final PersonaDAO personaDAO;

    public ControladorLogin(Login vista) {
        this.vista = vista;
        this.personaDAO = new PersonaDAO();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String usuario = vista.getTxtNombreUsuario().getText().trim();
        String contraseña = new String(vista.getTxtContraseña().getPassword());

        if (usuario.isEmpty() || usuario.equals("Nombre de usuario")
                || contraseña.isEmpty() || contraseña.equals("Contraseña")) {
            JOptionPane.showMessageDialog(vista, "Por favor, rellena todos los campos.");
            return;
        }

        Persona personaLogueada = personaDAO.obtenerPersonaPorCredenciales(usuario, contraseña);

        if (personaLogueada == null) {
            JOptionPane.showMessageDialog(vista, "Usuario o contraseña incorrectos.");
            return;
        }
        int idUsuario = personaLogueada.getIdPersona(); 
        Session.setCurrentUserId(idUsuario);

        Animator.fadeOut(vista, () -> {
            JFrame ventana;
            if (personaLogueada instanceof Administrador) {
                ventana = new Admin_Panel(true, personaLogueada);

            } else if (personaLogueada instanceof Usuario) {
                ventana = new Ventana_Principal(personaLogueada);

            } else if (personaLogueada instanceof SuperUsuario) {
                ventana = new Ventana_VistaSuperUsuario(personaLogueada);

            } else {
                ventana = new Ventana_Principal(personaLogueada);
            }

            ventana.setLocationRelativeTo(null);
            ventana.setOpacity(0f);
            ventana.setVisible(true);
            Animator.fadeIn(ventana);
            vista.dispose();
        });
    }
}
