package Design;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class RoundedButtonIniciarSesionRegistrarse extends JButton {
    private int cornerRadius = 10;

private Color normalColor = new Color(140, 85, 0);        // Antes hover, ahora principal
private Color hoverColor = new Color(160, 100, 20);       // Aún más claro para el hover
private Color pressedColor = new Color(111, 68, 0);       // El anterior normal, ahora como pressed
private Color currentColor = normalColor;


    public RoundedButtonIniciarSesionRegistrarse(String text) {
        super(text);
        setContentAreaFilled(false);
        setFocusPainted(false);
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

        super.paintComponent(g);
        g2.dispose();
    }
}
