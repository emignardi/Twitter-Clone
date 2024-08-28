package org.ac.cst8277.mignardi.eric.usermanagementservice.repository;

import org.ac.cst8277.mignardi.eric.usermanagementservice.database.Database;
import org.ac.cst8277.mignardi.eric.usermanagementservice.model.Role;
import org.ac.cst8277.mignardi.eric.usermanagementservice.model.User;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class RoleDaoImpl implements RoleDao {

    @Override
    public List<Role> findAll() throws SQLException {
        List<Role> roles = new ArrayList<>();
        Connection connection = Database.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM roles;");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Role role = new Role();
            role.setId(resultSet.getInt("id"));
            role.setName(resultSet.getString("name"));
            roles.add(role);
        }
        return roles;
    }

    @Override
    public Role findById(int id) throws SQLException {
        Role role = new Role();
        Connection connection = Database.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM roles WHERE id = ?;");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            role.setId(resultSet.getInt("id"));
            role.setName(resultSet.getString("name"));
        }
        return role;
    }

    @Override
    public int save(Role role) {
        return 0;
    }

    @Override
    public int insert(Role role) throws SQLException {
        Connection connection = Database.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO roles (id, name) VALUES (?,?);");
        preparedStatement.setInt(1, role.getId());
        preparedStatement.setString(2, role.getName());
        int results = preparedStatement.executeUpdate();
        if (results > 0) {
            System.out.println("Insert Success");
        }
        else {
            System.out.println("Insert Fail");
        }
        return results;
    }

    @Override
    public int update(Role role) throws SQLException {
        Connection connection = Database.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE roles SET name = ? WHERE id = ?;");
        preparedStatement.setString(1, role.getName());
        preparedStatement.setInt(2, role.getId());
        int results = preparedStatement.executeUpdate();
        if (results > 0) {
            System.out.println("Update Success");
        }
        else {
            System.out.println("Update Fail");
        }
        return results;
    }

    @Override
    public int delete(int id) throws SQLException {
        Connection connection = Database.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM roles WHERE id=?;");
        preparedStatement.setInt(1, id);
        int results = preparedStatement.executeUpdate();
        if (results > 0) {
            System.out.println("Delete Success");
        }
        else {
            System.out.println("Delete Fail");
        }
        return results;
    }

}