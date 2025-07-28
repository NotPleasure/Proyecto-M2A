/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.IglesiaDAO;
import Modelo.IglesiaDetalleVista;
import java.sql.SQLException;
import java.time.LocalTime;

public class ControladorActualizarIglesia {

    private final IglesiaDAO dao;

    public ControladorActualizarIglesia() {
        this.dao = new IglesiaDAO();
    }

    public IglesiaDetalleVista obtenerDatos(int lugarInteresId) throws SQLException {
        return dao.obtenerDetalleIglesia(lugarInteresId);
    }

    public boolean actualizarIglesiaCompleto(
            int lugarInteresId,
            String nombre,
            String direccion,
            double latitud,
            double longitud,
            String descripcion,
            LocalTime horaApertura,
            LocalTime horaCierre,
            byte[] img1,
            byte[] img2,
            byte[] img3
    ) throws SQLException {
        return dao.actualizarCompleto(
                lugarInteresId,
                nombre,
                direccion,
                latitud,
                longitud,
                descripcion,
                horaApertura,
                horaCierre,
                img1,
                img2,
                img3
        );
    }
}
