package jredding;

/**
 * This class is used to hold a user's information. 
 * @author johnr
 *
 */
public class User {
	private int userID;
	private String userName;
	private String firstName;
	private String lastName;
	private String emailAddress;
	private String phoneNumber;
	
	public User(int user_id, String user_name, String first_name, String last_name, String email_address, String phone_number) {
		userID = user_id;
		userName = user_name;
		firstName = first_name;
		lastName = last_name;
		emailAddress = email_address;
		phoneNumber = phone_number;
	}

	public int getUserID() { return userID;	}
	public String getUserName() { return userName; }
	public String getFirstName() { return firstName; }
	public String getLastName() { return lastName; }
	public String getEmailAddress() { return emailAddress; }
	public String getPhoneNumber() { return phoneNumber; }

	public void setUserID(int userID) { this.userID = userID; }
	public void setUserName(String userName) { this.userName = userName; }
	public void setFirstName(String firstName) { this.firstName = firstName; }
	public void setLastName(String lastName) { this.lastName = lastName; }
	public void setEmailAddress(String emailAddress) { this.emailAddress = emailAddress; }
	public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
	
	public String toString() {
		StringBuilder temp = new StringBuilder();
		temp.append("Name: " + firstName + " " + lastName);
		temp.append("\nUsername: " + userName);
		temp.append("\nEmail: " + emailAddress);
		temp.append("\nPhone Number: " + phoneNumber);
		
		return temp.toString();
	}
}
