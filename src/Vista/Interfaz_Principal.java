/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Design.RoundedBorder;
import java.awt.Font;
import javax.swing.JFrame;
import Design.RoundedButtonCerrarSesion;
import Design.RoundedButtonDetalles;
import Design.RoundedButtonInicio;
import Design.RoundedButtonLugares;
import Design.RoundedTextField;
import java.awt.Color;
import javax.swing.JTextField;
import Design.RoundedPanel;
import java.awt.BorderLayout;
import javax.swing.JPanel;

/**
 *
 * @author USER
 */
public class Interfaz_Principal extends javax.swing.JFrame {

    private String BuscarLugares = "Buscar Lugares, Hoteles, Restaurantes, Cafeterías...";

    /**
     * Creates new form Interfaz_Principal
     */
    public Interfaz_Principal() {
        initComponents();

        //Ampliar al máximo la ventana:
        this.setExtendedState(6);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);

        //Fuentes de los labels, jButtons y Jfileds:
        Huellas.setFont(new Font("Open Sans Bold", Font.PLAIN, 30));
        Cuencanas.setFont(new Font("Open Sans", Font.PLAIN, 30));
        Bienvenido.setFont(new Font("Open Sans Bold", Font.PLAIN, 40));
        CerrarSesión.setFont(new Font("Open Sans Bold", Font.PLAIN, 15));
        Buscar.setFont(new Font("Open Sans", Font.PLAIN, 14));
        Catedral.setFont(new Font("Open Sans Bold", Font.PLAIN, 15));
        Cajas.setFont(new Font("Open Sans", Font.PLAIN, 15));
        estrellas.setFont(new Font("Open Sans", Font.PLAIN, 12));
        HotelYanuncay.setFont(new Font("Open Sans Bold", Font.PLAIN, 15));
        ParqueNacional.setFont(new Font("Open Sans Bold", Font.PLAIN, 15));
        estrellas1.setFont(new Font("Open Sans", Font.PLAIN, 12));
        Catedral2.setFont(new Font("Open Sans Bold", Font.PLAIN, 15));
        Cajas.setFont(new Font("Open Sans Bold", Font.PLAIN, 15));
        ParqueMadre1.setFont(new Font("Open Sans Bold", Font.PLAIN, 15));
        Madre1.setFont(new Font("Open Sans Bold", Font.PLAIN, 15));
        estrellas2.setFont(new Font("Open Sans", Font.PLAIN, 12));
        SanRafael1.setFont(new Font("Open Sans Bold", Font.PLAIN, 15));
        HotelYanuncay.setFont(new Font("Open Sans Bold", Font.PLAIN, 15));
        Mirador.setFont(new Font("Open Sans Bold", Font.PLAIN, 15));
        Inicio.setFont(new Font("Open Sans Bold", Font.PLAIN, 18));
        Restaurantes.setFont(new Font("Open Sans Bold", Font.PLAIN, 18));
        Cafeterías.setFont(new Font("Open Sans Bold", Font.PLAIN, 18));
        Principal_Lugar.setFont(new Font("Open Sans Bold", Font.PLAIN, 18));
        Hoteles_Lugar.setFont(new Font("Open Sans Bold", Font.PLAIN, 18));

        //Centrar TextField "Buscar":
        Buscar.setHorizontalAlignment(JTextField.CENTER);
 
        //Poner placeholder al TextField "Buscar":
        ponerPlaceholder(Buscar, BuscarLugares);
        
    }

    private void ponerPlaceholder(javax.swing.JTextField campo, String textoPorDefecto) {
        campo.setText(textoPorDefecto);
        campo.setForeground(new Color(180, 180, 180));

        campo.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent e) {
                if (campo.getText().equals(textoPorDefecto)) {
                    campo.setText("");
                    campo.setForeground(new Color(180, 180, 180));
                }
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent e) {
                if (campo.getText().isEmpty()) {
                    campo.setForeground(new Color(180, 180, 180));
                    campo.setText(textoPorDefecto);
                }
            }
        });
    }

    private void ponerPlaceholder(javax.swing.JPasswordField campo, String textoPorDefecto) {
        campo.setEchoChar((char) 0);
        campo.setText(textoPorDefecto);
        campo.setForeground(new Color(180, 180, 180));
        campo.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent e) {
                String passText = new String(campo.getPassword());
                if (passText.equals(textoPorDefecto)) {
                    campo.setText("");
                    campo.setForeground(new Color(180, 180, 180));
                    campo.setEchoChar('•');
                }
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent e) {
                String passText = new String(campo.getPassword());
                if (passText.isEmpty()) {
                    campo.setEchoChar((char) 0);
                    campo.setForeground(new Color(180, 180, 180));
                    campo.setText(textoPorDefecto);
                }
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

        Panel_Menú = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        Huellas = new javax.swing.JLabel();
        Cuencanas = new javax.swing.JLabel();
        Principal = new javax.swing.JButton();
        Hoteles = new javax.swing.JButton();
        Principal_Lugar = new javax.swing.JLabel();
        Inicio = new RoundedButtonInicio("");
        RestauranteIcon = new javax.swing.JButton();
        CafeteríaIcon = new javax.swing.JButton();
        Hoteles_Lugar = new javax.swing.JLabel();
        jButton1 = new RoundedButtonInicio("");
        Restaurantes = new javax.swing.JLabel();
        jButton2 = new RoundedButtonInicio("");
        Cafeterías = new javax.swing.JLabel();
        jButton4 = new RoundedButtonInicio("");
        Fondo = new javax.swing.JButton();
        Inicio1 =  new RoundedButtonInicio("");
        RestauranteIcon1 = new javax.swing.JButton();
        Inicio2 = new RoundedButtonInicio("");
        Segundo_Panel = new javax.swing.JPanel();
        Bienvenido = new javax.swing.JLabel();
        Image_User = new javax.swing.JButton();
        Lupa = new javax.swing.JButton();
        CerrarSesión = new RoundedButtonCerrarSesion("");
        Buscar =  new RoundedTextField(20, 30, 30);
        Panel_Lugares = new Design.RoundedPanel();
        Estrellas = new javax.swing.JButton();
        VerDetalles = new RoundedButtonDetalles("");
        Catedral_Cuenca = new RoundedButtonLugares("");
        Catedral = new javax.swing.JLabel();
        Cajas = new javax.swing.JLabel();
        estrellas = new javax.swing.JLabel();
        Hotel_Rafael = new RoundedButtonLugares("");
        HotelYanuncay = new javax.swing.JLabel();
        ParquedelaMadre = new RoundedButtonLugares("");
        ParqueNacional = new javax.swing.JLabel();
        estrellas1 = new javax.swing.JLabel();
        Estrellas1 = new javax.swing.JButton();
        VerDetalles1 = new RoundedButtonDetalles("");
        Catedral2 = new javax.swing.JLabel();
        jButton22 = new RoundedButtonLugares("");
        ParqueMadre1 = new javax.swing.JLabel();
        Madre1 = new javax.swing.JLabel();
        jButton28 = new RoundedButtonLugares("");
        jButton16 = new RoundedButtonLugares("");
        estrellas2 = new javax.swing.JLabel();
        Estrellas2 = new javax.swing.JButton();
        VerDetalles2 = new RoundedButtonDetalles("");
        VerDetalles3 = new RoundedButtonDetalles("");
        VerDetalles4 = new RoundedButtonDetalles("");
        SanRafael1 = new javax.swing.JLabel();
        Mirador = new javax.swing.JLabel();
        VerDetalles5 = new RoundedButtonDetalles("");
        content = new javax.swing.JPanel();
        Fondo_derecha = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Panel_Menú.setBackground(new java.awt.Color(255, 204, 204));
        Panel_Menú.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/Copilot_20250610_114322 (3).png"))); // NOI18N
        jButton3.setBorderPainted(false);
        jButton3.setContentAreaFilled(false);
        jButton3.setFocusPainted(false);
        Panel_Menú.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 10, 60, 70));

        Huellas.setFont(new java.awt.Font("Dialog", 1, 30)); // NOI18N
        Huellas.setForeground(new java.awt.Color(255, 255, 255));
        Huellas.setText("Huellas ");
        Panel_Menú.add(Huellas, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 15, -1, -1));

        Cuencanas.setFont(new java.awt.Font("Dialog", 1, 30)); // NOI18N
        Cuencanas.setForeground(new java.awt.Color(255, 255, 255));
        Cuencanas.setText("Cuencanas");
        Panel_Menú.add(Cuencanas, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 43, -1, -1));

        Principal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/Lugar ICON.png"))); // NOI18N
        Principal.setBorderPainted(false);
        Principal.setContentAreaFilled(false);
        Principal.setFocusPainted(false);
        Panel_Menú.add(Principal, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 154, 60, 50));

        Hoteles.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/Hotel-removebg-preview (1).png"))); // NOI18N
        Hoteles.setBorderPainted(false);
        Hoteles.setContentAreaFilled(false);
        Hoteles.setFocusPainted(false);
        Panel_Menú.add(Hoteles, new org.netbeans.lib.awtextra.AbsoluteConstraints(54, 244, 50, 50));

        Principal_Lugar.setForeground(new java.awt.Color(255, 255, 255));
        Principal_Lugar.setText("Principal");
        Panel_Menú.add(Principal_Lugar, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 167, -1, -1));

        Inicio.setBorderPainted(false);
        Inicio.setContentAreaFilled(false);
        Inicio.setFocusPainted(false);
        Inicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InicioActionPerformed(evt);
            }
        });
        Panel_Menú.add(Inicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(-20, 150, 440, 60));

        RestauranteIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/Restaurante-removebg-preview (1).png"))); // NOI18N
        RestauranteIcon.setBorderPainted(false);
        RestauranteIcon.setContentAreaFilled(false);
        RestauranteIcon.setFocusPainted(false);
        Panel_Menú.add(RestauranteIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 332, 60, 60));

        CafeteríaIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/Cafetería-removebg-preview (2).png"))); // NOI18N
        CafeteríaIcon.setBorderPainted(false);
        CafeteríaIcon.setContentAreaFilled(false);
        CafeteríaIcon.setFocusPainted(false);
        Panel_Menú.add(CafeteríaIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 410, 60, 70));

        Hoteles_Lugar.setForeground(new java.awt.Color(255, 255, 255));
        Hoteles_Lugar.setText("Hoteles");
        Panel_Menú.add(Hoteles_Lugar, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 256, -1, -1));
        Panel_Menú.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 240, 420, 60));

        Restaurantes.setForeground(new java.awt.Color(255, 255, 255));
        Restaurantes.setText("Restaurantes");
        Panel_Menú.add(Restaurantes, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 348, -1, -1));
        Panel_Menú.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 330, 420, 60));

        Cafeterías.setForeground(new java.awt.Color(255, 255, 255));
        Cafeterías.setText("Cafeterías");
        Panel_Menú.add(Cafeterías, new org.netbeans.lib.awtextra.AbsoluteConstraints(133, 436, -1, -1));
        Panel_Menú.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 420, 420, 60));

        Fondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/Imagen_Menú.png"))); // NOI18N
        Fondo.setBorderPainted(false);
        Fondo.setContentAreaFilled(false);
        Fondo.setFocusPainted(false);
        Panel_Menú.add(Fondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 420, 760));

        Inicio1.setText("Inicio");
        Panel_Menú.add(Inicio1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 158, 190, 40));

        RestauranteIcon1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/Restaurante-removebg-preview (1).png"))); // NOI18N
        RestauranteIcon1.setBorderPainted(false);
        RestauranteIcon1.setContentAreaFilled(false);
        RestauranteIcon1.setFocusPainted(false);
        Panel_Menú.add(RestauranteIcon1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 330, 60, 60));

        Inicio2.setBorderPainted(false);
        Inicio2.setContentAreaFilled(false);
        Inicio2.setFocusPainted(false);
        Panel_Menú.add(Inicio2, new org.netbeans.lib.awtextra.AbsoluteConstraints(-20, 150, 440, 60));

        getContentPane().add(Panel_Menú, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 770));

        Segundo_Panel.setBackground(new java.awt.Color(204, 255, 204));
        Segundo_Panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Bienvenido.setForeground(new java.awt.Color(0, 0, 0));
        Bienvenido.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Bienvenido.setText("Bienvenido");
        Segundo_Panel.add(Bienvenido, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, -1, 300, 80));

        Image_User.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/User (111) (1).png"))); // NOI18N
        Image_User.setBorderPainted(false);
        Image_User.setContentAreaFilled(false);
        Image_User.setFocusPainted(false);
        Segundo_Panel.add(Image_User, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 10, 60, 60));

        Lupa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/Lupa__Ícono_-removebg-preview (1).png"))); // NOI18N
        Lupa.setBorderPainted(false);
        Lupa.setContentAreaFilled(false);
        Lupa.setFocusPainted(false);
        Segundo_Panel.add(Lupa, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 75, 50, 50));

        CerrarSesión.setForeground(new java.awt.Color(255, 255, 255));
        CerrarSesión.setText("Cerrar Sesión");
        Segundo_Panel.add(CerrarSesión, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 24, 140, 30));

        Buscar.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        Buscar.setForeground(new java.awt.Color(180, 180, 180));
        Buscar.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Buscar.setText("Buscar Lugares, Hoteles, Restaurantes...");
        Buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BuscarActionPerformed(evt);
            }
        });
        Segundo_Panel.add(Buscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 450, 40));

        Panel_Lugares.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Estrellas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/5 estrellas (1).jpg"))); // NOI18N
        Estrellas.setBorderPainted(false);
        Estrellas.setContentAreaFilled(false);
        Estrellas.setFocusPainted(false);
        Panel_Lugares.add(Estrellas, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 240, 150, 30));

        VerDetalles.setText("Ver Detalles");
        VerDetalles.setBorderPainted(false);
        VerDetalles.setContentAreaFilled(false);
        VerDetalles.setFocusPainted(false);
        Panel_Lugares.add(VerDetalles, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 530, -1, -1));

        Catedral_Cuenca.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/Catedral (1).jpg"))); // NOI18N
        Catedral_Cuenca.setBorderPainted(false);
        Catedral_Cuenca.setContentAreaFilled(false);
        Catedral_Cuenca.setFocusPainted(false);
        Panel_Lugares.add(Catedral_Cuenca, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 250, 160));

        Catedral.setForeground(new java.awt.Color(44, 38, 109));
        Catedral.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Catedral.setText("Catedral de la Inmaculada");
        Panel_Lugares.add(Catedral, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 190, -1, -1));

        Cajas.setForeground(new java.awt.Color(158, 149, 204));
        Cajas.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Cajas.setText("Cajas.");
        Panel_Lugares.add(Cajas, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 210, -1, -1));

        estrellas.setText("5.0");
        Panel_Lugares.add(estrellas, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 250, -1, -1));

        Hotel_Rafael.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/Hotel Rafael (1).jpg"))); // NOI18N
        Hotel_Rafael.setBorderPainted(false);
        Hotel_Rafael.setContentAreaFilled(false);
        Hotel_Rafael.setFocusPainted(false);
        Hotel_Rafael.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Hotel_RafaelActionPerformed(evt);
            }
        });
        Panel_Lugares.add(Hotel_Rafael, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 330, 260, 160));

        HotelYanuncay.setForeground(new java.awt.Color(44, 38, 109));
        HotelYanuncay.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        HotelYanuncay.setText("Hotel Yanuncay");
        Panel_Lugares.add(HotelYanuncay, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 500, -1, -1));

        ParquedelaMadre.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/Parque de la madre (1).jpg"))); // NOI18N
        ParquedelaMadre.setBorderPainted(false);
        ParquedelaMadre.setContentAreaFilled(false);
        ParquedelaMadre.setFocusPainted(false);
        Panel_Lugares.add(ParquedelaMadre, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 20, 270, 160));

        ParqueNacional.setForeground(new java.awt.Color(44, 38, 109));
        ParqueNacional.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ParqueNacional.setText("Parque Nacional el");
        Panel_Lugares.add(ParqueNacional, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 190, -1, -1));

        estrellas1.setText("5.0");
        Panel_Lugares.add(estrellas1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 250, -1, -1));

        Estrellas1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/5 estrellas (1).jpg"))); // NOI18N
        Estrellas1.setBorderPainted(false);
        Estrellas1.setContentAreaFilled(false);
        Estrellas1.setFocusPainted(false);
        Panel_Lugares.add(Estrellas1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 240, 150, 30));

        VerDetalles1.setText("Ver Detalles");
        VerDetalles1.setBorderPainted(false);
        VerDetalles1.setContentAreaFilled(false);
        VerDetalles1.setFocusPainted(false);
        Panel_Lugares.add(VerDetalles1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 530, -1, -1));

        Catedral2.setForeground(new java.awt.Color(158, 149, 204));
        Catedral2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Catedral2.setText("Concepción de Cuenca.");
        Panel_Lugares.add(Catedral2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 210, -1, -1));

        jButton22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/ParqueNacionalElCajas (1).jpg"))); // NOI18N
        jButton22.setBorderPainted(false);
        jButton22.setContentAreaFilled(false);
        jButton22.setFocusPainted(false);
        Panel_Lugares.add(jButton22, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 20, 270, 160));

        ParqueMadre1.setForeground(new java.awt.Color(44, 38, 109));
        ParqueMadre1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ParqueMadre1.setText("Parque de la");
        Panel_Lugares.add(ParqueMadre1, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 190, -1, -1));

        Madre1.setForeground(new java.awt.Color(158, 149, 204));
        Madre1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Madre1.setText("Madre.");
        Panel_Lugares.add(Madre1, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 210, -1, -1));

        jButton28.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/Hotel Yanuncay (2).jpg"))); // NOI18N
        jButton28.setBorderPainted(false);
        jButton28.setContentAreaFilled(false);
        jButton28.setFocusPainted(false);
        Panel_Lugares.add(jButton28, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 330, 270, 160));

        jButton16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/Mirador de Turi.jpg"))); // NOI18N
        jButton16.setBorderPainted(false);
        jButton16.setContentAreaFilled(false);
        jButton16.setFocusPainted(false);
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });
        Panel_Lugares.add(jButton16, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 330, 270, 160));

        estrellas2.setText("5.0");
        Panel_Lugares.add(estrellas2, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 250, -1, -1));

        Estrellas2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/5 estrellas (1).jpg"))); // NOI18N
        Estrellas2.setBorderPainted(false);
        Estrellas2.setContentAreaFilled(false);
        Estrellas2.setFocusPainted(false);
        Panel_Lugares.add(Estrellas2, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 240, 150, 30));

        VerDetalles2.setText("Ver Detalles");
        VerDetalles2.setBorderPainted(false);
        VerDetalles2.setContentAreaFilled(false);
        VerDetalles2.setFocusPainted(false);
        Panel_Lugares.add(VerDetalles2, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 530, -1, -1));

        VerDetalles3.setText("Ver Detalles");
        VerDetalles3.setBorderPainted(false);
        VerDetalles3.setContentAreaFilled(false);
        VerDetalles3.setFocusPainted(false);
        Panel_Lugares.add(VerDetalles3, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 280, -1, -1));

        VerDetalles4.setText("Ver Detalles");
        VerDetalles4.setBorderPainted(false);
        VerDetalles4.setContentAreaFilled(false);
        VerDetalles4.setFocusPainted(false);
        Panel_Lugares.add(VerDetalles4, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 280, -1, -1));

        SanRafael1.setForeground(new java.awt.Color(44, 38, 109));
        SanRafael1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        SanRafael1.setText("Hotel Casa San Rafael");
        Panel_Lugares.add(SanRafael1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 500, -1, -1));

        Mirador.setForeground(new java.awt.Color(44, 38, 109));
        Mirador.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Mirador.setText("Mirador de Turi");
        Mirador.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Panel_Lugares.add(Mirador, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 500, -1, -1));

        VerDetalles5.setText("Ver Detalles");
        VerDetalles5.setBorderPainted(false);
        VerDetalles5.setContentAreaFilled(false);
        VerDetalles5.setFocusPainted(false);
        Panel_Lugares.add(VerDetalles5, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 280, -1, -1));

        Segundo_Panel.add(Panel_Lugares, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, 920, 570));
        Segundo_Panel.add(content, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 960, 720));

        Fondo_derecha.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/Menú-Fondo.png"))); // NOI18N
        Fondo_derecha.setBorderPainted(false);
        Fondo_derecha.setContentAreaFilled(false);
        Fondo_derecha.setFocusPainted(false);
        Segundo_Panel.add(Fondo_derecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 960, 754));

        getContentPane().add(Segundo_Panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 0, 960, 760));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BuscarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BuscarActionPerformed

    private void Hotel_RafaelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Hotel_RafaelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Hotel_RafaelActionPerformed

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton16ActionPerformed

    private void InicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InicioActionPerformed
;    }//GEN-LAST:event_InicioActionPerformed

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
            java.util.logging.Logger.getLogger(Interfaz_Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Interfaz_Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Interfaz_Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Interfaz_Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Interfaz_Principal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Bienvenido;
    private javax.swing.JTextField Buscar;
    private javax.swing.JButton CafeteríaIcon;
    private javax.swing.JLabel Cafeterías;
    private javax.swing.JLabel Cajas;
    private javax.swing.JLabel Catedral;
    private javax.swing.JLabel Catedral2;
    private javax.swing.JButton Catedral_Cuenca;
    private javax.swing.JButton CerrarSesión;
    private javax.swing.JLabel Cuencanas;
    private javax.swing.JButton Estrellas;
    private javax.swing.JButton Estrellas1;
    private javax.swing.JButton Estrellas2;
    private javax.swing.JButton Fondo;
    private javax.swing.JButton Fondo_derecha;
    private javax.swing.JLabel HotelYanuncay;
    private javax.swing.JButton Hotel_Rafael;
    private javax.swing.JButton Hoteles;
    private javax.swing.JLabel Hoteles_Lugar;
    private javax.swing.JLabel Huellas;
    private javax.swing.JButton Image_User;
    private javax.swing.JButton Inicio;
    private javax.swing.JButton Inicio1;
    private javax.swing.JButton Inicio2;
    private javax.swing.JButton Lupa;
    private javax.swing.JLabel Madre1;
    private javax.swing.JLabel Mirador;
    private javax.swing.JPanel Panel_Lugares;
    private javax.swing.JPanel Panel_Menú;
    private javax.swing.JLabel ParqueMadre1;
    private javax.swing.JLabel ParqueNacional;
    private javax.swing.JButton ParquedelaMadre;
    private javax.swing.JButton Principal;
    private javax.swing.JLabel Principal_Lugar;
    private javax.swing.JButton RestauranteIcon;
    private javax.swing.JButton RestauranteIcon1;
    private javax.swing.JLabel Restaurantes;
    private javax.swing.JLabel SanRafael1;
    private javax.swing.JPanel Segundo_Panel;
    private javax.swing.JButton VerDetalles;
    private javax.swing.JButton VerDetalles1;
    private javax.swing.JButton VerDetalles2;
    private javax.swing.JButton VerDetalles3;
    private javax.swing.JButton VerDetalles4;
    private javax.swing.JButton VerDetalles5;
    private javax.swing.JPanel content;
    private javax.swing.JLabel estrellas;
    private javax.swing.JLabel estrellas1;
    private javax.swing.JLabel estrellas2;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton22;
    private javax.swing.JButton jButton28;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    // End of variables declaration//GEN-END:variables
}
