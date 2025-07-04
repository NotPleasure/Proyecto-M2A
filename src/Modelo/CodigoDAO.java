/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import ConexionHuellasCuencanas.ConexionHuellasCuencanas;
import java.sql.*;
import java.time.LocalDateTime;

public class CodigoDAO {

    private String codigo;
    static String[] a = new String[2];

    public CodigoDAO(String codigo) {
        this.codigo = codigo;
    }

    public CodigoDAO() {
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
        a[0] = codigo;

    }

    public static String generarCodigo() {
        int numero = (int) (Math.random() * 900000) + 100000;
        return String.valueOf(numero);
    }

    public boolean validarCodigo(String codigoingresado) {
        System.out.println(a[0]);
        System.out.println(codigoingresado);
        String codigos = a[0];
        boolean valida = false;
        if (codigoingresado.equalsIgnoreCase(codigos) == true) {

            valida = true;
        } else {
            valida = false;
        }
        return valida;
    }
}
