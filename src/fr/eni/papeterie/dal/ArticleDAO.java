package fr.eni.papeterie.dal;

import fr.eni.papeterie.bo.Article;

import java.util.List;

public interface ArticleDAO {
    // Selectionner un article par son id
    Article selectById(int id) throws DALException;

    // Selectionner tous les articles
    List<Article> selectAll() throws DALException;
    // Insertion d'un nouvel article
    void insert(Article article) throws DALException;
    // Mise à jour d'un article
    void update(Article article) throws DALException;
    // Suppression d'un article
    void delete(int id) throws DALException;
}
