package fr.eni.papeterie.bo;

public class Ramette extends Article {
    /******************** ATTRIBUTES ********************/
    private int grammage;

    /******************** CONSTRUCTORS ********************/
    public Ramette() {
    }

    public Ramette(Integer idArticle, String ref, String marque, String designation, float pu, int qte, int grammage) {
        super(idArticle, ref, marque, designation, pu, qte);
        this.grammage = grammage;
    }

    public Ramette(String ref, String marque, String designation, float pu, int qte, int grammage) {
        super(ref, marque, designation, pu, qte);
        this.grammage = grammage;
    }

    /******************** GETTERS AND SETTERS ********************/
    public int getGrammage() {
        return grammage;
    }

    public void setGrammage(int grammage) {
        this.grammage = grammage;
    }

    /******************** METHODS ********************/
    @Override
    public String toString() {
        return super.toString()+ " Rammette [grammage="+getGrammage()+"]";
    }
}
