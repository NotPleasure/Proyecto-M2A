package Design.Reloj;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class RelojMinimalista extends JPanel {

    public RelojMinimalista() {
        setOpaque(false);
        setLayout(new BorderLayout());
        setBackground(new Color(0, 0, 0, 0));

        JPanel contenedorHorizontal = new JPanel();
        contenedorHorizontal.setOpaque(false);
        contenedorHorizontal.setLayout(new BoxLayout(contenedorHorizontal, BoxLayout.X_AXIS));

        PanelReloj reloj = new PanelReloj();
        reloj.setPreferredSize(new Dimension(250, 250));

        PanelInfo info = new PanelInfo();
        info.setPreferredSize(new Dimension(220, 100));

        // Agregar espacios para ajustar posición
        contenedorHorizontal.add(Box.createHorizontalGlue());
        contenedorHorizontal.add(Box.createRigidArea(new Dimension(25, 0))); // mover reloj a la derecha
        contenedorHorizontal.add(reloj);
        contenedorHorizontal.add(Box.createRigidArea(new Dimension(1, 0))); // espacio entre reloj e info
        contenedorHorizontal.add(info);
        contenedorHorizontal.add(Box.createHorizontalGlue());

        add(contenedorHorizontal, BorderLayout.CENTER);
    }

    static class PanelReloj extends JPanel {

        public PanelReloj() {
            setOpaque(false);
            setBackground(new Color(0, 0, 0, 0));
            Timer timer = new Timer(1000, e -> repaint());
            timer.start();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            int ancho = getWidth();
            int alto = getHeight();
            int centroX = ancho / 2;
            int centroY = alto / 2;
            int radio = (int) (Math.min(ancho, alto) * 0.45);

            dibujarMarcas(g2d, centroX, centroY, radio);

            LocalTime ahora = LocalTime.now();
            int horas = ahora.getHour();
            int minutos = ahora.getMinute();
            int segundos = ahora.getSecond();

            dibujarManecilla(g2d, centroX, centroY, radio * 0.5, calcularAnguloHora(horas, minutos), 6f, Color.BLACK);
            dibujarManecilla(g2d, centroX, centroY, radio * 0.75, calcularAnguloMinuto(minutos, segundos), 4f, Color.BLACK);
            dibujarManecilla(g2d, centroX, centroY, radio * 0.9, calcularAnguloSegundo(segundos), 2f, Color.RED);

            g2d.setColor(Color.BLACK);
            g2d.fillOval(centroX - 5, centroY - 5, 10, 10);
        }

        private void dibujarMarcas(Graphics2D g2d, int cx, int cy, int r) {
            g2d.setColor(Color.BLACK);
            for (int i = 0; i < 60; i++) {
                double angulo = Math.toRadians(i * 6);
                int x1 = (int) (cx + r * Math.sin(angulo));
                int y1 = (int) (cy - r * Math.cos(angulo));
                int longitudMarca = (i % 5 == 0) ? 12 : 6;
                g2d.setStroke(new BasicStroke(i % 5 == 0 ? 3f : 1.5f));
                int x2 = (int) (cx + (r - longitudMarca) * Math.sin(angulo));
                int y2 = (int) (cy - (r - longitudMarca) * Math.cos(angulo));
                g2d.draw(new Line2D.Float(x1, y1, x2, y2));
            }
        }

        private void dibujarManecilla(Graphics2D g2d, int cx, int cy, double longitud, double angulo, float grosor, Color color) {
            g2d.setColor(color);
            g2d.setStroke(new BasicStroke(grosor, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
            int x = (int) (cx + longitud * Math.sin(angulo));
            int y = (int) (cy - longitud * Math.cos(angulo));
            g2d.draw(new Line2D.Float(cx, cy, x, y));
        }

        private double calcularAnguloSegundo(int s) {
            return Math.toRadians(s * 6);
        }

        private double calcularAnguloMinuto(int m, int s) {
            return Math.toRadians(m * 6 + s * 0.1);
        }

        private double calcularAnguloHora(int h, int m) {
            return Math.toRadians((h % 12) * 30 + m * 0.5);
        }
    }

    static class PanelInfo extends JPanel {

        private JLabel etiquetaHoraDigital;
        private JLabel etiquetaFecha;

        private final DateTimeFormatter formatoHora = DateTimeFormatter.ofPattern("HH:mm");
        private final DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("E, d MMM", new Locale("es", "ES"));

        public PanelInfo() {
            setOpaque(false);
            setLayout(new GridBagLayout());
            setBackground(new Color(0, 0, 0, 0));

            JPanel contenedorVertical = new JPanel();
            contenedorVertical.setLayout(new BoxLayout(contenedorVertical, BoxLayout.Y_AXIS));
            contenedorVertical.setBackground(new Color(0, 0, 0, 0));

            etiquetaHoraDigital = new JLabel();
            etiquetaHoraDigital.setFont(new Font("SansSerif", Font.BOLD, 32));
            etiquetaHoraDigital.setForeground(Color.BLACK);
            etiquetaHoraDigital.setAlignmentX(Component.CENTER_ALIGNMENT);

            etiquetaFecha = new JLabel();
            etiquetaFecha.setFont(new Font("SansSerif", Font.PLAIN, 18));
            etiquetaFecha.setForeground(Color.DARK_GRAY);
            etiquetaFecha.setAlignmentX(Component.CENTER_ALIGNMENT);

            contenedorVertical.add(etiquetaHoraDigital);
            contenedorVertical.add(Box.createRigidArea(new Dimension(0, 10)));
            contenedorVertical.add(etiquetaFecha);

            add(contenedorVertical);

            Timer timer = new Timer(1000, e -> actualizarHora());
            timer.start();

            actualizarHora();
        }

        private void actualizarHora() {
            LocalTime horaActual = LocalTime.now();
            LocalDate fechaActual = LocalDate.now();

            etiquetaHoraDigital.setText(horaActual.format(formatoHora));
            etiquetaFecha.setText(fechaActual.format(formatoFecha).toUpperCase());
        }
    }
}
