package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {

    private static DatabaseManager instance = null;
    public static DatabaseManager getInstance() throws SQLException {
        if(instance == null){
            instance = new DatabaseManager();
        }
        return instance;
    }

    public String url = "jdbc:postgresql://localhost:5433/bdd_test";
    public String user = "astrozeneka";
    public String pass = "deltaCharlie52";
    private static String driverName = "org.postgresql.Driver";


    public Connection con = null;
    public DatabaseManager() throws SQLException {
        try{
            // Check database connection
            Class.forName(driverName).newInstance();
            con = DriverManager.getConnection(url, user, pass);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

}
