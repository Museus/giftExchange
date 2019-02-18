package jredding;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
 
public class SqliteConnection {
	
	private String url;
	
	public SqliteConnection() {
		url = "jdbc:sqlite:db/GiftExchangeV1.db";
	}
	
	/**
	 * Open a connection to a database using the specified url
	 */
    private Connection connect() {
        Connection conn = null;
        
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
        return conn;
    }
    
    /**
     * 
     * @param username
     * @return
     */
    public boolean userExists(String username) {
    	 String sql = "SELECT user_id FROM users WHERE username = ?";
    	 
         
         try (Connection conn = this.connect();
              PreparedStatement pstmt = conn.prepareStatement(sql)){
        	  pstmt.setString(1, username);
              ResultSet rs    = pstmt.executeQuery();
             
             if(!rs.next()) {
            	 return false;
             }
         } catch (SQLException e) {
             System.out.println(e.getMessage());
         }
         
		return true;
    }
    
    /**
     * 
     * @param username
     * @param email
     * @return
     */
    public boolean[] userExists(String username, String email) {
    	String sql = "SELECT username, email_address FROM users WHERE username = ? OR email_address = ?";
   	 	boolean usernameUsed = false;
   	 	boolean emailUsed = false;
        
        try (Connection conn = this.connect(); 
        	 PreparedStatement pstmt = conn.prepareStatement(sql)){
        	
       	  	pstmt.setString(1, username);
       	  	pstmt.setString(2, email);
       	  	ResultSet rs = pstmt.executeQuery();
            
            while(rs.next()) {
        		if(rs.getString("username").contentEquals(username)) {
        			usernameUsed = true;
        		}
        		
        		if(rs.getString("email_address").contentEquals(email)) {
        			emailUsed = true;
        		}
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
		return new boolean[]{usernameUsed, emailUsed};
   }
    
    /**
     * 
     * @param firstName
     * @param lastName
     * @param username
     * @param emailAddress
     * @param password
     */
   public void createUser(String firstName, String lastName, String username, String emailAddress, String phoneNumber, String password) {
	   String sql = "INSERT INTO users(first_name, last_name, username, email_address, phone_number, password) VALUES(?, ?, ?, ?, ?, ?)";

	    try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
	    	
            pstmt.setString(1, firstName);
            pstmt.setString(2, lastName);
            pstmt.setString(3, username);
            pstmt.setString(4, emailAddress);
            pstmt.setString(5, phoneNumber);
            pstmt.setString(6, password);
            pstmt.executeUpdate();
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
   }
   
   /**
    * 
    * @param groupName
    * @param groupDescription
    * @param groupAdmin
    * @param eventDateTime
    */
   public void createGroup(String groupName, String groupDescription, int groupAdmin, String eventDateTime) {
	   String sql = "INSERT INTO groups(group_name, description, administrator, event_date_time) VALUES(?, ?, ?, ?)";
	   
	   try (Connection conn = this.connect();
	        PreparedStatement pstmt = conn.prepareStatement(sql)) {
		    	
			pstmt.setString(1, groupName);
			pstmt.setString(2, groupDescription);
			pstmt.setInt(3, groupAdmin);
			pstmt.setString(4, eventDateTime);

			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
   }
   
   /**
    * 
    * @param username
    * @param password
    * @return
    */
   public User getToken(String username, String password) {
	   String sql = "SELECT user_id, first_name, last_name, username, email_address, phone_number FROM users WHERE username = ? AND password = ?";
	   User temp = null;
	   
	   try (Connection conn = this.connect(); 
	        	 PreparedStatement pstmt = conn.prepareStatement(sql)){
	        	
	       	  	pstmt.setString(1, username);
	       	  	pstmt.setString(2, password);
	       	  	ResultSet rs = pstmt.executeQuery();
	            
	            if(rs.next()) {
	            	temp = new User(rs.getInt("user_id"),
	            					rs.getString("username"),
	            					rs.getString("first_name"),
	            					rs.getString("last_name"),
	            					rs.getString("email_address"), 
	            					rs.getString("phone_number"));
	            	
	            }
	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	        }
	        
			return temp;
	   
   }
}