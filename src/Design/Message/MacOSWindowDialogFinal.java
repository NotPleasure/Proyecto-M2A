/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Design.Message;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

public class MacOSWindowDialogFinal extends JDialog {

    private float opacity = 0f;

    public MacOSWindowDialogFinal(JFrame parent, String mensaje) {
        super(parent, true);
        setUndecorated(true);
        setSize(400, 160);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout());
        setBackground(new Color(0, 0, 0, 0));

        // Panel sombra detrás
        JPanel sombra = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(0, 0, 0, 80));
                g2.fillRoundRect(5, 5, getWidth() - 10, getHeight() - 10, 30, 30);
            }
        };
        sombra.setOpaque(false);
        sombra.setLayout(new BorderLayout());

        // Panel principal con fondo oscuro elegante
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(25, 25, 25, 240));
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
            }
        };
        panel.setOpaque(false);
        panel.setLayout(new BorderLayout());
        panel.setBorder(new EmptyBorder(10, 15, 10, 15));

        // Barra superior con círculos macOS
        JPanel barraMac = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(25, 25, 25, 240));
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
            }
        };
        barraMac.setOpaque(false);
        barraMac.setPreferredSize(new Dimension(400, 35));
        barraMac.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 8));
        barraMac.add(crearCirculo(new Color(255, 95, 86)));    // Rojo
        barraMac.add(crearCirculo(new Color(255, 189, 46)));   // Amarillo
        barraMac.add(crearCirculo(new Color(39, 201, 63)));    // Verde
        panel.add(barraMac, BorderLayout.NORTH);

        // Texto centrado sin ícono
        JLabel textLabel = new JLabel(mensaje);
        textLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        textLabel.setForeground(Color.WHITE);
        textLabel.setHorizontalAlignment(SwingConstants.CENTER);
        textLabel.setVerticalAlignment(SwingConstants.CENTER);
        textLabel.setBorder(new EmptyBorder(20, 10, 20, 10));

        panel.add(textLabel, BorderLayout.CENTER);

        // Botón personalizado estilo macOS PRO
        JButton btnOk = new JButton("Aceptar") {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                if (getModel().isRollover()) {
                    g2.setColor(new Color(70, 130, 230));  // Hover más claro
                } else {
                    g2.setColor(new Color(60, 120, 215));  // Azul elegante
                }
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
                g2.setColor(Color.WHITE);
                g2.setFont(new Font("Segoe UI", Font.PLAIN, 14));
                FontMetrics fm = g2.getFontMetrics();
                int x = (getWidth() - fm.stringWidth(getText())) / 2;
                int y = (getHeight() - fm.getHeight()) / 2 + fm.getAscent();
                g2.drawString(getText(), x, y);
            }
        };
        btnOk.setOpaque(false);
        btnOk.setContentAreaFilled(false);
        btnOk.setBorderPainted(false);
        btnOk.setFocusPainted(false);
        btnOk.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnOk.setPreferredSize(new Dimension(100, 35));
        btnOk.addActionListener(e -> dispose());

        JPanel panelBtn = new JPanel();
        panelBtn.setOpaque(false);
        panelBtn.add(btnOk);
        panel.add(panelBtn, BorderLayout.SOUTH);

        sombra.add(panel, BorderLayout.CENTER);
        add(sombra);

        // Animación fade-in
        new Thread(() -> {
            try {
                for (int i = 0; i <= 20; i++) {
                    opacity = i / 20f;
                    setOpacity(opacity);
                    Thread.sleep(15);
                }
            } catch (InterruptedException ignored) {}
        }).start();
    }

    private JPanel crearCirculo(Color color) {
        JPanel circulo = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(color);
                g2.fillOval(0, 0, getWidth(), getHeight());
            }
        };
        circulo.setPreferredSize(new Dimension(12, 12));
        circulo.setOpaque(false);
        return circulo;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame();
            frame.setUndecorated(true);
            frame.setOpacity(0f);
            frame.setSize(0, 0);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);

            MacOSWindowDialogFinal dialog = new MacOSWindowDialogFinal(frame, "Mensaje estilo macOS sin ícono");
            dialog.setVisible(true);
            System.exit(0);
        });
    }
}

