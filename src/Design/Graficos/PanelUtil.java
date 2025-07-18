package Design.Graficos;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import java.awt.Dimension;

public class PanelUtil {

    public static ChartPanel crearPanelConFunciones(JFreeChart chart) {
        ChartPanel panel = new ChartPanel(chart);
        panel.setDisplayToolTips(true); // ✅ Esto es suficiente

        panel.setMouseWheelEnabled(true);
        panel.setDomainZoomable(true);
        panel.setRangeZoomable(true);


        panel.setPreferredSize(new Dimension(600, 400));

        return panel;
    }
}
