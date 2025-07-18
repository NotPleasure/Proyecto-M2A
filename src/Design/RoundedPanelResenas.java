package Design;

import java.awt.*;
import javax.swing.*;

public class RoundedPanelResenas extends JPanel {
    private int cornerRadius = 10;

    public RoundedPanelResenas() {
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setComposite(AlphaComposite.Clear);
        g2.fillRect(0, 0, getWidth(), getHeight());

        g2.setComposite(AlphaComposite.SrcOver);

        g2.setColor(new Color(248, 250, 252));
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), cornerRadius, cornerRadius);

        g2.dispose();
    }
}
