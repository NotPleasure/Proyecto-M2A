// GUIProvincias.java
package test;

import dao.ProvinciaDAO;
import model.Provincia;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class GUIProvincias extends JFrame {
    private ProvinciaDAO provinciaDAO;
    private DefaultTableModel tableModel;
    private JTable table;
    private JTextField txtNombre;
    private JTextField txtId;
    private JTextField txtBuscar;
    private JButton btnCrear, btnActualizar, btnEliminar, btnBuscar, btnLimpiar;
    private JLabel lblEstadisticas;
    
    public GUIProvincias() {
        provinciaDAO = new ProvinciaDAO();
        initializeComponents();
        setupLayout();
        setupEventListeners();
        cargarDatos();
        actualizarEstadisticas();
    }
    
    private void initializeComponents() {
        setTitle("Sistema de Gestión de Provincias - HuellasCuencanas");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        
        // Componentes del formulario
        txtNombre = new JTextField(20);
        txtId = new JTextField(10);
        txtId.setEditable(false);
        txtBuscar = new JTextField(20);
        
        // Botones
        btnCrear = new JButton("Crear");
        btnActualizar = new JButton("Actualizar");
        btnEliminar = new JButton("Eliminar");
        btnBuscar = new JButton("Buscar");
        btnLimpiar = new JButton("Limpiar");
        
        // Configurar colores de botones
        btnCrear.setBackground(new Color(76, 175, 80));
        btnCrear.setForeground(Color.WHITE);
        btnActualizar.setBackground(new Color(33, 150, 243));
        btnActualizar.setForeground(Color.WHITE);
        btnEliminar.setBackground(new Color(244, 67, 54));
        btnEliminar.setForeground(Color.WHITE);
        btnBuscar.setBackground(new Color(255, 152, 0));
        btnBuscar.setForeground(Color.WHITE);
        
        // Tabla
        String[] columnNames = {"ID", "Nombre"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table = new JTable(tableModel);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.getTableHeader().setBackground(new Color(63, 81, 181));
        table.getTableHeader().setForeground(Color.WHITE);
        table.setRowHeight(25);
        
        // Label de estadísticas
        lblEstadisticas = new JLabel("Total de provincias: 0");
        lblEstadisticas.setFont(new Font("Arial", Font.BOLD, 12));
    }
    
    private void setupLayout() {
        setLayout(new BorderLayout());
        
        // Panel principal
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Panel superior - Formulario
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createTitledBorder("Datos de la Provincia"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        
        // ID
        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(new JLabel("ID:"), gbc);
        gbc.gridx = 1;
        formPanel.add(txtId, gbc);
        
        // Nombre
        gbc.gridx = 0; gbc.gridy = 1;
        formPanel.add(new JLabel("Nombre:"), gbc);
        gbc.gridx = 1;
        formPanel.add(txtNombre, gbc);
        
        // Panel de botones de acción
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(btnCrear);
        buttonPanel.add(btnActualizar);
        buttonPanel.add(btnEliminar);
        buttonPanel.add(btnLimpiar);
        
        gbc.gridx = 0; gbc.gridy = 2;
        gbc.gridwidth = 2;
        formPanel.add(buttonPanel, gbc);
        
        // Panel de búsqueda
        JPanel searchPanel = new JPanel(new FlowLayout());
        searchPanel.setBorder(BorderFactory.createTitledBorder("Búsqueda"));
        searchPanel.add(new JLabel("Buscar por nombre:"));
        searchPanel.add(txtBuscar);
        searchPanel.add(btnBuscar);
        
        // Panel superior completo
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(formPanel, BorderLayout.CENTER);
        topPanel.add(searchPanel, BorderLayout.SOUTH);
        
        // Panel central - Tabla
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Lista de Provincias"));
        
        // Panel inferior - Estadísticas
        JPanel statsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        statsPanel.add(lblEstadisticas);
        
        // Agregar todo al panel principal
        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(statsPanel, BorderLayout.SOUTH);
        
        add(mainPanel);
    }
    
    private void setupEventListeners() {
        // Botón Crear
        btnCrear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                crearProvincia();
            }
        });
        
        // Botón Actualizar
        btnActualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarProvincia();
            }
        });
        
        // Botón Eliminar
        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarProvincia();
            }
        });
        
        // Botón Buscar
        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarProvincia();
            }
        });
        
        // Botón Limpiar
        btnLimpiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiarCampos();
            }
        });
        
        // Selección en la tabla
        table.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    txtId.setText(table.getValueAt(selectedRow, 0).toString());
                    txtNombre.setText(table.getValueAt(selectedRow, 1).toString());
                }
            }
        });
        
        // Enter en campo de búsqueda
        txtBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarProvincia();
            }
        });
    }
    
    private void crearProvincia() {
        String nombre = txtNombre.getText().trim();
        
        if (nombre.isEmpty()) {
            JOptionPane.showMessageDialog(this, "El nombre no puede estar vacío", 
                "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Verificar si ya existe
        if (provinciaDAO.existePorNombre(nombre)) {
            JOptionPane.showMessageDialog(this, "Ya existe una provincia con ese nombre", 
                "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        Provincia provincia = new Provincia(nombre);
        
        if (provinciaDAO.crear(provincia)) {
            JOptionPane.showMessageDialog(this, "Provincia creada exitosamente con ID: " + provincia.getId(), 
                "Éxito", JOptionPane.INFORMATION_MESSAGE);
            limpiarCampos();
            cargarDatos();
            actualizarEstadisticas();
        } else {
            JOptionPane.showMessageDialog(this, "Error al crear provincia", 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void actualizarProvincia() {
        String idText = txtId.getText().trim();
        String nombre = txtNombre.getText().trim();
        
        if (idText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Seleccione una provincia de la tabla", 
                "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if (nombre.isEmpty()) {
            JOptionPane.showMessageDialog(this, "El nombre no puede estar vacío", 
                "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        try {
            int id = Integer.parseInt(idText);
            
            // Verificar si el nuevo nombre ya existe en otra provincia
            Provincia existente = provinciaDAO.leerPorNombre(nombre);
            if (existente != null && existente.getId() != id) {
                JOptionPane.showMessageDialog(this, "Ya existe otra provincia con ese nombre", 
                    "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            Provincia provincia = new Provincia(id, nombre);
            
            if (provinciaDAO.actualizar(provincia)) {
                JOptionPane.showMessageDialog(this, "Provincia actualizada exitosamente", 
                    "Éxito", JOptionPane.INFORMATION_MESSAGE);
                limpiarCampos();
                cargarDatos();
                actualizarEstadisticas();
            } else {
                JOptionPane.showMessageDialog(this, "Error al actualizar provincia", 
                    "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "ID inválido", 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void eliminarProvincia() {
        String idText = txtId.getText().trim();
        
        if (idText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Seleccione una provincia de la tabla", 
                "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        try {
            int id = Integer.parseInt(idText);
            String nombre = txtNombre.getText();
            
            int confirmacion = JOptionPane.showConfirmDialog(this, 
                "¿Está seguro de eliminar la provincia: " + nombre + "?", 
                "Confirmar eliminación", 
                JOptionPane.YES_NO_OPTION);
            
            if (confirmacion == JOptionPane.YES_OPTION) {
                if (provinciaDAO.eliminar(id)) {
                    JOptionPane.showMessageDialog(this, "Provincia eliminada exitosamente", 
                        "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    limpiarCampos();
                    cargarDatos();
                    actualizarEstadisticas();
                } else {
                    JOptionPane.showMessageDialog(this, "Error al eliminar provincia (puede tener registros dependientes)", 
                        "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "ID inválido", 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void buscarProvincia() {
        String textoBusqueda = txtBuscar.getText().trim();
        
        if (textoBusqueda.isEmpty()) {
            cargarDatos();
            return;
        }
        
        // Intentar buscar por ID primero
        try {
            int id = Integer.parseInt(textoBusqueda);
            Provincia provincia = provinciaDAO.leer(id);
            
            if (provincia != null) {
                mostrarProvinciaEnTabla(provincia);
            } else {
                buscarPorNombre(textoBusqueda);
            }
        } catch (NumberFormatException e) {
            // Si no es un número, buscar por nombre
            buscarPorNombre(textoBusqueda);
        }
    }
    
    private void buscarPorNombre(String nombre) {
        Provincia provincia = provinciaDAO.leerPorNombre(nombre);
        
        if (provincia != null) {
            mostrarProvinciaEnTabla(provincia);
        } else {
            JOptionPane.showMessageDialog(this, "Provincia no encontrada", 
                "Información", JOptionPane.INFORMATION_MESSAGE);
            cargarDatos();
        }
    }
    
    private void mostrarProvinciaEnTabla(Provincia provincia) {
        tableModel.setRowCount(0);
        Object[] row = {provincia.getId(), provincia.getNombre()};
        tableModel.addRow(row);
    }
    
    private void limpiarCampos() {
        txtId.setText("");
        txtNombre.setText("");
        txtBuscar.setText("");
        table.clearSelection();
    }
    
    private void cargarDatos() {
        tableModel.setRowCount(0);
        List<Provincia> provincias = provinciaDAO.listarTodas();
        
        for (Provincia provincia : provincias) {
            Object[] row = {provincia.getId(), provincia.getNombre()};
            tableModel.addRow(row);
        }
    }
    
    private void actualizarEstadisticas() {
        int total = provinciaDAO.contarProvincias();
        lblEstadisticas.setText("Total de provincias: " + total);
    }
    
    public static void main(String[] args) {
        // Configurar Look and Feel
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            // Si falla, usar el look and feel por defecto
            System.out.println("No se pudo cargar el Look and Feel, usando el predeterminado");
        }
        
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GUIProvincias().setVisible(true);
            }
        });
    }
}