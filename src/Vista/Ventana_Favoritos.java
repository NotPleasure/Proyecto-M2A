/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Vista;

import Animations.Animator1;
import Design.Panel.WrapLayout;
import Design.RoundedButtonIglesiaBusqueda;
import Design.RoundedButtonInsertar;
import Design.RoundedButtonLugaresEvent;
import Design.RoundedButtonMuseoBusqueda;
import Design.RoundedButtonMuseoBusqueda1;
import Design.RoundedButtonNegocios;
import Design.RoundedButtonSalirRe;
import Design.RoundedPanelAdmin;
import Design.RoundedPanelAdminSombra;
import Design.RoundedPanelAdminSombra1;
import Design.RoundedPanelBordesResenas;
import Design.RoundedPanelFav;
import Design.RoundedPanelHoteles;
import Design.RoundedPanelResenas;
import Design.RoundedPanelStatsFavoritos;
import Design.RoundedPanelStatsFavoritosDebajo;
import Design.RoundedPannelGris;
import Modelo.Administrador;
import Modelo.AdministradorDAO;
import Modelo.Favorito;
import Modelo.FavoritoDAO;
import Modelo.IglesiaDAO;
import Modelo.IglesiaVista;
import Modelo.LugarInteresDAO;
import Modelo.LugarInteresVista;
import Modelo.MuseoDAO;
import Modelo.MuseoVista;
import Modelo.ParqueDao;
import Modelo.ParqueVista;
import Modelo.Persona;
import Modelo.SuperUsuario;
import Modelo.SuperUsuarioDAO;
import Modelo.Usuario;
import Modelo.UsuarioDAO;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.sql.SQLException;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;
import raven.glasspanepopup.GlassPanePopup;

/**
 *
 * @author USER
 */
public class Ventana_Favoritos extends javax.swing.JFrame {

    //Permitir entrar a la ventana:
    private boolean sinBordes = true;
    private FavoritoDAO favDao;
    private IglesiaDAO iglesiaDao;
    private MuseoDAO museoDao;
    private ParqueDao parqueDao;
    private int idUser;
    private List<IglesiaVista> lista;
    private int idUsuario;
    private Persona personaLogueada;
    private final Admin_Panel adminParent;
    private JComboBox<Persona> cmbUsuarios;
    private JComboBox<LugarInteresVista> cmbLugares;

    /**
     * Creates new form Ventana_Resenas
     */
    public Ventana_Favoritos(Admin_Panel adminParent, int usuario, boolean sinBordes) {
        this.idUsuario = usuario;
        this.adminParent = adminParent;

        if (sinBordes) {
            setUndecorated(true);
        }

        initComponents();

        GlassPanePopup.install(this);

        //ScrollPane:
        configurarScrollPanel();
        //Cargar los cards:
        cargarFavoritos();

        //Fuentes:
        Huellas.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 20));
        Cuencanas.setFont(new Font("Segoe UI Light", Font.PLAIN, 20));
        Des.setFont(new Font("Segoe UI Light", Font.PLAIN, 20));
        De.setFont(new Font("Segoe UI Light", Font.PLAIN, 20));
        Mis.setFont(new Font("Segoe UI Bold", Font.PLAIN, 22));
        Igle.setFont(new Font("Segoe UI Light", Font.PLAIN, 14));
        lblTotalFav.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 32));
        Lug.setFont(new Font("Segoe UI Light", Font.PLAIN, 16));
        lblFavoritosMes.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 32));
        este.setFont(new Font("Segoe UI Light", Font.PLAIN, 16));
        Cate.setFont(new Font("Segoe UI Bold", Font.PLAIN, 22));

        //Extender la ventana al máximo: 
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        // Para pantalla completa:
        setExtendedState(Ventana_Principal.MAXIMIZED_BOTH);

        // Refrescar al recuperar el foco
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowActivated(java.awt.event.WindowEvent evt) {
                cargarFavoritos();
            }
        });

        //Llamar a todos los botones:
        btnIglesias.addActionListener(e -> cargarFavoritosIglesias());
        btnMuseos.addActionListener(e -> cargarFavoritosMuseos());
        btnParques.addActionListener(e -> cargarFavoritosParques());
        btnTodos.addActionListener(e -> cargarTodosLosFavoritos());

    }

    //Cargar los cards de Iglesias:
    private void cargarIglesiasEnPanel(List<IglesiaVista> iglesias) {

        panelCards.setLayout(new WrapLayout(FlowLayout.CENTER, 15, 15));

        for (IglesiaVista vista : iglesias) {
            PanelCardIglesia card = new PanelCardIglesia(vista);
            panelCards.add(card);

        }

        panelCards.revalidate();
        panelCards.repaint();
    }

    /*
    //Cargar los cards de los parques: 
    private void cargarParquesEnPanel(List<ParqueVista> parques) {
        for (ParqueVista vista : parques) {
            PanelCardParque card = new PanelCardParque(
                    vista.getId(),
                    vista.getNombre(),
                    vista.getEntidad_gestora(),
                    vista.getSuperficie(),
                    vista.getImagenPrincipal()
            );

            panelCards.add(card);
        }
    }
     */
    private void cargarFavoritos() {
        panelCards.removeAll();
        try {
            FavoritoDAO favDao = new FavoritoDAO();
            IglesiaDAO iDao = new IglesiaDAO();
            MuseoDAO museoDao = new MuseoDAO();
            ParqueDao parqueDao = new ParqueDao();

            List<Integer> favIglesias = favDao.obtenerFavoritosIglesias(idUsuario);
            for (int igId : favIglesias) {
                IglesiaVista iv = iDao.obtenerPorId(igId);
                panelCards.add(new PanelCardIglesia(iv));
            }

            List<Integer> favMuseos = favDao.obtenerFavoritosMuseos(idUsuario);
            for (int mId : favMuseos) {
                MuseoVista mv = museoDao.obtenerPorId(mId);
                if (mv != null) {
                    panelCards.add(new PanelCardMuseo(mv));
                }
            }
            List<Integer> favParques = favDao.obtenerFavoritosParques(idUsuario);
            for (int pId : favParques) {
                ParqueVista pv = parqueDao.obtenerPorId(pId);
                if (pv != null) {
                    panelCards.add(new PanelCardParque(pv));
                }
            }

            int total = favDao.contarFavoritos(idUsuario);
            int esteMes = favDao.contarFavoritosMes(idUsuario);

            lblTotalFav.setText(String.valueOf(total));
            lblFavoritosMes.setText(String.valueOf(esteMes));

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,
                    "Error al cargar favoritos: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }

        panelCards.revalidate();
        panelCards.repaint();
    }

    /*
    //Cargar las cards de los museos:
    private void cargarMuseosEnPanel(List<MuseoVista> museos) {
        for (MuseoVista vista : museos) {
            PanelCardMuseo card = new PanelCardMuseo(
                    vista.getId(),
                    vista.getNombre(),
                    vista.getHoraApertura() != null ? vista.getHoraApertura().toString() : "",
                    vista.getHoraCierre() != null ? vista.getHoraCierre().toString() : "",
                    vista.getImagenPrincipal()
            );

            panelCards.add(card);
        }
    }
     */
    //Configurar el ScrollPane para el panel:
    private void configurarScrollPanel() {
        panelCards.setLayout(new WrapLayout(FlowLayout.CENTER, 15, 15));

        // Crear JScrollPane que contiene a panelCards
        JScrollPane scroll = new JScrollPane(panelCards);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        // Limpiar panelCentro y poner el scroll dentro
        panelCentro.setLayout(new BorderLayout());
        panelCentro.removeAll();
        panelCentro.add(scroll, BorderLayout.CENTER);
        panelCentro.revalidate();
        panelCentro.repaint();
    }

    //Preparar los filtros:
    private void cargarFavoritosIglesias() {
        panelCards.removeAll();
        try {
            FavoritoDAO favDao = new FavoritoDAO();
            IglesiaDAO iDao = new IglesiaDAO();

            List<Integer> favIgs = favDao.obtenerFavoritosIglesias(idUsuario);
            for (int idLI : favIgs) {
                IglesiaVista iv = iDao.obtenerPorId(idLI);
                if (iv != null) {
                    panelCards.add(new PanelCardIglesia(iv));
                }
            }
        } catch (SQLException e) {
            mostrarError(e);
        }
        refrescarPanel();
    }

    private void cargarFavoritosMuseos() {
        panelCards.removeAll();
        try {
            FavoritoDAO favDao = new FavoritoDAO();
            MuseoDAO mDao = new MuseoDAO();

            List<Integer> favMs = favDao.obtenerFavoritosMuseos(idUsuario);
            for (int idLI : favMs) {
                MuseoVista mv = mDao.obtenerPorId(idLI);
                if (mv != null) {
                    panelCards.add(new PanelCardMuseo(mv));
                }
            }
        } catch (SQLException e) {
            mostrarError(e);
        }
        refrescarPanel();
    }

    private void cargarFavoritosParques() {
        panelCards.removeAll();
        try {
            FavoritoDAO favDao = new FavoritoDAO();
            ParqueDao pDao = new ParqueDao();

            List<Integer> favPs = favDao.obtenerFavoritosParques(idUsuario);
            for (int idLI : favPs) {
                ParqueVista pv = pDao.obtenerPorId(idLI);
                if (pv != null) {
                    panelCards.add(new PanelCardParque(pv));
                }
            }
        } catch (SQLException e) {
            mostrarError(e);
        }
        refrescarPanel();
    }

    private void cargarTodosLosFavoritos() {
        panelCards.removeAll();
        cargarFavoritos();
        refrescarPanel();
    }

    private void refrescarPanel() {
        panelCards.revalidate();
        panelCards.repaint();
    }

    private void mostrarError(SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(
                this,
                "Error al cargar favoritos: " + e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE
        );
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelPrincipal = new javax.swing.JPanel();
        jPanel3 = new RoundedPanelAdminSombra1();
        jButton3 = new javax.swing.JButton();
        Huellas = new javax.swing.JLabel();
        Cuencanas = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        Des = new javax.swing.JLabel();
        De = new javax.swing.JLabel();
        jPanel2 = new RoundedPanelStatsFavoritos();
        jLabel2 = new javax.swing.JLabel();
        Mis = new javax.swing.JLabel();
        jPanel4 = new RoundedPanelFav();
        lblTotalFav = new javax.swing.JLabel();
        Lug = new javax.swing.JLabel();
        Igle = new javax.swing.JLabel();
        jPanel6 = new RoundedPanelFav();
        lblFavoritosMes = new javax.swing.JLabel();
        este = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        panelCentro = new RoundedPanelStatsFavoritosDebajo();
        jScrollPane1 = new javax.swing.JScrollPane();
        panelCards = new RoundedPanelStatsFavoritosDebajo();
        Cate = new javax.swing.JLabel();
        btnParques = new RoundedButtonIglesiaBusqueda("");
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        btnMuseos = new RoundedButtonMuseoBusqueda("");
        jLabel5 = new javax.swing.JLabel();
        btnIglesias = new RoundedButtonMuseoBusqueda1("");
        jLabel6 = new javax.swing.JLabel();
        btnTodos = new RoundedButtonSalirRe("");
        btnAgregarFavorito = new RoundedButtonInsertar("");
        jButton1 = new RoundedButtonSalirRe("");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelPrincipal.setBackground(new java.awt.Color(236, 236, 236));
        panelPrincipal.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/Fondo Imagen Favoritos.png"))); // NOI18N
        jButton3.setBorderPainted(false);
        jButton3.setContentAreaFilled(false);
        jButton3.setFocusPainted(false);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 14, 70, 70));

        Huellas.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        Huellas.setForeground(new java.awt.Color(44, 70, 92));
        Huellas.setText("Huellas ");
        jPanel3.add(Huellas, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 26, -1, -1));

        Cuencanas.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        Cuencanas.setForeground(new java.awt.Color(44, 70, 92));
        Cuencanas.setText("Cuencanas");
        jPanel3.add(Cuencanas, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 42, -1, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/Descubre.png"))); // NOI18N
        jPanel3.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1180, 10, 70, 70));

        Des.setBackground(new java.awt.Color(132, 132, 132));
        Des.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        Des.setForeground(new java.awt.Color(132, 132, 132));
        Des.setText("Descubre la belleza ");
        jPanel3.add(Des, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 25, -1, -1));

        De.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        De.setForeground(new java.awt.Color(132, 132, 132));
        De.setText("de Cuenca.");
        jPanel3.add(De, new org.netbeans.lib.awtextra.AbsoluteConstraints(1080, 42, -1, -1));

        panelPrincipal.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, 1310, 100));

        jPanel2.setBackground(new java.awt.Color(41, 58, 75));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/Cora 1.png"))); // NOI18N
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 24, 70, 60));

        Mis.setFont(new java.awt.Font("Segoe UI", 0, 22)); // NOI18N
        Mis.setForeground(new java.awt.Color(255, 255, 255));
        Mis.setText("Mis Lugares Favoritos");
        jPanel2.add(Mis, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 28, -1, -1));

        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblTotalFav.setFont(new java.awt.Font("Segoe UI", 0, 32)); // NOI18N
        lblTotalFav.setForeground(new java.awt.Color(255, 255, 255));
        jPanel4.add(lblTotalFav, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 0, 40, 50));

        Lug.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        Lug.setForeground(new java.awt.Color(255, 255, 255));
        Lug.setText("Lugares Favoritos");
        jPanel4.add(Lug, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 50, -1, -1));

        jPanel2.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 10, 190, 90));

        Igle.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        Igle.setForeground(new java.awt.Color(255, 255, 255));
        Igle.setText("Iglesias, Museos y Parques.");
        Igle.addHierarchyListener(new java.awt.event.HierarchyListener() {
            public void hierarchyChanged(java.awt.event.HierarchyEvent evt) {
                IgleHierarchyChanged(evt);
            }
        });
        jPanel2.add(Igle, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 54, -1, -1));

        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblFavoritosMes.setFont(new java.awt.Font("Segoe UI", 0, 32)); // NOI18N
        lblFavoritosMes.setForeground(new java.awt.Color(255, 255, 255));
        jPanel6.add(lblFavoritosMes, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 10, 50, 40));

        este.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        este.setForeground(new java.awt.Color(255, 255, 255));
        este.setText("Este Mes");
        jPanel6.add(este, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 50, -1, -1));

        jPanel2.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(1090, 10, 190, 90));

        panelPrincipal.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, 1310, 110));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/lapiz (1).png"))); // NOI18N
        panelPrincipal.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 690, 50, 80));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/cerrar-sesion (1).png"))); // NOI18N
        panelPrincipal.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(1080, 690, 50, 60));

        jScrollPane1.setViewportView(panelCards);

        javax.swing.GroupLayout panelCentroLayout = new javax.swing.GroupLayout(panelCentro);
        panelCentro.setLayout(panelCentroLayout);
        panelCentroLayout.setHorizontalGroup(
            panelCentroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        panelCentroLayout.setVerticalGroup(
            panelCentroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCentroLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 359, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 1, Short.MAX_VALUE))
        );

        panelPrincipal.add(panelCentro, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 310, 1300, 360));

        Cate.setFont(new java.awt.Font("Segoe UI", 0, 22)); // NOI18N
        Cate.setForeground(new java.awt.Color(52, 73, 94));
        Cate.setText("Filtrar por:");
        panelPrincipal.add(Cate, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 252, -1, -1));

        btnParques.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        btnParques.setText("Parques");
        btnParques.setBorderPainted(false);
        btnParques.setContentAreaFilled(false);
        btnParques.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        panelPrincipal.add(btnParques, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 254, 160, 27));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/museo (1) (1).png"))); // NOI18N
        panelPrincipal.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 254, 50, 30));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/parque-nacional (2).png"))); // NOI18N
        panelPrincipal.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 254, 50, 30));

        btnMuseos.setText("Museos");
        btnMuseos.setBorderPainted(false);
        btnMuseos.setContentAreaFilled(false);
        btnMuseos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnMuseos.setFocusPainted(false);
        panelPrincipal.add(btnMuseos, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 254, 160, 27));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/igle uno.png"))); // NOI18N
        panelPrincipal.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 254, 30, 30));

        btnIglesias.setText("Iglesias");
        btnIglesias.setBorderPainted(false);
        btnIglesias.setContentAreaFilled(false);
        btnIglesias.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnIglesias.setFocusPainted(false);
        panelPrincipal.add(btnIglesias, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 254, 160, 27));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/Filtro todos.png"))); // NOI18N
        panelPrincipal.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 254, -1, 30));

        btnTodos.setText("Todos");
        btnTodos.setBorderPainted(false);
        btnTodos.setContentAreaFilled(false);
        btnTodos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnTodos.setFocusPainted(false);
        panelPrincipal.add(btnTodos, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 254, 160, 27));

        btnAgregarFavorito.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        btnAgregarFavorito.setForeground(new java.awt.Color(255, 255, 255));
        btnAgregarFavorito.setText("Marcar un favorito");
        btnAgregarFavorito.setBorderPainted(false);
        btnAgregarFavorito.setContentAreaFilled(false);
        btnAgregarFavorito.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAgregarFavorito.setFocusPainted(false);
        btnAgregarFavorito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarFavoritoActionPerformed(evt);
            }
        });
        panelPrincipal.add(btnAgregarFavorito, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 716, 180, 30));

        jButton1.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Salir");
        jButton1.setBorderPainted(false);
        jButton1.setContentAreaFilled(false);
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.setFocusPainted(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        panelPrincipal.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1150, 704, 180, 30));

        getContentPane().add(panelPrincipal, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1370, 860));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void IgleHierarchyChanged(java.awt.event.HierarchyEvent evt) {//GEN-FIRST:event_IgleHierarchyChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_IgleHierarchyChanged

    private void btnAgregarFavoritoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarFavoritoActionPerformed

        try {
            DefaultComboBoxModel<Persona> mu = new DefaultComboBoxModel<>();
            for (Usuario u : new UsuarioDAO().listarTodos()) {
                mu.addElement(u);
            }
            for (SuperUsuario s : new SuperUsuarioDAO().listarTodos()) {
                mu.addElement(s);
            }
            for (Administrador a : new AdministradorDAO().listarTodos()) {
                mu.addElement(a);
            }
            JComboBox<Persona> cmbUsuarios = new JComboBox<>(mu);

            cmbUsuarios.setRenderer(new ListCellRenderer<Persona>() {
                private final DefaultListCellRenderer defaultRenderer = new DefaultListCellRenderer();

                @Override
                public Component getListCellRendererComponent(
                        JList<? extends Persona> list,
                        Persona value,
                        int index,
                        boolean isSelected,
                        boolean cellHasFocus) {
                    String text = (value == null) ? "" : value.getUsuario();
                    return defaultRenderer.getListCellRendererComponent(
                            list, text, index, isSelected, cellHasFocus
                    );
                }
            });

            DefaultComboBoxModel<LugarInteresVista> ml = new DefaultComboBoxModel<>();
            for (LugarInteresVista li : new LugarInteresDAO().obtenerLugares()) {
                ml.addElement(li);
            }
            JComboBox<LugarInteresVista> cmbLugares = new JComboBox<>(ml);

            JPanel panel = new JPanel(new GridLayout(2, 2, 5, 5));
            panel.add(new JLabel("Usuario:"));
            panel.add(cmbUsuarios);
            panel.add(new JLabel("Lugar:"));
            panel.add(cmbLugares);

            int opcion = JOptionPane.showConfirmDialog(
                    this,
                    panel,
                    "Agregar favorito",
                    JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.PLAIN_MESSAGE
            );

            if (opcion != JOptionPane.OK_OPTION) {
                return;
            }

            Persona elegido = (Persona) cmbUsuarios.getSelectedItem();
            LugarInteresVista lugar = (LugarInteresVista) cmbLugares.getSelectedItem();

            if (elegido == null || lugar == null) {
                JOptionPane.showMessageDialog(this,
                        "Debes seleccionar usuario y lugar",
                        "Atención",
                        JOptionPane.WARNING_MESSAGE
                );
                return;
            }

            FavoritoDAO favDao = new FavoritoDAO();
            boolean creado = favDao.agregarFavorito(elegido.getIdPersona(), lugar.getId());
            if (creado) {
                GlassPanePopup.showPopup(new Ventana_AgregadoFavorito());
            } else {
                JOptionPane.showMessageDialog(this,
                        "Ese favorito ya existía.",
                        "Información",
                        JOptionPane.INFORMATION_MESSAGE
                );
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this,
                    "Error al agregar favorito:\n" + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }

    }//GEN-LAST:event_btnAgregarFavoritoActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Animator1.fadeOut(this, () -> {
            this.dispose();

            adminParent.setOpacity(0f);
            adminParent.setVisible(true);

            Animator1.fadeIn(adminParent);
        });


    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Cate;
    private javax.swing.JLabel Cuencanas;
    private javax.swing.JLabel De;
    private javax.swing.JLabel Des;
    private javax.swing.JLabel Huellas;
    private javax.swing.JLabel Igle;
    private javax.swing.JLabel Lug;
    private javax.swing.JLabel Mis;
    private javax.swing.JButton btnAgregarFavorito;
    private javax.swing.JButton btnIglesias;
    private javax.swing.JButton btnMuseos;
    private javax.swing.JButton btnParques;
    private javax.swing.JButton btnTodos;
    private javax.swing.JLabel este;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblFavoritosMes;
    private javax.swing.JLabel lblTotalFav;
    private javax.swing.JPanel panelCards;
    private javax.swing.JPanel panelCentro;
    private javax.swing.JPanel panelPrincipal;
    // End of variables declaration//GEN-END:variables
}
