package fr.eni.papeterie.bll;

import fr.eni.papeterie.bo.Article;
import fr.eni.papeterie.bo.Ramette;
import fr.eni.papeterie.bo.Stylo;
import fr.eni.papeterie.dal.ArticleDAO;
import fr.eni.papeterie.dal.DALException;
import fr.eni.papeterie.dal.DAOFactory;

import java.util.List;

public class CatalogueManager {
    private  static ArticleDAO daoArticle;

    public CatalogueManager() throws BLLException{
        // Instanciation du DAO
        daoArticle = DAOFactory.getArticleDAO();
    }

    /* Récupération du catalogue d'articles */
    public List<Article> getCatalogue() throws BLLException {
        List<Article> catalogue = null;
        try {
            catalogue = daoArticle.selectAll();
        } catch (DALException e) {
           throw new BLLException("Impossible de récupérer le contenu du catalogue - "+e);
        }
        return catalogue;
    }

    /**
     * Ajout d'un article dans le catalogue
     * @param a
     * @throws BLLException
     */
    public void addArticle(Article a) throws BLLException{
        // Vérification si l'article possède un id valide
        if (a.getIdArticle() != null){
            throw new BLLException("Article déjà présent dans le catalogue");
        }
        try {
            validerArticle(a);
            daoArticle.insert(a);
        } catch (DALException e) {
           throw new BLLException("Impossible d'ajouter l'article ",e);
        }
    }

    /**
     * Mise à jour du catalogue
     * @param a
     * @throws BLLException
     */
    public void updateArticle(Article a) throws BLLException{
        try{
            validerArticle(a);
            daoArticle.update(a);
        } catch (DALException e) {
            throw new BLLException("Mise à jour impossible de l'article : "+a,e);
        }
    }

    /**
     * Suppression de l'article dans le catalogue
     * @param index
     * @throws BLLException
     */
    public void removeArticle(int index) throws BLLException{
        try {
            daoArticle.delete(index);
        } catch (DALException e) {
           throw new BLLException("Impossible de supprimer l'article N° "+index,e);
        }
    }

    /**
     * Vérification que l'article n'est pas déjà présent
     * @param a
     * @throws BLLException
     */
    public void validerArticle(Article a) throws BLLException{
        boolean valide = true;
        StringBuffer sb = new StringBuffer();

        //Vérification si l'article est vide
        if (a==null){
            throw new BLLException("L'article est null");
        }

        //Vérification des attributs de l'article
        if (a.getReference() == null || a.getReference().trim().length() == 0){
            sb.append("La référence de l'article est obligatoire.\n");
            valide = false;
        }
        if (a.getMarque() == null || a.getMarque().trim().length() == 0){
            sb.append("La marque de l'article est obligatoire.\n");
            valide = false;
        }
        if (a.getDesignation() == null || a.getDesignation().trim().length() == 0){
            sb.append("La désignation de l'article est obligatoire.\n");
            valide = false;
        }
        if (a instanceof Stylo){
            Stylo stylo = (Stylo) a;
            if (stylo.getCouleur() == null || stylo.getCouleur().trim().length() == 0){
                sb.append("La couleur du stylo est obligatoire.\n");
                valide = false;
            }
        }
        if (a instanceof Ramette && ((Ramette) a).getGrammage() <= 0){
            sb.append("Le grammage de la ramette est obligatoire.\n");
            valide = false;
        }

        // Exception si l'article n'est pas valide
        if (!valide){
            throw new BLLException(sb.toString());
        }
    }

    public Article getArticle(int index) throws BLLException{
        Article article = null;
        try {
            article = daoArticle.selectById(index);
        } catch (DALException e) {
            throw new BLLException("Impossible de sélectionner l'article id - "+index,e);
        }
        return article;
    }
}
