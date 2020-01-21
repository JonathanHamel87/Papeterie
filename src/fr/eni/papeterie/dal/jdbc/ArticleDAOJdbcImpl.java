package fr.eni.papeterie.dal.jdbc;

import fr.eni.papeterie.bo.Article;
import fr.eni.papeterie.bo.Ramette;
import fr.eni.papeterie.bo.Stylo;
import fr.eni.papeterie.dal.DALException;
import fr.eni.papeterie.dal.DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ArticleDAOJdbcImpl implements DAO<Article> {
    /* Requête SQL */
    private static final String insert = "INSERT INTO articles(reference, marque, designation, prixUnitaire, qteStock, grammage, couleur, type) VALUES(?,?,?,?,?,?,?,?) ";
    private static final String update = "UPDATE articles SET idArticle=?, reference=?, marque=?, designation=?, prixUnitaire=?, qteStock=?, grammage=?, couleur=?, type=? WHERE idArticle=?";
    private static final String selectAll = "SELECT idArticle, reference, marque, designation, prixUnitaire, qteStock, grammage, couleur, type FROM articles";
    private static final String selectById = "SELECT idArticle, reference, marque, designation, prixUnitaire, qteStock, grammage, couleur, type FROM articles WHERE idArticle=?";
    private static final String delete = "DELETE FROM articles WHERE idArticle=?";

    private static final String selectByMarque = "SELECT reference, designation, marque, prixUnitaire, qteStock, grammage, couleur, type FROM articles WHERE marque=?";
    private static final String selectByMotCle = "SELECT reference, designation, marque, prixUnitaire, qteStock, grammage, couleur, type FROM articles WHERE marque LIKE ? OR designation LIKE ?";


    /* CONSTANTE Ramette et stylo */
    private static final String TYPE_RAMETTE = "RAMETTE";
    private static final String TYPE_STYLO = "STYLO";

    /****************************** CONSTRUCTEUR ******************************/
    public ArticleDAOJdbcImpl() {
    }
    /****************************** FONCTION DIVERSE ******************************/


    public void closeConnection(Connection connexion){
        if (connexion != null){
            try{
                connexion.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            connexion = null;
        }
    }
    /****************************** FONCTION SQL ******************************/
    @Override
    public Article selectById(int id) throws DALException {
        Connection cnx = null;
        PreparedStatement req = null;
        ResultSet rs = null;
        Article article = null;

        try{
            cnx = JdbcTools.getConnection();
            req = cnx.prepareStatement(selectById);
            req.setInt(1, id);

            rs = req.executeQuery();
            if(rs.next()){
                if (TYPE_STYLO.equalsIgnoreCase(rs.getString("type").trim())){
                    article = new Stylo(
                            rs.getInt("idArticle"),
                            rs.getString("marque"),
                            rs.getString("reference"),
                            rs.getString("designation"),
                            rs.getFloat("prixUnitaire"),
                            rs.getInt("qteStock"),
                            rs.getString("couleur")
                    );
                }
                if (TYPE_RAMETTE.equalsIgnoreCase(rs.getString("type").trim())){
                    article = new Ramette(
                            rs.getInt("idArticle"),
                            rs.getString("marque"),
                            rs.getString("reference"),
                            rs.getString("designation"),
                            rs.getFloat("prixUnitaire"),
                            rs.getInt("qteStock"),
                            rs.getInt("grammage")
                    );
                }
            }
        } catch (SQLException e) {
           throw new DALException("La selection par id à échoué - id = "+id, e);
        } finally {
            try {
                if (req != null){
                    req.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            closeConnection(cnx);
        }
        return article;
    }

    @Override
    public List<Article> selectAll() throws DALException {
        Connection cnx = null;
        Statement req = null;
        ResultSet rs = null;
        List<Article> liste = new ArrayList<>();

        try{
            cnx = JdbcTools.getConnection();
            req = cnx.createStatement();
            rs = req.executeQuery(selectAll);

            Article article = null;

            while(rs.next()){
                if (TYPE_STYLO.equalsIgnoreCase(rs.getString("type").trim())){
                    article = new Stylo(
                            rs.getInt("idArticle"),
                            rs.getString("marque"),
                            rs.getString("reference").trim(),
                            rs.getString("designation"),
                            rs.getFloat("prixUnitaire"),
                            rs.getInt("qteStock"),
                            rs.getString("couleur")
                    );
                }
                if (TYPE_RAMETTE.equalsIgnoreCase(rs.getString("type").trim())){
                    article = new Ramette(
                            rs.getInt("idArticle"),
                            rs.getString("marque"),
                            rs.getString("reference").trim(),
                            rs.getString("designation"),
                            rs.getFloat("prixUnitaire"),
                            rs.getInt("qteStock"),
                            rs.getInt("grammage")
                    );
                }
                liste.add(article);
            }

        } catch (SQLException e) {
            throw new DALException("La selection de tous les articles à échouée - "+ e);
        } finally {
            try {
                if (req != null){
                    req.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            closeConnection(cnx);
        }
        return liste;
    }

    @Override
    public void insert(Article article){
        Connection cnx = null;
        PreparedStatement req = null;

        try {
            cnx = JdbcTools.getConnection();
            req = cnx.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);

            req.setString(1, article.getReference());
            req.setString(2, article.getMarque());
            req.setString(3, article.getDesignation());
            req.setFloat(4, article.getPrixUnitaire());
            req.setInt(5, article.getQteStock());

           if (article instanceof Stylo){
               Stylo stylo = (Stylo) article;
               req.setNull(6, Types.INTEGER);
               req.setString(7, stylo.getCouleur());
               req.setString(8, TYPE_STYLO);
           }

           if (article instanceof Ramette){
               Ramette ramette = (Ramette) article;
               req.setInt(6, ramette.getGrammage());
               req.setNull(7, Types.VARCHAR);
               req.setString(8, TYPE_RAMETTE);
           }

           int nbRows = req.executeUpdate();
           if (nbRows == 1){
               ResultSet rs = req.getGeneratedKeys();
               if (rs.next()){
                   article.setIdArticle(rs.getInt(1));
               }
           }
        } catch (SQLException e) {
            new DALException("Insertion de l'article impossible - "+e);
        }finally {
            try {
                if (req != null){
                    req.close();
                }
            }catch (SQLException e) {
                e.printStackTrace();
            }
            closeConnection(cnx);
        }
    }

    @Override
    public void update(Article article){
        Connection cnx = null;
        PreparedStatement req = null;

        try{
            cnx = JdbcTools.getConnection();
            req = cnx.prepareStatement(update);

            req.setString(1, article.getReference());
            req.setString(2, article.getMarque());
            req.setString(3, article.getDesignation());
            req.setFloat(4, article.getPrixUnitaire());
            req.setInt(5, article.getQteStock());

            if (article instanceof Stylo){
                Stylo stylo = (Stylo) article;
                req.setNull(6, Types.INTEGER);
                req.setString(7, stylo.getCouleur());
            }

            if (article instanceof Ramette){
                Ramette ramette = (Ramette) article;
                req.setInt(6, ramette.getGrammage());
                req.setNull(7, Types.VARCHAR);
            }

            req.executeUpdate();

        } catch (SQLException e) {
            new DALException("Update impossible de l'article - "+article, e);
        } finally {
            try {
                if(req != null){
                    req.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            closeConnection(cnx);
        }
    }

    @Override
    public void delete(int id){
        Connection cnx = null;
        PreparedStatement req = null;

        try {
            cnx = JdbcTools.getConnection();
            req = cnx.prepareStatement(delete);

            req.setInt(1, id);
            req.executeUpdate();

        } catch (SQLException e) {
            new DALException("Suppression de l'article impossible - id=  "+id,e);
        } finally {
            try {
                if (req != null){
                    req.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            closeConnection(cnx);
        }
    }

    public List<Article> selectByMarque(String search) throws DALException{
        Connection cnx = null;
        PreparedStatement req = null;
        ResultSet rs = null;

        List<Article> liste = new ArrayList();

        try{
            cnx = JdbcTools.getConnection();
            req = cnx.prepareStatement(selectByMarque);
            req.setString(1, search);
            rs = req.executeQuery();

            Article article = null;

            while (rs.next()){
                if (TYPE_STYLO.equalsIgnoreCase(rs.getString("type").trim())){
                    article = new Stylo(
                            rs.getInt("idArticle"),
                            rs.getString("reference"),
                            rs.getString("designation"),
                            rs.getString("marque"),
                            rs.getFloat("prixUnitaire"),
                            rs.getInt("qteStock"),
                            rs.getString("couleur")
                    );
                }
                if (TYPE_RAMETTE.equalsIgnoreCase(rs.getString("type").trim())){
                    article = new Ramette(
                            rs.getInt("idArticle"),
                            rs.getString("reference"),
                            rs.getString("designation"),
                            rs.getString("marque"),
                            rs.getFloat("prixUnitaire"),
                            rs.getInt("qteStock"),
                            rs.getInt("grammage")
                    );
                }

            }
            liste.add(article);

        } catch (SQLException e) {
            throw new DALException("Impossible de sélectionner par les marques",e);
        }finally {
            if (req != null){
                try {
                    req.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            closeConnection(cnx);
        }
        return liste;
    }

    public List<Article> selectByMotCle(String search) throws DALException, SQLException {
        Connection cnx = null;
        PreparedStatement req = null;
        ResultSet rs = null;

        List<Article> liste = new ArrayList();

        try {
            cnx = JdbcTools.getConnection();
            req = cnx.prepareStatement(selectByMotCle);
            rs = req.executeQuery();

            Article article = null;

            while (rs.next()){
                if (TYPE_STYLO.equalsIgnoreCase(rs.getString("type").trim())){
                    article = new Stylo(
                            rs.getString("reference"),
                            rs.getString("designation"),
                            rs.getString("marque"),
                            rs.getFloat("prixUnitaire"),
                            rs.getInt("qteStock"),
                            rs.getString("couleur")
                    );
                }
                if (TYPE_RAMETTE.equalsIgnoreCase(rs.getString("type").trim())){
                    article = new Ramette(
                            rs.getString("reference"),
                            rs.getString("designation"),
                            rs.getString("marque"),
                            rs.getFloat("prixUnitaire"),
                            rs.getInt("qteStock"),
                            rs.getInt("grammage")
                    );
                }
            }
            liste.add(article);

        } catch (SQLException e) {
            throw new DALException("Selection par mot clé impossible",e);
        }finally {
            if (req != null){
                req.close();
            }
            closeConnection(cnx);
        }
        return liste;
    }

}
