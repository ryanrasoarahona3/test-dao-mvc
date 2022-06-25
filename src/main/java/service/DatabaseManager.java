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

    public String url = "jdbc:mysql://localhost:3306/bdd_test";
    public String user = "root";
    public String pass = "";
    private static String driverName = "com.mysql.jdbc.Driver";


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
