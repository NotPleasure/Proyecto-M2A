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
public class Negocios {
    private int id_negocio;
    private int id_super;
    private String nombre;
    private String descripcion;
    private LocalTime hora_apertura;
    private LocalTime hora_cierre;
    private int capacidad;
    private byte[] imagenPrincipal;

    public Negocios(int id_negocio, int id_super, String nombre, String descripcion, LocalTime hora_apertura, LocalTime hora_cierre, int capacidad, byte[] imagenPrincipal) {
        this.id_negocio = id_negocio;
        this.id_super = id_super;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.hora_apertura = hora_apertura;
        this.hora_cierre = hora_cierre;
        this.capacidad = capacidad;
        this.imagenPrincipal = imagenPrincipal;
    }
    
    public Negocios(int id_negocio, String nombre, String descripcion, LocalTime hora_apertura, LocalTime hora_cierre, int capacidad) {
        this.id_negocio = id_negocio;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.hora_apertura = hora_apertura;
        this.hora_cierre = hora_cierre;
        this.capacidad = capacidad;
    }

    public Negocios(int id_negocio, String nombre, LocalTime hora_apertura, LocalTime hora_cierre, byte[] imagenPrincipal) {
        this.id_negocio = id_negocio;
        this.nombre = nombre;
        this.hora_apertura = hora_apertura;
        this.hora_cierre = hora_cierre;
        this.imagenPrincipal = imagenPrincipal;
    }

    public Negocios(int id_negocio, String nombre, String descripcion, LocalTime hora_apertura, LocalTime hora_cierre, int capacidad, byte[] imagenPrincipal) {
        this.id_negocio = id_negocio;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.hora_apertura = hora_apertura;
        this.hora_cierre = hora_cierre;
        this.capacidad = capacidad;
        this.imagenPrincipal = imagenPrincipal;
    }

    public Negocios() {
    }

    public byte[] getImagenPrincipal() {
        return imagenPrincipal;
    }

    public void setImagenPrincipal(byte[] imagenPrincipal) {
        this.imagenPrincipal = imagenPrincipal;
    }

    public int getId_negocio() {
        return id_negocio;
    }

    public void setId_negocio(int id_negocio) {
        this.id_negocio = id_negocio;
    }

    public int getId_super() {
        return id_super;
    }

    public void setId_super(int id_super) {
        this.id_super = id_super;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalTime getHora_apertura() {
        return hora_apertura;
    }

    public void setHora_apertura(LocalTime hora_apertura) {
        this.hora_apertura = hora_apertura;
    }

    public LocalTime getHora_cierre() {
        return hora_cierre;
    }

    public void setHora_cierre(LocalTime hora_cierre) {
        this.hora_cierre = hora_cierre;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }
    
    
}
