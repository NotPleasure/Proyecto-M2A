/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

public class UbicacionNegocio {

    private int id;
    private int lugarInteresId;
    private String direccion;
    private double latitud;
    private double longitud;

    public UbicacionNegocio() {
    }

    public UbicacionNegocio(int lugarInteresId, String direccion, double latitud, double longitud) {
        this.lugarInteresId = lugarInteresId;
        this.direccion = direccion;
        this.latitud = latitud;
        this.longitud = longitud;
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

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
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
}
