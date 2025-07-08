/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Vista;

import Controlador.ControladorUsuarioPrincipal;
import Design.ComboBox.Combobox;
import Design.Imagen.CircularImageLabel;
import Design.Imagen.ImagenUtils;
import Design.RoundedButtonEliminarRe;
import Design.RoundedButtonInsertar;
import Design.RoundedButtonLimpiar;
import Design.RoundedButtonSalirRe;
import Design.RoundedFormattedTextField;
import Design.RoundedPanelFecha;
import Design.RoundedPasswordField;
import Design.RoundedTextArea;
import Design.RoundedTextArea1;
import Design.RoundedTextField2;
import Modelo.Usuario;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;
import javax.swing.UIManager;
import raven.glasspanepopup.GlassPanePopup;
import com.github.lgooddatepicker.components.DatePicker;
import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 *
 * @author USER
 */
public class Ventana_Formulario_Usuario extends javax.swing.JPanel {

    //Declarar para cargar la imagen:
    private CircularImageLabel lblFotoPerfil;
    private byte[] icono;
    private JTextArea txtSobreMi;
    private RoundedFormattedTextField Fecha;
    private boolean passwordVisible = false;
    private boolean confirmarVisible = false;

    /**
     * Creates new form Ventana_Formulario_Usuario
     */
    public Ventana_Formulario_Usuario() {
        setOpaque(false);

        initComponents();
        Nacionalidades.setLightWeightPopupEnabled(false);
        Generos.setLightWeightPopupEnabled(false);

        //Inicializar la imagen:
        lblFotoPerfil = new CircularImageLabel();
        lblFotoPerfil.setPreferredSize(new Dimension(100, 100));
        lblFotoPerfil.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panelFotoPerfil.add(lblFotoPerfil);

        //Forzar el ComboBox:
        System.setProperty("sun.awt.noerasebackground", "true");
        UIManager.put("ComboBox.isPopDownHeavyWeight", Boolean.TRUE);

        //Instanciar la cédula como "No editable":
        txtCedula.setEnabled(false);

        Fecha = new RoundedFormattedTextField(20);
        PanelFecha.add(Fecha);

        //Cargar las funciones:
        cargarNacionalidades();
        cargarGeneros();

        // Crea el JTextArea.
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

        // Panel intermedio para dar margen y evitar que el scroll tape el borde del contenedor
        JPanel margenPanel = new JPanel(new BorderLayout());
        margenPanel.setOpaque(false);
        margenPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        margenPanel.add(scroll, BorderLayout.CENTER);

        // Contenedor redondeado que pinta el borde
        RoundedPanelFecha contenedor = new RoundedPanelFecha();
        contenedor.setLayout(new BorderLayout());
        contenedor.setPreferredSize(new Dimension(250, 100));
        contenedor.add(margenPanel, BorderLayout.CENTER);

        // Agrega al panel principal
        SobreMi.add(contenedor);

        //Fuentes:
        Registro.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 30));
        Cedula.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 16));
        Aceptar.setFont(new Font("Segoe UI Light", Font.PLAIN, 14));
        Cancelar.setFont(new Font("Segoe UI Light", Font.PLAIN, 14));
        NombreUser.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 16));
        Correo.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 16));
        Nacionalidad.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 16));
        Nacionalidades.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 16));
        Contraseña.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 16));
        Nombre.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 16));
        Apellido.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 16));
        Fecha_Nacimiento.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 16));
        Género.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 16));
        Generos.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 16));
        Sobre.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 16));
        Cargar.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 16));
        txtCedula.setFont(new Font("Segoe UI Light", Font.PLAIN, 16));
        txtNombredeusuario.setFont(new Font("Segoe UI Light", Font.PLAIN, 16));
        CorreoElectronico.setFont(new Font("Segoe UI Light", Font.PLAIN, 16));
        Nombres.setFont(new Font("Segoe UI Light", Font.PLAIN, 16));
        Apellidos.setFont(new Font("Segoe UI Light", Font.PLAIN, 16));
        Generos.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 16));
        Contrasenia.setFont(new Font("Segoe UI Light", Font.PLAIN, 16));
        SobreMi.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 16));

    }

    //Darle bordes al popup:
    @Override
    protected void paintComponent(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        g2.fill(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 25, 25));
        g2.dispose();
        super.paintComponent(grphcs);
    }

    //Cargar Datos de Usuario:
    public void cargarDatosUsuario(Usuario usuario) {
        this.getTxtCedula().setText(usuario.getCedula() != null ? usuario.getCedula() : "");
        this.getNombres().setText(usuario.getNombres() != null ? usuario.getNombres() : "");
        this.getApellidos().setText(usuario.getApellidos() != null ? usuario.getApellidos() : "");
        if (usuario.getFechaNacimiento() != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String fechaFormateada = usuario.getFechaNacimiento().format(formatter);
            this.getFecha().setText(fechaFormateada);
        } else {
            this.getFecha().setText("");
        }
        this.getGeneros().setSelectedItem(usuario.getGenero() != null ? usuario.getGenero() : "");
        this.getTxtSobreMi().setText(usuario.getSobreMi() != null ? usuario.getSobreMi() : "");
        this.getTxtNombredeusuario().setText(usuario.getUsuario() != null ? usuario.getUsuario() : "");
        this.getCorreoElectronico().setText(usuario.getCorreo() != null ? usuario.getCorreo() : "");
        this.getContrasenia().setText(usuario.getContrasena());
        this.getNacionalidades().setSelectedItem(usuario.getNacionalidad() != null ? usuario.getNacionalidad() : "");
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
        Registro = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        Cedula = new javax.swing.JLabel();
        txtCedula = new RoundedTextField2(20);
        Aceptar = new RoundedButtonInsertar("");
        Cancelar = new RoundedButtonEliminarRe("");
        NombreUser = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        txtNombredeusuario = new RoundedTextField2(20);
        Correo = new javax.swing.JLabel();
        CorreoElectronico = new RoundedTextField2(20);
        Nacionalidad = new javax.swing.JLabel();
        Contraseña = new javax.swing.JLabel();
        Contrasenia = new RoundedPasswordField(20);
        Nombre = new javax.swing.JLabel();
        Nombres =  new RoundedTextField2(20);
        Apellido = new javax.swing.JLabel();
        Apellidos =  new RoundedTextField2(20);
        Fecha_Nacimiento = new javax.swing.JLabel();
        Género = new javax.swing.JLabel();
        Generos = new Combobox<>(new String[]{
        });
        Sobre = new javax.swing.JLabel();
        SobreMi = new javax.swing.JPanel();
        Cargar = new javax.swing.JLabel();
        Limpiar = new RoundedButtonLimpiar("");
        panelFotoPerfil = new javax.swing.JPanel();
        PanelFecha = new RoundedPanelFecha();
        Nacionalidades = new Combobox<>(new String[]{
        });

        setPreferredSize(new java.awt.Dimension(1200, 650));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(34, 54, 94));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Registro.setForeground(new java.awt.Color(255, 255, 255));
        Registro.setText("Registro de Persona");
        jPanel1.add(Registro, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 20, -1, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/Admin User.png"))); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(496, 24, 60, 40));

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1290, 90));

        Cedula.setForeground(new java.awt.Color(0, 0, 0));
        Cedula.setText("Cédula");
        add(Cedula, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 220, -1, -1));

        txtCedula.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        add(txtCedula, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 250, 250, 40));

        Aceptar.setText("Aceptar");
        Aceptar.setBorderPainted(false);
        Aceptar.setContentAreaFilled(false);
        Aceptar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Aceptar.setFocusPainted(false);
        Aceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AceptarActionPerformed(evt);
            }
        });
        add(Aceptar, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 580, 100, 30));

        Cancelar.setText("Cancelar");
        Cancelar.setBorderPainted(false);
        Cancelar.setContentAreaFilled(false);
        Cancelar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Cancelar.setFocusPainted(false);
        Cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CancelarActionPerformed(evt);
            }
        });
        add(Cancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 580, 100, 30));

        NombreUser.setForeground(new java.awt.Color(0, 0, 0));
        NombreUser.setText("Nombre de Usuario");
        add(NombreUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 320, -1, -1));

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
        add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 170, 40, 20));

        txtNombredeusuario.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtNombredeusuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombredeusuarioActionPerformed(evt);
            }
        });
        add(txtNombredeusuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 360, 250, 40));

        Correo.setForeground(new java.awt.Color(0, 0, 0));
        Correo.setText("Correo Electrónico");
        add(Correo, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 430, -1, -1));
        add(CorreoElectronico, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 470, 250, 40));

        Nacionalidad.setForeground(new java.awt.Color(0, 0, 0));
        Nacionalidad.setText("Nacionalidad");
        add(Nacionalidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 130, -1, -1));

        Contraseña.setForeground(new java.awt.Color(0, 0, 0));
        Contraseña.setText("Contraseña");
        add(Contraseña, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 130, -1, 20));

        Contrasenia.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        add(Contrasenia, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 160, 250, 40));

        Nombre.setForeground(new java.awt.Color(0, 0, 0));
        Nombre.setText("Nombres");
        add(Nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 130, -1, -1));

        Nombres.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        add(Nombres, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 160, 250, 40));

        Apellido.setForeground(new java.awt.Color(0, 0, 0));
        Apellido.setText("Apellidos");
        add(Apellido, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 220, -1, -1));

        Apellidos.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        add(Apellidos, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 250, 250, 40));

        Fecha_Nacimiento.setForeground(new java.awt.Color(0, 0, 0));
        Fecha_Nacimiento.setText("Fecha de Nacimiento");
        add(Fecha_Nacimiento, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 320, -1, -1));

        Género.setForeground(new java.awt.Color(0, 0, 0));
        Género.setText("Género");
        add(Género, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 430, -1, -1));

        Generos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        Generos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        add(Generos, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 470, 250, 40));

        Sobre.setForeground(new java.awt.Color(0, 0, 0));
        Sobre.setText("Sobre Mí");
        add(Sobre, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 220, -1, 20));
        add(SobreMi, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 250, 260, 150));

        Cargar.setText("Cargar Imagen");
        add(Cargar, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 420, -1, -1));

        Limpiar.setText("Seleccione una imagen");
        Limpiar.setBorderPainted(false);
        Limpiar.setContentAreaFilled(false);
        Limpiar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Limpiar.setFocusPainted(false);
        Limpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LimpiarActionPerformed(evt);
            }
        });
        add(Limpiar, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 474, 260, 30));
        add(panelFotoPerfil, new org.netbeans.lib.awtextra.AbsoluteConstraints(1140, 400, 100, 120));
        add(PanelFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 360, 250, 40));

        Nacionalidades.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        Nacionalidades.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        add(Nacionalidades, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 160, 250, 40));
    }// </editor-fold>//GEN-END:initComponents

    private void CancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CancelarActionPerformed
        GlassPanePopup.closePopupLast();

    }//GEN-LAST:event_CancelarActionPerformed

    private void LimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LimpiarActionPerformed
        icono = ImagenUtils.seleccionarYConvertirImagen(lblFotoPerfil);


    }//GEN-LAST:event_LimpiarActionPerformed

    private void AceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AceptarActionPerformed


    }//GEN-LAST:event_AceptarActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed

        if (passwordVisible) {
            Contrasenia.setEchoChar('•');
            jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/hidden (1).png")));
            passwordVisible = false;
        } else {
            Contrasenia.setEchoChar((char) 0);
            jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/watch (1).png")));
            passwordVisible = true;
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void txtNombredeusuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombredeusuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombredeusuarioActionPerformed

    //Método para cargar Nacionalidades:
    private void cargarNacionalidades() {
        Nacionalidades.removeAllItems();
        Nacionalidades.addItem("Seleccione nacionalidad");

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
            Nacionalidades.addItem(nacionalidad);
        }
    }

    //Cargar Géneros:
    private void cargarGeneros() {
        Generos.removeAllItems();

        String[] generos = {
            "Seleccione género",
            "Masculino",
            "Femenino",
            "Otro",};

        for (String genero : generos) {
            Generos.addItem(genero);
        }

        Generos.setSelectedIndex(0);
    }

    //Getters y Setters para poder captar lo que está en los campos.
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

    public JButton getAceptar() {
        return Aceptar;
    }

    public void setAceptar(JButton Aceptar) {
        this.Aceptar = Aceptar;
    }

    public JTextField getApellidos() {
        return Apellidos;
    }

    public void setApellidos(JTextField Apellidos) {
        this.Apellidos = Apellidos;
    }

    public JButton getCancelar() {
        return Cancelar;
    }

    public void setCancelar(JButton Cancelar) {
        this.Cancelar = Cancelar;
    }

    public JPasswordField getContrasenia() {
        return Contrasenia;
    }

    public void setContrasenia(JPasswordField Contrasenia) {
        this.Contrasenia = Contrasenia;
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

    public JComboBox<String> getGeneros() {
        return Generos;
    }

    public void setGeneros(JComboBox<String> Generos) {
        this.Generos = Generos;
    }

    public JButton getLimpiar() {
        return Limpiar;
    }

    public void setLimpiar(JButton Limpiar) {
        this.Limpiar = Limpiar;
    }

    public JComboBox<String> getNacionalidades() {
        return Nacionalidades;
    }

    public void setNacionalidades(JComboBox<String> Nacionalidades) {
        this.Nacionalidades = Nacionalidades;
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

    public CircularImageLabel getLblFotoPerfil() {
        return lblFotoPerfil;
    }

    public void setLblFotoPerfil(CircularImageLabel lblFotoPerfil) {
        this.lblFotoPerfil = lblFotoPerfil;
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Aceptar;
    private javax.swing.JLabel Apellido;
    private javax.swing.JTextField Apellidos;
    private javax.swing.JButton Cancelar;
    private javax.swing.JLabel Cargar;
    private javax.swing.JLabel Cedula;
    private javax.swing.JPasswordField Contrasenia;
    private javax.swing.JLabel Contraseña;
    private javax.swing.JLabel Correo;
    private javax.swing.JTextField CorreoElectronico;
    private javax.swing.JLabel Fecha_Nacimiento;
    private javax.swing.JComboBox<String> Generos;
    private javax.swing.JLabel Género;
    private javax.swing.JButton Limpiar;
    private javax.swing.JLabel Nacionalidad;
    private javax.swing.JComboBox<String> Nacionalidades;
    private javax.swing.JLabel Nombre;
    private javax.swing.JLabel NombreUser;
    private javax.swing.JTextField Nombres;
    private javax.swing.JPanel PanelFecha;
    private javax.swing.JLabel Registro;
    private javax.swing.JLabel Sobre;
    private javax.swing.JPanel SobreMi;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel panelFotoPerfil;
    private javax.swing.JTextField txtCedula;
    private javax.swing.JTextField txtNombredeusuario;
    // End of variables declaration//GEN-END:variables
}
