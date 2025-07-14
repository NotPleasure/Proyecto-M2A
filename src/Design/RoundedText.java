package Design;

import javax.swing.*;
import javax.swing.border.AbstractBorder;
import java.awt.*;

public class RoundedText extends AbstractBorder {
    private static final Color BORDER_COLOR = new Color(145, 141, 143);
    private static final int THICKNESS = 1;
    private static final int ARC = 5;

    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        Graphics2D g2 = (Graphics2D) g.create();

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(BORDER_COLOR);

        g2.setStroke(new BasicStroke(THICKNESS));

        g2.drawRoundRect(x + THICKNESS / 2, y + THICKNESS / 2,
                width - THICKNESS, height - THICKNESS, ARC, ARC);

        g2.dispose();
    }

    @Override
    public Insets getBorderInsets(Component c) {
        return new Insets(THICKNESS, THICKNESS, THICKNESS, THICKNESS);
    }

    @Override
    public Insets getBorderInsets(Component c, Insets insets) {
        insets.left = insets.top = insets.right = insets.bottom = THICKNESS;
        return insets;
    }
}
