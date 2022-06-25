package dao;

import model.UserModel;
import service.DatabaseManager;

import javax.xml.crypto.Data;
import javax.xml.transform.Result;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class UserDAO {

    private static UserDAO instance = null;
    public static UserDAO getInstance() throws SQLException {
        if(instance == null){
            instance = new UserDAO();
        }
        return instance;
    }

    public ArrayList<UserModel> getAll() throws SQLException {
        ArrayList<UserModel> output = new ArrayList<>();
        Statement stmt = DatabaseManager.getInstance().con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM \"user\";");
        while(rs.next()){
            UserModel u = new UserModel();
            u.id = rs.getInt("id");
            u.nom = rs.getString("nom");
            u.email = rs.getString("email");
            output.add(u);
        }
        return output;
    }

    public void insert(UserModel u) throws SQLException {
        // Classe iray -> Objet/Instance be dia be
        // Singleton : Classe iray -> Objet/Instance iray
        // Classe appli : appli iray ihany no ao
        // Classe bdd : tsy bdd be dia be fa iray ihany
        // Classe user : tsy possible ho singleton satria misy user be dia be
        PreparedStatement stmt = DatabaseManager.getInstance().con.prepareStatement(
                "INSERT INTO \"user\" (nom, email) VALUES (?, ?)",
                Statement.RETURN_GENERATED_KEYS);
        // objet anakiray ihany
        // référence be dia be
        stmt.setString(1, u.nom);
        stmt.setString(2, u.email);
        stmt.execute();

        // Attribution an'ilay id automatique avy @bdd
        // Mi-récupérer
        ResultSet generatedKeys = stmt.getGeneratedKeys();
        generatedKeys.next();
        int auto_id = generatedKeys.getInt(1);
        u.id = auto_id;
    }

    public UserModel recherche(int id) throws SQLException {
        PreparedStatement stmt = DatabaseManager.getInstance().con.prepareStatement("SELECT * FROM \"user\" WHERE id=?");
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();
        if(!rs.next())
            return null;
        UserModel u = new UserModel();
        u.id = rs.getInt("id");
        u.nom = rs.getString("nom");
        u.email = rs.getString("email");
        return u;
    }

    public void update(UserModel u) throws SQLException {
        // u.id tsy afaka ovaina
        PreparedStatement stmt = DatabaseManager.getInstance().con.prepareStatement("UPDATE \"user\" set nom=?, email=? WHERE id=?");
        stmt.setString(1, u.nom);
        stmt.setString(2, u.email);
        stmt.setInt(3, u.id);
        stmt.execute();
    }

    public void delete(UserModel u) throws SQLException {
        PreparedStatement stmt = DatabaseManager.getInstance().con.prepareStatement("DELETE FROM \"user\" WHERE id=?");
        stmt.setInt(1, u.id);
        stmt.execute();
    }

    /**
     * Installation
     * @throws SQLException
     */
    public void createTable() throws SQLException {
        String sql =    "CREATE TABLE \"user\" (\n" +
                        "                        id serial PRIMARY KEY,\n" +
                        "                        nom varchar(255),\n" +
                        "                        email varchar(255)\n" + ");";;
        Statement stmt = DatabaseManager.getInstance().con.createStatement();
        stmt.execute(sql);
    }

    public void dropTable() throws SQLException {
        String sql =    "DROP TABLE IF EXISTS \"user\"";
        Statement stmt = DatabaseManager.getInstance().con.createStatement();
        stmt.execute(sql);
    }
}
