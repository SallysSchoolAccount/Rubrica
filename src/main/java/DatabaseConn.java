import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConn {

    private static String url = "jdbc:sqlite:./Database/Phonebook.db";
    private static String user = "";
    private static String password = "";

    private DatabaseConn() {

    }

    public static Connection getConnection() throws SQLException {
        Connection conn = null;
        conn = DriverManager.getConnection(url);
        return conn;
    }
}
