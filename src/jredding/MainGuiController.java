package jredding;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class MainGuiController implements Initializable {
	
	private SqliteConnection dbConn;
	private Stage primaryStage;
	
	@FXML
	private Pane pneWrapper;
	
	public MainGuiController(SqliteConnection db, Stage prim) {
		dbConn = db;
		primaryStage = prim;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
			FXMLLoader loginLoader = new FXMLLoader(getClass().getResource("loginPage.fxml"));
			loginLoader.setController(new LoginGuiController(dbConn, primaryStage));
			Pane loginPane = loginLoader.load();
			//Pane loginPane = FXMLLoader.load(getClass().getResource("loginPage.fxml"));
			pneWrapper.getChildren().add(loginPane); 
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void handleRegister() {
		
	}

}
