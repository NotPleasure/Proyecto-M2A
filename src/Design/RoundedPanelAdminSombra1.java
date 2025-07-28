package Design;

import java.awt.*;
import javax.swing.*;

public class RoundedPanelAdminSombra1 extends JPanel {
    private int cornerRadius = 15;
    private Color backgroundColor = new Color(254, 255, 255);
    private int shadowSize = 2; 
    private Color shadowColor = new Color(0, 0, 0, 80); 

    public RoundedPanelAdminSombra1() {
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int width = getWidth();
        int height = getHeight();

        g2.setColor(shadowColor);
        g2.fillRoundRect(
            shadowSize, shadowSize,
            width - 1, height - 1,
            cornerRadius, cornerRadius
        );

        g2.setColor(backgroundColor);
        g2.fillRoundRect(
            0, 0,
            width - shadowSize, height - shadowSize,
            cornerRadius, cornerRadius
        );

        super.paintComponent(g);
        g2.dispose();
    }
}
