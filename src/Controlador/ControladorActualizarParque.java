package Controlador;

import Modelo.ParqueDao;
import Modelo.ParqueDetalleVista;
import java.sql.SQLException;

public class ControladorActualizarParque {

    private final ParqueDao dao;

    public ControladorActualizarParque() {
        this.dao = new ParqueDao();
    }

    public ParqueDetalleVista obtenerDatos(int lugarInteresId) throws SQLException {
        return dao.obtenerDetalleParque(lugarInteresId);
    }

    public boolean actualizarParqueCompleto(
            int lugarInteresId,
            String nombre,
            String direccion,
            double latitud,
            double longitud,
            String descripcion,
            String entidadGestora,
            float superficie,
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
                entidadGestora,
                superficie,
                img1,
                img2,
                img3
        );
    }
}
