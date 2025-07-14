/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Vista;

import Animations.Animator1;
import Design.RoundedButtonInsertar;
import Design.RoundedButtonSalirRe;
import Design.RoundedPannelGris;
import Design.Tabla.EncabezadoModernoRenderer;
import Design.Tabla.EstiloTablaModerna;
import Modelo.Persona;
import Modelo.PersonaDAO;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import raven.glasspanepopup.GlassPanePopup;

/**
 *
 * @author USER
 */
public class Ventana_Usuarios extends javax.swing.JFrame {

    private boolean sinBordes = true;
    private Persona personaLogueada;
    private Ventana_Admin ventanaAdministradores;

    /**
     * Creates new form Ventana_Usuarios
     */
    public Ventana_Usuarios(boolean sinBordes, Persona persona) {
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
        personalizarTabla(jTable1);
        cargarTablaUsuarios();
        inicializarFiltroBusqueda();

        GlassPanePopup.install(this);

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

    }

    //Personalizar la tabla:
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

    private void cargarTablaUsuarios() {
        PersonaDAO dao = new PersonaDAO();
        List<Persona> lista = dao.listarTodas();
        cargarTablaConLista(lista);
    }

    private void cargarTablaConLista(List<Persona> lista) {
        String[] columnas = {
            "ID", "Usuario", "Correo", "Nombres", "Apellidos",
            "Nacionalidad", "Género", "Nacimiento", "Rol", "Cédula"
        };
        DefaultTableModel modelo = new DefaultTableModel(null, columnas);
        for (Persona p : lista) {
            Object[] fila = {
                p.getIdPersona(),
                p.getUsuario(),
                p.getCorreo(),
                p.getNombres(),
                p.getApellidos(),
                p.getNacionalidad(),
                p.getGenero(),
                p.getFechaNacimiento(),
                p.getRolId(),
                p.getCedula()
            };
            modelo.addRow(fila);
        }
        jTable1.setModel(modelo);
        personalizarTabla(jTable1);
    }

    private void inicializarFiltroBusqueda() {
        PersonaDAO dao = new PersonaDAO();
        Busqueda2.getDocument().addDocumentListener(new DocumentListener() {
            private void filtrar() {
                String txt = Busqueda2.getText().trim();
                List<Persona> filtrada;
                if (txt.isEmpty()) {
                    filtrada = dao.listarTodas();
                } else {
                    filtrada = dao.buscarPorUsuarioONombre(txt);
                }
                cargarTablaConLista(filtrada);
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
        cargarTablaUsuarios();
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
        jPanel2 = new javax.swing.JPanel();
        Gestion = new javax.swing.JLabel();
        jPanel4 = new RoundedPannelGris();
        Busqueda2 = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        Insertar = new RoundedButtonInsertar("");
        jLabel5 = new javax.swing.JLabel();
        Salir = new RoundedButtonSalirRe("");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1390, 800));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(111, 133, 183));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Gestion.setBackground(new java.awt.Color(0, 0, 0));
        Gestion.setForeground(new java.awt.Color(0, 0, 0));
        Gestion.setText("GESTIÓN DE USUARIOS ");
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

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 120, 1160, 450));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/lapiz (1).png"))); // NOI18N
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 590, 50, 80));

        Insertar.setText("Cambiar Rol");
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
        jPanel2.add(Insertar, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 610, 120, 40));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/cerrar-sesion (1).png"))); // NOI18N
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 600, 50, 60));

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
        jPanel2.add(Salir, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 610, 120, 40));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, 1300, 770));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1370, 860));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Busqueda2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Busqueda2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Busqueda2ActionPerformed

    private void InsertarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InsertarActionPerformed
        int fila = jTable1.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(null, "Seleccione un usuario de la tabla");
            return;
        }

        int idPersona = (int) jTable1.getValueAt(fila, 0);
        String usuario = jTable1.getValueAt(fila, 1).toString();

        GlassPanePopup.showPopup(new Ventana_CambiarRol(idPersona, usuario, this, ventanaAdministradores));

    }//GEN-LAST:event_InsertarActionPerformed

    private void SalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SalirActionPerformed

        Animator1.fadeOut(this, () -> {
            this.dispose();

            Admin_Panel ventana = new Admin_Panel(true, personaLogueada);
            ventana.setOpacity(0f);
            ventana.setVisible(true);

            Animator1.fadeIn(ventana);
        });
    }//GEN-LAST:event_SalirActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Busqueda2;
    private javax.swing.JLabel Gestion;
    private javax.swing.JButton Insertar;
    private javax.swing.JButton Salir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
