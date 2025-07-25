/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Vista;

import Animations.Animator1;
import Design.RoundedButtonLugaresEvent;
import Design.RoundedButtonSalirRe;
import Design.RoundedButtonVerMuseos;
import Design.RoundedButtonVerParques;
import Design.RoundedPanelAdmin;
import Design.RoundedPanelLugares;
import Design.RoundedPanelLugares2;
import Design.RoundedPanelLugares3;
import Design.RoundedPanelLugares4;
import Design.RounderButton2;
import Modelo.LugarInteresDAO;
import Modelo.Persona;
import Modelo.PersonaDAO;
import java.awt.Font;
import javax.swing.JFrame;
import raven.glasspanepopup.GlassPanePopup;

/**
 *
 * @author USER
 */
public class Ventana_Lugares extends javax.swing.JFrame {

    //Permitir entrar a la ventana:
    private boolean sinBordes = true;
    private Persona personaLogueada;

    /**
     * Creates new form Ventana_Lugares
     */
    public Ventana_Lugares(boolean sinBordes) {

        if (sinBordes) {
            setUndecorated(true);
        }
        initComponents();

        GlassPanePopup.install(this);

        //Cargar las estadísticas del panel general: 
        cargarEstadisticasGenerales();

        //Extender la ventana al máximo: 
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        // Para pantalla completa:
        setExtendedState(Ventana_Principal.MAXIMIZED_BOTH);

        //Fuentes:
        Huellas.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 20));
        Cuencanas.setFont(new Font("Segoe UI Light", Font.PLAIN, 20));
        LabelTotalUsuarios.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 45));
        Lugares.setFont(new Font("Segoe UI Light", Font.PLAIN, 15));
        ConteoLugares.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 45));
        ConteoImagenes.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 45));
        Lugares1.setFont(new Font("Segoe UI Light", Font.PLAIN, 15));
        ConteoDescripciones.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 45));
        Lugares2.setFont(new Font("Segoe UI Light", Font.PLAIN, 15));
        Lugares3.setFont(new Font("Segoe UI Light", Font.PLAIN, 15));
        Actividad.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 20));
        Iglesias.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 20));
        Gestion.setFont(new Font("Segoe UI Light", Font.PLAIN, 15));
        ConteoUser5.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 35));
        LabelIglesias.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 35));
        Registradas.setFont(new Font("Segoe UI Light", Font.PLAIN, 12));
        Registradas1.setFont(new Font("Segoe UI Light", Font.PLAIN, 12));
        Gestion1.setFont(new Font("Segoe UI Light", Font.PLAIN, 15));
        Museos.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 20));
        LabelMuseos.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 35));
        ConteoUser8.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 35));
        Registradas2.setFont(new Font("Segoe UI Light", Font.PLAIN, 12));
        Registradas3.setFont(new Font("Segoe UI Light", Font.PLAIN, 12));
        Parques.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 20));
        Gestion2.setFont(new Font("Segoe UI Light", Font.PLAIN, 15));
        LabelParques.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 35));
        ConteoUser10.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 35));
        Registradas4.setFont(new Font("Segoe UI Light", Font.PLAIN, 12));
        Registradas5.setFont(new Font("Segoe UI Light", Font.PLAIN, 12));
        Registros.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 20));
        Gestion3.setFont(new Font("Segoe UI Light", Font.PLAIN, 15));
        LabelHistorias.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 35));
        ConteoUser12.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 35));
        Registradas6.setFont(new Font("Segoe UI Light", Font.PLAIN, 12));
        Registradas7.setFont(new Font("Segoe UI Light", Font.PLAIN, 12));
        Categorias1.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 20));

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
        jButton3 = new javax.swing.JButton();
        Huellas = new javax.swing.JLabel();
        Cuencanas = new javax.swing.JLabel();
        jPanel3 = new RoundedPanelAdmin();
        LabelTotalUsuarios = new javax.swing.JLabel();
        Lugares = new javax.swing.JLabel();
        ConteoLugares = new javax.swing.JLabel();
        Lugares1 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        ConteoImagenes = new javax.swing.JLabel();
        Lugares2 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        ConteoDescripciones = new javax.swing.JLabel();
        Lugares3 = new javax.swing.JLabel();
        Actividad = new javax.swing.JLabel();
        jPanel4 = new RoundedPanelLugares();
        jLabel2 = new javax.swing.JLabel();
        Iglesias = new javax.swing.JLabel();
        Gestion = new javax.swing.JLabel();
        ConteoUser5 = new javax.swing.JLabel();
        LabelIglesias = new javax.swing.JLabel();
        Registradas = new javax.swing.JLabel();
        Registradas1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        Gestionar = new RoundedButtonLugaresEvent("");
        jPanel5 = new RoundedPanelLugares2();
        jLabel4 = new javax.swing.JLabel();
        Gestion1 = new javax.swing.JLabel();
        Museos = new javax.swing.JLabel();
        LabelMuseos = new javax.swing.JLabel();
        ConteoUser8 = new javax.swing.JLabel();
        Registradas2 = new javax.swing.JLabel();
        Registradas3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        Gestionar1 = new RoundedButtonLugaresEvent("");
        jPanel6 = new RoundedPanelLugares3();
        jLabel6 = new javax.swing.JLabel();
        Parques = new javax.swing.JLabel();
        Gestion2 = new javax.swing.JLabel();
        LabelParques = new javax.swing.JLabel();
        ConteoUser10 = new javax.swing.JLabel();
        Registradas4 = new javax.swing.JLabel();
        Registradas5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        Gestionar2 = new RoundedButtonLugaresEvent("");
        jPanel7 = new RoundedPanelLugares4();
        jLabel8 = new javax.swing.JLabel();
        Registros = new javax.swing.JLabel();
        Gestion3 = new javax.swing.JLabel();
        LabelHistorias = new javax.swing.JLabel();
        ConteoUser12 = new javax.swing.JLabel();
        Registradas6 = new javax.swing.JLabel();
        Registradas7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        Gestionar3 = new RoundedButtonLugaresEvent("");
        Categorias1 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jPanel8 = new RoundedPanelAdmin();
        jButton1 = new RoundedButtonLugaresEvent("");
        jLabel11 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jButton2 = new RoundedButtonVerMuseos(""
        );
        jLabel14 = new javax.swing.JLabel();
        jButton4 = new RoundedButtonVerParques(""
        );
        Regresar = new RoundedButtonSalirRe("");
        jButton5 = new RounderButton2("");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1390, 800));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(240, 244, 248));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(45, 63, 82));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/Copilot_20250610_114322 (3).png"))); // NOI18N
        jButton3.setBorderPainted(false);
        jButton3.setContentAreaFilled(false);
        jButton3.setFocusPainted(false);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, 60, 70));

        Huellas.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        Huellas.setForeground(new java.awt.Color(255, 255, 255));
        Huellas.setText("Huellas ");
        jPanel2.add(Huellas, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 26, -1, -1));

        Cuencanas.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        Cuencanas.setForeground(new java.awt.Color(255, 255, 255));
        Cuencanas.setText("Cuencanas");
        jPanel2.add(Cuencanas, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 44, -1, -1));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1370, 100));

        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        LabelTotalUsuarios.setFont(new java.awt.Font("Arial Black", 0, 30)); // NOI18N
        LabelTotalUsuarios.setForeground(new java.awt.Color(142, 68, 173));
        LabelTotalUsuarios.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel3.add(LabelTotalUsuarios, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 20, 90, 40));

        Lugares.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        Lugares.setForeground(new java.awt.Color(127, 140, 141));
        Lugares.setText("Usuarios Totales");
        jPanel3.add(Lugares, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 70, -1, -1));

        ConteoLugares.setFont(new java.awt.Font("Arial Black", 0, 30)); // NOI18N
        ConteoLugares.setForeground(new java.awt.Color(142, 68, 173));
        ConteoLugares.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel3.add(ConteoLugares, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 20, 90, 40));

        Lugares1.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        Lugares1.setForeground(new java.awt.Color(127, 140, 141));
        Lugares1.setText("Lugares Totales");
        jPanel3.add(Lugares1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 70, -1, -1));

        jSeparator2.setBackground(new java.awt.Color(127, 140, 141));
        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPanel3.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 0, 30, 110));

        jSeparator3.setBackground(new java.awt.Color(127, 140, 141));
        jSeparator3.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPanel3.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 0, 30, 110));

        ConteoImagenes.setFont(new java.awt.Font("Arial Black", 0, 30)); // NOI18N
        ConteoImagenes.setForeground(new java.awt.Color(142, 68, 173));
        ConteoImagenes.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel3.add(ConteoImagenes, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 20, 90, 40));

        Lugares2.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        Lugares2.setForeground(new java.awt.Color(127, 140, 141));
        Lugares2.setText("Imágenes");
        jPanel3.add(Lugares2, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 70, -1, -1));

        jSeparator4.setBackground(new java.awt.Color(127, 140, 141));
        jSeparator4.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPanel3.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 0, 30, 110));

        ConteoDescripciones.setFont(new java.awt.Font("Arial Black", 0, 30)); // NOI18N
        ConteoDescripciones.setForeground(new java.awt.Color(142, 68, 173));
        ConteoDescripciones.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel3.add(ConteoDescripciones, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 20, 90, 40));

        Lugares3.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        Lugares3.setForeground(new java.awt.Color(127, 140, 141));
        Lugares3.setText("Registros Históricos");
        jPanel3.add(Lugares3, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 70, -1, -1));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 90, 1170, 110));

        Actividad.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        Actividad.setForeground(new java.awt.Color(44, 62, 80));
        Actividad.setText("Actividad Reciente");
        jPanel1.add(Actividad, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 620, -1, -1));

        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/Iglesias 3.png"))); // NOI18N
        jPanel4.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 70, 90));

        Iglesias.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        Iglesias.setForeground(new java.awt.Color(44, 62, 80));
        Iglesias.setText("Iglesias");
        jPanel4.add(Iglesias, new org.netbeans.lib.awtextra.AbsoluteConstraints(28, 80, -1, -1));

        Gestion.setForeground(new java.awt.Color(127, 140, 141));
        Gestion.setText("Gestión de lugares religiosos.");
        jPanel4.add(Gestion, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, -1, -1));

        ConteoUser5.setFont(new java.awt.Font("Arial Black", 0, 35)); // NOI18N
        ConteoUser5.setForeground(new java.awt.Color(142, 68, 173));
        ConteoUser5.setText("0");
        ConteoUser5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel4.add(ConteoUser5, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 156, 90, 40));

        LabelIglesias.setFont(new java.awt.Font("Arial Black", 0, 35)); // NOI18N
        LabelIglesias.setForeground(new java.awt.Color(142, 68, 173));
        LabelIglesias.setText("0");
        LabelIglesias.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel4.add(LabelIglesias, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 156, 90, 40));

        Registradas.setForeground(new java.awt.Color(127, 140, 141));
        Registradas.setText("Favoritos");
        jPanel4.add(Registradas, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 200, -1, -1));

        Registradas1.setForeground(new java.awt.Color(127, 140, 141));
        Registradas1.setText("Registradas");
        jPanel4.add(Registradas1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 200, -1, -1));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/IIIIIIII.png"))); // NOI18N
        jPanel4.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 251, 40, 30));

        Gestionar.setForeground(new java.awt.Color(255, 255, 255));
        Gestionar.setText("Gestionar");
        Gestionar.setBorderPainted(false);
        Gestionar.setContentAreaFilled(false);
        Gestionar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Gestionar.setFocusPainted(false);
        Gestionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GestionarActionPerformed(evt);
            }
        });
        jPanel4.add(Gestionar, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 250, 170, 30));

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 290, 270, 310));

        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/Museoa A.png"))); // NOI18N
        jPanel5.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 70, 90));

        Gestion1.setForeground(new java.awt.Color(127, 140, 141));
        Gestion1.setText("Gestión de museos");
        jPanel5.add(Gestion1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, -1, -1));

        Museos.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        Museos.setForeground(new java.awt.Color(44, 62, 80));
        Museos.setText("Museos");
        jPanel5.add(Museos, new org.netbeans.lib.awtextra.AbsoluteConstraints(28, 80, -1, -1));

        LabelMuseos.setFont(new java.awt.Font("Arial Black", 0, 35)); // NOI18N
        LabelMuseos.setForeground(new java.awt.Color(142, 68, 173));
        LabelMuseos.setText("0");
        LabelMuseos.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel5.add(LabelMuseos, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 156, 90, 40));

        ConteoUser8.setFont(new java.awt.Font("Arial Black", 0, 35)); // NOI18N
        ConteoUser8.setForeground(new java.awt.Color(142, 68, 173));
        ConteoUser8.setText("0");
        ConteoUser8.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel5.add(ConteoUser8, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 156, 90, 40));

        Registradas2.setForeground(new java.awt.Color(127, 140, 141));
        Registradas2.setText("Registradas");
        jPanel5.add(Registradas2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 200, -1, -1));

        Registradas3.setForeground(new java.awt.Color(127, 140, 141));
        Registradas3.setText("Favoritos");
        jPanel5.add(Registradas3, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 200, -1, -1));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/IIIIIIII.png"))); // NOI18N
        jPanel5.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 252, 40, 30));

        Gestionar1.setForeground(new java.awt.Color(255, 255, 255));
        Gestionar1.setText("Gestionar");
        Gestionar1.setBorderPainted(false);
        Gestionar1.setContentAreaFilled(false);
        Gestionar1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Gestionar1.setFocusPainted(false);
        Gestionar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Gestionar1ActionPerformed(evt);
            }
        });
        jPanel5.add(Gestionar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 250, 170, 30));

        jPanel1.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 290, 270, 310));

        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/Parques A.png"))); // NOI18N
        jPanel6.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 70, 90));

        Parques.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        Parques.setForeground(new java.awt.Color(44, 62, 80));
        Parques.setText("Parques");
        jPanel6.add(Parques, new org.netbeans.lib.awtextra.AbsoluteConstraints(28, 80, -1, -1));

        Gestion2.setForeground(new java.awt.Color(127, 140, 141));
        Gestion2.setText("Gestión de Parques.");
        jPanel6.add(Gestion2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, -1, -1));

        LabelParques.setFont(new java.awt.Font("Arial Black", 0, 35)); // NOI18N
        LabelParques.setForeground(new java.awt.Color(142, 68, 173));
        LabelParques.setText("0");
        LabelParques.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel6.add(LabelParques, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 156, 90, 40));

        ConteoUser10.setFont(new java.awt.Font("Arial Black", 0, 35)); // NOI18N
        ConteoUser10.setForeground(new java.awt.Color(142, 68, 173));
        ConteoUser10.setText("0");
        ConteoUser10.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel6.add(ConteoUser10, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 156, 90, 40));

        Registradas4.setForeground(new java.awt.Color(127, 140, 141));
        Registradas4.setText("Registradas");
        jPanel6.add(Registradas4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 200, -1, -1));

        Registradas5.setForeground(new java.awt.Color(127, 140, 141));
        Registradas5.setText("Favoritos");
        jPanel6.add(Registradas5, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 200, -1, -1));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/IIIIIIII.png"))); // NOI18N
        jPanel6.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 252, 40, 30));

        Gestionar2.setForeground(new java.awt.Color(255, 255, 255));
        Gestionar2.setText("Gestionar");
        Gestionar2.setBorderPainted(false);
        Gestionar2.setContentAreaFilled(false);
        Gestionar2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Gestionar2.setFocusPainted(false);
        Gestionar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Gestionar2ActionPerformed(evt);
            }
        });
        jPanel6.add(Gestionar2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 250, 170, 30));

        jPanel1.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 290, 270, 310));

        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/Registros.png"))); // NOI18N
        jPanel7.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 70, 90));

        Registros.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        Registros.setForeground(new java.awt.Color(44, 62, 80));
        Registros.setText("Registros Históricos");
        jPanel7.add(Registros, new org.netbeans.lib.awtextra.AbsoluteConstraints(28, 80, -1, -1));

        Gestion3.setForeground(new java.awt.Color(127, 140, 141));
        Gestion3.setText("Información Histórica de Lugares");
        jPanel7.add(Gestion3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, -1, -1));

        LabelHistorias.setFont(new java.awt.Font("Arial Black", 0, 35)); // NOI18N
        LabelHistorias.setForeground(new java.awt.Color(142, 68, 173));
        LabelHistorias.setText("0");
        LabelHistorias.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel7.add(LabelHistorias, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 156, 90, 40));

        ConteoUser12.setFont(new java.awt.Font("Arial Black", 0, 35)); // NOI18N
        ConteoUser12.setForeground(new java.awt.Color(142, 68, 173));
        ConteoUser12.setText("0");
        ConteoUser12.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel7.add(ConteoUser12, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 156, 90, 40));

        Registradas6.setForeground(new java.awt.Color(127, 140, 141));
        Registradas6.setText("Registradas");
        jPanel7.add(Registradas6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 200, -1, -1));

        Registradas7.setForeground(new java.awt.Color(127, 140, 141));
        Registradas7.setText("Favoritos");
        jPanel7.add(Registradas7, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 200, -1, -1));

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/IIIIIIII.png"))); // NOI18N
        jPanel7.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 252, 40, 30));

        Gestionar3.setForeground(new java.awt.Color(255, 255, 255));
        Gestionar3.setText("Gestionar");
        Gestionar3.setBorderPainted(false);
        Gestionar3.setContentAreaFilled(false);
        Gestionar3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Gestionar3.setFocusPainted(false);
        jPanel7.add(Gestionar3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 250, 170, 30));

        jPanel1.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 290, 270, 310));

        Categorias1.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        Categorias1.setForeground(new java.awt.Color(44, 62, 80));
        Categorias1.setText("Categorías de Lugares");
        jPanel1.add(Categorias1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 226, -1, -1));

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/ppppppp.png"))); // NOI18N
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 596, 90, 80));

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/a1.png"))); // NOI18N
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, 90, 80));

        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton1.setText("Ver mis Iglesisas");
        jButton1.setBorderPainted(false);
        jButton1.setContentAreaFilled(false);
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.setFocusPainted(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel8.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 20, 140, 30));

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/Iglesias 3.png"))); // NOI18N
        jPanel8.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -10, 70, 90));

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/Museoa A.png"))); // NOI18N
        jPanel8.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, -10, 70, 90));

        jButton2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton2.setText("Ver mis Museos");
        jButton2.setBorderPainted(false);
        jButton2.setContentAreaFilled(false);
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton2.setFocusPainted(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel8.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 20, 140, 30));

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/Parques A.png"))); // NOI18N
        jPanel8.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, -10, 70, 90));

        jButton4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton4.setText("Ver mis Parques");
        jButton4.setBorderPainted(false);
        jButton4.setContentAreaFilled(false);
        jButton4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton4.setFocusPainted(false);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel8.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 20, 140, 30));

        Regresar.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        Regresar.setText("Regresar");
        Regresar.setBorderPainted(false);
        Regresar.setContentAreaFilled(false);
        Regresar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Regresar.setFocusPainted(false);
        Regresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RegresarActionPerformed(evt);
            }
        });
        jPanel8.add(Regresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 20, 140, 30));

        jButton5.setText("Estadísticas");
        jButton5.setBorderPainted(false);
        jButton5.setContentAreaFilled(false);
        jButton5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton5.setFocusPainted(false);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel8.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 20, 140, 30));

        jPanel1.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 680, 1220, 70));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1370, 860));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void GestionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GestionarActionPerformed

        Ventana_InsertarIglesia ayudaPanel = new Ventana_InsertarIglesia();

        GlassPanePopup.showPopup(ayudaPanel);

    }//GEN-LAST:event_GestionarActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        Ventana_TarjetasIglesia ventana = new Ventana_TarjetasIglesia();
        ventana.setExtendedState(JFrame.MAXIMIZED_BOTH);
        ventana.setVisible(true);


    }//GEN-LAST:event_jButton1ActionPerformed

    private void Gestionar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Gestionar1ActionPerformed

        Ventana_InsertarMuseo ayudaPanel = new Ventana_InsertarMuseo();

        GlassPanePopup.showPopup(ayudaPanel);


    }//GEN-LAST:event_Gestionar1ActionPerformed

    private void Gestionar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Gestionar2ActionPerformed

        Ventana_InsertarParque ayudaPanel = new Ventana_InsertarParque();

        GlassPanePopup.showPopup(ayudaPanel);
    }//GEN-LAST:event_Gestionar2ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        Ventana_TarjetasMuseo ventana = new Ventana_TarjetasMuseo();
        ventana.setExtendedState(JFrame.MAXIMIZED_BOTH);
        ventana.setVisible(true);

    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed

        Ventana_TarjetasParque ventana = new Ventana_TarjetasParque();
        ventana.setExtendedState(JFrame.MAXIMIZED_BOTH);
        ventana.setVisible(true);

    }//GEN-LAST:event_jButton4ActionPerformed

    private void RegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RegresarActionPerformed

 Animator1.fadeOut(this, () -> {
            this.dispose();

            Admin_Panel ventana = new Admin_Panel(true, personaLogueada);
            ventana.setOpacity(0f);
            ventana.setVisible(true);

            Animator1.fadeIn(ventana);
        });

    }//GEN-LAST:event_RegresarActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed

 Ventana_EstadísticasLugares ayudaPanel = new Ventana_EstadísticasLugares();

        GlassPanePopup.showPopup(ayudaPanel);

    }//GEN-LAST:event_jButton5ActionPerformed

    public void cargarEstadisticasGenerales() {
        LugarInteresDAO lugarDAO = new LugarInteresDAO();
        PersonaDAO personaDAO = new PersonaDAO();
        int totalIglesias = lugarDAO.contarIglesias();
        int totalMuseos = lugarDAO.contarMuseos();
        int totalParques = lugarDAO.contarParques();
        int totalLugares = lugarDAO.contarLugares();
        int totalImagenes = lugarDAO.contarImagenes();
        int totalDescripciones = lugarDAO.contarDescripciones();
        int totalUsuarios = personaDAO.contarUsuarios(null);

        // Labels generales
        LabelTotalUsuarios.setText(String.valueOf(totalUsuarios));
        ConteoLugares.setText(String.valueOf(totalLugares));
        ConteoImagenes.setText(String.valueOf(totalImagenes));
        ConteoDescripciones.setText(String.valueOf(totalDescripciones));

        LabelIglesias.setText(String.valueOf(totalIglesias));
        LabelMuseos.setText(String.valueOf(totalMuseos));
        LabelParques.setText(String.valueOf(totalParques));
        LabelHistorias.setText(String.valueOf(totalDescripciones));
    }

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
            java.util.logging.Logger.getLogger(Ventana_Lugares.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Ventana_Lugares.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Ventana_Lugares.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Ventana_Lugares.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Ventana_Lugares(true).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Actividad;
    private javax.swing.JLabel Categorias1;
    private javax.swing.JLabel ConteoDescripciones;
    private javax.swing.JLabel ConteoImagenes;
    private javax.swing.JLabel ConteoLugares;
    private javax.swing.JLabel ConteoUser10;
    private javax.swing.JLabel ConteoUser12;
    private javax.swing.JLabel ConteoUser5;
    private javax.swing.JLabel ConteoUser8;
    private javax.swing.JLabel Cuencanas;
    private javax.swing.JLabel Gestion;
    private javax.swing.JLabel Gestion1;
    private javax.swing.JLabel Gestion2;
    private javax.swing.JLabel Gestion3;
    private javax.swing.JButton Gestionar;
    private javax.swing.JButton Gestionar1;
    private javax.swing.JButton Gestionar2;
    private javax.swing.JButton Gestionar3;
    private javax.swing.JLabel Huellas;
    private javax.swing.JLabel Iglesias;
    private javax.swing.JLabel LabelHistorias;
    private javax.swing.JLabel LabelIglesias;
    private javax.swing.JLabel LabelMuseos;
    private javax.swing.JLabel LabelParques;
    private javax.swing.JLabel LabelTotalUsuarios;
    private javax.swing.JLabel Lugares;
    private javax.swing.JLabel Lugares1;
    private javax.swing.JLabel Lugares2;
    private javax.swing.JLabel Lugares3;
    private javax.swing.JLabel Museos;
    private javax.swing.JLabel Parques;
    private javax.swing.JLabel Registradas;
    private javax.swing.JLabel Registradas1;
    private javax.swing.JLabel Registradas2;
    private javax.swing.JLabel Registradas3;
    private javax.swing.JLabel Registradas4;
    private javax.swing.JLabel Registradas5;
    private javax.swing.JLabel Registradas6;
    private javax.swing.JLabel Registradas7;
    private javax.swing.JLabel Registros;
    private javax.swing.JButton Regresar;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
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
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    // End of variables declaration//GEN-END:variables
}
