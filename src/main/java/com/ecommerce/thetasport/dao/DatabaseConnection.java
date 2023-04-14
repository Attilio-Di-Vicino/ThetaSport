package com.ecommerce.thetasport.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * La classe DatabaseConnection è responsabile della connessione al database. <br>
 * Utilizza il pattern Singleton per garantire che venga creata una sola <br>
 * istanza della connessione al database.
 *
 * @author Theta Sport
 * @version 1.0
 */
public class DatabaseConnection {
    private static DatabaseConnection instance;
    private Connection connection;

    /**
     * Costruttore privato della classe DatabaseConnection. <br>
     * Viene invocato una sola volta per creare una nuova connessione al database. <br>
     * Se la connessione fallisce, viene lanciata un'eccezione di tipo SQLException.
     *
     * @throws SQLException in caso di errori durante la creazione della connessione
     */
    private DatabaseConnection() throws SQLException {
        try {
            Class.forName( "com.mysql.cj.jdbc.Driver" );
            String url = "jdbc:mysql://localhost:3306/THETASPORTDB";
            this.connection = DriverManager.getConnection( url, "root", "password" );
        } catch ( ClassNotFoundException ex ) {
            System.out.println( "Database Connection Creation Failed : " + ex.getMessage() );
        }
    }

    /**
     * Metodo pubblico che restituisce la connessione al database.
     *
     * @return la connessione al database
     */
    public Connection getConnection() {
        return connection;
    }

    /**
     * Metodo pubblico che restituisce l'unica istanza della classe DatabaseConnection. <br>
     * Se l'istanza non è ancora stata creata, viene creata e restituita.
     *
     * @return l'unica istanza della classe DatabaseConnection
     * @throws SQLException in caso di errori durante la creazione della connessione
     */
    public static DatabaseConnection getInstance() throws SQLException {
        if ( instance == null ) {
            instance = new DatabaseConnection();
        }
        return instance;
    }
}
