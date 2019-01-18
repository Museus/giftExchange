package jredding;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
 
/**
 *
 * @author sqlitetutorial.net
 */
public class SQLiteJDBCDriverConnection {
     /**
     * Connect to a sample database
     */
    public static void connect() {
        Connection conn = null;
        try {
            // db parameters
            String url = "jdbc:sqlite:db/GiftExchangeV1.db";
            // create a connection to the database
            conn = DriverManager.getConnection(url);
            
            if(conn != null) {
            	System.out.println("Connection to SQLite has been established.");
            } else {
            	System.out.println("File does not exist.");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        connect();
    }
}