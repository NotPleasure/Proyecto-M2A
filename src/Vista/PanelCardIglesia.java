/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Vista;

import Controlador.ControladorIglesia;
import Design.RoundedButtonInsertar;
import Design.RoundedPanelAdminSombra;
import Design.RoundedPanelAdminSombra1;
import Design.RoundedPanelLugares;
import Modelo.IglesiaDetalleVista;
import Modelo.IglesiaVista;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.time.LocalTime;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import Modelo.FavoritoDAO;
import ConexionHuellasCuencanas.ConexionHuellasCuencanas;
import Design.EstrellaClick.EstrellaClik;
import java.awt.Container;
import java.awt.Window;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JViewport;
import javax.swing.SwingUtilities;
import raven.glasspanepopup.GlassPanePopup;
import utils.Session;

/**
 *
 * @author USER
 */
public class PanelCardIglesia extends javax.swing.JPanel {

    private IglesiaVista vista;

    //Para inyectar la lógica de la estrella:
    private int idUsuario;
    private EstrellaClik estrella;
    private final FavoritoDAO favoritoDao = new FavoritoDAO();

    /**
     * Creates new form PanelCardIglesia
     */
    public PanelCardIglesia(IglesiaVista vista) {
        initComponents();
        this.vista = vista;
        this.idUsuario = Session.getCurrentUserId();

        //Fuentes:
        lblNombre.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 20));

        lblHoraApertura.setFont(new Font("Segoe UI Light", Font.PLAIN, 12));
        lblHoraCierre.setFont(new Font("Segoe UI Light", Font.PLAIN, 12));

        configurarVista();
        try {
            boolean isFav = favoritoDao.esFavorito(idUsuario, vista.getId());
            estrellaClik1.setFilled(isFav);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        estrellaClik1.addFavoritoListener(filled -> {
            try {
                if (filled) {
                    favoritoDao.agregarFavorito(idUsuario, vista.getId());
                    GlassPanePopup.showPopup(new Ventana_AgregadoFavorito());
                } else {
                    favoritoDao.quitarFavorito(idUsuario, vista.getId());
                    GlassPanePopup.showPopup(new Ventana_QuitadoFavorito());
    
                    Container p = this.getParent();
                    if (p instanceof JViewport) {
                        p = p.getParent();
                    }
                    p.remove(this);
                    p.revalidate();
                    p.repaint();
                }

                
            } catch (SQLException ex) {
                estrellaClik1.setFilled(!filled);
                JOptionPane.showMessageDialog(this,
                        "Error al actualizar favorito:\n" + ex.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

    }

    private void mostrarPopupFavorito() {
        SwingUtilities.invokeLater(() -> {
            try {
                GlassPanePopup.showPopup(new Ventana_AgregadoFavorito());
            } catch (Exception ex) {
                System.err.println("Error al mostrar popup: " + ex.getMessage());
            }
        });
    }

    /* public PanelCardIglesia(int id, String nombre, String horaApertura, String horaCierre, byte[] imagenBytes) {

        initComponents();

        System.out.println(id);
        LocalTime apertura = null;
        LocalTime cierre = null;
        try {
            if (horaApertura != null && !horaApertura.isEmpty()) {
                apertura = LocalTime.parse(horaApertura);
            }
            if (horaCierre != null && !horaCierre.isEmpty()) {
                cierre = LocalTime.parse(horaCierre);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.vista = new IglesiaVista(id, nombre, apertura, cierre, imagenBytes);
        lblNombre.setText(nombre);
        lblHoraApertura.setText("Entrada: " + horaApertura);
        lblHoraCierre.setText("Salida: " + horaCierre);

        lblNombre.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 20));
        lblHoraApertura.setFont(new Font("Segoe UI Light", Font.PLAIN, 12));
        lblHoraCierre.setFont(new Font("Segoe UI Light", Font.PLAIN, 12));

        if (imagenBytes != null) {
            ImageIcon icono = new ImageIcon(imagenBytes);
            Image img = icono.getImage().getScaledInstance(350, 180, Image.SCALE_SMOOTH);
            lblImagen.setIcon(new ImageIcon(img));
        }

        btnVer.addActionListener(e -> mostrarVentanaDetalle());

    }
     */
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(350, 340);
    }

    private void mostrarVentanaDetalle() {
        try {
            int id = vista.getId();
            ControladorIglesia ctrl = new ControladorIglesia();
            IglesiaDetalleVista detalle = ctrl.obtenerDetalleIglesia(id);

            if (detalle != null) {
                Ventana_VerIglesias ventana = new Ventana_VerIglesias(detalle);
                ventana.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "No se encontraron los datos completos.");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this,
                    "Error al mostrar la iglesia:\n" + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    /*
    //Constructor que me permite llamar a favoritos:
    public PanelCardIglesia(int idUsuario, IglesiaVista v) throws SQLException {

        //Invocar al constructor que tiene todo:
        this(
                v.getId(),
                v.getNombre(),
                v.getHoraApertura() != null ? v.getHoraApertura().toString() : "",
                v.getHoraCierre() != null ? v.getHoraCierre().toString() : "",
                v.getImagenPrincipal()
        );

        this.idUsuario = idUsuario;

        boolean isFav = favoritoDao.esFavorito(idUsuario, vista.getId());
        estrellaClik1.setFilled(isFav);

        estrellaClik1.addFavoritoListener(filled -> {
            try {
                if (filled) {
                    favoritoDao.agregarFavorito(idUsuario, vista.getId());
                    GlassPanePopup.showPopup(new Ventana_AgregadoFavorito());
                    
                } else {   System.out.println("Listener disparado: quitando favorito usuario=" 
                + idUsuario + " lugar=" + vista.getId());
                
                    favoritoDao.quitarFavorito(idUsuario, vista.getId());
                    Container parent = this.getParent();
                    parent.remove(this);
                    parent.revalidate();
                    parent.repaint();
                }
            } catch (SQLException ex) {
                estrellaClik1.setFilled(!filled);
                JOptionPane.showMessageDialog(
                        this,
                        "Error al actualizar favorito:\n" + ex.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });
    }
     */
    private void configurarVista() {
        lblNombre.setText(vista.getNombre());
        lblHoraApertura.setText("Entrada: "
                + (vista.getHoraApertura() != null ? vista.getHoraApertura() : ""));
        lblHoraCierre.setText("Salida: "
                + (vista.getHoraCierre() != null ? vista.getHoraCierre() : ""));
        if (vista.getImagenPrincipal() != null) {
            ImageIcon icon = new ImageIcon(vista.getImagenPrincipal());
            Image img = icon.getImage()
                    .getScaledInstance(350, 180, Image.SCALE_SMOOTH);
            lblImagen.setIcon(new ImageIcon(img));
        }
        btnVer.addActionListener(e -> mostrarVentanaDetalle());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new RoundedPanelAdminSombra
        ();
        lblImagenIglesia = new javax.swing.JLabel();
        estrellaClik1 = new Design.EstrellaClick.EstrellaClik(false);
        lblNombre = new javax.swing.JLabel();
        lblImagen = new javax.swing.JLabel();
        lblReloj = new javax.swing.JLabel();
        lblHoraCierre = new javax.swing.JLabel();
        lblHoraApertura = new javax.swing.JLabel();
        btnVer = new RoundedButtonInsertar("");

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblImagenIglesia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/Iglesias 3.png"))); // NOI18N
        jPanel1.add(lblImagenIglesia, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, 70, 90));
        jPanel1.add(estrellaClik1, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 0, -1, -1));

        lblNombre.setForeground(new java.awt.Color(49, 49, 49));
        jPanel1.add(lblNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 190, 240, 30));
        jPanel1.add(lblImagen, new org.netbeans.lib.awtextra.AbsoluteConstraints(-2, 0, 350, 180));

        lblReloj.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/oRA.png"))); // NOI18N
        jPanel1.add(lblReloj, new org.netbeans.lib.awtextra.AbsoluteConstraints(34, 240, 30, 30));

        lblHoraCierre.setForeground(new java.awt.Color(127, 140, 141));
        jPanel1.add(lblHoraCierre, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 240, 80, 20));

        lblHoraApertura.setForeground(new java.awt.Color(127, 140, 141));
        jPanel1.add(lblHoraApertura, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 242, 80, 20));

        btnVer.setText("Ver");
        btnVer.setBorderPainted(false);
        btnVer.setContentAreaFilled(false);
        btnVer.setFocusPainted(false);
        jPanel1.add(btnVer, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 290, 90, -1));

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 350, 340));
    }// </editor-fold>//GEN-END:initComponents

    private void btnVerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnVerActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnVer;
    private Design.EstrellaClick.EstrellaClik estrellaClik1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblHoraApertura;
    private javax.swing.JLabel lblHoraCierre;
    private javax.swing.JLabel lblImagen;
    private javax.swing.JLabel lblImagenIglesia;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblReloj;
    // End of variables declaration//GEN-END:variables
}
