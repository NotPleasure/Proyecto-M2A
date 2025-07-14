/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Design;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class RoundedPanelLugares3 extends JPanel {

    public RoundedPanelLugares3() {
        setOpaque(false);
        setPreferredSize(new Dimension(220, 280));
        setBorder(new EmptyBorder(15, 15, 15, 15)); // Margen interno
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int arc = 30;
        int x = 5;
        int y = 5;
        int width = getWidth() - 10;
        int height = getHeight() - 10;

        g2.setColor(new Color(0, 0, 0, 30)); 
        g2.fillRoundRect(x, y, width, height, arc, arc);

        g2.setColor(new Color(0x2E, 0xCC, 0x71)); 
        g2.fillRoundRect(x, y - 8, width, height, arc, arc);  

        g2.setColor(Color.WHITE);
        g2.fillRoundRect(x, y, width, height, arc, arc);

        g2.dispose();
    }
}
