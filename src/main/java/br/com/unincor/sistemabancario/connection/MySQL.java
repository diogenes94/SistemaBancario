package br.com.unincor.sistemabancario.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dioge
 */
public class MySQL {

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/sistema_bancario",
                    "root", "admin");

            return connection;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(MySQL.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public static void main(String[] args) {
        System.out.println(getConnection());
    }
}
