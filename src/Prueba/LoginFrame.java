/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Prueba;

/**
 *
 * @author USER
 */
import javax.swing.*;

public class LoginFrame extends JFrame {

    private JTextField txtUsuario = new JTextField("admin");
    private JPasswordField txtPassword = new JPasswordField("1234");
    private JButton btnLogin = new JButton("Iniciar sesión");

    public LoginFrame() {
        setTitle("Login");
        setSize(400, 200);
        setLayout(null);
        setUndecorated(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        txtUsuario.setBounds(100, 30, 200, 30);
        txtPassword.setBounds(100, 70, 200, 30);
        btnLogin.setBounds(130, 120, 140, 30);

        add(txtUsuario);
        add(txtPassword);
        add(btnLogin);

        btnLogin.addActionListener(e -> {
            String user = txtUsuario.getText().trim();
            String pass = new String(txtPassword.getPassword());

            if (user.equals("admin") && pass.equals("1234")) {
                Animator1.fadeOut(this, () -> {
                    new InicioFrame(user);
                });
            } else {
                JOptionPane.showMessageDialog(this, "Credenciales incorrectas");
            }
        });

        Animator1.fadeIn(this);
    }

    public static void main(String[] args) {
        // Activar soporte para transparencia en JFrames
        System.setProperty("sun.java2d.noddraw", "true");
        SwingUtilities.invokeLater(LoginFrame::new);
    }
}
