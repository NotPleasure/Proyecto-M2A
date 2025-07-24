/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Design.Graficos;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.chart.util.PublicCloneable;
import org.jfree.chart.ui.Drawable;
import java.awt.Color;
import java.awt.Paint;
import java.text.DecimalFormat;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.labels.StandardCategoryToolTipGenerator;

/**
 *
 * @author USER
 */
public class GraficosEstadisticos {

    public JFreeChart crearGraficoLugares(int iglesias, int museos, int parques) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(iglesias, "Cantidad", "Iglesias");
        dataset.addValue(museos, "Cantidad", "Museos");
        dataset.addValue(parques, "Cantidad", "Parques");

        JFreeChart chart = ChartFactory.createBarChart(
                null,
                null,
                null,
                dataset
        );

        CategoryPlot plot = chart.getCategoryPlot();

        BarRenderer renderer = new BarRenderer() {
            @Override
            public Paint getItemPaint(int row, int column) {
                switch (column) {
                    case 0:
                        return new Color(253, 63, 5);
                    case 1:
                        return new Color(0, 137, 248);
                    case 2:
                        return new Color(46, 180, 50);
                    default:
                        return super.getItemPaint(row, column);
                }
            }
        };

    renderer.setBaseToolTipGenerator(new StandardCategoryToolTipGenerator("{1}: {2}", new DecimalFormat("0")));

    plot.setRenderer(renderer);

        plot.setBackgroundPaint(Color.WHITE);
        chart.setBackgroundPaint(Color.WHITE);
        plot.setOutlineVisible(false);
        plot.setRangeGridlinesVisible(true);
        plot.setRangeGridlinePaint(Color.LIGHT_GRAY);

        plot.getDomainAxis().setVisible(true);
        plot.getRangeAxis().setVisible(true);

        plot.getDomainAxis().setLabel(null);
        plot.getRangeAxis().setLabel(null);

        chart.removeLegend();

        return chart;
    }

    public JFreeChart crearGraficoRecursos(int imagenes, int descripciones) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(imagenes, "Cantidad", "Imágenes");
        dataset.addValue(descripciones, "Cantidad", "Descripciones");

        JFreeChart chart = ChartFactory.createBarChart(
                null, null, null, dataset
        );

        CategoryPlot plot = chart.getCategoryPlot();

        BarRenderer renderer = new BarRenderer() {
            @Override
            public Paint getItemPaint(int row, int column) {
                switch (column) {
                    case 0:
                        return new Color(127, 212, 30);
                    case 1:
                        return new Color(0, 203, 248);
                    default:
                        return super.getItemPaint(row, column);
                }
            }
        };
    renderer.setBaseToolTipGenerator(new StandardCategoryToolTipGenerator("{1}: {2}", new DecimalFormat("0")));

    plot.setRenderer(renderer);

        plot.setBackgroundPaint(Color.WHITE);
        chart.setBackgroundPaint(Color.WHITE);
        plot.setOutlineVisible(false);
        plot.setRangeGridlinesVisible(true);
        plot.setRangeGridlinePaint(Color.LIGHT_GRAY);

        plot.getDomainAxis().setVisible(true);
        plot.getRangeAxis().setVisible(true);

        plot.getDomainAxis().setLabel(null);
        plot.getRangeAxis().setLabel(null);

        chart.removeLegend();

        return chart;
    }

    public JFreeChart crearGraficoHorarios(int hIglesias, int hMuseos, int hParques) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(hIglesias, "Cantidad", "Iglesias");
        dataset.addValue(hMuseos, "Cantidad", "Museos");
        dataset.addValue(hParques, "Cantidad", "Parques");

        JFreeChart chart = ChartFactory.createBarChart(
                null, null, null, dataset
        );
        CategoryPlot plot = chart.getCategoryPlot();


        BarRenderer renderer = new BarRenderer() {
            @Override
            public Paint getItemPaint(int row, int column) {
                switch (column) {
                    case 0:
                        return new Color(255, 63, 5);
                    case 1:
                        return new Color(0, 137, 248);
                    case 2:
                        return new Color(46, 180, 50);
                    default:
                        return super.getItemPaint(row, column);
                }
            }
        };
    renderer.setBaseToolTipGenerator(new StandardCategoryToolTipGenerator("{1}: {2}", new DecimalFormat("0")));

    plot.setRenderer(renderer);

        plot.setBackgroundPaint(Color.WHITE);
        chart.setBackgroundPaint(Color.WHITE);
        plot.setOutlineVisible(false);
        plot.setRangeGridlinesVisible(true);
        plot.setRangeGridlinePaint(Color.LIGHT_GRAY);

        plot.getDomainAxis().setVisible(true);
        plot.getRangeAxis().setVisible(true);

        plot.getDomainAxis().setLabel(null);
        plot.getRangeAxis().setLabel(null);

        chart.removeLegend();

        return chart;
    }

    private void personalizarColores(JFreeChart chart, Color[] colores) {
        CategoryPlot plot = chart.getCategoryPlot();
        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        for (int i = 0; i < colores.length; i++) {
            renderer.setSeriesPaint(i, colores[i]);
        }

        plot.setBackgroundPaint(Color.WHITE);
        chart.setBackgroundPaint(Color.WHITE);
        plot.setOutlineVisible(false);
        plot.setRangeGridlinesVisible(false);
    }
}
