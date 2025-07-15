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
public class Museo {
     private int id;
    private int lugarInteresId;
    private LocalTime horaApertura;
    private LocalTime horaCierre;

    public Museo(int id, int lugarInteresId, LocalTime horaApertura, LocalTime horaCierre) {
        this.id = id;
        this.lugarInteresId = lugarInteresId;
        this.horaApertura = horaApertura;
        this.horaCierre = horaCierre;
    }

    public Museo(int lugarInteresId, LocalTime horaApertura, LocalTime horaCierre) {
        this.lugarInteresId = lugarInteresId;
        this.horaApertura = horaApertura;
        this.horaCierre = horaCierre;
    }
    
    public Museo() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLugarInteresId() {
        return lugarInteresId;
    }

    public void setLugarInteresId(int lugarInteresId) {
        this.lugarInteresId = lugarInteresId;
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
    
}
