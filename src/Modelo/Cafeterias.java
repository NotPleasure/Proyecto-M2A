/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.time.LocalTime;

/**
 *
 * @author MeiRens
 */
public class Cafeterias extends Negocios {
    private String tematica;
    private byte[] imagen1;
    private byte[] imagen2;
    private byte[] imagen3;
    private double latitud;
    private double longitud;
    private String direccion;

    public Cafeterias() {
    }

    public Cafeterias(int id_negocio, String nombre, LocalTime horaapertura, LocalTime horacierre, byte[] imagenBytes) {
        super(id_negocio, nombre, horaapertura, horacierre, imagenBytes);
    }

    public Cafeterias(String tematica, byte[] imagen1, byte[] imagen2, byte[] imagen3, double latitud, double longitud, String direccion, int id_negocio, int id_super, String nombre, String descripcion, LocalTime hora_apertura, LocalTime hora_cierre, int capacidad, byte[] imagenPrincipal) {
        super(id_negocio, id_super, nombre, descripcion, hora_apertura, hora_cierre, capacidad, imagenPrincipal);
        this.tematica = tematica;
        this.imagen1 = imagen1;
        this.imagen2 = imagen2;
        this.imagen3 = imagen3;
        this.latitud = latitud;
        this.longitud = longitud;
        this.direccion = direccion;
    }

    public Cafeterias(int id_negocio, int id_super, String nombre, String descripcion, LocalTime hora_apertura, LocalTime hora_cierre, int capacidad, byte[] imagenPrincipal) {
        super(id_negocio, id_super, nombre, descripcion, hora_apertura, hora_cierre, capacidad, imagenPrincipal);
    }

    public Cafeterias(String tematica, int id_negocio, String nombre, String descripcion, LocalTime hora_apertura, LocalTime hora_cierre, int capacidad) {
        super(id_negocio, nombre, descripcion, hora_apertura, hora_cierre, capacidad);
        this.tematica = tematica;
    }

    public Cafeterias(String tematica, byte[] imagen1, byte[] imagen2, byte[] imagen3, double latitud, double longitud, String direccion, int id_negocio, String nombre, String descripcion, LocalTime hora_apertura, LocalTime hora_cierre, int capacidad) {
        super(id_negocio, nombre, descripcion, hora_apertura, hora_cierre, capacidad);
        this.tematica = tematica;
        this.imagen1 = imagen1;
        this.imagen2 = imagen2;
        this.imagen3 = imagen3;
        this.latitud = latitud;
        this.longitud = longitud;
        this.direccion = direccion;
    }

 

    public String getTematica() {
        return tematica;
    }

    public void setTematica(String tematica) {
        this.tematica = tematica;
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
    
    
}
