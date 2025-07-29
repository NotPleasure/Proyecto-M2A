/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Vista;

import Controlador.ControladorActualizarIglesia;
import Controlador.ControladorActualizarMuseo;
import Design.RoundedButtonAgregarImagenes;
import Design.RoundedButtonEliminarRe;
import Design.RoundedButtonInsertar;
import Design.RoundedPanelFecha;
import Design.RoundedPanelLateral;
import Design.RoundedText;
import Design.RoundedTextArea1;
import Design.RoundedTextField2;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import raven.glasspanepopup.GlassPanePopup;
import Controlador.ControladorIglesia;
import Controlador.ControladorRestaurante;
import Design.Imagen.ImagenUtils;
import Modelo.IglesiaDetalleVista;
import Modelo.MuseoDetalleVista;
import java.sql.SQLException;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

/**
 *
 * @author USER
 */
public class Ventana_RegistroRestaurante extends javax.swing.JPanel {

    private JTextArea textArea;
    private byte[] imagenBytes1;
    private byte[] imagenBytes2;
    private byte[] imagenBytes3;
    private Controlador.ControladorIglesia controlador;
    private int lugarInteresId;
    //Para pasar el método de recargar la tabla:
   // private final Ventana_AdministrarMuseos ventanaPadre;

    /**
     * Creates new form Ventana_InsertarIglesia
     */
    public Ventana_RegistroRestaurante() {
        initComponents();

        //Fuentes:
        Registro.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 30));
        Categorias1.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 20));
        NomLugar.setFont(new Font("Segoe UI Light", Font.PLAIN, 15));
        Descrip.setFont(new Font("Segoe UI Light", Font.PLAIN, 15));
        Categorias2.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 20));
        Longi.setFont(new Font("Segoe UI Light", Font.PLAIN, 15));
        Direcc1.setFont(new Font("Segoe UI Light", Font.PLAIN, 15));
        Lati.setFont(new Font("Segoe UI Light", Font.PLAIN, 15));
        Categorias3.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 20));
        Vis1.setFont(new Font("Segoe UI Light", Font.PLAIN, 15));
        Vis2.setFont(new Font("Segoe UI Light", Font.PLAIN, 15));
        Vis3.setFont(new Font("Segoe UI Light", Font.PLAIN, 15));
        Categorias4.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 20));
        HoraApe.setFont(new Font("Segoe UI Light", Font.PLAIN, 15));
        HoraCierre.setFont(new Font("Segoe UI Light", Font.PLAIN, 15));
        CapacidadNegocio.setFont(new Font("Segoe UI Light", Font.PLAIN, 16));
        DescripcionNegocio.setFont(new Font("Segoe UI Light", Font.PLAIN, 16));
        Direccion.setFont(new Font("Segoe UI Light", Font.PLAIN, 16));
        Latitud.setFont(new Font("Segoe UI Light", Font.PLAIN, 16));
        Longitud.setFont(new Font("Segoe UI Light", Font.PLAIN, 16));
        HoraApertura.setFont(new Font("Segoe UI Light", Font.PLAIN, 16));
        Horacierre.setFont(new Font("Segoe UI Light", Font.PLAIN, 16));
        Horacierre.setFont(new Font("Segoe UI Light", Font.PLAIN, 16));
        Ejem1.setFont(new Font("Segoe UI Light", Font.PLAIN, 12));
        Ejem2.setFont(new Font("Segoe UI Light", Font.PLAIN, 12));
        Ejem3.setFont(new Font("Segoe UI Light", Font.PLAIN, 12));
        Ejem4.setFont(new Font("Segoe UI Light", Font.PLAIN, 12));
        Ejem5.setFont(new Font("Segoe UI Light", Font.PLAIN, 12));
        Ejem6.setFont(new Font("Segoe UI Light", Font.PLAIN, 12));
        Ejem7.setFont(new Font("Segoe UI Light", Font.PLAIN, 12));
        Ejem8.setFont(new Font("Segoe UI Light", Font.PLAIN, 12));

        //Crear el TextArea:
        this.textArea = new JTextArea();
        textArea.setBackground(new Color(224, 224, 224));
        textArea.setBorder(BorderFactory.createEmptyBorder());
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setFont(new Font("Segoe UI Light", Font.PLAIN, 16));

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setBorder(new RoundedText());
        scrollPane.setPreferredSize(new Dimension(410, 50));

        DescripcionNegocio.setLayout(new BorderLayout());
        DescripcionNegocio.add(scrollPane, BorderLayout.CENTER);
        DescripcionNegocio.revalidate();
        DescripcionNegocio.repaint();

    }

    public void cargarDatosMuseo(MuseoDetalleVista vista) {
        this.lugarInteresId = vista.getId();

        CapacidadNegocio.setText(vista.getNombre() != null
                ? vista.getNombre()
                : "");

        HoraApertura.setText(vista.getHoraApertura() != null
                ? vista.getHoraApertura().toString()
                : "");
        if (vista.getHoraCierre() != null) {
            Horacierre.setText(vista.getHoraCierre().toString());
        } else {
            Horacierre.setText("");
        }

        Direccion.setText(vista.getDireccion() != null
                ? vista.getDireccion()
                : "");

        Latitud.setText(Double.toString(vista.getLatitud()));
        Longitud.setText(Double.toString(vista.getLongitud()));

        textArea.setText(vista.getDescripcion() != null
                ? vista.getDescripcion()
                : "");

        if (vista.getImagen1() != null) {
            imagenBytes1 = vista.getImagen1();
            roundedImageLabel4.setIcon(new ImageIcon(imagenBytes1));
        }
        if (vista.getImagen2() != null) {
            imagenBytes2 = vista.getImagen2();
            roundedImageLabel5.setIcon(new ImageIcon(imagenBytes2));
        }
        if (vista.getImagen3() != null) {
            imagenBytes3 = vista.getImagen3();
            roundedImageLabel6.setIcon(new ImageIcon(imagenBytes3));
        }
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
        jLabel6 = new javax.swing.JLabel();
        jPanel3 = new RoundedPanelLateral();
        Categorias1 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        NomLugar = new javax.swing.JLabel();
        CapacidadNegocio = new RoundedTextField2(20);
        DescripcionNegocio = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtADescripcion = new javax.swing.JTextArea();
        Descrip = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        NomLugar1 = new javax.swing.JLabel();
        NombreNegocio1 = new RoundedTextField2(20);
        HoraApe = new javax.swing.JLabel();
        Ejem4 = new javax.swing.JLabel();
        Ejem5 = new javax.swing.JLabel();
        HoraApertura = new RoundedTextField2(20);
        HoraCierre = new javax.swing.JLabel();
        Horacierre = new RoundedTextField2(20);
        Ejem7 = new javax.swing.JLabel();
        Ejem8 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jPanel2 = new RoundedPanelLateral();
        jLabel4 = new javax.swing.JLabel();
        Categorias2 = new javax.swing.JLabel();
        Longi = new javax.swing.JLabel();
        Longitud = new RoundedTextField2(20);
        Direcc1 = new javax.swing.JLabel();
        Latitud = new RoundedTextField2(20);
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        Lati = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        Direccion = new RoundedTextField2(20);
        Ejem3 = new javax.swing.JLabel();
        Ejem1 = new javax.swing.JLabel();
        Ejem2 = new javax.swing.JLabel();
        Ejem6 = new javax.swing.JLabel();
        jPanel4 = new RoundedPanelLateral();
        Categorias3 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        Vis1 = new javax.swing.JLabel();
        Vis2 = new javax.swing.JLabel();
        Vis3 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        CargarImagen = new RoundedButtonAgregarImagenes("");
        roundedImageLabel4 = new Design.Imagen.RoundedImageLabel();
        roundedImageLabel5 = new Design.Imagen.RoundedImageLabel();
        roundedImageLabel6 = new Design.Imagen.RoundedImageLabel();
        jPanel5 = new RoundedPanelLateral();
        jLabel12 = new javax.swing.JLabel();
        Categorias4 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        txtEspecialidad = new RoundedTextField2(20);
        NomLugar2 = new javax.swing.JLabel();
        txtCategoria = new RoundedTextField2(20);
        NomLugar3 = new javax.swing.JLabel();
        txtTipo_Cocina = new RoundedTextField2(20);
        NomLugar4 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        btnActualizar = new javax.swing.JLabel();
        btnCancelar = new javax.swing.JLabel();
        Cancelar = new RoundedButtonEliminarRe("");
        Guardar = new RoundedButtonInsertar("");
        jButton4 = new javax.swing.JButton();

        setBackground(new java.awt.Color(248, 249, 250));
        setPreferredSize(new java.awt.Dimension(1350, 750));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Registro.setFont(new java.awt.Font("Segoe UI", 0, 30)); // NOI18N
        Registro.setForeground(new java.awt.Color(255, 255, 255));
        Registro.setText("Registro Restaurante");
        jPanel1.add(Registro, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 28, -1, -1));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/RestauranteD.png"))); // NOI18N
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 20, 80, 70));

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1350, 100));

        jPanel3.setBackground(new java.awt.Color(248, 249, 250));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Categorias1.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        Categorias1.setForeground(new java.awt.Color(44, 62, 80));
        Categorias1.setText("Datos Generales");
        jPanel3.add(Categorias1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 16, 210, 40));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/Básico.png"))); // NOI18N
        jPanel3.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, 50, 40));

        jSeparator1.setForeground(new java.awt.Color(224, 224, 224));
        jPanel3.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 70, 410, 10));

        NomLugar.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        NomLugar.setForeground(new java.awt.Color(44, 62, 80));
        NomLugar.setText("Nombre del Negocio");
        jPanel3.add(NomLugar, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 90, -1, -1));

        CapacidadNegocio.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel3.add(CapacidadNegocio, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 320, 410, 40));

        txtADescripcion.setColumns(20);
        txtADescripcion.setRows(5);
        jScrollPane1.setViewportView(txtADescripcion);

        javax.swing.GroupLayout DescripcionNegocioLayout = new javax.swing.GroupLayout(DescripcionNegocio);
        DescripcionNegocio.setLayout(DescripcionNegocioLayout);
        DescripcionNegocioLayout.setHorizontalGroup(
            DescripcionNegocioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 440, Short.MAX_VALUE)
        );
        DescripcionNegocioLayout.setVerticalGroup(
            DescripcionNegocioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
        );

        jPanel3.add(DescripcionNegocio, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 210, 440, 70));

        Descrip.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        Descrip.setForeground(new java.awt.Color(44, 62, 80));
        Descrip.setText("Descripcion del Negocio");
        jPanel3.add(Descrip, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 190, -1, -1));

        jLabel2.setForeground(new java.awt.Color(255, 0, 0));
        jLabel2.setText("*");
        jPanel3.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 380, 70, -1));

        jLabel3.setForeground(new java.awt.Color(255, 0, 0));
        jLabel3.setText("*");
        jPanel3.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 190, 60, -1));

        NomLugar1.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        NomLugar1.setForeground(new java.awt.Color(44, 62, 80));
        NomLugar1.setText("Capacidad");
        jPanel3.add(NomLugar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 290, -1, -1));

        NombreNegocio1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel3.add(NombreNegocio1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 130, 410, 40));

        HoraApe.setForeground(new java.awt.Color(60, 63, 65));
        HoraApe.setText("Hora de Apertura");
        jPanel3.add(HoraApe, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 370, 220, 30));

        Ejem4.setForeground(new java.awt.Color(44, 62, 80));
        Ejem4.setText("09:00");
        jPanel3.add(Ejem4, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 460, -1, -1));

        Ejem5.setForeground(new java.awt.Color(127, 140, 141));
        Ejem5.setText("Ejemplo: ");
        jPanel3.add(Ejem5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 460, -1, -1));

        HoraApertura.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel3.add(HoraApertura, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 410, 290, 40));

        HoraCierre.setForeground(new java.awt.Color(60, 63, 65));
        HoraCierre.setText("Hora de Cierre:");
        jPanel3.add(HoraCierre, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 380, 230, -1));

        Horacierre.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel3.add(Horacierre, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 410, 270, 40));

        Ejem7.setForeground(new java.awt.Color(127, 140, 141));
        Ejem7.setText("Ejemplo: ");
        jPanel3.add(Ejem7, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 460, 70, -1));

        Ejem8.setForeground(new java.awt.Color(44, 62, 80));
        Ejem8.setText("12:00");
        jPanel3.add(Ejem8, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 460, 50, -1));

        jLabel13.setForeground(new java.awt.Color(255, 0, 0));
        jLabel13.setText("*");
        jPanel3.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, 70, -1));

        jLabel14.setForeground(new java.awt.Color(255, 0, 0));
        jLabel14.setText("*");
        jPanel3.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 300, 70, -1));

        jLabel15.setForeground(new java.awt.Color(255, 0, 0));
        jLabel15.setText("*");
        jPanel3.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 380, 70, -1));

        add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 110, 610, 480));

        jPanel2.setBackground(new java.awt.Color(248, 249, 250));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/Ubication.png"))); // NOI18N
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, 50, 40));

        Categorias2.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        Categorias2.setForeground(new java.awt.Color(44, 62, 80));
        Categorias2.setText("Ubicación Geográfica");
        jPanel2.add(Categorias2, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 16, 210, 40));

        Longi.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        Longi.setForeground(new java.awt.Color(44, 62, 80));
        Longi.setText("Longitud");
        jPanel2.add(Longi, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 180, -1, -1));

        Longitud.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel2.add(Longitud, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 220, 180, 40));

        Direcc1.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        Direcc1.setForeground(new java.awt.Color(44, 62, 80));
        Direcc1.setText("Dirección");
        jPanel2.add(Direcc1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 80, -1, -1));

        Latitud.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel2.add(Latitud, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 220, 180, 40));

        jLabel5.setForeground(new java.awt.Color(255, 0, 0));
        jLabel5.setText("*");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 186, -1, -1));

        jLabel7.setForeground(new java.awt.Color(255, 0, 0));
        jLabel7.setText("*");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 86, -1, -1));

        Lati.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        Lati.setForeground(new java.awt.Color(44, 62, 80));
        Lati.setText("Latitud");
        jPanel2.add(Lati, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 180, -1, -1));

        jLabel8.setForeground(new java.awt.Color(255, 0, 0));
        jLabel8.setText("*");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 186, -1, -1));

        Direccion.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel2.add(Direccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 120, 410, 40));

        Ejem3.setForeground(new java.awt.Color(127, 140, 141));
        Ejem3.setText("Ejemplo: ");
        jPanel2.add(Ejem3, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 273, -1, -1));

        Ejem1.setForeground(new java.awt.Color(127, 140, 141));
        Ejem1.setText("Ejemplo: ");
        jPanel2.add(Ejem1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 273, -1, -1));

        Ejem2.setForeground(new java.awt.Color(44, 62, 80));
        Ejem2.setText("-12.43434");
        jPanel2.add(Ejem2, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 273, -1, -1));

        Ejem6.setForeground(new java.awt.Color(44, 62, 80));
        Ejem6.setText("-13.3232");
        jPanel2.add(Ejem6, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 273, -1, -1));

        add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 460, 500, 310));

        jPanel4.setBackground(new java.awt.Color(248, 249, 250));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Categorias3.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        Categorias3.setForeground(new java.awt.Color(44, 62, 80));
        Categorias3.setText("Imágenes del Lugar");
        jPanel4.add(Categorias3, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 16, 210, 40));

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/Ima.png"))); // NOI18N
        jPanel4.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 50, 40));

        jSeparator2.setForeground(new java.awt.Color(224, 224, 224));
        jPanel4.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 70, 640, 10));

        Vis1.setForeground(new java.awt.Color(44, 62, 80));
        Vis1.setText("Vista Previa");
        jPanel4.add(Vis1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 200, -1, -1));

        Vis2.setForeground(new java.awt.Color(44, 62, 80));
        Vis2.setText("Vista Previa");
        jPanel4.add(Vis2, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 200, -1, -1));

        Vis3.setForeground(new java.awt.Color(44, 62, 80));
        Vis3.setText("Vista Previa");
        jPanel4.add(Vis3, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 200, -1, -1));

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/Ges.png"))); // NOI18N
        jPanel4.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 260, 30, 20));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Agregar Imágenes");
        jPanel4.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 258, -1, -1));

        CargarImagen.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        CargarImagen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CargarImagenActionPerformed(evt);
            }
        });
        jPanel4.add(CargarImagen, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 250, 250, 40));
        jPanel4.add(roundedImageLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 80, 170, 110));
        jPanel4.add(roundedImageLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(282, 80, 160, 110));
        jPanel4.add(roundedImageLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 80, 160, 110));

        add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 110, 740, 310));

        jPanel5.setBackground(new java.awt.Color(248, 249, 250));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/Igle.png"))); // NOI18N
        jPanel5.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 50, 40));

        Categorias4.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        Categorias4.setForeground(new java.awt.Color(44, 62, 80));
        Categorias4.setText("Información del Restaurante");
        jPanel5.add(Categorias4, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 16, 320, 40));

        jSeparator3.setForeground(new java.awt.Color(224, 224, 224));
        jPanel5.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 70, 640, 10));

        txtEspecialidad.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel5.add(txtEspecialidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 130, 410, 40));

        NomLugar2.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        NomLugar2.setForeground(new java.awt.Color(44, 62, 80));
        NomLugar2.setText("Especialidad");
        jPanel5.add(NomLugar2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 100, -1, -1));

        txtCategoria.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel5.add(txtCategoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 220, 410, 40));

        NomLugar3.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        NomLugar3.setForeground(new java.awt.Color(44, 62, 80));
        NomLugar3.setText("Categoria");
        jPanel5.add(NomLugar3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 180, -1, -1));

        txtTipo_Cocina.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel5.add(txtTipo_Cocina, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 330, 410, 40));

        NomLugar4.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        NomLugar4.setForeground(new java.awt.Color(44, 62, 80));
        NomLugar4.setText("Tipo de Cocina");
        jPanel5.add(NomLugar4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 300, -1, -1));

        jLabel16.setForeground(new java.awt.Color(255, 0, 0));
        jLabel16.setText("*");
        jPanel5.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 100, 20, -1));

        jLabel17.setForeground(new java.awt.Color(255, 0, 0));
        jLabel17.setText("*");
        jPanel5.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 180, 30, -1));

        jLabel18.setForeground(new java.awt.Color(255, 0, 0));
        jLabel18.setText("*");
        jPanel5.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 300, 20, -1));

        add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 590, 720, 380));

        btnActualizar.setBackground(new java.awt.Color(255, 102, 51));
        btnActualizar.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnActualizar.setForeground(new java.awt.Color(255, 255, 255));
        btnActualizar.setText("Insertar");
        add(btnActualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 1030, 110, 20));

        btnCancelar.setBackground(new java.awt.Color(255, 102, 51));
        btnCancelar.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnCancelar.setForeground(new java.awt.Color(255, 255, 255));
        btnCancelar.setText("Cancelar");
        add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 1040, 140, -1));

        Cancelar.setBackground(new java.awt.Color(153, 153, 153));
        Cancelar.setBorderPainted(false);
        Cancelar.setContentAreaFilled(false);
        Cancelar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Cancelar.setFocusPainted(false);
        Cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CancelarActionPerformed(evt);
            }
        });
        add(Cancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 1040, 180, 30));

        Guardar.setBackground(new java.awt.Color(153, 0, 153));
        Guardar.setBorderPainted(false);
        Guardar.setContentAreaFilled(false);
        Guardar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Guardar.setFocusPainted(false);
        Guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GuardarActionPerformed(evt);
            }
        });
        add(Guardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 1030, 180, 30));

        jButton4.setBorderPainted(false);
        jButton4.setContentAreaFilled(false);
        jButton4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton4.setFocusPainted(false);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 1040, 150, 30));
    }// </editor-fold>//GEN-END:initComponents

    private void CancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CancelarActionPerformed

        GlassPanePopup.closePopupLast();


    }//GEN-LAST:event_CancelarActionPerformed

    private void GuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GuardarActionPerformed
        try {
            if (CapacidadNegocio.getText().trim().isEmpty()
                    ||txtCategoria.getText().trim().isEmpty()
                    ||txtEspecialidad.getText().trim().isEmpty()
                    ||txtTipo_Cocina.getText().trim().isEmpty()
                    || HoraApertura.getText().trim().isEmpty()
                    || Horacierre.getText().trim().isEmpty()
                    || Direccion.getText().trim().isEmpty()
                    || Latitud.getText().trim().isEmpty()
                    || Longitud.getText().trim().isEmpty()
                    || txtADescripcion.getText().trim().isEmpty()
                    || imagenBytes1 == null
                    || imagenBytes2 == null
                    || imagenBytes3 == null) {

                JOptionPane.showMessageDialog(this,
                        "Por favor, complete todos los campos y cargue las 3 imágenes.",
                        "Campos incompletos",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            String nombre = CapacidadNegocio.getText().trim();
            String direccion = Direccion.getText().trim();
            double latitud = Double.parseDouble(Latitud.getText().trim());
            double longitud = Double.parseDouble(Longitud.getText().trim());
            String desc = txtADescripcion.getText().trim();
            int capacidad = Integer.parseInt(CapacidadNegocio.getText());
            String especialidad = txtEspecialidad.getText();
            String categoria = txtCategoria.getText();
            String tipo_cocina =txtTipo_Cocina.getText();
            LocalTime ha = LocalTime.parse(HoraApertura.getText().trim());
            LocalTime hc = LocalTime.parse(Horacierre.getText().trim());
           
            ControladorRestaurante ctrl = new ControladorRestaurante();
               ctrl.guardarRestaurante(WIDTH, nombre, desc, String.valueOf(ha),String.valueOf(hc), capacidad, especialidad, categoria, tipo_cocina, direccion, latitud, longitud, imagenBytes1, imagenBytes2, imagenBytes3);
             } catch (NumberFormatException | DateTimeParseException e) {
            JOptionPane.showMessageDialog(this,
                    "Formato inválido en número u hora.",
                    "Error de formato",
                    JOptionPane.ERROR_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this,
                    "Error de base de datos:\n" + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
           /* boolean ok = ctrl.actualizarMuseoCompleto(
                    lugarInteresId,
                    nombre,
                    direccion,
                    latitud,
                    longitud,
                    desc,
                    ha,
                    hc,
                    imagenBytes1,
                    imagenBytes2,
                    imagenBytes3
            );

            if (ok) {
                JOptionPane.showMessageDialog(this,
                        "Museo actualizado correctamente.",
                        "Éxito",
                        JOptionPane.INFORMATION_MESSAGE);
                GlassPanePopup.closePopupLast();
                ventanaPadre.recargarTablaMuseos();
            } else {
                JOptionPane.showMessageDialog(this,
                        "No se pudo actualizar el museo.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }

        } catch (NumberFormatException | DateTimeParseException e) {
            JOptionPane.showMessageDialog(this,
                    "Formato inválido en número u hora.",
                    "Error de formato",
                    JOptionPane.ERROR_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this,
                    "Error de base de datos:\n" + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }*/


    }//GEN-LAST:event_GuardarActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

    private void CargarImagenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CargarImagenActionPerformed

        int slot = 0;
        byte[] datos = null;

        if (imagenBytes1 == null) {
            slot = 1;
        } else if (imagenBytes2 == null) {
            slot = 2;
        } else if (imagenBytes3 == null) {
            slot = 3;
        } else {
            String[] opciones = {"1", "2", "3", "Cancelar"};
            String elegida = (String) JOptionPane.showInputDialog(
                    this,
                    "Ya cargaste las 3 imágenes.\n¿Cuál deseas reemplazar?",
                    "Reemplazar imagen",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    opciones,
                    opciones[0]
            );
            if (elegida == null || elegida.equals("Cancelar")) {
                return;
            }
            slot = Integer.parseInt(elegida);
        }

        datos = ImagenUtils.seleccionarYConvertirImagen(
                slot == 1 ? roundedImageLabel4
                        : slot == 2 ? roundedImageLabel5
                                : roundedImageLabel6
        );

        if (datos == null) {
            JOptionPane.showMessageDialog(
                    this,
                    "No se cargó ninguna imagen.",
                    "Carga de imagen",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        switch (slot) {
            case 1:
                imagenBytes1 = datos;
                roundedImageLabel4.setImagenDesdeBytes(datos);
                break;
            case 2:
                imagenBytes2 = datos;
                roundedImageLabel5.setImagenDesdeBytes(datos);
                break;
            case 3:
                imagenBytes3 = datos;
                roundedImageLabel6.setImagenDesdeBytes(datos);
                break;
        }


    }//GEN-LAST:event_CargarImagenActionPerformed

    private void limpiarFormulario() {
        NombreNegocio1.setText("");
        CapacidadNegocio.setText("");
        txtADescripcion.setText("");
        txtCategoria.setText("");
        txtEspecialidad.setText("");
        txtTipo_Cocina.setText("");
        HoraApertura.setText("");
        Horacierre.setText("");
        Direccion.setText("");
        Latitud.setText("");
        Longitud.setText("");
        textArea.setText("");
        imagenBytes1 = imagenBytes2 = imagenBytes3 = null;
        roundedImageLabel4.setImagenDesdeBytes(null);
        roundedImageLabel5.setImagenDesdeBytes(null);
        roundedImageLabel6.setImagenDesdeBytes(null);
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Cancelar;
    private javax.swing.JTextField CapacidadNegocio;
    private javax.swing.JButton CargarImagen;
    private javax.swing.JLabel Categorias1;
    private javax.swing.JLabel Categorias2;
    private javax.swing.JLabel Categorias3;
    private javax.swing.JLabel Categorias4;
    private javax.swing.JLabel Descrip;
    private javax.swing.JPanel DescripcionNegocio;
    private javax.swing.JLabel Direcc1;
    private javax.swing.JTextField Direccion;
    private javax.swing.JLabel Ejem1;
    private javax.swing.JLabel Ejem2;
    private javax.swing.JLabel Ejem3;
    private javax.swing.JLabel Ejem4;
    private javax.swing.JLabel Ejem5;
    private javax.swing.JLabel Ejem6;
    private javax.swing.JLabel Ejem7;
    private javax.swing.JLabel Ejem8;
    private javax.swing.JButton Guardar;
    private javax.swing.JLabel HoraApe;
    private javax.swing.JTextField HoraApertura;
    private javax.swing.JLabel HoraCierre;
    private javax.swing.JTextField Horacierre;
    private javax.swing.JLabel Lati;
    private javax.swing.JTextField Latitud;
    private javax.swing.JLabel Longi;
    private javax.swing.JTextField Longitud;
    private javax.swing.JLabel NomLugar;
    private javax.swing.JLabel NomLugar1;
    private javax.swing.JLabel NomLugar2;
    private javax.swing.JLabel NomLugar3;
    private javax.swing.JLabel NomLugar4;
    private javax.swing.JTextField NombreNegocio1;
    private javax.swing.JLabel Registro;
    private javax.swing.JLabel Vis1;
    private javax.swing.JLabel Vis2;
    private javax.swing.JLabel Vis3;
    private javax.swing.JLabel btnActualizar;
    private javax.swing.JLabel btnCancelar;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private Design.Imagen.RoundedImageLabel roundedImageLabel4;
    private Design.Imagen.RoundedImageLabel roundedImageLabel5;
    private Design.Imagen.RoundedImageLabel roundedImageLabel6;
    private javax.swing.JTextArea txtADescripcion;
    private javax.swing.JTextField txtCategoria;
    private javax.swing.JTextField txtEspecialidad;
    private javax.swing.JTextField txtTipo_Cocina;
    // End of variables declaration//GEN-END:variables
}
