package fr.eni.papeterie.bo;

import java.util.List;

public class Panier {
    /******************** ATTRIBUTES ********************/
    private float montant;
    private List<Ligne>lignesPanier;

    /******************** CONSTRUCTORS ********************/
    public Panier() {
    }

    /******************** GETTERS AND SETTERS ********************/
    public float getMontant() {
        return montant;
    }

    public void setMontant(float montant) {
        this.montant = montant;
    }

    public List<Ligne> getLignesPanier() {
        return lignesPanier;
    }

    public void setLignesPanier(List<Ligne> lignesPanier) {
        this.lignesPanier = lignesPanier;
    }
    /******************** METHODS ********************/
    @Override
    public String toString() {
        return "Panier{" +
                "montant=" + montant +
                ", lignesPanier=" + lignesPanier +
                '}';
    }

    public Ligne getLigne(int index){
        return lignesPanier.get(index);
    }

    public void addLigne(Article article, int qte){
        Ligne newLigne = new Ligne(article, qte);
        lignesPanier.add(newLigne);
    }

    public void updateLigne(int index, int newQte){
        this.getLigne(index).setQte(newQte);
    }

    public void removeLigne(int index){
        lignesPanier.remove(index);
    }
}
