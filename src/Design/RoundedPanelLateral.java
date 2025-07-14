package Design;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class RoundedPanelLateral extends JPanel {

    public RoundedPanelLateral() {
        setOpaque(false);
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
        int borderWidth = 12; // Grosor del borde lateral

        // 1. Dibujar sombra
        g2.setColor(new Color(0, 0, 0, 30));
        g2.fillRoundRect(x + 2, y + 2, width, height, arc, arc); // sombra desplazada ligeramente

        // 2. Dibujar el borde azul lateral izquierdo
        g2.setColor(new Color(57, 85, 135)); // Azul oscuro
        g2.fillRoundRect(x - 8, y, width + 8, height, arc, arc); // extendido hacia la izquierda

        // 3. Dibujar el panel blanco encima, dejando visible solo el borde izquierdo
        g2.setColor(Color.WHITE);
        g2.fillRoundRect(x, y, width, height, arc, arc);

        g2.dispose();
    }
}
