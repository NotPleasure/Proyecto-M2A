/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Design;

import java.awt.*;
import java.awt.geom.Path2D;
import javax.swing.*;

public class RoundedPanelStatsFavoritosDebajo extends JPanel {

    private int cornerRadius = 15;
    private Color backgroundColor = new Color(255, 255, 255);

    public RoundedPanelStatsFavoritosDebajo() {
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int w = getWidth();
        int h = getHeight();
        int r = cornerRadius;

        Path2D.Double path = new Path2D.Double();
        path.moveTo(0, 0);
        path.lineTo(0, h - r);
        path.quadTo(0, h, r, h);
        path.lineTo(w - r, h);
        path.quadTo(w, h, w, h - r);
        path.lineTo(w, 0);
        path.closePath();

        g2.setColor(backgroundColor);
        g2.fill(path);

        super.paintComponent(g);
        g2.dispose();
    }
}
