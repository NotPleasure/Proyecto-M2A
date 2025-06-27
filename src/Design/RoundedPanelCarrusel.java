package Design;

import java.awt.*;
import javax.swing.*;

public class RoundedPanelCarrusel extends JPanel {

    private int cornerRadius = 75;
    private int alpha = 50; 
    private Color fillColor = new Color(255, 255, 255); 

    public RoundedPanelCarrusel() {
        setOpaque(false);
    }

  
    public void setAlpha(int alpha) {
        this.alpha = alpha;
        repaint();
    }

    public void setCornerRadius(int radius) {
        this.cornerRadius = radius;
        repaint();
    }

    public void setFillColor(Color color) {
        this.fillColor = color;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setComposite(AlphaComposite.Clear);
        g2.fillRect(0, 0, getWidth(), getHeight());

        g2.setComposite(AlphaComposite.SrcOver);
        g2.setColor(new Color(fillColor.getRed(), fillColor.getGreen(), fillColor.getBlue(), alpha));
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), cornerRadius, cornerRadius);

        g2.dispose();
    }
}
