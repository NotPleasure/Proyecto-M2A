/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Animations.Animator1;
import Controlador.ControladorParque;
import Design.RoundedButtonDetalles;
import Design.RoundedButtonDetallesParques;
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
import Modelo.ParqueDetalleVista;
import Modelo.ParqueVistaUser;
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
public class Ventana_Parques extends javax.swing.JFrame {

    private final String BusquedaText = "Buscar Iglesias";
    private Persona persona;
    private Ventana_Principal ventanaPrincipal;
    private JFrame ventanaPadre;

    /**
     * Creates new form Interfaz_Lugares
     */
    public Ventana_Parques(JFrame ventanaPadre, Persona persona) {
        initComponents();
        this.ventanaPadre = ventanaPadre;
        this.persona = persona;

        //Cargar los parques:
        cargarParquesEnComponentes();

        //Instanciar el popup: 
        GlassPanePopup.install(this);

        //Fuentes:
        lblParqueNombre3.setFont(new Font("CocogooseProTrial", Font.PLAIN, 15));
        lblParqueTipo3.setFont(new Font("Caviar Dreams", Font.PLAIN, 13));
        lblParqueDireccion5.setFont(new Font("Caviar Dreams", Font.PLAIN, 13));
        lblParqueNombre5.setFont(new Font("CocogooseProTrial", Font.PLAIN, 15));
        lblParqueTipo5.setFont(new Font("Caviar Dreams", Font.PLAIN, 15));
        lblParqueDireccion3.setFont(new Font("Caviar Dreams", Font.PLAIN, 13));
        lblParqueNombre1.setFont(new Font("CocogooseProTrial", Font.PLAIN, 15));
        lblParqueTipo1.setFont(new Font("Caviar Dreams", Font.PLAIN, 13));
        lblParqueDireccion1.setFont(new Font("Caviar Dreams", Font.PLAIN, 13));
        lblParqueNombre4.setFont(new Font("CocogooseProTrial", Font.PLAIN, 15));
        lblParqueTipo4.setFont(new Font("Caviar Dreams", Font.PLAIN, 15));
        lblParqueDireccion4.setFont(new Font("Caviar Dreams", Font.PLAIN, 13));
        lblParqueNombre2.setFont(new Font("CocogooseProTrial", Font.PLAIN, 15));
        lblParqueTipo2.setFont(new Font("Caviar Dreams", Font.PLAIN, 13));
        lblParqueDireccion2.setFont(new Font("Caviar Dreams", Font.PLAIN, 13));

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
    private void cargarParquesEnComponentes() {
        LugarInteresDAO dao = new LugarInteresDAO();
        List<ParqueVistaUser> parques = dao.obtenerParques();

        JLabel[] labelsNombre = {lblParqueNombre1, lblParqueNombre2, lblParqueNombre3, lblParqueNombre4, lblParqueNombre5};
        JLabel[] labelsTipo = {lblParqueTipo1, lblParqueTipo2, lblParqueTipo3, lblParqueTipo4, lblParqueTipo5};
        JLabel[] labelsDireccion = {lblParqueDireccion1, lblParqueDireccion2, lblParqueDireccion3, lblParqueDireccion4, lblParqueDireccion5};
        JButton[] botonesImagen = {btnParqueImg1, btnParqueImg2, btnParqueImg3, btnParqueImg4, btnParqueImg5};
        JButton[] botonesVer = {btnVerParque1, btnVerParque2, btnVerParque3, btnVerParque4, btnVerParque5};

        for (JButton b : botonesVer) {
            for (ActionListener al : b.getActionListeners()) {
                b.removeActionListener(al);
            }
        }

        for (int i = 0; i < parques.size(); i++) {
            ParqueVistaUser pq = parques.get(i);

            String dir = pq.getDireccion();
            if (dir != null && dir.length() > 15) {
                dir = dir.substring(0, 15) + "...";
            }
            labelsDireccion[i].setText(dir != null ? dir : "");

            labelsNombre[i].setText(pq.getNombre());
            labelsTipo[i].setText("Parque");

            byte[] imgBytes = pq.getImagenPrincipal();
            if (imgBytes != null) {
                ImageIcon icon = new ImageIcon(imgBytes);
                Image imgEscalada = icon.getImage().getScaledInstance(380, 290, Image.SCALE_SMOOTH);
                botonesImagen[i].setIcon(new ImageIcon(imgEscalada));
            }
            final int idParque = pq.getId();
            botonesVer[i].addActionListener(e -> {
                try {
                    ControladorParque ctrl = new ControladorParque();
                    ParqueDetalleVista detalle = ctrl.obtenerDetalleParque(idParque);
                    if (detalle != null) {
                        Ventana_VerDetalleParques ventana = new Ventana_VerDetalleParques(detalle);
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
                            "Error al mostrar el parque:\n" + ex.getMessage(),
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
        btnVerParque1 = new RoundedButtonDetalles("");
        jLabel19 = new javax.swing.JLabel();
        lblParqueTipo1 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        lblParqueDireccion1 = new javax.swing.JLabel();
        btnParqueImg3 = new RoundedButtonLugares("");
        btnParqueImg2 = new RoundedButtonLugares("");
        btnParqueImg1 = new RoundedButtonLugares("");
        lblParqueNombre1 = new javax.swing.JLabel();
        lblParqueNombre2 = new javax.swing.JLabel();
        lblParqueNombre3 = new javax.swing.JLabel();
        lblParqueTipo3 = new javax.swing.JLabel();
        lblParqueTipo2 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        btnParqueImg4 = new RoundedButtonLugares("");
        btnParqueImg5 = new RoundedButtonLugares("");
        btnVerParque3 = new RoundedButtonDetalles("");
        lblParqueNombre4 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        lblParqueTipo4 = new javax.swing.JLabel();
        lblParqueNombre5 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        lblParqueTipo5 = new javax.swing.JLabel();
        btnVerParque4 = new RoundedButtonDetalles("");
        btnVerParque2 = new RoundedButtonDetalles("");
        btnVerParque5 = new RoundedButtonDetalles("");
        jLabel6 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel2 = new RoundedPanel2();
        jPanel3 = new RoundedPanel2
        ();
        jLabel26 = new javax.swing.JLabel();
        lblParqueDireccion2 = new javax.swing.JLabel();
        jPanel4 = new RoundedPanel2();
        jLabel18 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        lblParqueDireccion3 = new javax.swing.JLabel();
        jPanel5 = new RoundedPanel2();
        jLabel24 = new javax.swing.JLabel();
        lblParqueDireccion4 = new javax.swing.JLabel();
        jPanel6 = new RoundedPanel2
        ();
        jLabel20 = new javax.swing.JLabel();
        lblParqueDireccion5 = new javax.swing.JLabel();
        jButton1 = new RoundedButtonDetalles("");
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton2 = new RoundedButtonDetallesRegresar("");
        jLabel28 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jButton3 = new RoundedButtonDetallesParques("");
        jLabel7 = new javax.swing.JLabel();

        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        content.setBackground(new java.awt.Color(242, 242, 242));
        content.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(245, 245, 245));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnVerParque1.setBackground(new java.awt.Color(204, 204, 204));
        btnVerParque1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnVerParque1.setForeground(new java.awt.Color(255, 255, 255));
        btnVerParque1.setText("Ver Detalles");
        btnVerParque1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnVerParque1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerParque1ActionPerformed(evt);
            }
        });
        jPanel1.add(btnVerParque1, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 380, -1, -1));

        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/hombre-caminando (2).png"))); // NOI18N
        jPanel1.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 350, -1, -1));

        lblParqueTipo1.setText("Iglesias");
        jPanel1.add(lblParqueTipo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 352, -1, -1));

        jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/ubicacion (1).png"))); // NOI18N
        jPanel1.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 350, -1, -1));

        lblParqueDireccion1.setText("Cuenca, Ecuador.");
        jPanel1.add(lblParqueDireccion1, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 352, -1, -1));

        btnParqueImg3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/IglesiaSanFrancisco (1).jpg"))); // NOI18N
        btnParqueImg3.setBorderPainted(false);
        btnParqueImg3.setContentAreaFilled(false);
        btnParqueImg3.setFocusPainted(false);
        jPanel1.add(btnParqueImg3, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 140, 310, 180));

        btnParqueImg2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/IglesiaSanBlas (1).jpg"))); // NOI18N
        btnParqueImg2.setBorderPainted(false);
        btnParqueImg2.setContentAreaFilled(false);
        btnParqueImg2.setFocusPainted(false);
        jPanel1.add(btnParqueImg2, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 140, 310, 180));

        btnParqueImg1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/Catedral Vieja (1).jpg"))); // NOI18N
        btnParqueImg1.setBorderPainted(false);
        btnParqueImg1.setContentAreaFilled(false);
        btnParqueImg1.setFocusPainted(false);
        btnParqueImg1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnParqueImg1ActionPerformed(evt);
            }
        });
        jPanel1.add(btnParqueImg1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 140, 340, 180));

        lblParqueNombre1.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        lblParqueNombre1.setForeground(new java.awt.Color(237, 79, 31));
        lblParqueNombre1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblParqueNombre1.setText("Iglesia el Sagrario");
        jPanel1.add(lblParqueNombre1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 330, -1, -1));

        lblParqueNombre2.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        lblParqueNombre2.setForeground(new java.awt.Color(237, 79, 31));
        lblParqueNombre2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblParqueNombre2.setText("Iglesia San");
        jPanel1.add(lblParqueNombre2, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 330, -1, -1));

        lblParqueNombre3.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        lblParqueNombre3.setForeground(new java.awt.Color(237, 79, 31));
        lblParqueNombre3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblParqueNombre3.setText("Iglesia San");
        jPanel1.add(lblParqueNombre3, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 330, -1, -1));

        lblParqueTipo3.setText("Iglesias");
        jPanel1.add(lblParqueTipo3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 352, -1, -1));

        lblParqueTipo2.setText("Iglesias");
        jPanel1.add(lblParqueTipo2, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 352, -1, -1));

        jLabel25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/hombre-caminando (2).png"))); // NOI18N
        jPanel1.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 350, -1, -1));

        btnParqueImg4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/Iglesia SanSebastián (1).jpg"))); // NOI18N
        btnParqueImg4.setBorderPainted(false);
        btnParqueImg4.setContentAreaFilled(false);
        btnParqueImg4.setFocusPainted(false);
        jPanel1.add(btnParqueImg4, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 460, 340, 170));

        btnParqueImg5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/IglesiaTodosSantos (1).jpg"))); // NOI18N
        btnParqueImg5.setBorderPainted(false);
        btnParqueImg5.setContentAreaFilled(false);
        btnParqueImg5.setFocusPainted(false);
        jPanel1.add(btnParqueImg5, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 460, 310, 172));

        btnVerParque3.setBackground(new java.awt.Color(204, 204, 204));
        btnVerParque3.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnVerParque3.setForeground(new java.awt.Color(255, 255, 255));
        btnVerParque3.setText("Ver Detalles");
        btnVerParque3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnVerParque3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerParque3ActionPerformed(evt);
            }
        });
        jPanel1.add(btnVerParque3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 380, -1, -1));

        lblParqueNombre4.setForeground(new java.awt.Color(237, 79, 31));
        lblParqueNombre4.setText("Iglesia San");
        jPanel1.add(lblParqueNombre4, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 640, -1, -1));

        jLabel23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/hombre-caminando (2).png"))); // NOI18N
        jPanel1.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 660, -1, -1));

        lblParqueTipo4.setText("Iglesias");
        jPanel1.add(lblParqueTipo4, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 662, -1, -1));

        lblParqueNombre5.setForeground(new java.awt.Color(237, 79, 31));
        lblParqueNombre5.setText("Iglesia de Todos");
        jPanel1.add(lblParqueNombre5, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 640, -1, -1));

        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/hombre-caminando (2).png"))); // NOI18N
        jPanel1.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 660, -1, -1));

        lblParqueTipo5.setText("Iglesias");
        jPanel1.add(lblParqueTipo5, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 662, -1, -1));

        btnVerParque4.setBackground(new java.awt.Color(204, 204, 204));
        btnVerParque4.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnVerParque4.setForeground(new java.awt.Color(255, 255, 255));
        btnVerParque4.setText("Ver Detalles");
        btnVerParque4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel1.add(btnVerParque4, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 690, -1, -1));

        btnVerParque2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnVerParque2.setForeground(new java.awt.Color(255, 255, 255));
        btnVerParque2.setText("Ver Detalles");
        btnVerParque2.setBorderPainted(false);
        btnVerParque2.setContentAreaFilled(false);
        btnVerParque2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnVerParque2.setFocusPainted(false);
        jPanel1.add(btnVerParque2, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 380, 130, -1));

        btnVerParque5.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnVerParque5.setForeground(new java.awt.Color(255, 255, 255));
        btnVerParque5.setText("Ver Detalles");
        btnVerParque5.setBorderPainted(false);
        btnVerParque5.setContentAreaFilled(false);
        btnVerParque5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnVerParque5.setFocusPainted(false);
        jPanel1.add(btnVerParque5, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 690, 130, -1));

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

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 130, 380, 290));

        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/ubicacion (1).png"))); // NOI18N
        jPanel3.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 220, -1, -1));

        lblParqueDireccion2.setText("Cuenca, Ecuador.");
        jPanel3.add(lblParqueDireccion2, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 222, -1, -1));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 130, 370, 290));

        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/hombre-caminando (2).png"))); // NOI18N
        jPanel4.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 220, -1, -1));

        jLabel21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/ubicacion (1).png"))); // NOI18N
        jPanel4.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 218, -1, -1));

        lblParqueDireccion3.setText("Cuenca, Ecuador.");
        jPanel4.add(lblParqueDireccion3, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 220, -1, -1));

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 130, 350, 290));

        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/ubicacion (1).png"))); // NOI18N
        jPanel5.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 208, -1, -1));

        lblParqueDireccion4.setText("Cuenca, Ecuador.");
        jPanel5.add(lblParqueDireccion4, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 212, -1, -1));

        jPanel1.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 450, 380, 290));

        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/ubicacion (1).png"))); // NOI18N
        jPanel6.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 208, -1, -1));

        lblParqueDireccion5.setText("Cuenca, Ecuador.");
        jPanel6.add(lblParqueDireccion5, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 210, -1, -1));

        jPanel1.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 450, 330, 290));

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
        jLabel1.setText("Agregar un Parque");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1190, 708, -1, -1));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/mas.png"))); // NOI18N
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1160, 710, 20, 20));

        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1150, 700, 190, 40));

        jLabel28.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/iglesia (2).png"))); // NOI18N
        jPanel1.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 380, -1, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI Semibold", 1, 30)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(46, 78, 62));
        jLabel4.setText("Parques");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, -1, -1));

        jLabel5.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(112, 126, 109));
        jLabel5.setText("Descubre los espacios naturales más impresionantes");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 28, 440, -1));

        jLabel8.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(112, 126, 109));
        jLabel8.setText("planifica tu próxima aventura al aire libre");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 44, 410, -1));

        jSeparator1.setForeground(new java.awt.Color(112, 126, 109));
        jPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 64, 120, 10));

        jButton3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Explorar más lugares");
        jButton3.setBorderPainted(false);
        jButton3.setContentAreaFilled(false);
        jButton3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton3.setFocusPainted(false);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 30, 220, 30));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/Fondo Parque.png"))); // NOI18N
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1380, 850));

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

    private void btnVerParque3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerParque3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnVerParque3ActionPerformed

    private void btnParqueImg1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnParqueImg1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnParqueImg1ActionPerformed

    private void btnVerParque1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerParque1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnVerParque1ActionPerformed

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

        Ventana_TarjetasParque ventanaTarjetas = new Ventana_TarjetasParque();

        ventanaTarjetas.setVentanaTarjetas(this);

        ventanaTarjetas.setLocationRelativeTo(this);
        ventanaTarjetas.setVisible(true);

        this.setVisible(false);

    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        Ventana_InsertarParque ayudaPanel = new Ventana_InsertarParque();

        GlassPanePopup.showPopup(ayudaPanel);


    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnParqueImg1;
    private javax.swing.JButton btnParqueImg2;
    private javax.swing.JButton btnParqueImg3;
    private javax.swing.JButton btnParqueImg4;
    private javax.swing.JButton btnParqueImg5;
    private javax.swing.JButton btnVerParque1;
    private javax.swing.JButton btnVerParque2;
    private javax.swing.JButton btnVerParque3;
    private javax.swing.JButton btnVerParque4;
    private javax.swing.JButton btnVerParque5;
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
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblParqueDireccion1;
    private javax.swing.JLabel lblParqueDireccion2;
    private javax.swing.JLabel lblParqueDireccion3;
    private javax.swing.JLabel lblParqueDireccion4;
    private javax.swing.JLabel lblParqueDireccion5;
    private javax.swing.JLabel lblParqueNombre1;
    private javax.swing.JLabel lblParqueNombre2;
    private javax.swing.JLabel lblParqueNombre3;
    private javax.swing.JLabel lblParqueNombre4;
    private javax.swing.JLabel lblParqueNombre5;
    private javax.swing.JLabel lblParqueTipo1;
    private javax.swing.JLabel lblParqueTipo2;
    private javax.swing.JLabel lblParqueTipo3;
    private javax.swing.JLabel lblParqueTipo4;
    private javax.swing.JLabel lblParqueTipo5;
    // End of variables declaration//GEN-END:variables
}
