package se.project.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import se.project.database.Context;
import se.project.database.MySQLConnection;

public class App extends Application {

	public App() {

		Context.selectDataBase(new MySQLConnection());
	}

	public static void main(String[] args) {
		launch(args);
	}

	public void start(Stage stage) throws Exception {
		Parent root = (Parent) FXMLLoader.load(getClass().getResource("/se/project/gui/login/splash.fxml"));
		Scene scene = new Scene(root);
		Image icon = new Image("se/project/image/Bicycle-icon.png");
		stage.getIcons().add(icon);
		stage.setTitle("EcoBikeRental");
		stage.setScene(scene);
		stage.show();
	}

}
