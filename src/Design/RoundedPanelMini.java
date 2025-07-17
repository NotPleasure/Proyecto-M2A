/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Design;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;

public class RoundedPanelMini extends JPanel {

    public RoundedPanelMini() {
        initComponents();
        setOpaque(false);
    }

    private void initComponents() {

    }
@Override
protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2 = (Graphics2D) g;
    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

    g2.setColor(Color.decode("#051342"));  
    g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);  
}
}