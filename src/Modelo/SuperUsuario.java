package Modelo;

public class SuperUsuario extends Persona {

    private String direccion;
    private String nombreNegocio;
    private String descripcion;
    private String cargo;
    public SuperUsuario() {
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getNombreNegocio() {
        return nombreNegocio;
    }

    public void setNombreNegocio(String nombreNegocio) {
        this.nombreNegocio = nombreNegocio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCargo() { 
        return cargo;
    }

    public void setCargo(String cargo) { 
        this.cargo = cargo;
    }
}
