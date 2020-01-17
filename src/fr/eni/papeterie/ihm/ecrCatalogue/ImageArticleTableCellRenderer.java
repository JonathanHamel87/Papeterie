package fr.eni.papeterie.ihm.ecrCatalogue;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class ImageArticleTableCellRenderer implements TableCellRenderer {
    private static ImageIcon imgStylo;
    private static ImageIcon imgRamette;

    public ImageArticleTableCellRenderer() {
        imgStylo = new ImageIcon(getClass().getResource("../resources/pencil.gif"));
        imgRamette = new ImageIcon(getClass().getResource("../resources/ramette.gif"));
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        String type = (String) value;

        JLabel component = new JLabel();

        if (type.equals("S")){
            component.setIcon(imgStylo);
        }else {
            component.setIcon(imgRamette);
        }
        component.setHorizontalAlignment(SwingConstants.CENTER);

        return component;
    }
}
