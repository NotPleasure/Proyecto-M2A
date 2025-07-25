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
public class Restaurante extends Negocios {

    private String especialidad;
    private String categoria;
    private String tipo_cocina;
    private byte[] imagen1;
    private byte[] imagen2;
    private byte[] imagen3;
    private double latitud;
    private double longitud;
    private String direccion;
    public Restaurante() {
    }

    public Restaurante(String especialidad, String categoria, String tipo_cocina, byte[] imagen1, byte[] imagen2, byte[] imagen3, double latitud, double longitud, String direccion, int id_negocio, String nombre, String descripcion, LocalTime hora_apertura, LocalTime hora_cierre, int capacidad) {
        super(id_negocio, nombre, descripcion, hora_apertura, hora_cierre, capacidad);
        this.especialidad = especialidad;
        this.categoria = categoria;
        this.tipo_cocina = tipo_cocina;
        this.imagen1 = imagen1;
        this.imagen2 = imagen2;
        this.imagen3 = imagen3;
        this.latitud = latitud;
        this.longitud = longitud;
        this.direccion = direccion;
    }

    public Restaurante(int id_negocio, String nombre, LocalTime hora_apertura, LocalTime hora_cierre, byte[] imagenPrincipal) {
        super(id_negocio, nombre, hora_apertura, hora_cierre, imagenPrincipal);
    }
    
    public Restaurante(String especialidad, int id_negocio, String nombre, LocalTime hora_apertura, LocalTime hora_cierre, byte[] imagenPrincipal) {
        super(id_negocio, nombre, hora_apertura, hora_cierre, imagenPrincipal);
        this.especialidad = especialidad;
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
    
    

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getTipo_cocina() {
        return tipo_cocina;
    }

    public void setTipo_cocina(String tipo_cocina) {
        this.tipo_cocina = tipo_cocina;
    }

}
