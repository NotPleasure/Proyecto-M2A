/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package proyecto_final_m2a;
import Vista.Login;
import javax.swing.UIManager;
import javax.swing.SwingUtilities;
import Animations.Animator;
import Vista.Ventana_Bienvenida;
import javax.swing.UIDefaults;
/**
 *
 * @author USER
 */
public class Prueba_Proyecto {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        try {
             UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel");

    UIManager.put("Button.focusPainted", Boolean.FALSE);
    UIManager.put("Button.focusWidth", 0);
    UIManager.put("Button.focusColor", new java.awt.Color(0, 0, 0, 0));
    UIManager.put("Button.focusInsets", new java.awt.Insets(0, 0, 0, 0));
    UIManager.put("Button.contentAreaFilled", Boolean.FALSE);
    UIManager.put("Button.border", null);
        } catch (Exception e) {
            e.printStackTrace();
        }
  SwingUtilities.invokeLater(() -> {
    Ventana_Bienvenida login = new Ventana_Bienvenida();
    Animations.Animator.fadeIn(login);  
});
    }
}
