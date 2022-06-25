import org.junit.jupiter.api.Test;
import service.DatabaseManager;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class DatabaseManagerTest{
    @Test
    public void testDatabaseManager() throws SQLException {
        DatabaseManager object = new DatabaseManager();
        assertNotEquals(null, object.con);
    }
}
