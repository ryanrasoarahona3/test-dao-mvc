package dao;

import model.UserModel;
import service.DatabaseManager;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class UserDAO {

    public void add(UserModel u) throws SQLException {
        PreparedStatement stmt = DatabaseManager.getInstance().con.prepareStatement("INSERT INTO \"user\" (id, nom, email) VALUES (null, ?, ?);");
        stmt.setString(1, u.nom);
        stmt.setString(2, u.email);
        stmt.execute();
    }

    public ArrayList<UserModel> getAll() throws SQLException {
        ArrayList<UserModel> output = new ArrayList<>();
        Statement stmt = DatabaseManager.getInstance().con.createStatement();
        stmt.execute("SELECT * FROM \"user\";");
    }
}
