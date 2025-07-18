/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Design;

import java.awt.*;
import java.awt.geom.Path2D;
import javax.swing.*;

public class RoundedPanelStats3 extends JPanel {

    private int cornerRadius = 15;
    private Color backgroundColor = new Color(33, 156, 243);

    public RoundedPanelStats3() {
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
        path.moveTo(0, h);
        path.lineTo(0, r);
        path.quadTo(0, 0, r, 0);
        path.lineTo(w - r, 0);
        path.quadTo(w, 0, w, r);
        path.lineTo(w, h);
        path.closePath();

        g2.setColor(backgroundColor);
        g2.fill(path);

        super.paintComponent(g);
        g2.dispose();
    }
}
