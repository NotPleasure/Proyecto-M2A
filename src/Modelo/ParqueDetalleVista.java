/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.time.LocalTime;

/**
 *
 * @author MeiRen
 */
public class ParqueDetalleVista {
      private int id;
    private String nombre;
    private String entidad_gestora;
    private  float superficie;
    private double latitud;
    private double longitud;
    private String direccion;
    private String descripcion;
    private byte[] imagen1;
    private byte[] imagen2;
    private byte[] imagen3;

    public ParqueDetalleVista(int id, String nombre, String entidad_gestora, float superficie, double latitud, double longitud, String direccion, String descripcion, byte[] imagen1, byte[] imagen2, byte[] imagen3) {
        this.id = id;
        this.nombre = nombre;
        this.entidad_gestora = entidad_gestora;
        this.superficie = superficie;
        this.latitud = latitud;
        this.longitud = longitud;
        this.direccion = direccion;
        this.descripcion = descripcion;
        this.imagen1 = imagen1;
        this.imagen2 = imagen2;
        this.imagen3 = imagen3;
    }

    public ParqueDetalleVista() {
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

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEntidad_gestora() {
        return entidad_gestora;
    }

    public void setEntidad_gestora(String entidad_gestora) {
        this.entidad_gestora = entidad_gestora;
    }

    public float getSuperficie() {
        return superficie;
    }

    public void setSuperficie(float superficie) {
        this.superficie = superficie;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public byte[] getImagen1() {
        return imagen1;
    }

    public void setImagen1(byte[] imagen1) {
        this.imagen1 = imagen1;
    }

    public byte[] getImagen2() {
        return imagen2;
    }

    public void setImagen2(byte[] imagen2) {
        this.imagen2 = imagen2;
    }

    public byte[] getImagen3() {
        return imagen3;
    }

    public void setImagen3(byte[] imagen3) {
        this.imagen3 = imagen3;
    }
    
}
