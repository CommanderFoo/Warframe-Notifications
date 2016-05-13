package net.pixeldepth.warframe;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Settings_Application {

	public static Stage settings_stage;

	public static void launch(Stage stage) throws Exception {
		Platform.setImplicitExit(false);

		Parent root = FXMLLoader.load(Settings_Application.class.getResource("/resources/FXML/settings.fxml"));

		stage.setTitle("Warframe Notifications - Settings");

		Scene scene = new Scene(root, 730, 420);

		stage.setScene(scene);
		stage.initStyle(StageStyle.UTILITY);
		stage.setResizable(false);

		settings_stage = stage;
	}

	public static void save_settings(Settings_Controller controller){
		System.out.println("saving...");
	}

}
