package org.ac.cst8277.mignardi.eric.usermanagementservice.repository;

import org.ac.cst8277.mignardi.eric.usermanagementservice.model.User;

import java.sql.SQLException;
import java.util.List;

public interface Dao <T> {
    List<T> findAll() throws SQLException;
    T findById(int id) throws SQLException;
    int save(T t);
    int insert(T t) throws SQLException;
    int update(T t) throws SQLException;
    int delete(int id) throws SQLException;
}