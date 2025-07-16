/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.time.LocalTime;

public class IglesiaDetalleVista {
    private int id;
    private String nombre;
    private LocalTime horaApertura;
    private LocalTime horaCierre;
    private double latitud;
    private double longitud;
    private String direccion;
    private String descripcion;
    private byte[] imagen1;
    private byte[] imagen2;
    private byte[] imagen3;

    public IglesiaDetalleVista(int id, String nombre, LocalTime horaApertura, LocalTime horaCierre,
                               double latitud, double longitud, String direccion, String descripcion,
                               byte[] imagen1, byte[] imagen2, byte[] imagen3) {
        this.id = id;
        this.nombre = nombre;
        this.horaApertura = horaApertura;
        this.horaCierre = horaCierre;
        this.latitud = latitud;
        this.longitud = longitud;
        this.direccion = direccion;
        this.descripcion = descripcion;
        this.imagen1 = imagen1;
        this.imagen2 = imagen2;
        this.imagen3 = imagen3;
    }


    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public LocalTime getHoraApertura() {
        return horaApertura;
    }

    public LocalTime getHoraCierre() {
        return horaCierre;
    }

    public double getLatitud() {
        return latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public byte[] getImagen1() {
        return imagen1;
    }

    public byte[] getImagen2() {
        return imagen2;
    }

    public byte[] getImagen3() {
        return imagen3;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    
}
