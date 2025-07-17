package Design;

import javax.swing.border.Border;
import java.awt.*;

public class RoundedBorderRegistro implements Border {
    private final Color borderColor;
    private final int thickness;
    private final int arcWidth;
    private final int arcHeight;

    public RoundedBorderRegistro(Color borderColor, int thickness, int arcWidth, int arcHeight) {
        this.borderColor = borderColor;
        this.thickness = thickness;
        this.arcWidth = arcWidth;
        this.arcHeight = arcHeight;
    }

    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        Graphics2D g2 = (Graphics2D) g.create();

        // Suavizado de bordes
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Pintar fondo redondeado blanco
        g2.setColor(Color.WHITE);
        g2.fillRoundRect(x, y, width - 1, height - 1, arcWidth, arcHeight);

        // Pintar borde redondeado
        g2.setColor(borderColor);
        g2.setStroke(new BasicStroke(thickness));
        g2.drawRoundRect(x, y, width - 1, height - 1, arcWidth, arcHeight);

        g2.dispose();
    }

    @Override
    public Insets getBorderInsets(Component c) {
        return new Insets(thickness + 4, thickness + 8, thickness + 4, thickness + 8);
    }

    @Override
    public boolean isBorderOpaque() {
        return false;
    }
}