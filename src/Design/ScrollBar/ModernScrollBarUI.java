package Design.ScrollBar;

import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;

public class ModernScrollBarUI extends BasicScrollBarUI {

    private static final int SCROLLBAR_WIDTH = 6;  // ancho total de la scrollbar

    @Override
    protected void configureScrollBarColors() {
        this.thumbColor = new Color(0x9B59B6);
        this.trackColor = new Color(0xF8FAFC);
        this.thumbHighlightColor = thumbColor.darker();
        this.thumbDarkShadowColor    = thumbColor.darker();
        this.thumbLightShadowColor   = thumbColor.brighter();
    }

    @Override
    protected JButton createDecreaseButton(int orientation) {
        return createZeroButton();
    }

    @Override
    protected JButton createIncreaseButton(int orientation) {
        return createZeroButton();
    }

    private JButton createZeroButton() {
        JButton btn = new JButton();
        btn.setPreferredSize(new Dimension(0, 0));
        btn.setMinimumSize(new Dimension(0, 0));
        btn.setMaximumSize(new Dimension(0, 0));
        return btn;
    }

    @Override
    protected Dimension getMinimumThumbSize() {
        // Thumb mínimo: ahora de sólo 6 px de ancho
        return new Dimension(SCROLLBAR_WIDTH, 30);
    }

    @Override
    protected void paintTrack(Graphics g, JComponent c, Rectangle trackBounds) {
        g.setColor(trackColor);
        g.fillRect(trackBounds.x, trackBounds.y, trackBounds.width, trackBounds.height);
    }

    @Override
    protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                            RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(thumbColor);
        int arc = SCROLLBAR_WIDTH;  // usa el mismo valor para un pulgar ligeramente redondeado
        g2.fillRoundRect(thumbBounds.x, thumbBounds.y,
                         thumbBounds.width, thumbBounds.height, arc, arc);
        g2.dispose();
    }

    @Override
    public Dimension getPreferredSize(JComponent c) {
        // Se asegura de que la pista también mida SCROLLBAR_WIDTH
        if (scrollbar.getOrientation() == JScrollBar.VERTICAL) {
            return new Dimension(SCROLLBAR_WIDTH, super.getPreferredSize(c).height);
        } else {
            return new Dimension(super.getPreferredSize(c).width, SCROLLBAR_WIDTH);
        }
    }
}
