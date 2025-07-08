package Design;

import java.awt.*;
import javax.swing.*;

public class RoundedPanelAdminBorde extends JPanel {
    private int cornerRadius = 15;
    private Color backgroundColor = new Color(254, 255, 255);
    private Color borderColor = Color.decode("#F7F6FB");
    private int borderThickness = 2;

    public RoundedPanelAdminBorde() {
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(backgroundColor);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), cornerRadius, cornerRadius);

        g2.setColor(borderColor);
        g2.setStroke(new BasicStroke(borderThickness));
        int offset = borderThickness / 2;
        g2.drawRoundRect(offset, offset, getWidth() - borderThickness, getHeight() - borderThickness, cornerRadius, cornerRadius);

        super.paintComponent(g);
        g2.dispose();
    }
}
