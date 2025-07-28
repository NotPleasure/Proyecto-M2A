package Design;

import java.awt.*;
import javax.swing.*;

public class RoundedPanel1 extends JPanel {

    private int cornerRadius = 60;
    private Color backgroundColor = new Color(255, 255, 255, 200);
    private Color borderColor = new Color(233, 227, 219); 
    private int borderThickness = 1;        

    public RoundedPanel1() {
        setOpaque(false);
    }

    public void setBorderColor(Color color) {
        this.borderColor = color;
        repaint();
    }

    public void setBorderThickness(int thickness) {
        this.borderThickness = thickness;
        repaint();
    }

    public void setCornerRadius(int radius) {
        this.cornerRadius = radius;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Fondo transparente
        g2.setComposite(AlphaComposite.Clear);
        g2.fillRect(0, 0, getWidth(), getHeight());

        g2.setComposite(AlphaComposite.SrcOver);

        // Fondo redondeado
        g2.setColor(backgroundColor);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), cornerRadius, cornerRadius);

        // Dibuja el borde redondeado
        if (borderThickness > 0) {
            g2.setStroke(new BasicStroke(borderThickness));
            g2.setColor(borderColor);
            g2.drawRoundRect(
                borderThickness / 2,
                borderThickness / 2,
                getWidth() - borderThickness,
                getHeight() - borderThickness,
                cornerRadius,
                cornerRadius
            );
        }

        g2.dispose();
    }
}
