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

public class RoundedPanel extends JPanel {

    private int cornerRadius = 60;
    private Color backgroundColor = new Color(35, 35, 35, 200);

    public RoundedPanel() {
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setComposite(AlphaComposite.Clear);
        g2.fillRect(0, 0, getWidth(), getHeight());

        g2.setComposite(AlphaComposite.SrcOver);

        g2.setColor(new Color(255, 255, 255, 180));
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), cornerRadius, cornerRadius);

        g2.dispose();
    }
}
