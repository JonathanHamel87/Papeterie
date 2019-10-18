package fr.eni.papeterie.bo;

public class Stylo extends Article {
    /******************** ATTRIBUTES ********************/
    private String couleur;

    /******************** CONSTRUCTORS ********************/
    public Stylo(int idArticle, String ref, String marque, String designation, float pu, int qte, String couleur) {
        super(idArticle, ref, marque, designation, pu, qte);
        this.couleur = couleur;
    }

    public Stylo(String ref, String marque, String designation, float pu, int qte, String couleur) {
        super(ref, marque, designation, pu, qte);
        this.couleur = couleur;
    }

    /******************** GETTERS AND SETTERS ********************/
    public String getCouleur() {
        return couleur;
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }

    /******************** METHODS ********************/
    @Override
    public String toString() {
        return super.toString()+ " Stylo [Couleur="+getCouleur()+"]";
    }
}
