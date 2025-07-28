/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Reporte;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import ConexionHuellasCuencanas.ConexionHuellasCuencanas;
import java.io.File;
import net.sf.jasperreports.engine.util.JRLoader;

public class ReporteLugares {
public void mostrarReporte() {
    Connection conexion = null;
    try {
        System.out.println(new File("src/images/Cuenca Logo.png").getAbsolutePath());
        System.out.println(new File("build/classes/images/Cuenca Logo.png").exists());

        conexion = ConexionHuellasCuencanas.conectar();

        String rutaReporte = "src/Reporte/HuellasCuencanas.jasper";

        JasperReport reporte = (JasperReport) JRLoader.loadObjectFromFile(rutaReporte);

        Map<String, Object> params = new HashMap<>();

        JasperPrint print = JasperFillManager.fillReport(reporte, params, conexion);

        JasperViewer.viewReport(print, false);

    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        try {
            if (conexion != null) {
                conexion.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
}