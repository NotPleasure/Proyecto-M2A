/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Vista.PanelIglesias;

import Design.RoundedButton;
import Design.RoundedButtonAceptarComentario;
import Design.RoundedButtonCancelarComentario;
import java.awt.Font;
import Design.RoundedButtonLugares;
import javax.swing.ImageIcon;
import Design.RoundedPanel;
import Design.RoundedPanelCarrusel;
import Vista.AyudaMensaje;
import Vista.Ventana_Iglesias;
import Vista.Ventana_Inicio;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Frame;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import raven.glasspanepopup.GlassPanePopup;
import Design.RoundedPanel;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashSet;
import java.util.Set;
import javax.swing.JOptionPane;
import javax.swing.event.MouseInputListener;
import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.OSMTileFactoryInfo;
import org.jxmapviewer.input.PanMouseInputListener;
import org.jxmapviewer.input.ZoomMouseWheelListenerCenter;
import org.jxmapviewer.viewer.DefaultTileFactory;
import org.jxmapviewer.viewer.DefaultWaypoint;
import org.jxmapviewer.viewer.GeoPosition;
import org.jxmapviewer.viewer.Waypoint;
import org.jxmapviewer.viewer.WaypointPainter;
import Design.RoundedButtonFavoritos;
import Design.RoundedButtonComentarios;
import Design.RoundedPanel;
import Design.RoundedPanelComentarios;
import Design.RoundedTextArea;
import Design.RoundedTextField1;

/**
 *
 * @author USER
 */
public class Ventana_IglesiaElSagrario extends javax.swing.JFrame {
//Varianes para cargar las imágenes:

    /**
     * Creates new form Ventana_IglesiaElSagrario
     */
    public Ventana_IglesiaElSagrario() {

        initComponents();

        //Estrella:
        //Fuentes:
        IglesiasLabel.setFont(new Font("CocogooseProTrial", Font.PLAIN, 13));
        Cuenca_Ecuador.setFont(new Font("CocogooseProTrial", Font.PLAIN, 13));
        Descripcion.setFont(new Font("Caviar Dreams", Font.PLAIN, 18));
        Descripcion2.setFont(new Font("Caviar Dreams", Font.PLAIN, 18));
        Ubicacion.setFont(new Font("CocogooseProTrial", Font.PLAIN, 13));
        Ubicacion2.setFont(new Font("Caviar Dreams", Font.PLAIN, 15));
        Horario.setFont(new Font("CocogooseProTrial", Font.PLAIN, 13));
        Horario2.setFont(new Font("Caviar Dreams", Font.PLAIN, 15));
        Entrada.setFont(new Font("CocogooseProTrial", Font.PLAIN, 13));
        Entrada2.setFont(new Font("Caviar Dreams", Font.PLAIN, 15));
        Favoritos.setFont(new Font("Caviar Dreams Bold", Font.PLAIN, 14));
        Comentarios.setFont(new Font("Caviar Dreams Bold", Font.PLAIN, 14));
        Construcción.setFont(new Font("CocogooseProTrial", Font.PLAIN, 13));
        Construcción2.setFont(new Font("Caviar Dreams", Font.PLAIN, 15));
        Comentarios12.setFont(new Font("CocogooseProTrial", Font.PLAIN, 18));
        Antonio.setFont(new Font("Caviar Dreams Bold", Font.PLAIN, 14));
        Melo.setFont(new Font("Caviar Dreams", Font.PLAIN, 15));
        Esta.setFont(new Font("Caviar Dreams", Font.PLAIN, 15));
        Rodrigo.setFont(new Font("Caviar Dreams Bold", Font.PLAIN, 14));
        Super.setFont(new Font("Caviar Dreams", Font.PLAIN, 15));
        Para.setFont(new Font("Caviar Dreams", Font.PLAIN, 15));
        VerMas.setFont(new Font("Caviar Dreams Bold", Font.PLAIN, 14));
        ComentarSobre.setFont(new Font("CocogooseProTrial", Font.PLAIN, 25));
        ComentarSob.setFont(new Font("CocogooseProTrial", Font.PLAIN, 20));
        ElSagrado.setFont(new Font("Caviar Dreams", Font.PLAIN, 15));
        AceptarComentario.setFont(new Font("Caviar Dreams Bold", Font.PLAIN, 14));
        CancelarComentario.setFont(new Font("Caviar Dreams Bold", Font.PLAIN, 14));

        //Hacer invisible el panel inicialmente.
        roundedPanelCarrusel1.setVisible(false);
        roundedPanelComentar1.setVisible(false);

        // Crear el mapa
        JXMapViewer mapViewer = new JXMapViewer();
        mapViewer.setTileFactory(new DefaultTileFactory(new OSMTileFactoryInfo()));

        GeoPosition turi = new GeoPosition(-2.9271, -78.9942);
        mapViewer.setAddressLocation(turi);
        mapViewer.setZoom(5);
        mapViewer.setPreferredSize(new Dimension(280, 220));

// Movimiento con mouse (pan)
        MouseInputListener mia = new PanMouseInputListener(mapViewer);
        mapViewer.addMouseListener(mia);
        mapViewer.addMouseMotionListener(mia);

// Zoom con rueda del mouse
        mapViewer.addMouseWheelListener(new ZoomMouseWheelListenerCenter(mapViewer));

// Crear Waypoints (marcadores)
        Set<Waypoint> waypoints = new HashSet<>();
        waypoints.add(new DefaultWaypoint(turi));

        WaypointPainter<Waypoint> waypointPainter = new WaypointPainter<>();
        waypointPainter.setWaypoints(waypoints);
        mapViewer.setOverlayPainter(waypointPainter);

// Click en el mapa para agregar marcadores y mostrar lat/lon en consola
        mapViewer.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                GeoPosition pos = mapViewer.convertPointToGeoPosition(e.getPoint());
                waypoints.add(new DefaultWaypoint(pos));
                waypointPainter.setWaypoints(waypoints);
                mapViewer.repaint();

                // Mostrar latitud y longitud en consola
                double lat = pos.getLatitude();
                double lon = pos.getLongitude();
                System.out.println(String.format("Click en lat: %.6f, lon: %.6f", lat, lon));
            }
        });

// Mostrar el mapa dentro de jPanel1
        jPanel1.setLayout(new BorderLayout());
        jPanel1.add(mapViewer, BorderLayout.CENTER);
        jPanel1.revalidate();
        jPanel1.repaint();

        //Fuentes:
        Iglesia.setFont(new Font("CocogooseProTrial", Font.PLAIN, 26));

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        content = new javax.swing.JPanel();
        Iglesia = new javax.swing.JLabel();
        roundedPanelCarrusel1 = new Design.RoundedPanelCarrusel();
        jButton1 = new RoundedButtonLugares("");
        jButton2 = new RoundedButtonLugares("");
        btnVerCarrusel = new RoundedButtonLugares("");
        jPanel1 = new RoundedPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        IglesiasLabel = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        Cuenca_Ecuador = new javax.swing.JLabel();
        Descripcion = new javax.swing.JLabel();
        Descripcion2 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        Ubicacion = new javax.swing.JLabel();
        Ubicacion2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        Horario = new javax.swing.JLabel();
        Horario2 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        Entrada = new javax.swing.JLabel();
        Entrada2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        Favoritos = new RoundedButtonFavoritos("");
        jLabel5 = new javax.swing.JLabel();
        Comentarios = new RoundedButtonComentarios("");
        jLabel7 = new javax.swing.JLabel();
        Construcción = new javax.swing.JLabel();
        Construcción2 = new javax.swing.JLabel();
        roundedPanelRound1 = new Design.RoundedPanelRound();
        Comentarios12 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        Antonio = new javax.swing.JLabel();
        Melo = new javax.swing.JLabel();
        Esta = new javax.swing.JLabel();
        Rodrigo = new javax.swing.JLabel();
        Super = new javax.swing.JLabel();
        Para = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        VerMas = new RoundedButtonComentarios("");
        roundedPanelComentar1 = new Design.RoundedPanelComentar();
        ComentarSobre = new javax.swing.JLabel();
        ComentarSob = new javax.swing.JLabel();
        ElSagrado = new javax.swing.JLabel();
        AceptarComentario = new RoundedButtonAceptarComentario("");
        CancelarComentario = new RoundedButtonCancelarComentario("");
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea2 = new RoundedTextArea(5, 30);

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        content.setBackground(new java.awt.Color(255, 255, 255));
        content.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Iglesia.setText("Iglesia El Sagrario");
        content.add(Iglesia, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 56, -1, -1));

        roundedPanelCarrusel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/Iglesia EL SAGRARIO(3).JPG"))); // NOI18N
        jButton1.setText("jButton1");
        jButton1.setBorderPainted(false);
        jButton1.setContentAreaFilled(false);
        jButton1.setFocusPainted(false);
        roundedPanelCarrusel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 20, 750, 340));

        content.add(roundedPanelCarrusel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 120, 890, 430));

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/Iglesia ELSAGRARIO(2) (1).jpg"))); // NOI18N
        jButton2.setBorderPainted(false);
        jButton2.setContentAreaFilled(false);
        jButton2.setFocusPainted(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        content.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 110, 310, 220));

        btnVerCarrusel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/Iglesia El SAGRARIO 1 (1).jpg"))); // NOI18N
        btnVerCarrusel.setBorderPainted(false);
        btnVerCarrusel.setContentAreaFilled(false);
        btnVerCarrusel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnVerCarrusel.setFocusPainted(false);
        btnVerCarrusel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerCarruselActionPerformed(evt);
            }
        });
        content.add(btnVerCarrusel, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 110, 430, 220));

        jPanel1.setBackground(new java.awt.Color(102, 255, 0));
        content.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 110, 280, 220));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/icons8_Expand_Arrow_32px.png"))); // NOI18N
        jLabel6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel6MouseClicked(evt);
            }
        });
        content.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 20, 30, 30));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/Salir (2) (2).png"))); // NOI18N
        jLabel3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });
        content.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 16, 30, 40));

        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/iglesia (2).png"))); // NOI18N
        content.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 56, -1, -1));

        IglesiasLabel.setForeground(new java.awt.Color(255, 153, 0));
        IglesiasLabel.setText("Iglesias");
        content.add(IglesiasLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 64, -1, -1));

        jLabel21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/ubicacion (1).png"))); // NOI18N
        content.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 60, -1, -1));

        Cuenca_Ecuador.setForeground(new java.awt.Color(255, 153, 51));
        Cuenca_Ecuador.setText("Cuenca, Ecuador.");
        content.add(Cuenca_Ecuador, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 64, -1, -1));

        Descripcion.setText("Antigua catedral de Cuenca, construida en el siglo XVI. Hoy es museo de arte religioso y uno ");
        content.add(Descripcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 330, 770, 70));

        Descripcion2.setText("de los hitos históricos más importantes del  centro histórico.");
        content.add(Descripcion2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 380, -1, -1));

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/marcador-de-posicion (1).png"))); // NOI18N
        jButton3.setBorderPainted(false);
        jButton3.setContentAreaFilled(false);
        jButton3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton3.setFocusPainted(false);
        content.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 430, -1, 60));

        Ubicacion.setForeground(new java.awt.Color(0, 204, 204));
        Ubicacion.setText("Ubicación:");
        content.add(Ubicacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 450, -1, -1));

        Ubicacion2.setForeground(new java.awt.Color(51, 51, 51));
        Ubicacion2.setText("Frente al Parque el Calderón,  Cuenca.");
        content.add(Ubicacion2, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 446, -1, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/relojes-de-pared (1).png"))); // NOI18N
        content.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(28, 480, 60, 70));

        Horario.setForeground(new java.awt.Color(0, 204, 204));
        Horario.setText("Horario:");
        content.add(Horario, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 502, -1, -1));

        Horario2.setForeground(new java.awt.Color(51, 51, 51));
        Horario2.setText("Lunes a Viernes de 9:00 am a 17:00 pm.");
        content.add(Horario2, new org.netbeans.lib.awtextra.AbsoluteConstraints(208, 496, -1, -1));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/puerta-abierta (1).png"))); // NOI18N
        content.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(28, 550, 50, 40));

        Entrada.setForeground(new java.awt.Color(0, 204, 204));
        Entrada.setText("Entrada:");
        content.add(Entrada, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 554, -1, -1));

        Entrada2.setForeground(new java.awt.Color(51, 51, 51));
        Entrada2.setText("Libre/Contribución Voluntaria.");
        content.add(Entrada2, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 552, -1, -1));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/estrella-de-calificacion-medio-llena (1).png"))); // NOI18N
        content.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 690, 60, 60));

        Favoritos.setForeground(new java.awt.Color(0, 0, 0));
        Favoritos.setText("Añadir a Favoritos");
        Favoritos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        content.add(Favoritos, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 690, 330, 60));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/burbuja-de-comentarios (1).png"))); // NOI18N
        content.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 680, 50, 80));

        Comentarios.setForeground(new java.awt.Color(255, 255, 255));
        Comentarios.setText("Agregar Comentario");
        Comentarios.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Comentarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComentariosActionPerformed(evt);
            }
        });
        content.add(Comentarios, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 690, 330, 60));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/carretilla (1).png"))); // NOI18N
        content.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(28, 600, 60, 50));

        Construcción.setForeground(new java.awt.Color(0, 204, 204));
        Construcción.setText("Construcción:");
        content.add(Construcción, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 614, -1, -1));

        Construcción2.setForeground(new java.awt.Color(51, 51, 51));
        Construcción2.setText("Desde 1567.");
        content.add(Construcción2, new org.netbeans.lib.awtextra.AbsoluteConstraints(213, 610, -1, -1));

        roundedPanelRound1.setRoundBottomLeft(20);
        roundedPanelRound1.setRoundBottomRight(20);
        roundedPanelRound1.setRoundTopLeft(20);
        roundedPanelRound1.setRoundTopRight(20);
        roundedPanelRound1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Comentarios12.setForeground(new java.awt.Color(0, 0, 0));
        Comentarios12.setText("Comentarios");
        roundedPanelRound1.add(Comentarios12, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, -1, -1));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/masculino (3).png"))); // NOI18N
        roundedPanelRound1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, 40, 50));

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/masculino (4) (1).png"))); // NOI18N
        roundedPanelRound1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 50, 40, 60));

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/corazon (1).png"))); // NOI18N
        roundedPanelRound1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 50, 30, 50));

        Antonio.setForeground(new java.awt.Color(0, 0, 0));
        Antonio.setText("Antonio");
        roundedPanelRound1.add(Antonio, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 70, -1, -1));

        Melo.setForeground(new java.awt.Color(0, 0, 0));
        Melo.setText("Me lo recomendaron y");
        roundedPanelRound1.add(Melo, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 90, -1, -1));

        Esta.setForeground(new java.awt.Color(0, 0, 0));
        Esta.setText("está cool.");
        roundedPanelRound1.add(Esta, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 110, -1, -1));

        Rodrigo.setForeground(new java.awt.Color(0, 0, 0));
        Rodrigo.setText("Rodrigo");
        roundedPanelRound1.add(Rodrigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 70, -1, -1));

        Super.setForeground(new java.awt.Color(0, 0, 0));
        Super.setText("Super relajado, ideal");
        roundedPanelRound1.add(Super, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 90, -1, -1));

        Para.setForeground(new java.awt.Color(0, 0, 0));
        Para.setText("para ir.");
        roundedPanelRound1.add(Para, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 110, -1, -1));

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/corazon (1).png"))); // NOI18N
        roundedPanelRound1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 50, 30, 50));

        VerMas.setText("Ver más comentarios");
        VerMas.setBorderPainted(false);
        VerMas.setContentAreaFilled(false);
        VerMas.setFocusPainted(false);
        roundedPanelRound1.add(VerMas, new org.netbeans.lib.awtextra.AbsoluteConstraints(141, 162, 210, 30));

        content.add(roundedPanelRound1, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 440, 510, 220));

        roundedPanelComentar1.setBackground(new java.awt.Color(153, 0, 0));
        roundedPanelComentar1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ComentarSobre.setText("Comentar sobre: ");
        roundedPanelComentar1.add(ComentarSobre, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 670, 240, 30));

        ComentarSob.setText("Comentar Sobre:");
        roundedPanelComentar1.add(ComentarSob, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 270, -1));

        ElSagrado.setText("Iglesia el Sagrado");
        roundedPanelComentar1.add(ElSagrado, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, -1, -1));

        AceptarComentario.setText("Aceptar");
        roundedPanelComentar1.add(AceptarComentario, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 50, 130, 30));

        CancelarComentario.setText("Cancelar");
        roundedPanelComentar1.add(CancelarComentario, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 90, 130, 30));

        jTextArea2.setColumns(20);
        jTextArea2.setRows(5);
        jTextArea2.setBorder(null);
        jScrollPane2.setViewportView(jTextArea2);

        roundedPanelComentar1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 20, 540, 120));

        content.add(roundedPanelComentar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 590, 1160, 170));

        add(content, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1150, 1260));
    }// </editor-fold>//GEN-END:initComponents

    private void btnVerCarruselActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerCarruselActionPerformed

        roundedPanelCarrusel1.setAlpha(150);
        roundedPanelCarrusel1.setCornerRadius(100);
        roundedPanelCarrusel1.setFillColor(new Color(255, 255, 255));
        roundedPanelCarrusel1.setVisible(true);

        content.setComponentZOrder(roundedPanelCarrusel1, 0);

        content.revalidate();
        content.repaint();


    }//GEN-LAST:event_btnVerCarruselActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
     }//GEN-LAST:event_jButton2ActionPerformed

    private void jLabel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseClicked

    }//GEN-LAST:event_jLabel6MouseClicked

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        int dialog = JOptionPane.YES_NO_OPTION;
        int result = JOptionPane.showConfirmDialog(null, "¿Desea salir?", "Exit", dialog);
        if (result == 0) {
            System.exit(0);
        }
    }//GEN-LAST:event_jLabel3MouseClicked

    private void ComentariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComentariosActionPerformed

        roundedPanelComentar1.setCornerRadius(100);
        roundedPanelComentar1.setFillColor(new Color(255, 255, 255));
        roundedPanelComentar1.setVisible(true);

        content.setComponentZOrder(roundedPanelComentar1, 0);

        content.revalidate();
        content.repaint();


    }//GEN-LAST:event_ComentariosActionPerformed

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Ventana_IglesiaElSagrario ventana = new Ventana_IglesiaElSagrario();
                ventana.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
                ventana.pack();
                ventana.setLocationRelativeTo(null);
                ventana.setVisible(true);
            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AceptarComentario;
    private javax.swing.JLabel Antonio;
    private javax.swing.JButton CancelarComentario;
    private javax.swing.JLabel ComentarSob;
    private javax.swing.JLabel ComentarSobre;
    private javax.swing.JButton Comentarios;
    private javax.swing.JLabel Comentarios12;
    private javax.swing.JLabel Construcción;
    private javax.swing.JLabel Construcción2;
    private javax.swing.JLabel Cuenca_Ecuador;
    private javax.swing.JLabel Descripcion;
    private javax.swing.JLabel Descripcion2;
    private javax.swing.JLabel ElSagrado;
    private javax.swing.JLabel Entrada;
    private javax.swing.JLabel Entrada2;
    private javax.swing.JLabel Esta;
    private javax.swing.JButton Favoritos;
    private javax.swing.JLabel Horario;
    private javax.swing.JLabel Horario2;
    private javax.swing.JLabel Iglesia;
    private javax.swing.JLabel IglesiasLabel;
    private javax.swing.JLabel Melo;
    private javax.swing.JLabel Para;
    private javax.swing.JLabel Rodrigo;
    private javax.swing.JLabel Super;
    private javax.swing.JLabel Ubicacion;
    private javax.swing.JLabel Ubicacion2;
    private javax.swing.JButton VerMas;
    private javax.swing.JButton btnVerCarrusel;
    private javax.swing.JPanel content;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea2;
    private Design.RoundedPanelCarrusel roundedPanelCarrusel1;
    private Design.RoundedPanelComentar roundedPanelComentar1;
    private Design.RoundedPanelRound roundedPanelRound1;
    // End of variables declaration//GEN-END:variables
}
