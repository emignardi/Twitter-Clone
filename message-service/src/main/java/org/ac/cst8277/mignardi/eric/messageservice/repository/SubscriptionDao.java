package org.ac.cst8277.mignardi.eric.messageservice.repository;

import org.ac.cst8277.mignardi.eric.messageservice.model.Message;
import org.ac.cst8277.mignardi.eric.messageservice.model.Subscription;

import java.sql.SQLException;
import java.util.List;

public interface SubscriptionDao extends Dao<Subscription> {

    List<Subscription> getSubscriptionsForSubscriberById(int id) throws SQLException;
    int createSubscription(Subscription subscription) throws SQLException;
    int deleteSubscriptionById(int id) throws SQLException;

}