/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

public class ImagenNegocio {

    private int id;
    private int negocioid;
    private byte[] imagen;
    private int tamano;



    public ImagenNegocio(int negocioid, byte[] imagen) {
        this.negocioid = negocioid;
        this.imagen = imagen;
        this.tamano = imagen.length;
    }
    


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNegocioid() {
        return negocioid;
    }

    public void setNegocioid(int negocioid) {
        this.negocioid = negocioid;
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
