/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Design.RoundedBorder;
import Design.RoundedButtonCerrarSesion;
import Design.RoundedButtonDetalles;
import Design.RoundedButtonLugares;
import Design.RoundedButtonPlaces;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.plaf.basic.BasicComboBoxUI;

/**
 *
 * @author USER
 */
public class InterfazPrincipal extends javax.swing.JFrame {

    private String Nombre_de_Usuario = "Buscar Lugares, Hoteles, Restaurantes, Cafeterías...";

    /**
     * Creates new form InterfazPrincipal
     */
    public InterfazPrincipal() {
        initComponents();

        this.setExtendedState(6);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);

        //Fuentes
        Bienvenido.setFont(new Font("Open Sans Bold", Font.PLAIN, 45));
        Huellas.setFont(new Font("Open Sans Bold", Font.PLAIN, 15));
        Cuencanas.setFont(new Font("Open Sans", Font.PLAIN, 15));
        Lugares.setFont(new Font("Open Sans Bold", Font.PLAIN, 15));
        Hoteles.setFont(new Font("Open Sans Bold", Font.PLAIN, 15));
        Restaurantes.setFont(new Font("Open Sans Bold", Font.PLAIN, 15));
        Cafeterías.setFont(new Font("Open Sans Bold", Font.PLAIN, 15));
        txtNombredeusuario.setFont(new Font("Open Sans Bold", Font.PLAIN, 15));
        Catedral.setFont(new Font("Open Sans Bold", Font.PLAIN, 15));
        Catedral1.setFont(new Font("Open Sans Bold", Font.PLAIN, 15));
        estrellas.setFont(new Font("Open Sans", Font.PLAIN, 15));
        ParqueMadre.setFont(new Font("Open Sans Bold", Font.PLAIN, 15));
        MadreParque.setFont(new Font("Open Sans", Font.PLAIN, 15));
        Mirador.setFont(new Font("Open Sans Bold", Font.PLAIN, 15));
        Turi.setFont(new Font("Open Sans", Font.PLAIN, 15));
        ParqueCalderón.setFont(new Font("Open Sans Bold", Font.PLAIN, 15));
        Calderón.setFont(new Font("Open Sans", Font.PLAIN, 15));
        ParqueNacional.setFont(new Font("Open Sans Bold", Font.PLAIN, 15));
        ElCajas.setFont(new Font("Open Sans", Font.PLAIN, 15));
        SanRafael.setFont(new Font("Open Sans Bold", Font.PLAIN, 15));
        HotelCasa.setFont(new Font("Open Sans", Font.PLAIN, 15));
        HotelYanuncay.setFont(new Font("Open Sans Bold", Font.PLAIN, 15));
        YanuHotel.setFont(new Font("Open Sans", Font.PLAIN, 15));

        //Personalizar ComboBox
        JComboBox<String> MiComboBox = new JComboBox<>(new String[]{"Opción 1", "Opción 2"});
        MiComboBox.setEditable(true);

        JTextField editor = (JTextField) MiComboBox.getEditor().getEditorComponent();
        editor.setBackground(new Color(217, 217, 217));
        editor.setForeground(Color.WHITE);
        editor.setOpaque(true);
        MiComboBox.setBorder(null);

        MiComboBox.setUI(new BasicComboBoxUI() {
            @Override
            protected JButton createArrowButton() {
                JButton button = new JButton();
                button.setBorder(null);
                button.setBackground(new Color(27, 78, 23)); // Fondo del botón de flecha
                button.setContentAreaFilled(true);
                button.setFocusPainted(false);
                return button;
            }
        });

        MiComboBox.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                    boolean isSelected, boolean cellHasFocus) {
                Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (isSelected) {
                    c.setBackground(new Color(51, 102, 51));  // Color al pasar el mouse o seleccionar
                    c.setForeground(Color.WHITE);
                } else {
                    c.setBackground(new Color(217, 217, 217)); // Color normal de fondo
                    c.setForeground(Color.BLACK);
                }
                return c;
            }
        });

        //Dar bordes al botón:
        txtNombredeusuario.setBorder(new RoundedBorder(new Color(165, 170, 163), 2, 30, 30));
        txtNombredeusuario.setOpaque(false);

        
        //Poner placeholder a los textField
        ponerPlaceholder(txtNombredeusuario, Nombre_de_Usuario);
    }

    private void ponerPlaceholder(javax.swing.JTextField campo, String textoPorDefecto) {
        campo.setText(textoPorDefecto);
        campo.setForeground(new Color(255, 255, 255, 255));

        campo.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent e) {
                if (campo.getText().equals(textoPorDefecto)) {
                    campo.setText("");
                    campo.setForeground(new Color(217, 217, 217));
                }
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent e) {
                if (campo.getText().isEmpty()) {
                    campo.setForeground(new Color(217, 217, 217, 220));
                    campo.setText(textoPorDefecto);
                }
            }
        });
    }

    private void ponerPlaceholder(javax.swing.JPasswordField campo, String textoPorDefecto) {
        campo.setEchoChar((char) 0);
        campo.setText(textoPorDefecto);
        campo.setForeground(new Color(255, 255, 255, 255));

        campo.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent e) {
                String passText = new String(campo.getPassword());
                if (passText.equals(textoPorDefecto)) {
                    campo.setText("");
                    campo.setForeground(new Color(255, 255, 255, 255));
                    campo.setEchoChar('•');
                }
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent e) {
                String passText = new String(campo.getPassword());
                if (passText.isEmpty()) {
                    campo.setEchoChar((char) 0);
                    campo.setForeground(new Color(255, 255, 255, 255));
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

        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel2 = new javax.swing.JPanel();
        jButton11 = new RoundedButtonLugares("");
        Catedral = new javax.swing.JLabel();
        Catedral1 = new javax.swing.JLabel();
        estrellas = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        jButton12 = new RoundedButtonDetalles("");
        jButton13 = new RoundedButtonLugares("");
        ParqueMadre = new javax.swing.JLabel();
        MadreParque = new javax.swing.JLabel();
        jButton14 = new javax.swing.JButton();
        jButton15 = new RoundedButtonDetalles("");
        jButton16 = new RoundedButtonLugares("");
        Mirador = new javax.swing.JLabel();
        Turi = new javax.swing.JLabel();
        jButton17 = new javax.swing.JButton();
        jButton19 = new RoundedButtonLugares("");
        ParqueCalderón = new javax.swing.JLabel();
        Calderón = new javax.swing.JLabel();
        jButton20 = new javax.swing.JButton();
        jButton21 = new RoundedButtonDetalles("");
        jButton22 = new RoundedButtonLugares("");
        ParqueNacional = new javax.swing.JLabel();
        ElCajas = new javax.swing.JLabel();
        jButton23 = new javax.swing.JButton();
        jButton24 = new RoundedButtonDetalles("");
        jButton25 = new RoundedButtonLugares("");
        SanRafael = new javax.swing.JLabel();
        HotelCasa = new javax.swing.JLabel();
        jButton26 = new javax.swing.JButton();
        jButton27 = new RoundedButtonDetalles("");
        jButton28 = new RoundedButtonLugares("");
        HotelYanuncay = new javax.swing.JLabel();
        YanuHotel = new javax.swing.JLabel();
        jButton29 = new javax.swing.JButton();
        jButton30 = new RoundedButtonDetalles("");
        jButton31 = new RoundedButtonDetalles("");
        Panel1 = new javax.swing.JPanel();
        Bienvenido = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        Huellas = new javax.swing.JLabel();
        Cuencanas = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        MiComboBox = new javax.swing.JComboBox<>();
        jButton3 =  new RoundedButtonCerrarSesion("");
        jButton5 = new javax.swing.JButton();
        Lugares = new RoundedButtonPlaces("");
        jButton7 = new javax.swing.JButton();
        Hoteles = new RoundedButtonPlaces("");
        jButton8 = new javax.swing.JButton();
        Restaurantes = new RoundedButtonPlaces("");
        jButton9 = new javax.swing.JButton();
        Cafeterías = new RoundedButtonPlaces("");
        jButton10 = new javax.swing.JButton();
        txtNombredeusuario = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        jButton18 = new javax.swing.JButton();
        jButton32 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jButton11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/Catedral (1).jpg"))); // NOI18N
        jButton11.setBorderPainted(false);
        jButton11.setContentAreaFilled(false);
        jButton11.setFocusPainted(false);

        Catedral.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Catedral.setText("Catedral de la Inmaculada");

        Catedral1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Catedral1.setText("Concepción de Cuenca.");

        estrellas.setText("5.0");

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/5 estrellas.png"))); // NOI18N
        jButton6.setBorderPainted(false);
        jButton6.setContentAreaFilled(false);
        jButton6.setFocusPainted(false);

        jButton12.setText("Ver Detalles");
        jButton12.setBorderPainted(false);
        jButton12.setContentAreaFilled(false);
        jButton12.setFocusPainted(false);

        jButton13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/Parque de la madre (1).jpg"))); // NOI18N
        jButton13.setBorderPainted(false);
        jButton13.setContentAreaFilled(false);
        jButton13.setFocusPainted(false);

        ParqueMadre.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ParqueMadre.setText("Parque de la Madre.");

        MadreParque.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        MadreParque.setText("5.0");

        jButton14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/5 estrellas.png"))); // NOI18N
        jButton14.setBorderPainted(false);
        jButton14.setContentAreaFilled(false);
        jButton14.setFocusPainted(false);

        jButton15.setText("Ver Detalles");
        jButton15.setBorderPainted(false);
        jButton15.setContentAreaFilled(false);
        jButton15.setFocusPainted(false);

        jButton16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/Mirador de Turi.jpg"))); // NOI18N
        jButton16.setBorderPainted(false);
        jButton16.setContentAreaFilled(false);
        jButton16.setFocusPainted(false);

        Mirador.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Mirador.setText("Mirador de Turi");
        Mirador.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        Turi.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Turi.setText("5.0");

        jButton17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/5 estrellas.png"))); // NOI18N
        jButton17.setBorderPainted(false);
        jButton17.setContentAreaFilled(false);
        jButton17.setFocusPainted(false);

        jButton19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/Parque Calderón.jpg"))); // NOI18N
        jButton19.setBorderPainted(false);
        jButton19.setContentAreaFilled(false);
        jButton19.setFocusPainted(false);

        ParqueCalderón.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ParqueCalderón.setText("Parque Calderón");
        ParqueCalderón.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        Calderón.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Calderón.setText("5.0");

        jButton20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/5 estrellas.png"))); // NOI18N
        jButton20.setBorderPainted(false);
        jButton20.setContentAreaFilled(false);
        jButton20.setFocusPainted(false);

        jButton21.setText("Ver Detalles");
        jButton21.setBorderPainted(false);
        jButton21.setContentAreaFilled(false);
        jButton21.setFocusPainted(false);

        jButton22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/ParqueNacionalElCajas (1).jpg"))); // NOI18N
        jButton22.setBorderPainted(false);
        jButton22.setContentAreaFilled(false);
        jButton22.setFocusPainted(false);

        ParqueNacional.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ParqueNacional.setText("Parque Nacional El Cajas");
        ParqueNacional.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        ElCajas.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ElCajas.setText("5.0");

        jButton23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/5 estrellas.png"))); // NOI18N
        jButton23.setBorderPainted(false);
        jButton23.setContentAreaFilled(false);
        jButton23.setFocusPainted(false);

        jButton24.setText("Ver Detalles");
        jButton24.setBorderPainted(false);
        jButton24.setContentAreaFilled(false);
        jButton24.setFocusPainted(false);

        jButton25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/Hotel Rafael (1).jpg"))); // NOI18N
        jButton25.setBorderPainted(false);
        jButton25.setContentAreaFilled(false);
        jButton25.setFocusPainted(false);
        jButton25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton25ActionPerformed(evt);
            }
        });

        SanRafael.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        SanRafael.setText("Hotel Casa San Rafael");

        HotelCasa.setText("5.0");

        jButton26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/5 estrellas.png"))); // NOI18N
        jButton26.setBorderPainted(false);
        jButton26.setContentAreaFilled(false);
        jButton26.setFocusPainted(false);

        jButton27.setText("Ver Detalles");
        jButton27.setBorderPainted(false);
        jButton27.setContentAreaFilled(false);
        jButton27.setFocusPainted(false);

        jButton28.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/Hotel Yanuncay (2).jpg"))); // NOI18N
        jButton28.setBorderPainted(false);
        jButton28.setContentAreaFilled(false);
        jButton28.setFocusPainted(false);

        HotelYanuncay.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        HotelYanuncay.setText("Hotel Yanuncay");

        YanuHotel.setText("5.0");

        jButton29.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/5 estrellas.png"))); // NOI18N
        jButton29.setBorderPainted(false);
        jButton29.setContentAreaFilled(false);
        jButton29.setFocusPainted(false);

        jButton30.setText("Ver Detalles");
        jButton30.setBorderPainted(false);
        jButton30.setContentAreaFilled(false);
        jButton30.setFocusPainted(false);

        jButton31.setText("Ver Detalles");
        jButton31.setBorderPainted(false);
        jButton31.setContentAreaFilled(false);
        jButton31.setFocusPainted(false);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Mirador, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(222, 222, 222)
                .addComponent(ParqueCalderón, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(552, 552, 552))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(95, 95, 95)
                                .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 323, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(estrellas, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(Catedral, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(202, 202, 202)
                                        .addComponent(MadreParque)
                                        .addGap(31, 31, 31)
                                        .addComponent(jButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(171, 171, 171)
                                        .addComponent(ParqueMadre, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(Catedral1, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(10, 10, 10)))
                                .addGap(234, 234, 234)
                                .addComponent(jButton15, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(SanRafael, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(166, 166, 166)
                                .addComponent(HotelYanuncay, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(76, 76, 76)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(3, 3, 3)
                                        .addComponent(jButton16, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(88, 88, 88)
                                        .addComponent(jButton19, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addGap(69, 69, 69)
                                                .addComponent(jButton17, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(Calderón))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(Turi)
                                        .addGap(273, 273, 273)))
                                .addGap(0, 13, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton20, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(84, 84, 84)))
                        .addGap(55, 55, 55))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(141, 141, 141)
                        .addComponent(jButton31, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton21, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(135, 135, 135)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton22, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(ElCajas)
                            .addGap(18, 18, 18)
                            .addComponent(jButton23, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(ParqueNacional, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton24, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(57, 57, 57))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(65, 65, 65)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(YanuHotel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(50, 50, 50)
                                .addComponent(jButton29, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(41, 41, 41))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jButton27, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(HotelCasa, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jButton26, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(235, 235, 235)
                                .addComponent(jButton30, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(52, 52, 52))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(jButton25, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton28, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton19, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton16, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton22, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(ParqueNacional, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(Catedral)
                        .addComponent(ParqueMadre)
                        .addComponent(ParqueCalderón, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(Mirador, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton23, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ElCajas, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jButton24))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Catedral1)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(estrellas, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jButton12)
                                    .addComponent(jButton31)
                                    .addComponent(jButton15)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jButton20, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jButton17, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(Calderón, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(Turi, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(MadreParque, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(3, 3, 3)))
                                .addGap(18, 18, 18)
                                .addComponent(jButton21)))))
                .addGap(61, 61, 61)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton28, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton25, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(SanRafael)
                    .addComponent(HotelYanuncay))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton26, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(HotelCasa, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(YanuHotel, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton29, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton27)
                    .addComponent(jButton30))
                .addContainerGap(610, Short.MAX_VALUE))
        );

        jScrollPane1.setViewportView(jPanel2);

        getContentPane().add(jScrollPane1, java.awt.BorderLayout.CENTER);

        Panel1.setBackground(new java.awt.Color(255, 255, 255));
        Panel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Bienvenido.setForeground(new java.awt.Color(255, 255, 255));
        Bienvenido.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Bienvenido.setText("Bienvenido");
        Panel1.add(Bienvenido, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 10, 440, 80));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/Copilot_20250610_114322 (3).png"))); // NOI18N
        jButton1.setBorderPainted(false);
        jButton1.setContentAreaFilled(false);
        jButton1.setFocusPainted(false);
        Panel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, 60, 70));

        Huellas.setForeground(new java.awt.Color(255, 255, 255));
        Huellas.setText("Huellas ");
        Panel1.add(Huellas, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 30, -1, -1));

        Cuencanas.setForeground(new java.awt.Color(255, 255, 255));
        Cuencanas.setText("Cuencanas");
        Panel1.add(Cuencanas, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 50, -1, -1));

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/User (1).png"))); // NOI18N
        jButton2.setBorderPainted(false);
        jButton2.setContentAreaFilled(false);
        jButton2.setFocusPainted(false);
        Panel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 10, 80, 80));

        MiComboBox.setBackground(new java.awt.Color(217, 217, 217));
        MiComboBox.setForeground(new java.awt.Color(255, 255, 255));
        MiComboBox.setBorder(null);
        MiComboBox.setOpaque(false);
        MiComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MiComboBoxActionPerformed(evt);
            }
        });
        Panel1.add(MiComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(1110, 40, -1, 20));

        jButton3.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jButton3.setText("Cerrar Sesión");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        Panel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1156, 30, 160, 30));

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/Lugar ICON.png"))); // NOI18N
        jButton5.setBorderPainted(false);
        jButton5.setContentAreaFilled(false);
        jButton5.setFocusPainted(false);
        Panel1.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 150, 60, 50));

        Lugares.setForeground(new java.awt.Color(0, 0, 0));
        Lugares.setText("Lugares");
        Lugares.setBorderPainted(false);
        Lugares.setContentAreaFilled(false);
        Lugares.setFocusPainted(false);
        Panel1.add(Lugares, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 160, 160, 30));

        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/Hotel-removebg-preview (1).png"))); // NOI18N
        jButton7.setBorderPainted(false);
        jButton7.setContentAreaFilled(false);
        jButton7.setFocusPainted(false);
        Panel1.add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 150, 50, 50));

        Hoteles.setForeground(new java.awt.Color(0, 0, 0));
        Hoteles.setText("Hoteles");
        Hoteles.setBorderPainted(false);
        Hoteles.setContentAreaFilled(false);
        Hoteles.setFocusPainted(false);
        Panel1.add(Hoteles, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 159, 190, 30));

        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/Restaurante-removebg-preview (1).png"))); // NOI18N
        jButton8.setBorderPainted(false);
        jButton8.setContentAreaFilled(false);
        jButton8.setFocusPainted(false);
        Panel1.add(jButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 140, 60, 60));

        Restaurantes.setForeground(new java.awt.Color(0, 0, 0));
        Restaurantes.setText("Restaurantes");
        Restaurantes.setBorderPainted(false);
        Restaurantes.setContentAreaFilled(false);
        Restaurantes.setFocusPainted(false);
        Panel1.add(Restaurantes, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 160, 190, 30));

        jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/Cafetería-removebg-preview (2).png"))); // NOI18N
        jButton9.setBorderPainted(false);
        jButton9.setContentAreaFilled(false);
        jButton9.setFocusPainted(false);
        Panel1.add(jButton9, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 130, 60, 70));

        Cafeterías.setForeground(new java.awt.Color(0, 0, 0));
        Cafeterías.setText("Cafeterías");
        Cafeterías.setBorderPainted(false);
        Cafeterías.setContentAreaFilled(false);
        Cafeterías.setFocusPainted(false);
        Panel1.add(Cafeterías, new org.netbeans.lib.awtextra.AbsoluteConstraints(1110, 160, 200, 30));

        jButton10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/Lupa PNG (2).png"))); // NOI18N
        jButton10.setBorderPainted(false);
        jButton10.setContentAreaFilled(false);
        jButton10.setFocusPainted(false);
        Panel1.add(jButton10, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 240, 40, 40));

        txtNombredeusuario.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtNombredeusuario.setForeground(new java.awt.Color(255, 255, 255));
        txtNombredeusuario.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtNombredeusuario.setText("Buscar Lugares, Hoteles, Restaurantes...");
        txtNombredeusuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombredeusuarioActionPerformed(evt);
            }
        });
        Panel1.add(txtNombredeusuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 240, 1250, 40));

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/Banner Cuenca.png"))); // NOI18N
        jButton4.setBorderPainted(false);
        jButton4.setContentAreaFilled(false);
        jButton4.setFocusPainted(false);
        Panel1.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1470, 320));

        jButton18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/Restaurante-removebg-preview (1).png"))); // NOI18N
        jButton18.setBorderPainted(false);
        jButton18.setContentAreaFilled(false);
        jButton18.setFocusPainted(false);
        Panel1.add(jButton18, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 140, 60, 60));

        jButton32.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/Hotel-removebg-preview (1).png"))); // NOI18N
        jButton32.setBorderPainted(false);
        jButton32.setContentAreaFilled(false);
        jButton32.setFocusPainted(false);
        Panel1.add(jButton32, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 150, 50, 50));

        getContentPane().add(Panel1, java.awt.BorderLayout.PAGE_START);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void MiComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MiComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_MiComboBoxActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        Login miLogin = new Login();
        miLogin.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void txtNombredeusuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombredeusuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombredeusuarioActionPerformed

    private void jButton25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton25ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton25ActionPerformed

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
            java.util.logging.Logger.getLogger(InterfazPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InterfazPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InterfazPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InterfazPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new InterfazPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Bienvenido;
    private javax.swing.JButton Cafeterías;
    private javax.swing.JLabel Calderón;
    private javax.swing.JLabel Catedral;
    private javax.swing.JLabel Catedral1;
    private javax.swing.JLabel Cuencanas;
    private javax.swing.JLabel ElCajas;
    private javax.swing.JLabel HotelCasa;
    private javax.swing.JLabel HotelYanuncay;
    private javax.swing.JButton Hoteles;
    private javax.swing.JLabel Huellas;
    private javax.swing.JButton Lugares;
    private javax.swing.JLabel MadreParque;
    private javax.swing.JComboBox<String> MiComboBox;
    private javax.swing.JLabel Mirador;
    private javax.swing.JPanel Panel1;
    private javax.swing.JLabel ParqueCalderón;
    private javax.swing.JLabel ParqueMadre;
    private javax.swing.JLabel ParqueNacional;
    private javax.swing.JButton Restaurantes;
    private javax.swing.JLabel SanRafael;
    private javax.swing.JLabel Turi;
    private javax.swing.JLabel YanuHotel;
    private javax.swing.JLabel estrellas;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton19;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton20;
    private javax.swing.JButton jButton21;
    private javax.swing.JButton jButton22;
    private javax.swing.JButton jButton23;
    private javax.swing.JButton jButton24;
    private javax.swing.JButton jButton25;
    private javax.swing.JButton jButton26;
    private javax.swing.JButton jButton27;
    private javax.swing.JButton jButton28;
    private javax.swing.JButton jButton29;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton30;
    private javax.swing.JButton jButton31;
    private javax.swing.JButton jButton32;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txtNombredeusuario;
    // End of variables declaration//GEN-END:variables
}
