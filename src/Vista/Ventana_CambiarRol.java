package Vista;

import Design.RoundedButtonAceptarComentario;
import Design.RoundedButtonActualizar;
import Design.RoundedButtonEliminarRe;
import Modelo.Administrador;
import Modelo.AdministradorDAO;
import Modelo.Persona;
import Modelo.PersonaDAO;
import Modelo.SuperUsuario;
import Modelo.SuperUsuarioDAO;
import Modelo.Usuario;
import Modelo.UsuarioDAO;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import raven.glasspanepopup.GlassPanePopup;

/**
 *
 * @author USER
 */
public class Ventana_CambiarRol extends javax.swing.JPanel {

    private int idPersona;
    private String usuario;
    private Ventana_Usuarios ventanaUsuarios;
    private Ventana_Admin ventanaAdministradores;
    private Ventana_SuperUsuario ventanaSuperUsuario;

    /**
     * Creates new form Ventana_CambiarRol
     */
    public Ventana_CambiarRol(int idPersona, String usuario, Ventana_Usuarios ventanaUsuarios, Ventana_Admin ventanaAdministradores, Ventana_SuperUsuario ventanaSuperUsuario) {
        initComponents();
        this.idPersona = idPersona;
        this.usuario = usuario;
        this.ventanaUsuarios = ventanaUsuarios;
        this.ventanaAdministradores = ventanaAdministradores;
        this.ventanaSuperUsuario = ventanaSuperUsuario;

        lblId.setText(String.valueOf(idPersona));
        lblNombre.setText(usuario);

        //Fuentes:
        Cambiar.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 25));
        Esta.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 25));
        lblNombre.setFont(new Font("Segoe UI Light", Font.PLAIN, 20));
        lblId.setFont(new Font("Segoe UI Light", Font.PLAIN, 20));
        user.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 15));
        id.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 15));

        btnUsuario.addActionListener(e -> cambiarRol(2));
        btnAdmin.addActionListener(e -> cambiarRol(1));
        btnSuperUsuario.addActionListener(e -> cambiarRol(3));

    }

    //Darle fondo redondeado al Popup:
    @Override
    protected void paintComponent(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        g2.fill(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 25, 25));
        g2.dispose();
        super.paintComponent(grphcs);
    }

    public JLabel getLblId() {
        return lblId;
    }

    public void setLblId(JLabel lblId) {
        this.lblId = lblId;
    }

    public JLabel getLblNombre() {
        return lblNombre;
    }

    public void setLblNombre(JLabel lblNombre) {
        this.lblNombre = lblNombre;
    }

    private void cambiarRol(int nuevoRolId) {
        PersonaDAO personaDAO = new PersonaDAO();
        Persona persona = personaDAO.leerPorId(idPersona);
        if (persona.getRolId() == nuevoRolId) {
            JOptionPane.showMessageDialog(this, "Ya tiene ese rol.");
            return;
        }

        switch (persona.getRolId()) {
            case 1:
                new AdministradorDAO().eliminarPorId(idPersona);
                break;
            case 2:
                new UsuarioDAO().eliminarPorId(idPersona);
                break;
            case 3:
                new SuperUsuarioDAO().eliminarPorId(idPersona);
                break;
        }

        personaDAO.actualizarRol(idPersona, nuevoRolId);

        switch (nuevoRolId) {
            case 1:
                AdministradorDAO adminDao = new AdministradorDAO();
                if (!adminDao.existePorId(idPersona)) {
                    Administrador admin = new Administrador();
                    admin.setIdPersona(idPersona);
                    admin.setCodigoAdmin(adminDao.generarCodigoAdmin());
                    adminDao.insertar(admin);
                }
                break;

            case 2:
                UsuarioDAO userDao = new UsuarioDAO();
                if (!userDao.existePorId(idPersona)) {
                    Usuario u = new Usuario();
                    u.setIdPersona(idPersona);
                    userDao.insertar(u);
                }
                break;
            case 3:
                SuperUsuarioDAO suDao = new SuperUsuarioDAO();
                if (!suDao.existePorId(idPersona)) {
                    SuperUsuario su = new SuperUsuario();
                    su.setIdPersona(idPersona);
                    suDao.insertar(su);
                }
                break;
        }

        if (ventanaUsuarios != null) {
            ventanaUsuarios.recargarTabla();
        }
        if (ventanaAdministradores != null) {
            ventanaAdministradores.recargarTabla();
        }
        if (ventanaSuperUsuario != null) {
            ventanaSuperUsuario.recargarTabla();
        }

        JOptionPane.showMessageDialog(this, "Rol actualizado correctamente.");
        GlassPanePopup.closePopupLast();
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
        Cambiar = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        Esta = new javax.swing.JLabel();
        lblNombre = new javax.swing.JLabel();
        lblId = new javax.swing.JLabel();
        user = new javax.swing.JLabel();
        id = new javax.swing.JLabel();
        btnAdmin = new RoundedButtonAceptarComentario("");
        btnUsuario = new RoundedButtonActualizar("");
        Cancelar = new RoundedButtonEliminarRe("");
        btnSuperUsuario = new RoundedButtonActualizar("");

        setPreferredSize(new java.awt.Dimension(630, 360));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(234, 45, 65));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Cambiar.setForeground(new java.awt.Color(255, 255, 255));
        Cambiar.setText("Cambiar Rol");
        jPanel1.add(Cambiar, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 24, -1, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/personas (1).png"))); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 4, 60, 70));

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 650, 80));

        Esta.setForeground(new java.awt.Color(0, 0, 0));
        Esta.setText("Cambiar rol de:");
        add(Esta, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 110, -1, -1));
        add(lblNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 170, 90, 20));
        add(lblId, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 170, 110, 20));

        user.setText("User");
        add(user, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 210, -1, -1));

        id.setText("Id");
        add(id, new org.netbeans.lib.awtextra.AbsoluteConstraints(374, 210, 30, -1));

        btnAdmin.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnAdmin.setText("Cambiar a Administrador");
        btnAdmin.setBorderPainted(false);
        btnAdmin.setContentAreaFilled(false);
        btnAdmin.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAdmin.setFocusPainted(false);
        btnAdmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdminActionPerformed(evt);
            }
        });
        add(btnAdmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 270, -1, -1));

        btnUsuario.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnUsuario.setText("Cambiar a Usuario");
        btnUsuario.setBorderPainted(false);
        btnUsuario.setContentAreaFilled(false);
        btnUsuario.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnUsuario.setFocusPainted(false);
        btnUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUsuarioActionPerformed(evt);
            }
        });
        add(btnUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 270, -1, -1));

        Cancelar.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        Cancelar.setText("Cancelar");
        Cancelar.setBorderPainted(false);
        Cancelar.setContentAreaFilled(false);
        Cancelar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Cancelar.setFocusPainted(false);
        Cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CancelarActionPerformed(evt);
            }
        });
        add(Cancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 270, 130, 30));

        btnSuperUsuario.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnSuperUsuario.setText("Cambiar a SuperUsuario");
        btnSuperUsuario.setBorderPainted(false);
        btnSuperUsuario.setContentAreaFilled(false);
        btnSuperUsuario.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSuperUsuario.setFocusPainted(false);
        add(btnSuperUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 320, -1, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void CancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CancelarActionPerformed
        GlassPanePopup.closePopupLast();
    }//GEN-LAST:event_CancelarActionPerformed

    private void btnAdminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdminActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAdminActionPerformed

    private void btnUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUsuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnUsuarioActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Cambiar;
    private javax.swing.JButton Cancelar;
    private javax.swing.JLabel Esta;
    private javax.swing.JButton btnAdmin;
    private javax.swing.JButton btnSuperUsuario;
    private javax.swing.JButton btnUsuario;
    private javax.swing.JLabel id;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblId;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel user;
    // End of variables declaration//GEN-END:variables
}
