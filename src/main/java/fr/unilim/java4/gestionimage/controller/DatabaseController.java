package fr.unilim.java4.gestionimage.controller;
import java.sql.Connection ;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseController {
    private static String URL ="";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL,"username","password");
    }
}
