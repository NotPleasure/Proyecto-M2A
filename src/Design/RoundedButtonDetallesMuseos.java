package Design;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class RoundedButtonDetallesMuseos extends JButton {

    private int cornerRadius = 20;

    // Colores personalizados
    private Color normalColor = new Color(0xAB, 0xAB, 0xAB);
    private Color hoverColor = new Color(0x95, 0x95, 0x95);
    private Color pressedColor = new Color(0x80, 0x80, 0x80);
    private Color borderColor = new Color(0xC8, 0xB9, 0x8E);

    private Color currentColor = normalColor;

    public RoundedButtonDetallesMuseos(String text) {
        super(text);
        setContentAreaFilled(false);
        setFocusPainted(false);
        setBorderPainted(false);
        setForeground(Color.WHITE);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                currentColor = hoverColor;
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                currentColor = normalColor;
                repaint();
            }

            @Override
            public void mousePressed(MouseEvent e) {
                currentColor = pressedColor;
                repaint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                currentColor = hoverColor;
                repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(currentColor);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), cornerRadius, cornerRadius);

        g2.setColor(borderColor);
        g2.setStroke(new BasicStroke(2f));
        g2.drawRoundRect(1, 1, getWidth() - 2, getHeight() - 2, cornerRadius, cornerRadius);

        super.paintComponent(g);
        g2.dispose();
    }
}
