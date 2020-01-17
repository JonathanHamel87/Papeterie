package fr.eni.papeterie.ihm;

import javax.swing.*;
import java.awt.*;

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
    private JPanel panelBoutons;
    private JButton btnPrecedent;
    private JButton btnNouveau;
    private JButton btnEnregistrer;
    private JButton btnSupprimer;
    private JButton btnSuivant;

    private Integer idArticleAfficher;

    public EcranArticle(){
        // Définition action croix fermeture
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        // Taille de la fenêtre
        this.setSize(400,400);
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
        /*gbc.gridy = 7;

        gbc.gridx = 0;
        panelPrincipal.add(getLblCouleur(), gbc);

        gbc.gridx = 1;
        panelPrincipal.add(getCboCouleur(), gbc);

        /////////////////////////// Ligne 9 /////////////////////////////
        gbc.gridy = 8;

        gbc.gridx = 0;
        gbc.gridwidth = 2;
        panelPrincipal.add(getPanelBoutons(), gbc);*/

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
        }
        return radioRamette;
    }

    public JRadioButton getRadioStylo() {
        if (radioStylo == null){
            radioStylo = new JRadioButton("Stylo");
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
        return cboCouleur;
    }

    public JPanel getPanelBoutons() {
        return panelBoutons;
    }

    public JButton getBtnPrecedent() {
        return btnPrecedent;
    }

    public JButton getBtnNouveau() {
        return btnNouveau;
    }

    public JButton getBtnEnregistrer() {
        return btnEnregistrer;
    }

    public JButton getBtnSupprimer() {
        return btnSupprimer;
    }

    public JButton getBtnSuivant() {
        return btnSuivant;
    }

    public Integer getIdArticleAfficher() {
        return idArticleAfficher;
    }
}
