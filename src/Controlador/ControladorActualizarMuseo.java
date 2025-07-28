package Controlador;

import Modelo.MuseoDAO;
import Modelo.MuseoDetalleVista;
import java.sql.SQLException;
import java.time.LocalTime;

public class ControladorActualizarMuseo {

    private final MuseoDAO dao;

    public ControladorActualizarMuseo() {
        this.dao = new MuseoDAO();
    }

    public MuseoDetalleVista obtenerDatos(int lugarInteresId) throws SQLException {
        return dao.obtenerDetalleMuseo(lugarInteresId);
    }

    public boolean actualizarMuseoCompleto(
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
