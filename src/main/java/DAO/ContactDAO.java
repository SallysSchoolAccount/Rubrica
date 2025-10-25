package DAO;

import Models.Contact;

import java.sql.SQLException;
import java.util.List;

public interface ContactDAO extends DAO<Contact> {
    @Override
    default Contact get(int id) throws SQLException {
        return null;
    }

    @Override
    default List<Contact> getAll() throws SQLException {
        return List.of();
    }

    @Override
    default int save(Contact contact) throws SQLException {
        return 0;
    }

    @Override
    default int insert(Contact contact) throws SQLException {
        return 0;
    }

    @Override
    default int update(Contact contact) throws SQLException {
        return 0;
    }

    @Override
    default int delete(Contact contact) throws SQLException {
        return 0;
    }
}
