/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import ConexionHuellasCuencanas.ConexionHuellasCuencanas;
import Modelo.ImagenLugar;
import Modelo.ImagenLugarDAO;
import Modelo.ImagenNegocio;
import Modelo.ImagenNegocioDAO;
import Modelo.InformacionHistorica;
import Modelo.InformacionHistoricaDAO;
import Modelo.LugarInteres;
import Modelo.LugarInteresDAO;
import Modelo.Museo;
import Modelo.MuseoDAO;
import Modelo.MuseoDetalleVista;
import Modelo.MuseoVista;
import Modelo.Negocios;
import Modelo.NegociosDAO;
import Modelo.Parque;
import Modelo.ParqueDao;
import Modelo.ParqueDetalleVista;
import Modelo.ParqueVista;
import Modelo.Restaurante;
import Modelo.RestauranteDAO;
import Modelo.UbicacionDAO;
import Modelo.UbicacionLugar;
import Modelo.UbicacionNegocio;
import Modelo.UbicacionNegocioDAO;
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
public class ControladorRestaurante {

    private final RestauranteDAO dao;

    public ControladorRestaurante() {
        this.dao = new RestauranteDAO();
    }

    public void guardarRestaurante(
            int id_super,
            String nombreLugar,
            String descripcion,
            String horaAperturaStr,
            String horaCierreStr,
            int capacidad,
            String especialidad,
            String categoria,
            String tipo_cocina,
            String direccion,
            double latitud,
            double longitud,
            byte[] imagen1,
            byte[] imagen2,
            byte[] imagen3
    ) throws SQLException {

        if (nombreLugar == null || nombreLugar.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del lugar es obligatorio.");
        }

        if (descripcion.isEmpty()) {
            throw new IllegalArgumentException("La descripcion es obligatoria.");
        }

        if (horaAperturaStr == null || horaAperturaStr.trim().isEmpty()) {
            throw new IllegalArgumentException("La hora de apertura es obligatoria.");
        }
        if (horaCierreStr == null || horaCierreStr.trim().isEmpty()) {
            throw new IllegalArgumentException("La hora de cierre es obligatoria.");
        }
        if (capacidad == 0) {
            throw new IllegalArgumentException("La capacidad es obligatoria.");
        }
        if (especialidad.isEmpty()) {
            throw new IllegalArgumentException("La especialidad es obligatoria.");
        }
        if (categoria.isEmpty()) {
            throw new IllegalArgumentException("La especialidad es obligatoria.");
        }
        if (tipo_cocina.isEmpty()) {
            throw new IllegalArgumentException("La especialidad es obligatoria.");
        }
        if (direccion == null || direccion.trim().isEmpty()) {
            throw new IllegalArgumentException("La dirección es obligatoria.");
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

        try (Connection con = ConexionHuellasCuencanas.conectar()) {
            try {
                con.setAutoCommit(false);

                if (NegociosDAO.existeNombre(con, nombreLugar)) {
                    throw new IllegalArgumentException("Ya existe un negocio con ese nombre.");
                }

                Negocios negocio = new Negocios();
                negocio.setId_super(id_super);
                negocio.setNombre(nombreLugar);
                negocio.setHora_apertura(horaApertura);
                negocio.setHora_cierre(horaCierre);
                negocio.setDescripcion(descripcion);
                negocio.setCapacidad(capacidad);
                int negocioId = NegociosDAO.insertar(con, negocio);

                Restaurante restaurante = new Restaurante();
                restaurante.setId_negocio(negocioId);
                restaurante.setTipo_cocina(tipo_cocina);
                restaurante.setCategoria(categoria);
                restaurante.getEspecialidad();
                RestauranteDAO guardar = new RestauranteDAO();
                guardar.insertar(con, restaurante);

                UbicacionNegocio ubicacion = new UbicacionNegocio(negocioId, direccion.trim(), latitud, longitud);
                UbicacionNegocioDAO.insertar(con, ubicacion);

                ImagenNegocioDAO.insertar(con, new ImagenLugar(negocioId, imagen1));
                ImagenNegocioDAO.insertar(con, new ImagenLugar(negocioId, imagen2));
                ImagenNegocioDAO.insertar(con, new ImagenLugar(negocioId, imagen3));

                con.commit();

            } catch (Exception e) {
                con.rollback();
                throw e;
            }
        }
    }

    public List<Restaurante> obtenerRestaurantesVista() throws SQLException {
        List<Restaurante> lista = new ArrayList<>();

        String sql = "SELECT n.id, n.nombre, n .hora_apertura, n.hora_cierre, img.imagen "
                + "FROM negocio n "
                + "JOIN restaurante r ON r.id_negocio = n.id "
                + "JOIN imagen_negocio img ON img.id_negocio = n.id  "
                + "WHERE img.id_negocio = ( "
                + "    SELECT MIN(id) "
                + "    FROM imagen_negocio "
                + "    WHERE id_negocio = n.id "
                + ")";

        try (Connection conn = ConexionHuellasCuencanas.conectar(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                LocalTime horaApertura = rs.getTime("hora_apertura").toLocalTime();
                LocalTime horaCierre = rs.getTime("hora_cierre").toLocalTime();
                byte[] imagen = rs.getBytes("imagen");

                lista.add(new Restaurante(id, nombre, horaApertura, horaCierre, imagen));
            }
        }

        return lista;
    }

    public Restaurante obtenerDetalleRestaurante(int lugarInteresId) throws SQLException {
        RestauranteDAO restaurantes = new RestauranteDAO();
        return restaurantes.obtenerDetalleRestaurante(lugarInteresId);
    }

    public boolean actualizarRestauranteCompleto(
            int capacidad,
            String tipo_cocina,
            String categoria,
            String especialidad,
            int negocioid,
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
      return dao.actualizarCompleto(capacidad, tipo_cocina, categoria, especialidad, negocioid, nombre, direccion, latitud, longitud, descripcion, horaApertura, horaCierre, img1, img2, img3);
    }
}
