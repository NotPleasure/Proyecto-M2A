package Design;

import javax.swing.*;
import java.awt.*;

public class RoundedTextArea1 extends JTextArea {

    private static final Color BACKGROUND_COLOR = new Color(224, 224, 224);
    private static final Color BORDER_COLOR = new Color(145, 141, 143);
    private static final int THICKNESS = 1;
    private static final int ARC = 5;

    public RoundedTextArea1(int rows, int columns) {
        super(rows, columns);
        setOpaque(false);
        setBackground(BACKGROUND_COLOR);
        setBorder(BorderFactory.createEmptyBorder(
            THICKNESS,
            THICKNESS + 10,
            THICKNESS,
            THICKNESS + 10
        ));
        setLineWrap(true);
        setWrapStyleWord(true);
        setFont(new Font("Segoe UI", Font.PLAIN, 14));
        setCaretColor(Color.BLACK);
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
}
}
