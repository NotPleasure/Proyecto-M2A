/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Design.EstrellaClick;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Path2D;
import javax.swing.*;

public class EstrellaClik extends JPanel {
    private boolean isFilled = false;

    public EstrellaClik() {
        setPreferredSize(new Dimension(300, 300));
        setBackground(Color.WHITE);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                isFilled = !isFilled;
                repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int x = getWidth() / 2;
        int y = getHeight() / 2;
        int radius = 80;

        Shape estrella = crearEstrella(x, y, radius, radius / 2, 5);

        g2.setColor(isFilled ? Color.ORANGE : Color.LIGHT_GRAY);
        g2.fill(estrella);

        g2.setColor(Color.DARK_GRAY);
        g2.setStroke(new BasicStroke(3));
        g2.draw(estrella);
    }

    private Shape crearEstrella(int centerX, int centerY, int outerRadius, int innerRadius, int numRays) {
        Path2D path = new Path2D.Double();
        double angle = Math.PI / numRays;

        for (int i = 0; i < numRays * 2; i++) {
            double r = (i % 2 == 0) ? outerRadius : innerRadius;
            double a = i * angle;
            double sx = centerX + Math.cos(a) * r;
            double sy = centerY + Math.sin(a) * r;
            if (i == 0) {
                path.moveTo(sx, sy);
            } else {
                path.lineTo(sx, sy);
            }
        }
        path.closePath();
        return path;
    }
}