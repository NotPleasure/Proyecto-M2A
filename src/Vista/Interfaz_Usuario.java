/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Vista;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Design.RoundedPanelRound;
import Design.RoundedPanelUser;
import Design.RoundedPanel;
import java.awt.Font;
import javax.swing.JOptionPane;
import Design.RoundedTextField1;
import java.awt.Component;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;
import Design.ComboBox.Combobox;
import Design.RoundedButtonAceptar;
import Design.RoundedButtonInicio;
import Design.RoundedButtonReOscuro;
import Design.RoundedPanelMenu;
import Design.RoundedPanelMini;
import Animations.Animator1;
import Controlador.ControladorActualizarPerfilUsuarioPrincipal1;
import Controlador.ControladorActualizarUsuarioPrincipal;
import Design.Imagen.CircularImageLabel;
import Design.Imagen.FotoPerfilRedonda;
import Design.RoundedFormattedTextField;
import Design.RoundedPanelFecha;
import Design.RoundedTextArea1;
import java.awt.BorderLayout;
import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.util.prefs.Preferences;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileNameExtensionFilter;
import Design.RoundedTextField2;
import Modelo.Persona;
import Modelo.Usuario;
import Modelo.UsuarioDAO;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import raven.glasspanepopup.GlassPanePopup;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author USER
 */
public class Interfaz_Usuario extends javax.swing.JFrame {

    private Interfaz_Usuario principal1;
    RoundedFormattedTextField Fecha2 = new RoundedFormattedTextField(20);
    private RoundedFormattedTextField Fecha;
    private JTextArea txtSobreMi;
    private byte[] icono;
    FotoPerfilRedonda panelFotoPerfil = new FotoPerfilRedonda();

    private Ventana_UsuarioPrincipal ventanaUsuarios;
    private Usuario usuarioLogueado;
    private Persona persona;

    public Interfaz_Usuario(Persona persona, Ventana_UsuarioPrincipal ventanaUsuarios) {

        initComponents();
        this.persona = persona;
        this.ventanaUsuarios = ventanaUsuarios;

        if (persona != null) {
            String nombreCompleto = persona.getUsuario();
            lblNombre.setText(nombreCompleto);
            lblCorreo.setText(persona.getCorreo());
        }

        //Instanciar la cédula como "No editable":
        txtCedula.setEnabled(false);

        //Extender y maximizar la Ventana:
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setExtendedState(Ventana_Principal.MAXIMIZED_BOTH);

        //Inicializar el popup:
        GlassPanePopup.install(this);

        // Crea el JTextArea
        txtSobreMi = new RoundedTextArea1(5, 20);
        txtSobreMi.setOpaque(false);
        txtSobreMi.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        txtSobreMi.setLineWrap(true);
        txtSobreMi.setWrapStyleWord(true);
        txtSobreMi.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtSobreMi.setCaretColor(Color.BLACK);

        // ScrollPane sin bordes ni opacidad
        JScrollPane scroll = new JScrollPane(txtSobreMi);
        scroll.setBorder(BorderFactory.createEmptyBorder());
        scroll.setOpaque(false);
        scroll.getViewport().setOpaque(false);
        scroll.setViewportBorder(null);

        // Panel intermedio para margen (solo lateral, sin margen arriba)
        JPanel margenPanel = new JPanel(new BorderLayout());
        margenPanel.setOpaque(false);
        margenPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10)); // margen lateral e inferior
        margenPanel.add(scroll, BorderLayout.CENTER);

        // Contenedor redondeado que pinta el borde
        RoundedPanelFecha contenedor = new RoundedPanelFecha();
        contenedor.setLayout(new BorderLayout());
        contenedor.setPreferredSize(new Dimension(640, 180));
        contenedor.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0)); // sin borde adicional
        contenedor.add(margenPanel, BorderLayout.CENTER);

        // Agrega al panel principal
        SobreMi1.setLayout(new BorderLayout());
        SobreMi1.add(contenedor, BorderLayout.CENTER);

        SobreMi1.revalidate();
        SobreMi1.repaint();

        //Cargar el fecha:
        Fecha = new RoundedFormattedTextField(20);
        PanelFecha.add(Fecha);

        //Fuentes para el Label:
        Huellas.setFont(new Font("Open Sans Bold", Font.PLAIN, 20));
        Cuencanas.setFont(new Font("Open Sans", Font.PLAIN, 18));
        Informacion.setFont(new Font("CocogooseProTrial", Font.PLAIN, 20));
        txtNombredeusuario.setFont(new Font("Caviar Dreams", Font.PLAIN, 15));
        UsuarioNombre.setFont(new Font("Caviar Dreams", Font.PLAIN, 15));
        Fecha.setFont(new Font("Caviar Dreams", Font.PLAIN, 15));
        NombreOp.setFont(new Font("Caviar Dreams", Font.PLAIN, 15));
        Nombres.setFont(new Font("Caviar Dreams", Font.PLAIN, 15));
        ApellidoOP.setFont(new Font("Caviar Dreams", Font.PLAIN, 15));
        Apellidos.setFont(new Font("Caviar Dreams", Font.PLAIN, 15));
        CorreoOP.setFont(new Font("Caviar Dreams", Font.PLAIN, 15));
        CorreoElectronico.setFont(new Font("Caviar Dreams", Font.PLAIN, 15));
        lblGenero.setFont(new Font("Caviar Dreams", Font.PLAIN, 15));
        GeneroOp.setFont(new Font("Caviar Dreams", Font.PLAIN, 15));
        Cantón.setFont(new Font("Caviar Dreams", Font.PLAIN, 15));
        Provincias.setFont(new Font("Caviar Dreams", Font.PLAIN, 15));
        lblNacionalidad.setFont(new Font("Caviar Dreams", Font.PLAIN, 15));
        Sobre.setFont(new Font("CocogooseProTrial", Font.PLAIN, 19));
        Guardar.setFont(new Font("Caviar Dreams", Font.PLAIN, 15));
        lblNombre.setFont(new Font("CocogooseProTrial", Font.PLAIN, 25));
        lblCorreo.setFont(new Font("Caviar Dreams", Font.PLAIN, 15));
        Selecciona.setFont(new Font("CocogooseProTrial", Font.PLAIN, 15));
        status.setFont(new Font("Caviar Dreams", Font.PLAIN, 15));
        pixeles.setFont(new Font("Caviar Dreams", Font.PLAIN, 15));
        jLabel9.setFont(new Font("Caviar Dreams", Font.PLAIN, 24));
        Fecha1.setFont(new Font("Caviar Dreams", Font.PLAIN, 15));

        //Cargar los datos del usuario:
        if (persona != null) {
            cargarDatosUsuario(persona);
        }

        deshabilitarCampos();

    }

    public void refreshFields(Usuario u) {
        if (u == null) {
            return;
        }

        if (u.getIcono() != null) {
            try {
                byte[] bytesImagen = u.getIcono();
                ByteArrayInputStream bais = new ByteArrayInputStream(bytesImagen);
                BufferedImage img = ImageIO.read(bais);
                fotoPerfilRedonda1.setImagen(img);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } else {
            fotoPerfilRedonda1.setImagen(null);
        }

        getTxtSobreMi().setText(u.getSobreMi());

        getApellidos().setText(u.getApellidos());

        if (u.getFechaNacimiento() != null) {
            DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            getFecha().setText(u.getFechaNacimiento().format(fmt));
        } else {
            getFecha().setText("");
        }

        getLblGenero().setText(u.getGenero());
        getLblNacionalidad().setText(u.getNacionalidad());

        getNombres().setText(u.getNombres());
        getTxtCedula().setText(u.getCedula() != null ? u.getCedula() : "");

        getTxtNombredeusuario().setText(u.getUsuario());
    }

    public void cargarDatosUsuario(Persona persona) {
        if (persona == null) {
            System.err.println("Error: La persona es null");
            JOptionPane.showMessageDialog(this, "Error: No se ha seleccionado ningún usuario");
            return;
        }
        this.getTxtCedula().setText(persona.getCedula() != null ? persona.getCedula() : "");
        this.getNombres().setText(persona.getNombres() != null ? persona.getNombres() : "");
        this.getApellidos().setText(persona.getApellidos() != null ? persona.getApellidos() : "");
        if (persona.getFechaNacimiento() != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String fechaFormateada = persona.getFechaNacimiento().format(formatter);
            this.getFecha().setText(fechaFormateada);
        } else {
            this.getFecha().setText("");
        }
        this.lblGenero.setText(persona.getGenero() != null ? persona.getGenero() : "");
        this.getTxtSobreMi().setText(persona.getSobreMi() != null ? persona.getSobreMi() : "");
        System.out.println("SobreMi persona: " + persona.getSobreMi());
        System.out.println("txtSobreMi: " + txtSobreMi);

        this.getTxtNombredeusuario().setText(persona.getUsuario() != null ? persona.getUsuario() : "");
        this.getCorreoElectronico().setText(persona.getCorreo() != null ? persona.getCorreo() : "");
        this.lblNacionalidad.setText(persona.getNacionalidad() != null ? persona.getNacionalidad() : "");

        if (persona.getIcono() != null) {
            try {
                byte[] bytesImagen = persona.getIcono();
                ByteArrayInputStream bais = new ByteArrayInputStream(bytesImagen);
                BufferedImage img = ImageIO.read(bais);

                fotoPerfilRedonda1.setImagen(img);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private void deshabilitarCampos() {
        txtSobreMi.setEditable(false);
        txtCedula.setEditable(false);
        txtNombredeusuario.setEditable(false);
        CorreoElectronico.setEditable(false);
        Nombres.setEditable(false);
        Apellidos.setEditable(false);

        Fecha2.setEnabled(false);
    }

    public byte[] getIcono() {
        return icono;
    }

    public void setIcono(byte[] icono) {
        this.icono = icono;
    }

    public JTextArea getTxtSobreMi() {
        return txtSobreMi;
    }

    public void setTxtSobreMi(JTextArea txtSobreMi) {
        this.txtSobreMi = txtSobreMi;
    }

    public JTextField getApellidos() {
        return Apellidos;
    }

    public void setApellidos(JTextField Apellidos) {
        this.Apellidos = Apellidos;
    }

    public JTextField getCorreoElectronico() {
        return CorreoElectronico;
    }

    public void setCorreoElectronico(JTextField CorreoElectronico) {
        this.CorreoElectronico = CorreoElectronico;
    }

    public RoundedFormattedTextField getFecha() {
        return Fecha;
    }

    public void setFecha(RoundedFormattedTextField Fecha) {
        this.Fecha = Fecha;
    }

    public JTextField getLblGenero() {
        return lblGenero;
    }

    public void setLblGenero(JTextField lblGenero) {
        this.lblGenero = lblGenero;
    }

    public JTextField getLblNacionalidad() {
        return lblNacionalidad;
    }

    public void setLblNacionalidad(JTextField lblNacionalidad) {
        this.lblNacionalidad = lblNacionalidad;
    }

    public JTextField getNombres() {
        return Nombres;
    }

    public void setNombres(JTextField Nombres) {
        this.Nombres = Nombres;
    }

    public JTextField getTxtCedula() {
        return txtCedula;
    }

    public void setTxtCedula(JTextField txtCedula) {
        this.txtCedula = txtCedula;
    }

    public JTextField getTxtNombredeusuario() {
        return txtNombredeusuario;
    }

    public void setTxtNombredeusuario(JTextField txtNombredeusuario) {
        this.txtNombredeusuario = txtNombredeusuario;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        roundedPanelRound1 = new Design.RoundedPanelRound();
        jPanel1 = new RoundedPanelUser();
        jLabel7 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel2 = new RoundedPanelUser();
        Informacion = new javax.swing.JLabel();
        txtNombredeusuario =  new RoundedTextField1(20);
        UsuarioNombre = new javax.swing.JLabel();
        Fecha1 = new javax.swing.JLabel();
        NombreOp = new javax.swing.JLabel();
        Nombres = new RoundedTextField1(20);
        ApellidoOP = new javax.swing.JLabel();
        Apellidos =  new RoundedTextField1(20);
        CorreoOP = new javax.swing.JLabel();
        CorreoElectronico = new RoundedTextField1(20);
        GeneroOp = new javax.swing.JLabel();
        Cantón = new javax.swing.JLabel();
        Provincias = new javax.swing.JLabel();
        Sobre = new javax.swing.JLabel();
        Guardar = new RoundedButtonAceptar("");
        PanelFecha = new RoundedPanelFecha();
        SobreMi1 = new javax.swing.JPanel();
        txtCedula =  new RoundedTextField1(20);
        lblGenero =  new RoundedTextField1(20);
        lblNacionalidad =  new RoundedTextField1(20);
        jPanel3 = new RoundedPanelUser();
        fotoPerfilRedonda1 = new Design.Imagen.FotoPerfilRedonda();
        jLabel1 = new javax.swing.JLabel();
        lblNombre = new javax.swing.JLabel();
        lblCorreo = new javax.swing.JLabel();
        jPanel4 = new RoundedPanelMini();
        Selecciona = new javax.swing.JLabel();
        status = new javax.swing.JLabel();
        pixeles = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jPanel5 = new RoundedPanelMenu();
        jButton3 = new javax.swing.JButton();
        Huellas = new javax.swing.JLabel();
        Cuencanas = new javax.swing.JLabel();
        Principal_Lugar = new javax.swing.JLabel();
        Principal = new javax.swing.JButton();
        jButton2 = new RoundedButtonInicio("");
        Hoteles = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jButton4 = new RoundedButtonInicio("");
        jLabel4 = new javax.swing.JLabel();
        RestauranteIcon = new javax.swing.JButton();
        jButton5 = new RoundedButtonInicio("");
        jLabel3 = new javax.swing.JLabel();
        jButton13 = new javax.swing.JButton();
        jButton6 = new RoundedButtonInicio("");
        jLabel9 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        btnUsuario = new RoundedButtonReOscuro("");
        jLabel29 = new javax.swing.JLabel();
        btnPerfil = new javax.swing.JLabel();
        jButton10 = new RoundedButtonReOscuro("");
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jButton11 = new RoundedButtonReOscuro("");
        jLabel32 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jButton9 = new RoundedButtonReOscuro("");
        jLabel33 = new javax.swing.JLabel();
        jButton7 = new RoundedButtonReOscuro("");

        setBackground(new java.awt.Color(204, 255, 204));
        setPreferredSize(new java.awt.Dimension(1090, 800));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(51, 51, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(1120, 800));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/icons8_Expand_Arrow_32px.png"))); // NOI18N
        jLabel7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel7MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(1032, 2, -1, -1));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/Salir (2) (2).png"))); // NOI18N
        jLabel5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(1072, 2, -1, -1));

        jPanel2.setForeground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Informacion.setForeground(new java.awt.Color(255, 255, 255));
        Informacion.setText("Información Personal");
        jPanel2.add(Informacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 31, -1, -1));

        txtNombredeusuario.setEditable(false);
        txtNombredeusuario.setText("Andrés");
        jPanel2.add(txtNombredeusuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, 180, 40));

        UsuarioNombre.setForeground(new java.awt.Color(255, 255, 255));
        UsuarioNombre.setText("Nombre de Usuario:");
        jPanel2.add(UsuarioNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, -1, -1));

        Fecha1.setForeground(new java.awt.Color(255, 255, 255));
        Fecha1.setText("Fecha de Nacimiento");
        jPanel2.add(Fecha1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 190, 160, -1));

        NombreOp.setForeground(new java.awt.Color(255, 255, 255));
        NombreOp.setText("Nombre (Opcional) :");
        jPanel2.add(NombreOp, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 80, -1, -1));

        Nombres.setEditable(false);
        Nombres.setText("Ingrese su nombre");
        jPanel2.add(Nombres, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 110, 180, 40));

        ApellidoOP.setForeground(new java.awt.Color(255, 255, 255));
        ApellidoOP.setText("Apellido (Opcional):");
        jPanel2.add(ApellidoOP, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 80, -1, -1));

        Apellidos.setEditable(false);
        Apellidos.setText("Ingrese su apellido:");
        jPanel2.add(Apellidos, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 110, 180, 40));

        CorreoOP.setForeground(new java.awt.Color(255, 255, 255));
        CorreoOP.setText("Correo:");
        jPanel2.add(CorreoOP, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 190, -1, -1));

        CorreoElectronico.setEditable(false);
        CorreoElectronico.setText("avasuca2024@gmail.com");
        jPanel2.add(CorreoElectronico, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 220, 180, 40));

        GeneroOp.setForeground(new java.awt.Color(255, 255, 255));
        GeneroOp.setText("Género:");
        jPanel2.add(GeneroOp, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 190, -1, -1));

        Cantón.setForeground(new java.awt.Color(255, 255, 255));
        Cantón.setText("Cédula");
        jPanel2.add(Cantón, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 300, -1, -1));

        Provincias.setForeground(new java.awt.Color(255, 255, 255));
        Provincias.setText("Nacionalidad");
        jPanel2.add(Provincias, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 300, -1, -1));

        Sobre.setForeground(new java.awt.Color(255, 255, 255));
        Sobre.setText("Sobre Mí (Opcional)");
        jPanel2.add(Sobre, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 410, -1, -1));

        Guardar.setForeground(new java.awt.Color(255, 255, 255));
        Guardar.setText("Cambiar Datos");
        Guardar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GuardarActionPerformed(evt);
            }
        });
        jPanel2.add(Guardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 670, 140, 30));
        jPanel2.add(PanelFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 220, 180, 40));
        jPanel2.add(SobreMi1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 460, 640, 180));

        txtCedula.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel2.add(txtCedula, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 330, 180, 40));

        lblGenero.setEditable(false);
        jPanel2.add(lblGenero, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 220, 180, 40));

        lblNacionalidad.setEditable(false);
        jPanel2.add(lblNacionalidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 330, 180, 40));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 20, 700, 720));

        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel3.add(fotoPerfilRedonda1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 140, 210, 170));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/CAJAS.png"))); // NOI18N
        jPanel3.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 0, 580, 340));

        lblNombre.setForeground(new java.awt.Color(255, 255, 255));
        lblNombre.setText("Andrés");
        jPanel3.add(lblNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 390, -1, -1));

        lblCorreo.setForeground(new java.awt.Color(255, 255, 255));
        lblCorreo.setText("andresjuca@gmail.com");
        jPanel3.add(lblCorreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 420, -1, -1));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 50, 320, 490));

        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Selecciona.setForeground(new java.awt.Color(255, 255, 255));
        Selecciona.setText("Selecciona una Imagen:");
        jPanel4.add(Selecciona, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 40, -1, -1));

        status.setForeground(new java.awt.Color(255, 255, 255));
        status.setText("JPG, GIF o PNG, máximo de");
        jPanel4.add(status, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 90, -1, -1));

        pixeles.setText("800 píxeles.");
        jPanel4.add(pixeles, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 110, -1, -1));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/[CITYPNG.COM]PNG Photo Camera White Icon - 600x600 (3).png"))); // NOI18N
        jButton1.setBorderPainted(false);
        jButton1.setContentAreaFilled(false);
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.setFocusPainted(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 62, -1, 80));

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 560, 320, 180));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 0, 1130, 800));

        jPanel5.setBackground(new java.awt.Color(255, 153, 153));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/Copilot_20250610_114322 (3).png"))); // NOI18N
        jButton3.setBorderPainted(false);
        jButton3.setContentAreaFilled(false);
        jButton3.setFocusPainted(false);
        jPanel5.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(33, 10, 60, 70));

        Huellas.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        Huellas.setForeground(new java.awt.Color(255, 255, 255));
        Huellas.setText("Huellas ");
        jPanel5.add(Huellas, new org.netbeans.lib.awtextra.AbsoluteConstraints(96, 27, -1, -1));

        Cuencanas.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        Cuencanas.setForeground(new java.awt.Color(255, 255, 255));
        Cuencanas.setText("Cuencanas");
        jPanel5.add(Cuencanas, new org.netbeans.lib.awtextra.AbsoluteConstraints(103, 48, -1, -1));

        Principal_Lugar.setFont(new java.awt.Font("Dialog", 1, 17)); // NOI18N
        Principal_Lugar.setForeground(new java.awt.Color(255, 255, 255));
        Principal_Lugar.setText("Principal");
        jPanel5.add(Principal_Lugar, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 137, -1, -1));

        Principal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/Lugar ICON.png"))); // NOI18N
        Principal.setBorderPainted(false);
        Principal.setContentAreaFilled(false);
        Principal.setFocusPainted(false);
        jPanel5.add(Principal, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 124, 60, 50));

        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 120, 250, 60));

        Hoteles.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/Hotel-removebg-preview (1).png"))); // NOI18N
        Hoteles.setBorderPainted(false);
        Hoteles.setContentAreaFilled(false);
        Hoteles.setFocusPainted(false);
        jPanel5.add(Hoteles, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 184, 50, 50));

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 17)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Hoteles");
        jPanel5.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 196, -1, -1));

        jButton4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 180, 250, 60));

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 17)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Restaurantes");
        jPanel5.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 256, -1, -1));

        RestauranteIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/Restaurante-removebg-preview (1).png"))); // NOI18N
        RestauranteIcon.setBorderPainted(false);
        RestauranteIcon.setContentAreaFilled(false);
        RestauranteIcon.setFocusPainted(false);
        jPanel5.add(RestauranteIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 240, 60, 60));

        jButton5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 230, 250, 70));

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 17)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Cafeterías");
        jPanel5.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 320, -1, -1));

        jButton13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/Cafetería-removebg-preview (2).png"))); // NOI18N
        jButton13.setBorderPainted(false);
        jButton13.setContentAreaFilled(false);
        jButton13.setFocusPainted(false);
        jPanel5.add(jButton13, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 304, 60, 50));

        jButton6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 300, 250, 60));

        jLabel9.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Usuario");
        jPanel5.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 384, 120, -1));

        jLabel26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/experiencia-del-cliente (1).png"))); // NOI18N
        jLabel26.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel26.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel26MouseClicked(evt);
            }
        });
        jPanel5.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(33, 440, 90, 60));

        jLabel28.setFont(new java.awt.Font("Dialog", 1, 17)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(255, 255, 255));
        jLabel28.setText("Usuario");
        jPanel5.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(97, 460, -1, -1));

        btnUsuario.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnUsuario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnUsuarioMouseClicked(evt);
            }
        });
        btnUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUsuarioActionPerformed(evt);
            }
        });
        jPanel5.add(btnUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 440, 250, 60));

        jLabel29.setFont(new java.awt.Font("Dialog", 1, 17)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(255, 255, 255));
        jLabel29.setText("Favoritos");
        jPanel5.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 520, -1, -1));

        btnPerfil.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/satisfaccion (1).png"))); // NOI18N
        btnPerfil.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnPerfil.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnPerfilMouseClicked(evt);
            }
        });
        jPanel5.add(btnPerfil, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 500, 70, 60));

        jButton10.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton10, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 500, 250, 60));

        jLabel30.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/mala-retroalimentacion (1).png"))); // NOI18N
        jLabel30.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel30.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel30MouseClicked(evt);
            }
        });
        jPanel5.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 560, 50, 60));

        jLabel31.setFont(new java.awt.Font("Dialog", 1, 17)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(255, 255, 255));
        jLabel31.setText("Comentarios");
        jPanel5.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 578, -1, -1));

        jButton11.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton11, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 560, 250, 60));

        jLabel32.setFont(new java.awt.Font("Dialog", 1, 17)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(255, 255, 255));
        jLabel32.setText("Reseñas");
        jPanel5.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 636, -1, -1));

        jLabel25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/resenas (1).png"))); // NOI18N
        jPanel5.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 628, 60, 50));

        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 620, 250, 60));

        jLabel33.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/salida (1).png"))); // NOI18N
        jLabel33.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel5.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 693, 50, 70));

        jButton7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 680, 250, 100));

        getContentPane().add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 250, 800));
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseClicked
        this.setState(Registro.ICONIFIED);


    }//GEN-LAST:event_jLabel7MouseClicked

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
        int dialog = JOptionPane.YES_NO_OPTION;
        int result = JOptionPane.showConfirmDialog(null, "¿Desea salir?", "Exit", dialog);
        if (result == 0) {
            System.exit(0);
        }
    }//GEN-LAST:event_jLabel5MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        String nombre = (this.persona != null && this.persona.getUsuario() != null)
                ? this.persona.getUsuario()
                : "";

        Animator1.fadeOut(this, () -> {
            this.dispose();
            Ventana_Principal ventanaUsuario = new Ventana_Principal(this.persona);
            ventanaUsuario.setOpacity(0f);
            ventanaUsuario.setVisible(true);
            Animator1.fadeIn(ventanaUsuario);
        });

    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed

    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton6ActionPerformed

    private void btnUsuarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnUsuarioMouseClicked
        //AnimationClass Hola1 = new AnimationClass();
        //Hola1.jButtonXRight(930, 970, 10, 5, btnPerfil);

        //AnimationClass Hola11 = new AnimationClass();
    }//GEN-LAST:event_btnUsuarioMouseClicked

    private void btnUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUsuarioActionPerformed

        Animator1.fadeOut(this, () -> {
            this.dispose();

            Interfaz_Usuario ventana = new Interfaz_Usuario(usuarioLogueado, ventanaUsuarios);

            ventana.setUndecorated(true);
            ventana.setOpacity(0f);

            ventana.setVisible(true);

            Animator1.fadeIn(ventana);
        });

    }//GEN-LAST:event_btnUsuarioActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        Animator1.fadeOut(this, () -> {
            this.dispose();

            Ventana_MisFavoritos ventana = new Ventana_MisFavoritos();
            ventana.setUndecorated(true);
            ventana.setOpacity(0f);

            ventana.setVisible(true);

            Animator1.fadeIn(ventana);
        });

    }//GEN-LAST:event_jButton10ActionPerformed

    private void btnPerfilMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPerfilMouseClicked


    }//GEN-LAST:event_btnPerfilMouseClicked

    private void jLabel26MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel26MouseClicked

    }//GEN-LAST:event_jLabel26MouseClicked

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        Animator1.fadeOut(this, () -> {
            this.dispose();

            Ventana_Comentarios ventana = new Ventana_Comentarios();
            ventana.setUndecorated(true);
            ventana.setOpacity(0f);
            ventana.setVisible(true);

            Animator1.fadeIn(ventana);
        });
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jLabel30MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel30MouseClicked

    }//GEN-LAST:event_jLabel30MouseClicked

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        int dialog = JOptionPane.YES_NO_OPTION;
        int result = JOptionPane.showConfirmDialog(null, "¿Desea cerrar sesión", "Exit", dialog);
        if (result == 0) {
            System.exit(0);
        }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton9ActionPerformed

    private void GuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GuardarActionPerformed
        if (persona == null) {
            JOptionPane.showMessageDialog(this, "Error: No hay usuario seleccionado");
            return;
        }

        if (!(persona instanceof Usuario)) {
            JOptionPane.showMessageDialog(this, "Error: Solo los usuarios pueden actualizar su perfil");
            return;
        }

        Usuario usuario = (Usuario) persona;

        Ventana_Actualizar_PerfilUsuarioPrincipal1 form = new Ventana_Actualizar_PerfilUsuarioPrincipal1();
        form.cargarDatosUsuario(usuario);
        new ControladorActualizarPerfilUsuarioPrincipal1(form, usuario, this);
        GlassPanePopup.showPopup(form);

    }//GEN-LAST:event_GuardarActionPerformed

    //Refrescar la tabla:
    public void recargarTabla() {
        UsuarioDAO dao = new UsuarioDAO();
        List<Usuario> lista = dao.listarPorRol(2);
        cargarTablaConLista(lista);
    }

    //Cargar la tabla con el Array:
    private void cargarTablaConLista(List<Usuario> lista) {
        String[] columnas = {"ID", "Usuario", "Correo", "Nombres", "Apellidos",
            "Nacionalidad", "Género", "Nacimiento", "Rol", "Cédula"};
        DefaultTableModel modelo = new DefaultTableModel(null, columnas);

        for (Usuario u : lista) {
            Object[] fila = {
                u.getIdPersona(),
                u.getUsuario(),
                u.getCorreo(),
                u.getNombres(),
                u.getApellidos(),
                u.getNacionalidad(),
                u.getGenero(),
                u.getFechaNacimiento(),
                u.getRolId(),
                u.getCedula()
            };
            modelo.addRow(fila);
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel ApellidoOP;
    private javax.swing.JTextField Apellidos;
    private javax.swing.JLabel Cantón;
    private javax.swing.JTextField CorreoElectronico;
    private javax.swing.JLabel CorreoOP;
    private javax.swing.JLabel Cuencanas;
    private javax.swing.JLabel Fecha1;
    private javax.swing.JLabel GeneroOp;
    private javax.swing.JButton Guardar;
    private javax.swing.JButton Hoteles;
    private javax.swing.JLabel Huellas;
    private javax.swing.JLabel Informacion;
    private javax.swing.JLabel NombreOp;
    private javax.swing.JTextField Nombres;
    private javax.swing.JPanel PanelFecha;
    private javax.swing.JButton Principal;
    private javax.swing.JLabel Principal_Lugar;
    private javax.swing.JLabel Provincias;
    private javax.swing.JButton RestauranteIcon;
    private javax.swing.JLabel Selecciona;
    private javax.swing.JLabel Sobre;
    private javax.swing.JPanel SobreMi1;
    private javax.swing.JLabel UsuarioNombre;
    private javax.swing.JLabel btnPerfil;
    private javax.swing.JButton btnUsuario;
    private Design.Imagen.FotoPerfilRedonda fotoPerfilRedonda1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JLabel lblCorreo;
    private javax.swing.JTextField lblGenero;
    private javax.swing.JTextField lblNacionalidad;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel pixeles;
    private Design.RoundedPanelRound roundedPanelRound1;
    private javax.swing.JLabel status;
    private javax.swing.JTextField txtCedula;
    private javax.swing.JTextField txtNombredeusuario;
    // End of variables declaration//GEN-END:variables
}
