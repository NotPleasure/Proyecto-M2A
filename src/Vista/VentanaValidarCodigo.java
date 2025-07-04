/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Vista;

import Animations.Animator;
import Controlador.ControladorLogin;
import Vista.VentanaCambiarContrasenia;
import javax.swing.JOptionPane;
import java.sql.SQLException;
import Design.RoundedBorder;
import Design.RoundedButton;
import Design.RoundedPanelContrasenia;
import Email.EmailService;
import Modelo.Codigo;
import Modelo.CodigoDAO;
import Modelo.PersonaDAO;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDateTime;
import javax.swing.SwingUtilities;

/**
 *
 * @author USER
 */
public class VentanaValidarCodigo extends javax.swing.JFrame {

    private String email;
    private javax.swing.JTextField txtCodigo;
    private String Correo_electronico = "Correo electronico";

    public VentanaValidarCodigo(String email) {
        initComponents();
        this.email = email;
        
        
        Reenviar.addMouseListener(new java.awt.event.MouseAdapter() {
        @Override
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            System.out.println("Reenviar clic detectado"); 
            try {
                if (email != null && !email.isEmpty()) {
                    String nuevoCodigo = CodigoDAO.generarCodigo();

                    Codigo nuevo = new Codigo(email, nuevoCodigo, LocalDateTime.now());
                    CodigoDAO dao = new CodigoDAO();
                    dao.setCodigo(nuevoCodigo);

                    EmailService.sendResetEmail(email, nuevoCodigo);

                    JOptionPane.showMessageDialog(null, "¡Se ha eviado un nuevo código enviado a tu correo!");
                } else {
                    JOptionPane.showMessageDialog(null, "No se puede reenviar porque el correo no está definido.");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error al reenviar el código.");
            }
        }
    });

        //Color Hypervínculo Reenviar:
        String originalText = Reenviar.getText();
        Reenviar.setText("<html><u><span style='color:#F9CC4B;'>" + originalText + "</span></u></html>");
        Reenviar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        //Fuentes:
        Verificar.setFont(new Font("Montserrat SemiBold", Font.PLAIN, 26));
        CodigoEnviado.setFont(new Font("Caviar Dreams", Font.PLAIN, 14));
        txtCodigoRecibido.setFont(new Font("Caviar Dreams", Font.PLAIN, 16));
        No.setFont(new Font("Caviar Dreams", Font.PLAIN, 14));
        Reenviar.setFont(new Font("Caviar Dreams", Font.PLAIN, 14));

        //Hacer la ventana redonda:
        setShape(new java.awt.geom.RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 60, 60));

        //Poner PlaceHolder al TextField y darle bordes:
        txtCodigoRecibido.setBorder(new RoundedBorder(new Color(165, 170, 163), 1, 30, 30));
        txtCodigoRecibido.setOpaque(false);

        ponerPlaceholder(txtCodigoRecibido, Correo_electronico);
    }

    private void ponerPlaceholder(javax.swing.JTextField campo, String textoPorDefecto) {
        campo.setText(textoPorDefecto);
        campo.setForeground(java.awt.Color.GRAY);

        campo.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent e) {
                if (campo.getText().equals(textoPorDefecto)) {
                    campo.setText("");
                    campo.setForeground(java.awt.Color.GRAY);
                }
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent e) {
                if (campo.getText().isEmpty()) {
                    campo.setForeground(java.awt.Color.GRAY);
                    campo.setText(textoPorDefecto);
                }
            }
        });
    }

    private void ponerPlaceholder(javax.swing.JPasswordField campo, String textoPorDefecto) {
        campo.setEchoChar((char) 0);
        campo.setText(textoPorDefecto);
        campo.setForeground(java.awt.Color.GRAY);

        campo.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent e) {
                String passText = new String(campo.getPassword());
                if (passText.equals(textoPorDefecto)) {
                    campo.setText("");
                    campo.setForeground(java.awt.Color.GRAY);
                    campo.setEchoChar('•');
                }
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent e) {
                String passText = new String(campo.getPassword());
                if (passText.isEmpty()) {
                    campo.setEchoChar((char) 0);
                    campo.setForeground(java.awt.Color.GRAY);
                    campo.setText(textoPorDefecto);
                }
            }
        });
       
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new RoundedPanelContrasenia();
        Continuar =  new RoundedButton("Entrar");
        txtCodigoRecibido = new javax.swing.JTextField();
        Verificar = new javax.swing.JLabel();
        CodigoEnviado = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        No = new javax.swing.JLabel();
        Reenviar = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        FondoLogin = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Continuar.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        Continuar.setText("Continuar");
        Continuar.setBorderPainted(false);
        Continuar.setContentAreaFilled(false);
        Continuar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Continuar.setFocusPainted(false);
        Continuar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ContinuarActionPerformed(evt);
            }
        });
        jPanel3.add(Continuar, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 340, 130, 40));

        txtCodigoRecibido.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtCodigoRecibido.setForeground(new java.awt.Color(0, 0, 0));
        txtCodigoRecibido.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCodigoRecibido.setText("Correo electrónico:");
        txtCodigoRecibido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCodigoRecibidoActionPerformed(evt);
            }
        });
        jPanel3.add(txtCodigoRecibido, new org.netbeans.lib.awtextra.AbsoluteConstraints(34, 210, 313, 54));

        Verificar.setForeground(new java.awt.Color(255, 255, 255));
        Verificar.setText("Verificar Código");
        jPanel3.add(Verificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(88, 110, 300, -1));

        CodigoEnviado.setForeground(new java.awt.Color(151, 157, 164));
        CodigoEnviado.setText("Ingrese el código enviado a su correo:");
        jPanel3.add(CodigoEnviado, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 150, -1, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/Candado 2.png"))); // NOI18N
        jPanel3.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 20, 90, 80));

        No.setForeground(new java.awt.Color(151, 157, 164));
        No.setText("¿No recibiste el código?");
        jPanel3.add(No, new org.netbeans.lib.awtextra.AbsoluteConstraints(52, 300, -1, -1));

        Reenviar.setText("Reenviar código");
        jPanel3.add(Reenviar, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 300, -1, -1));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 80, 380, 430));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/icons8_Expand_Arrow_32px.png"))); // NOI18N
        jLabel7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel7MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 10, 30, 30));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/Salir (2) (2).png"))); // NOI18N
        jLabel8.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel8MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 6, 30, 40));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/Atrás 2.png"))); // NOI18N
        jButton1.setBorderPainted(false);
        jButton1.setContentAreaFilled(false);
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.setFocusPainted(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 540, -1, 30));

        FondoLogin.setForeground(new java.awt.Color(11, 187, 187));
        FondoLogin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/Fondo PT.png"))); // NOI18N
        getContentPane().add(FondoLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 500, 590));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void ContinuarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ContinuarActionPerformed
        String codigoIngresado = txtCodigoRecibido.getText().trim();

        if (codigoIngresado.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, ingresa el código.");
            return;
        }

        try {
            CodigoDAO codigoDAO = new CodigoDAO();
            //boolean esValido = codigoDAO.validarCodigo(email, codigoIngresado);
             boolean esValido = codigoDAO.validarCodigo(codigoIngresado);
            if (esValido==true) {
                JOptionPane.showMessageDialog(this, "¡Código correcto!");

                Animator.fadeOut(this, () -> {
                    VentanaCambiarContrasenia cambiarVentana = new VentanaCambiarContrasenia(email);
                    cambiarVentana.setVisible(true);
                    Animator.fadeIn(cambiarVentana);
                    this.dispose();
                });

            } else {
                JOptionPane.showMessageDialog(this, "Código incorrecto. Inténtalo de nuevo.");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Ocurrió un error al verificar el código.");
        }

    }//GEN-LAST:event_ContinuarActionPerformed

    private void txtCodigoRecibidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodigoRecibidoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodigoRecibidoActionPerformed

    private void jLabel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseClicked
        this.setState(RecuperarContrasenia.ICONIFIED);
    }//GEN-LAST:event_jLabel7MouseClicked

    private void jLabel8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MouseClicked
        int dialog = JOptionPane.YES_NO_OPTION;
        int result = JOptionPane.showConfirmDialog(null, "¿Desea salir?", "Exit", dialog);
        if (result == 0) {
            System.exit(0);
        }
    }//GEN-LAST:event_jLabel8MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
    Animator.fadeOut(this, () -> {
        Login miR = new Login();
        ControladorLogin controladorLogin = new ControladorLogin(miR);
        Animator.fadeIn(miR);
    }); 


    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel CodigoEnviado;
    private javax.swing.JButton Continuar;
    private javax.swing.JLabel FondoLogin;
    private javax.swing.JLabel No;
    private javax.swing.JLabel Reenviar;
    private javax.swing.JLabel Verificar;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JTextField txtCodigoRecibido;
    // End of variables declaration//GEN-END:variables
}
