/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Design;

import javax.swing.*;
import java.awt.*;

public class RoundedPasswordField extends JPasswordField {
    private static final Color BACKGROUND_COLOR = new Color(229, 228, 227); 
    private static final Color BORDER_COLOR     = new Color(145, 141, 143); 
    private static final int   THICKNESS        = 1;                      
    private static final int   ARC              = 15;                    

    public RoundedPasswordField(int columns) {
        super(columns);
        setOpaque(false);
        setBackground(BACKGROUND_COLOR);
        setBorder(BorderFactory.createEmptyBorder(
            THICKNESS,
            THICKNESS + 10,
            THICKNESS,
            THICKNESS + 10
        ));
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(
            RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON
        );

        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), ARC, ARC);
        g2.dispose();

        super.paintComponent(g); 
    }

    @Override
    protected void paintBorder(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(
            RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON
        );

        g2.setColor(BORDER_COLOR);
        g2.setStroke(new BasicStroke(THICKNESS));
        g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, ARC, ARC);
        g2.dispose();
    }
}
