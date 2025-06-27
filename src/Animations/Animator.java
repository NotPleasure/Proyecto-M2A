/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Animations;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.Timer;

/**
 *
 * @author USER
 */
public class Animator {

    public static void fadeOut(JFrame frame, Runnable after) {
        Timer t = new Timer(15, null);
        t.addActionListener(new ActionListener() {
            float opacity = 1f;

            public void actionPerformed(ActionEvent e) {
                opacity -= 0.05f;
                if (opacity <= 0f) {
                    t.stop();
                    frame.setVisible(false);
                    after.run();
                } else {
                    frame.setOpacity(opacity);
                }
            }
        });
        t.start();
    }

public static void fadeIn(JFrame frame) {
    frame.setOpacity(0f);               
    frame.setVisible(true);
    frame.setExtendedState(JFrame.NORMAL); 
    frame.toFront();                   

    Timer t = new Timer(15, null);
    t.addActionListener(new ActionListener() {
        float opacity = 0f;

        public void actionPerformed(ActionEvent e) {
            opacity += 0.05f;
            if (opacity >= 1f) {
                frame.setOpacity(1f);   
                t.stop();
            } else {
                frame.setOpacity(opacity);
            }
        }
    });
    t.start();
}
}