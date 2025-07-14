/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

public class ImagenLugar {

    private int id;
    private int lugarInteresId;
    private byte[] imagen;
    private int tamano;

    public ImagenLugar() {
    }

    public ImagenLugar(int lugarInteresId, byte[] imagen) {
        this.lugarInteresId = lugarInteresId;
        this.imagen = imagen;
        this.tamano = imagen.length;
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

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
        this.tamano = (imagen != null) ? imagen.length : 0;
    }

    public int getTamano() {
        return tamano;
    }

    public void setTamano(int tamano) {
        this.tamano = tamano;
    }
}
