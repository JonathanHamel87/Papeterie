package fr.eni.papeterie.dal;

public class DAOFactory {
    public static ArticleDAO getArticleDAO(){
        ArticleDAO articleDAO = null;
        try {
            articleDAO = (ArticleDAO) Class.forName("fr.eni.papeterie.dal.jdbc.ArticleDAOJdbcImpl").newInstance();
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e){
            e.printStackTrace();
        }
        return articleDAO;
    }
}
