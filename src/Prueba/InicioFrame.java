/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Prueba;

import javax.swing.*;

public class InicioFrame extends JFrame {

    public InicioFrame(String usuario) {
        setTitle("Inicio");
        setSize(500, 300);
        setUndecorated(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        JLabel bienvenida = new JLabel("Bienvenido, " + usuario);
        bienvenida.setBounds(150, 120, 300, 30);
        add(bienvenida);

        Animator1.fadeIn(this);
    }
}

