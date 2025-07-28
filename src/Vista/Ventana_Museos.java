/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Animations.Animator1;
import Controlador.ControladorMuseo;
import Design.RoundedButtonDetalles;
import Design.RoundedButtonDetallesMuseos;
import Design.RoundedButtonDetallesRegresar;
import Design.RoundedButtonInicio;
import Design.RoundedButtonLugares;
import Design.RoundedButtonRegresar;
import Design.RoundedPanel;
import Design.RoundedPanel1;
import Design.RoundedPanel2;
import Design.RoundedPannelGris;
import Modelo.IglesiaVistaUser;
import Modelo.LugarInteresDAO;
import Modelo.MuseoDetalleVista;
import Modelo.MuseoVistaUser;
import Modelo.Persona;
import Vista.PanelIglesias.Ventana_IglesiaElSagrario;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import raven.glasspanepopup.GlassPanePopup;

/**
 *
 * @author USER
 */
public class Ventana_Museos extends javax.swing.JFrame {

    private final String BusquedaText = "Buscar Iglesias";
    private Persona persona;
    private Ventana_Principal ventanaPrincipal;
    private JFrame ventanaPadre;

    /**
     * Creates new form Interfaz_Lugares
     */
    public Ventana_Museos(JFrame ventanaPadre, Persona persona) {
        initComponents();
        this.ventanaPadre = ventanaPadre;
        this.persona = persona;

        //Cargar los museos:
        cargarMuseosEnComponentes();

        //Instanciar el popup: 
        GlassPanePopup.install(this);

        //Fuentes:
        lblMuseoNombre3.setFont(new Font("CocogooseProTrial", Font.PLAIN, 15));
        lblMuseoTipo3.setFont(new Font("Caviar Dreams", Font.PLAIN, 13));
        lblMuseoDireccion5.setFont(new Font("Caviar Dreams", Font.PLAIN, 13));
        lblMuseoNombre5.setFont(new Font("CocogooseProTrial", Font.PLAIN, 15));
        lblMuseoTipo5.setFont(new Font("Caviar Dreams", Font.PLAIN, 15));
        lblMuseoDireccion3.setFont(new Font("Caviar Dreams", Font.PLAIN, 13));
        lblMuseoNombre1.setFont(new Font("CocogooseProTrial", Font.PLAIN, 15));
        lblMuseoTipo1.setFont(new Font("Caviar Dreams", Font.PLAIN, 13));
        lblMuseoDireccion1.setFont(new Font("Caviar Dreams", Font.PLAIN, 13));
        lblMuseoNombre4.setFont(new Font("CocogooseProTrial", Font.PLAIN, 15));
        lblMuseoTipo4.setFont(new Font("Caviar Dreams", Font.PLAIN, 15));
        lblMuseoDireccion4.setFont(new Font("Caviar Dreams", Font.PLAIN, 13));
        lblMuseoNombre2.setFont(new Font("CocogooseProTrial", Font.PLAIN, 15));
        lblMuseoTipo2.setFont(new Font("Caviar Dreams", Font.PLAIN, 13));
        lblMuseoDireccion2.setFont(new Font("Caviar Dreams", Font.PLAIN, 13));
        Iglesis.setFont(new Font("Open Sans Bold", Font.PLAIN, 30));
        Des.setFont(new Font("Segoe UI SemiBold", Font.PLAIN, 16));
        Cu.setFont(new Font("Segoe UI Light", Font.PLAIN, 16));

        //Extender la ventana al máximo: 
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Para pantalla completa:
        setExtendedState(MAXIMIZED_BOTH);

    }

    private void ponerPlaceholder(JTextField campo, String textoPorDefecto) {
        campo.setText(textoPorDefecto);
        campo.setForeground(Color.GRAY);

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
                    campo.setForeground(Color.GRAY);
                    campo.setText(textoPorDefecto);
                }
            }
        });
    }

    //Cargar museos para User:
    private void cargarMuseosEnComponentes() {
        LugarInteresDAO dao = new LugarInteresDAO();
        List<MuseoVistaUser> museos = dao.obtenerMuseos();

        JLabel[] labelsNombre = {lblMuseoNombre1, lblMuseoNombre2, lblMuseoNombre3, lblMuseoNombre4, lblMuseoNombre5};
        JLabel[] labelsTipo = {lblMuseoTipo1, lblMuseoTipo2, lblMuseoTipo2, lblMuseoTipo4, lblMuseoTipo5};
        JLabel[] labelsDireccion = {lblMuseoDireccion1, lblMuseoDireccion2, lblMuseoDireccion3, lblMuseoDireccion4, lblMuseoDireccion5};
        JButton[] botonesImagen = {btnMuseoImg1, btnMuseoImg2, btnMuseoImg3, btnMuseoImg4, btnMuseoImg5};
        JButton[] botonesVer = {btnVerMuseo1, btnVerMuseo2, btnVerMuseo3, btnVerMuseo4, btnVerMuseo5};

        for (JButton b : botonesVer) {
            for (ActionListener al : b.getActionListeners()) {
                b.removeActionListener(al);
            }
        }

        for (int i = 0; i < museos.size(); i++) {
            MuseoVistaUser mu = museos.get(i);

            String dir = mu.getDireccion();
            if (dir != null && dir.length() > 15) {
                dir = dir.substring(0, 15) + "...";
            }
            labelsDireccion[i].setText(dir != null ? dir : "");

            labelsNombre[i].setText(mu.getNombre());
            labelsTipo[i].setText("Museo");

            byte[] imgBytes = mu.getImagenPrincipal();
            if (imgBytes != null) {
                ImageIcon icon = new ImageIcon(imgBytes);
                Image imgEscalada = icon.getImage().getScaledInstance(340, 170, Image.SCALE_SMOOTH);
                botonesImagen[i].setIcon(new ImageIcon(imgEscalada));
            }

            final int idMuseo = mu.getId();
            botonesVer[i].addActionListener(e -> {
                try {
                    ControladorMuseo ctrl = new ControladorMuseo();
                    MuseoDetalleVista detalle = ctrl.obtenerDetalleMuseo(idMuseo);
                    if (detalle != null) {
                        Ventana_VerDetalleMuseos ventana = new Ventana_VerDetalleMuseos(detalle);
                        ventana.setLocationRelativeTo(this);
                        ventana.setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(this,
                                "No se encontraron los datos completos.",
                                "Sin datos",
                                JOptionPane.WARNING_MESSAGE);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(this,
                            "Error al mostrar el museo:\n" + ex.getMessage(),
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            });
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

        content = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        btnVerMuseo1 = new RoundedButtonDetalles("");
        jLabel19 = new javax.swing.JLabel();
        lblMuseoTipo1 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        lblMuseoDireccion1 = new javax.swing.JLabel();
        btnMuseoImg3 = new RoundedButtonLugares("");
        btnMuseoImg2 = new RoundedButtonLugares("");
        btnMuseoImg1 = new RoundedButtonLugares("");
        lblMuseoNombre1 = new javax.swing.JLabel();
        lblMuseoNombre2 = new javax.swing.JLabel();
        lblMuseoNombre3 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        lblMuseoTipo3 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        lblMuseoDireccion3 = new javax.swing.JLabel();
        lblMuseoTipo2 = new javax.swing.JLabel();
        lblMuseoDireccion2 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        btnMuseoImg4 = new RoundedButtonLugares("");
        btnMuseoImg5 = new RoundedButtonLugares("");
        btnVerMuseo3 = new RoundedButtonDetalles("");
        lblMuseoNombre4 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        lblMuseoTipo4 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        lblMuseoDireccion4 = new javax.swing.JLabel();
        lblMuseoNombre5 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        lblMuseoTipo5 = new javax.swing.JLabel();
        lblMuseoDireccion5 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        btnVerMuseo4 = new RoundedButtonDetalles("");
        btnVerMuseo2 = new RoundedButtonDetalles("");
        btnVerMuseo5 = new RoundedButtonDetalles("");
        jLabel6 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel2 = new RoundedPanel2();
        jPanel3 = new RoundedPanel2();
        jPanel4 = new RoundedPanel2();
        jPanel5 = new RoundedPanel2();
        jPanel6 = new RoundedPanel2();
        Iglesis = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jButton1 = new RoundedButtonDetalles("");
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton2 = new RoundedButtonDetallesRegresar("");
        Des = new javax.swing.JLabel();
        Cu = new javax.swing.JLabel();
        jButton3 = new RoundedButtonDetallesMuseos("");
        jLabel4 = new javax.swing.JLabel();

        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        content.setBackground(new java.awt.Color(242, 242, 242));
        content.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(245, 245, 245));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnVerMuseo1.setBackground(new java.awt.Color(204, 204, 204));
        btnVerMuseo1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnVerMuseo1.setForeground(new java.awt.Color(255, 255, 255));
        btnVerMuseo1.setText("Ver Detalles");
        btnVerMuseo1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnVerMuseo1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerMuseo1ActionPerformed(evt);
            }
        });
        jPanel1.add(btnVerMuseo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 350, -1, -1));

        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/museo-britanico (2).png"))); // NOI18N
        jPanel1.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 320, -1, -1));

        lblMuseoTipo1.setText("Iglesias");
        jPanel1.add(lblMuseoTipo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 322, -1, -1));

        jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/ubicacion (1).png"))); // NOI18N
        jPanel1.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 320, -1, -1));

        lblMuseoDireccion1.setText("Cuenca, Ecuador.");
        jPanel1.add(lblMuseoDireccion1, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 322, -1, -1));

        btnMuseoImg3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/IglesiaSanFrancisco (1).jpg"))); // NOI18N
        btnMuseoImg3.setBorderPainted(false);
        btnMuseoImg3.setContentAreaFilled(false);
        btnMuseoImg3.setFocusPainted(false);
        jPanel1.add(btnMuseoImg3, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 110, 310, 180));

        btnMuseoImg2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/IglesiaSanBlas (1).jpg"))); // NOI18N
        btnMuseoImg2.setBorderPainted(false);
        btnMuseoImg2.setContentAreaFilled(false);
        btnMuseoImg2.setFocusPainted(false);
        jPanel1.add(btnMuseoImg2, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 110, 310, 180));

        btnMuseoImg1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/Catedral Vieja (1).jpg"))); // NOI18N
        btnMuseoImg1.setBorderPainted(false);
        btnMuseoImg1.setContentAreaFilled(false);
        btnMuseoImg1.setFocusPainted(false);
        btnMuseoImg1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMuseoImg1ActionPerformed(evt);
            }
        });
        jPanel1.add(btnMuseoImg1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 110, 340, 180));

        lblMuseoNombre1.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        lblMuseoNombre1.setForeground(new java.awt.Color(237, 79, 31));
        lblMuseoNombre1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblMuseoNombre1.setText("Iglesia el Sagrario");
        jPanel1.add(lblMuseoNombre1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 300, -1, -1));

        lblMuseoNombre2.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        lblMuseoNombre2.setForeground(new java.awt.Color(237, 79, 31));
        lblMuseoNombre2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblMuseoNombre2.setText("Iglesia San");
        jPanel1.add(lblMuseoNombre2, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 300, -1, -1));

        lblMuseoNombre3.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        lblMuseoNombre3.setForeground(new java.awt.Color(237, 79, 31));
        lblMuseoNombre3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblMuseoNombre3.setText("Iglesia San");
        jPanel1.add(lblMuseoNombre3, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 300, -1, -1));

        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/museo-britanico (2).png"))); // NOI18N
        jPanel1.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 320, -1, -1));

        lblMuseoTipo3.setText("Iglesias");
        jPanel1.add(lblMuseoTipo3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 322, -1, -1));

        jLabel21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/ubicacion (1).png"))); // NOI18N
        jPanel1.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(1080, 320, -1, -1));

        lblMuseoDireccion3.setText("Cuenca, Ecuador.");
        jPanel1.add(lblMuseoDireccion3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1110, 322, -1, -1));

        lblMuseoTipo2.setText("Iglesias");
        jPanel1.add(lblMuseoTipo2, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 322, -1, -1));

        lblMuseoDireccion2.setText("Cuenca, Ecuador.");
        jPanel1.add(lblMuseoDireccion2, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 322, -1, -1));

        jLabel26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/ubicacion (1).png"))); // NOI18N
        jPanel1.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 320, -1, -1));

        jLabel25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/museo-britanico (2).png"))); // NOI18N
        jPanel1.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 320, -1, -1));

        btnMuseoImg4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/Iglesia SanSebastián (1).jpg"))); // NOI18N
        btnMuseoImg4.setBorderPainted(false);
        btnMuseoImg4.setContentAreaFilled(false);
        btnMuseoImg4.setFocusPainted(false);
        jPanel1.add(btnMuseoImg4, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 430, 340, 170));

        btnMuseoImg5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/IglesiaTodosSantos (1).jpg"))); // NOI18N
        btnMuseoImg5.setBorderPainted(false);
        btnMuseoImg5.setContentAreaFilled(false);
        btnMuseoImg5.setFocusPainted(false);
        jPanel1.add(btnMuseoImg5, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 430, 310, 172));

        btnVerMuseo3.setBackground(new java.awt.Color(204, 204, 204));
        btnVerMuseo3.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnVerMuseo3.setForeground(new java.awt.Color(255, 255, 255));
        btnVerMuseo3.setText("Ver Detalles");
        btnVerMuseo3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnVerMuseo3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerMuseo3ActionPerformed(evt);
            }
        });
        jPanel1.add(btnVerMuseo3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 350, -1, -1));

        lblMuseoNombre4.setForeground(new java.awt.Color(237, 79, 31));
        lblMuseoNombre4.setText("Iglesia San");
        jPanel1.add(lblMuseoNombre4, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 610, -1, -1));

        jLabel23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/museo-britanico (2).png"))); // NOI18N
        jPanel1.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 630, -1, -1));

        lblMuseoTipo4.setText("Iglesias");
        jPanel1.add(lblMuseoTipo4, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 632, -1, -1));

        jLabel24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/ubicacion (1).png"))); // NOI18N
        jPanel1.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 630, -1, -1));

        lblMuseoDireccion4.setText("Cuenca, Ecuador.");
        jPanel1.add(lblMuseoDireccion4, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 632, -1, -1));

        lblMuseoNombre5.setForeground(new java.awt.Color(237, 79, 31));
        lblMuseoNombre5.setText("Iglesia de Todos");
        jPanel1.add(lblMuseoNombre5, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 610, -1, -1));

        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/museo-britanico (2).png"))); // NOI18N
        jPanel1.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 630, -1, -1));

        lblMuseoTipo5.setText("Iglesias");
        jPanel1.add(lblMuseoTipo5, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 632, -1, -1));

        lblMuseoDireccion5.setText("Cuenca, Ecuador.");
        jPanel1.add(lblMuseoDireccion5, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 632, -1, -1));

        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/ubicacion (1).png"))); // NOI18N
        jPanel1.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 630, -1, -1));

        btnVerMuseo4.setBackground(new java.awt.Color(204, 204, 204));
        btnVerMuseo4.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnVerMuseo4.setForeground(new java.awt.Color(255, 255, 255));
        btnVerMuseo4.setText("Ver Detalles");
        btnVerMuseo4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel1.add(btnVerMuseo4, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 660, -1, -1));

        btnVerMuseo2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnVerMuseo2.setForeground(new java.awt.Color(255, 255, 255));
        btnVerMuseo2.setText("Ver Detalles");
        btnVerMuseo2.setBorderPainted(false);
        btnVerMuseo2.setContentAreaFilled(false);
        btnVerMuseo2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnVerMuseo2.setFocusPainted(false);
        jPanel1.add(btnVerMuseo2, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 350, 130, -1));

        btnVerMuseo5.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnVerMuseo5.setForeground(new java.awt.Color(255, 255, 255));
        btnVerMuseo5.setText("Ver Detalles");
        btnVerMuseo5.setBorderPainted(false);
        btnVerMuseo5.setContentAreaFilled(false);
        btnVerMuseo5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnVerMuseo5.setFocusPainted(false);
        jPanel1.add(btnVerMuseo5, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 660, 130, -1));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/icons8_Expand_Arrow_32px.png"))); // NOI18N
        jLabel6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel6MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(1290, 4, 30, 30));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/Salir (2) (2).png"))); // NOI18N
        jLabel3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1330, 0, 30, 40));
        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 100, 380, 290));
        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 100, 370, 290));
        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 100, 350, 290));
        jPanel1.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 420, 380, 290));
        jPanel1.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 420, 330, 290));

        Iglesis.setFont(new java.awt.Font("Segoe UI", 0, 30)); // NOI18N
        Iglesis.setForeground(new java.awt.Color(42, 76, 125));
        Iglesis.setText("Museos");
        jPanel1.add(Iglesis, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 160, -1));

        jSeparator1.setForeground(new java.awt.Color(42, 76, 125));
        jPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, 270, 10));

        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton1.setText("Regresar");
        jButton1.setBorderPainted(false);
        jButton1.setContentAreaFilled(false);
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.setFocusPainted(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 690, 170, 40));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Agregar un museo.");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1190, 708, -1, -1));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/mas.png"))); // NOI18N
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1160, 710, 20, 20));

        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1150, 700, 190, 40));

        Des.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        Des.setForeground(new java.awt.Color(94, 88, 86));
        Des.setText("Descubre las colecciones más impresionantes y ");
        jPanel1.add(Des, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 30, 630, -1));

        Cu.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        Cu.setForeground(new java.awt.Color(94, 88, 86));
        Cu.setText("las exposiciones temporales de los principales museos de Cuenca.");
        jPanel1.add(Cu, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 50, 610, -1));

        jButton3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Ver más museos.");
        jButton3.setBorderPainted(false);
        jButton3.setContentAreaFilled(false);
        jButton3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton3.setFocusPainted(false);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1075, 50, 150, -1));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/Fondo Museo.png"))); // NOI18N
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1380, 850));

        content.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1380, 870));

        getContentPane().add(content, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1380, 850));
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseClicked

        this.setState(Ventana_Principal.ICONIFIED);

    }//GEN-LAST:event_jLabel6MouseClicked

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        int dialog = JOptionPane.YES_NO_OPTION;
        int result = JOptionPane.showConfirmDialog(null, "¿Desea salir?", "Exit", dialog);
        if (result == 0) {
            System.exit(0);
        }

    }//GEN-LAST:event_jLabel3MouseClicked

    private void btnVerMuseo3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerMuseo3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnVerMuseo3ActionPerformed

    private void btnMuseoImg1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMuseoImg1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnMuseoImg1ActionPerformed

    private void btnVerMuseo1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerMuseo1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnVerMuseo1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

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


    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed

        Ventana_TarjetasMuseo ventanaTarjetas = new Ventana_TarjetasMuseo();

        ventanaTarjetas.setVentanaTarjetas(this);

        ventanaTarjetas.setLocationRelativeTo(this);
        ventanaTarjetas.setVisible(true);

        this.setVisible(false);


    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        Ventana_InsertarMuseo ayudaPanel = new Ventana_InsertarMuseo();

        GlassPanePopup.showPopup(ayudaPanel);

    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Cu;
    private javax.swing.JLabel Des;
    private javax.swing.JLabel Iglesis;
    private javax.swing.JButton btnMuseoImg1;
    private javax.swing.JButton btnMuseoImg2;
    private javax.swing.JButton btnMuseoImg3;
    private javax.swing.JButton btnMuseoImg4;
    private javax.swing.JButton btnMuseoImg5;
    private javax.swing.JButton btnVerMuseo1;
    private javax.swing.JButton btnVerMuseo2;
    private javax.swing.JButton btnVerMuseo3;
    private javax.swing.JButton btnVerMuseo4;
    private javax.swing.JButton btnVerMuseo5;
    private javax.swing.JPanel content;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblMuseoDireccion1;
    private javax.swing.JLabel lblMuseoDireccion2;
    private javax.swing.JLabel lblMuseoDireccion3;
    private javax.swing.JLabel lblMuseoDireccion4;
    private javax.swing.JLabel lblMuseoDireccion5;
    private javax.swing.JLabel lblMuseoNombre1;
    private javax.swing.JLabel lblMuseoNombre2;
    private javax.swing.JLabel lblMuseoNombre3;
    private javax.swing.JLabel lblMuseoNombre4;
    private javax.swing.JLabel lblMuseoNombre5;
    private javax.swing.JLabel lblMuseoTipo1;
    private javax.swing.JLabel lblMuseoTipo2;
    private javax.swing.JLabel lblMuseoTipo3;
    private javax.swing.JLabel lblMuseoTipo4;
    private javax.swing.JLabel lblMuseoTipo5;
    // End of variables declaration//GEN-END:variables
}
