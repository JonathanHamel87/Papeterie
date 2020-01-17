package fr.eni.papeterie.ihm;

import fr.eni.papeterie.bll.BLLException;
import fr.eni.papeterie.bll.CatalogueManager;
import fr.eni.papeterie.bo.Article;

import java.util.List;

public class ArticleController {
    private EcranArticle ecranArticle;
    private int indexCatalogue;
    private CatalogueManager catalogueManager;
    private List<Article> catalogues;
    private static ArticleController articleController;

    private ArticleController() {
        try {
            catalogueManager = new CatalogueManager();

            catalogues = catalogueManager.getCatalogue();
        } catch (BLLException e) {
            e.printStackTrace();
        }
    }

    public static synchronized ArticleController action() {
        if (articleController == null){
            articleController = new ArticleController();
        }
        return articleController;
    }

    public void runApp(){
        ecranArticle = new EcranArticle();

        afficherPremierArticle();
        ecranArticle.setVisible(true);
    }

    public void afficherPremierArticle() {
        if (catalogues.size()>0){
            indexCatalogue = 0;
            ecranArticle.afficherArticle(catalogues.get(indexCatalogue));
        }else {
            indexCatalogue = -1;
            ecranArticle.afficherNouveau();
        }
    }

    public void precedent(){
        if (indexCatalogue > 0){
            indexCatalogue--;
            ecranArticle.afficherArticle(catalogues.get(indexCatalogue));
        }
    }

    public void suivant(){
        if (indexCatalogue < catalogues.size()-1){
            indexCatalogue++;
            ecranArticle.afficherArticle(catalogues.get(indexCatalogue));
        }
    }

    public void nouveau(){
        indexCatalogue = catalogues.size();
        ecranArticle.afficherNouveau();
    }

    public void enregistrer(){
        Article article = ecranArticle.getArticle();

        try {
            if (article.getIdArticle() == null){
                catalogueManager.addArticle(article);
                catalogues.add(article);
                ecranArticle.afficherArticle(article);
            }else{
                catalogueManager.updateArticle(article);
                catalogues.set(indexCatalogue, article);
            }
        } catch (BLLException e) {
            ecranArticle.erreurAffichage("Erreur d'enregistrement");
            e.printStackTrace();
        }
    }

    public void supprimer(){
        try {
            catalogueManager.removeArticle(indexCatalogue);
            catalogues.remove(indexCatalogue);
        } catch(BLLException e){
            ecranArticle.erreurAffichage("Supression impossible");
            e.printStackTrace();
        }

        if (indexCatalogue < catalogues.size()){
            ecranArticle.afficherArticle(catalogues.get(indexCatalogue));
        }else if (indexCatalogue > 0 ){
            indexCatalogue--;
            ecranArticle.afficherArticle(catalogues.get(indexCatalogue));
        }else{
            ecranArticle.afficherNouveau();
        }
    }


}
