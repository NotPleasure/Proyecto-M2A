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

// MODELO
class Genero {

    private int id;
    private String nombre;

    public Genero() {
    }

    public Genero(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}

// DAO
class GeneroDAO {

    public List<Genero> listarGeneros() {
        List<Genero> lista = new ArrayList<>();
        String sql = "SELECT * FROM generos ORDER BY id";

        try ( Connection con = Conexion.conectar();  Statement st = con.createStatement();  ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Genero g = new Genero(rs.getInt("id"), rs.getString("nombre"));
                lista.add(g);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    public boolean insertarGenero(String nombre) {
        String sql = "INSERT INTO generos(nombre) VALUES(?)";

        try ( Connection con = Conexion.conectar();  PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, nombre);
            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean actualizarGenero(int id, String nuevoNombre) {
        String sql = "UPDATE generos SET nombre=? WHERE id=?";

        try ( Connection con = Conexion.conectar();  PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, nuevoNombre);
            ps.setInt(2, id);
            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean eliminarGenero(int id) {
        String sql = "DELETE FROM generos WHERE id=?";

        try ( Connection con = Conexion.conectar();  PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}

// INTERFAZ
public class InterfazGenero extends JFrame {

    private JTextField txtGenero;
    private JTable tabla;
    private DefaultTableModel modelo;
    private JButton btnGuardar, btnActualizar, btnEliminar;
    private int idSeleccionado = -1;

    public InterfazGenero() {
        setTitle("CRUD de Géneros");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        JLabel lbl = new JLabel("Género:");
        lbl.setBounds(20, 20, 60, 25);
        add(lbl);

        txtGenero = new JTextField();
        txtGenero.setBounds(90, 20, 150, 25);
        add(txtGenero);

        btnGuardar = new JButton("Guardar");
        btnGuardar.setBounds(250, 20, 100, 25);
        add(btnGuardar);

        modelo = new DefaultTableModel(new String[]{"ID", "Nombre"}, 0);
        tabla = new JTable(modelo);
        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setBounds(20, 60, 330, 120);
        add(scroll);

        btnActualizar = new JButton("Actualizar");
        btnActualizar.setBounds(20, 200, 100, 25);
        add(btnActualizar);

        btnEliminar = new JButton("Eliminar");
        btnEliminar.setBounds(140, 200, 100, 25);
        add(btnEliminar);

        cargarTabla();

        btnGuardar.addActionListener(e -> {
            String nombre = txtGenero.getText().trim();
            if (!nombre.isEmpty()) {
                if (new GeneroDAO().insertarGenero(nombre)) {
                    JOptionPane.showMessageDialog(this, "Insertado correctamente");
                    cargarTabla();
                    txtGenero.setText("");
                }
            }
        });

        tabla.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int fila = tabla.getSelectedRow();
                idSeleccionado = Integer.parseInt(modelo.getValueAt(fila, 0).toString());
                txtGenero.setText(modelo.getValueAt(fila, 1).toString());
            }
        });

        btnActualizar.addActionListener(e -> {
            if (idSeleccionado != -1) {
                String nuevo = txtGenero.getText().trim();
                if (!nuevo.isEmpty()) {
                    if (new GeneroDAO().actualizarGenero(idSeleccionado, nuevo)) {
                        JOptionPane.showMessageDialog(this, "Actualizado correctamente");
                        cargarTabla();
                        txtGenero.setText("");
                        idSeleccionado = -1;
                    }
                }
            }
        });

        btnEliminar.addActionListener(e -> {
            if (idSeleccionado != -1) {
                if (new GeneroDAO().eliminarGenero(idSeleccionado)) {
                    JOptionPane.showMessageDialog(this, "Eliminado correctamente");
                    cargarTabla();
                    txtGenero.setText("");
                    idSeleccionado = -1;
                }
            }
        });
    }

    public void cargarTabla() {
        modelo.setRowCount(0);
        for (Genero g : new GeneroDAO().listarGeneros()) {
            modelo.addRow(new Object[]{g.getId(), g.getNombre()});
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new InterfazGenero().setVisible(true));
    }
}
