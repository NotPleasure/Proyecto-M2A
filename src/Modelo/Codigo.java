/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.time.LocalDateTime;

public class Codigo {
    private String userEmail;
    private String codigo;
    private LocalDateTime creadoEn;

    public Codigo(String userEmail, String codigo, LocalDateTime creadoEn) {
        this.userEmail = userEmail;
        this.codigo = codigo;
        this.creadoEn = creadoEn;
    }

    // getters y setters
    public String getUserEmail() { return userEmail; }
    public void setUserEmail(String userEmail) { this.userEmail = userEmail; }

    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }

    public LocalDateTime getCreadoEn() { return creadoEn; }
    public void setCreadoEn(LocalDateTime creadoEn) { this.creadoEn = creadoEn; }
}
