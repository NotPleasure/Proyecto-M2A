/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Design.RoundedButton;
import Design.RoundedPanel;
import Design.RoundedBorder;
import Design.RounderButton2;
import Design.RoundedButton;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.JOptionPane;

/**
 *
 * @author USER
 */
public class Registro_SuperUsuario extends javax.swing.JFrame {

    //Delaración de las variaables para los placeholders.
    private String Nombre_de_Usuario = "Nombre de usuario";
    private String Correo_electronico = "Correo electronico";
    private String Contrasenia = "Contraseña";
    private String Confirmar_contrasenia = "Confirmar contraseña";
    private int x;
    private int y;
    /**
     * Creates new form Registro_SuperUsuario
     */
    public Registro_SuperUsuario() {
        initComponents();
        setLocationRelativeTo(null);

        //Método que permite que la ventana se mueva:
        initMoving();
        
        //Hacer redondeada la ventana: 
        setShape(new java.awt.geom.RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 60, 60));

        // Fuentes de los labels.
        jLabel1.setFont(new Font("Open Sans Bold", Font.PLAIN, 30));
        txtNombredeusuario.setFont(new Font("Open Sans", Font.PLAIN, 13));
        txtCorreo.setFont(new Font("Open Sans", Font.PLAIN, 13));
        txtContraseña.setFont(new Font("Open Sans", Font.PLAIN, 13));
        txtConfirmarContraseña.setFont(new Font("Open Sans", Font.PLAIN, 13));
        jLabel3.setFont(new Font("Open Sans", Font.PLAIN, 13));

        //Dar bordes a los TextField.
        setLocationRelativeTo(null);
        txtNombredeusuario.setBorder(new RoundedBorder(new Color(165, 170, 163), 2, 30, 30));
        txtNombredeusuario.setOpaque(false);

        txtCorreo.setBorder(new RoundedBorder(new Color(165, 170, 163), 2, 30, 30));
        txtCorreo.setOpaque(false);

        txtContraseña.setBorder(new RoundedBorder(new Color(165, 170, 163), 2, 30, 30));
        txtContraseña.setOpaque(false);

        txtConfirmarContraseña.setBorder(new RoundedBorder(new Color(165, 170, 163), 2, 30, 30));
        txtConfirmarContraseña.setOpaque(false);

        ponerPlaceholder(txtNombredeusuario, Nombre_de_Usuario);
        ponerPlaceholder(txtCorreo, Correo_electronico);
        ponerPlaceholder(txtContraseña, Contrasenia);
        ponerPlaceholder(txtConfirmarContraseña, Confirmar_contrasenia);
    }

    //Método para el PlaceHolder.
    private void ponerPlaceholder(javax.swing.JTextField campo, String textoPorDefecto) {
        campo.setText(textoPorDefecto);
        campo.setForeground(java.awt.Color.GRAY);

        campo.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent e) {
                if (campo.getText().equals(textoPorDefecto)) {
                    campo.setText("");
                    campo.setForeground(new Color(160, 160, 160));
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
                    campo.setForeground(new Color(160, 160, 160));
                    campo.setEchoChar('•');
                }
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent e) {
                String passText = new String(campo.getPassword());
                if (passText.isEmpty()) {
                    campo.setEchoChar((char) 0); // Mostrar texto plano
                    campo.setForeground(java.awt.Color.GRAY);
                    campo.setText(textoPorDefecto);
                }
            }
        });
        
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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new Design.RoundedPanel();
        jLabel1 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        txtNombredeusuario = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        txtCorreo = new javax.swing.JTextField();
        txtContraseña = new javax.swing.JTextField();
        txtConfirmarContraseña = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jButton1 =  new RoundedButton("Entrar");
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(458, 590));
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Arial", 1, 30)); // NOI18N
        jLabel1.setText("Registro");
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel3.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 10, 199, 49));

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/U.png"))); // NOI18N
        jButton5.setBorderPainted(false);
        jButton5.setContentAreaFilled(false);
        jButton5.setFocusPainted(false);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(68, 123, 30, 30));

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
        jPanel3.add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(68, 282, 30, 30));

        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/pr password (1).png"))); // NOI18N
        jButton8.setBorderPainted(false);
        jButton8.setContentAreaFilled(false);
        jButton8.setFocusPainted(false);
        jButton8.setFocusable(false);
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 362, 30, 30));

        txtNombredeusuario.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtNombredeusuario.setForeground(new java.awt.Color(0, 0, 0));
        txtNombredeusuario.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtNombredeusuario.setText("Nombre de Usuario:");
        txtNombredeusuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombredeusuarioActionPerformed(evt);
            }
        });
        jPanel3.add(txtNombredeusuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, 313, 53));

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/Correo ícon (1).png"))); // NOI18N
        jButton2.setBorderPainted(false);
        jButton2.setContentAreaFilled(false);
        jButton2.setFocusPainted(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 200, 30, 30));

        txtCorreo.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtCorreo.setForeground(new java.awt.Color(0, 0, 0));
        txtCorreo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCorreo.setText("Correo electrónico:");
        txtCorreo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCorreoActionPerformed(evt);
            }
        });
        jPanel3.add(txtCorreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 190, 313, 54));

        txtContraseña.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtContraseña.setForeground(new java.awt.Color(0, 0, 0));
        txtContraseña.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtContraseña.setText("Contraseña:");
        txtContraseña.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtContraseñaActionPerformed(evt);
            }
        });
        jPanel3.add(txtContraseña, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 270, 313, 54));

        txtConfirmarContraseña.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtConfirmarContraseña.setForeground(new java.awt.Color(0, 0, 0));
        txtConfirmarContraseña.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtConfirmarContraseña.setText("Confirmar Contraseña:");
        txtConfirmarContraseña.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtConfirmarContraseñaActionPerformed(evt);
            }
        });
        jPanel3.add(txtConfirmarContraseña, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 350, 313, 54));

        jLabel3.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel3.setText("¿Ya tienes una cuenta?");
        jPanel3.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 75, -1, -1));

        jLabel4.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 51, 255));
        jLabel4.setText("Iniciar Sesión");
        jLabel4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel3.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 73, -1, 22));

        jButton1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jButton1.setText("Crear Cuenta");
        jButton1.setBorderPainted(false);
        jButton1.setContentAreaFilled(false);
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.setFocusPainted(false);
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 440, 180, 38));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 50, 370, 510));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/icons8_Expand_Arrow_32px.png"))); // NOI18N
        jLabel7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel7MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 10, 30, 30));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/Salir (2) (2).png"))); // NOI18N
        jLabel8.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel8MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 6, 30, 40));

        jLabel2.setForeground(new java.awt.Color(11, 187, 187));
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/Fondo Login.png"))); // NOI18N
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 500, 590));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton8ActionPerformed

    private void txtNombredeusuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombredeusuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombredeusuarioActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void txtCorreoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCorreoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCorreoActionPerformed

    private void txtContraseñaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtContraseñaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtContraseñaActionPerformed

    private void txtConfirmarContraseñaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtConfirmarContraseñaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtConfirmarContraseñaActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Mensaje_ExitoSuperUsuario miLogin = new Mensaje_ExitoSuperUsuario();
        miLogin.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jLabel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseClicked
        this.setState(Registro_SuperUsuario.ICONIFIED);
    }//GEN-LAST:event_jLabel7MouseClicked

    private void jLabel8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MouseClicked
        int dialog = JOptionPane.YES_NO_OPTION;
        int result = JOptionPane.showConfirmDialog(null, "¿Desea salir?", "Exit", dialog);
        if (result == 0) {
            System.exit(0);
        }
    }//GEN-LAST:event_jLabel8MouseClicked

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
JOptionPane.showMessageDialog(null, "Registro exitoso");

    }//GEN-LAST:event_jButton1MouseClicked

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
            java.util.logging.Logger.getLogger(Registro_SuperUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Registro_SuperUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Registro_SuperUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Registro_SuperUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Registro_SuperUsuario().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JTextField txtConfirmarContraseña;
    private javax.swing.JTextField txtContraseña;
    private javax.swing.JTextField txtCorreo;
    private javax.swing.JTextField txtNombredeusuario;
    // End of variables declaration//GEN-END:variables
}
