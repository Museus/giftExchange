package jredding;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class RegisterGuiController {
	
	private SqliteConnection dbConn;
	
	@FXML
	private TextField txtFirstName;
	@FXML
	private TextField txtLastName;
	@FXML
	private TextField txtUsername;
	@FXML
	private TextField txtEmailAddress;
	@FXML
	private TextField txtPhoneNumber;
	@FXML
	private PasswordField pwdPassword;
	@FXML
	private PasswordField pwdRePassword;
	@FXML
	private Button btnRegister;

	public RegisterGuiController(SqliteConnection dbPass) {
		dbConn = dbPass;
	}
	
	public void handleRegister() {
		String firstName = txtFirstName.getText();
		String lastName = txtLastName.getText();
		String username = txtUsername.getText();
		String emailAddress = txtEmailAddress.getText();
		String phoneNumber = txtPhoneNumber.getText();
		String password = pwdPassword.getText();
		
		boolean[] dup = dbConn.userExists(username, emailAddress);
		if(!dup[0] && !dup[1]) {
			dbConn.createUser(firstName, lastName, username, emailAddress, phoneNumber, password);
			Stage stage = (Stage) btnRegister.getScene().getWindow();
		    stage.close();
		}
		
		if(dup[0]) {
			System.out.println("Found this username");
		}
		
		if(dup[1]) {
			System.out.println("Found this email");
		}
	}
}
