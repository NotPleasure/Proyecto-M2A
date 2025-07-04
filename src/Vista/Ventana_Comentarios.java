/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Vista;

import Design.ComboBox.Combobox;
import Design.RoundedButtonAceptarComentario;
import java.awt.Color;
import java.awt.Font;
import Design.RoundedTextField;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import Design.RoundedPannelGris;
import Design.RoundedPannelGris1;
import java.awt.Component;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;
import Design.RoundedPanel;
import Design.RoundedTextArea;

/**
 *
 * @author USER
 */
public class Ventana_Comentarios extends javax.swing.JFrame {

    private String Lugares = "Todos los lugares:";
    private String Ordenar = "Ordenar por";

    /**
     * Creates new form Ventana_Comentarios
     */
    public Ventana_Comentarios() {
        initComponents();
        
        //Maximizar la ventana al máximo:
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setExtendedState(Ventana_Principal.MAXIMIZED_BOTH);
        
        //Fuentes:
        MisComentarios.setFont(new Font("CocogooseProTrial", Font.PLAIN, 28));
        Opiniones.setFont(new Font("Caviar Dreams", Font.PLAIN, 20));
        Usuario_Comentarios.setFont(new Font("Caviar Dreams", Font.PLAIN, 20));
        Comentarios1.setFont(new Font("Caviar Dreams Bold", Font.PLAIN, 20));
        BúsquedaComentarios.setFont(new Font("Caviar Dreams", Font.PLAIN, 17));
        LugaresCombo.setFont(new Font("Caviar Dreams", Font.PLAIN, 15));
        Masreciente.setFont(new Font("Caviar Dreams", Font.PLAIN, 15));
        Jorge.setFont(new Font("CocogooseProTrial", Font.PLAIN, 20));
        Hace.setFont(new Font("Caviar Dreams", Font.PLAIN, 15));
        Jorge1.setFont(new Font("CocogooseProTrial", Font.PLAIN, 20));
        Hace1.setFont(new Font("Caviar Dreams", Font.PLAIN, 15));
        Jorge2.setFont(new Font("CocogooseProTrial", Font.PLAIN, 20));
        Hace2.setFont(new Font("Caviar Dreams", Font.PLAIN, 15));
        Jorge3.setFont(new Font("CocogooseProTrial", Font.PLAIN, 20));
        Hace3.setFont(new Font("Caviar Dreams", Font.PLAIN, 15));
        jComboBox1.setFont(new Font("Caviar Dreams", Font.PLAIN, 15));
        Todavia.setFont(new Font("CocogooseProTrial", Font.PLAIN, 20));
        Experiencias.setFont(new Font("Caviar Dreams", Font.PLAIN, 15));

        //Quitar el fondo al TextField:
        BúsquedaComentarios.setOpaque(false);
        BúsquedaComentarios.setBorder(null);
        BúsquedaComentarios.setForeground(Color.BLACK);

        //ActionListener para la búsqueda:
        BúsquedaComentarios.addActionListener(e -> {
            String texto = BúsquedaComentarios.getText().trim();

            if (texto.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Escribe algo para buscar");
                return;
            }

            BúsquedaComentarios.setEnabled(false);

            new Thread(() -> {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }

                SwingUtilities.invokeLater(() -> {

                    BúsquedaComentarios.setEnabled(true);
                    BúsquedaComentarios.requestFocus();
                    BúsquedaComentarios.selectAll();
                });
            }).start();
        });

        //Bordes Redondeados ComboBox:
        String[] Lugares = {
            "Lugares", "Iglesias", "Museos"
        };

        LugaresCombo.setModel(new DefaultComboBoxModel<>(Lugares));
        LugaresCombo.setSelectedIndex(-1);

        LugaresCombo.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                    boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

                if (index == -1 && value == null) {
                    setText("Todos los lugares:");
                }

                return this;
            }
        });

        //Bordes Redondeados ComboBox:
        String[] Ordenar = {
            "Fecha más reciente", "Fecha más antigua"
        };

        Masreciente.setModel(new DefaultComboBoxModel<>(Ordenar));
        Masreciente.setSelectedIndex(-1);

        Masreciente.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                    boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

                if (index == -1 && value == null) {
                    setText("Ordenar por:");
                }

                return this;
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

        jPanel1 = new javax.swing.JPanel();
        MisComentarios = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        Opiniones = new javax.swing.JLabel();
        Usuario_Comentarios = new javax.swing.JLabel();
        Comentarios1 = new javax.swing.JLabel();
        jPanel3 = new RoundedPannelGris1();
        BúsquedaComentarios = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        LugaresCombo =  new Combobox<>(new String[]{
            "Lugares","Museos","Iglesias"});
    Masreciente = new Combobox<>(new String[]{
        "Más reciente","Más antiguo"});
jPanel2 = new RoundedPanel();
jLabel2 = new javax.swing.JLabel();
Jorge = new javax.swing.JLabel();
Hace = new javax.swing.JLabel();
estrellaClik1 = new Design.EstrellaClick.EstrellaClik();
estrellaClik2 = new Design.EstrellaClick.EstrellaClik();
estrellaClik3 = new Design.EstrellaClick.EstrellaClik();
estrellaClik4 = new Design.EstrellaClick.EstrellaClik();
estrellaClik5 = new Design.EstrellaClick.EstrellaClik();
jScrollPane6 = new javax.swing.JScrollPane();
jTextArea6 = new RoundedTextArea(5, 30);
jPanel4 = new RoundedPanel();
jLabel3 = new javax.swing.JLabel();
Jorge1 = new javax.swing.JLabel();
Hace1 = new javax.swing.JLabel();
estrellaClik6 = new Design.EstrellaClick.EstrellaClik();
estrellaClik7 = new Design.EstrellaClick.EstrellaClik();
estrellaClik8 = new Design.EstrellaClick.EstrellaClik();
estrellaClik9 = new Design.EstrellaClick.EstrellaClik();
estrellaClik10 = new Design.EstrellaClick.EstrellaClik();
jScrollPane7 = new javax.swing.JScrollPane();
jTextArea7 = new RoundedTextArea(5, 30);
jPanel5 = new RoundedPanel();
jLabel4 = new javax.swing.JLabel();
Jorge2 = new javax.swing.JLabel();
Hace2 = new javax.swing.JLabel();
estrellaClik11 = new Design.EstrellaClick.EstrellaClik();
estrellaClik12 = new Design.EstrellaClick.EstrellaClik();
estrellaClik13 = new Design.EstrellaClick.EstrellaClik();
estrellaClik14 = new Design.EstrellaClick.EstrellaClik();
estrellaClik15 = new Design.EstrellaClick.EstrellaClik();
jScrollPane5 = new javax.swing.JScrollPane();
jTextArea5 = new RoundedTextArea(5, 30);
jPanel6 = new RoundedPanel();
jLabel5 = new javax.swing.JLabel();
Jorge3 = new javax.swing.JLabel();
Hace3 = new javax.swing.JLabel();
estrellaClik16 = new Design.EstrellaClick.EstrellaClik();
estrellaClik17 = new Design.EstrellaClick.EstrellaClik();
estrellaClik18 = new Design.EstrellaClick.EstrellaClik();
estrellaClik19 = new Design.EstrellaClick.EstrellaClik();
estrellaClik20 = new Design.EstrellaClick.EstrellaClik();
jScrollPane8 = new javax.swing.JScrollPane();
jTextArea8 = new RoundedTextArea(5, 30);
jComboBox1 = new Combobox<>(new String[]{
    "Lugares","Museos","Iglesias"});
    jLabel14 = new javax.swing.JLabel();
    jPanel7 = new RoundedPanel();
    jLabel6 = new javax.swing.JLabel();
    Todavia = new javax.swing.JLabel();
    Experiencias = new javax.swing.JLabel();
    jButton1 = new RoundedButtonAceptarComentario("");

    setPreferredSize(new java.awt.Dimension(1090, 800));
    getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

    jPanel1.setBackground(new java.awt.Color(250, 250, 250));
    jPanel1.setPreferredSize(new java.awt.Dimension(1090, 800));
    jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

    MisComentarios.setFont(new java.awt.Font("Segoe UI", 0, 28)); // NOI18N
    MisComentarios.setForeground(new java.awt.Color(0, 0, 0));
    MisComentarios.setText("Mis Comentarios");
    jPanel1.add(MisComentarios, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, -1, -1));

    jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/Comentarios PNG.png"))); // NOI18N
    jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 24, 40, 40));

    Opiniones.setForeground(new java.awt.Color(0, 0, 0));
    Opiniones.setText("Tus opiniones y la de otros usuarios.");
    jPanel1.add(Opiniones, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 70, -1, -1));

    Usuario_Comentarios.setForeground(new java.awt.Color(0, 0, 0));
    Usuario_Comentarios.setText("Usuario >");
    jPanel1.add(Usuario_Comentarios, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 120, -1, -1));

    Comentarios1.setText("Comentarios");
    jPanel1.add(Comentarios1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 120, -1, -1));

    jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

    BúsquedaComentarios.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
    BúsquedaComentarios.setOpaque(true);
    BúsquedaComentarios.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            BúsquedaComentariosActionPerformed(evt);
        }
    });
    jPanel3.add(BúsquedaComentarios, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 0, 280, 50));

    jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/Lupa__Ícono_-removebg-preview (1).png"))); // NOI18N
    jPanel3.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, -14, 48, 80));

    jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 180, 360, 50));

    LugaresCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
    jPanel1.add(LugaresCombo, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 180, 270, 50));

    Masreciente.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
    jPanel1.add(Masreciente, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 180, 270, 50));

    jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

    jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/masculino (3).png"))); // NOI18N
    jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 40, 60));

    Jorge.setText("Matías");
    jPanel2.add(Jorge, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 20, -1, -1));

    Hace.setText("Hace 2 horas.");
    jPanel2.add(Hace, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 40, -1, -1));

    javax.swing.GroupLayout estrellaClik1Layout = new javax.swing.GroupLayout(estrellaClik1);
    estrellaClik1.setLayout(estrellaClik1Layout);
    estrellaClik1Layout.setHorizontalGroup(
        estrellaClik1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGap(0, 30, Short.MAX_VALUE)
    );
    estrellaClik1Layout.setVerticalGroup(
        estrellaClik1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGap(0, 40, Short.MAX_VALUE)
    );

    jPanel2.add(estrellaClik1, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 10, 30, 40));

    javax.swing.GroupLayout estrellaClik2Layout = new javax.swing.GroupLayout(estrellaClik2);
    estrellaClik2.setLayout(estrellaClik2Layout);
    estrellaClik2Layout.setHorizontalGroup(
        estrellaClik2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGap(0, 30, Short.MAX_VALUE)
    );
    estrellaClik2Layout.setVerticalGroup(
        estrellaClik2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGap(0, 40, Short.MAX_VALUE)
    );

    jPanel2.add(estrellaClik2, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 10, 30, 40));

    javax.swing.GroupLayout estrellaClik3Layout = new javax.swing.GroupLayout(estrellaClik3);
    estrellaClik3.setLayout(estrellaClik3Layout);
    estrellaClik3Layout.setHorizontalGroup(
        estrellaClik3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGap(0, 30, Short.MAX_VALUE)
    );
    estrellaClik3Layout.setVerticalGroup(
        estrellaClik3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGap(0, 40, Short.MAX_VALUE)
    );

    jPanel2.add(estrellaClik3, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 10, 30, 40));

    javax.swing.GroupLayout estrellaClik4Layout = new javax.swing.GroupLayout(estrellaClik4);
    estrellaClik4.setLayout(estrellaClik4Layout);
    estrellaClik4Layout.setHorizontalGroup(
        estrellaClik4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGap(0, 30, Short.MAX_VALUE)
    );
    estrellaClik4Layout.setVerticalGroup(
        estrellaClik4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGap(0, 40, Short.MAX_VALUE)
    );

    jPanel2.add(estrellaClik4, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 10, 30, 40));

    javax.swing.GroupLayout estrellaClik5Layout = new javax.swing.GroupLayout(estrellaClik5);
    estrellaClik5.setLayout(estrellaClik5Layout);
    estrellaClik5Layout.setHorizontalGroup(
        estrellaClik5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGap(0, 30, Short.MAX_VALUE)
    );
    estrellaClik5Layout.setVerticalGroup(
        estrellaClik5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGap(0, 40, Short.MAX_VALUE)
    );

    jPanel2.add(estrellaClik5, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 10, 30, 40));

    jTextArea6.setColumns(20);
    jTextArea6.setRows(5);
    jScrollPane6.setViewportView(jTextArea6);

    jPanel2.add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 320, 70));

    jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 270, 360, 180));

    jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

    jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/masculino (3).png"))); // NOI18N
    jPanel4.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 40, 60));

    Jorge1.setText("Diego");
    jPanel4.add(Jorge1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 20, -1, -1));

    Hace1.setText("Hace 2 horas.");
    jPanel4.add(Hace1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 40, -1, -1));

    javax.swing.GroupLayout estrellaClik6Layout = new javax.swing.GroupLayout(estrellaClik6);
    estrellaClik6.setLayout(estrellaClik6Layout);
    estrellaClik6Layout.setHorizontalGroup(
        estrellaClik6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGap(0, 30, Short.MAX_VALUE)
    );
    estrellaClik6Layout.setVerticalGroup(
        estrellaClik6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGap(0, 40, Short.MAX_VALUE)
    );

    jPanel4.add(estrellaClik6, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 10, 30, 40));

    javax.swing.GroupLayout estrellaClik7Layout = new javax.swing.GroupLayout(estrellaClik7);
    estrellaClik7.setLayout(estrellaClik7Layout);
    estrellaClik7Layout.setHorizontalGroup(
        estrellaClik7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGap(0, 30, Short.MAX_VALUE)
    );
    estrellaClik7Layout.setVerticalGroup(
        estrellaClik7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGap(0, 40, Short.MAX_VALUE)
    );

    jPanel4.add(estrellaClik7, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 10, 30, 40));

    javax.swing.GroupLayout estrellaClik8Layout = new javax.swing.GroupLayout(estrellaClik8);
    estrellaClik8.setLayout(estrellaClik8Layout);
    estrellaClik8Layout.setHorizontalGroup(
        estrellaClik8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGap(0, 30, Short.MAX_VALUE)
    );
    estrellaClik8Layout.setVerticalGroup(
        estrellaClik8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGap(0, 40, Short.MAX_VALUE)
    );

    jPanel4.add(estrellaClik8, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 10, 30, 40));

    javax.swing.GroupLayout estrellaClik9Layout = new javax.swing.GroupLayout(estrellaClik9);
    estrellaClik9.setLayout(estrellaClik9Layout);
    estrellaClik9Layout.setHorizontalGroup(
        estrellaClik9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGap(0, 30, Short.MAX_VALUE)
    );
    estrellaClik9Layout.setVerticalGroup(
        estrellaClik9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGap(0, 40, Short.MAX_VALUE)
    );

    jPanel4.add(estrellaClik9, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 10, 30, 40));

    javax.swing.GroupLayout estrellaClik10Layout = new javax.swing.GroupLayout(estrellaClik10);
    estrellaClik10.setLayout(estrellaClik10Layout);
    estrellaClik10Layout.setHorizontalGroup(
        estrellaClik10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGap(0, 30, Short.MAX_VALUE)
    );
    estrellaClik10Layout.setVerticalGroup(
        estrellaClik10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGap(0, 40, Short.MAX_VALUE)
    );

    jPanel4.add(estrellaClik10, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 10, 30, 40));

    jTextArea7.setColumns(20);
    jTextArea7.setRows(5);
    jScrollPane7.setViewportView(jTextArea7);

    jPanel4.add(jScrollPane7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 320, 70));

    jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 470, 360, 180));

    jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

    jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/masculino (3).png"))); // NOI18N
    jPanel5.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 40, 60));

    Jorge2.setText("Jorge");
    jPanel5.add(Jorge2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 20, -1, -1));

    Hace2.setText("Hace 2 horas.");
    jPanel5.add(Hace2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 40, -1, -1));

    javax.swing.GroupLayout estrellaClik11Layout = new javax.swing.GroupLayout(estrellaClik11);
    estrellaClik11.setLayout(estrellaClik11Layout);
    estrellaClik11Layout.setHorizontalGroup(
        estrellaClik11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGap(0, 30, Short.MAX_VALUE)
    );
    estrellaClik11Layout.setVerticalGroup(
        estrellaClik11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGap(0, 40, Short.MAX_VALUE)
    );

    jPanel5.add(estrellaClik11, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 10, 30, 40));

    javax.swing.GroupLayout estrellaClik12Layout = new javax.swing.GroupLayout(estrellaClik12);
    estrellaClik12.setLayout(estrellaClik12Layout);
    estrellaClik12Layout.setHorizontalGroup(
        estrellaClik12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGap(0, 30, Short.MAX_VALUE)
    );
    estrellaClik12Layout.setVerticalGroup(
        estrellaClik12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGap(0, 40, Short.MAX_VALUE)
    );

    jPanel5.add(estrellaClik12, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 10, 30, 40));

    javax.swing.GroupLayout estrellaClik13Layout = new javax.swing.GroupLayout(estrellaClik13);
    estrellaClik13.setLayout(estrellaClik13Layout);
    estrellaClik13Layout.setHorizontalGroup(
        estrellaClik13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGap(0, 30, Short.MAX_VALUE)
    );
    estrellaClik13Layout.setVerticalGroup(
        estrellaClik13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGap(0, 40, Short.MAX_VALUE)
    );

    jPanel5.add(estrellaClik13, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 10, 30, 40));

    javax.swing.GroupLayout estrellaClik14Layout = new javax.swing.GroupLayout(estrellaClik14);
    estrellaClik14.setLayout(estrellaClik14Layout);
    estrellaClik14Layout.setHorizontalGroup(
        estrellaClik14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGap(0, 30, Short.MAX_VALUE)
    );
    estrellaClik14Layout.setVerticalGroup(
        estrellaClik14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGap(0, 40, Short.MAX_VALUE)
    );

    jPanel5.add(estrellaClik14, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 10, 30, 40));

    javax.swing.GroupLayout estrellaClik15Layout = new javax.swing.GroupLayout(estrellaClik15);
    estrellaClik15.setLayout(estrellaClik15Layout);
    estrellaClik15Layout.setHorizontalGroup(
        estrellaClik15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGap(0, 30, Short.MAX_VALUE)
    );
    estrellaClik15Layout.setVerticalGroup(
        estrellaClik15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGap(0, 40, Short.MAX_VALUE)
    );

    jPanel5.add(estrellaClik15, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 10, 30, 40));

    jTextArea5.setColumns(20);
    jTextArea5.setRows(5);
    jScrollPane5.setViewportView(jTextArea5);

    jPanel5.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 320, 70));

    jPanel1.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 270, 360, 180));

    jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

    jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/masculino (3).png"))); // NOI18N
    jPanel6.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 40, 60));

    Jorge3.setText("Alex");
    jPanel6.add(Jorge3, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 20, -1, -1));

    Hace3.setText("Hace 2 horas.");
    jPanel6.add(Hace3, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 40, -1, -1));

    javax.swing.GroupLayout estrellaClik16Layout = new javax.swing.GroupLayout(estrellaClik16);
    estrellaClik16.setLayout(estrellaClik16Layout);
    estrellaClik16Layout.setHorizontalGroup(
        estrellaClik16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGap(0, 30, Short.MAX_VALUE)
    );
    estrellaClik16Layout.setVerticalGroup(
        estrellaClik16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGap(0, 40, Short.MAX_VALUE)
    );

    jPanel6.add(estrellaClik16, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 10, 30, 40));

    javax.swing.GroupLayout estrellaClik17Layout = new javax.swing.GroupLayout(estrellaClik17);
    estrellaClik17.setLayout(estrellaClik17Layout);
    estrellaClik17Layout.setHorizontalGroup(
        estrellaClik17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGap(0, 30, Short.MAX_VALUE)
    );
    estrellaClik17Layout.setVerticalGroup(
        estrellaClik17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGap(0, 40, Short.MAX_VALUE)
    );

    jPanel6.add(estrellaClik17, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 10, 30, 40));

    javax.swing.GroupLayout estrellaClik18Layout = new javax.swing.GroupLayout(estrellaClik18);
    estrellaClik18.setLayout(estrellaClik18Layout);
    estrellaClik18Layout.setHorizontalGroup(
        estrellaClik18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGap(0, 30, Short.MAX_VALUE)
    );
    estrellaClik18Layout.setVerticalGroup(
        estrellaClik18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGap(0, 40, Short.MAX_VALUE)
    );

    jPanel6.add(estrellaClik18, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 10, 30, 40));

    javax.swing.GroupLayout estrellaClik19Layout = new javax.swing.GroupLayout(estrellaClik19);
    estrellaClik19.setLayout(estrellaClik19Layout);
    estrellaClik19Layout.setHorizontalGroup(
        estrellaClik19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGap(0, 30, Short.MAX_VALUE)
    );
    estrellaClik19Layout.setVerticalGroup(
        estrellaClik19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGap(0, 40, Short.MAX_VALUE)
    );

    jPanel6.add(estrellaClik19, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 10, 30, 40));

    javax.swing.GroupLayout estrellaClik20Layout = new javax.swing.GroupLayout(estrellaClik20);
    estrellaClik20.setLayout(estrellaClik20Layout);
    estrellaClik20Layout.setHorizontalGroup(
        estrellaClik20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGap(0, 30, Short.MAX_VALUE)
    );
    estrellaClik20Layout.setVerticalGroup(
        estrellaClik20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGap(0, 40, Short.MAX_VALUE)
    );

    jPanel6.add(estrellaClik20, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 10, 30, 40));

    jTextArea8.setColumns(20);
    jTextArea8.setRows(5);
    jScrollPane8.setViewportView(jTextArea8);

    jPanel6.add(jScrollPane8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 320, 70));

    jPanel1.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 470, 360, 180));

    jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ordenar por:" }));
    jComboBox1.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jComboBox1ActionPerformed(evt);
        }
    });
    jPanel1.add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 24, 180, 40));

    jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/Menu 2.png"))); // NOI18N
    jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 14, 60, 60));

    jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

    jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/Oren.png"))); // NOI18N
    jPanel7.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, -20, 90, 130));

    Todavia.setText("Todavía no hay comentarios");
    jPanel7.add(Todavia, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, -1, -1));

    Experiencias.setText("Sé el primero en compartir tus experiencias. ");
    jPanel7.add(Experiencias, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, -1, -1));

    jButton1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
    jButton1.setText("Comentar");
    jButton1.setBorderPainted(false);
    jButton1.setContentAreaFilled(false);
    jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    jButton1.setFocusPainted(false);
    jPanel7.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 20, 190, 40));

    jPanel1.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 670, 960, 100));

    getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1140, 800));
    }// </editor-fold>//GEN-END:initComponents

    private void BúsquedaComentariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BúsquedaComentariosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BúsquedaComentariosActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField BúsquedaComentarios;
    private javax.swing.JLabel Comentarios1;
    private javax.swing.JLabel Experiencias;
    private javax.swing.JLabel Hace;
    private javax.swing.JLabel Hace1;
    private javax.swing.JLabel Hace2;
    private javax.swing.JLabel Hace3;
    private javax.swing.JLabel Jorge;
    private javax.swing.JLabel Jorge1;
    private javax.swing.JLabel Jorge2;
    private javax.swing.JLabel Jorge3;
    private javax.swing.JComboBox<String> LugaresCombo;
    private javax.swing.JComboBox<String> Masreciente;
    private javax.swing.JLabel MisComentarios;
    private javax.swing.JLabel Opiniones;
    private javax.swing.JLabel Todavia;
    private javax.swing.JLabel Usuario_Comentarios;
    private Design.EstrellaClick.EstrellaClik estrellaClik1;
    private Design.EstrellaClick.EstrellaClik estrellaClik10;
    private Design.EstrellaClick.EstrellaClik estrellaClik11;
    private Design.EstrellaClick.EstrellaClik estrellaClik12;
    private Design.EstrellaClick.EstrellaClik estrellaClik13;
    private Design.EstrellaClick.EstrellaClik estrellaClik14;
    private Design.EstrellaClick.EstrellaClik estrellaClik15;
    private Design.EstrellaClick.EstrellaClik estrellaClik16;
    private Design.EstrellaClick.EstrellaClik estrellaClik17;
    private Design.EstrellaClick.EstrellaClik estrellaClik18;
    private Design.EstrellaClick.EstrellaClik estrellaClik19;
    private Design.EstrellaClick.EstrellaClik estrellaClik2;
    private Design.EstrellaClick.EstrellaClik estrellaClik20;
    private Design.EstrellaClick.EstrellaClik estrellaClik3;
    private Design.EstrellaClick.EstrellaClik estrellaClik4;
    private Design.EstrellaClick.EstrellaClik estrellaClik5;
    private Design.EstrellaClick.EstrellaClik estrellaClik6;
    private Design.EstrellaClick.EstrellaClik estrellaClik7;
    private Design.EstrellaClick.EstrellaClik estrellaClik8;
    private Design.EstrellaClick.EstrellaClik estrellaClik9;
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JTextArea jTextArea5;
    private javax.swing.JTextArea jTextArea6;
    private javax.swing.JTextArea jTextArea7;
    private javax.swing.JTextArea jTextArea8;
    // End of variables declaration//GEN-END:variables

}
