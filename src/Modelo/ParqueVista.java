/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author MeiRen
 */
public class ParqueVista {
     private String nombre;
     private float superficie;
     private String entidad_gestora;
     private byte[] imagenPrincipal;

    public ParqueVista() {
    }

    public ParqueVista(String nombre, float superficie, String entidad_gestora, byte[] imagenPrincipal) {
        this.nombre = nombre;
        this.superficie = superficie;
        this.entidad_gestora = entidad_gestora;
        this.imagenPrincipal = imagenPrincipal;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float getSuperficie() {
        return superficie;
    }

    public void setSuperficie(float superficie) {
        this.superficie = superficie;
    }

    public String getEntidad_gestora() {
        return entidad_gestora;
    }

    public void setEntidad_gestora(String entidad_gestora) {
        this.entidad_gestora = entidad_gestora;
    }

    public byte[] getImagenPrincipal() {
        return imagenPrincipal;
    }

    public void setImagenPrincipal(byte[] imagenPrincipal) {
        this.imagenPrincipal = imagenPrincipal;
    }
     
}
