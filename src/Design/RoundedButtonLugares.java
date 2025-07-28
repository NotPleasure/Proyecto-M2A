package Design;

import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import javax.swing.*;

public class RoundedButtonLugares extends JButton {

    private int cornerRadius = 20;

    public RoundedButtonLugares(String text) {
        super(text);
        setContentAreaFilled(false);
        setFocusPainted(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        Shape clip = new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), cornerRadius, cornerRadius);
        g2.setClip(clip);
        Color currentColor = null;

        g2.setColor(currentColor);
        g2.fill(clip);

        super.paintComponent(g2);

        g2.setClip(null);
        g2.setColor(new Color(180, 180, 180, 180));
        g2.setStroke(new BasicStroke(1.5f));
        g2.draw(clip);

        g2.dispose();
    }
}
