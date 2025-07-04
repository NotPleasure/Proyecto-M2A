/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

public class Provincia {
    private int id;
    private String nombre;
    
    // Constructor vacío
    public Provincia() {}
    
    // Constructor sin ID (para crear nueva provincia)
    public Provincia(String nombre) {
        this.nombre = nombre;
    }
    
    // Constructor con ID (para provincias existentes)
    public Provincia(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }
    
    // Getters y Setters
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    @Override
    public String toString() {
        return "Provincia{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}
