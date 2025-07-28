/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Vista;

import Controlador.ControladorIglesia;
import Controlador.ControladorMuseo;
import Design.RoundedButtonInsertar;
import Design.RoundedPanelAdminSombra2;
import Design.RoundedPanelAdminSombra3;
import Design.RoundedPanelLugares;
import Modelo.FavoritoDAO;
import Modelo.IglesiaDetalleVista;
import Modelo.IglesiaVista;
import Modelo.MuseoDetalleVista;
import Modelo.MuseoVista;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.time.LocalTime;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JViewport;
import raven.glasspanepopup.GlassPanePopup;
import java.sql.SQLException;          
import utils.Session;

/**
 *
 * @author USER
 */
public class PanelCardMuseo extends javax.swing.JPanel {

    private MuseoVista vista;

    //Variables para inicializar los favoritos:
    private int idUsuario;
    private FavoritoDAO favoritoDao = new FavoritoDAO();

    /**
     * Creates new form PanelCardIglesia
     */
    public PanelCardMuseo(MuseoVista vista) {
        initComponents();
  this.vista = vista;
    this.idUsuario     = Session.getCurrentUserId();
    this.favoritoDao   = new FavoritoDAO();
        //Fuentes:
        lblNombre.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 20));
        lblHoraApertura.setFont(new Font("Segoe UI Light", Font.PLAIN, 12));
        lblHoraCierre.setFont(new Font("Segoe UI Light", Font.PLAIN, 12));
        configurarVista();

        // —— FAVORITOS ——
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
                    Container parent = this.getParent();
                    if (parent instanceof JViewport) {
                        parent = parent.getParent();
                    }
                    parent.remove(this);
                    parent.revalidate();
                    parent.repaint();
                }
            } catch (SQLException ex) {
                estrellaClik1.setFilled(!filled);
                JOptionPane.showMessageDialog(this,
                        "Error al actualizar favorito:\n" + ex.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    /*
    public PanelCardMuseo(int id, String nombre, String horaApertura, String horaCierre, byte[] imagenBytes) {
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

        this.vista = new MuseoVista(id, nombre, apertura, cierre, imagenBytes);
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

        Ver.addActionListener(e -> mostrarVentanaDetalle());
    }
     */
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(350, 340);
    }

    private void mostrarVentanaDetalle() {
        try {
            int id = vista.getId();
            ControladorMuseo ctrl = new ControladorMuseo();
            MuseoDetalleVista detalle = ctrl.obtenerDetalleMuseo(id);

            if (detalle != null) {
                Ventana_VerDetalleMuseos ventana = new Ventana_VerDetalleMuseos(detalle);
                ventana.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "No se encontraron los datos completos.");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this,
                    "Error al mostrar la museo:\n" + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    //Para poder visualizar los detalles visuales de cada card y luego poder entrar a la ventana de detalles:
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
        Ver.addActionListener(e -> mostrarVentanaDetalle());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new RoundedPanelAdminSombra2();
        lblImagenIglesia = new javax.swing.JLabel();
        estrellaClik1 = new Design.EstrellaClick.EstrellaClik(false);
        lblNombre = new javax.swing.JLabel();
        lblImagen = new javax.swing.JLabel();
        lblReloj = new javax.swing.JLabel();
        lblHoraCierre = new javax.swing.JLabel();
        lblHoraApertura = new javax.swing.JLabel();
        Ver = new RoundedButtonInsertar("");

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblImagenIglesia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/Museoa A.png"))); // NOI18N
        jPanel1.add(lblImagenIglesia, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, 70, 90));
        jPanel1.add(estrellaClik1, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 0, -1, -1));

        lblNombre.setForeground(new java.awt.Color(49, 49, 49));
        jPanel1.add(lblNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 190, 240, 30));
        jPanel1.add(lblImagen, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 350, 180));

        lblReloj.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/oRA.png"))); // NOI18N
        jPanel1.add(lblReloj, new org.netbeans.lib.awtextra.AbsoluteConstraints(34, 240, 30, 30));

        lblHoraCierre.setForeground(new java.awt.Color(127, 140, 141));
        jPanel1.add(lblHoraCierre, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 240, 80, 20));

        lblHoraApertura.setForeground(new java.awt.Color(127, 140, 141));
        jPanel1.add(lblHoraApertura, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 242, 80, 20));

        Ver.setText("Ver");
        Ver.setBorderPainted(false);
        Ver.setContentAreaFilled(false);
        Ver.setFocusPainted(false);
        jPanel1.add(Ver, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 290, 90, -1));

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(-2, 0, 350, 340));
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Ver;
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
