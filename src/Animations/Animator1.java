package Animations;

import javax.swing.*;
import java.awt.*;

public class Animator1 {

    public static void fadeOut(JFrame frame, Runnable onFinished) {
        Timer timer = new Timer(1, null);  
        final float[] opacity = {1.0f};

        timer.addActionListener(e -> {
            opacity[0] -= 0.25f;  
            if (opacity[0] <= 0f) {
                timer.stop();
                frame.dispose();
                onFinished.run();
            } else {
                frame.setOpacity(opacity[0]);
            }
        });

        timer.start();
    }

    public static void fadeIn(JFrame frame) {
        frame.setOpacity(0f);
        frame.setVisible(true);

        Timer timer = new Timer(1, null);
        final float[] opacity = {0f};

        timer.addActionListener(e -> {
            opacity[0] += 0.25f; 
            if (opacity[0] >= 1f) {
                opacity[0] = 1f;
                timer.stop();
            }
            frame.setOpacity(opacity[0]);
        });

        timer.start();
    }
}
