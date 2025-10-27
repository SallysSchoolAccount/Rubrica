import DAO.ContactDAO;
import Models.Contact;

import java.util.ArrayList;
import java.util.List;

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
    //GET ALL
    @Override
    public java.util.List<Contact> getAll() throws SQLException {
        Connection conn  =  DatabaseConn.getConnection();
        List<Contact> contacts = new ArrayList<>();

        String sql = "SELECT id, vorname, nachname, telefonnummer, email FROM t_contacts";
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            int oid = rs.getInt("id");
            String vorname = rs.getString("vorname");
            String nachname = rs.getString("nachname");
            String telefonnummer = rs.getString("telefonnummer");
            String email = rs.getString("email");

            Contact contact = new Contact(vorname, nachname, telefonnummer, email);
            contacts.add(contact);
        }

        return contacts;
    }
}
