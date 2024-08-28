package org.ac.cst8277.mignardi.eric.usermanagementservice.repository;

import org.ac.cst8277.mignardi.eric.usermanagementservice.database.Database;
import org.ac.cst8277.mignardi.eric.usermanagementservice.model.User;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @Override
    public List<User> findAll() throws SQLException {
        List<User> users = new ArrayList<>();
        Connection connection = Database.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users;");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            User user = new User();
            user.setId(resultSet.getInt("id"));
            user.setName(resultSet.getString("name"));
            user.setEmail(resultSet.getString("email"));
            user.setPassword(resultSet.getString("password"));
            users.add(user);
        }
        return users;
    }

    @Override
    public User findById(int id) throws SQLException {
        User user = new User();
        Connection connection = Database.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE id = ?;");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            user.setId(resultSet.getInt("id"));
            user.setName(resultSet.getString("name"));
            user.setEmail(resultSet.getString("email"));
            user.setPassword(resultSet.getString("password"));
        }
        return user;
    }

    @Override
    public int save(User user) {
        return 0;
    }

    @Override
    public int insert(User user) throws SQLException {
        Connection connection = Database.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO users (id, name, email, password) VALUES (?,?,?,?);");
        preparedStatement.setInt(1, user.getId());
        preparedStatement.setString(2, user.getName());
        preparedStatement.setString(3, user.getEmail());
        preparedStatement.setString(4, user.getPassword());
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
    public int update(User user) throws SQLException {
        Connection connection = Database.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE users SET name = ?, email = ?, password = ? WHERE id = ?;");
        preparedStatement.setString(1, user.getName());
        preparedStatement.setString(2, user.getEmail());
        preparedStatement.setString(3, user.getPassword());
        preparedStatement.setInt(4, user.getId());
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
        PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM users WHERE id=?;");
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