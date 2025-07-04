/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Prueba;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.AbstractBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class FavoritosCuencaSwing extends JFrame {
    
    private List<LugarTuristico> favoritos;
    private JPanel cardPanel;
    private JLabel statsTotal, statsIglesias, statsHoteles, statsVisitados;
    private Color primaryColor = new Color(52, 152, 219);
    private Color secondaryColor = new Color(231, 76, 60);
    private Color backgroundColor = new Color(236, 240, 241);
    
    public FavoritosCuencaSwing() {
        initializeFavoritos();
        setupUI();
    }
    
    private void setupUI() {
        setTitle("Mis Favoritos - Cuenca Ecuador");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1920, 1080);
        setLocationRelativeTo(null);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        
        // Panel principal con gradiente
        JPanel mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                // Gradiente de fondo
                GradientPaint gradient = new GradientPaint(
                    0, 0, new Color(102, 126, 234),
                    getWidth(), getHeight(), new Color(118, 75, 162)
                );
                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        
        // Panel contenedor con efecto glassmorphism
        JPanel contentPanel = createContentPanel();
        mainPanel.add(contentPanel, BorderLayout.CENTER);
        
        add(mainPanel);
    }
    
    private JPanel createContentPanel() {
        JPanel contentPanel = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                // Fondo con transparencia
                g2d.setColor(new Color(255, 255, 255, 240));
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
                
                // Borde sutil
                g2d.setStroke(new BasicStroke(2));
                g2d.setColor(new Color(255, 255, 255, 100));
                g2d.drawRoundRect(1, 1, getWidth()-2, getHeight()-2, 20, 20);
            }
        };
        contentPanel.setBorder(new EmptyBorder(30, 30, 30, 30));
        contentPanel.setOpaque(false);
        
        // Header
        JPanel header = createHeader();
        contentPanel.add(header, BorderLayout.NORTH);
        
        // Contenido principal
        JPanel mainContent = createMainContent();
        contentPanel.add(mainContent, BorderLayout.CENTER);
        
        return contentPanel;
    }
    
    private JPanel createHeader() {
        JPanel header = new JPanel(new BorderLayout());
        header.setOpaque(false);
        header.setBorder(new EmptyBorder(0, 0, 30, 0));
        
        // Header izquierdo
        JPanel headerLeft = new JPanel();
        headerLeft.setLayout(new BoxLayout(headerLeft, BoxLayout.Y_AXIS));
        headerLeft.setOpaque(false);
        
        // Título
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        titlePanel.setOpaque(false);
        
        JLabel title = new JLabel("Mis Favoritos ");
        title.setFont(new Font("Segoe UI", Font.BOLD, 35));
        title.setForeground(new Color(44, 62, 80));
        
        JLabel heart = new JLabel("❤️");
        heart.setFont(new Font("Segoe UI", Font.PLAIN, 32));
        
        titlePanel.add(title);
        titlePanel.add(heart);
        
        JLabel subtitle = new JLabel("Guarda aquí los lugares que más te inspiran");
        subtitle.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        subtitle.setForeground(new Color(127, 140, 141));
        
        JLabel breadcrumb = new JLabel("Usuario > Favoritos");
        breadcrumb.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        breadcrumb.setForeground(primaryColor);
        
        headerLeft.add(titlePanel);
        headerLeft.add(Box.createVerticalStrut(8));
        headerLeft.add(subtitle);
        headerLeft.add(Box.createVerticalStrut(8));
        headerLeft.add(breadcrumb);
        
        // Header derecho
        JPanel headerRight = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 0));
        headerRight.setOpaque(false);
        
        // Botones de vista
        JToggleButton horizontalBtn = createToggleButton("🔲 Horizontal", true);
        JToggleButton listBtn = createToggleButton("📋 Lista", false);
        
        ButtonGroup viewGroup = new ButtonGroup();
        viewGroup.add(horizontalBtn);
        viewGroup.add(listBtn);
        
        // ComboBox filtro
        JComboBox<String> filterCombo = new JComboBox<>(
            new String[]{"Filtrar por categoría", "Hoteles", "Iglesias", "Museos", "Parques"}
        );
        filterCombo.setPreferredSize(new Dimension(180, 40));
        filterCombo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        
        headerRight.add(horizontalBtn);
        headerRight.add(listBtn);
        headerRight.add(filterCombo);
        
        header.add(headerLeft, BorderLayout.WEST);
        header.add(headerRight, BorderLayout.EAST);
        
        return header;
    }
    
    private JPanel createMainContent() {
        JPanel mainContent = new JPanel(new BorderLayout());
        mainContent.setOpaque(false);
        
        // Scroll horizontal para las cards
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        scrollPane.setPreferredSize(new Dimension(1200, 600));
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        
        // Panel de cards horizontales
        cardPanel = new JPanel();
        cardPanel.setLayout(new BoxLayout(cardPanel, BoxLayout.X_AXIS));
        cardPanel.setOpaque(false);
        cardPanel.setBorder(new EmptyBorder(0, 0, 20, 0));
        
        createFavoriteCards();
        scrollPane.setViewport(new JViewport() {
            @Override
            public void setViewPosition(Point p) {
                super.setViewPosition(p);
                repaint();
            }
        });
        scrollPane.setViewportView(cardPanel);
        
        // Panel de estadísticas
        JPanel statsPanel = createStatsPanel();
        
        mainContent.add(scrollPane, BorderLayout.CENTER);
        mainContent.add(statsPanel, BorderLayout.SOUTH);
        
        return mainContent;
    }
    
    private JPanel createStatsPanel() {
        JPanel statsPanel = new JPanel(new GridLayout(1, 4, 20, 0));
        statsPanel.setOpaque(false);
        statsPanel.setBorder(new EmptyBorder(20, 0, 0, 0));
        
        // Estadísticas
        statsTotal = createStatCard("Total de Favoritos", String.valueOf(favoritos.size()), "📍", primaryColor);
        statsIglesias = createStatCard("Iglesias", String.valueOf(contarPorCategoria("Iglesia")), "⛪", new Color(155, 89, 182));
        statsHoteles = createStatCard("Hoteles", String.valueOf(contarPorCategoria("Hotel")), "🏨", new Color(52, 152, 219));
        statsVisitados = createStatCard("Visitados", String.valueOf(contarVisitados()), "✅", new Color(46, 204, 113));
        
        statsPanel.add(statsTotal);
        statsPanel.add(statsIglesias);
        statsPanel.add(statsHoteles);
        statsPanel.add(statsVisitados);
        
        return statsPanel;
    }
    
    private JLabel createStatCard(String title, String value, String icon, Color color) {
        JLabel statCard = new JLabel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                // Fondo de la tarjeta
                g2d.setColor(Color.WHITE);
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
                
                // Borde
                g2d.setStroke(new BasicStroke(2));
                g2d.setColor(color);
                g2d.drawRoundRect(1, 1, getWidth()-2, getHeight()-2, 15, 15);
            }
        };
        
        statCard.setOpaque(false);
        statCard.setPreferredSize(new Dimension(200, 80));
        statCard.setHorizontalAlignment(SwingConstants.CENTER);
        statCard.setText(String.format("<html><div style='text-align: center'>" +
                "<div style='font-size: 18px; margin-bottom: 5px;'>%s %s</div>" +
                "<div style='font-size: 24px; font-weight: bold; color: %s;'>%s</div>" +
                "<div style='font-size: 12px; color: #7f8c8d;'>%s</div>" +
                "</div></html>", 
                icon, value, String.format("#%06X", color.getRGB() & 0xFFFFFF), value, title));
        
        return statCard;
    }
    
    private void createFavoriteCards() {
        cardPanel.removeAll();
        
        for (LugarTuristico lugar : favoritos) {
            JPanel card = createFavoriteCard(lugar);
            cardPanel.add(card);
            cardPanel.add(Box.createHorizontalStrut(20));
        }
        
        cardPanel.revalidate();
        cardPanel.repaint();
    }
    
    private JPanel createFavoriteCard(LugarTuristico lugar) {
        JPanel card = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                // Sombra
                g2d.setColor(new Color(0, 0, 0, 30));
                g2d.fillRoundRect(5, 5, getWidth()-5, getHeight()-5, 20, 20);
                
                // Fondo de la tarjeta
                g2d.setColor(Color.WHITE);
                g2d.fillRoundRect(0, 0, getWidth()-5, getHeight()-5, 20, 20);
                
                // Borde sutil
                g2d.setStroke(new BasicStroke(1));
                g2d.setColor(new Color(0, 0, 0, 50));
                g2d.drawRoundRect(0, 0, getWidth()-6, getHeight()-6, 20, 20);
            }
        };
        
        card.setLayout(new BorderLayout());
        card.setPreferredSize(new Dimension(300, 450));
        card.setOpaque(false);
        card.setBorder(new EmptyBorder(15, 15, 15, 15));
        
        // Panel superior con imagen
        JPanel imagePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                // Gradiente de imagen placeholder
                GradientPaint gradient = new GradientPaint(
                    0, 0, new Color(74, 144, 226),
                    getWidth(), getHeight(), new Color(80, 227, 194)
                );
                g2d.setPaint(gradient);
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
            }
        };
        imagePanel.setPreferredSize(new Dimension(270, 200));
        imagePanel.setOpaque(false);
        imagePanel.setLayout(new BorderLayout());
        
        // Categoría en la imagen
        JLabel categoryLabel = new JLabel(lugar.getCategoria().toUpperCase());
        categoryLabel.setForeground(Color.WHITE);
        categoryLabel.setFont(new Font("Segoe UI", Font.BOLD, 12));
        categoryLabel.setOpaque(true);
        categoryLabel.setBackground(new Color(0, 0, 0, 100));
        categoryLabel.setBorder(new EmptyBorder(5, 10, 5, 10));
        categoryLabel.setHorizontalAlignment(SwingConstants.CENTER);
        
        JPanel categoryPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        categoryPanel.setOpaque(false);
        categoryPanel.add(categoryLabel);
        
        imagePanel.add(categoryPanel, BorderLayout.NORTH);
        
        // Información del lugar
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setOpaque(false);
        infoPanel.setBorder(new EmptyBorder(15, 0, 0, 0));
        
        // Nombre del lugar
        JLabel nameLabel = new JLabel(lugar.getNombre());
        nameLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        nameLabel.setForeground(new Color(44, 62, 80));
        nameLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        // Descripción
        JLabel descLabel = new JLabel("<html><div style='width: 250px;'>" + lugar.getDescripcion() + "</div></html>");
        descLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        descLabel.setForeground(new Color(127, 140, 141));
        descLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        // Rating
        JPanel ratingPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        ratingPanel.setOpaque(false);
        ratingPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        for (int i = 1; i <= 5; i++) {
            JLabel star = new JLabel(i <= lugar.getRating() ? "★" : "☆");
            star.setFont(new Font("Segoe UI", Font.PLAIN, 16));
            star.setForeground(i <= lugar.getRating() ? new Color(241, 196, 15) : new Color(189, 195, 199));
            ratingPanel.add(star);
        }
        
        JLabel ratingText = new JLabel(" (" + lugar.getRating() + "/5)");
        ratingText.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        ratingText.setForeground(new Color(127, 140, 141));
        ratingPanel.add(ratingText);
        
        // Botones
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        buttonPanel.setOpaque(false);
        buttonPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        JButton visitedBtn = createRoundButton(lugar.isVisitado() ? "✅ Visitado" : "📍 Marcar", 
                lugar.isVisitado() ? new Color(46, 204, 113) : primaryColor);
        JButton removeBtn = createRoundButton("🗑️ Eliminar", secondaryColor);
        
        visitedBtn.addActionListener(e -> {
            lugar.setVisitado(!lugar.isVisitado());
            refreshCards();
        });
        
        removeBtn.addActionListener(e -> {
            favoritos.remove(lugar);
            refreshCards();
        });
        
        buttonPanel.add(visitedBtn);
        buttonPanel.add(Box.createHorizontalStrut(10));
        buttonPanel.add(removeBtn);
        
        infoPanel.add(nameLabel);
        infoPanel.add(Box.createVerticalStrut(8));
        infoPanel.add(descLabel);
        infoPanel.add(Box.createVerticalStrut(10));
        infoPanel.add(ratingPanel);
        infoPanel.add(Box.createVerticalStrut(15));
        infoPanel.add(buttonPanel);
        
        card.add(imagePanel, BorderLayout.NORTH);
        card.add(infoPanel, BorderLayout.CENTER);
        
        // Efecto hover
        card.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                card.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                card.setCursor(Cursor.getDefaultCursor());
            }
        });
        
        return card;
    }
    
    private JButton createRoundButton(String text, Color backgroundColor) {
        JButton button = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                if (getModel().isPressed()) {
                    g2d.setColor(backgroundColor.darker());
                } else if (getModel().isRollover()) {
                    g2d.setColor(backgroundColor.brighter());
                } else {
                    g2d.setColor(backgroundColor);
                }
                
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
                
                // Texto
                g2d.setColor(Color.WHITE);
                g2d.setFont(getFont());
                FontMetrics fm = g2d.getFontMetrics();
                int x = (getWidth() - fm.stringWidth(getText())) / 2;
                int y = (getHeight() + fm.getAscent()) / 2 - 2;
                g2d.drawString(getText(), x, y);
            }
        };
        
        button.setPreferredSize(new Dimension(100, 30));
        button.setFont(new Font("Segoe UI", Font.BOLD, 11));
        button.setOpaque(false);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        
        return button;
    }
    
    private JToggleButton createToggleButton(String text, boolean selected) {
        JToggleButton button = new JToggleButton(text, selected) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                if (isSelected()) {
                    g2d.setColor(primaryColor);
                } else {
                    g2d.setColor(new Color(189, 195, 199));
                }
                
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
                
                // Texto
                g2d.setColor(Color.WHITE);
                g2d.setFont(getFont());
                FontMetrics fm = g2d.getFontMetrics();
                int x = (getWidth() - fm.stringWidth(getText())) / 2;
                int y = (getHeight() + fm.getAscent()) / 2 - 2;
                g2d.drawString(getText(), x, y);
            }
        };
        
        button.setPreferredSize(new Dimension(120, 40));
        button.setFont(new Font("Segoe UI", Font.BOLD, 12));
        button.setOpaque(false);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        
        return button;
    }
    
    private void refreshCards() {
        createFavoriteCards();
        updateStats();
    }
    
    private void updateStats() {
        statsTotal.setText(String.format("<html><div style='text-align: center'>" +
                "<div style='font-size: 18px; margin-bottom: 5px;'>📍 %d</div>" +
                "<div style='font-size: 24px; font-weight: bold; color: #3498db;'>%d</div>" +
                "<div style='font-size: 12px; color: #7f8c8d;'>Total de Favoritos</div>" +
                "</div></html>", favoritos.size(), favoritos.size()));
        
        statsIglesias.setText(String.format("<html><div style='text-align: center'>" +
                "<div style='font-size: 18px; margin-bottom: 5px;'>⛪ %d</div>" +
                "<div style='font-size: 24px; font-weight: bold; color: #9b59b6;'>%d</div>" +
                "<div style='font-size: 12px; color: #7f8c8d;'>Iglesias</div>" +
                "</div></html>", contarPorCategoria("Iglesia"), contarPorCategoria("Iglesia")));
        
        statsHoteles.setText(String.format("<html><div style='text-align: center'>" +
                "<div style='font-size: 18px; margin-bottom: 5px;'>🏨 %d</div>" +
                "<div style='font-size: 24px; font-weight: bold; color: #3498db;'>%d</div>" +
                "<div style='font-size: 12px; color: #7f8c8d;'>Hoteles</div>" +
                "</div></html>", contarPorCategoria("Hotel"), contarPorCategoria("Hotel")));
        
        statsVisitados.setText(String.format("<html><div style='text-align: center'>" +
                "<div style='font-size: 18px; margin-bottom: 5px;'>✅ %d</div>" +
                "<div style='font-size: 24px; font-weight: bold; color: #2ecc71;'>%d</div>" +
                "<div style='font-size: 12px; color: #7f8c8d;'>Visitados</div>" +
                "</div></html>", contarVisitados(), contarVisitados()));
    }
    
    private int contarPorCategoria(String categoria) {
        return (int) favoritos.stream().filter(l -> l.getCategoria().equals(categoria)).count();
    }
    
    private int contarVisitados() {
        return (int) favoritos.stream().filter(LugarTuristico::isVisitado).count();
    }
    
    private void initializeFavoritos() {
        favoritos = new ArrayList<>();
        
        favoritos.add(new LugarTuristico(
            "Catedral de la Inmaculada Concepción",
            "Iglesia",
            "Majestuosa catedral con cúpulas de cerámica azul, símbolo icónico de Cuenca",
            5,
            true
        ));
        
        favoritos.add(new LugarTuristico(
            "Hotel Mansion Alcazar",
            "Hotel",
            "Elegante hotel boutique en el centro histórico con arquitectura colonial",
            4,
            false
        ));
        
        favoritos.add(new LugarTuristico(
            "Iglesia de San Francisco",
            "Iglesia",
            "Hermosa iglesia colonial con impresionante fachada de piedra",
            4,
            true
        ));
        
        favoritos.add(new LugarTuristico(
            "Museo Pumapungo",
            "Museo",
            "Museo arqueológico con ruinas incas y jardines etnobotánicos",
            5,
            false
        ));
        
        favoritos.add(new LugarTuristico(
            "Parque Calderón",
            "Parque",
            "Plaza central de Cuenca rodeada de edificios históricos",
            4,
            true
        ));
        
        favoritos.add(new LugarTuristico(
            "Hotel Santa Lucia",
            "Hotel",
            "Acogedor hotel con vista al río Tomebamba",
            4,
            false
        ));
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            
            new FavoritosCuencaSwing().setVisible(true);
        });
    }
}

// Clase auxiliar para borde redondeado
class RoundedBorder extends AbstractBorder {
    private int radius;
    private Color color;
    private int thickness;
    
    public RoundedBorder(int radius) {
        this(radius, Color.BLACK, 1);
    }
    
    public RoundedBorder(int radius, Color color, int thickness) {
        this.radius = radius;
        this.color = color;
        this.thickness = thickness;
    }
    
    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(color);
        g2d.setStroke(new BasicStroke(thickness));
        g2d.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
        g2d.dispose();
    }
    
    @Override
    public Insets getBorderInsets(Component c) {
        return new Insets(thickness, thickness, thickness, thickness);
    }
    
    @Override
    public Insets getBorderInsets(Component c, Insets insets) {
        insets.left = insets.top = insets.right = insets.bottom = thickness;
        return insets;
    }
}

// Clase auxiliar para representar lugares turísticos
class LugarTuristico {
    private String nombre;
    private String categoria;
    private String descripcion;
    private int rating;
    private boolean visitado;
    
    public LugarTuristico(String nombre, String categoria, String descripcion, int rating, boolean visitado) {
        this.nombre = nombre;
        this.categoria = categoria;
        this.descripcion = descripcion;
        this.rating = rating;
        this.visitado = visitado;
    }
    
    // Getters y setters
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    
    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }
    
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    
    public int getRating() { return rating; }
    public void setRating(int rating) { this.rating = rating; }
    
    public boolean isVisitado() { return visitado; }
    public void setVisitado(boolean visitado) { this.visitado = visitado; }
}