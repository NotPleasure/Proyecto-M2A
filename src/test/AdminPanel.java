/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import test.GUIProvincias;
/**
 *
 * @author USER
 */
public class AdminPanel extends JFrame {
    public AdminPanel() {
        // Esta es la ventana principal
        setTitle("Administración");
        setSize(900, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Creamos un objeto con pestañas
        JTabbedPane tabs = new JTabbedPane();

        // Agregamos tus paneles a las pestañas
        tabs.addTab("Provincias", new ProvinciasPanel());
        tabs.addTab("Cantones", new CantonesPanel());

        // Añadimos las pestañas a la ventana
        add(tabs);
    }

    public static void main(String[] args) {
        // Para mostrar la ventana al correr el programa
        javax.swing.SwingUtilities.invokeLater(() -> {
            new AdminPanel().setVisible(true);
        });
    }
}
