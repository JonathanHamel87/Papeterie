package fr.eni.papeterie.ihm;

import fr.eni.papeterie.bll.BLLException;
import fr.eni.papeterie.bll.CatalogueManager;
import fr.eni.papeterie.bo.Article;
import fr.eni.papeterie.ihm.ecrCatalogue.EcranCatalogue;

import java.util.List;

public class ArticleController {
    private EcranArticle ecranArticle;
    private int indexCatalogue;
    private CatalogueManager catalogueManager;
    private List<Article> catalogue;
    private static ArticleController articleController;

    private ArticleController() {
        try {
            catalogueManager = new CatalogueManager();

            catalogue = catalogueManager.getCatalogue();
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

        EcranCatalogue ecranCatalogue = new EcranCatalogue();
        ecranCatalogue.setVisible(true);
    }

    public void afficherPremierArticle() {
        if (catalogue.size()>0){
            indexCatalogue = 0;
            ecranArticle.afficherArticle(catalogue.get(indexCatalogue));
        }else {
            indexCatalogue = -1;
            ecranArticle.afficherNouveau();
        }
    }

    public void precedent(){
        if (indexCatalogue > 0){
            indexCatalogue--;
            ecranArticle.afficherArticle(catalogue.get(indexCatalogue));
        }
    }

    public void suivant(){
        if (indexCatalogue < catalogue.size()-1){
            indexCatalogue++;
            ecranArticle.afficherArticle(catalogue.get(indexCatalogue));
        }
    }

    public void nouveau(){
        indexCatalogue = catalogue.size();
        ecranArticle.afficherNouveau();
    }

    public void enregistrer(){
        Article article = ecranArticle.getArticle();

        try {
            if (article.getIdArticle() == null){
                catalogueManager.addArticle(article);
                catalogue.add(article);
                ecranArticle.afficherArticle(article);
            }else{
                catalogueManager.updateArticle(article);
                catalogue.set(indexCatalogue, article);
            }
        } catch (BLLException e) {
            ecranArticle.erreurAffichage("Erreur d'enregistrement");
            e.printStackTrace();
        }
    }

    public void supprimer(){
        try {
            catalogueManager.removeArticle(catalogue.get(indexCatalogue).getIdArticle());
            catalogue.remove(indexCatalogue);
        } catch(BLLException e){
            ecranArticle.erreurAffichage("Supression impossible");
            e.printStackTrace();
        }

        if (indexCatalogue < catalogue.size()){
            ecranArticle.afficherArticle(catalogue.get(indexCatalogue));
        }else if (indexCatalogue > 0 ){
            indexCatalogue--;
            ecranArticle.afficherArticle(catalogue.get(indexCatalogue));
        }else{
            ecranArticle.afficherNouveau();
        }
    }


    public List<Article> getCatalogue() {
        return catalogue;
    }
}
