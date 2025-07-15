/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author MeiRen
 */
public class Parque {
     private int id;
    private int lugarInteresId;
    private String entidad_gestora;
    private float superficie;

    public Parque() {
    }

    public Parque(int id, int lugarInteresId, String entidad_gestora, float superficie) {
        this.id = id;
        this.lugarInteresId = lugarInteresId;
        this.entidad_gestora = entidad_gestora;
        this.superficie = superficie;
    }

    public Parque(int lugarInteresId, String entidad_gestora, float superficie) {
        this.lugarInteresId = lugarInteresId;
        this.entidad_gestora = entidad_gestora;
        this.superficie = superficie;
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
    
}
