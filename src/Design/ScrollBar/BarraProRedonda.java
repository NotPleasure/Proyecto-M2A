/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Design.ScrollBar;

import javax.swing.*;
import java.awt.*;

public class BarraProRedonda extends JProgressBar {

    public BarraProRedonda() {
        setBorderPainted(false);
        setIndeterminate(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Fondo
        g2.setColor(new Color(230, 230, 230)); // Fondo gris claro
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), getHeight(), getHeight());

        // Barra animada (foreground)
        g2.setColor(new Color(52, 152, 219)); // Azul bonito
        int width = (int) (getPercentComplete() * getWidth());
        g2.fillRoundRect(0, 0, width, getHeight(), getHeight(), getHeight());

        g2.dispose();
    }
}