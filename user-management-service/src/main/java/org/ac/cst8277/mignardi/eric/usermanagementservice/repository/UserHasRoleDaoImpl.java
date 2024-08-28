package org.ac.cst8277.mignardi.eric.usermanagementservice.repository;

import org.ac.cst8277.mignardi.eric.usermanagementservice.database.Database;
import org.ac.cst8277.mignardi.eric.usermanagementservice.model.UserHasRole;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserHasRoleDaoImpl implements UserHasRoleDao {

    @Override
    public List<UserHasRole> findAll() throws SQLException {
        List<UserHasRole> userHasRoles = new ArrayList<>();
        Connection connection = Database.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users_has_roles;");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            UserHasRole userHasRole = new UserHasRole();
            userHasRole.setUsers_id(resultSet.getInt("users_id"));
            userHasRole.setRoles_id(resultSet.getInt("roles_id"));
            userHasRoles.add(userHasRole);
        }
        return userHasRoles;
    }

    @Override
    public UserHasRole findById(int id) throws SQLException {
        UserHasRole userHasRole = new UserHasRole();
        Connection connection = Database.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users_has_roles WHERE users_id = ?;");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            userHasRole.setUsers_id(resultSet.getInt("users_id"));
            userHasRole.setRoles_id(resultSet.getInt("roles_id"));
        }
        return userHasRole;
    }

    @Override
    public int save(UserHasRole userHasRole) {
        return 0;
    }

    @Override
    public int insert(UserHasRole userHasRole) throws SQLException {
        return 0;
    }

    @Override
    public int update(UserHasRole userHasRole) throws SQLException {
        return 0;
    }

    @Override
    public int delete(int id) throws SQLException {
        return 0;
    }

}