/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import ConexionHuellasCuencanas.ConexionHuellasCuencanas;
import Modelo.Iglesia;
import Modelo.IglesiaDAO;
import Modelo.IglesiaVista;
import Modelo.ImagenLugar;
import Modelo.ImagenLugarDAO;
import Modelo.InformacionHistorica;
import Modelo.InformacionHistoricaDAO;
import Modelo.LugarInteres;
import Modelo.LugarInteresDAO;
import Modelo.Museo;
import Modelo.MuseoDAO;
import Modelo.MuseoVista;
import Modelo.UbicacionDAO;
import Modelo.UbicacionLugar;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author MeiRen
 */
public class ControladorMuseo {
     public void guardarMuseo(
            String nombreLugar,
            String horaAperturaStr,
            String horaCierreStr,
            String direccion,
            double latitud,
            double longitud,
            String descripcionHist,
            byte[] imagen1,
            byte[] imagen2,
            byte[] imagen3
    ) throws SQLException {

        if (nombreLugar == null || nombreLugar.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del lugar es obligatorio.");
        }

        if (horaAperturaStr == null || horaAperturaStr.trim().isEmpty()) {
            throw new IllegalArgumentException("La hora de apertura es obligatoria.");
        }

        if (horaCierreStr == null || horaCierreStr.trim().isEmpty()) {
            throw new IllegalArgumentException("La hora de cierre es obligatoria.");
        }

        if (direccion == null || direccion.trim().isEmpty()) {
            throw new IllegalArgumentException("La dirección es obligatoria.");
        }

        if (descripcionHist == null || descripcionHist.trim().isEmpty()) {
            throw new IllegalArgumentException("La descripción histórica es obligatoria.");
        }

        if (imagen1 == null || imagen2 == null || imagen3 == null) {
            throw new IllegalArgumentException("Se requieren tres imágenes.");
        }

        LocalTime horaApertura = LocalTime.parse(horaAperturaStr.trim());
        LocalTime horaCierre = LocalTime.parse(horaCierreStr.trim());

        try {
            horaApertura = LocalTime.parse(horaAperturaStr.trim());
            horaCierre = LocalTime.parse(horaCierreStr.trim());
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Formato de hora inválido. Usa HH:mm, por ejemplo: 08:30");
        }

        if (!horaCierre.isAfter(horaApertura)) {
            throw new IllegalArgumentException("La hora de cierre debe ser posterior a la de apertura.");
        }

        if (latitud < -90 || latitud > 90) {
            throw new IllegalArgumentException("La latitud debe estar entre -90 y 90.");
        }

        if (longitud < -180 || longitud > 180) {
            throw new IllegalArgumentException("La longitud debe estar entre -180 y 180.");
        }

        try ( Connection con = ConexionHuellasCuencanas.conectar()) {
            try {
                con.setAutoCommit(false);

                if (LugarInteresDAO.existeNombre(con, nombreLugar.trim())) {
                    throw new IllegalArgumentException("Ya existe un lugar con ese nombre.");
                }

                LugarInteres lugar = new LugarInteres(nombreLugar.trim());
                int lugarId = LugarInteresDAO.insertar(con, lugar);

                Museo museo = new Museo(lugarId, horaApertura, horaCierre);
                MuseoDAO.insertar(con, museo);

                UbicacionLugar ubicacion = new UbicacionLugar(lugarId, direccion.trim(), latitud, longitud);
                UbicacionDAO.insertar(con, ubicacion);

                InformacionHistorica historia = new InformacionHistorica(lugarId, descripcionHist.trim());
                InformacionHistoricaDAO.insertar(con, historia);

                ImagenLugarDAO.insertar(con, new ImagenLugar(lugarId, imagen1));
                ImagenLugarDAO.insertar(con, new ImagenLugar(lugarId, imagen2));
                ImagenLugarDAO.insertar(con, new ImagenLugar(lugarId, imagen3));

                con.commit();

            } catch (Exception e) {
                con.rollback();
                throw e;
            }
        }
    }

    public List<MuseoVista> obtenerMuseosVista() throws SQLException {
        List<MuseoVista> lista = new ArrayList<>();

        String sql = "SELECT li.nombre, m.hora_apertura, m.hora_cierre, img.imagen "
                + "FROM lugares_interes li "
                + "JOIN museo m ON m.lugar_interes_id = li.lugar_interes_id "
                + "JOIN imagenes_lugar img ON img.lugar_interes_id = li.lugar_interes_id "
                + "WHERE img.imagen_lugar_id = ( "
                + "    SELECT MIN(imagen_lugar_id) "
                + "    FROM imagenes_lugar "
                + "    WHERE lugar_interes_id = li.lugar_interes_id "
                + ")";

        try ( Connection conn = ConexionHuellasCuencanas.conectar();  PreparedStatement ps = conn.prepareStatement(sql);  ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                String nombre = rs.getString("nombre");
                LocalTime horaApertura = rs.getTime("hora_apertura").toLocalTime();
                LocalTime horaCierre = rs.getTime("hora_cierre").toLocalTime();
                byte[] imagen = rs.getBytes("imagen");

                lista.add(new MuseoVista(nombre, horaApertura, horaCierre, imagen));
            }
        }

        return lista;
    }
}
