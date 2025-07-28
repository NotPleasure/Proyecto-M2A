package Modelo;

public class MuseoVistaUser {

    private int id;
    private String nombre;
    private String direccion;
    private byte[] imagenPrincipal;

    public MuseoVistaUser(int id, String nombre, String direccion, byte[] imagenPrincipal) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.imagenPrincipal = imagenPrincipal;
    }

    public MuseoVistaUser() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public byte[] getImagenPrincipal() {
        return imagenPrincipal;
    }
}
