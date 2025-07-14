/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Design.Imagen;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;

public class RoundedImageLabel extends JLabel {

    private Image imagenOriginal;
    private int cornerRadius = 20;
    private Color bordeColor = new Color(155, 89, 182); 
    private int grosorBorde = 2;

    public void setImagenDesdeBytes(byte[] bytes) {
        if (bytes != null) {
            ImageIcon icon = new ImageIcon(bytes);
            this.imagenOriginal = icon.getImage();
            repaint();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); 

        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        Shape clip = new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), cornerRadius, cornerRadius);
        g2.setColor(bordeColor);
        g2.setStroke(new BasicStroke(grosorBorde));
        g2.draw(clip);

        if (imagenOriginal != null) {
            BufferedImage buffer = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
            Graphics2D gBuffer = buffer.createGraphics();
            gBuffer.setClip(clip);
            gBuffer.drawImage(imagenOriginal, 0, 0, getWidth(), getHeight(), this);
            gBuffer.dispose();
            g2.drawImage(buffer, 0, 0, null);
        }

        g2.dispose();
    }
}
