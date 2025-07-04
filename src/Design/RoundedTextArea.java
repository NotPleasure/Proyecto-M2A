package Design;

import javax.swing.*;
import javax.swing.plaf.TextUI;
import java.awt.*;

public class RoundedTextArea extends JTextArea {
    private static final Color BACKGROUND_COLOR = new Color(229, 228, 227);
    private static final Color BORDER_COLOR = new Color(145, 141, 143);
    private static final int ARC = 0;
private static final float THICKNESS = 0.1f;

    public RoundedTextArea(int rows, int columns) {
        super(rows, columns);
        setOpaque(false);
        setBackground(BACKGROUND_COLOR);
        setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        setLineWrap(true);
        setWrapStyleWord(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), ARC, ARC);
        g2.dispose();

        super.paintComponent(g);
    }

    @Override
    protected void paintBorder(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(BORDER_COLOR);
        g2.setStroke(new BasicStroke(THICKNESS));
        g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, ARC, ARC);
        g2.dispose();
    }

    @Override
    public void updateUI() {
        setUI(new javax.swing.plaf.basic.BasicTextAreaUI());
        invalidate();
    }
}
