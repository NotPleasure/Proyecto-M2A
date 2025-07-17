package Design;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class RoundedButtonInicio extends JButton {
private Color normalColor      = new Color(0x16, 0x68, 0xBF);
private Color hoverColor       = new Color(0x12, 0x58, 0xA2);
private Color placeholderColor = new Color(0x16, 0x68, 0xBF, 128);
    private Color currentColor = normalColor;

    public RoundedButtonInicio(String text) {
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
                currentColor = normalColor;
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
        g.setColor(currentColor);
        g.fillRect(0, 0, getWidth(), getHeight());
        super.paintComponent(g);
    }
}