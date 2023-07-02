package com.example.inzynieria_sprint_n;

import java.sql.*;
import java.util.Properties;

/**
 * DatabaseConnection odpowiada za nawiązanie połączenia z bazą danych
 */
public class DatabaseConnection {

    private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";

    private static final String DB_NAME = "stacja_kosmicznadb";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/" + DB_NAME;
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "password";

    private static final String MAX_POOL = "250";
    private Connection connection;

    public Connection getConnection(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            String sql  = "Select * from missions";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            int count = 0;
            while (resultSet.next()){
                int id = resultSet.getInt("mission_id");

            }
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }

}
