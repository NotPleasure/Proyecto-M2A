package Design.ComboBox;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicComboBoxUI;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;




import javax.swing.*;
import javax.swing.plaf.basic.BasicComboBoxUI;
import java.awt.*;


public class Combobox<E> extends JComboBox<E> {
    private static final Color BACKGROUND_COLOR = new Color(229, 228, 227); 
    private static final Color BORDER_COLOR     = new Color(145, 141, 143); 
    private static final Color ARROW_COLOR      = new Color(80, 80, 80);    
    private static final int ARC                = 15;

    public Combobox(E[] items) {
        super(items);
        setOpaque(false);
        setBackground(BACKGROUND_COLOR);
        setForeground(Color.BLACK);
        setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        setUI(new RoundedComboBoxUI());
    }

    private class RoundedComboBoxUI extends BasicComboBoxUI {
        @Override
        protected JButton createArrowButton() {
            JButton arrowButton = new JButton() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    Graphics2D g2 = (Graphics2D) g.create();
                    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    int w = getWidth();
                    int h = getHeight();
                    int size = 8;
                    int x = (w - size) / 2;
                    int y = (h - size) / 2 + 2;
                    g2.setColor(ARROW_COLOR);
                    int[] xPoints = {x, x + size / 2, x + size};
                    int[] yPoints = {y, y + size, y};
                    g2.fillPolygon(xPoints, yPoints, 3);
                    g2.dispose();
                }
            };
            arrowButton.setBorderPainted(false);
            arrowButton.setContentAreaFilled(false);
            arrowButton.setFocusPainted(false);
            arrowButton.setOpaque(false);
            return arrowButton;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                            RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), ARC, ARC);

        g2.setColor(BORDER_COLOR);
        g2.setStroke(new BasicStroke(1));
        g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, ARC, ARC);

        g2.dispose();
        super.paintComponent(g);
    }
}