/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author MeiRen
 */
public class Iglesia extends Lugar_Interes {
    private String hora_apertura;
    private String hora_cierre;

    public Iglesia() {
    }

    public Iglesia(String hora_apertura, String hora_cierre) {
        this.hora_apertura = hora_apertura;
        this.hora_cierre = hora_cierre;
    }

    public String getHora_apertura() {
        return hora_apertura;
    }

    public void setHora_apertura(String hora_apertura) {
        this.hora_apertura = hora_apertura;
    }

    public String getHora_cierre() {
        return hora_cierre;
    }

    public void setHora_cierre(String hora_cierre) {
        this.hora_cierre = hora_cierre;
    }
    
    
}
