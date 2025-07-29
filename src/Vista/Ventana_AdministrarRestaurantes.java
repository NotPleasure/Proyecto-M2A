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
import Animations.Animator1;
import Controlador.ControladorActualizarAdmin;
import Controlador.ControladorActualizarUsuarioPrincipal;
import Controlador.ControladorAdministrador;
import Design.RoundedPanel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;
import raven.glasspanepopup.GlassPanePopup;
import Modelo.IglesiaDAO;
import Modelo.IglesiaDetalleVista;
import Modelo.IglesiaVista;
import Modelo.MuseoDAO;
import Modelo.MuseoDetalleVista;
import Modelo.MuseoVista;
import Modelo.Persona;
import Modelo.Restaurante;
import Modelo.RestauranteDAO;
import java.awt.Window;
import java.sql.SQLException;
import javax.swing.JFrame;

/**
 *
 * @author USER
 */
public class Ventana_AdministrarRestaurantes extends javax.swing.JFrame {

    private List<Restaurante> listaMuseosCompleta = new ArrayList<>();
    private Persona personaLogueada;
    private boolean sinBordes = true;
    private JFrame ventanaDestino;

    public Ventana_AdministrarRestaurantes(JFrame padre, boolean sinBordes) {

        super();
        this.ventanaDestino = padre;

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
        cargarTablaMuseos();
        inicializarFiltroMuseos();

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

    private void cargarTablaMuseos() {
        try {
            RestauranteDAO dao = new RestauranteDAO();
            listaMuseosCompleta = dao.buscarSoloTexto(Busqueda2.getText());
            poblarTablaMuseos(listaMuseosCompleta);
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("No se ha podido cargar los museos");
        }
    }

    // 2) Método que recibe la lista y la dibuja
    private void poblarTablaMuseos(List<Restaurante> lista) {
        String[] columnas = {"ID", "Nombre", "Apertura", "Cierre"};
        DefaultTableModel modelo = new DefaultTableModel(null, columnas);

        for (Restaurante r : lista) {
            Object[] fila = {
                r.getId_negocio(),
                r.getNombre(),
                r.getHora_apertura(),
                r.getHora_cierre()
            };
            modelo.addRow(fila);
        }

        jTable1.setModel(modelo);
        personalizarTabla(jTable1);
    }

    // 3) Filtro de búsqueda
    private void inicializarFiltroMuseos() {
        RestauranteDAO dao = new RestauranteDAO();

        Busqueda2.getDocument().addDocumentListener(new DocumentListener() {
            private void filtrar() {
                String texto = Busqueda2.getText().trim();
                try {
                    List<Restaurante> listaFiltrada;
                    if (texto.isEmpty()) {
                        listaFiltrada = listaMuseosCompleta;
                    } else {
                        listaFiltrada = dao.buscarSoloTexto(texto);
                    }
                    poblarTablaMuseos(listaFiltrada);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
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

    public void recargarTablaMuseos() {
        cargarTablaMuseos();
    }

    //Para regresar entre ventanas:
    public void setVentanaDestino(JFrame v) {
        this.ventanaDestino = v;
    }

    private void mostrarConfirmacionEliminarIglesia(int id, String nombre) {

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
        jPanel2 = new RoundedPanel();
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
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(1330, 720));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Gestion.setBackground(new java.awt.Color(0, 0, 0));
        Gestion.setForeground(new java.awt.Color(81, 54, 31));
        Gestion.setText("GESTIÓN DE MUSEOS");
        jPanel2.add(Gestion, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, 400, -1));

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

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 90, 1180, 450));

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
        jPanel2.add(Eliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 600, 110, 30));

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

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/Fondo Adminsitrar Museos 1.png"))); // NOI18N
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1370, 860));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1370, 860));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Busqueda2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Busqueda2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Busqueda2ActionPerformed

    private void InsertarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InsertarActionPerformed
        Ventana_RegistroRestaurante ayudaPanel = new Ventana_RegistroRestaurante();

        GlassPanePopup.showPopup(ayudaPanel);


    }//GEN-LAST:event_InsertarActionPerformed

    private void ActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ActualizarActionPerformed
  int fila = jTable1.getSelectedRow();
    if (fila == -1) {
        JOptionPane.showMessageDialog(
            this,
            "Seleccione un restaurante de la tabla",
            "Atención",
            JOptionPane.WARNING_MESSAGE
        );
        return;
    }

    int neogicoid = (int) jTable1.getValueAt(fila, 0);

    try {
        RestauranteDAO dao = new RestauranteDAO();
        Restaurante vista = dao.obtenerDetalleRestaurante(neogicoid);

        if (vista == null) {
            JOptionPane.showMessageDialog(
                this,
                "No se encontraron datos del restaurante seleccionado.",
                "Error",
                JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        Ventana_ActualizarRestaurantes panel = new Ventana_ActualizarRestaurantes(this);
        panel.cargarDatosMuseo(vista);
        GlassPanePopup.showPopup(panel);

    } catch (SQLException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(
            this,
            "Error al cargar datos del restaurante:\n" + ex.getMessage(),
            "Error",
            JOptionPane.ERROR_MESSAGE
        );
    }

    }//GEN-LAST:event_ActualizarActionPerformed

    private void EliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EliminarActionPerformed
        int fila = jTable1.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this,
                    "Seleccione un museo de la tabla",
                    "Atención",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        int negocioid = (int) jTable1.getValueAt(fila, 0);
        String nombre = jTable1.getValueAt(fila, 1).toString();
        System.out.println("ID seleccionado a eliminar: " + negocioid + ", Nombre: " + nombre);

        mostrarConfirmacionEliminarMuseo(negocioid, nombre);

    }//GEN-LAST:event_EliminarActionPerformed

    private void SalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SalirActionPerformed
        Animator1.fadeOut(this, () -> {
            this.dispose();

            if (ventanaDestino != null) {
                ventanaDestino.setOpacity(0f);
                ventanaDestino.setVisible(true);
                Animator1.fadeIn(ventanaDestino);
            }
        });

    }//GEN-LAST:event_SalirActionPerformed

    private void mostrarConfirmacionEliminarMuseo(int id, String nombre) {
        Ventana_EliminarRestaurante panel = new Ventana_EliminarRestaurante(this);

        // Pasa el ID al panel
        panel.setIdMuseo(id);

        // Personaliza los labels dentro del panel
        panel.setLabelNombre(nombre);
        panel.setLabelId(String.valueOf(id));

        // Muestra el popup
        GlassPanePopup.showPopup(panel);
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
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
