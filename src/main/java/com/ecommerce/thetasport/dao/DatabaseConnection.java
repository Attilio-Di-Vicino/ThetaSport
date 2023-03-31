package com.ecommerce.thetasport.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static DatabaseConnection instance;
    private Connection connection;

    private DatabaseConnection() throws SQLException {
        try {
            Class.forName( "com.mysql.cj.jdbc.Driver" );
            String url = "jdbc:mysql://localhost:3306/THETASPORTDB";
            this.connection = DriverManager.getConnection( url, "root", "password" );
            System.out.println( "Database Connection Successful.. " );
        } catch ( ClassNotFoundException ex ) {
            System.out.println( "Database Connection Creation Failed : " + ex.getMessage() );
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public static DatabaseConnection getInstance() throws SQLException {
        if ( instance == null ) {
            instance = new DatabaseConnection();
        } else if ( instance.getConnection().isClosed() ) {
            instance = new DatabaseConnection();
        }
        return instance;
    }
}
