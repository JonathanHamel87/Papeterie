package fr.eni.papeterie.ihm;

import fr.eni.papeterie.bo.Article;
import fr.eni.papeterie.bo.Ramette;
import fr.eni.papeterie.bo.Stylo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EcranArticle extends JFrame {
    /* Formulaire de saisie */
    private JLabel lblReference;
    private JLabel lblDesignation;
    private JLabel lblMarque;
    private JLabel lblStock;
    private JLabel lblPrix;

    private JTextField txtReference;
    private JTextField txtDesignation;
    private JTextField txtMarque;
    private JTextField txtStock;
    private JTextField txtPrix;

    private JPanel panelType;
    private JLabel lblType;
    private JRadioButton radioRamette;
    private JRadioButton radioStylo;

    private JPanel panelGrammage;
    private JLabel lblGrammage;
    private JCheckBox chk80;
    private JCheckBox chk100;

    private JLabel lblCouleur;
    private JComboBox<String> cboCouleur;

    /* Boutons */
    private PanelBoutons panelBoutons;

    private Integer idArticleAfficher;

    public EcranArticle(){
        // Définition action croix fermeture
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        // Taille de la fenêtre
        this.setSize(400,320);
        // Positionnement de la fenêtre
        this.setLocationRelativeTo(null);
        // Fenêtre redimensionnable
        this.setResizable(false);
        // Nom de la fenêtre
        this.setTitle("Information article");
        
        // Appel de l'intialisation du contenu de la fenêtre
        initIHM();
    }

    private void initIHM() {
        // Création du conteneur général
        JPanel panelPrincipal = new JPanel();
        // Définition du Layout
        panelPrincipal.setLayout(new GridBagLayout());
        // Ajout de la contrainte GridBagLayout
        GridBagConstraints gbc = new GridBagConstraints();

        /////////////////////////// Ligne 1 /////////////////////////////
        gbc.gridy = 0;

        // Ajout du JLabel reference
        gbc.gridx = 0;
        panelPrincipal.add(getLblReference(), gbc);

        // Ajout du JtextField reference
        gbc.gridx = 1;
        panelPrincipal.add(getTxtReference(), gbc);

        /////////////////////////// Ligne 2 /////////////////////////////
        gbc.gridy = 1;

        gbc.gridx = 0;
        panelPrincipal.add(getLblDesignation(), gbc);

        gbc.gridx = 1;
        panelPrincipal.add(getTxtDesignation(), gbc);

        /////////////////////////// Ligne 3 /////////////////////////////
        gbc.gridy = 2;

        gbc.gridx = 0;
        panelPrincipal.add(getLblMarque(), gbc);

        gbc.gridx = 1;
        panelPrincipal.add(getTxtMarque(), gbc);

        /////////////////////////// Ligne 4 /////////////////////////////
        gbc.gridy = 3;

        gbc.gridx = 0;
        panelPrincipal.add(getLblStock(), gbc);

        gbc.gridx = 1;
        panelPrincipal.add(getTxtStock(), gbc);

        /////////////////////////// Ligne 5 /////////////////////////////
        gbc.gridy = 4;

        gbc.gridx = 0;
        panelPrincipal.add(getLblPrix(), gbc);

        gbc.gridx = 1;
        panelPrincipal.add(getTxtPrix(), gbc);

        /////////////////////////// Ligne 6 /////////////////////////////
        gbc.gridy = 5;

        gbc.gridx = 0;
        panelPrincipal.add(getLblType(), gbc);

        gbc.gridx = 1;
        panelPrincipal.add(getPanelType(), gbc);

        /////////////////////////// Ligne 7 /////////////////////////////
        gbc.gridy = 6;

        gbc.gridx = 0;
        panelPrincipal.add(getLblGrammage(), gbc);

        gbc.gridx = 1;
        panelPrincipal.add(getPanelGrammage(), gbc);

        /////////////////////////// Ligne 8 /////////////////////////////
        gbc.gridy = 7;

        gbc.gridx = 0;
        panelPrincipal.add(getLblCouleur(), gbc);

        gbc.gridx = 1;
        panelPrincipal.add(getCboCouleur(), gbc);

        /////////////////////////// Ligne 9 /////////////////////////////
        gbc.gridy = 8;

        gbc.gridx = 0;
        gbc.gridwidth = 2;
        panelPrincipal.add(getPanelBoutons(), gbc);

        // Ajout de l'ensemble dans l'écran principal
        this.setContentPane(panelPrincipal);
    }



    /************************************* GETTERS *************************************/
    public JLabel getLblReference() {
        if (lblReference == null){
            lblReference = new JLabel("Référence");
        }
        return lblReference;
    }

    public JLabel getLblDesignation() {
        if (lblDesignation == null){
            lblDesignation = new JLabel("Désignation");
        }
        return lblDesignation;
    }

    public JLabel getLblMarque() {
        if (lblMarque == null){
            lblMarque = new JLabel("Marque");
        }
        return lblMarque;
    }

    public JLabel getLblStock() {
        if (lblStock == null){
            lblStock = new JLabel("Stock");
        }
        return lblStock;
    }

    public JLabel getLblPrix() {
        if (lblPrix == null){
            lblPrix = new JLabel("Prix");
        }
        return lblPrix;
    }

    public JLabel getLblType() {
        if (lblType == null){
            lblType = new JLabel("Type");
        }
        return lblType;
    }

    public JLabel getLblGrammage() {
        if (lblGrammage == null){
            lblGrammage = new JLabel("Grammage");
        }
        return lblGrammage;
    }

    public JLabel getLblCouleur() {
        if (lblCouleur == null){
            lblCouleur = new JLabel("Couleur");
        }
        return lblCouleur;
    }

    public JTextField getTxtReference() {
        if (txtReference == null){
            txtReference = new JTextField(30);
        }
        return txtReference;
    }

    public JTextField getTxtDesignation() {
        if (txtDesignation == null){
            txtDesignation = new JTextField(30);
        }
        return txtDesignation;
    }

    public JTextField getTxtMarque() {
        if (txtMarque == null){
            txtMarque = new JTextField(30);
        }
        return txtMarque;
    }

    public JTextField getTxtStock() {
        if (txtStock == null){
            txtStock = new JTextField(30);
        }
        return txtStock;
    }

    public JTextField getTxtPrix() {
        if (txtPrix == null){
            txtPrix = new JTextField(30);
        }
        return txtPrix;
    }

    public JPanel getPanelType() {
        if (panelType == null){
            panelType = new JPanel();
            panelType.setLayout(new BoxLayout(panelType, BoxLayout.Y_AXIS));
            panelType.add(getRadioRamette());
            panelType.add(getRadioStylo());

            ButtonGroup bg = new ButtonGroup();
            bg.add(getRadioRamette());
            bg.add(getRadioStylo());
        }
        return panelType;
    }

    public JRadioButton getRadioRamette() {
        if (radioRamette == null){
            radioRamette = new JRadioButton("Ramette");

            radioRamette.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    getChk80().setEnabled(true);
                    getChk100().setEnabled(true);
                    getCboCouleur().setEnabled(false);
                }
            });
        }
        return radioRamette;
    }

    public JRadioButton getRadioStylo() {
        if (radioStylo == null){
            radioStylo = new JRadioButton("Stylo");

            radioStylo.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    getCboCouleur().setEnabled(true);
                    getChk80().setEnabled(false);
                    getChk100().setEnabled(false);
                }
            });
        }
        return radioStylo;
    }

    public JPanel getPanelGrammage() {
        if (panelGrammage == null){
            panelGrammage = new JPanel();
            panelGrammage.setLayout(new BoxLayout(panelGrammage, BoxLayout.Y_AXIS));
            panelGrammage.add(getChk80());
            panelGrammage.add(getChk100());

            ButtonGroup bg = new ButtonGroup();
            bg.add(getChk80());
            bg.add(getChk100());
        }
        return panelGrammage;
    }

    public JCheckBox getChk80() {
        if (chk80 == null){
            chk80 = new JCheckBox("80");
        }
        return chk80;
    }

    public JCheckBox getChk100() {
        if (chk100 == null){
            chk100 = new JCheckBox("100");
        }
        return chk100;
    }

    public JComboBox<String> getCboCouleur() {
        if (cboCouleur == null){
            String[]couleurs = {"bleu","vert", "rouge", "noir"};
            cboCouleur = new JComboBox<String>(couleurs);
        }
        return cboCouleur;
    }

    public JPanel getPanelBoutons() {
        if(panelBoutons == null){
            panelBoutons = new PanelBoutons();
            panelBoutons.addPanelBoutonObserver(new IPanelBoutonObserver() {
                @Override
                public void precedent() {
                    ArticleController.action().precedent();
                }

                @Override
                public void nouveau() {
                    ArticleController.action().nouveau();
                }

                @Override
                public void sauvegarder() {
                    ArticleController.action().enregistrer();
                }

                @Override
                public void supprimer() {
                    ArticleController.action().supprimer();
                }

                @Override
                public void suivant() {
                    ArticleController.action().suivant();
                }
            });


        }
        return panelBoutons;
    }


    /************************************* METHODS *************************************/
    public void afficherArticle(Article article) {
        idArticleAfficher = article.getIdArticle();
        getTxtReference().setText(article.getReference() + "");
        getTxtDesignation().setText(article.getDesignation() + "");
        getTxtMarque().setText(article.getMarque() + "");
        getTxtStock().setText(new Integer(article.getQteStock()) + "");
        getTxtPrix().setText(String.valueOf(article.getPrixUnitaire()) + "");

        // Stylo
        if (article.getClass().equals(Stylo.class)){
            // Activation du bouton radio
            getRadioStylo().setSelected(true);
            // activation du choix de la couleur
            getCboCouleur().setEnabled(true);
            // Sélection de la couleur
            getCboCouleur().setSelectedItem(((Stylo) article).getCouleur());
            // Désactivation des cases à cocher des Ramettes
            getChk80().setEnabled(false);
            getChk100().setEnabled(false);
        }
        // Ramette
        else{
            getRadioRamette().setSelected(true);
            getChk80().setEnabled(true);
            getChk100().setEnabled(true);
            // Sélection grammage
            getChk80().setSelected(((Ramette)article).getGrammage() == 80);
            getChk100().setSelected(((Ramette)article).getGrammage() == 100);
            // Désactivation de la couleur
            getCboCouleur().setSelectedItem(null);
            getCboCouleur().setEnabled(false);
        }
        // Activation en cas de nouvel article
        getRadioStylo().setEnabled(article.getIdArticle() == null);
        getRadioRamette().setEnabled(article.getIdArticle() == null);
    }

    public void afficherNouveau() {
        Stylo stylo = new Stylo(null, "", "", "", 0.0f, 0, "bleu");
        afficherArticle(stylo);
    }

    public Article getArticle() {
        Article article = null;
        if (getRadioStylo().isSelected()){
            article = new Stylo();
        }else{
            article = new Ramette();
        }

        try{
            article.setIdArticle(idArticleAfficher);
            article.setReference(getTxtReference().getText());
            article.setDesignation(getTxtDesignation().getText());
            article.setMarque(getTxtMarque().getText());
            article.setQteStock(Integer.parseInt(getTxtStock().getText()));
            article.setPrixUnitaire(Float.parseFloat(getTxtPrix().getText()));
            if (getCboCouleur().isEnabled()){
                ((Stylo) article).setCouleur((String) getCboCouleur().getSelectedItem());
            }else{
                ((Ramette)article).setGrammage(getChk80().isSelected()?80:100);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return article;
    }

    public void erreurAffichage(String s) {
        JOptionPane.showMessageDialog(EcranArticle.this, s, "", JOptionPane.ERROR_MESSAGE);
    }
}
