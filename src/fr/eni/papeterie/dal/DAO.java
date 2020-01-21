package fr.eni.papeterie.dal;

import java.util.List;

public interface DAO<T> {
    // Selectionner un article par son id
    T selectById(int id) throws DALException;

    // Selectionner tous les articles
    List<T> selectAll() throws DALException;
    // Insertion d'un nouvel article
    void insert(T data) throws DALException;
    // Mise à jour d'un article
    void update(T data) throws DALException;
    // Suppression d'un article
    void delete(int id) throws DALException;
}
