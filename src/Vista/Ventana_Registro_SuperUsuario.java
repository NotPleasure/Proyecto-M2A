/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Vista;

import ConexionHuellasCuencanas.ConexionHuellasCuencanas;
import Controlador.ControladorRegistro;
import Design.ComboBox.Combobox;
import Design.RoundedButton;
import Design.RoundedPanel;
import Design.RoundedPasswordField;
import Design.RoundedTextField1;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import raven.glasspanepopup.GlassPanePopup;

/**
 *
 * @author USER
 */
public class Ventana_Registro_SuperUsuario extends javax.swing.JFrame {
    //Variables para el placeholder:

    private String Nombre_de_Usuario1 = "Nombre de usuario:";
    private String Correo_electronico1 = "Correo electronico:";
    private String Contrasenia1 = "Contraseña:";
    private String Confirmar_contrasenia1 = "Confirmar contraseña:";
    private String Correo1 = "Escriba su correo:";
    private String Cedula = "Escriba su cédula:";
    private boolean passwordVisible = false;
    private boolean confirmarVisible = false;

    private ControladorRegistro controlador;

    /**
     * Creates new form Ventana_Registro_SuperUsuario
     */
    public Ventana_Registro_SuperUsuario() {
        initComponents();

        //Llenar géneros y provincias:
        llenarProvincias();
        llenarGeneros();

        //Fuentes
        Huellas.setFont(new Font("Open Sans Bold", Font.PLAIN, 13));
        Cuencanas.setFont(new Font("Open Sans", Font.PLAIN, 13));
        Catedral.setFont(new Font("CocogooseProTrial", Font.PLAIN, 35));
        Yatienes.setFont(new Font("Caviar Dreams", Font.PLAIN, 15));
        IniciarSesión.setFont(new Font("Caviar Dreams", Font.PLAIN, 15));
        txtContraseña.setFont(new Font("Caviar Dreams", Font.PLAIN, 15));
        txtConfirmarContraseña.setFont(new Font("Caviar Dreams", Font.PLAIN, 15));
        btn_Guardar.setFont(new Font("Caviar Dreams Bold", Font.PLAIN, 15));
        txtNombredeusuario2.setFont(new Font("Caviar Dreams", Font.PLAIN, 15));
        txtCorreo1.setFont(new Font("Caviar Dreams", Font.PLAIN, 15));
        jComboBox2.setFont(new Font("Caviar Dreams", Font.PLAIN, 15));
        jComboBox3.setFont(new Font("Caviar Dreams", Font.PLAIN, 15));
        jComboBox4.setFont(new Font("Caviar Dreams", Font.PLAIN, 15));
        txtCedula.setFont(new Font("Caviar Dreams", Font.PLAIN, 15));

        //Hacer redonda la ventana:
        setSize(960, 660);
        setShape(new java.awt.geom.RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 60, 60));
        setLocationRelativeTo(null);

        //Método Placeholder:
        ponerPlaceholder(txtNombredeusuario2, Nombre_de_Usuario1);
        ponerPlaceholder(txtCorreo1, Correo1);
        ponerPlaceholder(txtCedula, Cedula);
        ponerPlaceholderPassword(txtContraseña, "Contraseña");
        ponerPlaceholderPassword(txtConfirmarContraseña, "Confirmar Contraseña");

    }

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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        Huellas = new javax.swing.JLabel();
        Cuencanas = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jPanel2 = new RoundedPanel();
        Catedral = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        Yatienes = new javax.swing.JLabel();
        IniciarSesión = new javax.swing.JLabel();
        txtNombredeusuario2 = new RoundedTextField1(20);
        txtContraseña = new RoundedPasswordField(20);
        txtCorreo1 = new RoundedTextField1(20);
        jComboBox3 = new Combobox<>(new String[]{
        })
        ;
        txtCedula = new RoundedTextField1(20);
        txtConfirmarContraseña = new RoundedPasswordField(20);
        jComboBox2 = new Combobox<>(new String[]{
        })
        ;
        jComboBox4 = new Combobox<>(new String[] {});
        ;
        btn_Guardar =  new RoundedButton("");
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setPreferredSize(new java.awt.Dimension(970, 620));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/Copilot_20250610_114322 (3).png"))); // NOI18N
        jButton2.setBorderPainted(false);
        jButton2.setContentAreaFilled(false);
        jButton2.setFocusPainted(false);
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 12, 60, 70));

        Huellas.setForeground(new java.awt.Color(255, 255, 255));
        Huellas.setText("Huellas ");
        jPanel1.add(Huellas, new org.netbeans.lib.awtextra.AbsoluteConstraints(86, 30, -1, -1));

        Cuencanas.setForeground(new java.awt.Color(255, 255, 255));
        Cuencanas.setText("Cuencanas");
        jPanel1.add(Cuencanas, new org.netbeans.lib.awtextra.AbsoluteConstraints(92, 47, -1, -1));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/icons8_Expand_Arrow_32px.png"))); // NOI18N
        jLabel7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel7MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 20, 30, 30));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/Salir (2) (2).png"))); // NOI18N
        jLabel8.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel8MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 16, 30, 40));

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Catedral.setFont(new java.awt.Font("Dialog", 1, 26)); // NOI18N
        Catedral.setForeground(new java.awt.Color(0, 0, 0));
        Catedral.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Catedral.setText("Crear Cuenta");
        jPanel2.add(Catedral, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 30, -1, -1));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/question (2).png"))); // NOI18N
        jButton1.setBorderPainted(false);
        jButton1.setContentAreaFilled(false);
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.setFocusPainted(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 34, -1, -1));

        Yatienes.setText("¿Ya tienes una cuenta?");
        jPanel2.add(Yatienes, new org.netbeans.lib.awtextra.AbsoluteConstraints(282, 80, -1, -1));

        IniciarSesión.setText("Iniciar Sesión");
        IniciarSesión.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel2.add(IniciarSesión, new org.netbeans.lib.awtextra.AbsoluteConstraints(466, 80, -1, -1));

        txtNombredeusuario2.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        txtNombredeusuario2.setForeground(new java.awt.Color(0, 0, 0));
        txtNombredeusuario2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtNombredeusuario2.setText("Nombre de Usuario:");
        txtNombredeusuario2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombredeusuario2ActionPerformed(evt);
            }
        });
        jPanel2.add(txtNombredeusuario2, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 130, 250, 44));

        txtContraseña.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel2.add(txtContraseña, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 200, 250, 44));

        txtCorreo1.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        txtCorreo1.setForeground(new java.awt.Color(0, 0, 0));
        txtCorreo1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCorreo1.setText("Correo");
        txtCorreo1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCorreo1ActionPerformed(evt);
            }
        });
        jPanel2.add(txtCorreo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 270, 250, 44));

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {}));
        jComboBox3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox3ActionPerformed(evt);
            }
        });
        jPanel2.add(jComboBox3, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 340, 250, 44));

        txtCedula.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCedula.setText("Cédula:");
        jPanel2.add(txtCedula, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 130, 250, 44));

        txtConfirmarContraseña.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel2.add(txtConfirmarContraseña, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 200, 250, 44));

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {}));
        jComboBox2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });
        jPanel2.add(jComboBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 270, 250, 44));

        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {}));
        jComboBox4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jComboBox4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox4ActionPerformed(evt);
            }
        });
        jPanel2.add(jComboBox4, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 340, 250, 44));

        btn_Guardar.setForeground(new java.awt.Color(255, 255, 255));
        btn_Guardar.setText("Crear Cuenta");
        btn_Guardar.setBorderPainted(false);
        btn_Guardar.setContentAreaFilled(false);
        btn_Guardar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_Guardar.setFocusPainted(false);
        btn_Guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_GuardarActionPerformed(evt);
            }
        });
        jPanel2.add(btn_Guardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 480, 190, 30));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 90, 820, 530));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/Imagen Registro.png"))); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 960, 660));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 960, 660));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
//Método para llenar el ComboBox de Provincias:

    private void llenarProvincias() {
        String sql = "SELECT nombre FROM provincias ORDER BY nombre";

        try ( Connection con = ConexionHuellasCuencanas.conectar();  PreparedStatement ps = con.prepareStatement(sql);  ResultSet rs = ps.executeQuery()) {

            jComboBox2.removeAllItems();

            jComboBox2.addItem("Seleccione provincia");

            while (rs.next()) {
                jComboBox2.addItem(rs.getString("nombre"));
            }

            jComboBox2.setSelectedIndex(0);

            jComboBox2.setRenderer(new DefaultListCellRenderer() {
                @Override
                public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                        boolean isSelected, boolean cellHasFocus) {
                    super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

                    if (index == -1 && "Seleccione provincia".equals(value)) {
                        setForeground(Color.GRAY);
                    } else {
                        setForeground(Color.BLACK);
                    }
                    return this;
                }
            });

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al cargar provincias: " + e.getMessage());
        }
    }

    //Método para llenar los cantones por provincia:
    private void llenarCantones(String nombreProvincia) {
        String sql = "SELECT c.nombre FROM cantones c JOIN provincias p ON c.provincia_id = p.id WHERE p.nombre = ? ORDER BY c.nombre";

        try ( Connection con = ConexionHuellasCuencanas.conectar();  PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, nombreProvincia);
            ResultSet rs = ps.executeQuery();

            jComboBox4.removeAllItems();
            jComboBox4.addItem("Seleccione cantón");

            while (rs.next()) {
                jComboBox4.addItem(rs.getString("nombre"));
            }

            jComboBox4.setSelectedIndex(0);

            // Aquí el renderer como antes
            jComboBox4.setRenderer(new DefaultListCellRenderer() {
                @Override
                public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                        boolean isSelected, boolean cellHasFocus) {
                    super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                    if (index == -1 && "Seleccione cantón".equals(value)) {
                        setForeground(Color.GRAY);
                    } else {
                        setForeground(Color.BLACK);
                    }
                    return this;
                }
            });

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al cargar cantones: " + e.getMessage());
        }
    }

    //Método para llenar el ComboBox de Género:
    private void llenarGeneros() {
        String sql = "SELECT nombre FROM generos ORDER BY nombre";

        try ( Connection con = ConexionHuellasCuencanas.conectar();  PreparedStatement ps = con.prepareStatement(sql);  ResultSet rs = ps.executeQuery()) {

            jComboBox3.removeAllItems();

            // Placeholder
            jComboBox3.addItem("Seleccione género");

            while (rs.next()) {
                jComboBox3.addItem(rs.getString("nombre"));
            }

            jComboBox3.setSelectedIndex(0);

            jComboBox3.setRenderer(new DefaultListCellRenderer() {
                @Override
                public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                        boolean isSelected, boolean cellHasFocus) {
                    super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                    if (index == -1 && "Seleccione género".equals(value)) {
                        setForeground(Color.GRAY);
                    } else {
                        setForeground(Color.BLACK);
                    }
                    return this;
                }
            });

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al cargar géneros: " + e.getMessage());
        }
    }

    //Getters y Setters para los botones:
    public JButton getBtn_Guardar() {
        return btn_Guardar;
    }

    public void setBtn_Guardar(JButton btn_Guardar) {
        this.btn_Guardar = btn_Guardar;
    }

    public JComboBox<String> getjComboBox2() {
        return jComboBox2;
    }

    public void setjComboBox2(JComboBox<String> jComboBox2) {
        this.jComboBox2 = jComboBox2;
    }

    public JComboBox<String> getjComboBox3() {
        return jComboBox3;
    }

    public void setjComboBox3(JComboBox<String> jComboBox3) {
        this.jComboBox3 = jComboBox3;
    }

    public JComboBox<String> getjComboBox4() {
        return jComboBox4;
    }

    public void setjComboBox4(JComboBox<String> jComboBox4) {
        this.jComboBox4 = jComboBox4;
    }

    public JTextField getTxtCedula() {
        return txtCedula;
    }

    public void setTxtCedula(JTextField txtCedula) {
        this.txtCedula = txtCedula;
    }

    public JPasswordField getTxtConfirmarContraseña() {
        return txtConfirmarContraseña;
    }

    public void setTxtConfirmarContraseña(JPasswordField txtConfirmarContraseña) {
        this.txtConfirmarContraseña = txtConfirmarContraseña;
    }

    public JPasswordField getTxtContraseña() {
        return txtContraseña;
    }

    public JTextField getTxtCorreo1() {
        return txtCorreo1;
    }

    public void setTxtCorreo1(JTextField txtCorreo1) {
        this.txtCorreo1 = txtCorreo1;
    }

    public JTextField getTxtNombredeusuario2() {
        return txtNombredeusuario2;
    }

    public void setTxtNombredeusuario2(JTextField txtNombredeusuario2) {
        this.txtNombredeusuario2 = txtNombredeusuario2;
    }

    public void limpiarFormulario() {
        txtCedula.setText("");
        txtNombredeusuario2.setText("");
        txtCorreo1.setText("");
        txtContraseña.setText("");
        txtConfirmarContraseña.setText("");
        jComboBox2.setSelectedIndex(0);
        jComboBox4.setSelectedIndex(0);
        jComboBox3.setSelectedIndex(0);
    }

    public void setControlador(ControladorRegistro controlador) {
        this.controlador = controlador;
    }


    private void jLabel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseClicked
        this.setState(Registro.ICONIFIED);
    }//GEN-LAST:event_jLabel7MouseClicked

    private void jLabel8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MouseClicked
        int dialog = JOptionPane.YES_NO_OPTION;
        int result = JOptionPane.showConfirmDialog(null, "¿Desea salir?", "Exit", dialog);
        if (result == 0) {
            System.exit(0);
        }
    }//GEN-LAST:event_jLabel8MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        AyudaMensaje ayudaPanel = new AyudaMensaje();
        GlassPanePopup.showPopup(ayudaPanel);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtNombredeusuario2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombredeusuario2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombredeusuario2ActionPerformed

    private void txtCorreo1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCorreo1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCorreo1ActionPerformed

    private void jComboBox3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox3ActionPerformed

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        String provincia = (String) jComboBox2.getSelectedItem();
        if (provincia != null && !provincia.equals("Seleccione provincia")) {
            llenarCantones(provincia);
        }

    }//GEN-LAST:event_jComboBox2ActionPerformed

    private void jComboBox4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox4ActionPerformed

    private void btn_GuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_GuardarActionPerformed
        controlador.registrarPersona(true);
    }//GEN-LAST:event_btn_GuardarActionPerformed

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
            java.util.logging.Logger.getLogger(Ventana_Registro_SuperUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Ventana_Registro_SuperUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Ventana_Registro_SuperUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Ventana_Registro_SuperUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Ventana_Registro_SuperUsuario().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Catedral;
    private javax.swing.JLabel Cuencanas;
    private javax.swing.JLabel Huellas;
    private javax.swing.JLabel IniciarSesión;
    private javax.swing.JLabel Yatienes;
    private javax.swing.JButton btn_Guardar;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JComboBox<String> jComboBox4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField txtCedula;
    private javax.swing.JPasswordField txtConfirmarContraseña;
    private javax.swing.JPasswordField txtContraseña;
    private javax.swing.JTextField txtCorreo1;
    private javax.swing.JTextField txtNombredeusuario2;
    // End of variables declaration//GEN-END:variables
}
