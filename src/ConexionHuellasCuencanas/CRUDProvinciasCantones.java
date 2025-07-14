/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ConexionHuellasCuencanas;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.sql.*;
import java.util.*;

// MODELOS
class Provincia {
    private int id;
    private String nombre;
    public Provincia() {}
    public Provincia(int id, String nombre) {
        this.id = id; this.nombre = nombre;
    }
    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public String toString() { return nombre; }
}

class Canton {
    private int id;
    private String nombre;
    private Provincia provincia;
    public Canton() {}
    public Canton(int id, String nombre, Provincia provincia) {
        this.id = id; this.nombre = nombre; this.provincia = provincia;
    }
    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public Provincia getProvincia() { return provincia; }
    public String toString() { return nombre; }
}

// CONEXIÓN
class Conexion {
    public static Connection conectar() throws SQLException {
        // Cambia estos datos según tu configuración:
        String url = "jdbc:postgresql://localhost:5432/HuellasCuencanas";
        String user = "postgres";
        String pass = "a";
        return DriverManager.getConnection(url, user, pass);
    }
}

// DAO Provincias
class ProvinciaDAO {
    public List<Provincia> listar() {
        List<Provincia> lista = new ArrayList<>();
        String sql = "SELECT * FROM provincias ORDER BY nombre";
        try (Connection con = Conexion.conectar();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new Provincia(rs.getInt("id"), rs.getString("nombre")));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return lista;
    }
    public boolean insertar(String nombre) {
        String sql = "INSERT INTO provincias(nombre) VALUES(?)";
        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, nombre);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); return false; }
    }
    public boolean actualizar(int id, String nombre) {
        String sql = "UPDATE provincias SET nombre=? WHERE id=?";
        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, nombre);
            ps.setInt(2, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); return false; }
    }
    public boolean eliminar(int id) {
        String sql = "DELETE FROM provincias WHERE id=?";
        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); return false; }
    }
}

// DAO Cantones
class CantonDAO {
    public List<Canton> listarPorProvincia(int provinciaId) {
        List<Canton> lista = new ArrayList<>();
        String sql = "SELECT c.id, c.nombre, p.id as pid, p.nombre as pnombre " +
                     "FROM cantones c JOIN provincias p ON c.provincia_id = p.id " +
                     "WHERE c.provincia_id = ? ORDER BY c.nombre";
        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, provinciaId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Provincia p = new Provincia(rs.getInt("pid"), rs.getString("pnombre"));
                lista.add(new Canton(rs.getInt("id"), rs.getString("nombre"), p));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return lista;
    }
    public boolean insertar(String nombre, int provinciaId) {
        String sql = "INSERT INTO cantones(nombre, provincia_id) VALUES(?, ?)";
        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, nombre);
            ps.setInt(2, provinciaId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); return false; }
    }
    public boolean actualizar(int id, String nombre, int provinciaId) {
        String sql = "UPDATE cantones SET nombre=?, provincia_id=? WHERE id=?";
        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, nombre);
            ps.setInt(2, provinciaId);
            ps.setInt(3, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); return false; }
    }
    public boolean eliminar(int id) {
        String sql = "DELETE FROM cantones WHERE id=?";
        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); return false; }
    }
}

// INTERFAZ PRINCIPAL
public class CRUDProvinciasCantones extends JFrame {
    private JTable tablaProvincias, tablaCantones;
    private DefaultTableModel modeloProv, modeloCant;
    private JTextField txtProvNombre, txtCantNombre;
    private JButton btnProvGuardar, btnProvActualizar, btnProvEliminar;
    private JButton btnCantGuardar, btnCantActualizar, btnCantEliminar;
    private ProvinciaDAO provinciaDAO = new ProvinciaDAO();
    private CantonDAO cantonDAO = new CantonDAO();
    private int idProvSeleccionada = -1;
    private int idCantSeleccionado = -1;

    public CRUDProvinciasCantones() {
        setTitle("CRUD Provincias y Cantones");
        setSize(800, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        // Panel provincias
        JLabel lblProv = new JLabel("Provincias:");
        lblProv.setBounds(20, 10, 100, 25);
        add(lblProv);

        modeloProv = new DefaultTableModel(new String[]{"ID", "Nombre"}, 0);
        tablaProvincias = new JTable(modeloProv);
        JScrollPane scrollProv = new JScrollPane(tablaProvincias);
        scrollProv.setBounds(20, 40, 350, 200);
        add(scrollProv);

        txtProvNombre = new JTextField();
        txtProvNombre.setBounds(20, 250, 200, 25);
        add(txtProvNombre);

        btnProvGuardar = new JButton("Guardar");
        btnProvGuardar.setBounds(230, 250, 140, 25);
        add(btnProvGuardar);

        btnProvActualizar = new JButton("Actualizar");
        btnProvActualizar.setBounds(20, 280, 175, 25);
        add(btnProvActualizar);

        btnProvEliminar = new JButton("Eliminar");
        btnProvEliminar.setBounds(195, 280, 175, 25);
        add(btnProvEliminar);

        // Panel cantones
        JLabel lblCant = new JLabel("Cantones:");
        lblCant.setBounds(400, 10, 100, 25);
        add(lblCant);

        modeloCant = new DefaultTableModel(new String[]{"ID", "Nombre", "Provincia"}, 0);
        tablaCantones = new JTable(modeloCant);
        JScrollPane scrollCant = new JScrollPane(tablaCantones);
        scrollCant.setBounds(400, 40, 350, 200);
        add(scrollCant);

        txtCantNombre = new JTextField();
        txtCantNombre.setBounds(400, 250, 200, 25);
        add(txtCantNombre);

        btnCantGuardar = new JButton("Guardar");
        btnCantGuardar.setBounds(610, 250, 140, 25);
        add(btnCantGuardar);

        btnCantActualizar = new JButton("Actualizar");
        btnCantActualizar.setBounds(400, 280, 175, 25);
        add(btnCantActualizar);

        btnCantEliminar = new JButton("Eliminar");
        btnCantEliminar.setBounds(575, 280, 175, 25);
        add(btnCantEliminar);

        cargarProvincias();

        // Eventos Provincias
        btnProvGuardar.addActionListener(e -> {
            String nombre = txtProvNombre.getText().trim();
            if (!nombre.isEmpty()) {
                if (provinciaDAO.insertar(nombre)) {
                    JOptionPane.showMessageDialog(this, "Provincia insertada");
                    txtProvNombre.setText("");
                    cargarProvincias();
                } else {
                    JOptionPane.showMessageDialog(this, "Error al insertar");
                }
            }
        });

        btnProvActualizar.addActionListener(e -> {
            if (idProvSeleccionada != -1) {
                String nombre = txtProvNombre.getText().trim();
                if (!nombre.isEmpty()) {
                    if (provinciaDAO.actualizar(idProvSeleccionada, nombre)) {
                        JOptionPane.showMessageDialog(this, "Provincia actualizada");
                        txtProvNombre.setText("");
                        idProvSeleccionada = -1;
                        cargarProvincias();
                        limpiarCantones();
                    } else {
                        JOptionPane.showMessageDialog(this, "Error al actualizar");
                    }
                }
            }
        });

        btnProvEliminar.addActionListener(e -> {
            if (idProvSeleccionada != -1) {
                int confirm = JOptionPane.showConfirmDialog(this, "Eliminar provincia? Se borrarán también sus cantones.", "Confirmar", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    if (provinciaDAO.eliminar(idProvSeleccionada)) {
                        JOptionPane.showMessageDialog(this, "Provincia eliminada");
                        txtProvNombre.setText("");
                        idProvSeleccionada = -1;
                        cargarProvincias();
                        limpiarCantones();
                    } else {
                        JOptionPane.showMessageDialog(this, "Error al eliminar");
                    }
                }
            }
        });

        tablaProvincias.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int fila = tablaProvincias.getSelectedRow();
                if (fila != -1) {
                    idProvSeleccionada = Integer.parseInt(modeloProv.getValueAt(fila, 0).toString());
                    String nombre = modeloProv.getValueAt(fila, 1).toString();
                    txtProvNombre.setText(nombre);
                    cargarCantones(idProvSeleccionada);
                }
            }
        });

        // Eventos Cantones
        btnCantGuardar.addActionListener(e -> {
            String nombre = txtCantNombre.getText().trim();
            if (!nombre.isEmpty() && idProvSeleccionada != -1) {
                if (cantonDAO.insertar(nombre, idProvSeleccionada)) {
                    JOptionPane.showMessageDialog(this, "Cantón insertado");
                    txtCantNombre.setText("");
                    cargarCantones(idProvSeleccionada);
                } else {
                    JOptionPane.showMessageDialog(this, "Error al insertar cantón");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Seleccione una provincia y escriba un nombre para el cantón");
            }
        });

        btnCantActualizar.addActionListener(e -> {
            if (idCantSeleccionado != -1 && idProvSeleccionada != -1) {
                String nombre = txtCantNombre.getText().trim();
                if (!nombre.isEmpty()) {
                    if (cantonDAO.actualizar(idCantSeleccionado, nombre, idProvSeleccionada)) {
                        JOptionPane.showMessageDialog(this, "Cantón actualizado");
                        txtCantNombre.setText("");
                        idCantSeleccionado = -1;
                        cargarCantones(idProvSeleccionada);
                    } else {
                        JOptionPane.showMessageDialog(this, "Error al actualizar cantón");
                    }
                }
            }
        });

        btnCantEliminar.addActionListener(e -> {
            if (idCantSeleccionado != -1) {
                int confirm = JOptionPane.showConfirmDialog(this, "Eliminar cantón?", "Confirmar", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    if (cantonDAO.eliminar(idCantSeleccionado)) {
                        JOptionPane.showMessageDialog(this, "Cantón eliminado");
                        txtCantNombre.setText("");
                        idCantSeleccionado = -1;
                        cargarCantones(idProvSeleccionada);
                    } else {
                        JOptionPane.showMessageDialog(this, "Error al eliminar cantón");
                    }
                }
            }
        });

        tablaCantones.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int fila = tablaCantones.getSelectedRow();
                if (fila != -1) {
                    idCantSeleccionado = Integer.parseInt(modeloCant.getValueAt(fila, 0).toString());
                    String nombre = modeloCant.getValueAt(fila, 1).toString();
                    txtCantNombre.setText(nombre);
                }
            }
        });
    }

    private void cargarProvincias() {
        modeloProv.setRowCount(0);
        List<Provincia> lista = provinciaDAO.listar();
        for (Provincia p : lista) {
            modeloProv.addRow(new Object[]{p.getId(), p.getNombre()});
        }
    }

    private void cargarCantones(int provinciaId) {
        modeloCant.setRowCount(0);
        List<Canton> lista = cantonDAO.listarPorProvincia(provinciaId);
        for (Canton c : lista) {
            modeloCant.addRow(new Object[]{c.getId(), c.getNombre(), c.getProvincia().getNombre()});
        }
    }

    private void limpiarCantones() {
        modeloCant.setRowCount(0);
        txtCantNombre.setText("");
        idCantSeleccionado = -1;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CRUDProvinciasCantones().setVisible(true));
    }
}

