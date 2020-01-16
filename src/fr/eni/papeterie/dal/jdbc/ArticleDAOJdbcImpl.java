package fr.eni.papeterie.dal.jdbc;

import fr.eni.papeterie.bo.Article;
import fr.eni.papeterie.bo.Ramette;
import fr.eni.papeterie.bo.Stylo;
import fr.eni.papeterie.dal.DALException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ArticleDAOJdbcImpl {
    /* Requête SQL */
    private static final String insert = "INSERT INTO articles(reference, marque, designation, prixUnitaire, qteStock, grammage, couleur, type) VALUES(?,?,?,?,?,?,?,?) ";
    private static final String update = "UPDATE articles SET idArticle=?, reference=?, marque=?, designation=?, prixUnitaire=?, qteStock=?, grammage=?, couleur=?, type=? WHERE idArticle=?";
private static final String selectAll = "SELECT idArticle, reference, marque, designation, prixUnitaire, qteStock, grammage, couleur, type FROM articles";
    private static final String selectById = "SELECT idArticle, reference, marque, designation, prixUnitaire, qteStock, grammage, couleur, type FROM articles WHERE idArticle=?";
    private static final String delete = "DELETE FROM articles WHERE idArticle=?";

    /* CONSTANTE Ramette et stylo */
    private static final String TYPE_RAMETTE = "RAMETTE";
    private static final String TYPE_STYLO = "STYLO";

    /* Connexion */
    private Connection connexion;

    /* CHARGEMENT DU DRIVER JDBC */
    static{
        // Chargement du driver
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    /****************************** CONSTRUCTEUR ******************************/
    public ArticleDAOJdbcImpl() {
    }
    /****************************** FONCTION DIVERSE ******************************/
    public Connection getConnection() throws SQLException{
        if (connexion == null){
            String url = "jdbc:sqlserver://127.0.0.1;databasename=PAPETERIE_DB";
            connexion = DriverManager.getConnection(url, "user", "Pa$$W0rd");
        }
        return connexion;
    }

    public void closeConnection(){
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
    public Article selectById(int id) throws DALException {
        Connection cnx = null;
        PreparedStatement req = null;
        ResultSet rs = null;
        Article article = null;

        try{
            cnx = getConnection();
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
            closeConnection();
        }
        return article;
    }

    public List<Article> selectAll() throws DALException {
        Connection cnx = null;
        Statement req = null;
        ResultSet rs = null;
        List<Article> liste = new ArrayList<>();

        try{
            cnx = getConnection();
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
            closeConnection();
        }
        return liste;
    }

    public void insert(Article article){
        Connection cnx = null;
        PreparedStatement req = null;

        try {
            cnx = getConnection();
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
            closeConnection();
        }
    }

    public void update(Article article){
        Connection cnx = null;
        PreparedStatement req = null;

        try{
            cnx = getConnection();
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
            closeConnection();
        }
    }

    public void delete(int id){
        Connection cnx = null;
        PreparedStatement req = null;

        try {
            cnx = getConnection();
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
            closeConnection();
        }
    }


}
