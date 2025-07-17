package Design;

import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import javax.swing.*;

public class RoundedButtonLugares extends JButton {
    private int cornerRadius = 20;

    public RoundedButtonLugares(String text) {
        super(text);
        setContentAreaFilled(false);
        setFocusPainted(false);
    }

@Override
protected void paintComponent(Graphics g) {
    Graphics2D g2 = (Graphics2D) g.create();
    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

    // Forma redondeada
    Shape clip = new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), cornerRadius, cornerRadius);
    g2.setClip(clip);
        Color currentColor = null;

    // Fondo del botón
    g2.setColor(currentColor);
    g2.fill(clip);

    // Pinta imagen y texto (recortados al borde redondo)
    super.paintComponent(g2);

    // Dibujar borde
    g2.setClip(null); // quita el clip para que el borde se dibuje completo
    g2.setColor(new Color(180, 180, 180, 180)); // gris claro con algo de transparencia
    g2.setStroke(new BasicStroke(1.5f)); // grosor del borde
    g2.draw(clip); // dibuja el contorno

    g2.dispose();
}
}

