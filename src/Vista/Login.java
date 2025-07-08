/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import ConexionHuellasCuencanas.Seguridad;
import javax.swing.border.Border;
import Animations.Animator;
import Controlador.ControladorAdministrador;
import Controlador.ControladorLogin;
import Controlador.ControladorRegistro;
import Design.Message.MacOSWindowDialogFinal;
import java.awt.Font;
import Design.RoundedPanel;
import Design.RoundedBorder;
import Design.RounderButton2;
import Design.RoundedButton;
import Design.RoundedPanel;
import Modelo.Persona;
import Modelo.PersonaDAO;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import raven.glasspanepopup.GlassPanePopup;

/**
 *
 * @author USER
 */
public class Login extends javax.swing.JFrame {

    private final String Nombre_de_Usuario = "Nombre de usuario";
    private final String Contrasenia = "Contraseña";
    private boolean recuperacionAbierta = false;
    private int x;
    private int y;
    private boolean passwordVisible = false;

    /**
     * Creates new form Login
     */
    public Login() {
        setUndecorated(true);

        setOpacity(0f);
        initComponents();

        Animator.fadeIn(this);

        //Controlador de Admin:
       Iniciar_Sesión.addActionListener(new ControladorLogin(this));


        //Método que permite que la ventana se mueva:
        initMoving();

        //Fuentes de los labels.
        Inicio.setFont(new Font("Montserrat Bold", Font.PLAIN, 28));
        txtNombreUsuario.setFont(new Font("Caviar Dreams", Font.PLAIN, 14));
        txtContraseña.setFont(new Font("Caviar Dreams", Font.PLAIN, 14));
        Iniciar_Sesión.setFont(new Font("Caviar Dreams Bold", Font.PLAIN, 14));
        Registrarse.setFont(new Font("Caviar Dreams Bold", Font.PLAIN, 14));
        jLabel4.setFont(new Font("Caviar Dreams", Font.PLAIN, 13));
        jLabel4.setForeground(new Color(21, 67, 96));
        Huellas.setFont(new Font("Open Sans Bold", Font.PLAIN, 11));
        Cuencanas.setFont(new Font("Open Sans", Font.PLAIN, 11));

        //Centrar el texto de los JTextField:
        txtNombreUsuario.setHorizontalAlignment(JTextField.CENTER);
        txtContraseña.setHorizontalAlignment(JTextField.CENTER);

        //Hacer que la ventana apareciera en el centro.
        setLocationRelativeTo(null);

        //Variables para el placeholder:
        ponerPlaceholder(txtNombreUsuario, Nombre_de_Usuario);
        ponerPlaceholderPassword(txtContraseña, "Contraseña");

        //Darle bordes redondeados a la ventana:
        setShape(new java.awt.geom.RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 60, 60));

        //Darle borde a los textField:
        txtNombreUsuario.setBorder(new RoundedBorder(new Color(165, 170, 163), 1, 30, 30));
        txtNombreUsuario.setOpaque(false);

        txtContraseña.setBorder(new RoundedBorder(new Color(165, 170, 163), 1, 30, 30));
        txtContraseña.setOpaque(false);
    }
    //Método del placeholder:

    private void ponerPlaceholder(JTextField campo, String textoPorDefecto) {
        campo.setText(textoPorDefecto);
        campo.setForeground(Color.GRAY);

        campo.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent e) {
                if (campo.getText().equals(textoPorDefecto)) {
                    campo.setText("");
                    campo.setForeground(new Color(0, 0, 0, 255));
                }
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent e) {
                if (campo.getText().isEmpty()) {
                    campo.setForeground(Color.GRAY);
                    campo.setText(textoPorDefecto);
                }
            }
        });

        // Hypervínculo <<Recuperar Contraseña>>.
        MouseAdapter ma = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                if (!recuperacionAbierta
                        && SwingUtilities.isLeftMouseButton(evt)
                        && evt.getClickCount() == 1) {

                    recuperacionAbierta = true;
                    jLabel4.removeMouseListener(this);

                    Animator.fadeOut((JFrame) SwingUtilities.getWindowAncestor(jLabel4), () -> {
                        ((JFrame) SwingUtilities.getWindowAncestor(jLabel4)).dispose();

                        RecuperarContrasenia ventana = new RecuperarContrasenia();
                        ventana.setLocationRelativeTo(null);
                        Animator.fadeIn(ventana);
                    });
                }
            }

            @Override
            public void mouseEntered(MouseEvent evt) {
                jLabel4.setForeground(new java.awt.Color(41, 128, 185));
            }

            @Override
            public void mouseExited(MouseEvent evt) {
                jLabel4.setForeground(new java.awt.Color(21, 67, 96));
            }
        };

        jLabel4.addMouseListener(ma);
    }
    //Hacer que la ventana se mueva: 

    public void initMoving() {
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent me) {
                x = me.getX();
                y = me.getY();
            }
        });
        this.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent me) {
                setLocation(me.getXOnScreen() - x, me.getYOnScreen() - y);
            }
        });
    }

    //PlaceHolder para la contraseña:
    private void ponerPlaceholderPassword(JPasswordField campo, String textoPorDefecto) {
        campo.setEchoChar((char) 0);
        campo.setText(textoPorDefecto);
        campo.setForeground(Color.GRAY);

        campo.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent e) {
                if (String.valueOf(campo.getPassword()).equals(textoPorDefecto)) {
                    campo.setText("");
                    campo.setForeground(Color.BLACK);
                    campo.setEchoChar('•');
                }
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent e) {
                if (String.valueOf(campo.getPassword()).isEmpty()) {
                    campo.setForeground(Color.GRAY);
                    campo.setText(textoPorDefecto);
                    campo.setEchoChar((char) 0);
                }
            }
        });

    }

    //Getters y setters para los TextField:
    public JButton getIniciar_Sesión() {
        return Iniciar_Sesión;
    }

    public void setIniciar_Sesión(JButton Iniciar_Sesión) {
        this.Iniciar_Sesión = Iniciar_Sesión;
    }

    public JButton getRegistrarse() {
        return Registrarse;
    }

    public void setRegistrarse(JButton Registrarse) {
        this.Registrarse = Registrarse;
    }

    public JPasswordField getTxtContraseña() {
        return txtContraseña;
    }

    public void setTxtContraseña(JPasswordField txtContraseña) {
        this.txtContraseña = txtContraseña;
    }

    public JTextField getTxtNombreUsuario() {
        return txtNombreUsuario;
    }

    public void setTxtNombreUsuario(JTextField txtNombreUsuario) {
        this.txtNombreUsuario = txtNombreUsuario;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new RoundedPanel();
        jButton7 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        txtNombreUsuario = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        Iniciar_Sesión = new RoundedButton("");
        Inicio = new javax.swing.JLabel();
        Registrarse = new RounderButton2("");
        jButton1 = new javax.swing.JButton();
        txtContraseña = new javax.swing.JPasswordField();
        jButton2 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        Huellas = new javax.swing.JLabel();
        Cuencanas = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setForeground(new java.awt.Color(204, 204, 204));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/pr password (1).png"))); // NOI18N
        jButton7.setBorderPainted(false);
        jButton7.setContentAreaFilled(false);
        jButton7.setFocusPainted(false);
        jButton7.setFocusable(false);
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 168, 30, 30));

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/pr user (2).png"))); // NOI18N
        jButton5.setBorderPainted(false);
        jButton5.setContentAreaFilled(false);
        jButton5.setFocusPainted(false);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(26, 96, -1, -1));

        txtNombreUsuario.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        txtNombreUsuario.setForeground(new java.awt.Color(255, 255, 255));
        txtNombreUsuario.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtNombreUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreUsuarioActionPerformed(evt);
            }
        });
        jPanel2.add(txtNombreUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, 256, 44));

        jLabel4.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 51, 255));
        jLabel4.setText("¿Has olvidado la contraseña?");
        jLabel4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 224, -1, -1));

        Iniciar_Sesión.setText("Iniciar Sesión");
        Iniciar_Sesión.setBorderPainted(false);
        Iniciar_Sesión.setContentAreaFilled(false);
        Iniciar_Sesión.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Iniciar_Sesión.setFocusPainted(false);
        Iniciar_Sesión.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Iniciar_SesiónActionPerformed(evt);
            }
        });
        jPanel2.add(Iniciar_Sesión, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 260, 250, 40));

        Inicio.setFont(new java.awt.Font("Segoe UI", 0, 28)); // NOI18N
        Inicio.setText("Iniciar Sesión");
        jPanel2.add(Inicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(66, 24, -1, -1));

        Registrarse.setText("Registrarse");
        Registrarse.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Registrarse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RegistrarseActionPerformed(evt);
            }
        });
        jPanel2.add(Registrarse, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 320, 250, 40));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/hidden (1).png"))); // NOI18N
        jButton1.setBorderPainted(false);
        jButton1.setContentAreaFilled(false);
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.setFocusPainted(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 172, 40, 20));

        txtContraseña.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel2.add(txtContraseña, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 160, 256, 44));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 70, 320, 390));

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/Copilot_20250610_114322 (3).png"))); // NOI18N
        jButton2.setBorderPainted(false);
        jButton2.setContentAreaFilled(false);
        jButton2.setFocusPainted(false);
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 60, 70));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/icons8_Expand_Arrow_32px.png"))); // NOI18N
        jLabel6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel6MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 4, 30, 30));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/Salir (2) (2).png"))); // NOI18N
        jLabel5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(454, 0, 30, 40));

        Huellas.setForeground(new java.awt.Color(255, 255, 255));
        Huellas.setText("Huellas ");
        jPanel1.add(Huellas, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 20, -1, -1));

        Cuencanas.setForeground(new java.awt.Color(255, 255, 255));
        Cuencanas.setText("Cuencanas");
        jPanel1.add(Cuencanas, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 30, -1, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/Fondo PT.png"))); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 500, 690));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 500, 520));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseClicked
        this.setState(Login.ICONIFIED);    }//GEN-LAST:event_jLabel6MouseClicked

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
        int dialog = JOptionPane.YES_NO_OPTION;
        int result = JOptionPane.showConfirmDialog(null, "¿Desea salir?", "Exit", dialog);
        if (result == 0) {
            System.exit(0);
        }    }//GEN-LAST:event_jLabel5MouseClicked

    private void RegistrarseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RegistrarseActionPerformed
        Animator.fadeOut(this, () -> {
            Registro miR = new Registro();
            ControladorRegistro c = new ControladorRegistro(miR);
            miR.setControlador(c);
            Animator.fadeIn(miR);
        });
    }//GEN-LAST:event_RegistrarseActionPerformed

    private void Iniciar_SesiónActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Iniciar_SesiónActionPerformed


    }//GEN-LAST:event_Iniciar_SesiónActionPerformed

    private void txtNombreUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreUsuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreUsuarioActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        if (passwordVisible) {
            txtContraseña.setEchoChar('•');
            jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/hidden (1).png")));
            passwordVisible = false;
        } else {
            txtContraseña.setEchoChar((char) 0);
            jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/watch (1).png")));
            passwordVisible = true;
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton5ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Cuencanas;
    private javax.swing.JLabel Huellas;
    private javax.swing.JButton Iniciar_Sesión;
    private javax.swing.JLabel Inicio;
    private javax.swing.JButton Registrarse;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPasswordField txtContraseña;
    private javax.swing.JTextField txtNombreUsuario;
    // End of variables declaration//GEN-END:variables
}
