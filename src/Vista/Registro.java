/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import Design.ComboBox.Combobox;
import Design.RoundedPanel;
import Design.RoundedTextField1;
import Design.RoundedTextField;
import Design.RoundedBorderRegistro;
import javax.swing.border.Border;
import Animations.Animator;
import ConexionHuellasCuencanas.ConexionHuellasCuencanas;
import Controlador.ControladorLogin;
import Controlador.ControladorRegistro;
import Design.RoundedPanel;
import Design.RoundedBorder;
import Design.RounderButton2;
import Design.RoundedButton;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import raven.glasspanepopup.GlassPanePopup;
import Design.ComboBox.Combobox;
import Design.RoundedPasswordField;
import javax.swing.JButton;
import javax.swing.JPasswordField;

/**
 *
 * @author USER
 */
public class Registro extends javax.swing.JFrame {

    private ControladorRegistro controlador;

    private boolean recuperacionAbierta = true;

    //Variables para el placeholder:
    private String Nombre_de_Usuario1 = "Nombre de usuario:";
    private String Correo_electronico1 = "Correo electronico:";
    private String Contrasenia1 = "Contraseña:";
    private String Confirmar_contrasenia1 = "Confirmar contraseña:";
    private String Correo1 = "Escriba su correo:";
    private String Cedula = "Escriba su cédula:";
    private boolean passwordVisible = false;
    private boolean confirmarVisible = false;

    private int x;
    private int y;

    /**
     * Creates new form Registro
     */
    public Registro() {

        setUndecorated(true);

        setOpacity(0f);
        initComponents();
        GlassPanePopup.install(this);

        //Método para llamar al Hypervínculo:
        initMoving();

        //Llamar a los métodos de Provincia y Género:
        llenarProvincias();
        llenarGeneros();

        //Darle fuentes al hypervínculo:
        IniciarSesión.setForeground(new Color(52, 152, 219));
        Click.setForeground(new Color(52, 152, 219));

        //Fuentes
        Huellas.setFont(new Font("Open Sans Bold", Font.PLAIN, 13));
        Cuencanas.setFont(new Font("Open Sans", Font.PLAIN, 13));
        Catedral.setFont(new Font("CocogooseProTrial", Font.PLAIN, 35));
        txtContraseña.setFont(new Font("Caviar Dreams", Font.PLAIN, 15));
        txtConfirmarContraseña.setFont(new Font("Caviar Dreams", Font.PLAIN, 15));
        Yatienes.setFont(new Font("Caviar Dreams", Font.PLAIN, 15));
        IniciarSesión.setFont(new Font("Caviar Dreams", Font.PLAIN, 15));
        btn_Guardar.setFont(new Font("Caviar Dreams Bold", Font.PLAIN, 15));
        txtNombredeusuario2.setFont(new Font("Caviar Dreams", Font.PLAIN, 15));
        txtCorreo1.setFont(new Font("Caviar Dreams", Font.PLAIN, 15));
        jComboBox2.setFont(new Font("Caviar Dreams", Font.PLAIN, 15));
        jComboBox3.setFont(new Font("Caviar Dreams", Font.PLAIN, 15));
        jComboBox4.setFont(new Font("Caviar Dreams", Font.PLAIN, 15));
        Dueño.setFont(new Font("Caviar Dreams", Font.PLAIN, 15));
        Click.setFont(new Font("Caviar Dreams", Font.PLAIN, 15));
        txtCedula.setFont(new Font("Caviar Dreams", Font.PLAIN, 15));

        //Hacer Redondeada la Ventana:
        setSize(970, 620);
        setShape(new java.awt.geom.RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 60, 60));
        setLocationRelativeTo(null);

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

        // Hypervínculo Iniciar Sesión:
        MouseAdapter ma = new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent evt) {
                if (!recuperacionAbierta
                        && SwingUtilities.isLeftMouseButton(evt)
                        && evt.getClickCount() == 1) {

                    recuperacionAbierta = true;
                    IniciarSesión.removeMouseListener(this);

                    Animator.fadeOut((JFrame) SwingUtilities.getWindowAncestor(IniciarSesión), () -> {
                        ((JFrame) SwingUtilities.getWindowAncestor(IniciarSesión)).dispose();

                        Login ventana = new Login();
                        ventana.setLocationRelativeTo(null);
                        Animator.fadeIn(ventana);
                    });
                }
            }

            @Override
            public void mouseEntered(MouseEvent evt) {
                IniciarSesión.setForeground(new java.awt.Color(41, 128, 185));
            }

            @Override
            public void mouseExited(MouseEvent evt) {
                IniciarSesión.setForeground(new java.awt.Color(52, 152, 219));
            }
        };

        IniciarSesión.addMouseListener(ma);

        Click.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e) && e.getClickCount() == 1) {
                    Animator.fadeOut((JFrame) SwingUtilities.getWindowAncestor(Click), () -> {
                        ((JFrame) SwingUtilities.getWindowAncestor(Click)).dispose();

                        Ventana_Registro_SuperUsuario ventana = new Ventana_Registro_SuperUsuario();
                        ventana.setLocationRelativeTo(null);
                        Animator.fadeIn(ventana);
                    });
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                Click.setForeground(new Color(41, 128, 185));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                Click.setForeground(new Color(52, 152, 219));
            }
        });

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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jPanel2 = new RoundedPanel();
        Catedral = new javax.swing.JLabel();
        txtNombredeusuario2 = new RoundedTextField1(20);
        txtCorreo1 = new RoundedTextField1(20);
        jComboBox2 = new Combobox<>(new String[]{
        })
        ;
        jButton1 = new javax.swing.JButton();
        Yatienes = new javax.swing.JLabel();
        IniciarSesión = new javax.swing.JLabel();
        jComboBox3 = new Combobox<>(new String[]{
        })
        ;
        btn_Guardar =  new RoundedButton("");
        Dueño = new javax.swing.JLabel();
        Click = new javax.swing.JLabel();
        jComboBox4 = new Combobox<>(new String[] {});
        ;
        txtCedula = new RoundedTextField1(20);
        jButton3 = new javax.swing.JButton();
        txtContraseña = new RoundedPasswordField(20);
        jButton4 = new javax.swing.JButton();
        txtConfirmarContraseña = new RoundedPasswordField(20);
        jButton2 = new javax.swing.JButton();
        Huellas = new javax.swing.JLabel();
        Cuencanas = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setPreferredSize(new java.awt.Dimension(100, 600));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Catedral.setFont(new java.awt.Font("Dialog", 1, 26)); // NOI18N
        Catedral.setForeground(new java.awt.Color(0, 0, 0));
        Catedral.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Catedral.setText("Crear Cuenta");
        jPanel2.add(Catedral, new org.netbeans.lib.awtextra.AbsoluteConstraints(226, 30, -1, -1));

        txtNombredeusuario2.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        txtNombredeusuario2.setForeground(new java.awt.Color(0, 0, 0));
        txtNombredeusuario2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtNombredeusuario2.setText("Nombre de Usuario:");
        txtNombredeusuario2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombredeusuario2ActionPerformed(evt);
            }
        });
        jPanel2.add(txtNombredeusuario2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 120, 250, 44));

        txtCorreo1.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        txtCorreo1.setForeground(new java.awt.Color(0, 0, 0));
        txtCorreo1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCorreo1.setText("Correo");
        txtCorreo1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCorreo1ActionPerformed(evt);
            }
        });
        jPanel2.add(txtCorreo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 260, 250, 44));

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {}));
        jComboBox2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });
        jPanel2.add(jComboBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 260, 250, 44));

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
        jPanel2.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 32, -1, -1));

        Yatienes.setText("¿Ya tienes una cuenta?");
        jPanel2.add(Yatienes, new org.netbeans.lib.awtextra.AbsoluteConstraints(228, 78, -1, -1));

        IniciarSesión.setText("Iniciar Sesión");
        IniciarSesión.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel2.add(IniciarSesión, new org.netbeans.lib.awtextra.AbsoluteConstraints(402, 78, -1, -1));

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {}));
        jComboBox3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox3ActionPerformed(evt);
            }
        });
        jPanel2.add(jComboBox3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 330, 250, 44));

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
        jPanel2.add(btn_Guardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 440, 190, -1));

        Dueño.setText("¿Eres dueño de un Negocio?");
        jPanel2.add(Dueño, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 400, -1, -1));

        Click.setText("Clik Aquí.");
        Click.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel2.add(Click, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 400, -1, -1));

        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {}));
        jComboBox4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jComboBox4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox4ActionPerformed(evt);
            }
        });
        jPanel2.add(jComboBox4, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 330, 250, 44));

        txtCedula.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCedula.setText("Cédula:");
        jPanel2.add(txtCedula, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 120, 250, 44));

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/hidden (1).png"))); // NOI18N
        jButton3.setBorderPainted(false);
        jButton3.setContentAreaFilled(false);
        jButton3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton3.setFocusPainted(false);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(254, 204, 40, 20));

        txtContraseña.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel2.add(txtContraseña, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 190, 250, 44));

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/hidden (1).png"))); // NOI18N
        jButton4.setBorderPainted(false);
        jButton4.setContentAreaFilled(false);
        jButton4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton4.setFocusPainted(false);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(618, 204, 40, 20));

        txtConfirmarContraseña.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel2.add(txtConfirmarContraseña, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 190, 250, 44));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 80, 710, 500));

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

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/Atrás 2.png"))); // NOI18N
        jButton5.setBorderPainted(false);
        jButton5.setContentAreaFilled(false);
        jButton5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton5.setFocusPainted(false);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 570, -1, 30));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/Imagen Registro.png"))); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 980, 620));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 970, 620));

        pack();
    }// </editor-fold>//GEN-END:initComponents

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

    private void txtNombredeusuario2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombredeusuario2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombredeusuario2ActionPerformed

    private void txtCorreo1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCorreo1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCorreo1ActionPerformed

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        String provincia = (String) jComboBox2.getSelectedItem();
        if (provincia != null && !provincia.equals("Seleccione provincia")) {
            llenarCantones(provincia);
        }


    }//GEN-LAST:event_jComboBox2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        AyudaMensaje ayudaPanel = new AyudaMensaje();
        GlassPanePopup.showPopup(ayudaPanel);

           }//GEN-LAST:event_jButton1ActionPerformed

    private void jComboBox3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox3ActionPerformed

    private void jComboBox4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox4ActionPerformed

    private void btn_GuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_GuardarActionPerformed
        controlador.registrarPersona(true);
    }//GEN-LAST:event_btn_GuardarActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed

        if (passwordVisible) {
            txtContraseña.setEchoChar('•');
            jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/hidden (1).png")));
            passwordVisible = false;
        } else {
            txtContraseña.setEchoChar((char) 0);
            jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/watch (1).png")));
            passwordVisible = true;
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed

        if (confirmarVisible) {
            txtConfirmarContraseña.setEchoChar('•');
            jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/hidden (1).png")));
            confirmarVisible = false;
        } else {
            txtConfirmarContraseña.setEchoChar((char) 0);
            jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/watch (1).png")));
            confirmarVisible = true;
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        Animator.fadeOut(this, () -> {
            Login miR = new Login();
            ControladorLogin controladorLogin = new ControladorLogin(miR);
            Animator.fadeIn(miR);
        });


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
            java.util.logging.Logger.getLogger(Registro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Registro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Registro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Registro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Registro().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Catedral;
    private javax.swing.JLabel Click;
    private javax.swing.JLabel Cuencanas;
    private javax.swing.JLabel Dueño;
    private javax.swing.JLabel Huellas;
    private javax.swing.JLabel IniciarSesión;
    private javax.swing.JLabel Yatienes;
    private javax.swing.JButton btn_Guardar;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
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
