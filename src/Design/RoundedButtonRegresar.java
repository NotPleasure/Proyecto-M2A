package Design;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class RoundedButtonRegresar extends JButton {
private Color normalColor      = new Color(0, 68, 219);
private Color hoverColor       = new Color(30, 100, 240); 
private Color placeholderColor = new Color(0, 50, 160); 
    private Color currentColor = normalColor;

    public RoundedButtonRegresar(String text) {
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