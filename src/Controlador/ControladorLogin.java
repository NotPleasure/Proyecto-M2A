/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;
import ConexionHuellasCuencanas.Seguridad;
import Modelo.PersonaDAO;
import Vista.Login;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
        String contraseña = vista.getTxtContraseña().getText().trim();

        if (usuario.isEmpty() || contraseña.isEmpty()) {
            JOptionPane.showMessageDialog(vista, "Por favor, rellena todos los campos.");
            return;
        }

        if (personaDAO.validarLogin(usuario, contraseña)) {
            JOptionPane.showMessageDialog(vista, "Login exitoso.");
        } else {
            JOptionPane.showMessageDialog(vista, "Usuario o contraseña incorrectos.");
        }
    }
}
