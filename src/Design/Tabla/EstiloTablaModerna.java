package Design.Tabla;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;

public class EstiloTablaModerna extends DefaultTableCellRenderer {

    private final Color colorFondoClaro = new Color(245, 247, 250);
    private final Color colorFondoOscuro = new Color(230, 235, 240);
    private final Color colorSeleccion = new Color(100, 150, 240); 
    private final Color colorTextoNormal = new Color(40, 40, 40);
    private final Color colorTextoSeleccion = Color.WHITE;
    private final Border bordeSeleccion = BorderFactory.createMatteBorder(0, 0, 3, 0, colorSeleccion);

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {

        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        Color fondo = (row % 2 == 0) ? colorFondoClaro : colorFondoOscuro;
        setBackground(isSelected ? colorSeleccion : fondo);

        setForeground(isSelected ? colorTextoSeleccion : colorTextoNormal);

        setFont(isSelected ? getFont().deriveFont(Font.BOLD) : getFont().deriveFont(Font.PLAIN));

        setHorizontalAlignment(CENTER);

        setBorder(isSelected ? bordeSeleccion : noFocusBorder);

        return this;
    }
}
