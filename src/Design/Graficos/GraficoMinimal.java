/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Design.Graficos;

import org.knowm.xchart.CategoryChart;
import org.knowm.xchart.CategoryChartBuilder;
import org.knowm.xchart.XChartPanel;

import javax.swing.*;
import java.util.Arrays;

public class GraficoMinimal {

    public XChartPanel<CategoryChart> crearGraficoPanel(int totalUsuarios, int totalAdmins, int totalSuperusuarios, int totalUsuariosPrincipales) {
        CategoryChart chart = new CategoryChartBuilder()
                .width(600)
                .height(400)
                .build();

        chart.addSeries("Usuarios",
                Arrays.asList("Usuarios", "Admins", "Superusuarios", "Usuarios Principales"),
                Arrays.asList(totalUsuarios, totalAdmins, totalSuperusuarios, totalUsuariosPrincipales));

        chart.getStyler().setLegendVisible(false);
        chart.getStyler().setChartBackgroundColor(java.awt.Color.WHITE);

        return new XChartPanel<>(chart);
    }
}