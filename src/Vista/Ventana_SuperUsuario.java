/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Vista;

import Design.RoundedButtonActualizar;
import Design.RoundedButtonEliminarRe;
import Design.RoundedButtonInsertar;
import Design.RoundedButtonSalirRe;
import Design.RoundedPanelAdmin;
import Design.RoundedPannelGris;
import Design.Tabla.EncabezadoModernoRenderer;
import Design.Tabla.EstiloTablaModerna;
import Modelo.SuperUsuario;
import Modelo.SuperUsuarioDAO;
import Modelo.Usuario;
import Modelo.UsuarioDAO;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JTable;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import Modelo.AdministradorDAO;
import Modelo.Administrador;
import Animations.Animator1;
import Controlador.ControladorActualizarAdmin;
import Controlador.ControladorActualizarSuperUsuario;
import Controlador.ControladorActualizarUsuarioPrincipal;
import Controlador.ControladorAdministrador;
import Controlador.ControladorInsertarSuperUsuario;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;
import raven.glasspanepopup.GlassPanePopup;
import Modelo.AdministradorDAO;
import Modelo.Persona;

/**
 *
 * @author USER
 */
public class Ventana_SuperUsuario extends javax.swing.JFrame {

    private List<SuperUsuario> listaUsuariosCompleta = new ArrayList<>();
    private SuperUsuarioDAO superUsuarioDAO = new SuperUsuarioDAO();

    private boolean sinBordes = true;
    private Persona personaLogueada;

    /**
     * Creates new form Ventana_Admin
     */
    public Ventana_SuperUsuario(boolean sinBordes, Persona persona) {
        this.personaLogueada = persona;
        if (sinBordes) {
            setUndecorated(true);
        }

        //Para quitar la selección de la tabla:
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                jTable1.clearSelection();
            }
        });

        initComponents();
        GlassPanePopup.install(this);

        //Método para personalizar la tabla:
        personalizarTabla(jTable1);

        listaUsuariosCompleta = superUsuarioDAO.listarTodos();
        cargarTablaConLista(listaUsuariosCompleta);

        inicializarFiltroBusqueda();

        //Agregar ScrollPanel a la tabla:
        jScrollPane1.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 220)));

        //Extender la ventana al máximo: 
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        // Para pantalla completa:
        setExtendedState(Ventana_Principal.MAXIMIZED_BOTH);

        //Quitar el fondo al TextField:
        Busqueda2.setOpaque(false);
        Busqueda2.setBorder(null);
        Busqueda2.setForeground(Color.BLACK);

        //Fuentes:
        Gestion.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 24));
        Busqueda2.setFont(new Font("Segoe UI Light", Font.PLAIN, 14));
        Insertar.setFont(new Font("Segoe UI Light", Font.PLAIN, 12));
        Actualizar.setFont(new Font("Segoe UI Light", Font.PLAIN, 12));
        Eliminar.setFont(new Font("Segoe UI Light", Font.PLAIN, 12));
        Salir.setFont(new Font("Segoe UI Light", Font.PLAIN, 12));
    }

    public void personalizarTabla(JTable tabla) {

        tabla.setRowHeight(32);
        tabla.setShowVerticalLines(false);
        tabla.setShowHorizontalLines(false);
        tabla.setGridColor(new Color(220, 220, 220));
        tabla.setFont(new Font("Segoe UI Light", Font.PLAIN, 14));

        tabla.getTableHeader().setDefaultRenderer(new EncabezadoModernoRenderer());
        tabla.getTableHeader().setReorderingAllowed(false);

        TableCellRenderer rendererFilas = new EstiloTablaModerna();
        for (int i = 0; i < tabla.getColumnCount(); i++) {
            tabla.getColumnModel().getColumn(i).setCellRenderer(rendererFilas);
        }

        tabla.setIntercellSpacing(new Dimension(0, 0));
        tabla.setFillsViewportHeight(true);
        tabla.setBackground(new Color(250, 250, 250));
    }

    //Cargar tabla:
    private void cargarTablaSuperUsuarios() {
        SuperUsuarioDAO dao = new SuperUsuarioDAO();
        List<SuperUsuario> lista = dao.listarTodos();

        String[] columnas = {
            "ID", "Usuario", "Correo", "Nombres", "Apellidos",
            "Nacionalidad", "Género", "Nacimiento", "Rol", "Cédula"
        };

        DefaultTableModel modelo = new DefaultTableModel(null, columnas);

        for (SuperUsuario u : lista) {
            Object[] fila = {
                u.getIdPersona(),
                u.getUsuario(),
                u.getCorreo(),
                u.getNombres(),
                u.getApellidos(),
                u.getNacionalidad(),
                u.getGenero(),
                u.getFechaNacimiento(),
                u.getRolId(),
                u.getCedula()
            };
            modelo.addRow(fila);
        }

        jTable1.setModel(modelo);
    }

    //Cargar todos los superusuarios:
    public void cargarUsuarios() {
        SuperUsuarioDAO dao = new SuperUsuarioDAO();
        listaUsuariosCompleta = dao.listarTodos();
        cargarTablaConLista(listaUsuariosCompleta);
    }

    //Cargar la tabla con el Array:
    private void cargarTablaConLista(List<SuperUsuario> lista) {
        String[] columnas = {"ID", "Usuario", "Correo", "Nombres", "Apellidos",
            "Nacionalidad", "Género", "Nacimiento", "Rol", "Cédula"};

        DefaultTableModel modelo = new DefaultTableModel(null, columnas);

        for (SuperUsuario su : lista) {
            Object[] fila = {
                su.getIdPersona(),
                su.getUsuario(),
                su.getCorreo(),
                su.getNombres(),
                su.getApellidos(),
                su.getNacionalidad(),
                su.getGenero(),
                su.getFechaNacimiento(),
                su.getRolId(),
                su.getCedula()
            };
            modelo.addRow(fila);
        }

        jTable1.setModel(modelo);
        personalizarTabla(jTable1);

    }

    private void inicializarFiltroBusqueda() {
        Busqueda2.getDocument().addDocumentListener(new DocumentListener() {
            private void filtrar() {
                String texto = Busqueda2.getText().trim();
                List<SuperUsuario> listaFiltrada;

                if (texto.isEmpty()) {
                    listaFiltrada = superUsuarioDAO.listarTodos();
                } else {
                    listaFiltrada = superUsuarioDAO.buscarSuperusuarios(texto);
                }

                cargarTablaConLista(listaFiltrada);
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                filtrar();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                filtrar();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                filtrar();
            }
        });
    }

    public void recargarTabla() {
        listaUsuariosCompleta = superUsuarioDAO.listarTodos();
        cargarTablaConLista(listaUsuariosCompleta);
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
        jPanel2 =  new RoundedPanelAdmin();
        Gestion = new javax.swing.JLabel();
        jPanel4 = new RoundedPannelGris();
        Busqueda2 = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        Insertar = new RoundedButtonInsertar("");
        jLabel2 = new javax.swing.JLabel();
        Actualizar = new RoundedButtonActualizar("");
        jLabel3 = new javax.swing.JLabel();
        Eliminar = new RoundedButtonEliminarRe("");
        jLabel5 = new javax.swing.JLabel();
        Salir = new RoundedButtonSalirRe("");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 64, 89));
        jPanel1.setPreferredSize(new java.awt.Dimension(1330, 720));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Gestion.setBackground(new java.awt.Color(0, 0, 0));
        Gestion.setForeground(new java.awt.Color(0, 0, 0));
        Gestion.setText("GESTIÓN DE SUPERUSUARIOS");
        jPanel2.add(Gestion, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, 550, -1));

        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Busqueda2.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        Busqueda2.setOpaque(true);
        Busqueda2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Busqueda2ActionPerformed(evt);
            }
        });
        jPanel4.add(Busqueda2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 0, 420, 30));

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/Lupa__Ícono_-removebg-preview (1).png"))); // NOI18N
        jPanel4.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, -24, 48, 80));

        jPanel2.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 30, -1, 30));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 130, 1180, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/lapiz (1).png"))); // NOI18N
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 570, 50, 80));

        Insertar.setText("Insertar");
        Insertar.setBorderPainted(false);
        Insertar.setContentAreaFilled(false);
        Insertar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Insertar.setFocusPainted(false);
        Insertar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Insertar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InsertarActionPerformed(evt);
            }
        });
        jPanel2.add(Insertar, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 600, 110, 30));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/archivo (1).png"))); // NOI18N
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 580, 80, 70));

        Actualizar.setText("Actualizar");
        Actualizar.setBorderPainted(false);
        Actualizar.setContentAreaFilled(false);
        Actualizar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Actualizar.setFocusPainted(false);
        Actualizar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Actualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ActualizarActionPerformed(evt);
            }
        });
        jPanel2.add(Actualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 600, 110, 30));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/eliminar-producto (1).png"))); // NOI18N
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 590, 70, 50));

        Eliminar.setText("Eliminar");
        Eliminar.setBorderPainted(false);
        Eliminar.setContentAreaFilled(false);
        Eliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Eliminar.setFocusPainted(false);
        Eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EliminarActionPerformed(evt);
            }
        });
        jPanel2.add(Eliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 600, 110, 30));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/cerrar-sesion (1).png"))); // NOI18N
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 580, 50, 60));

        Salir.setText("Salir");
        Salir.setBorderPainted(false);
        Salir.setContentAreaFilled(false);
        Salir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Salir.setFocusPainted(false);
        Salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SalirActionPerformed(evt);
            }
        });
        jPanel2.add(Salir, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 600, 110, 30));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, 1300, 680));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1370, 860));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Busqueda2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Busqueda2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Busqueda2ActionPerformed

    private void InsertarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InsertarActionPerformed
        Ventana_FormularioSuperUsuario formSuper = new Ventana_FormularioSuperUsuario();

        GlassPanePopup.showPopup(formSuper);

        new ControladorInsertarSuperUsuario(this, formSuper);

    }//GEN-LAST:event_InsertarActionPerformed

    private void ActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ActualizarActionPerformed
        int fila = jTable1.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(null, "Seleccione un superusuario de la tabla");
            return;
        }

        int idPersona = (int) jTable1.getValueAt(fila, 0);

        SuperUsuarioDAO dao = new SuperUsuarioDAO();
        SuperUsuario superUsuario = dao.obtenerPorId(idPersona);

        if (superUsuario != null) {
            Ventana_Actualizar_SuperUsuario form = new Ventana_Actualizar_SuperUsuario();
            form.cargarDatosSuperUsuario(superUsuario);
            new ControladorActualizarSuperUsuario(form, superUsuario, this);
            GlassPanePopup.showPopup(form);
        } else {
            JOptionPane.showMessageDialog(this, "No se pudo cargar el superusuario.");
        }


    }//GEN-LAST:event_ActualizarActionPerformed

    private void EliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EliminarActionPerformed
        int fila = jTable1.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(null, "Seleccione un usuario de la tabla");
            return;
        }
        int idPersona = (int) jTable1.getValueAt(fila, 0);
        String usuario = jTable1.getValueAt(fila, 1).toString();

        mostrarConfirmacionEliminar(idPersona, usuario);
    }//GEN-LAST:event_EliminarActionPerformed

    private void SalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SalirActionPerformed
        Animator1.fadeOut(this, () -> {
            this.dispose();

            Admin_Panel ventana = new Admin_Panel(true, personaLogueada);
            ventana.setOpacity(0f);
            ventana.setVisible(true);

            Animator1.fadeIn(ventana);
        });


    }//GEN-LAST:event_SalirActionPerformed

    private void mostrarConfirmacionEliminar(int idPersona, String usuario) {
        Ventana_EliminarSuperUsuario popup = new Ventana_EliminarSuperUsuario(idPersona, usuario, this);
        GlassPanePopup.showPopup(popup);
    }

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Actualizar;
    private javax.swing.JTextField Busqueda2;
    private javax.swing.JButton Eliminar;
    private javax.swing.JLabel Gestion;
    private javax.swing.JButton Insertar;
    private javax.swing.JButton Salir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
