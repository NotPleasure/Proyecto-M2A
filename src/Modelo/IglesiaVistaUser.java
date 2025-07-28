package Modelo;

public class IglesiaVistaUser {

    private int id;
    private String nombre;
    private String direccion;
    private byte[] imagenPrincipal;
    

    public IglesiaVistaUser(int id, String nombre, String direccion, byte[] imagenPrincipal) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.imagenPrincipal = imagenPrincipal;
    }

    public IglesiaVistaUser() {
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
