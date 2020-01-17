package fr.eni.papeterie.ihm.ecrCatalogue;

import fr.eni.papeterie.bo.Article;
import fr.eni.papeterie.bo.Stylo;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.util.List;

public class TableCatalogueModel extends AbstractTableModel {
    private List<Article> catalogue;
    private String[] columnNames = {"", "Reference", "Designation", "Marque", "Prix", "Stock"};

    public TableCatalogueModel(List<Article> catalogue) {
        this.catalogue = catalogue;
    }

    public String getColumnName(int column){
        return columnNames[column];
    }

    @Override
    public int getRowCount() {
        return catalogue.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object value = null;
        switch (columnIndex){
            case TableCatalogue.COL_ICON:
                value = catalogue.get(rowIndex) instanceof Stylo? "S":"R";
                break;

            case TableCatalogue.COL_REFERENCE:
                value = catalogue.get(rowIndex).getReference();
                break;

            case TableCatalogue.COL_DESIGNATION:
                value = catalogue.get(rowIndex).getDesignation();
                break;

            case TableCatalogue.COL_MARQUE:
                value = catalogue.get(rowIndex).getMarque();
                break;

            case TableCatalogue.COL_PRIX:
                value = catalogue.get(rowIndex).getPrixUnitaire();
                break;

            case TableCatalogue.COL_STOCK:
                value = catalogue.get(rowIndex).getQteStock();
                break;
        }
        return value;
    }
}
