/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Vista;

import Controlador.ControladorMuseo;
import Controlador.ControladorParque;
import Design.RoundedButtonInsertar;
import Design.RoundedPanelLugares;
import Modelo.MuseoDetalleVista;
import Modelo.ParqueDetalleVista;
import Modelo.ParqueVista;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author USER
 */
public class PanelCardParque extends javax.swing.JPanel {

    /**
     * Creates new form PanelCardIglesia
     */
     private ParqueVista vista;
    public PanelCardParque() {
        initComponents();

        //Fuentes:
        lblNombre.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 20));

        lblHoraApertura.setFont(new Font("Segoe UI Light", Font.PLAIN, 12));
        lblHoraCierre.setFont(new Font("Segoe UI Light", Font.PLAIN, 12));

    }

    public PanelCardParque(int id,String nombre, String entidad_gestora, float superfice, byte[] imagenBytes) {
        initComponents();
        this.vista =new ParqueVista(id,nombre,superfice,entidad_gestora,imagenBytes);
        lblNombre.setText(nombre);
        lblHoraApertura.setText("Entidad Gestora: " + entidad_gestora);
        lblHoraCierre.setText("Superficie  m2: " + superfice);

        lblNombre.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 20));
        lblHoraApertura.setFont(new Font("Segoe UI Light", Font.PLAIN, 12));
        lblHoraCierre.setFont(new Font("Segoe UI Light", Font.PLAIN, 12));
         
        // Imagen principal
        if (imagenBytes != null) {
            ImageIcon icono = new ImageIcon(imagenBytes);
            Image img = icono.getImage().getScaledInstance(350, 180, Image.SCALE_SMOOTH);
            lblImagen.setIcon(new ImageIcon(img));
        }
         Ver.addActionListener(e -> mostrarVentanaDetalle());
        // (Los iconos lblImagenIglesia y lblReloj ya los pusiste desde el diseñador, así que no hay que tocarlos aquí.)
    }
    
    @Override
public Dimension getPreferredSize() {
    return new Dimension(350, 340); 
}
   private void mostrarVentanaDetalle() {
       
         try {
        System.out.println("ID que se envía al detalle: " + vista.getId()); 
        
        int id = vista.getId();
        ControladorParque ctrl = new ControladorParque();
        ParqueDetalleVista detalle = ctrl.obtenerDetalleParque(id);

            if (detalle != null) {
                Ventana_VerDetalleParques ventana = new Ventana_VerDetalleParques(detalle);
                ventana.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "No se encontraron los datos completos.");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this,
                    "Error al mostrar la parque:\n" + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
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

        jPanel1 = new RoundedPanelLugares();
        lblImagenIglesia = new javax.swing.JLabel();
        lblNombre = new javax.swing.JLabel();
        lblImagen = new javax.swing.JLabel();
        lblReloj = new javax.swing.JLabel();
        lblHoraCierre = new javax.swing.JLabel();
        lblHoraApertura = new javax.swing.JLabel();
        Ver = new RoundedButtonInsertar("");

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblImagenIglesia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/Parques A.png"))); // NOI18N
        jPanel1.add(lblImagenIglesia, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 160, 70, 90));

        lblNombre.setForeground(new java.awt.Color(49, 49, 49));
        jPanel1.add(lblNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 190, 260, 30));
        jPanel1.add(lblImagen, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 350, 180));

        lblReloj.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/oRA.png"))); // NOI18N
        jPanel1.add(lblReloj, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 240, 30, 30));

        lblHoraCierre.setForeground(new java.awt.Color(127, 140, 141));
        jPanel1.add(lblHoraCierre, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 242, 130, 20));

        lblHoraApertura.setForeground(new java.awt.Color(127, 140, 141));
        jPanel1.add(lblHoraApertura, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 242, 120, 20));

        Ver.setText("Ver");
        Ver.setBorderPainted(false);
        Ver.setContentAreaFilled(false);
        Ver.setFocusPainted(false);
        jPanel1.add(Ver, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 290, 90, -1));

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 350, 340));
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Ver;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblHoraApertura;
    private javax.swing.JLabel lblHoraCierre;
    private javax.swing.JLabel lblImagen;
    private javax.swing.JLabel lblImagenIglesia;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblReloj;
    // End of variables declaration//GEN-END:variables
}
