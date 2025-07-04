/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test;

import dao.CantonDAO;
import dao.ProvinciaDAO;
import model.Canton;
import model.Provincia;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class CantonesPanel extends JPanel {
    private CantonDAO cantonDAO;
    private ProvinciaDAO provinciaDAO;

    private JTable table;
    private DefaultTableModel tableModel;

    private JTextField txtId, txtNombre, txtBuscar;
    private JComboBox<Provincia> cbProvincia;
    private JButton btnCrear, btnActualizar, btnEliminar, btnBuscar, btnLimpiar, btnRefrescar;
    private JLabel lblTotal;

    public CantonesPanel() {
        cantonDAO = new CantonDAO();
        provinciaDAO = new ProvinciaDAO();

        initComponents();
        setupLayout();
        setupEvents();
        cargarProvincias();
        cargarDatos();
    }

    private void initComponents() {
        txtId = new JTextField(5);
        txtId.setEditable(false);
        txtNombre = new JTextField(20);
        txtBuscar = new JTextField(15);

        cbProvincia = new JComboBox<>();

        btnCrear = new JButton("Crear");
        btnActualizar = new JButton("Actualizar");
        btnEliminar = new JButton("Eliminar");
        btnBuscar = new JButton("Buscar");
        btnLimpiar = new JButton("Limpiar");
        btnRefrescar = new JButton("Refrescar");

        btnCrear.setBackground(new Color(76, 175, 80));
        btnCrear.setForeground(Color.WHITE);
        btnActualizar.setBackground(new Color(33, 150, 243));
        btnActualizar.setForeground(Color.WHITE);
        btnEliminar.setBackground(new Color(244, 67, 54));
        btnEliminar.setForeground(Color.WHITE);

        String[] columnas = {"ID", "Nombre", "Provincia"};
        tableModel = new DefaultTableModel(columnas, 0) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        table = new JTable(tableModel);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setRowHeight(25);

        lblTotal = new JLabel("Total: 0");
    }

    private void setupLayout() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createTitledBorder("Formulario"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(new JLabel("ID:"), gbc);
        gbc.gridx = 1;
        formPanel.add(txtId, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        formPanel.add(new JLabel("Nombre:"), gbc);
        gbc.gridx = 1;
        formPanel.add(txtNombre, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        formPanel.add(new JLabel("Provincia:"), gbc);
        gbc.gridx = 1;
        formPanel.add(cbProvincia, gbc);

        JPanel btnPanel = new JPanel();
        btnPanel.add(btnCrear);
        btnPanel.add(btnActualizar);
        btnPanel.add(btnEliminar);
        btnPanel.add(btnLimpiar);

        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 2;
        formPanel.add(btnPanel, gbc);

        JPanel searchPanel = new JPanel(new FlowLayout());
        searchPanel.setBorder(BorderFactory.createTitledBorder("Búsqueda"));
        searchPanel.add(new JLabel("Buscar:"));
        searchPanel.add(txtBuscar);
        searchPanel.add(btnBuscar);
        searchPanel.add(btnRefrescar);

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(formPanel, BorderLayout.WEST);
        topPanel.add(searchPanel, BorderLayout.EAST);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Cantones"));

        JPanel statsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        statsPanel.add(lblTotal);

        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(statsPanel, BorderLayout.SOUTH);
    }

    private void setupEvents() {
        btnCrear.addActionListener(e -> crearCanton());
        btnActualizar.addActionListener(e -> actualizarCanton());
        btnEliminar.addActionListener(e -> eliminarCanton());
        btnBuscar.addActionListener(e -> buscarCanton());
        btnRefrescar.addActionListener(e -> cargarDatos());
        btnLimpiar.addActionListener(e -> limpiarCampos());

        table.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int row = table.getSelectedRow();
                if (row != -1) {
                    txtId.setText(table.getValueAt(row, 0).toString());
                    txtNombre.setText(table.getValueAt(row, 1).toString());

                    // Buscar provincia seleccionada
                    String nombreProvincia = table.getValueAt(row, 2).toString();
                    for (int i = 0; i < cbProvincia.getItemCount(); i++) {
                        Provincia p = cbProvincia.getItemAt(i);
                        if (p.getNombre().equalsIgnoreCase(nombreProvincia)) {
                            cbProvincia.setSelectedIndex(i);
                            break;
                        }
                    }
                }
            }
        });
    }

    private void cargarProvincias() {
        cbProvincia.removeAllItems();
        List<Provincia> provincias = provinciaDAO.listarTodas();
        for (Provincia p : provincias) {
            cbProvincia.addItem(p);
        }
    }

    private void cargarDatos() {
        tableModel.setRowCount(0);
        List<Canton> cantones = cantonDAO.listarTodos();

        for (Canton c : cantones) {
            Provincia p = provinciaDAO.leer(c.getProvinciaId());
            String nombreProvincia = (p != null) ? p.getNombre() : "N/D";
            tableModel.addRow(new Object[]{c.getId(), c.getNombre(), nombreProvincia});
        }

        lblTotal.setText("Total: " + cantones.size());
    }

    private void crearCanton() {
        String nombre = txtNombre.getText().trim();
        Provincia provincia = (Provincia) cbProvincia.getSelectedItem();

        if (nombre.isEmpty() || provincia == null) {
            JOptionPane.showMessageDialog(this, "Nombre y provincia son requeridos.");
            return;
        }

        if (cantonDAO.existePorNombreYProvincia(nombre, provincia.getId())) {
            JOptionPane.showMessageDialog(this, "Ya existe un cantón con ese nombre en esa provincia.");
            return;
        }

        Canton canton = new Canton(nombre, provincia.getId());
        if (cantonDAO.crear(canton)) {
            JOptionPane.showMessageDialog(this, "Cantón creado correctamente.");
            limpiarCampos();
            cargarDatos();
        } else {
            JOptionPane.showMessageDialog(this, "Error al crear cantón.");
        }
    }

    private void actualizarCanton() {
        String idText = txtId.getText().trim();
        String nombre = txtNombre.getText().trim();
        Provincia provincia = (Provincia) cbProvincia.getSelectedItem();

        if (idText.isEmpty() || nombre.isEmpty() || provincia == null) {
            JOptionPane.showMessageDialog(this, "Completa todos los campos para actualizar.");
            return;
        }

        try {
            int id = Integer.parseInt(idText);
            Canton canton = new Canton(id, nombre, provincia.getId());

            if (cantonDAO.actualizar(canton)) {
                JOptionPane.showMessageDialog(this, "Cantón actualizado.");
                limpiarCampos();
                cargarDatos();
            } else {
                JOptionPane.showMessageDialog(this, "Error al actualizar cantón.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "ID inválido.");
        }
    }

    private void eliminarCanton() {
        String idText = txtId.getText().trim();
        if (idText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Selecciona un cantón para eliminar.");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this,
                "¿Eliminar el cantón: " + txtNombre.getText() + "?",
                "Confirmar", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            try {
                int id = Integer.parseInt(idText);
                if (cantonDAO.eliminar(id)) {
                    JOptionPane.showMessageDialog(this, "Cantón eliminado.");
                    limpiarCampos();
                    cargarDatos();
                } else {
                    JOptionPane.showMessageDialog(this, "Error al eliminar cantón.");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "ID inválido.");
            }
        }
    }

    private void buscarCanton() {
        String query = txtBuscar.getText().trim();
        if (query.isEmpty()) {
            cargarDatos();
            return;
        }

        try {
            int id = Integer.parseInt(query);
            Canton c = cantonDAO.leer(id);
            if (c != null) {
                mostrarEnTabla(c);
            } else {
                buscarPorNombre(query);
            }
        } catch (NumberFormatException e) {
            buscarPorNombre(query);
        }
    }

    private void buscarPorNombre(String nombre) {
        Canton c = cantonDAO.leerPorNombre(nombre);
        if (c != null) {
            mostrarEnTabla(c);
        } else {
            JOptionPane.showMessageDialog(this, "No se encontró el cantón.");
            cargarDatos();
        }
    }

    private void mostrarEnTabla(Canton c) {
        tableModel.setRowCount(0);
        Provincia p = provinciaDAO.leer(c.getProvinciaId());
        String nombreProvincia = (p != null) ? p.getNombre() : "N/D";
        tableModel.addRow(new Object[]{c.getId(), c.getNombre(), nombreProvincia});
        lblTotal.setText("Total: 1");
    }

    private void limpiarCampos() {
        txtId.setText("");
        txtNombre.setText("");
        txtBuscar.setText("");
        cbProvincia.setSelectedIndex(-1);
        table.clearSelection();
    }
}
