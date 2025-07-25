/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Design.ScrollBar.BarraProRedonda;
import Design.RoundedPannelGris;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

/**
 *
 * @author USER
 */
public class Ventana_Hoteles extends javax.swing.JFrame {

    /**
     * Creates new form Ventana_Hoteles
     */
    public Ventana_Hoteles() {
        initComponents();

        //Fuentes:
        Busqueda2.setFont(new Font("Caviar Dreams", Font.PLAIN, 17));
        lblTituloResultado.setFont(new Font("Caviar Dreams", Font.PLAIN, 17));
        lblTextoResultado.setFont(new Font("Caviar Dreams", Font.PLAIN, 17));

        //Hacer que la barra de búsqueda se oculte antes y despúes de cargar: 
        jProgressBar1.setIndeterminate(true);
        jProgressBar1.setVisible(true);
        jProgressBar1.setVisible(false);

        //Quitar el fondo al TextField:
        Busqueda2.setOpaque(false);
        Busqueda2.setBorder(null);
        Busqueda2.setForeground(Color.BLACK);

        //ActionListener para la búsqueda:
        Busqueda2.addActionListener(e -> {
            String texto = Busqueda2.getText().trim();

            if (texto.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Escribe algo para buscar");
                return;
            }

            jProgressBar1.setVisible(true);
            Busqueda2.setEnabled(false);
            lblTituloResultado.setText("");
            lblTextoResultado.setText("");

            new Thread(() -> {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }

                SwingUtilities.invokeLater(() -> {
                    jProgressBar1.setVisible(false);
                    lblTituloResultado.setText("Resultados para: ");
                    lblTextoResultado.setText(texto);
                    lblTextoResultado.setForeground(new Color(52, 152, 219));

                    Busqueda2.setEnabled(true);
                    Busqueda2.requestFocus();
                    Busqueda2.selectAll();
                });
            }).start();
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
        jPanel3 = new RoundedPannelGris();
        Busqueda2 = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jProgressBar1 = new javax.swing.JProgressBar();
        lblTituloResultado = new javax.swing.JLabel();
        lblTextoResultado = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        jPanel1.setBackground(new java.awt.Color(242, 242, 242));
        jPanel1.setPreferredSize(new java.awt.Dimension(1140, 830));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Busqueda2.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        Busqueda2.setOpaque(true);
        Busqueda2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Busqueda2ActionPerformed(evt);
            }
        });
        jPanel3.add(Busqueda2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 0, 480, 50));

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/Lupa__Ícono_-removebg-preview (1).png"))); // NOI18N
        jPanel3.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, -14, 48, 80));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 30, 560, 50));

        jProgressBar1.setIndeterminate(true);
        jPanel1.add(jProgressBar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 110, 1020, 10));
        jPanel1.add(lblTituloResultado, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 110, 1080, 20));
        jPanel1.add(lblTextoResultado, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 105, 240, 30));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/icons8_Expand_Arrow_32px.png"))); // NOI18N
        jLabel6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel6MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 20, 30, 30));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/Salir (2) (2).png"))); // NOI18N
        jLabel3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 16, 30, 40));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 824, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void Busqueda2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Busqueda2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Busqueda2ActionPerformed

    private void jLabel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseClicked

    }//GEN-LAST:event_jLabel6MouseClicked

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        int dialog = JOptionPane.YES_NO_OPTION;
        int result = JOptionPane.showConfirmDialog(null, "¿Desea salir?", "Exit", dialog);
        if (result == 0) {
            System.exit(0);
        }
    }//GEN-LAST:event_jLabel3MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Busqueda2;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JLabel lblTextoResultado;
    private javax.swing.JLabel lblTituloResultado;
    // End of variables declaration//GEN-END:variables
}
