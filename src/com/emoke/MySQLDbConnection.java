package com.emoke;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLDbConnection {
    private Connection connection;

    public static MySQLDbConnection getInstance() {
        if(instance == null) {
            instance = new MySQLDbConnection();
        }

        return instance;
    }

    public Connection getConnection() {
        return connection;
    }

    private MySQLDbConnection() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/library","root","");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static MySQLDbConnection instance;
}
