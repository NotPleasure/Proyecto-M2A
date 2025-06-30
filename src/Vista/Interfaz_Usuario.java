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
import Design.RoundedPanelMini;
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

/**
 *
 * @author USER
 */
public class Interfaz_Usuario extends javax.swing.JPanel {

    private JLabel imageLabel;
    private String Genero = "Masculino";
    private String Canton = "Cuenca";
    private String Provincia = "Azuay";

    /**
     * Creates new form Interfaz_Usuario
     */
    public Interfaz_Usuario() {
        initComponents();

        imageLabel = new JLabel();
        roundedPanelRound2.setLayout(new BorderLayout());
        roundedPanelRound2.add(imageLabel, BorderLayout.CENTER);

        SwingUtilities.invokeLater(() -> cargarUltimaImagen());

        //Cargar Imagen: 
        imageLabel = new JLabel();
        roundedPanelRound2.setLayout(new BorderLayout());
        roundedPanelRound2.add(imageLabel, BorderLayout.CENTER);

        //Fuentes para el Label:
        Informacion.setFont(new Font("CocogooseProTrial", Font.PLAIN, 20));
        Nombre.setFont(new Font("Caviar Dreams", Font.PLAIN, 15));
        UsuarioNombre.setFont(new Font("Caviar Dreams", Font.PLAIN, 15));
        Fecha.setFont(new Font("Caviar Dreams", Font.PLAIN, 15));
        FechaLabel.setFont(new Font("Caviar Dreams", Font.PLAIN, 15));
        NombreOp.setFont(new Font("Caviar Dreams", Font.PLAIN, 15));
        NombreOpcional.setFont(new Font("Caviar Dreams", Font.PLAIN, 15));
        ApellidoOP.setFont(new Font("Caviar Dreams", Font.PLAIN, 15));
        ApellidoOpcional.setFont(new Font("Caviar Dreams", Font.PLAIN, 15));
        CorreoOP.setFont(new Font("Caviar Dreams", Font.PLAIN, 15));
        CorreoOpcional.setFont(new Font("Caviar Dreams", Font.PLAIN, 15));
        jComboBox3.setFont(new Font("Caviar Dreams", Font.PLAIN, 15));
        GeneroOp.setFont(new Font("Caviar Dreams", Font.PLAIN, 15));
        Cantón.setFont(new Font("Caviar Dreams", Font.PLAIN, 15));
        jComboBox1.setFont(new Font("Caviar Dreams", Font.PLAIN, 15));
        Provincias.setFont(new Font("Caviar Dreams", Font.PLAIN, 15));
        jComboBox2.setFont(new Font("Caviar Dreams", Font.PLAIN, 15));
        SobreMi.setFont(new Font("CocogooseProTrial", Font.PLAIN, 19));
        Guardar.setFont(new Font("Caviar Dreams", Font.PLAIN, 15));
        Andres.setFont(new Font("CocogooseProTrial", Font.PLAIN, 25));
        andres.setFont(new Font("Caviar Dreams", Font.PLAIN, 15));
        Imagen.setFont(new Font("CocogooseProTrial", Font.PLAIN, 15));
        status.setFont(new Font("Caviar Dreams", Font.PLAIN, 15));
        pixeles.setFont(new Font("Caviar Dreams", Font.PLAIN, 15));
        TextArea1.setFont(new Font("Caviar Dreams", Font.PLAIN, 15));

        //Bordes Redondeados ComboBox:
        String[] generos = {
            "Masculino",
            "Femenino",
            "No Binario",
            "Prefiero no Decirlo"
        };

        jComboBox3.setModel(new DefaultComboBoxModel<>(generos));
        jComboBox3.setSelectedIndex(-1);

        jComboBox3.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                    boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

                if (index == -1 && value == null) {
                    setText("Masculino");
                }

                return this;
            }
        });

        //Hacer redondo el otro ComboBox: 
        String[] Cantones = {
            "Azuay", "Bolívar", "Cañar", "Carchi", "Chimborazo", "Cotopaxi", "El Oro",
            "Esmeraldas", "Galápagos", "Guayas", "Imbabura", "Loja", "Los Ríos",
            "Manabí", "Morona Santiago", "Napo", "Orellana", "Pastaza", "Pichincha",
            "Santa Elena", "Santo Domingo de los Tsáchilas", "Sucumbíos", "Tungurahua", "Zamora Chinchipe"
        };

        jComboBox1.setModel(new DefaultComboBoxModel<>(Cantones));
        jComboBox1.setSelectedIndex(-1);

        jComboBox1.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                    boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

                if (index == -1 && value == null) {
                    setText("Cuenca");
                }

                return this;
            }
        });

        //Bordes Redondeados ComboBox:
        String[] provincias = {
            "Azuay", "Bolívar", "Cañar", "Carchi", "Chimborazo", "Cotopaxi", "El Oro",
            "Esmeraldas", "Galápagos", "Guayas", "Imbabura", "Loja", "Los Ríos",
            "Manabí", "Morona Santiago", "Napo", "Orellana", "Pastaza", "Pichincha",
            "Santa Elena", "Santo Domingo de los Tsáchilas", "Sucumbíos", "Tungurahua", "Zamora Chinchipe"
        };

        jComboBox2.setModel(new DefaultComboBoxModel<>(provincias));
        jComboBox2.setSelectedIndex(-1);

        jComboBox2.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                    boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

                if (index == -1 && value == null) {
                    setText("Azuay");
                }

                return this;
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

        roundedPanelRound1 = new Design.RoundedPanelRound();
        jPanel1 = new RoundedPanelUser();
        jLabel7 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel2 = new RoundedPanelUser();
        Informacion = new javax.swing.JLabel();
        Nombre =  new RoundedTextField1(20);
        UsuarioNombre = new javax.swing.JLabel();
        Fecha = new javax.swing.JLabel();
        FechaLabel = new RoundedTextField1(20);
        NombreOp = new javax.swing.JLabel();
        NombreOpcional = new RoundedTextField1(20);
        ApellidoOP = new javax.swing.JLabel();
        ApellidoOpcional =  new RoundedTextField1(20);
        CorreoOP = new javax.swing.JLabel();
        CorreoOpcional = new RoundedTextField1(20);
        GeneroOp = new javax.swing.JLabel();
        jComboBox3 = new Combobox<>(new String[]{
            "Masculino,Femenino, No Binario, Prefiero no Decirlo"});
    ;
    Cantón = new javax.swing.JLabel();
    jComboBox1 = new Combobox<>(new String[]{
        "Azuay", "Bolívar", "Cañar", "Carchi", "Chimborazo", "Cotopaxi", "El Oro",
        "Esmeraldas", "Galápagos", "Guayas", "Imbabura", "Loja", "Los Ríos",
        "Manabí", "Morona Santiago", "Napo", "Orellana", "Pastaza", "Pichincha",
        "Santa Elena", "Santo Domingo de los Tsáchilas", "Sucumbíos", "Tungurahua", "Zamora Chinchipe"});
Provincias = new javax.swing.JLabel();
jComboBox2 = new Combobox<>(new String[]{
    "Azuay", "Bolívar", "Cañar", "Carchi", "Chimborazo", "Cotopaxi", "El Oro",
    "Esmeraldas", "Galápagos", "Guayas", "Imbabura", "Loja", "Los Ríos",
    "Manabí", "Morona Santiago", "Napo", "Orellana", "Pastaza", "Pichincha",
    "Santa Elena", "Santo Domingo de los Tsáchilas", "Sucumbíos", "Tungurahua", "Zamora Chinchipe"});
    SobreMi = new javax.swing.JLabel();
    Guardar = new RoundedButtonAceptar("");
    jScrollPane2 = new javax.swing.JScrollPane();
    TextArea1 = new javax.swing.JTextArea();
    jPanel3 = new RoundedPanelUser();
    roundedPanelRound2 = new Design.RoundedPanelRound();
    jLabel1 = new javax.swing.JLabel();
    Andres = new javax.swing.JLabel();
    andres = new javax.swing.JLabel();
    jPanel4 = new RoundedPanelMini();
    Imagen = new javax.swing.JLabel();
    status = new javax.swing.JLabel();
    pixeles = new javax.swing.JLabel();
    jButton1 = new javax.swing.JButton();

    setBackground(new java.awt.Color(204, 255, 204));
    setPreferredSize(new java.awt.Dimension(1090, 800));
    setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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
    jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 30, -1, -1));

    jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/Salir (2) (2).png"))); // NOI18N
    jLabel5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            jLabel5MouseClicked(evt);
        }
    });
    jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(1090, 30, -1, -1));

    jPanel2.setForeground(new java.awt.Color(255, 255, 255));
    jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

    Informacion.setForeground(new java.awt.Color(255, 255, 255));
    Informacion.setText("Información Personal");
    jPanel2.add(Informacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 31, -1, -1));

    Nombre.setText("Andrés");
    jPanel2.add(Nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, 180, 40));

    UsuarioNombre.setForeground(new java.awt.Color(255, 255, 255));
    UsuarioNombre.setText("Nombre de Usuario:");
    jPanel2.add(UsuarioNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, -1, -1));

    Fecha.setForeground(new java.awt.Color(255, 255, 255));
    Fecha.setText("Fecha de Nacimiento");
    jPanel2.add(Fecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 190, 160, -1));

    FechaLabel.setText("dd/mm/yyyy");
    jPanel2.add(FechaLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 220, 180, 42));

    NombreOp.setForeground(new java.awt.Color(255, 255, 255));
    NombreOp.setText("Nombre (Opcional) :");
    jPanel2.add(NombreOp, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 80, -1, -1));

    NombreOpcional.setText("Ingrese su nombre");
    jPanel2.add(NombreOpcional, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 110, 180, 40));

    ApellidoOP.setForeground(new java.awt.Color(255, 255, 255));
    ApellidoOP.setText("Apellido (Opcional):");
    jPanel2.add(ApellidoOP, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 80, -1, -1));

    ApellidoOpcional.setText("Ingrese su apellido:");
    jPanel2.add(ApellidoOpcional, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 110, 180, 40));

    CorreoOP.setForeground(new java.awt.Color(255, 255, 255));
    CorreoOP.setText("Correo:");
    jPanel2.add(CorreoOP, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 190, -1, -1));

    CorreoOpcional.setText("avasuca2024@gmail.com");
    jPanel2.add(CorreoOpcional, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 220, 180, 40));

    GeneroOp.setForeground(new java.awt.Color(255, 255, 255));
    GeneroOp.setText("Género:");
    jPanel2.add(GeneroOp, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 190, -1, -1));

    jComboBox3.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jComboBox3ActionPerformed(evt);
        }
    });
    jPanel2.add(jComboBox3, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 220, 180, 40));

    Cantón.setForeground(new java.awt.Color(255, 255, 255));
    Cantón.setText("Cantón:");
    jPanel2.add(Cantón, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 300, -1, -1));

    jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
    jPanel2.add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 330, 180, 40));

    Provincias.setForeground(new java.awt.Color(255, 255, 255));
    Provincias.setText("Provincia:");
    jPanel2.add(Provincias, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 300, -1, -1));

    jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
    jPanel2.add(jComboBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 330, 190, 40));

    SobreMi.setForeground(new java.awt.Color(255, 255, 255));
    SobreMi.setText("Sobre Mí (Opcional)");
    jPanel2.add(SobreMi, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 410, -1, -1));

    Guardar.setForeground(new java.awt.Color(255, 255, 255));
    Guardar.setText("Guardar");
    Guardar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    jPanel2.add(Guardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 670, 140, 30));

    TextArea1.setColumns(20);
    TextArea1.setRows(5);
    jScrollPane2.setViewportView(TextArea1);

    jPanel2.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 460, 640, 180));

    jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 50, 700, 720));

    jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

    roundedPanelRound2.setBackground(new java.awt.Color(204, 204, 204));
    roundedPanelRound2.setRoundBottomLeft(500);
    roundedPanelRound2.setRoundBottomRight(500);
    roundedPanelRound2.setRoundTopLeft(500);
    roundedPanelRound2.setRoundTopRight(500);
    jPanel3.add(roundedPanelRound2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 170, 200, 200));

    jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/CAJAS.png"))); // NOI18N
    jPanel3.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 0, 580, 340));

    Andres.setForeground(new java.awt.Color(255, 255, 255));
    Andres.setText("Andrés");
    jPanel3.add(Andres, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 390, -1, -1));

    andres.setForeground(new java.awt.Color(255, 255, 255));
    andres.setText("andresjuca@gmail.com");
    jPanel3.add(andres, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 420, -1, -1));

    jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 90, 320, 490));

    jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

    Imagen.setForeground(new java.awt.Color(255, 255, 255));
    Imagen.setText("Selecciona una Imagen:");
    jPanel4.add(Imagen, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 40, -1, -1));

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

    jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 590, 320, 180));

    add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(-20, -20, 1140, 800));
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseClicked
    }//GEN-LAST:event_jLabel7MouseClicked

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
        int dialog = JOptionPane.YES_NO_OPTION;
        int result = JOptionPane.showConfirmDialog(null, "¿Desea salir?", "Exit", dialog);
        if (result == 0) {
            System.exit(0);
        }
    }//GEN-LAST:event_jLabel5MouseClicked

    private void jComboBox3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        JFileChooser jFileChooser = new JFileChooser();
        FileNameExtensionFilter filtrado = new FileNameExtensionFilter("JPG, PNG, GIF", "jpg", "png", "gif");
        jFileChooser.setFileFilter(filtrado);

        int respuesta = jFileChooser.showOpenDialog(this);
        if (respuesta == JFileChooser.APPROVE_OPTION) {
            try {
                File archivoSeleccionado = jFileChooser.getSelectedFile();
                FileInputStream fis = new FileInputStream(archivoSeleccionado);

                Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Prueba", "postgres", "a");

                PreparedStatement ps = conn.prepareStatement("INSERT INTO imagenes(nombre, imagen) VALUES (?, ?)");
                ps.setString(1, archivoSeleccionado.getName()); // Guarda el nombre del archivo
                ps.setBinaryStream(2, fis, (int) archivoSeleccionado.length());
                ps.executeUpdate();

                conn.close();

                // Mostrarla al instante en el JLabel
                Image mImagen = new ImageIcon(archivoSeleccionado.getAbsolutePath()).getImage()
                        .getScaledInstance(imageLabel.getWidth(), imageLabel.getHeight(), Image.SCALE_SMOOTH);
                ImageIcon mIcono = new ImageIcon(mImagen);
                imageLabel.setIcon(mIcono);

                JOptionPane.showMessageDialog(null, "Imagen guardada correctamente.");

            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error al guardar la imagen.");
            }


    }//GEN-LAST:event_jButton1ActionPerformed
    }

    private void cargarUltimaImagen() {
        if (imageLabel == null) {
            return;   // <- corte inmediato si no existe
        }
        try ( Connection conn = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/Prueba", "postgres", "a");  PreparedStatement ps = conn.prepareStatement(
                        "SELECT imagen FROM imagenes ORDER BY id DESC LIMIT 1");  ResultSet rs = ps.executeQuery()) {
            if (!rs.next()) {
                return;        // no hay datos, nada que hacer
            }
            byte[] bytes = rs.getBytes("imagen");
            if (bytes == null) {
                return;     // fila sin imagen
            }
            ImageIcon raw = new ImageIcon(bytes);

            int w = imageLabel.getWidth();
            int h = imageLabel.getHeight();
            if (w > 0 && h > 0) {
                Image scaled = raw.getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH);
                imageLabel.setIcon(new ImageIcon(scaled));
            } else {
                imageLabel.setIcon(raw);
                // reintentar luego de layout
                SwingUtilities.invokeLater(this::cargarUltimaImagen);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this,
                    "Error de base de datos al cargar la imagen:\n" + ex.getMessage(),
                    "BD Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Andres;
    private javax.swing.JLabel ApellidoOP;
    private javax.swing.JTextField ApellidoOpcional;
    private javax.swing.JLabel Cantón;
    private javax.swing.JLabel CorreoOP;
    private javax.swing.JTextField CorreoOpcional;
    private javax.swing.JLabel Fecha;
    private javax.swing.JTextField FechaLabel;
    private javax.swing.JLabel GeneroOp;
    private javax.swing.JButton Guardar;
    private javax.swing.JLabel Imagen;
    private javax.swing.JLabel Informacion;
    private javax.swing.JTextField Nombre;
    private javax.swing.JLabel NombreOp;
    private javax.swing.JTextField NombreOpcional;
    private javax.swing.JLabel Provincias;
    private javax.swing.JLabel SobreMi;
    private javax.swing.JTextArea TextArea1;
    private javax.swing.JLabel UsuarioNombre;
    private javax.swing.JLabel andres;
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel pixeles;
    private Design.RoundedPanelRound roundedPanelRound1;
    private Design.RoundedPanelRound roundedPanelRound2;
    private javax.swing.JLabel status;
    // End of variables declaration//GEN-END:variables
}
