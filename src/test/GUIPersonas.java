/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test;

import Modelo.Persona;
import Modelo.PersonaDAO;
import Modelo.Rol;
import Modelo.RolDAO;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

public class GUIPersonas extends JPanel {

    private PersonaDAO personaDAO;
    private RolDAO rolDAO;

    private DefaultTableModel modeloTabla;
    private JTable tabla;

    private JTextField txtCedula, txtUsuario, txtCorreo, txtNombres, txtApellidos, txtFechaNacimiento;
    private JPasswordField txtContrasenia;
    private JComboBox<String> comboProvincia, comboCanton, comboGenero, comboRol;
    private JTextArea txtSobreMi;

    private JButton btnCrear, btnActualizar, btnEliminar, btnLimpiar;

    public GUIPersonas() {
        personaDAO = new PersonaDAO();
        rolDAO = new RolDAO();

        initComponentes();
        cargarProvincias();      // Implementa este método para llenar comboProvincia
        cargarRoles();           // Implementa para llenar comboRol
        cargarGeneros();
        cargarPersonasEnTabla();

        configurarEventos();
    }

    private void initComponentes() {
        setLayout(new BorderLayout());

        // Tabla
        String[] columnas = {"Cédula", "Usuario", "Correo", "Provincia", "Cantón", "Género", "Nombres", "Apellidos", "Fecha Nac.", "Rol"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override public boolean isCellEditable(int row, int col) { return false; }
        };
        tabla = new JTable(modeloTabla);
        tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        add(new JScrollPane(tabla), BorderLayout.CENTER);

        // Formulario
        JPanel panelForm = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(3,3,3,3);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        int y = 0;

        // Cédula
        gbc.gridx = 0; gbc.gridy = y;
        panelForm.add(new JLabel("Cédula:"), gbc);
        txtCedula = new JTextField(15);
        gbc.gridx = 1;
        panelForm.add(txtCedula, gbc);

        // Usuario
        gbc.gridx = 2;
        panelForm.add(new JLabel("Usuario:"), gbc);
        txtUsuario = new JTextField(15);
        gbc.gridx = 3;
        panelForm.add(txtUsuario, gbc);

        y++;
        // Correo
        gbc.gridx = 0; gbc.gridy = y;
        panelForm.add(new JLabel("Correo:"), gbc);
        txtCorreo = new JTextField(15);
        gbc.gridx = 1;
        panelForm.add(txtCorreo, gbc);

        // Contraseña
        gbc.gridx = 2;
        panelForm.add(new JLabel("Contraseña:"), gbc);
        txtContrasenia = new JPasswordField(15);
        gbc.gridx = 3;
        panelForm.add(txtContrasenia, gbc);

        y++;
        // Provincia
        gbc.gridx = 0; gbc.gridy = y;
        panelForm.add(new JLabel("Provincia:"), gbc);
        comboProvincia = new JComboBox<>();
        gbc.gridx = 1;
        panelForm.add(comboProvincia, gbc);

        // Cantón
        gbc.gridx = 2;
        panelForm.add(new JLabel("Cantón:"), gbc);
        comboCanton = new JComboBox<>();
        gbc.gridx = 3;
        panelForm.add(comboCanton, gbc);

        y++;
        // Género
        gbc.gridx = 0; gbc.gridy = y;
        panelForm.add(new JLabel("Género:"), gbc);
        comboGenero = new JComboBox<>();
        gbc.gridx = 1;
        panelForm.add(comboGenero, gbc);

        // Rol
        gbc.gridx = 2;
        panelForm.add(new JLabel("Rol:"), gbc);
        comboRol = new JComboBox<>();
        gbc.gridx = 3;
        panelForm.add(comboRol, gbc);

        y++;
        // Nombres
        gbc.gridx = 0; gbc.gridy = y;
        panelForm.add(new JLabel("Nombres:"), gbc);
        txtNombres = new JTextField(15);
        gbc.gridx = 1;
        panelForm.add(txtNombres, gbc);

        // Apellidos
        gbc.gridx = 2;
        panelForm.add(new JLabel("Apellidos:"), gbc);
        txtApellidos = new JTextField(15);
        gbc.gridx = 3;
        panelForm.add(txtApellidos, gbc);

        y++;
        // Fecha de nacimiento
        gbc.gridx = 0; gbc.gridy = y;
        panelForm.add(new JLabel("Fecha Nac. (yyyy-MM-dd):"), gbc);
        txtFechaNacimiento = new JTextField(15);
        gbc.gridx = 1;
        panelForm.add(txtFechaNacimiento, gbc);

        // Sobre mí
        gbc.gridx = 2;
        panelForm.add(new JLabel("Sobre mí:"), gbc);
        txtSobreMi = new JTextArea(3, 15);
        txtSobreMi.setLineWrap(true);
        txtSobreMi.setWrapStyleWord(true);
        JScrollPane scrollSobreMi = new JScrollPane(txtSobreMi);
        gbc.gridx = 3;
        panelForm.add(scrollSobreMi, gbc);

        y++;

        // Botones
        JPanel panelBotones = new JPanel();
        btnCrear = new JButton("Crear");
        btnActualizar = new JButton("Actualizar");
        btnEliminar = new JButton("Eliminar");
        btnLimpiar = new JButton("Limpiar");

        panelBotones.add(btnCrear);
        panelBotones.add(btnActualizar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnLimpiar);

        gbc.gridx = 0; gbc.gridy = y; gbc.gridwidth = 4;
        panelForm.add(panelBotones, gbc);

        add(panelForm, BorderLayout.SOUTH);
    }

    private void cargarProvincias() {
        comboProvincia.removeAllItems();
        // Aquí deberías llamar a ProvinciaDAO para listar provincias y agregarlas
        // Ejemplo estático:
        comboProvincia.addItem("Azuay");
        comboProvincia.addItem("Pichincha");
        comboProvincia.addItem("Guayas");
        // Luego agrega el evento para cargar cantones al cambiar provincia
        comboProvincia.addActionListener(e -> cargarCantones());
    }

    private void cargarCantones() {
        comboCanton.removeAllItems();
        String provinciaSeleccionada = (String) comboProvincia.getSelectedItem();
        if (provinciaSeleccionada == null) return;
        // Aquí llamar a CantonDAO para obtener cantones según provincia
        // Ejemplo estático:
        if (provinciaSeleccionada.equals("Azuay")) {
            comboCanton.addItem("Cuenca");
            comboCanton.addItem("Gualaceo");
        } else if (provinciaSeleccionada.equals("Pichincha")) {
            comboCanton.addItem("Quito");
            comboCanton.addItem("Cayambe");
        } else if (provinciaSeleccionada.equals("Guayas")) {
            comboCanton.addItem("Guayaquil");
            comboCanton.addItem("Daule");
        }
    }

    private void cargarGeneros() {
        comboGenero.removeAllItems();
        comboGenero.addItem("Masculino");
        comboGenero.addItem("Femenino");
        comboGenero.addItem("Otro");
    }

   private void cargarRoles() {
    comboRol.removeAllItems();
    RolDAO rolDAO = new RolDAO();
    List<Rol> roles = rolDAO.listarRoles(); 
    for (Rol rol : roles) {
        comboRol.addItem(rol.getNombre());
    }
}

    private void cargarPersonasEnTabla() {
        modeloTabla.setRowCount(0);
        List<Persona> personas = personaDAO.listarTodas();
        for (Persona p : personas) {
            modeloTabla.addRow(new Object[]{
                    p.getCedula(),
                    p.getUsuario(),
                    p.getCorreo(),
                    p.getProvincia(),
                    p.getCanton(),
                    p.getGenero(),
                    p.getNombres(),
                    p.getApellidos(),
                    p.getFechaNacimiento() != null ? p.getFechaNacimiento().toString() : "",
                    p.getRol() != null ? p.getRol().getNombre() : ""
            });
        }
    }

    private void configurarEventos() {
        // Seleccionar fila en la tabla y llenar formulario
        tabla.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && tabla.getSelectedRow() != -1) {
                int fila = tabla.getSelectedRow();
                txtCedula.setText(modeloTabla.getValueAt(fila, 0).toString());
                txtUsuario.setText(modeloTabla.getValueAt(fila, 1).toString());
                txtCorreo.setText(modeloTabla.getValueAt(fila, 2).toString());
                comboProvincia.setSelectedItem(modeloTabla.getValueAt(fila, 3).toString());
                comboCanton.setSelectedItem(modeloTabla.getValueAt(fila, 4).toString());
                comboGenero.setSelectedItem(modeloTabla.getValueAt(fila, 5).toString());
                txtNombres.setText(modeloTabla.getValueAt(fila, 6).toString());
                txtApellidos.setText(modeloTabla.getValueAt(fila, 7).toString());
                txtFechaNacimiento.setText(modeloTabla.getValueAt(fila, 8).toString());
                comboRol.setSelectedItem(modeloTabla.getValueAt(fila, 9).toString());
                // No seteamos contraseña por seguridad
                txtContrasenia.setText("");
                txtSobreMi.setText(""); // Podrías cargarlo si implementas leerPersonaCompleta()
            }
        });

        btnCrear.addActionListener(e -> crearPersona());
        btnActualizar.addActionListener(e -> actualizarPersona());
        btnEliminar.addActionListener(e -> eliminarPersona());
        btnLimpiar.addActionListener(e -> limpiarCampos());
    }

    private void crearPersona() {
        try {
            Persona p = obtenerPersonaDesdeFormulario(true);
            if (p == null) return;

            if (personaDAO.insertar(p)) {
                JOptionPane.showMessageDialog(this, "Persona creada con éxito");
                limpiarCampos();
                cargarPersonasEnTabla();
            } else {
                JOptionPane.showMessageDialog(this, "Error al crear persona");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    private void actualizarPersona() {
        try {
            Persona p = obtenerPersonaDesdeFormulario(false);
            if (p == null) return;

            if (personaDAO.actualizar(p)) {
                JOptionPane.showMessageDialog(this, "Persona actualizada con éxito");
                limpiarCampos();
                cargarPersonasEnTabla();
            } else {
                JOptionPane.showMessageDialog(this, "Error al actualizar persona");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    private void eliminarPersona() {
        String cedula = txtCedula.getText().trim();
        if (cedula.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Seleccione una persona para eliminar");
            return;
        }
        int confirm = JOptionPane.showConfirmDialog(this, "¿Está seguro de eliminar la persona con cédula: " + cedula + "?");
        if (confirm != JOptionPane.YES_OPTION) return;

        if (personaDAO.eliminar(cedula)) {
            JOptionPane.showMessageDialog(this, "Persona eliminada con éxito");
            limpiarCampos();
            cargarPersonasEnTabla();
        } else {
            JOptionPane.showMessageDialog(this, "Error al eliminar persona");
        }
    }

    private Persona obtenerPersonaDesdeFormulario(boolean esNuevo) {
        String cedula = txtCedula.getText().trim();
        String usuario = txtUsuario.getText().trim();
        String correo = txtCorreo.getText().trim();
        String contrasenia = new String(txtContrasenia.getPassword());
        String provincia = (String) comboProvincia.getSelectedItem();
        String canton = (String) comboCanton.getSelectedItem();
        String genero = (String) comboGenero.getSelectedItem();
        String nombres = txtNombres.getText().trim();
        String apellidos = txtApellidos.getText().trim();
        String fechaNacStr = txtFechaNacimiento.getText().trim();
        String sobreMi = txtSobreMi.getText();
        String rolNombre = (String) comboRol.getSelectedItem();

        // Validaciones simples:
        if (cedula.isEmpty() || usuario.isEmpty() || correo.isEmpty() || provincia == null || canton == null || genero == null || rolNombre == null) {
            JOptionPane.showMessageDialog(this, "Complete todos los campos obligatorios");
            return null;
        }
        if (cedula.length() != 10) {
            JOptionPane.showMessageDialog(this, "Cédula debe tener 10 caracteres");
            return null;
        }
        // Validar correo básico (regex simple)
        if (!correo.matches("^\\S+@\\S+\\.\\S+$")) {
            JOptionPane.showMessageDialog(this, "Correo inválido");
            return null;
        }
        // Validar que contraseña no esté vacía sólo al crear
        if (esNuevo && contrasenia.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese contraseña");
            return null;
        }

        LocalDate fechaNacimiento = null;
        if (!fechaNacStr.isEmpty()) {
            try {
                fechaNacimiento = LocalDate.parse(fechaNacStr);
            } catch (DateTimeParseException e) {
                JOptionPane.showMessageDialog(this, "Fecha de nacimiento inválida (usar yyyy-MM-dd)");
                return null;
            }
        }

        Persona p = new Persona();
        p.setCedula(cedula);
        p.setUsuario(usuario);
        p.setCorreo(correo);
        // Aquí deberías encriptar la contraseña (usar BCrypt)
        // Por ejemplo: p.setContraseña(BCrypt.hashpw(contrasenia, BCrypt.gensalt()));
        p.setContraseña(contrasenia); // Cambia esto a hash para producción

        p.setProvincia(provincia);
        p.setCanton(canton);
        p.setGenero(genero);
        p.setNombres(nombres);
        p.setApellidos(apellidos);
        p.setFechaNacimiento(fechaNacimiento);
        p.setSobreMi(sobreMi);

        int rolId = rolDAO.obtenerIdRolPorNombre(rolNombre);
        Rol rol = new Rol(rolId, rolNombre);
        p.setRol(rol);

        return p;
    }

    private void limpiarCampos() {
        txtCedula.setText("");
        txtUsuario.setText("");
        txtCorreo.setText("");
        txtContrasenia.setText("");
        comboProvincia.setSelectedIndex(-1);
        comboCanton.removeAllItems();
        comboGenero.setSelectedIndex(-1);
        comboRol.setSelectedIndex(-1);
        txtNombres.setText("");
        txtApellidos.setText("");
        txtFechaNacimiento.setText("");
        txtSobreMi.setText("");
        tabla.clearSelection();
    }

    // Método para probar independiente:
    public static void main(String[] args) {
        JFrame frame = new JFrame("Gestión de Personas");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 600);
        frame.setLocationRelativeTo(null);
        frame.add(new GUIPersonas());
        frame.setVisible(true);
    }
}
