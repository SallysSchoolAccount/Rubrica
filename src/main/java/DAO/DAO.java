package DAO;

import java.sql.SQLException;
import java.util.List;

public interface DAO<T> {
    //Returns employee
    T get(int id) throws SQLException;

    //Returns all employees
    List<T> getAll() throws SQLException;

    int save(T t) throws SQLException;

    int insert (T t) throws SQLException;

    int update (T t) throws SQLException;

    int delete (T t) throws SQLException;
}
