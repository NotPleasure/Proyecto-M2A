/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Animations.Animator1;
import Controlador.ControladorIglesia;
import Design.RoundedButtonDetalles;
import Design.RoundedButtonDetallesIglesia;
import Design.RoundedButtonDetallesRegresar;
import Design.RoundedButtonInicio;
import Design.RoundedButtonLugares;
import Design.RoundedButtonRegresar;
import Design.RoundedPanel;
import Design.RoundedPanel1;
import Design.RoundedPannelGris;
import Modelo.IglesiaDetalleVista;
import Modelo.IglesiaVistaUser;
import Modelo.LugarInteresDAO;
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
public class Ventana_Iglesias extends javax.swing.JFrame {

    private final String BusquedaText = "Buscar Iglesias";
    private Persona persona;
    private Ventana_Principal ventanaPrincipal;
    private JFrame ventanaPadre;

    /**
     * Creates new form Interfaz_Lugares
     */
    public Ventana_Iglesias(JFrame ventanaPadre, Persona persona) {
        initComponents();
        this.ventanaPadre = ventanaPadre;
        this.persona = persona;

        //Cargar las iglesias:
        cargarIglesiasEnComponentes();

        //Instanciar el popup: 
        GlassPanePopup.install(this);

        //Fuentes:
        lblNombre3.setFont(new Font("CocogooseProTrial", Font.PLAIN, 15));
        lblTipo3.setFont(new Font("Caviar Dreams", Font.PLAIN, 13));
        lblDireccion5.setFont(new Font("Caviar Dreams", Font.PLAIN, 13));
        lblNombre5.setFont(new Font("CocogooseProTrial", Font.PLAIN, 15));
        lblTipo5.setFont(new Font("Caviar Dreams", Font.PLAIN, 15));
        lblDireccion3.setFont(new Font("Caviar Dreams", Font.PLAIN, 13));
        lblNombre1.setFont(new Font("CocogooseProTrial", Font.PLAIN, 15));
        lblTipo1.setFont(new Font("Caviar Dreams", Font.PLAIN, 13));
        lblDireccion1.setFont(new Font("Caviar Dreams", Font.PLAIN, 13));
        lblNombre4.setFont(new Font("CocogooseProTrial", Font.PLAIN, 15));
        lblTipo4.setFont(new Font("Caviar Dreams", Font.PLAIN, 15));
        lblDireccion4.setFont(new Font("Caviar Dreams", Font.PLAIN, 13));
        lblNombre2.setFont(new Font("CocogooseProTrial", Font.PLAIN, 15));
        lblTipo2.setFont(new Font("Caviar Dreams", Font.PLAIN, 13));
        lblDireccion2.setFont(new Font("Caviar Dreams", Font.PLAIN, 13));
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

    //Cargar iglesias para User:
    private void cargarIglesiasEnComponentes() {
        LugarInteresDAO dao = new LugarInteresDAO();
        List<IglesiaVistaUser> iglesias = dao.obtenerIglesias();

        JLabel[] labelsNombre = {lblNombre1, lblNombre2, lblNombre3, lblNombre4, lblNombre5};
        JLabel[] labelsTipo = {lblTipo1, lblTipo2, lblTipo3, lblTipo4, lblTipo5};
        JLabel[] labelsDireccion = {lblDireccion1, lblDireccion2, lblDireccion3, lblDireccion4, lblDireccion5};
        JButton[] botonesImagen = {btnImg1, btnImg2, btnImg3, btnImg4, btnImg5};
        JButton[] botonesVer = {btnVer1, btnVer2, btnVer3, btnVer4, btnVer5};

        for (JButton b : botonesVer) {
            for (ActionListener al : b.getActionListeners()) {
                b.removeActionListener(al);
            }
        }

        for (int i = 0; i < iglesias.size(); i++) {
            IglesiaVistaUser ig = iglesias.get(i);
            String dir = ig.getDireccion();
            if (dir != null && dir.length() > 15) {
                dir = dir.substring(0, 15) + "...";
            }
            labelsDireccion[i].setText(dir != null ? dir : "");
            labelsNombre[i].setText(ig.getNombre());
            labelsTipo[i].setText("Iglesia");

            byte[] imgBytes = ig.getImagenPrincipal();
            if (imgBytes != null) {
                ImageIcon icon = new ImageIcon(imgBytes);
                Image imgEscalada = icon.getImage().getScaledInstance(310, 180, Image.SCALE_SMOOTH);
                botonesImagen[i].setIcon(new ImageIcon(imgEscalada));
            }

            final int idIglesia = ig.getId();
            botonesVer[i].addActionListener(e -> {
                try {
                    ControladorIglesia ctrl = new ControladorIglesia();
                    IglesiaDetalleVista detalle = ctrl.obtenerDetalleIglesia(idIglesia);
                    if (detalle != null) {
                        Ventana_VerIglesias ventana = new Ventana_VerIglesias(detalle);
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
                            "Error al mostrar la iglesia:\n" + ex.getMessage(),
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
        btnVer1 = new RoundedButtonDetalles("");
        jLabel19 = new javax.swing.JLabel();
        lblTipo1 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        lblDireccion1 = new javax.swing.JLabel();
        btnImg3 = new RoundedButtonLugares("");
        btnImg2 = new RoundedButtonLugares("");
        btnImg1 = new RoundedButtonLugares("");
        lblNombre1 = new javax.swing.JLabel();
        lblNombre2 = new javax.swing.JLabel();
        lblNombre3 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        lblTipo3 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        lblDireccion3 = new javax.swing.JLabel();
        lblTipo2 = new javax.swing.JLabel();
        lblDireccion2 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        btnImg4 = new RoundedButtonLugares("");
        btnImg5 = new RoundedButtonLugares("");
        btnVer3 = new RoundedButtonDetalles("");
        lblNombre4 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        lblTipo4 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        lblDireccion4 = new javax.swing.JLabel();
        lblNombre5 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        lblTipo5 = new javax.swing.JLabel();
        lblDireccion5 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        btnVer4 = new RoundedButtonDetalles("");
        btnVer2 = new RoundedButtonDetalles("");
        btnVer5 = new RoundedButtonDetalles("");
        jLabel6 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel2 = new RoundedPanel1();
        jPanel3 = new RoundedPanel1();
        jPanel4 = new RoundedPanel1();
        jPanel5 = new RoundedPanel1();
        jPanel6 = new RoundedPanel1();
        Iglesis = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jButton1 = new RoundedButtonDetalles("");
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton2 = new RoundedButtonDetallesRegresar("");
        Des = new javax.swing.JLabel();
        Cu = new javax.swing.JLabel();
        btnVerTodasIglesias = new RoundedButtonDetallesIglesia("");
        jLabel4 = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(1140, 830));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        content.setBackground(new java.awt.Color(242, 242, 242));
        content.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(245, 245, 245));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnVer1.setBackground(new java.awt.Color(204, 204, 204));
        btnVer1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnVer1.setForeground(new java.awt.Color(255, 255, 255));
        btnVer1.setText("Ver Detalles");
        btnVer1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnVer1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVer1ActionPerformed(evt);
            }
        });
        jPanel1.add(btnVer1, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 350, -1, -1));

        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/iglesia (2).png"))); // NOI18N
        jPanel1.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 320, -1, -1));

        lblTipo1.setText("Iglesias");
        jPanel1.add(lblTipo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 322, -1, -1));

        jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/ubicacion (1).png"))); // NOI18N
        jPanel1.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 320, -1, -1));

        lblDireccion1.setText("Cuenca, Ecuador.");
        jPanel1.add(lblDireccion1, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 322, -1, -1));

        btnImg3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/IglesiaSanFrancisco (1).jpg"))); // NOI18N
        btnImg3.setBorderPainted(false);
        btnImg3.setContentAreaFilled(false);
        btnImg3.setFocusPainted(false);
        jPanel1.add(btnImg3, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 110, 310, 180));

        btnImg2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/IglesiaSanBlas (1).jpg"))); // NOI18N
        btnImg2.setBorderPainted(false);
        btnImg2.setContentAreaFilled(false);
        btnImg2.setFocusPainted(false);
        jPanel1.add(btnImg2, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 110, 310, 180));

        btnImg1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/Catedral Vieja (1).jpg"))); // NOI18N
        btnImg1.setBorderPainted(false);
        btnImg1.setContentAreaFilled(false);
        btnImg1.setFocusPainted(false);
        btnImg1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImg1ActionPerformed(evt);
            }
        });
        jPanel1.add(btnImg1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 110, 340, 180));

        lblNombre1.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        lblNombre1.setForeground(new java.awt.Color(237, 79, 31));
        lblNombre1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNombre1.setText("Iglesia el Sagrario");
        jPanel1.add(lblNombre1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 300, -1, -1));

        lblNombre2.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        lblNombre2.setForeground(new java.awt.Color(237, 79, 31));
        lblNombre2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNombre2.setText("Iglesia San");
        jPanel1.add(lblNombre2, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 300, -1, -1));

        lblNombre3.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        lblNombre3.setForeground(new java.awt.Color(237, 79, 31));
        lblNombre3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNombre3.setText("Iglesia San");
        jPanel1.add(lblNombre3, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 300, -1, -1));

        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/iglesia (2).png"))); // NOI18N
        jPanel1.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 320, -1, -1));

        lblTipo3.setText("Iglesias");
        jPanel1.add(lblTipo3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 322, -1, -1));

        jLabel21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/ubicacion (1).png"))); // NOI18N
        jPanel1.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(1080, 320, -1, -1));

        lblDireccion3.setText("Cuenca, Ecuador.");
        jPanel1.add(lblDireccion3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1110, 322, -1, -1));

        lblTipo2.setText("Iglesias");
        jPanel1.add(lblTipo2, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 322, -1, -1));

        lblDireccion2.setText("Cuenca, Ecuador.");
        jPanel1.add(lblDireccion2, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 322, -1, -1));

        jLabel26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/ubicacion (1).png"))); // NOI18N
        jPanel1.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 320, -1, -1));

        jLabel25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/iglesia (2).png"))); // NOI18N
        jPanel1.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 320, -1, -1));

        btnImg4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/Iglesia SanSebastián (1).jpg"))); // NOI18N
        btnImg4.setBorderPainted(false);
        btnImg4.setContentAreaFilled(false);
        btnImg4.setFocusPainted(false);
        jPanel1.add(btnImg4, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 430, 340, 170));

        btnImg5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/IglesiaTodosSantos (1).jpg"))); // NOI18N
        btnImg5.setBorderPainted(false);
        btnImg5.setContentAreaFilled(false);
        btnImg5.setFocusPainted(false);
        jPanel1.add(btnImg5, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 430, 310, 172));

        btnVer3.setBackground(new java.awt.Color(204, 204, 204));
        btnVer3.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnVer3.setForeground(new java.awt.Color(255, 255, 255));
        btnVer3.setText("Ver Detalles");
        btnVer3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnVer3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVer3ActionPerformed(evt);
            }
        });
        jPanel1.add(btnVer3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 350, -1, -1));

        lblNombre4.setForeground(new java.awt.Color(237, 79, 31));
        lblNombre4.setText("Iglesia San");
        jPanel1.add(lblNombre4, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 610, -1, -1));

        jLabel23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/iglesia (2).png"))); // NOI18N
        jPanel1.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 630, -1, -1));

        lblTipo4.setText("Iglesias");
        jPanel1.add(lblTipo4, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 632, -1, -1));

        jLabel24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/ubicacion (1).png"))); // NOI18N
        jPanel1.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 630, -1, -1));

        lblDireccion4.setText("Cuenca, Ecuador.");
        jPanel1.add(lblDireccion4, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 632, -1, -1));

        lblNombre5.setForeground(new java.awt.Color(237, 79, 31));
        lblNombre5.setText("Iglesia de Todos");
        jPanel1.add(lblNombre5, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 610, -1, -1));

        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/iglesia (2).png"))); // NOI18N
        jPanel1.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 630, -1, -1));

        lblTipo5.setText("Iglesias");
        jPanel1.add(lblTipo5, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 632, -1, -1));

        lblDireccion5.setText("Cuenca, Ecuador.");
        jPanel1.add(lblDireccion5, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 632, -1, -1));

        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/ubicacion (1).png"))); // NOI18N
        jPanel1.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 630, -1, -1));

        btnVer4.setBackground(new java.awt.Color(204, 204, 204));
        btnVer4.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnVer4.setForeground(new java.awt.Color(255, 255, 255));
        btnVer4.setText("Ver Detalles");
        btnVer4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel1.add(btnVer4, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 660, -1, -1));

        btnVer2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnVer2.setForeground(new java.awt.Color(255, 255, 255));
        btnVer2.setText("Ver Detalles");
        btnVer2.setBorderPainted(false);
        btnVer2.setContentAreaFilled(false);
        btnVer2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnVer2.setFocusPainted(false);
        jPanel1.add(btnVer2, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 350, 130, -1));

        btnVer5.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnVer5.setForeground(new java.awt.Color(255, 255, 255));
        btnVer5.setText("Ver Detalles");
        btnVer5.setBorderPainted(false);
        btnVer5.setContentAreaFilled(false);
        btnVer5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnVer5.setFocusPainted(false);
        jPanel1.add(btnVer5, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 660, 130, -1));

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
        Iglesis.setForeground(new java.awt.Color(185, 116, 135));
        Iglesis.setText("Iglesias ");
        jPanel1.add(Iglesis, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 160, -1));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/Sus.png"))); // NOI18N
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 10, 70, 60));

        jSeparator1.setForeground(new java.awt.Color(11, 1, 26));
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
        jLabel1.setText("Agregar una Iglesia");
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

        Des.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        Des.setText("Descubre y gestiona las iglesias más");
        jPanel1.add(Des, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 20, 280, -1));

        Cu.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        Cu.setText("emblemáticas de la ciudad de Cuenca.");
        jPanel1.add(Cu, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 40, -1, -1));

        btnVerTodasIglesias.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnVerTodasIglesias.setText("Ver más Iglesias");
        btnVerTodasIglesias.setBorderPainted(false);
        btnVerTodasIglesias.setContentAreaFilled(false);
        btnVerTodasIglesias.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnVerTodasIglesias.setFocusPainted(false);
        btnVerTodasIglesias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerTodasIglesiasActionPerformed(evt);
            }
        });
        jPanel1.add(btnVerTodasIglesias, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 30, 180, 30));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/Fondo Iglesia.png"))); // NOI18N
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

    private void btnVer3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVer3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnVer3ActionPerformed

    private void btnImg1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImg1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnImg1ActionPerformed

    private void btnVer1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVer1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnVer1ActionPerformed

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

    private void btnVerTodasIglesiasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerTodasIglesiasActionPerformed
        Ventana_TarjetasIglesia ventanaTarjetas = new Ventana_TarjetasIglesia();

        ventanaTarjetas.setVentanaTarjetas(this);

        ventanaTarjetas.setLocationRelativeTo(this);
        ventanaTarjetas.setVisible(true);

        this.setVisible(false);


    }//GEN-LAST:event_btnVerTodasIglesiasActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        Ventana_InsertarIglesia ayudaPanel = new Ventana_InsertarIglesia();

        GlassPanePopup.showPopup(ayudaPanel);


    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Cu;
    private javax.swing.JLabel Des;
    private javax.swing.JLabel Iglesis;
    private javax.swing.JButton btnImg1;
    private javax.swing.JButton btnImg2;
    private javax.swing.JButton btnImg3;
    private javax.swing.JButton btnImg4;
    private javax.swing.JButton btnImg5;
    private javax.swing.JButton btnVer1;
    private javax.swing.JButton btnVer2;
    private javax.swing.JButton btnVer3;
    private javax.swing.JButton btnVer4;
    private javax.swing.JButton btnVer5;
    private javax.swing.JButton btnVerTodasIglesias;
    private javax.swing.JPanel content;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
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
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblDireccion1;
    private javax.swing.JLabel lblDireccion2;
    private javax.swing.JLabel lblDireccion3;
    private javax.swing.JLabel lblDireccion4;
    private javax.swing.JLabel lblDireccion5;
    private javax.swing.JLabel lblNombre1;
    private javax.swing.JLabel lblNombre2;
    private javax.swing.JLabel lblNombre3;
    private javax.swing.JLabel lblNombre4;
    private javax.swing.JLabel lblNombre5;
    private javax.swing.JLabel lblTipo1;
    private javax.swing.JLabel lblTipo2;
    private javax.swing.JLabel lblTipo3;
    private javax.swing.JLabel lblTipo4;
    private javax.swing.JLabel lblTipo5;
    // End of variables declaration//GEN-END:variables
}
