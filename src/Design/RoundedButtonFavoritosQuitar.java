package Design;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class RoundedButtonFavoritosQuitar extends JButton {

    private int cornerRadius = 15;

    // Colores de fondo
    private Color normalColor = new Color(0xFF, 0xFF, 0xFF); 
    private Color hoverColor = new Color(0x24, 0x38, 0x62);  
    private Color pressedColor = new Color(0xCC, 0xCC, 0xCC);

    // Colores del borde
    private Color borderNormal = new Color(0x48, 0x68, 0xB1); 
    private Color borderHover = new Color(0x5A, 0x7C, 0xC5);  
    private Color borderPressed = new Color(0x36, 0x52, 0x8D); 

    private Color currentColor = normalColor;
    private Color currentBorderColor = borderNormal;

    public RoundedButtonFavoritosQuitar(String text) {
        super(text);
        setContentAreaFilled(false);
        setFocusPainted(false);
        setBorderPainted(false);

        // Texto oscuro para el fondo blanco
        setForeground(Color.BLACK);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                currentColor = hoverColor;
                currentBorderColor = borderHover;
                setForeground(Color.WHITE);
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                currentColor = normalColor;
                currentBorderColor = borderNormal;
                setForeground(Color.BLACK);
                repaint();
            }

            @Override
            public void mousePressed(MouseEvent e) {
                currentColor = pressedColor;
                currentBorderColor = borderPressed;
                setForeground(Color.BLACK);
                repaint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                currentColor = hoverColor;
                currentBorderColor = borderHover;
                setForeground(Color.WHITE);
                repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Fondo del botón
        g2.setColor(currentColor);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), cornerRadius, cornerRadius);

        // Borde
        g2.setColor(currentBorderColor);
        g2.setStroke(new BasicStroke(1)); // Grosor del borde = 1 píxel
        g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, cornerRadius, cornerRadius);

        super.paintComponent(g);
        g2.dispose();
    }
}
