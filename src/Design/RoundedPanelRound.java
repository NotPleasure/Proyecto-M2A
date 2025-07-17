package Design;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import javax.swing.JPanel;

public class RoundedPanelRound extends JPanel {

    private int roundTopLeft = 0;
    private int roundTopRight = 0;
    private int roundBottomLeft = 0;
    private int roundBottomRight = 0;

    // Imagen de fondo opcional
    private Image backgroundImage;

    public RoundedPanelRound() {
        setOpaque(false);
    }

    /** Permite asignar una imagen que se dibujará de fondo */
    public void setBackgroundImage(Image img) {
        this.backgroundImage = img;
        repaint();
    }

    public int getRoundTopLeft() { return roundTopLeft; }
    public void setRoundTopLeft(int roundTopLeft) { this.roundTopLeft = roundTopLeft; repaint(); }
    public int getRoundTopRight() { return roundTopRight; }
    public void setRoundTopRight(int roundTopRight) { this.roundTopRight = roundTopRight; repaint(); }
    public int getRoundBottomLeft() { return roundBottomLeft; }
    public void setRoundBottomLeft(int roundBottomLeft) { this.roundBottomLeft = roundBottomLeft; repaint(); }
    public int getRoundBottomRight() { return roundBottomRight; }
    public void setRoundBottomRight(int roundBottomRight) { this.roundBottomRight = roundBottomRight; repaint(); }

    @Override
    protected void paintComponent(Graphics grphcs) {
        int w = getWidth();
        int h = getHeight();
        Graphics2D g2 = (Graphics2D) grphcs.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Crear forma redondeada completa
        Shape roundShape = createRoundShape();

        // Dibujar imagen escalada, recortada a la forma
        if (backgroundImage != null) {
            g2.setClip(roundShape);
            g2.drawImage(backgroundImage, 0, 0, w, h, this);
            g2.setClip(null);
        }

        // Pintar fondo sobre la forma redondeada
        g2.setColor(getBackground());
        g2.fill(roundShape);

        g2.dispose();
        super.paintComponent(grphcs);
    }

    /** Combina los cuatro bordes redondeados en una única forma */
    private Shape createRoundShape() {
        int width = getWidth();
        int height = getHeight();
        int tl = Math.min(width, roundTopLeft);
        int tr = Math.min(width, roundTopRight);
        int bl = Math.min(width, roundBottomLeft);
        int br = Math.min(width, roundBottomRight);

        Area area = new Area(new RoundRectangle2D.Double(0, 0, width, height, tl, tl));
        if (tr > 0) area.intersect(new Area(new RoundRectangle2D.Double(0, 0, width, height, tr, tr)));
        if (bl > 0) area.intersect(new Area(new RoundRectangle2D.Double(0, 0, width, height, bl, bl)));
        if (br > 0) area.intersect(new Area(new RoundRectangle2D.Double(0, 0, width, height, br, br)));
        return area;
    }
}
