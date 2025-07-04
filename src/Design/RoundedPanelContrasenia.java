/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Design;

/**
 *
 * @author USER
 */
import java.awt.*;
import javax.swing.*;

public class RoundedPanelContrasenia extends JPanel {

    private int cornerRadius = 60;
    private Color backgroundColor = new Color(35, 35, 35, 200);

    public RoundedPanelContrasenia() {
        setOpaque(false);
    }

@Override
protected void paintComponent(Graphics g) {
    Graphics2D g2 = (Graphics2D) g.create();
    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

    // Borra el fondo transparente (opcional)
    g2.setComposite(AlphaComposite.Clear);
    g2.fillRect(0, 0, getWidth(), getHeight());

    // Vuelve a modo normal de dibujo
    g2.setComposite(AlphaComposite.SrcOver);

    // Pon el color deseado, con alfa si quieres transparencia (por ejemplo 200)
    Color fondo = new Color(11, 15, 24, 200);  // RGBA
    g2.setColor(fondo);

    g2.fillRoundRect(0, 0, getWidth(), getHeight(), cornerRadius, cornerRadius);

    g2.dispose();
}
}
