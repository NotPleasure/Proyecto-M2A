/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Design;

import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import javax.swing.border.AbstractBorder;
/**
 *
 * @author USER
 */


public class RoundedBorder extends AbstractBorder {
    private final Color color;
    private final int thickness;
    private final int arcWidth;
    private final int arcHeight;

    public RoundedBorder(Color color, int thickness, int arcWidth, int arcHeight) {
        this.color = color;
        this.thickness = thickness;
        this.arcWidth = arcWidth;
        this.arcHeight = arcHeight;
    }

    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setColor(color);
        g2.setStroke(new BasicStroke(thickness));
        g2.drawRoundRect(x + thickness/2, y + thickness/2, width - thickness, height - thickness, arcWidth, arcHeight);
        g2.dispose();
    }
}