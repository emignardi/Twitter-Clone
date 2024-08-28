package org.ac.cst8277.mignardi.eric.messageservice.repository;

import org.ac.cst8277.mignardi.eric.messageservice.database.Database;
import org.ac.cst8277.mignardi.eric.messageservice.model.Message;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class MessageDaoImpl implements MessageDao {

    @Override
    public List<Message> findAll() throws SQLException {
        List<Message> messages = new ArrayList<>();
        Connection connection = Database.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM messages;");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Message message = new Message();
            message.setId(resultSet.getInt("id"));
            message.setContent(resultSet.getString("content"));
            message.setProducer_id(resultSet.getInt("producer_id"));
            messages.add(message);
        }
        return messages;
    }

    @Override
    public Message findById(int id) throws SQLException {
        Message message = new Message();
        Connection connection = Database.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM messages WHERE id = ?;");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            message.setId(resultSet.getInt("id"));
            message.setContent(resultSet.getString("content"));
            message.setProducer_id(resultSet.getInt("producer_id"));
        }
        return message;
    }

    @Override
    public int save(Message message) {
        return 0;
    }

    @Override
    public int insert(Message message) throws SQLException {
        Connection connection = Database.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO messages (id, content, producer_id) VALUES (?,?,?);");
        preparedStatement.setInt(1, message.getId());
        preparedStatement.setString(2, message.getContent());
        preparedStatement.setInt(3, message.getProducer_id());
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
    public int update(Message message) throws SQLException {
        Connection connection = Database.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE messages SET content = ?, producer_id = ? WHERE id = ?;");
        preparedStatement.setString(1, message.getContent());
        preparedStatement.setInt(2, message.getProducer_id());
        preparedStatement.setInt(3, message.getId());
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
        PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM messages WHERE id=?;");
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

    @Override
    public List<Message> getMessagesForProducerById(int id) throws SQLException {
        List<Message> messages = new ArrayList<>();
        Connection connection = Database.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM messages WHERE producer_id = ?;");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Message message = new Message();
            message.setId(resultSet.getInt("id"));
            message.setContent(resultSet.getString("content"));
            message.setProducer_id(resultSet.getInt("producer_id"));
            messages.add(message);
        }
        return messages;
    }

    @Override
    public List<Message> getMessagesForSubscriberById(int id) throws SQLException {
        List<Message> messages = new ArrayList<>();
        Connection connection = Database.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT messages.id as id, messages.content as content, messages.producer_id as producer_id FROM messages LEFT JOIN producers ON messages.producer_id = producers.id LEFT JOIN subscriptions ON producers.id = subscriptions.producers_id LEFT JOIN subscribers ON subscriptions.subscribers_id = subscribers.id WHERE subscribers.id = ?;");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Message message = new Message();
            message.setId(resultSet.getInt("id"));
            message.setContent(resultSet.getString("content"));
            message.setProducer_id(resultSet.getInt("producer_id"));
            messages.add(message);
        }
        return messages;
    }

}