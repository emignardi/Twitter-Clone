package org.ac.cst8277.mignardi.eric.messageservice.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

    private static String url = "jdbc:mysql://10.1.188.168:3306/message_service";
    private static String user = "root";
    private static String password = "password";

    private Database() {

    }

    public static Connection getConnection() throws SQLException {
        Connection connection = null;
        connection = DriverManager.getConnection(url, user, password);
        return connection;
    }

}