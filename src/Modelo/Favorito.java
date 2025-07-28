/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.sql.Timestamp;

public class Favorito {
    private int id;
    private int idPersona;
    private int idLugarInteres;
    private Timestamp fechaAgregado;

    public Favorito() {}

    public Favorito(int id, int idPersona, int idLugarInteres, Timestamp fechaAgregado) {
        this.id = id;
        this.idPersona = idPersona;
        this.idLugarInteres = idLugarInteres;
        this.fechaAgregado = fechaAgregado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
    }

    public int getIdLugarInteres() {
        return idLugarInteres;
    }

    public void setIdLugarInteres(int idLugarInteres) {
        this.idLugarInteres = idLugarInteres;
    }

    public Timestamp getFechaAgregado() {
        return fechaAgregado;
    }

    public void setFechaAgregado(Timestamp fechaAgregado) {
        this.fechaAgregado = fechaAgregado;
    }
}
