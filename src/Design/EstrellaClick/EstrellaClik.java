package Design.EstrellaClick;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Path2D;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import javax.swing.*;

public class EstrellaClik extends JPanel {

    private boolean isFilled;
    private final List<Consumer<Boolean>> listeners = new ArrayList<>();

    // Constructor recibe el estado inicial
    public EstrellaClik(boolean initial) {
        this.isFilled = initial;
        setPreferredSize(new Dimension(30, 30));
        setOpaque(false);
        setCursor(new Cursor(Cursor.HAND_CURSOR));

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                isFilled = !isFilled;
                repaint();
                // Notificamos a todos los listeners
                for (Consumer<Boolean> l : listeners) {
                    l.accept(isFilled);
                }
            }
        });
    }

    public void addFavoritoListener(Consumer<Boolean> listener) {
        listeners.add(listener);
    }

    //Para revertir estado si la BD falla:
    public void setFilled(boolean filled) {
        this.isFilled = filled;
        repaint();
    }

    //Para consultar el estado actual:
    public boolean isFilled() {
        return isFilled;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        int x = getWidth() / 2, y = getHeight() / 2, outer = 12, inner = 6;
        Shape estrella = crearEstrella(x, y, outer, inner, 5);

        g2.setColor(isFilled ? Color.ORANGE : Color.LIGHT_GRAY);
        g2.fill(estrella);
        g2.setColor(Color.DARK_GRAY);
        g2.setStroke(new BasicStroke(2f));
        g2.draw(estrella);
        g2.dispose();
    }

    private Shape crearEstrella(int cx, int cy, int rOut, int rIn, int rays) {
        Path2D path = new Path2D.Double();
        double start = -Math.PI / 2, angle = Math.PI / rays;
        for (int i = 0; i < rays * 2; i++) {
            double r = (i % 2 == 0) ? rOut : rIn;
            double a = start + i * angle;
            double sx = cx + Math.cos(a) * r;
            double sy = cy + Math.sin(a) * r;
            if (i == 0) {
                path.moveTo(sx, sy);
            } else {
                path.lineTo(sx, sy);
            }
        }
        path.closePath();
        return path;
    }
}
