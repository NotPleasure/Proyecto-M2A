/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Vista;

import Animations.Animator1;
import Design.RoundedButtonEliminarRe;
import Design.RoundedPanelAdmin;
import Design.RoundedPanelLateral;
import Design.RoundedText;
import Modelo.IglesiaDetalleVista;
import Modelo.MuseoDetalleVista;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashSet;
import java.util.Set;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
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
import raven.glasspanepopup.GlassPanePopup;
import Vista.Ventana_TarjetasIglesia;

/**
 *
 * @author USER
 */
public class Ventana_VerDetalleMuseos extends javax.swing.JFrame {

    private JXMapViewer mapViewer;
    private WaypointPainter<Waypoint> waypointPainter;
    private Set<Waypoint> waypoints = new HashSet<>();
    private JTextArea textArea;
    private Ventana_TarjetasMuseo ventanaTarjetas;

    /**
     * Creates new form Ventana_VerIglesias
     */
    public Ventana_VerDetalleMuseos() {
        setUndecorated(true);
        initComponents();

        //Fuentes:
        NombreIglesia.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 35));
        Longi.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 20));
        Lati.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 20));
        lblLatitud.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 20));
        Descrip.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 20));
        Horario.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 20));
        HoraApe.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 15));
        HoraCiere.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 15));
        HoraApertura.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 15));
        HoraCierre.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 15));
        lblLongitud.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 20));
        Descrip1.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 20));
        lblDireccion.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 15));

        //Extender la ventana al máximo: 
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        // Para pantalla completa:
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        //Crear el TextArea:
        this.textArea = new JTextArea();
        textArea.setBackground(new Color(224, 224, 224));
        textArea.setBorder(BorderFactory.createEmptyBorder());
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setFont(new Font("Segoe UI Light", Font.PLAIN, 16));
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setBorder(new RoundedText());
        scrollPane.setPreferredSize(new Dimension(360, 80));

        DescripcionHistorica.setLayout(new BorderLayout());
        DescripcionHistorica.add(scrollPane, BorderLayout.CENTER);
        DescripcionHistorica.revalidate();
        DescripcionHistorica.repaint();

    }

    public Ventana_VerDetalleMuseos(MuseoDetalleVista detalle) {
        this();

        NombreIglesia.setText(detalle.getNombre());
        HoraApertura.setText(detalle.getHoraApertura() != null ? detalle.getHoraApertura().toString() : "");
        HoraCierre.setText(detalle.getHoraCierre() != null ? detalle.getHoraCierre().toString() : "");
        lblLatitud.setText(String.valueOf(detalle.getLatitud()));
        lblLongitud.setText(String.valueOf(detalle.getLongitud()));
        textArea.setText(detalle.getDescripcion());
        lblDireccion.setText(detalle.getDireccion());

        if (detalle.getImagen1() != null) {
            ImageIcon icon = new ImageIcon(detalle.getImagen1());
            Image img = icon.getImage().getScaledInstance(roundedImageLabel1.getWidth(), roundedImageLabel1.getHeight(), Image.SCALE_SMOOTH);
            roundedImageLabel1.setIcon(new ImageIcon(img));
        }
        if (detalle.getImagen2() != null) {
            ImageIcon icon = new ImageIcon(detalle.getImagen2());
            Image img = icon.getImage().getScaledInstance(roundedImageLabel2.getWidth(), roundedImageLabel2.getHeight(), Image.SCALE_SMOOTH);
            roundedImageLabel2.setIcon(new ImageIcon(img));
        }
        if (detalle.getImagen3() != null) {
            ImageIcon icon = new ImageIcon(detalle.getImagen3());
            Image img = icon.getImage().getScaledInstance(roundedImageLabel3.getWidth(), roundedImageLabel3.getHeight(), Image.SCALE_SMOOTH);
            roundedImageLabel3.setIcon(new ImageIcon(img));
        }
        mapViewer = new JXMapViewer();
        mapViewer.setTileFactory(new DefaultTileFactory(new OSMTileFactoryInfo()));
        PanelMapa.setLayout(new BorderLayout());
        PanelMapa.add(mapViewer, BorderLayout.CENTER);

        // 3) Configurar pan/zoom
        MouseInputListener mia = new PanMouseInputListener(mapViewer);
        mapViewer.addMouseListener(mia);
        mapViewer.addMouseMotionListener(mia);
        mapViewer.addMouseWheelListener(new ZoomMouseWheelListenerCenter(mapViewer));

        // 4) Prepara el painter de waypoints
        waypointPainter = new WaypointPainter<>();
        mapViewer.setOverlayPainter(waypointPainter);

        // 5) Centrar el mapa y poner el marcador inicial
        actualizarMapa(detalle.getLatitud(), detalle.getLongitud());

        // 6) Capturar clicks para mover el marcador y actualizar labels
        mapViewer.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                GeoPosition pos = mapViewer.convertPointToGeoPosition(e.getPoint());
                actualizarMapa(pos.getLatitude(), pos.getLongitude());
                lblLatitud.setText(String.format("%.6f", pos.getLatitude()));
                lblLongitud.setText(String.format("%.6f", pos.getLongitude()));
            }
        });
    }

    public void setVentanaTarjetas(Ventana_TarjetasMuseo v) {
        this.ventanaTarjetas = v;
    }

    /**
     * Centra el mapa en lat/lon y dibuja un único marcador
     */
    private void actualizarMapa(double lat, double lon) {
        GeoPosition pos = new GeoPosition(lat, lon);
        mapViewer.setAddressLocation(pos);
        mapViewer.setZoom(5);

        waypoints.clear();
        waypoints.add(new DefaultWaypoint(pos));
        waypointPainter.setWaypoints(waypoints);
        mapViewer.repaint();

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
        jPanel2 = new javax.swing.JPanel();
        NombreIglesia = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        PanelMapa = new RoundedPanelAdmin()
        ;
        Longi = new javax.swing.JLabel();
        lblLongitud = new javax.swing.JLabel();
        Lati = new javax.swing.JLabel();
        lblLatitud = new javax.swing.JLabel();
        jPanel3 = new RoundedPanelAdmin()
        ;
        Descrip = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        DescripcionHistorica = new javax.swing.JPanel();
        Descrip1 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        lblDireccion = new javax.swing.JLabel();
        jPanel4 = new RoundedPanelAdmin()
        ;
        jSeparator3 = new javax.swing.JSeparator();
        Horario = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        HoraApe = new javax.swing.JLabel();
        HoraCiere = new javax.swing.JLabel();
        HoraApertura = new javax.swing.JLabel();
        HoraCierre = new javax.swing.JLabel();
        jButton2 = new RoundedButtonEliminarRe("");
        roundedImageLabel1 = new Design.Imagen.RoundedImageLabel();
        roundedImageLabel2 = new Design.Imagen.RoundedImageLabel();
        roundedImageLabel3 = new Design.Imagen.RoundedImageLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(248, 248, 248));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(217, 188, 138));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        NombreIglesia.setFont(new java.awt.Font("Segoe UI", 0, 40)); // NOI18N
        NombreIglesia.setForeground(new java.awt.Color(255, 255, 255));
        NombreIglesia.setText("d");
        jPanel2.add(NombreIglesia, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 23, 470, -1));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/ImaMuseo.png"))); // NOI18N
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 20, 70, 60));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1370, 100));

        jSeparator1.setForeground(new java.awt.Color(224, 224, 224));
        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 100, 10, 760));

        PanelMapa.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout PanelMapaLayout = new javax.swing.GroupLayout(PanelMapa);
        PanelMapa.setLayout(PanelMapaLayout);
        PanelMapaLayout.setHorizontalGroup(
            PanelMapaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 880, Short.MAX_VALUE)
        );
        PanelMapaLayout.setVerticalGroup(
            PanelMapaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        jPanel1.add(PanelMapa, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 110, 880, 300));

        Longi.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        Longi.setForeground(new java.awt.Color(119, 119, 119));
        Longi.setText("Longitud:");
        jPanel1.add(Longi, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 420, 110, 40));

        lblLongitud.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        lblLongitud.setForeground(new java.awt.Color(51, 51, 51));
        lblLongitud.setText("L");
        jPanel1.add(lblLongitud, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 430, 290, 20));

        Lati.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        Lati.setForeground(new java.awt.Color(119, 119, 119));
        Lati.setText("Latitud:");
        jPanel1.add(Lati, new org.netbeans.lib.awtextra.AbsoluteConstraints(1110, 430, 110, -1));

        lblLatitud.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        lblLatitud.setForeground(new java.awt.Color(51, 51, 51));
        lblLatitud.setText("L");
        jPanel1.add(lblLatitud, new org.netbeans.lib.awtextra.AbsoluteConstraints(1210, 430, 200, -1));

        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Descrip.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        Descrip.setForeground(new java.awt.Color(139, 0, 0));
        Descrip.setText("Dirección");
        jPanel3.add(Descrip, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, 220, -1));

        jSeparator2.setForeground(new java.awt.Color(220, 220, 220));
        jPanel3.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, 360, 30));

        javax.swing.GroupLayout DescripcionHistoricaLayout = new javax.swing.GroupLayout(DescripcionHistorica);
        DescripcionHistorica.setLayout(DescripcionHistoricaLayout);
        DescripcionHistoricaLayout.setHorizontalGroup(
            DescripcionHistoricaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 360, Short.MAX_VALUE)
        );
        DescripcionHistoricaLayout.setVerticalGroup(
            DescripcionHistoricaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        jPanel3.add(DescripcionHistorica, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 360, 40));

        Descrip1.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        Descrip1.setForeground(new java.awt.Color(139, 0, 0));
        Descrip1.setText("Descripción Histórica");
        jPanel3.add(Descrip1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 220, -1));

        jSeparator4.setForeground(new java.awt.Color(220, 220, 220));
        jPanel3.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 360, 30));

        lblDireccion.setForeground(new java.awt.Color(119, 119, 119));
        jPanel3.add(lblDireccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 210, 270, 30));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 490, 400, 260));

        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jSeparator3.setForeground(new java.awt.Color(220, 220, 220));
        jPanel4.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 370, 30));

        Horario.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        Horario.setForeground(new java.awt.Color(139, 0, 0));
        Horario.setText("Horario de Atención");
        jPanel4.add(Horario, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 220, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/Puerta 2.png"))); // NOI18N
        jPanel4.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, 80, 60));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/Puerta 1.png"))); // NOI18N
        jPanel4.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 80, 60));

        HoraApe.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        HoraApe.setForeground(new java.awt.Color(119, 119, 119));
        HoraApe.setText("Hora de Apertura:");
        jPanel4.add(HoraApe, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 96, -1, -1));

        HoraCiere.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        HoraCiere.setForeground(new java.awt.Color(119, 119, 119));
        HoraCiere.setText("Hora de Cierre:");
        jPanel4.add(HoraCiere, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 156, -1, -1));

        HoraApertura.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        HoraApertura.setForeground(new java.awt.Color(51, 51, 51));
        jPanel4.add(HoraApertura, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 94, 140, 30));

        HoraCierre.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        HoraCierre.setForeground(new java.awt.Color(51, 51, 51));
        jPanel4.add(HoraCierre, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 150, 180, 30));

        jButton2.setText("Salir");
        jButton2.setBorderPainted(false);
        jButton2.setContentAreaFilled(false);
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton2.setFocusPainted(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 210, 150, 30));

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 490, 420, 260));
        jPanel1.add(roundedImageLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, 380, 200));
        jPanel1.add(roundedImageLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 330, 380, 200));
        jPanel1.add(roundedImageLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 540, 380, 200));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1370, 860));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        Animator1.fadeOut(this, () -> {
            this.dispose(); 

            if (ventanaTarjetas != null) {
                ventanaTarjetas.setOpacity(0f);
                ventanaTarjetas.setVisible(true);
                Animator1.fadeIn(ventanaTarjetas);
            }
        });
    }//GEN-LAST:event_jButton2ActionPerformed

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
            java.util.logging.Logger.getLogger(Ventana_VerDetalleMuseos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Ventana_VerDetalleMuseos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Ventana_VerDetalleMuseos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Ventana_VerDetalleMuseos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Ventana_VerDetalleMuseos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Descrip;
    private javax.swing.JLabel Descrip1;
    private javax.swing.JPanel DescripcionHistorica;
    private javax.swing.JLabel HoraApe;
    private javax.swing.JLabel HoraApertura;
    private javax.swing.JLabel HoraCiere;
    private javax.swing.JLabel HoraCierre;
    private javax.swing.JLabel Horario;
    private javax.swing.JLabel Lati;
    private javax.swing.JLabel Longi;
    private javax.swing.JLabel NombreIglesia;
    private javax.swing.JPanel PanelMapa;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JLabel lblDireccion;
    private javax.swing.JLabel lblLatitud;
    private javax.swing.JLabel lblLongitud;
    private Design.Imagen.RoundedImageLabel roundedImageLabel1;
    private Design.Imagen.RoundedImageLabel roundedImageLabel2;
    private Design.Imagen.RoundedImageLabel roundedImageLabel3;
    // End of variables declaration//GEN-END:variables

}
