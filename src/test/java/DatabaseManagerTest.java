import dao.UserDAO;
import model.UserModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.DatabaseManager;

import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class DatabaseManagerTest{

    @BeforeEach
    public void init() throws SQLException {
        UserDAO.getInstance().dropTable();
        UserDAO.getInstance().createTable();
    }

    @Test
    public void testDatabaseManager() throws SQLException {
        DatabaseManager object = new DatabaseManager();
        assertNotEquals(null, object.con);
    }

    @Test
    public void testInsertion() throws SQLException {
        UserDAO.getInstance().dropTable();
        UserDAO.getInstance().createTable();

        UserModel u = new UserModel();
        u.nom = "Maggy";
        u.email = "maggy@gmail.com";
        UserDAO.getInstance().insert(u); // INSERTION

        // LIste utilisateurs
        ArrayList<UserModel> list = UserDAO.getInstance().getAll();
        assertEquals(1, list.size());
    }

    @Test
    public void autoIDInsert() throws SQLException {
        UserModel u = new UserModel();
        u.nom = "Maggy";
        u.email = "maggy@gmail.com";
        UserDAO.getInstance().insert(u); // INSERTION
        assertTrue(u.id > 0); // ==1
    }

    @Test
    public void autoIncrementID() throws SQLException {
        UserModel u = new UserModel();
        u.nom = "Maggy";
        u.email = "maggy@gmail.com";
        UserModel v = new UserModel();
        v.nom = "Ryan";
        v.email = "ryan@gmail.com";
        UserDAO.getInstance().insert(u); // INSERTION
        UserDAO.getInstance().insert(v); // INSERTION
        assertEquals(1, u.id); // utilisateur voloahnay manana id=1
        assertEquals(2, v.id); // faharoa id=2
    }

    @Test
    public void recherche() throws SQLException {
        UserModel u = new UserModel(); // 1
        u.nom = "Maggy";
        u.email = "maggy@gmail.com";
        UserModel v = new UserModel(); // 2
        v.nom = "Ryan";
        v.email = "ryan@gmail.com";
        UserDAO.getInstance().insert(u); // INSERTION
        UserDAO.getInstance().insert(v); // INSERTION

        UserModel p = UserDAO.getInstance().recherche(1);
        assertEquals("Maggy", p.nom);
        UserModel q = UserDAO.getInstance().recherche(2);
        assertEquals("Ryan", q.nom);
    }

    @Test
    public void miseAJour() throws SQLException {

        UserModel u = new UserModel(); // 1
        u.nom = "Maggy";
        u.email = "maggy@gmail.com";
        UserModel v = new UserModel(); // 2
        v.nom = "Ryan";
        v.email = "ryan@gmail.com";
        UserDAO.getInstance().insert(u); // INSERTION
        UserDAO.getInstance().insert(v); // INSERTION

        u.email = "maggy@lecnam.net";
        UserDAO.getInstance().update(u);

        UserModel p = UserDAO.getInstance().recherche(1);
        assertEquals("maggy@lecnam.net", p.email);
    }

    @Test
    public void suppression() throws SQLException {

        UserModel u = new UserModel(); // 1
        u.nom = "Maggy";
        u.email = "maggy@gmail.com";
        UserModel v = new UserModel(); // 2
        v.nom = "Ryan";
        v.email = "ryan@gmail.com";
        UserDAO.getInstance().insert(u); // INSERTION
        UserDAO.getInstance().insert(v); // INSERTION

        UserDAO.getInstance().delete(v);
        ArrayList<UserModel> list = UserDAO.getInstance().getAll();
        assertEquals(1, list.size());
    }


}
