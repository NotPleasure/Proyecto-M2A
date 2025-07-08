/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Vista;

import Animations.Animator;
import ConexionHuellasCuencanas.ConexionHuellasCuencanas;
import Controlador.ControladorLogin;
import Controlador.ControladorRegistro;
import Controlador.ControladorSuperUsuario;
import Design.ComboBox.Combobox;
import Design.RoundedButton;
import Design.RoundedPanel;
import Design.RoundedPasswordField;
import Design.RoundedTextArea;
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
import javax.swing.JTextArea;
import javax.swing.JTextField;
import raven.glasspanepopup.GlassPanePopup;
import java.awt.event.FocusEvent;
import java.awt.event.FocusAdapter;

/**
 *
 * @author USER
 */
public class Ventana_Registro_SuperUsuario extends javax.swing.JFrame {

    //Variables para el placeholder:
    private String descripcionPlaceholder = "Escriba una descripción del negocio...";
    private String Nombre_de_Usuario1 = "Nombre de usuario:";
    private String Correo_electronico1 = "Correo electronico:";
    private String Contrasenia1 = "Contraseña:";
    private String Confirmar_contrasenia1 = "Confirmar contraseña:";
    private String Correo1 = "Escriba su correo:";
    private String Cedula = "Escriba su cédula:";
    private String NombreNegocio = "Escriba el nombre de su negocio";
    private String Direccion = "Escriba la direccion de su negocio";

    private boolean passwordVisible = false;
    private boolean confirmarVisible = false;
    private boolean ventanaCargada = false;

    private ControladorSuperUsuario controladorSuperUsuario;

    /**
     * Creates new form Ventana_Registro_SuperUsuario
     */
    public Ventana_Registro_SuperUsuario() {

        initComponents();

        controladorSuperUsuario = new ControladorSuperUsuario(this);
        
        //Llenar géneros, nacionalidades,cargos:
        cargarCargos();
        cargarGeneros();
        cargarNacionalidades();

        //Instanciar la cédula como "No editable":
        txtCedula.setEnabled(false);

        //Fuentes
        Huellas.setFont(new Font("Open Sans Bold", Font.PLAIN, 13));
        Cuencanas.setFont(new Font("Open Sans", Font.PLAIN, 13));
        Catedral.setFont(new Font("CocogooseProTrial", Font.PLAIN, 35));
        Yatienes.setFont(new Font("Caviar Dreams", Font.PLAIN, 15));
        IniciarSesión.setFont(new Font("Caviar Dreams", Font.PLAIN, 15));
        txtNombredeNegocio.setFont(new Font("Caviar Dreams", Font.PLAIN, 15));
        txtDireccion.setFont(new Font("Caviar Dreams", Font.PLAIN, 15));
        btn_Guardar.setFont(new Font("Caviar Dreams Bold", Font.PLAIN, 15));
        txtNombredeusuario2.setFont(new Font("Caviar Dreams", Font.PLAIN, 15));
        txtCorreo1.setFont(new Font("Caviar Dreams", Font.PLAIN, 15));
        jComboBox2.setFont(new Font("Caviar Dreams", Font.PLAIN, 15));
        jComboBox3.setFont(new Font("Caviar Dreams", Font.PLAIN, 15));
        txtCedula.setFont(new Font("Caviar Dreams", Font.PLAIN, 15));
        Cargo.setFont(new Font("Caviar Dreams", Font.PLAIN, 15));
        Descripcion.setFont(new Font("Caviar Dreams", Font.PLAIN, 15));

        //Hacer redonda la ventana:
        setSize(960, 660);
        setShape(new java.awt.geom.RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 60, 60));
        setLocationRelativeTo(null);

        //Método Placeholder:
        ponerPlaceholder(txtNombredeusuario2, Nombre_de_Usuario1);
        ponerPlaceholder(txtCorreo1, Correo1);
        ponerPlaceholder(txtCedula, Cedula);
        ponerPlaceholder(txtDireccion, NombreNegocio);
        ponerPlaceholder(txtNombredeNegocio, Direccion);
        Descripcion.setText(descripcionPlaceholder);
        Descripcion.setForeground(Color.GRAY);
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

    public void ponerPlaceholderTextArea(JTextArea area, String textoPorDefecto) {
        area.setText(textoPorDefecto);
        area.setForeground(Color.GRAY);

        area.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                System.out.println("Focus gained!");
                if (area.getText().equals(textoPorDefecto)) {
                    area.setText("");
                    area.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                System.out.println("Focus lost!");
                if (area.getText().trim().isEmpty()) {
                    area.setText(textoPorDefecto);
                    area.setForeground(Color.GRAY);
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
        txtCorreo1 = new RoundedTextField1(20);
        jComboBox3 = new Combobox<>(new String[]{
        })
        ;
        txtCedula = new RoundedTextField1(20);
        jComboBox2 = new Combobox<>(new String[]{
        })
        ;
        btn_Guardar =  new RoundedButton("");
        Cargo = new Combobox<>(new String[]{"Administrador del negocio",
            "Gerente",
            "Encargado",
            "Supervisor",
            "Jefe de operaciones"
        });
        txtNombredeNegocio = new RoundedTextField1(20);
        txtDireccion = new RoundedTextField1(20);
        jScrollPane1 = new javax.swing.JScrollPane();
        Descripcion = new RoundedTextArea(5, 30);
        jButton5 = new javax.swing.JButton();
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

        txtCorreo1.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        txtCorreo1.setForeground(new java.awt.Color(0, 0, 0));
        txtCorreo1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCorreo1.setText("Correo");
        txtCorreo1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCorreo1ActionPerformed(evt);
            }
        });
        jPanel2.add(txtCorreo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 190, 250, 44));

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {}));
        jComboBox3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox3ActionPerformed(evt);
            }
        });
        jPanel2.add(jComboBox3, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 310, 250, 44));

        txtCedula.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCedula.setText("Cédula:");
        jPanel2.add(txtCedula, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 190, 250, 44));

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {}));
        jComboBox2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });
        jPanel2.add(jComboBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 130, 250, 44));

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

        Cargo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Administrador del negocio",
            "Gerente",
            "Encargado",
            "Supervisor",
            "Jefe de operaciones" }));
Cargo.addActionListener(new java.awt.event.ActionListener() {
    public void actionPerformed(java.awt.event.ActionEvent evt) {
        CargoActionPerformed(evt);
    }
    });
    jPanel2.add(Cargo, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 310, 250, 44));

    txtNombredeNegocio.setHorizontalAlignment(javax.swing.JTextField.CENTER);
    jPanel2.add(txtNombredeNegocio, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 250, 250, 44));
    jPanel2.add(txtDireccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 250, 250, 44));

    Descripcion.setColumns(20);
    Descripcion.setRows(5);
    jScrollPane1.setViewportView(Descripcion);

    jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(224, 380, 360, 80));

    jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 90, 820, 530));

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
    jPanel1.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 610, -1, 30));

    jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/Imagen Registro.png"))); // NOI18N
    jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 960, 660));

    getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 960, 660));

    pack();
    setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

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

    public JTextField getTxtCedula() {
        return txtCedula;
    }

    public void setTxtCedula(JTextField txtCedula) {
        this.txtCedula = txtCedula;
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

    public JComboBox<String> getCargo() {
        return Cargo;
    }

    public void setCargo(JComboBox<String> Cargo) {
        this.Cargo = Cargo;
    }

    public JTextField getTxtDireccion() {
        return txtDireccion;
    }

    public void setTxtDireccion(JTextField txtDireccion) {
        this.txtDireccion = txtDireccion;
    }

    public JTextField getTxtNombredeNegocio() {
        return txtNombredeNegocio;
    }

    public void setTxtNombredeNegocio(JTextField txtNombredeNegocio) {
        this.txtNombredeNegocio = txtNombredeNegocio;
    }

    public JTextArea getjTextArea1() {
        return Descripcion;
    }

    public void setjTextArea1(JTextArea jTextArea1) {
        this.Descripcion = jTextArea1;
    }

    public JTextArea getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(JTextArea Descripcion) {
        this.Descripcion = Descripcion;
    }

    public void limpiarFormulario() {
        txtCedula.setText("");
        txtNombredeusuario2.setText("");
        txtCorreo1.setText("");
        txtNombredeNegocio.setText("");
        txtDireccion.setText("");
        jComboBox2.setSelectedIndex(0);
        jComboBox3.setSelectedIndex(0);
    }

    private void cargarCargos() {
        Cargo.removeAllItems();

        String[] cargos = {
            "Administrador del negocio",
            "Gerente",
            "Encargado",
            "Supervisor",
            "Jefe de operaciones"
        };

        for (String cargo : cargos) {
            Cargo.addItem(cargo);
        }

        Cargo.setSelectedIndex(-1);
    }

    private void cargarGeneros() {
        jComboBox3.removeAllItems();

        String[] generos = {
            "Seleccione género",
            "Masculino",
            "Femenino",
            "Otro",};

        for (String genero : generos) {
            jComboBox3.addItem(genero);
        }

        jComboBox3.setSelectedIndex(0);
    }

    private void cargarNacionalidades() {
        jComboBox2.removeAllItems();
        jComboBox2.addItem("Seleccione nacionalidad");

        String[] nacionalidades = {
            "Afgana", "Alemana", "Andorrana", "Angoleña", "Antiguana", "Aparecida", "Arabe Saudita", "Argelina", "Argentina", "Armenia",
            "Australiana", "Austríaca", "Azerbaiyana", "Bahameña", "Bahreiní", "Bangladesí", "Barbadense", "Belga", "Beliceña", "Beninesa",
            "Bielorrusa", "Birmana", "Boliviana", "Bosnia", "Botsuana", "Brasileña", "Bruneana", "Búlgara", "Burkinesa", "Burundesa",
            "Butanesa", "Cabo Verdiana", "Camboyana", "Camerunesa", "Canadiense", "Catarí", "Centroafricana", "Chadiana", "Checa",
            "Chilena", "China", "Chipriota", "Colombiana", "Comorense", "Congoleña", "Costarricense", "Croata", "Cubana", "Danesa",
            "Dominicana", "Ecuatoriana", "Egipcia", "Salvadoreña", "Emiratí", "Eritrea", "Eslovaca", "Eslovena", "Española", "Estadounidense",
            "Estonia", "Etíope", "Filipina", "Finlandesa", "Francesa", "Gabonense", "Gambiana", "Georgiana", "Ghanesa", "Granadina",
            "Griega", "Guatemalteca", "Guineana", "Guineana Ecuatorial", "Guineana-Bisau", "Guyana", "Haitiana", "Hondureña", "Húngara",
            "India", "Indonesia", "Iraní", "Iraquí", "Irlandesa", "Islandesa", "Israelí", "Italiana", "Jamaicana", "Japonesa", "Jordana",
            "Kazaja", "Keniata", "Kirguisa", "Kiribatiana", "Kuwaití", "Laosiana", "Lesotense", "Letona", "Libanesa", "Liberiana",
            "Libia", "Liechtensteiniana", "Lituana", "Luxemburguesa", "Macedonia", "Malasia", "Malauí", "Maldiva", "Maliense", "Maltesa",
            "Marfileña", "Marroquí", "Marshallesa", "Mauriciana", "Mauritana", "Mexicana", "Micronesia", "Moldava", "Monegasca", "Mongola",
            "Montenegrina", "Mozambiqueña", "Namibia", "Nauruana", "Nepalí", "Nicaragüense", "Nigeriana", "Nigerina", "Norcoreana",
            "Noruega", "Neozelandesa", "Omana", "Paquistaní", "Palaosiana", "Panameña", "Papú", "Paraguaya", "Peruana", "Polaca",
            "Portuguesa", "Qatarí", "Reino Unido", "Centroafricana", "Rumana", "Rusa", "Ruandesa", "Samoana", "San Cristóbal y Nieves",
            "Sanvicentina", "Santalucense", "Samoana", "Sanmarinense", "Santomense", "Senegalesa", "Serbia", "Seychellense", "Sierraleonesa",
            "Singapurense", "Siria", "Somalí", "Sri Lanka", "Suazi", "Sudafricana", "Sudanesa", "Sueca", "Suiza", "Surcoreana",
            "Surinamesa", "Tailandesa", "Tanzana", "Tayika", "Timorense", "Togolesa", "Tongana", "Trinitense", "Tunecina", "Turca",
            "Turcomana", "Tuvaluana", "Ucraniana", "Ugandesa", "Uruguaya", "Uzbeca", "Vanuatuense", "Venezolana", "Vietnamita",
            "Yemení", "Yibutiana", "Zambiana", "Zimbabuense", "Otra"
        };

        for (String nacionalidad : nacionalidades) {
            jComboBox2.addItem(nacionalidad);
        }
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


    }//GEN-LAST:event_jComboBox2ActionPerformed

    private void btn_GuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_GuardarActionPerformed
       controladorSuperUsuario.registrarSuperUsuario(true);


    }//GEN-LAST:event_btn_GuardarActionPerformed

    private void CargoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CargoActionPerformed


    }//GEN-LAST:event_CargoActionPerformed

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
 public boolean isVentanaCargada() {
             System.out.println("Ventana cargada completamente");
        return ventanaCargada;
        
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> Cargo;
    private javax.swing.JLabel Catedral;
    private javax.swing.JLabel Cuencanas;
    private javax.swing.JTextArea Descripcion;
    private javax.swing.JLabel Huellas;
    private javax.swing.JLabel IniciarSesión;
    private javax.swing.JLabel Yatienes;
    private javax.swing.JButton btn_Guardar;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton5;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txtCedula;
    private javax.swing.JTextField txtCorreo1;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtNombredeNegocio;
    private javax.swing.JTextField txtNombredeusuario2;
    // End of variables declaration//GEN-END:variables
}
