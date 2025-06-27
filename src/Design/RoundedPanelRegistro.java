/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Design;

/**
 *
 * @author USER
 */
import java.awt.*;
import javax.swing.*;

public class RoundedPanelRegistro extends JPanel {
    private int cornerRadius = 15;
    // Prueba con un alfa MUY bajo, por ejemplo 30/255 ≈ 12% de opacidad
    private Color backgroundColor = new Color(35, 35, 35, 10);

    public RoundedPanelRegistro() {
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        // No “limpiar” con Clear, simplemente pintamos nuestro fondo translúcido
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                            RenderingHints.VALUE_ANTIALIAS_ON);

        // Pintar el fondo redondeado usando el color con alfa muy bajo
        g2.setColor(backgroundColor);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(),
                         cornerRadius, cornerRadius);

        g2.dispose();

        // Opcional: si quieres que Swing pinte los hijos sobre ese fondo,
        // llama aquí a super.paintComponent(g) después del fill, o
        // simplemente déjalo fuera si no hay componentes internos.
        super.paintComponent(g);
    }
}