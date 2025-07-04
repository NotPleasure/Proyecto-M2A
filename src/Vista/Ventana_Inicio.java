/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Vista;
import Vista.Ventana_Principal;
import AppPackage.AnimationClass;
import Design.RoundedButtonDetalles;
import Design.RoundedButtonIglesias;
import org.netbeans.lib.awtextra.AbsoluteLayout;
import org.netbeans.lib.awtextra.AbsoluteConstraints;
import Design.RoundedButtonLugares;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.JOptionPane;
import Design.RoundedPanelMenu;
import java.awt.BorderLayout;
import java.awt.Font;
import Design.RoundedButtonInicio;
import Design.RoundedButtonMiradores;
import Design.RoundedButtonMuseos;
import Design.RoundedButtonOscuro;
import Design.RoundedButtonParques;
import Design.RoundedButtonReOscuro;
import Design.RoundedPanel;
import Design.RoundedPannelGris;
import Design.RounderButton2;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

/**
 *
 * @author USER
 */
public class Ventana_Inicio extends javax.swing.JPanel {
private Ventana_Principal principal;
    private Ventana_Iglesias panelIglesias;

    private final String BusquedaText = "Buscar Lugares, Hoteles....";
    private String usuario;
    private int x;
    private int y;

    /**
     * Creates new form Ventana_Inicio
     */
    public Ventana_Inicio(String usuario) {
        setOpaque(false);

        initComponents();
        NombreUsuario.setText(usuario);
        //Dimensiones del usuario:
        NombreUsuario.setBounds(60, 80, 240, 50);
        //Fuentes de los labels, jButtons y Jfileds:
        Bienvenido.setFont(new Font("Open Sans Bold", Font.PLAIN, 39));
        NombreUsuario.setFont(new Font("Open Sans Bold", Font.PLAIN, 39));
        País.setFont(new Font("Caviar Dreams", Font.PLAIN, 20));
        Provincia.setFont(new Font("Caviar Dreams", Font.PLAIN, 17));
        Canton.setFont(new Font("Caviar Dreams", Font.PLAIN, 17));
        Genero.setFont(new Font("Caviar Dreams", Font.PLAIN, 20));
        VariableGenero.setFont(new Font("Caviar Dreams", Font.PLAIN, 17));
        Busqueda.setFont(new Font("Caviar Dreams", Font.PLAIN, 17));
        HolaBandeja.setFont(new Font("Caviar Dreams Bold", Font.PLAIN, 11));
        Categoría.setFont(new Font("CocogooseProTrial", Font.PLAIN, 25));
        IglesiasBoton.setFont(new Font("Caviar Dreams", Font.PLAIN, 17));
        Miradores.setFont(new Font("Caviar Dreams", Font.PLAIN, 17));
        Parques.setFont(new Font("Caviar Dreams", Font.PLAIN, 17));
        Museos.setFont(new Font("Caviar Dreams", Font.PLAIN, 17));
        DestinosDestacados.setFont(new Font("CocogooseProTrial", Font.PLAIN, 30));
        VerMas.setFont(new Font("CocogooseProTrial", Font.PLAIN, 15));
        Catedral.setFont(new Font("CocogooseProTrial", Font.PLAIN, 15));
        Concepción.setFont(new Font("Caviar Dreams", Font.PLAIN, 15));
        IglesiasLabel.setFont(new Font("CocogooseProTrial", Font.PLAIN, 13));
        Cuenca_Ecuador.setFont(new Font("CocogooseProTrial", Font.PLAIN, 13));
        Parquedela.setFont(new Font("CocogooseProTrial", Font.PLAIN, 15));
        Madre.setFont(new Font("Caviar Dreams", Font.PLAIN, 15));
        ParquesLabel.setFont(new Font("CocogooseProTrial", Font.PLAIN, 13));
        Ubicacion_Cuenca.setFont(new Font("CocogooseProTrial", Font.PLAIN, 13));
        ParqueNacional.setFont(new Font("CocogooseProTrial", Font.PLAIN, 15));
        Cajas.setFont(new Font("Caviar Dreams", Font.PLAIN, 15));
        ParquesLabelCajas.setFont(new Font("CocogooseProTrial", Font.PLAIN, 13));
        CuencaEcuadorLabel.setFont(new Font("CocogooseProTrial", Font.PLAIN, 13));

        //Hacer invisible el TextField de Búsqueda:
        Busqueda.setOpaque(false);
        Busqueda.setBorder(null);
        Busqueda.setForeground(Color.BLACK);

        //Hacer el Holder para la búsqueda:
        ponerPlaceholder(Busqueda, BusquedaText);

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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        content = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel1 = new RoundedPannelGris();
        Bienvenido = new javax.swing.JLabel();
        NombreUsuario = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jPanel2 = new RoundedPannelGris();
        jLabel8 = new javax.swing.JLabel();
        País = new javax.swing.JLabel();
        Provincia = new javax.swing.JLabel();
        Canton = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        Genero = new javax.swing.JLabel();
        VariableGenero = new javax.swing.JLabel();
        jPanel3 = new RoundedPannelGris();
        Busqueda = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        HolaBandeja = new javax.swing.JLabel();
        Categoría = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        IglesiasBoton = new RoundedButtonIglesias("");
        jLabel14 = new javax.swing.JLabel();
        Miradores =  new RoundedButtonMiradores("");
        jLabel15 = new javax.swing.JLabel();
        Parques = new RoundedButtonParques("");
        jLabel16 = new javax.swing.JLabel();
        Museos = new RoundedButtonMuseos("");
        DestinosDestacados = new javax.swing.JLabel();
        jButton1 =  new RoundedButtonLugares("");
        ParquedelaMadre = new RoundedButtonLugares("");
        jButton22 = new RoundedButtonLugares("");
        Catedral = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        VerMas = new javax.swing.JButton();
        Concepción = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        IglesiasLabel = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        Cuenca_Ecuador = new javax.swing.JLabel();
        Parquedela = new javax.swing.JLabel();
        Madre = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        ParquesLabel = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        Ubicacion_Cuenca = new javax.swing.JLabel();
        jButton7 = new RoundedButtonDetalles("");
        jButton8 = new RoundedButtonDetalles("");
        ParqueNacional = new javax.swing.JLabel();
        Cajas = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        ParquesLabelCajas = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        CuencaEcuadorLabel = new javax.swing.JLabel();
        jButton12 = new RoundedButtonDetalles("");

        setPreferredSize(new java.awt.Dimension(1140, 830));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        content.setBackground(new java.awt.Color(242, 242, 242));
        content.setLayout(null);

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/icons8_Expand_Arrow_32px.png"))); // NOI18N
        jLabel7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel7MouseClicked(evt);
            }
        });
        content.add(jLabel7);
        jLabel7.setBounds(1030, 10, 32, 32);

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/Salir (2) (2).png"))); // NOI18N
        jLabel5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
        });
        content.add(jLabel5);
        jLabel5.setBounds(1070, 11, 32, 32);

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/file_00000000c53861f790997cc9bcde40b7__5_-removebg-preview.png"))); // NOI18N
        content.add(jLabel3);
        jLabel3.setBounds(280, 20, 250, 200);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Bienvenido.setFont(new java.awt.Font("Dialog", 1, 39)); // NOI18N
        Bienvenido.setText("¡Bienvenid@!");
        jPanel1.add(Bienvenido, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, -1, -1));

        NombreUsuario.setFont(new java.awt.Font("Dialog", 1, 39)); // NOI18N
        jPanel1.add(NombreUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 80, 240, 50));

        jLabel24.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        jLabel24.setText("¡Es un gusto tenerte de vuelta!");
        jPanel1.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, -1, -1));

        content.add(jPanel1);
        jPanel1.setBounds(50, 20, 480, 200);

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/Ecuador (1).png"))); // NOI18N
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 80, 70));

        País.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        País.setText("Ecuador");
        jPanel2.add(País, new org.netbeans.lib.awtextra.AbsoluteConstraints(75, 30, -1, -1));

        Provincia.setForeground(new java.awt.Color(0, 41, 83));
        Provincia.setText("Azuay,");
        jPanel2.add(Provincia, new org.netbeans.lib.awtextra.AbsoluteConstraints(115, 52, -1, -1));

        Canton.setForeground(new java.awt.Color(0, 41, 83));
        Canton.setText("Cuenca.");
        jPanel2.add(Canton, new org.netbeans.lib.awtextra.AbsoluteConstraints(174, 52, -1, -1));

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/Género_Hombre__3_-removebg-preview.png"))); // NOI18N
        jPanel2.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(246, 10, 100, 70));

        Genero.setText("Género");
        jPanel2.add(Genero, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 30, -1, -1));

        VariableGenero.setForeground(new java.awt.Color(0, 41, 83));
        VariableGenero.setText("Hombre");
        jPanel2.add(VariableGenero, new org.netbeans.lib.awtextra.AbsoluteConstraints(388, 52, -1, -1));

        content.add(jPanel2);
        jPanel2.setBounds(50, 240, 480, 90);

        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel3.add(Busqueda, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, -4, 290, 50));

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/Lupa__Ícono_-removebg-preview (1).png"))); // NOI18N
        jPanel3.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, -20, 48, 80));

        content.add(jPanel3);
        jPanel3.setBounds(570, 20, 340, 40);

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/Bandeja de Entrada (1).png"))); // NOI18N
        jLabel12.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel12MouseClicked(evt);
            }
        });
        content.add(jLabel12);
        jLabel12.setBounds(930, 9, 40, 50);

        HolaBandeja.setText("Hola");
        content.add(HolaBandeja);
        HolaBandeja.setBounds(930, 30, 30, 16);

        Categoría.setText("Buscar por Categoría:");
        content.add(Categoría);
        Categoría.setBounds(570, 90, 430, 60);

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/iglesia (1).png"))); // NOI18N
        content.add(jLabel13);
        jLabel13.setBounds(580, 144, 120, 100);

        IglesiasBoton.setText("Iglesias");
        IglesiasBoton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        IglesiasBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                IglesiasBotonActionPerformed(evt);
            }
        });
        content.add(IglesiasBoton);
        IglesiasBoton.setBounds(570, 170, 230, 60);

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/binoculares (1).png"))); // NOI18N
        content.add(jLabel14);
        jLabel14.setBounds(860, 160, 60, 80);

        Miradores.setText("Miradores");
        Miradores.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        content.add(Miradores);
        Miradores.setBounds(850, 170, 240, 60);

        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/hombre-caminando (1).png"))); // NOI18N
        content.add(jLabel15);
        jLabel15.setBounds(580, 220, 60, 110);

        Parques.setText("Parques");
        Parques.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        content.add(Parques);
        Parques.setBounds(570, 250, 230, 60);

        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/museo-britanico (1).png"))); // NOI18N
        content.add(jLabel16);
        jLabel16.setBounds(860, 216, 60, 120);

        Museos.setText("Museos");
        Museos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        content.add(Museos);
        Museos.setBounds(850, 250, 240, 60);

        DestinosDestacados.setText("Destinos Destacados");
        content.add(DestinosDestacados);
        DestinosDestacados.setBounds(50, 352, 380, 40);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/Catedral (1).jpg"))); // NOI18N
        jButton1.setBorderPainted(false);
        jButton1.setContentAreaFilled(false);
        jButton1.setFocusPainted(false);
        content.add(jButton1);
        jButton1.setBounds(50, 410, 300, 170);

        ParquedelaMadre.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/Parque de la madre (1).jpg"))); // NOI18N
        ParquedelaMadre.setBorderPainted(false);
        ParquedelaMadre.setContentAreaFilled(false);
        ParquedelaMadre.setFocusPainted(false);
        content.add(ParquedelaMadre);
        ParquedelaMadre.setBounds(420, 410, 300, 170);

        jButton22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/ParqueNacionalElCajas (1).jpg"))); // NOI18N
        jButton22.setBorderPainted(false);
        jButton22.setContentAreaFilled(false);
        jButton22.setFocusPainted(false);
        content.add(jButton22);
        jButton22.setBounds(770, 410, 300, 170);

        Catedral.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        Catedral.setForeground(new java.awt.Color(237, 79, 31));
        Catedral.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Catedral.setText("Catedral de la Inmaculada");
        content.add(Catedral);
        Catedral.setBounds(50, 590, 240, 20);

        jLabel23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/agregar-boton (1).png"))); // NOI18N
        content.add(jLabel23);
        jLabel23.setBounds(785, 724, 40, 40);

        VerMas.setBackground(new java.awt.Color(242, 242, 242));
        VerMas.setForeground(new java.awt.Color(48, 35, 81));
        VerMas.setText("Mostrar más resultados");
        VerMas.setBorderPainted(false);
        VerMas.setContentAreaFilled(false);
        VerMas.setFocusPainted(false);
        VerMas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                VerMasActionPerformed(evt);
            }
        });
        content.add(VerMas);
        VerMas.setBounds(780, 722, 330, 50);

        Concepción.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        Concepción.setText("Concepción de Cuenca.");
        content.add(Concepción);
        Concepción.setBounds(50, 610, 180, 16);

        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/iglesia (2).png"))); // NOI18N
        content.add(jLabel17);
        jLabel17.setBounds(50, 630, 30, 40);

        IglesiasLabel.setText("Iglesias");
        content.add(IglesiasLabel);
        IglesiasLabel.setBounds(80, 640, 70, 20);

        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/ubicacion (1).png"))); // NOI18N
        content.add(jLabel18);
        jLabel18.setBounds(900, 630, 20, 40);

        Cuenca_Ecuador.setText("Cuenca, Ecuador.");
        content.add(Cuenca_Ecuador);
        Cuenca_Ecuador.setBounds(220, 640, 170, 16);

        Parquedela.setForeground(new java.awt.Color(237, 79, 31));
        Parquedela.setText("Parque de la");
        content.add(Parquedela);
        Parquedela.setBounds(420, 590, 130, 16);

        Madre.setText("Madre");
        content.add(Madre);
        Madre.setBounds(420, 610, 110, 16);

        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/hombre-caminando (2).png"))); // NOI18N
        content.add(jLabel19);
        jLabel19.setBounds(770, 630, 40, 40);

        ParquesLabel.setText("Parques");
        content.add(ParquesLabel);
        ParquesLabel.setBounds(450, 640, 90, 16);

        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/ubicacion (1).png"))); // NOI18N
        content.add(jLabel20);
        jLabel20.setBounds(180, 630, 20, 40);

        Ubicacion_Cuenca.setText("Cuenca, Ecuador.");
        content.add(Ubicacion_Cuenca);
        Ubicacion_Cuenca.setBounds(600, 640, 150, 16);

        jButton7.setBackground(new java.awt.Color(204, 204, 204));
        jButton7.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jButton7.setForeground(new java.awt.Color(255, 255, 255));
        jButton7.setText("Ver Detalles");
        content.add(jButton7);
        jButton7.setBounds(840, 676, 140, 30);

        jButton8.setBackground(new java.awt.Color(204, 204, 204));
        jButton8.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jButton8.setForeground(new java.awt.Color(255, 255, 255));
        jButton8.setText("Ver Detalles");
        content.add(jButton8);
        jButton8.setBounds(110, 675, 140, 30);

        ParqueNacional.setForeground(new java.awt.Color(237, 79, 31));
        ParqueNacional.setText("Parque Nacional el ");
        content.add(ParqueNacional);
        ParqueNacional.setBounds(770, 590, 200, 16);

        Cajas.setText("Cajas.");
        content.add(Cajas);
        Cajas.setBounds(770, 610, 90, 16);

        jLabel21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/hombre-caminando (2).png"))); // NOI18N
        content.add(jLabel21);
        jLabel21.setBounds(420, 630, 40, 40);

        ParquesLabelCajas.setText("Parques");
        content.add(ParquesLabelCajas);
        ParquesLabelCajas.setBounds(800, 640, 80, 16);

        jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/ubicacion (1).png"))); // NOI18N
        content.add(jLabel22);
        jLabel22.setBounds(570, 628, 20, 40);

        CuencaEcuadorLabel.setText("Cuenca, Ecuador.");
        content.add(CuencaEcuadorLabel);
        CuencaEcuadorLabel.setBounds(940, 644, 160, 16);

        jButton12.setBackground(new java.awt.Color(204, 204, 204));
        jButton12.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jButton12.setForeground(new java.awt.Color(255, 255, 255));
        jButton12.setText("Ver Detalles");
        content.add(jButton12);
        jButton12.setBounds(500, 675, 140, 30);

        add(content, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1140, 830));
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseClicked
    }//GEN-LAST:event_jLabel7MouseClicked

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
        int dialog = JOptionPane.YES_NO_OPTION;
        int result = JOptionPane.showConfirmDialog(null, "¿Desea salir?", "Exit", dialog);
        if (result == 0) {
            System.exit(0);
        }
    }//GEN-LAST:event_jLabel5MouseClicked

    private void jLabel12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel12MouseClicked
        AnimationClass Hola1 = new AnimationClass();
        Hola1.jLabelXRight(930, 970, 10, 5, HolaBandeja);

        AnimationClass Hola112 = new AnimationClass();
        Hola112.jLabelXLeft(970, 930, 10, 5, HolaBandeja);
    }//GEN-LAST:event_jLabel12MouseClicked

    private void IglesiasBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_IglesiasBotonActionPerformed
          if (panelIglesias == null) {
        panelIglesias = new Ventana_Iglesias(); 
    }
Ventana_Iglesias MiLugar = new Ventana_Iglesias();
        MiLugar.setSize(1140, 830);

        content.removeAll();
        content.add(MiLugar, java.awt.BorderLayout.CENTER);
        content.revalidate();
        content.repaint();
    }//GEN-LAST:event_IglesiasBotonActionPerformed

    private void VerMasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_VerMasActionPerformed
        Ventanas_MostrarMás MiLugar = new Ventanas_MostrarMás();
        MiLugar.setSize(1140, 830);

        content.removeAll();
        content.add(MiLugar, java.awt.BorderLayout.CENTER);
        content.revalidate();
    }//GEN-LAST:event_VerMasActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Bienvenido;
    private javax.swing.JTextField Busqueda;
    private javax.swing.JLabel Cajas;
    private javax.swing.JLabel Canton;
    private javax.swing.JLabel Catedral;
    private javax.swing.JLabel Categoría;
    private javax.swing.JLabel Concepción;
    private javax.swing.JLabel CuencaEcuadorLabel;
    private javax.swing.JLabel Cuenca_Ecuador;
    private javax.swing.JLabel DestinosDestacados;
    private javax.swing.JLabel Genero;
    private javax.swing.JLabel HolaBandeja;
    private javax.swing.JButton IglesiasBoton;
    private javax.swing.JLabel IglesiasLabel;
    private javax.swing.JLabel Madre;
    private javax.swing.JButton Miradores;
    private javax.swing.JButton Museos;
    private javax.swing.JLabel NombreUsuario;
    private javax.swing.JLabel ParqueNacional;
    private javax.swing.JLabel Parquedela;
    private javax.swing.JButton ParquedelaMadre;
    private javax.swing.JButton Parques;
    private javax.swing.JLabel ParquesLabel;
    private javax.swing.JLabel ParquesLabelCajas;
    private javax.swing.JLabel País;
    private javax.swing.JLabel Provincia;
    private javax.swing.JLabel Ubicacion_Cuenca;
    private javax.swing.JLabel VariableGenero;
    private javax.swing.JButton VerMas;
    private javax.swing.JPanel content;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton22;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    // End of variables declaration//GEN-END:variables
}
