package DAO;

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

            contact = new Contact(oid, vorname, nachname, telefonnummer, email);
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

            Contact contact = new Contact(oid, vorname, nachname, telefonnummer, email);
            contacts.add(contact);
        }

        return contacts;
    }

    public int insert(Contact contact) throws SQLException {
        Connection conn = DatabaseConn.getConnection();

        String sql = "INSERT INTO t_contacts (vorname, nachname, telefonnummer, email) VALUES (?, ?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, contact.getVorname());
        ps.setString(2, contact.getNachname());
        ps.setString(3, contact.getTelefonNumer());
        ps.setString(4, contact.getEmail());

        int restult = ps.executeUpdate();

        return restult;
    }

    public int update(Contact contact) throws SQLException {
        Connection conn = DatabaseConn.getConnection();

        String sql = "UPDATE t_contacts SET vorname = ?, nachname = ?, telefonnummer = ?, email = ? WHERE id = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, contact.getVorname());
        ps.setString(2, contact.getNachname());
        ps.setString(3, contact.getTelefonNumer());
        ps.setString(4, contact.getEmail());
        ps.setInt(5, contact.getId());
        int result = ps.executeUpdate();

        if (result == 0) {
            throw new SQLException("Update failed, no rows affected.");
        }

        return result;
    }
}
