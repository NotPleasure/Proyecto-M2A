package Design;

import java.awt.*;
import javax.swing.*;

public class RoundedPanelAdmin extends JPanel {
    private int cornerRadius = 15;
    private Color backgroundColor = new Color(254, 255, 255); 
    public RoundedPanelAdmin() {
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(backgroundColor);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), cornerRadius, cornerRadius);

        super.paintComponent(g);

        g2.dispose();
    }
}
