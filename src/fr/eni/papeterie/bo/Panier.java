package fr.eni.papeterie.bo;

import java.util.ArrayList;
import java.util.List;

public class Panier {
    /******************** ATTRIBUTES ********************/
    private float montant;
    private List<Ligne>lignesPanier;

    /******************** CONSTRUCTORS ********************/
    public Panier() {
        lignesPanier = new ArrayList<Ligne>();
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
        StringBuffer sb = new StringBuffer();
        sb.append("Panier : \n\n");
        for (Ligne ligne : lignesPanier) {
            if (ligne !=null){
                sb.append("ligne "+lignesPanier.indexOf(ligne)+ " :\t");
                sb.append(ligne.toString());
                sb.append("\n");
            }
        }
        sb.append("\nValeur du panier : " + getMontant());
        sb.append("\n\n");

        return sb.toString();
    }

    public final Ligne getLigne(int index){
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
