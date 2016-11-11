package com.nwords;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionGetter {

    private static Connection mysql;

    private static void openMySqlConnection() {
        try {
            mysql = DriverManager.getConnection("jdbc:mysql://localhost:3306/language_coach?" +
                    "user=coach&password=coach");
        } catch (SQLException e) {
            System.out.println("Cannot establish connection with MYSQL database");
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }
    }

    public static Connection mysql() throws SQLException {
        if (mysql == null) openMySqlConnection();
        if (mysql.isClosed()) openMySqlConnection();
        return mysql;
    }
}
