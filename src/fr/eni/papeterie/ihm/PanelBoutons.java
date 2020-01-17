package fr.eni.papeterie.ihm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class PanelBoutons extends JPanel {
    private List<IPanelBoutonObserver> observers;

    private JButton btnPrecedent;
    private JButton btnNouveau;
    private JButton btnEnregistrer;
    private JButton btnSupprimer;
    private JButton btnSuivant;

    public PanelBoutons(){
        setLayout(new FlowLayout());

        add(getBtnPrecedent());
        add(getBtnNouveau());
        add(getBtnEnregistrer());
        add(getBtnSupprimer());
        add(getBtnSuivant());

        observers = new ArrayList<IPanelBoutonObserver>();
    }

    /************************************* GETTERS *************************************/
    public JButton getBtnPrecedent() {
        if (btnPrecedent == null){
            btnPrecedent = new JButton();
            ImageIcon img = new ImageIcon(this.getClass().getResource("resources/Back24.gif"));
            btnPrecedent.setIcon(img);
            btnPrecedent.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ArticleController.action().precedent();
                }
            });
        }
        return btnPrecedent;
    }

    public JButton getBtnNouveau() {
        if (btnNouveau == null){
            btnNouveau = new JButton();
            ImageIcon img = new ImageIcon(this.getClass().getResource("resources/New24.gif"));
            btnNouveau.setIcon(img);

            btnNouveau.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ArticleController.action().nouveau();
                }
            });
        }
        return btnNouveau;
    }

    public JButton getBtnEnregistrer() {
        if (btnEnregistrer == null){
            btnEnregistrer = new JButton();
            ImageIcon img = new ImageIcon(this.getClass().getResource("resources/Save24.gif"));
            btnEnregistrer.setIcon(img);

            btnEnregistrer.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ArticleController.action().enregistrer();
                }
            });
        }
        return btnEnregistrer;
    }

    public JButton getBtnSupprimer() {
        if (btnSupprimer == null){
            btnSupprimer = new JButton();
            ImageIcon img = new ImageIcon(this.getClass().getResource("resources/Delete24.gif"));
            btnSupprimer.setIcon(img);

            btnSupprimer.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ArticleController.action().supprimer();
                }
            });
        }
        return btnSupprimer;
    }

    public JButton getBtnSuivant() {
        if (btnSuivant == null){
            btnSuivant = new JButton();
            ImageIcon img = new ImageIcon(this.getClass().getResource("resources/Forward24.gif"));
            btnSuivant.setIcon(img);

            btnSuivant.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ArticleController.action().suivant();
                }
            });
        }
        return btnSuivant;
    }

    /************************************* METHODS *************************************/
    public void addPanelBoutonObserver(IPanelBoutonObserver observer){
        observers.add(observer);
    }
}
