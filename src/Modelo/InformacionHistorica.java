/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

public class InformacionHistorica {

    private int id;
    private int lugarInteresId;
    private String descripcion;

    public InformacionHistorica() {
    }

    public InformacionHistorica(int lugarInteresId, String descripcion) {
        this.lugarInteresId = lugarInteresId;
        this.descripcion = descripcion;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
