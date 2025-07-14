/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ConexionHuellasCuencanas;
import Modelo.Rol;
import Modelo.RolDAO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.util.List;

public class CRUDRoles extends JFrame {

    private JTable tablaRoles;
    private DefaultTableModel modelo;
    private JTextField txtNombre;
    private JButton btnGuardar, btnActualizar, btnEliminar;
    private RolDAO rolDAO = new RolDAO();
    private int idSeleccionado = -1;

    public CRUDRoles() {
        setTitle("CRUD Roles");
        setSize(400, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        JLabel lblTitulo = new JLabel("Gestión de Roles");
        lblTitulo.setBounds(130, 10, 200, 30);
        add(lblTitulo);

        modelo = new DefaultTableModel(new String[]{"ID", "Nombre"}, 0);
        tablaRoles = new JTable(modelo);
        JScrollPane scroll = new JScrollPane(tablaRoles);
        scroll.setBounds(20, 50, 340, 150);
        add(scroll);

        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(20, 210, 100, 25);
        add(lblNombre);

        txtNombre = new JTextField();
        txtNombre.setBounds(80, 210, 180, 25);
        add(txtNombre);

        btnGuardar = new JButton("Guardar");
        btnGuardar.setBounds(270, 210, 90, 25);
        add(btnGuardar);

        btnActualizar = new JButton("Actualizar");
        btnActualizar.setBounds(20, 250, 160, 30);
        add(btnActualizar);

        btnEliminar = new JButton("Eliminar");
        btnEliminar.setBounds(200, 250, 160, 30);
        add(btnEliminar);

        cargarRoles();

        btnGuardar.addActionListener(e -> {
            String nombre = txtNombre.getText().trim();
            if (!nombre.isEmpty()) {
                if (rolDAO.insertarRol(nombre)) {
                    JOptionPane.showMessageDialog(this, "Rol insertado");
                    txtNombre.setText("");
                    cargarRoles();
                } else {
                    JOptionPane.showMessageDialog(this, "Error al insertar rol");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Ingrese un nombre");
            }
        });

        btnActualizar.addActionListener(e -> {
            if (idSeleccionado != -1) {
                String nombre = txtNombre.getText().trim();
                if (!nombre.isEmpty()) {
                    if (rolDAO.actualizarRol(idSeleccionado, nombre)) {
                        JOptionPane.showMessageDialog(this, "Rol actualizado");
                        txtNombre.setText("");
                        idSeleccionado = -1;
                        cargarRoles();
                    } else {
                        JOptionPane.showMessageDialog(this, "Error al actualizar rol");
                    }
                }
            }
        });

        btnEliminar.addActionListener(e -> {
            if (idSeleccionado != -1) {
                int confirm = JOptionPane.showConfirmDialog(this, "¿Eliminar rol?", "Confirmar", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    if (rolDAO.eliminarRol(idSeleccionado)) {
                        JOptionPane.showMessageDialog(this, "Rol eliminado");
                        txtNombre.setText("");
                        idSeleccionado = -1;
                        cargarRoles();
                    } else {
                        JOptionPane.showMessageDialog(this, "Error al eliminar rol");
                    }
                }
            }
        });

        tablaRoles.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int fila = tablaRoles.getSelectedRow();
                if (fila != -1) {
                    idSeleccionado = Integer.parseInt(modelo.getValueAt(fila, 0).toString());
                    String nombre = modelo.getValueAt(fila, 1).toString();
                    txtNombre.setText(nombre);
                }
            }
        });
    }

    private void cargarRoles() {
        modelo.setRowCount(0);
        List<Rol> lista = rolDAO.listarRoles();
        for (Rol r : lista) {
            modelo.addRow(new Object[]{r.getId(), r.getNombre()});
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CRUDRoles().setVisible(true));
    }
}

