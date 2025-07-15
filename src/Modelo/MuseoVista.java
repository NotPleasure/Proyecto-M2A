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
public class MuseoVista {
    private String nombre;
    private LocalTime horaApertura;
    private LocalTime horaCierre;
    private byte[] imagenPrincipal;

    public MuseoVista(String nombre, LocalTime horaApertura, LocalTime horaCierre, byte[] imagenPrincipal) {
        this.nombre = nombre;
        this.horaApertura = horaApertura;
        this.horaCierre = horaCierre;
        this.imagenPrincipal = imagenPrincipal;
    }

    public MuseoVista() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalTime getHoraApertura() {
        return horaApertura;
    }

    public void setHoraApertura(LocalTime horaApertura) {
        this.horaApertura = horaApertura;
    }

    public LocalTime getHoraCierre() {
        return horaCierre;
    }

    public void setHoraCierre(LocalTime horaCierre) {
        this.horaCierre = horaCierre;
    }

    public byte[] getImagenPrincipal() {
        return imagenPrincipal;
    }

    public void setImagenPrincipal(byte[] imagenPrincipal) {
        this.imagenPrincipal = imagenPrincipal;
    }
    
}
