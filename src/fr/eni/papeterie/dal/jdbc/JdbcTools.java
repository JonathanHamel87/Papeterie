package fr.eni.papeterie.dal.jdbc;

import fr.eni.papeterie.dal.Settings;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcTools {
    private static String url;
    private static String user;
    private static String password;

    /* CHARGEMENT DU DRIVER JDBC */
    static{
        // Chargement du driver
        try{
            Class.forName(Settings.getProperty("driverjdbc"));
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        url = Settings.getProperty("url");
        user = Settings.getProperty("user");
        password = Settings.getProperty("password");
    }

    public static Connection getConnection() throws SQLException {
        Connection cnx = DriverManager.getConnection(url, user, password);
        return cnx;
    }
}
