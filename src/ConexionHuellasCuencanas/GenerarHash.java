/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ConexionHuellasCuencanas;

import org.mindrot.jbcrypt.BCrypt;

public class GenerarHash {
    public static void main(String[] args) {
        String contraseña = "admin123";
        String hash = BCrypt.hashpw(contraseña, BCrypt.gensalt());
        System.out.println("Contraseña hasheada: " + hash);
    }
}

