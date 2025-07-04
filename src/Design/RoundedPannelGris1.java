package Design;

import java.awt.*;
import javax.swing.*;

public class RoundedPannelGris1 extends JPanel {
    private static final Color BACKGROUND_COLOR = new Color(229, 228, 227);
    private static final Color BORDER_COLOR     = new Color(145, 141, 143);
    private static final int   CORNER_RADIUS   = 15;
    private static final int   BORDER_THICKNESS = 1;

    public RoundedPannelGris1() {
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Limpia el área para transparencia
        g2.setComposite(AlphaComposite.Clear);
        g2.fillRect(0, 0, getWidth(), getHeight());
        g2.setComposite(AlphaComposite.SrcOver);

        // Dibuja fondo redondeado con color deseado
        g2.setColor(BACKGROUND_COLOR);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), CORNER_RADIUS, CORNER_RADIUS);

        // Dibuja borde redondeado con color deseado
        g2.setColor(BORDER_COLOR);
        g2.setStroke(new BasicStroke(BORDER_THICKNESS));
        int half = BORDER_THICKNESS / 2;
        g2.drawRoundRect(half, half,
                         getWidth() - BORDER_THICKNESS,
                         getHeight() - BORDER_THICKNESS,
                         CORNER_RADIUS, CORNER_RADIUS);

        g2.dispose();
    }
}
