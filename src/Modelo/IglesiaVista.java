/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.time.LocalTime;

public class IglesiaVista {
    private int id;
    private String nombre;
    private LocalTime horaApertura;
    private LocalTime horaCierre;
    private byte[] imagenPrincipal;


    public IglesiaVista(int id, String nombre, LocalTime horaApertura, LocalTime horaCierre, byte[] imagenPrincipal) {
        this.id = id;
        this.nombre = nombre;
        this.horaApertura = horaApertura;
        this.horaCierre = horaCierre;
        this.imagenPrincipal = imagenPrincipal;
    }
    
    public IglesiaVista() {
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

    public LocalTime getHoraApertura() {
        return horaApertura;
    }

    public LocalTime getHoraCierre() {
        return horaCierre;
    }

    public byte[] getImagenPrincipal() {
        return imagenPrincipal;
    }
}
