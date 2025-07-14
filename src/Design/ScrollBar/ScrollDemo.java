/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Design.ScrollBar;
import javax.swing.*;
import java.awt.*;

public class ScrollDemo {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Crea el área de texto
            JTextArea area = new JTextArea(
                "Este es un demo de la barra de scroll moderna.\n\n" +
                "La thumb es morada (#9B59B6) con bordes redondeados,\n" +
                "el track es muy claro (#F8FAFC),\n" +
                "y los botones de flecha desaparecen.",
                20, 40
            );
            area.setLineWrap(true);
            area.setWrapStyleWord(true);

            JScrollPane scroll = new JScrollPane(area);
            scroll.getVerticalScrollBar().setUI(new ModernScrollBarUI());
            scroll.getHorizontalScrollBar().setUI(new ModernScrollBarUI());
            scroll.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));

            JFrame frame = new JFrame("Demo ModernScrollBarUI");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().add(scroll);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
