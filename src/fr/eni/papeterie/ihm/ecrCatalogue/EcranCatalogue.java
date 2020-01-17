package fr.eni.papeterie.ihm.ecrCatalogue;

import fr.eni.papeterie.ihm.ArticleController;

import javax.swing.*;
import java.awt.*;

public class EcranCatalogue extends JFrame {
    public EcranCatalogue(){
        super("Catalogue");

        setSize(600, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        Toolkit tk = Toolkit.getDefaultToolkit();
        this.setIconImage(tk.getImage(getClass().getResource("../resources/aim.png")));

        init();
    }

    private void init() {
        JPanel mainContent = new JPanel();
        mainContent.setOpaque(true);

        mainContent.setLayout(new GridLayout(1, 0));
        TableCatalogue tableCatalogue = new TableCatalogue(ArticleController.action().getCatalogue());

        // Création du scroll
        JScrollPane scrollPane = new JScrollPane(tableCatalogue);

        // Ajout dans le panel principal
        mainContent.add(scrollPane);

        this.setContentPane(mainContent);
    }
}
