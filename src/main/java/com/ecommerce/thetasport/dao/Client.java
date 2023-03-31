package com.ecommerce.thetasport.dao;

import java.sql.Connection;
import java.sql.SQLException;

public class Client {
    public static void main( String[] args ) throws SQLException {
        Connection connection = DatabaseConnection.getInstance().getConnection();
    }
}
