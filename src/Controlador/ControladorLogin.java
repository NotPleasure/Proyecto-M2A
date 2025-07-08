/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Animations.Animator;
import ConexionHuellasCuencanas.Seguridad;
import Modelo.Persona;
import Modelo.PersonaDAO;
import Vista.Admin_Panel;
import Vista.Login;
import Vista.Ventana_Principal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author USER
 */
public class ControladorLogin implements ActionListener {

    private Login vista;
    private PersonaDAO personaDAO;

    public ControladorLogin(Login vista) {
        this.vista = vista;
        this.personaDAO = new PersonaDAO();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String usuario = vista.getTxtNombreUsuario().getText().trim();
        String contraseña = new String(vista.getTxtContraseña().getPassword());

        if (usuario.isEmpty() || usuario.equals("Nombre de usuario") || contraseña.isEmpty() || contraseña.equals("Contraseña")) {
            JOptionPane.showMessageDialog(vista, "Por favor, rellena todos los campos.");
            return;
        }

        Persona personaLogueada = personaDAO.obtenerPersonaPorCredenciales(usuario, contraseña);

        if (personaLogueada != null) {
            int rolId = personaLogueada.getRolId();

            Animator.fadeOut(vista, () -> {
                JFrame ventana;
                if (rolId == 1) {
                    ventana = new Admin_Panel(true, personaLogueada);
                } else {
                    ventana = new Ventana_Principal(usuario);
                }
                ventana.setLocationRelativeTo(null);
                ventana.setOpacity(0f);
                ventana.setVisible(true);
                Animator.fadeIn(ventana);
                vista.dispose();
            });
        } else {
            JOptionPane.showMessageDialog(vista, "Usuario o contraseña incorrectos.");
        }
    }
}
