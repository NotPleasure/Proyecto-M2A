package Design;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class RoundedButtonOscuro extends JButton {
private Color normalColor  = new Color(0x0F, 0x46, 0x97);
private Color hoverColor   = new Color(0x19, 0x5C, 0xBD);
private Color pressedColor = new Color(0x0B, 0x34, 0x70);
private Color currentColor = normalColor;

    public RoundedButtonOscuro(String text) {
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