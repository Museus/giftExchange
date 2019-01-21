package jredding;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class LoginGuiController {
	
	@FXML
	private TextField txtUsername;
	@FXML
	private TextField pwdPassword;
	@FXML
	private Button btnLogin;
	
	private SqliteConnection dbConn;
	private Stage primaryStage;
	
	public LoginGuiController(SqliteConnection dbPass, Stage prim) {
		dbConn = dbPass;
		primaryStage = prim;
	}
	
	public void handleRegister() {
		Stage popupStage = new Stage();
		popupStage.initModality(Modality.APPLICATION_MODAL);
        popupStage.initOwner(primaryStage);
		RegisterGuiController registerController = new RegisterGuiController(dbConn);
		FXMLLoader registerLoader = new FXMLLoader(getClass().getResource("registerPage.fxml"));
		registerLoader.setController(registerController);
		
		try {
	        Scene popupScene = new Scene((Parent)registerLoader.load(), 500, 500);

			System.out.println("Running");
			popupStage.setScene(popupScene);
			popupStage.show();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void handleLogin() {
		String username = txtUsername.getText();
		if(dbConn.userExists(username)) {
			System.out.println("User exists.");
		} else {
			System.out.println("User does not exist.");
		}
	}
}
