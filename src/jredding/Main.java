package jredding;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("mainGUI.fxml"));
			SqliteConnection dbConn = new SqliteConnection();

			MainGuiController controller = new MainGuiController(dbConn, primaryStage);
			loader.setController(controller);
			
		    Parent root = loader.load();
			Scene scene = new Scene(root,400,400);
			
			primaryStage.setTitle("JRedding Gift Exchange");
			primaryStage.setScene(scene);   
			
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.show();
			

		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
