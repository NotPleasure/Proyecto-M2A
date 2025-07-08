package Design;

import java.awt.*;
import javax.swing.*;

public class RoundedPanelAdminEncabezado extends JPanel {

    private int cornerRadius = 5;
    private Color backgroundColor = new Color(230, 230, 230);

    public RoundedPanelAdminEncabezado() {
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        super.paintComponent(g);

        g2.setColor(backgroundColor);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), cornerRadius, cornerRadius);

        g2.dispose();
    }
}
