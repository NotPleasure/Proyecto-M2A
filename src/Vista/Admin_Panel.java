/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Animations.Animator;
import Design.Graficos.GraficoMinimal;
import Design.RoundedPanelAdmin;
import Design.RoundedButtonAdmin;
import Design.RoundedButtonLugar;
import Design.RoundedButtonSalir;
import Design.RoundedPanelAdminBorde;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import Design.Tabla.EncabezadoModernoRenderer;
import Design.Tabla.EstiloTablaModerna;
import Modelo.AdministradorDAO;
import Modelo.Persona;
import Modelo.PersonaDAO;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import Design.Reloj.RelojMinimalista;
import Animations.Animator1;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

/**
 *
 * @author USER
 */
public class Admin_Panel extends javax.swing.JFrame {

    private DefaultTableModel modeloUsuarios;
    private JTable tablaUsuarios;
    private PersonaDAO personaDAO = new PersonaDAO();
    private ImageIcon placeholderIcon;
    private boolean sinBordes = true;
    private Persona personaLogueada;

    /**
     * Creates new form Admin_Panel
     */
    public Admin_Panel(boolean sinBordes, Persona persona) {
        this.personaLogueada = persona;
        if (sinBordes) {
            setUndecorated(true);
        }
        initComponents();

        //Cargar las funciones:
        cargarUsuarioEnLabel();
        cargarEstadisticas();
        cargarGraficoEnPanelUsuarios();

        setLocationRelativeTo(null);

        //Cargar el reloj:  
        Reloj.removeAll();
        Reloj.setLayout(new BorderLayout());
        RelojMinimalista relojMinimalista = new RelojMinimalista();
        Reloj.add(relojMinimalista, BorderLayout.CENTER);
        Reloj.revalidate();
        Reloj.repaint();

        //Extender la ventana al máximo: 
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        // Para pantalla completa:
        setExtendedState(Ventana_Principal.MAXIMIZED_BOTH);

        //Fuentes de los labels, jButtons y Jfileds:
        Huellas.setFont(new Font("Open Sans Bold", Font.PLAIN, 18));
        Cuencanas.setFont(new Font("Open Sans", Font.PLAIN, 14));
        Total.setFont(new Font("Caviar Dreams", Font.PLAIN, 24));
        Usuarios.setFont(new Font("Caviar Dreams Bold", Font.PLAIN, 24));
        ConteoUser.setFont(new Font("Caviar Dreams Bold", Font.PLAIN, 24));
        Total1.setFont(new Font("Caviar Dreams", Font.PLAIN, 24));
        ConteoUser1.setFont(new Font("Caviar Dreams Bold", Font.PLAIN, 24));
        Total2.setFont(new Font("Caviar Dreams", Font.PLAIN, 24));
        Usuarios1.setFont(new Font("Caviar Dreams Bold", Font.PLAIN, 24));
        ConteoUser2.setFont(new Font("Caviar Dreams Bold", Font.PLAIN, 24));
        Total3.setFont(new Font("Caviar Dreams", Font.PLAIN, 24));
        Usuarios2.setFont(new Font("Caviar Dreams Bold", Font.PLAIN, 24));
        ConteoUser3.setFont(new Font("Caviar Dreams Bold", Font.PLAIN, 24));
        Bienvenido.setFont(new Font("Monserrat Bold", Font.PLAIN, 40));
        Gusto.setFont(new Font("Monserrat Bold", Font.PLAIN, 30));
        Vuelta.setFont(new Font("Monserrat Bold", Font.PLAIN, 30));
        Rol.setFont(new Font("Caviar Dreams", Font.PLAIN, 24));
        Dashboard.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 17));
        admin_label.setFont(new Font("Caviar Dreams", Font.PLAIN, 24));
        Lugares.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 17));
        Lugares1.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 17));
        Lugares2.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 17));
        Reservas.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 17));
        Favoritos.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 17));
        Rutas.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 17));

    }

    //Contar Usuarios:
    public void cargarEstadisticas() {
        PersonaDAO dao = new PersonaDAO();

        int totalUsuarios = dao.contarUsuarios(null);
        int totalAdmins = dao.contarUsuarios("rol_id = 1");
        int totalSuperUsuarios = dao.contarUsuarios("rol_id = 3");
        int totalUsuariosPrincipales = dao.contarUsuarios("rol_id = 2");

        ConteoUser1.setText(String.valueOf(totalUsuarios));
        ConteoUser.setText(String.valueOf(totalAdmins));
        ConteoUser2.setText(String.valueOf(totalSuperUsuarios));
        ConteoUser3.setText(String.valueOf(totalUsuariosPrincipales));

    }

    //Para cargar el nombrel del admin:
    public void cargarUsuarioEnLabel() {
        if (personaLogueada != null) {
            admin_label.setText(personaLogueada.getUsuario());
        } else {
            admin_label.setText("—");
        }
    }

    //Para cargar los datos en los labels y el gráfico:
    public void cargarGraficoEnPanelUsuarios() {
        PersonaDAO dao = new PersonaDAO();
        int totalUsuarios = dao.contarUsuarios(null);

        int totalAdmins = dao.contarUsuarios("rol_id = 1");
        int totalSuperusuarios = dao.contarUsuarios("rol_id = 3");
        int totalUsuariosPrincipales = dao.contarUsuarios("rol_id = 2");

        // Actualizar labels o gráficos con estos valores
        ConteoUser1.setText(String.valueOf(totalUsuarios));
        ConteoUser.setText(String.valueOf(totalAdmins));
        ConteoUser2.setText(String.valueOf(totalSuperusuarios));
        ConteoUser3.setText(String.valueOf(totalUsuariosPrincipales));

        GraficoMinimal grafico = new GraficoMinimal();
        JPanel chartPanel = grafico.crearGraficoPanel(totalUsuarios, totalAdmins, totalSuperusuarios, totalUsuariosPrincipales);

        PanelUsuarios.removeAll();
        PanelUsuarios.setLayout(new java.awt.BorderLayout());
        PanelUsuarios.add(chartPanel, java.awt.BorderLayout.CENTER);
        PanelUsuarios.revalidate();
        PanelUsuarios.repaint();
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
        jButton3 = new javax.swing.JButton();
        Cuencanas = new javax.swing.JLabel();
        Huellas = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jButton1 = new RoundedButtonSalir("");
        Dashboard = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        Lugares = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        Lugares2 = new javax.swing.JLabel();
        jButton2 =  new RoundedButtonSalir("")
        ;
        jButton9 =  new RoundedButtonSalir("")
        ;
        Lugares1 = new javax.swing.JLabel();
        jButton10 =  new RoundedButtonSalir("")
        ;
        jLabel10 = new javax.swing.JLabel();
        jButton11 = new RoundedButtonSalir("");
        jLabel11 = new javax.swing.JLabel();
        Reservas = new javax.swing.JLabel();
        jButton12 = new RoundedButtonSalir("");
        jLabel12 = new javax.swing.JLabel();
        Favoritos = new javax.swing.JLabel();
        jButton13 = new RoundedButtonSalir("");
        jLabel13 = new javax.swing.JLabel();
        Rutas = new javax.swing.JLabel();
        jButton14 = new RoundedButtonSalir("");
        jPanel2 = new javax.swing.JPanel();
        miPanelDiseñado = new RoundedPanelAdmin();
        Total = new javax.swing.JLabel();
        Usuarios = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        ConteoUser1 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jPanel3 = new RoundedPanelAdmin();
        jLabel2 = new javax.swing.JLabel();
        Total1 = new javax.swing.JLabel();
        ConteoUser = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        jPanel4 = new RoundedPanelAdmin();
        jLabel3 = new javax.swing.JLabel();
        Total2 = new javax.swing.JLabel();
        Usuarios1 = new javax.swing.JLabel();
        ConteoUser2 = new javax.swing.JLabel();
        jButton7 = new javax.swing.JButton();
        jPanel5 = new RoundedPanelAdmin();
        jLabel4 = new javax.swing.JLabel();
        Total3 = new javax.swing.JLabel();
        Usuarios2 = new javax.swing.JLabel();
        ConteoUser3 = new javax.swing.JLabel();
        jButton8 = new javax.swing.JButton();
        jPanel6 = new RoundedPanelAdmin();
        Bienvenido = new javax.swing.JLabel();
        admin_label = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        Gusto = new javax.swing.JLabel();
        Vuelta = new javax.swing.JLabel();
        Reloj = new RoundedPanelAdmin();
        PanelUsuarios = new RoundedPanelAdmin();
        jPanel11 = new RoundedPanelAdmin();
        jLabel5 = new javax.swing.JLabel();
        jPanel7 = new RoundedPanelAdmin();
        Rol = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(62, 83, 160));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/Copilot_20250610_114322 (3).png"))); // NOI18N
        jButton3.setBorderPainted(false);
        jButton3.setContentAreaFilled(false);
        jButton3.setFocusPainted(false);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, 60, 70));

        Cuencanas.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        Cuencanas.setForeground(new java.awt.Color(255, 255, 255));
        Cuencanas.setText("Cuencanas");
        jPanel1.add(Cuencanas, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 50, -1, -1));

        Huellas.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        Huellas.setForeground(new java.awt.Color(255, 255, 255));
        Huellas.setText("Huellas ");
        jPanel1.add(Huellas, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 30, -1, -1));

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/puerta-abierta (1).png"))); // NOI18N
        jButton4.setBorderPainted(false);
        jButton4.setContentAreaFilled(false);
        jButton4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton4.setFocusPainted(false);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 690, -1, 70));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/tablero-de-mensajes (1).png"))); // NOI18N
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 130, 60, 50));

        jButton1.setBorderPainted(false);
        jButton1.setContentAreaFilled(false);
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.setFocusPainted(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 690, 250, 110));

        Dashboard.setFont(new java.awt.Font("Dialog", 1, 17)); // NOI18N
        Dashboard.setForeground(new java.awt.Color(255, 255, 255));
        Dashboard.setText("Dashboard");
        jPanel1.add(Dashboard, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 146, -1, -1));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/mapa (1).png"))); // NOI18N
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 200, 60, 40));

        Lugares.setFont(new java.awt.Font("Dialog", 1, 17)); // NOI18N
        Lugares.setForeground(new java.awt.Color(255, 255, 255));
        Lugares.setText("Reseñas");
        jPanel1.add(Lugares, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 350, -1, -1));

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/oportunidades-de-trabajo (1).png"))); // NOI18N
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 270, 50, 50));

        Lugares2.setFont(new java.awt.Font("Dialog", 1, 17)); // NOI18N
        Lugares2.setForeground(new java.awt.Color(255, 255, 255));
        Lugares2.setText("Negocios");
        jPanel1.add(Lugares2, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 280, -1, -1));

        jButton2.setBorderPainted(false);
        jButton2.setContentAreaFilled(false);
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton2.setFocusPainted(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 260, 250, 70));

        jButton9.setBorderPainted(false);
        jButton9.setContentAreaFilled(false);
        jButton9.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton9.setFocusPainted(false);
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 120, 250, 70));

        Lugares1.setFont(new java.awt.Font("Dialog", 1, 17)); // NOI18N
        Lugares1.setForeground(new java.awt.Color(255, 255, 255));
        Lugares1.setText("Lugares");
        jPanel1.add(Lugares1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 210, -1, -1));

        jButton10.setBorderPainted(false);
        jButton10.setContentAreaFilled(false);
        jButton10.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton10.setFocusPainted(false);
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton10, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 250, 70));

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/Reseñas.png"))); // NOI18N
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 340, 50, 50));

        jButton11.setBorderPainted(false);
        jButton11.setContentAreaFilled(false);
        jButton11.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton11.setFocusPainted(false);
        jPanel1.add(jButton11, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 330, 250, 70));

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/campana (1).png"))); // NOI18N
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 410, 50, -1));

        Reservas.setFont(new java.awt.Font("Segoe UI", 0, 17)); // NOI18N
        Reservas.setForeground(new java.awt.Color(255, 255, 255));
        Reservas.setText("Reservas");
        jPanel1.add(Reservas, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 420, -1, -1));

        jButton12.setBorderPainted(false);
        jButton12.setContentAreaFilled(false);
        jButton12.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton12.setFocusPainted(false);
        jPanel1.add(jButton12, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 400, 250, 70));

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/estrella (2).png"))); // NOI18N
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 480, 50, 50));

        Favoritos.setFont(new java.awt.Font("Segoe UI", 0, 17)); // NOI18N
        Favoritos.setForeground(new java.awt.Color(255, 255, 255));
        Favoritos.setText("Favoritos");
        jPanel1.add(Favoritos, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 490, -1, -1));

        jButton13.setBorderPainted(false);
        jButton13.setContentAreaFilled(false);
        jButton13.setFocusPainted(false);
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton13, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 470, 250, 70));

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/camino (1).png"))); // NOI18N
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 540, 50, 70));

        Rutas.setFont(new java.awt.Font("Segoe UI", 0, 17)); // NOI18N
        Rutas.setForeground(new java.awt.Color(255, 255, 255));
        Rutas.setText("Rutas");
        jPanel1.add(Rutas, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 560, -1, -1));

        jButton14.setBorderPainted(false);
        jButton14.setContentAreaFilled(false);
        jButton14.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton14.setFocusPainted(false);
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton14, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 540, 250, 70));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 250, 860));

        jPanel2.setBackground(new java.awt.Color(236, 238, 240));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        miPanelDiseñado.setBackground(new java.awt.Color(204, 204, 204));
        miPanelDiseñado.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Total.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        Total.setForeground(new java.awt.Color(0, 0, 0));
        Total.setText("Total de ");
        Total.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        miPanelDiseñado.add(Total, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 24, -1, -1));

        Usuarios.setBackground(new java.awt.Color(0, 0, 0));
        Usuarios.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        Usuarios.setForeground(new java.awt.Color(0, 0, 0));
        Usuarios.setText("Usuarios");
        miPanelDiseñado.add(Usuarios, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 44, -1, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/Admin 2.png"))); // NOI18N
        miPanelDiseñado.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 80, 60));

        ConteoUser1.setFont(new java.awt.Font("Arial Black", 0, 24)); // NOI18N
        ConteoUser1.setForeground(new java.awt.Color(0, 0, 0));
        ConteoUser1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        miPanelDiseñado.add(ConteoUser1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 90, 90, 40));

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/vista (1).png"))); // NOI18N
        jButton5.setBorderPainted(false);
        jButton5.setContentAreaFilled(false);
        jButton5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton5.setFocusPainted(false);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        miPanelDiseñado.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 90, 70, 30));

        jPanel2.add(miPanelDiseñado, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 240, 140));

        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/Admin 3.png"))); // NOI18N
        jPanel3.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 80, 60));

        Total1.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        Total1.setForeground(new java.awt.Color(0, 0, 0));
        Total1.setText("Admin");
        Total1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel3.add(Total1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 34, -1, -1));

        ConteoUser.setFont(new java.awt.Font("Arial Black", 0, 24)); // NOI18N
        ConteoUser.setForeground(new java.awt.Color(0, 0, 0));
        ConteoUser.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel3.add(ConteoUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 90, 90, 40));

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/vista (1).png"))); // NOI18N
        jButton6.setBorderPainted(false);
        jButton6.setContentAreaFilled(false);
        jButton6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton6.setFocusPainted(false);
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 90, 70, 30));

        jPanel2.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 20, 240, 140));

        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/Admin 4.png"))); // NOI18N
        jPanel4.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 80, 60));

        Total2.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        Total2.setForeground(new java.awt.Color(0, 0, 0));
        Total2.setText("Super");
        Total2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel4.add(Total2, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 24, -1, -1));

        Usuarios1.setBackground(new java.awt.Color(0, 0, 0));
        Usuarios1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        Usuarios1.setForeground(new java.awt.Color(0, 0, 0));
        Usuarios1.setText("Usuarios");
        jPanel4.add(Usuarios1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 44, -1, -1));

        ConteoUser2.setFont(new java.awt.Font("Arial Black", 0, 24)); // NOI18N
        ConteoUser2.setForeground(new java.awt.Color(0, 0, 0));
        ConteoUser2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel4.add(ConteoUser2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 90, 90, 40));

        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/vista (1).png"))); // NOI18N
        jButton7.setBorderPainted(false);
        jButton7.setContentAreaFilled(false);
        jButton7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton7.setFocusPainted(false);
        jPanel4.add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 90, 70, 30));

        jPanel2.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 20, 240, 140));

        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/Admin 5.png"))); // NOI18N
        jPanel5.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 80, 60));

        Total3.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        Total3.setForeground(new java.awt.Color(0, 0, 0));
        Total3.setText("Usuario");
        Total3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel5.add(Total3, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 24, -1, -1));

        Usuarios2.setBackground(new java.awt.Color(0, 0, 0));
        Usuarios2.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        Usuarios2.setForeground(new java.awt.Color(0, 0, 0));
        Usuarios2.setText("Principal");
        jPanel5.add(Usuarios2, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 44, -1, -1));

        ConteoUser3.setFont(new java.awt.Font("Arial Black", 0, 24)); // NOI18N
        ConteoUser3.setForeground(new java.awt.Color(0, 0, 0));
        ConteoUser3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel5.add(ConteoUser3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 90, 90, 40));

        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/vista (1).png"))); // NOI18N
        jButton8.setBorderPainted(false);
        jButton8.setContentAreaFilled(false);
        jButton8.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton8.setFocusPainted(false);
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 90, 70, 30));

        jPanel2.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 20, 240, 140));

        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Bienvenido.setText("Bienvenido");
        jPanel6.add(Bienvenido, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 14, 220, 60));
        jPanel6.add(admin_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 70, 130, 40));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/file_00000000c53861f790997cc9bcde40b7__5_-removebg-preview.png"))); // NOI18N
        jPanel6.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, -20, 250, 250));

        Gusto.setForeground(new java.awt.Color(0, 0, 0));
        Gusto.setText("¡Es un gusto tenerte de ");
        jPanel6.add(Gusto, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 120, -1, -1));

        Vuelta.setForeground(new java.awt.Color(0, 0, 0));
        Vuelta.setText("vuelta!");
        jPanel6.add(Vuelta, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 150, 170, -1));

        jPanel2.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, 640, 220));

        Reloj.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel2.add(Reloj, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 180, 420, 220));

        PanelUsuarios.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel2.add(PanelUsuarios, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 480, 1080, 270));

        jPanel11.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/sol (1).png"))); // NOI18N
        jPanel11.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 40, 50, 60));

        jPanel2.add(jPanel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 20, 80, 140));

        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Rol.setText("Usuarios por Rol");
        jPanel7.add(Rol, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 20, 180, -1));

        jPanel2.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 410, 1080, 80));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 0, 1140, 860));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        int dialog = JOptionPane.YES_NO_OPTION;
        int result = JOptionPane.showConfirmDialog(null, "¿Desea salir?", "Exit", dialog);
        if (result == 0) {
            System.exit(0);

        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        int dialog = JOptionPane.YES_NO_OPTION;
        int result = JOptionPane.showConfirmDialog(null, "¿Desea salir?", "Exit", dialog);
        if (result == 0) {
            System.exit(0);
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        Animator1.fadeOut(this, () -> {
            this.dispose();

            Ventana_UsuarioPrincipal ventana = new Ventana_UsuarioPrincipal(true, personaLogueada);
            ventana.setOpacity(0f);
            ventana.setVisible(true);

            Animator1.fadeIn(ventana);
        });
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed

        Animator1.fadeOut(this, () -> {
            this.dispose();

            Ventana_Usuarios ventana = new Ventana_Usuarios(true, personaLogueada);
            ventana.setOpacity(0f);
            ventana.setVisible(true);

            Animator1.fadeIn(ventana);
        });

    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        Animator1.fadeOut(this, () -> {
            this.dispose();

            Admin_Lugares ventana = new Admin_Lugares(true);
            ventana.setOpacity(0f);
            ventana.setVisible(true);

            Animator1.fadeIn(ventana);
        });


    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed


    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
 Animator1.fadeOut(this, () -> {
            this.dispose();

            Ventana_Lugares ventana = new Ventana_Lugares(true);

            GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
            if (gd.isWindowTranslucencySupported(GraphicsDevice.WindowTranslucency.TRANSLUCENT)) {
                ventana.setOpacity(0f);
            }

            ventana.setVisible(true);
            Animator1.fadeIn(ventana);
        });
       


    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed



    }//GEN-LAST:event_jButton14ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed

 Animator1.fadeOut(this, () -> {
            this.dispose();

            Admin_Lugares ventana = new Admin_Lugares(true);

            GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
            if (gd.isWindowTranslucencySupported(GraphicsDevice.WindowTranslucency.TRANSLUCENT)) {
                ventana.setOpacity(0f);
            }

            ventana.setVisible(true);
            Animator1.fadeIn(ventana);
        });

    }//GEN-LAST:event_jButton13ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Bienvenido;
    private javax.swing.JLabel ConteoUser;
    private javax.swing.JLabel ConteoUser1;
    private javax.swing.JLabel ConteoUser2;
    private javax.swing.JLabel ConteoUser3;
    private javax.swing.JLabel Cuencanas;
    private javax.swing.JLabel Dashboard;
    private javax.swing.JLabel Favoritos;
    private javax.swing.JLabel Gusto;
    private javax.swing.JLabel Huellas;
    private javax.swing.JLabel Lugares;
    private javax.swing.JLabel Lugares1;
    private javax.swing.JLabel Lugares2;
    private javax.swing.JPanel PanelUsuarios;
    private javax.swing.JPanel Reloj;
    private javax.swing.JLabel Reservas;
    private javax.swing.JLabel Rol;
    private javax.swing.JLabel Rutas;
    private javax.swing.JLabel Total;
    private javax.swing.JLabel Total1;
    private javax.swing.JLabel Total2;
    private javax.swing.JLabel Total3;
    private javax.swing.JLabel Usuarios;
    private javax.swing.JLabel Usuarios1;
    private javax.swing.JLabel Usuarios2;
    private javax.swing.JLabel Vuelta;
    private javax.swing.JLabel admin_label;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel miPanelDiseñado;
    // End of variables declaration//GEN-END:variables
}
