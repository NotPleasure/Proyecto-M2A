/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import ConexionHuellasCuencanas.ConexionHuellasCuencanas;
import Modelo.ImagenLugar;
import Modelo.ImagenLugarDAO;
import Modelo.InformacionHistorica;
import Modelo.InformacionHistoricaDAO;
import Modelo.LugarInteres;
import Modelo.LugarInteresDAO;
import Modelo.Museo;
import Modelo.MuseoDAO;
import Modelo.MuseoVista;
import Modelo.Parque;
import Modelo.ParqueDao;
import Modelo.ParqueVista;
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
public class ControladorParque {
      public void guardarParque(
            String nombreLugar,
            String entidad_gestora,
            float superfice,
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

        if (entidad_gestora.isEmpty()) {
            throw new IllegalArgumentException("La entidad gestora es obligatoria.");
        }

        if (superfice == 0) {
            throw new IllegalArgumentException("La superficie en metros cuadrados  es obligatoria.");
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



        if (latitud < -90 || latitud > 90) {
            throw new IllegalArgumentException("La latitud debe estar entre -90 y 90.");
        }

        if (longitud < -180 || longitud > 180) {
            throw new IllegalArgumentException("La longitud debe estar entre -180 y 180.");
        }

        try (Connection con = ConexionHuellasCuencanas.conectar()) {
            try {
                con.setAutoCommit(false);

                if (LugarInteresDAO.existeNombre(con, nombreLugar.trim())) {
                    throw new IllegalArgumentException("Ya existe un lugar con ese nombre.");
                }

                LugarInteres lugar = new LugarInteres(nombreLugar.trim());
                int lugarId = LugarInteresDAO.insertar(con, lugar);

                Parque parque = new Parque(lugarId, entidad_gestora, superfice);
                ParqueDao.insertar(con, parque);

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

    public List<ParqueVista> obtenerMuseosVista() throws SQLException {
        List<ParqueVista> lista = new ArrayList<>();

        String sql = "SELECT li.nombre, p.entidad_gestora, p.superficie, img.imagen "
                + "FROM lugares_interes li "
                + "JOIN parque p ON p.lugar_interes_id = li.lugar_interes_id "
                + "JOIN imagenes_lugar img ON img.lugar_interes_id = li.lugar_interes_id "
                + "WHERE img.imagen_lugar_id = ( "
                + "    SELECT MIN(imagen_lugar_id) "
                + "    FROM imagenes_lugar "
                + "    WHERE lugar_interes_id = li.lugar_interes_id "
                + ")";

        try (Connection conn = ConexionHuellasCuencanas.conectar(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                String nombre = rs.getString("nombre");
                String entidad_gestora = rs.getString("entidad_gestora");
                float superfice = rs.getFloat("superficie");
                byte[] imagen = rs.getBytes("imagen");

                lista.add(new ParqueVista(nombre, superfice, entidad_gestora, imagen));
            }
        }

        return lista;
    }
}
