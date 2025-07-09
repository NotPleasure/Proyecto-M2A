/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.awt.image.BufferedImage;

/**
 *
 * @author MeiRen
 */
public class Imagenes_Lugar {
      private BufferedImage imagen;
      private int size;

    public Imagenes_Lugar() {
    }

    public Imagenes_Lugar(BufferedImage imagen, int size) {
        this.imagen = imagen;
        this.size = size;
    }

    public BufferedImage getImagen() {
        return imagen;
    }

    public void setImagen(BufferedImage imagen) {
        this.imagen = imagen;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
      
}
