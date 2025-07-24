/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Design.Imagen;

import java.awt.*;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.awt.geom.Ellipse2D;

public class FotoPerfilRedonda extends JPanel {

    private BufferedImage imagen;

    public void setImagen(BufferedImage img) {
        setOpaque(false);

        this.imagen = img;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (imagen != null) {
            int size = Math.min(getWidth(), getHeight());
            BufferedImage redonda = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2 = redonda.createGraphics();

            // Hacer el recorte circular
            g2.setClip(new Ellipse2D.Float(0, 0, size, size));
            g2.drawImage(imagen.getScaledInstance(size, size, Image.SCALE_SMOOTH), 0, 0, null);
            g2.dispose();

            // Dibujar la imagen centrada
            g.drawImage(redonda, (getWidth() - size) / 2, (getHeight() - size) / 2, null);
        }
    }
}
