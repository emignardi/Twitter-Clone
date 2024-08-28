package org.ac.cst8277.mignardi.eric.messageservice.repository;

import org.ac.cst8277.mignardi.eric.messageservice.database.Database;
import org.ac.cst8277.mignardi.eric.messageservice.model.Subscription;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class SubscriptionDaoImpl implements SubscriptionDao {

    @Override
    public List<Subscription> getSubscriptionsForSubscriberById(int id) throws SQLException {
        List<Subscription> subscriptions = new ArrayList<>();
        Connection connection = Database.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM subscriptions WHERE subscribers_id = ?;");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Subscription subscription = new Subscription();
            subscription.setProducers_id(resultSet.getInt("producers_id"));
            subscription.setSubscribers_id(resultSet.getInt("subscribers_id"));
            subscriptions.add(subscription);
        }
        return subscriptions;
    }

    @Override
    public int createSubscription(Subscription subscription) throws SQLException {
        Connection connection = Database.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO subscriptions (producers_id, subscribers_id) VALUES (?,?);");
        preparedStatement.setInt(1, subscription.getProducers_id());
        preparedStatement.setInt(2, subscription.getSubscribers_id());
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
    public int deleteSubscriptionById(int id) throws SQLException {
        Connection connection = Database.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM subscriptions WHERE subscribers_id = ?;");
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
    public List<Subscription> findAll() throws SQLException {
        return List.of();
    }

    @Override
    public Subscription findById(int id) throws SQLException {
        return null;
    }

    @Override
    public int save(Subscription subscription) {
        return 0;
    }

    @Override
    public int insert(Subscription subscription) throws SQLException {
        return 0;
    }

    @Override
    public int update(Subscription subscription) throws SQLException {
        return 0;
    }

    @Override
    public int delete(int id) throws SQLException {
        return 0;
    }
}