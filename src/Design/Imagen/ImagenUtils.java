/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Design.Imagen;

import Design.Imagen.CircularImageLabel;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.nio.file.Files;

public class ImagenUtils {

    public static byte[] seleccionarYConvertirImagen(CircularImageLabel destino) {
        JFileChooser selector = new JFileChooser();
        selector.setDialogTitle("Seleccionar imagen");
        selector.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Imágenes", "jpg", "jpeg", "png"));

        int resultado = selector.showOpenDialog(null);
        if (resultado == JFileChooser.APPROVE_OPTION) {
            File archivo = selector.getSelectedFile();
            try {
                byte[] bytes = Files.readAllBytes(archivo.toPath());
                destino.setImagenDesdeBytes(bytes);
                return bytes;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static void cargarDesdeBytes(byte[] datos, CircularImageLabel destino) {
        destino.setImagenDesdeBytes(datos);
    }
}
