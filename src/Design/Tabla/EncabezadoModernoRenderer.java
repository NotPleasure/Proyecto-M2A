package Design.Tabla;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;

public class EncabezadoModernoRenderer extends DefaultTableCellRenderer {

    public EncabezadoModernoRenderer() {
        setHorizontalAlignment(CENTER);
        setOpaque(true);
        setForeground(Color.BLACK);
        setFont(new Font("Segoe UI Light", Font.PLAIN, 16));
        setBorder(BorderFactory.createMatteBorder(0, 0, 3, 0, new Color(100, 150, 240)));
        setBackground(new Color(0xE2E4E7));
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();

        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        int ancho = getWidth();
        int alto = getHeight();

        g2d.setColor(getBackground());
        g2d.fillRect(0, 0, ancho, alto);

        g2d.setColor(getForeground());
        g2d.setFont(getFont());
        FontMetrics fm = g2d.getFontMetrics();
        String texto = getText();
        int x = (ancho - fm.stringWidth(texto)) / 2;
        int y = (alto + fm.getAscent() - fm.getDescent()) / 2;
        g2d.drawString(texto, x, y);

        g2d.dispose();
    }
}
