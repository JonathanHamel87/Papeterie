package fr.eni.papeterie.ihm.ecrCatalogue;

import fr.eni.papeterie.bo.Article;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class TableCatalogue extends JTable {
    public static final int COL_ICON = 0;
    public static final int COL_REFERENCE = 1;
    public static final int COL_DESIGNATION = 2;
    public static final int COL_MARQUE = 3;
    public static final int COL_PRIX = 4;
    public static final int COL_STOCK = 5;

    private List<Article> catalogue;

    private static ImageArticleTableCellRenderer imageArticleTableCellRenderer;

    public TableCatalogue(List<Article> catalogue) {
        super(new TableCatalogueModel(catalogue));

        setPreferredScrollableViewportSize(new Dimension(500, 70));
        setFillsViewportHeight(true);

        this.getColumnModel().getColumn(COL_ICON).setPreferredWidth(50);
        this.getColumnModel().getColumn(COL_REFERENCE).setPreferredWidth(100);
        this.getColumnModel().getColumn(COL_DESIGNATION).setPreferredWidth(200);
        this.getColumnModel().getColumn(COL_MARQUE).setPreferredWidth(100);
        this.getColumnModel().getColumn(COL_PRIX).setPreferredWidth(50);
        this.getColumnModel().getColumn(COL_STOCK).setPreferredWidth(50);

        this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        imageArticleTableCellRenderer = new ImageArticleTableCellRenderer();

        this.getColumnModel().getColumn(COL_ICON).setCellRenderer(imageArticleTableCellRenderer);
        this.setRowHeight(30);
    }
}
