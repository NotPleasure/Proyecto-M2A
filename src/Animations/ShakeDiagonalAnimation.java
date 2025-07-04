/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Animations;

import javax.swing.*;
import java.awt.event.*;

public class ShakeDiagonalAnimation {
    private Timer timer;
    private int originalX, originalY;
    private int shakeDistanceX = 1; // distancia horizontal
    private int shakeDistanceY = 1; // distancia vertical
    private int shakeCount = 0;
    private int maxShakes = 3; // total pasos ida y vuelta
    private JLabel label;

    public ShakeDiagonalAnimation(JLabel label) {
        this.label = label;
        this.originalX = label.getX();
        this.originalY = label.getY();
    }

    public void start() {
        timer = new Timer(100, new ActionListener() { 
            public void actionPerformed(ActionEvent e) {
                int offsetX = (shakeCount % 4 == 0 || shakeCount % 4 == 3) ? -shakeDistanceX : shakeDistanceX;
                int offsetY = (shakeCount % 2 == 0) ? -shakeDistanceY : shakeDistanceY;

                label.setLocation(originalX + offsetX, originalY + offsetY);

                shakeCount++;

                if (shakeCount > maxShakes) {
                    timer.stop();
                    label.setLocation(originalX, originalY); 
                }
            }
        });
        timer.start();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Shake Diagonal Animation");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 250);
        frame.setLayout(null);

        JLabel label = new JLabel("¡Tambaleo diagonal!");
        label.setBounds(140, 100, 150, 30);
        frame.add(label);

        frame.setVisible(true);

        ShakeDiagonalAnimation shake = new ShakeDiagonalAnimation(label);
        shake.start();
    }
}

