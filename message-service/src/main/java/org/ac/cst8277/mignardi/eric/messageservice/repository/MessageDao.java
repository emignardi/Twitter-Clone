package org.ac.cst8277.mignardi.eric.messageservice.repository;

import org.ac.cst8277.mignardi.eric.messageservice.model.Message;

import java.sql.SQLException;
import java.util.List;

public interface MessageDao extends Dao<Message> {

    List<Message> getMessagesForProducerById(int id) throws SQLException;
    List<Message> getMessagesForSubscriberById(int id) throws SQLException;

}