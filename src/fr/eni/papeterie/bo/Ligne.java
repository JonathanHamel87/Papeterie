package fr.eni.papeterie.bo;

public class Ligne {
    /******************** ATTRIBUTES ********************/
    private int qte;
    private Article article;

    /******************** CONSTRUCTORS ********************/
    public Ligne(Article article, int qte) {
        this.qte = qte;
        this.article = article;
    }

    /******************** SETTERS AND GETTERS ********************/
    public int getQte() {
        return qte;
    }

    public void setQte(int qte) {
        this.qte = qte;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public float getPrix(){
        return article.getPrixUnitaire();
    }

    /******************** METHODS ********************/
    @Override
    public String toString() {
        return "Ligne{" +
                "qte=" + qte +
                ", article=" + article +
                '}';
    }
}
