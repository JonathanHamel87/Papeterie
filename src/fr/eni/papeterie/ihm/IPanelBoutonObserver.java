package fr.eni.papeterie.ihm;

public interface IPanelBoutonObserver {
    void precedent();
    void nouveau();
    void sauvegarder();
    void supprimer();
    void suivant();
}
