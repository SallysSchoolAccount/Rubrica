import DAO.ContactDAO;
import Models.Contact;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class ContactDAOImpl implements ContactDAO {

    //GET
    @Override
    public Contact get(int id) throws SQLException {
        Connection conn = DatabaseConn.getConnection();
        Contact contact = null;

        String sql = "SELECT id, vorname, nachname, telefonnummer, email FROM t_contacts WHERE id = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            int oid = rs.getInt("id");
            String vorname = rs.getString("vorname");
            String nachname = rs.getString("nachname");
            String telefonnummer = rs.getString("telefonnummer");
            String email = rs.getString("email");

            contact = new Contact(vorname, nachname, telefonnummer, email);
        }
        return contact;
    }
}
