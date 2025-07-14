/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Design;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class RoundedPanelLugares extends JPanel {

 public RoundedPanelLugares() {
        setOpaque(false);
        setPreferredSize(new Dimension(220, 280));
        setBorder(new EmptyBorder(15, 15, 15, 15));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int arc = 30;
        int x = 5;
        int y = 5;
        int width = getWidth() - 10;
        int height = getHeight() - 10;

        // 1. Dibujar sombra
        g2.setColor(new Color(0, 0, 0, 30)); // Sombra negra translúcida
        g2.fillRoundRect(x, y, width, height, arc, arc);

        // 2. Dibujar borde superior morado "externo"
        g2.setColor(new Color(142, 36, 170)); // Morado
        g2.fillRoundRect(x, y - 8, width, height, arc, arc);  // más arriba para que sobresalga

        // 3. Dibujar el panel blanco encima (cubre parte del morado, dejando solo la parte superior)
        g2.setColor(Color.WHITE);
        g2.fillRoundRect(x, y, width, height, arc, arc);

        g2.dispose();
    }
}