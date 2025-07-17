/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Design;

/**
 *
 * @author USER
 */
import java.awt.*;
import javax.swing.*;

public class RoundedPannelBuscar extends JPanel {
     private int cornerRadius = 25;
    private Color backgroundColor = new Color(125, 50, 6);   // #7d3206
    private Color borderColor = new Color(203, 93, 27);      // #cb5d1b
    private int borderThickness = 2;

    public RoundedPannelBuscar() {
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Fondo
        g2.setColor(backgroundColor);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), cornerRadius, cornerRadius);

        // Borde
        g2.setStroke(new BasicStroke(borderThickness));
        g2.setColor(borderColor);
        g2.drawRoundRect(borderThickness / 2, borderThickness / 2,
                         getWidth() - borderThickness, getHeight() - borderThickness,
                         cornerRadius, cornerRadius);

        super.paintComponent(g);
        g2.dispose();
    }
}