/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Design.Imagen;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;

public class CircularImageLabel extends JLabel {

    private Image imagenOriginal;

    public void setImagen(Image imagen) {
        this.imagenOriginal = imagen;
        repaint();
    }

    public void setImagenDesdeBytes(byte[] bytes) {
        if (bytes != null) {
            ImageIcon icon = new ImageIcon(bytes);
            this.imagenOriginal = icon.getImage();
            repaint();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(new Color(224, 224, 224));
        g2.fillOval(0, 0, getWidth(), getHeight());

        if (imagenOriginal != null) {
            BufferedImage buffer = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
            Graphics2D gBuffer = buffer.createGraphics();
            gBuffer.setClip(new Ellipse2D.Float(0, 0, getWidth(), getHeight()));
            gBuffer.drawImage(imagenOriginal, 0, 0, getWidth(), getHeight(), null);
            gBuffer.dispose();
            g2.drawImage(buffer, 0, 0, null);
        }

        g2.dispose();
    }
}