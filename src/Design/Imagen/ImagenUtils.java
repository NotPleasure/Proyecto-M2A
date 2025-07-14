package Design.Imagen;

import javax.swing.*;
import java.io.*;
import java.nio.file.Files;

public class ImagenUtils {

    public static byte[] seleccionarYConvertirImagen(JLabel destino) {
        JFileChooser selector = new JFileChooser();
        selector.setDialogTitle("Seleccionar imagen");
        selector.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Imágenes", "jpg", "jpeg", "png"));

        int resultado = selector.showOpenDialog(null);
        if (resultado == JFileChooser.APPROVE_OPTION) {
            File archivo = selector.getSelectedFile();
            try {
                byte[] bytes = Files.readAllBytes(archivo.toPath());
                if (destino instanceof CircularImageLabel) {
                    ((CircularImageLabel) destino).setImagenDesdeBytes(bytes);
                } else if (destino instanceof RoundedImageLabel) {
                    ((RoundedImageLabel) destino).setImagenDesdeBytes(bytes);
                } else {
                    destino.setIcon(new ImageIcon(bytes));
                }
                return bytes;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static void cargarDesdeBytes(byte[] datos, JLabel destino) {
        if (destino instanceof CircularImageLabel) {
            ((CircularImageLabel) destino).setImagenDesdeBytes(datos);
        } else if (destino instanceof RoundedImageLabel) {
            ((RoundedImageLabel) destino).setImagenDesdeBytes(datos);
        } else if (datos != null) {
            destino.setIcon(new ImageIcon(datos));
        }
    }
}
