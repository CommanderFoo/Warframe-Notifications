package net.pixeldepth.warframe;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

public class Settings_Application {

	public static Stage settings_stage;

	public static Properties properties;

	public static void launch(Stage stage, Properties props) throws Exception {
		properties = props;

		Platform.setImplicitExit(false);

		Parent root = FXMLLoader.load(Settings_Application.class.getResource("/resources/FXML/settings.fxml"));

		stage.setTitle("Warframe Notifications - Settings");

		Scene scene = new Scene(root, 730, 462);

		stage.setScene(scene);
		stage.initStyle(StageStyle.UTILITY);
		stage.setResizable(false);

		settings_stage = stage;
	}

	public static void save_settings(HashMap<String, Boolean> settings_values, Settings_Controller controller){
		StringBuilder match_against = new StringBuilder();

		settings_values.forEach((k, v) -> {
			properties.setProperty(k, (v)? "1": "0");

			if(v){
				if(match_against.length() > 0){
					match_against.append("|");
				}

				match_against.append(k);
			}
		});

		try {
			properties.store(new FileOutputStream("config.properties"), null);
			controller.reset_save_button();
			Warframe_Notifications.run_task(match_against);
		} catch(FileNotFoundException e){
			e.printStackTrace();
		} catch(IOException e){
			e.printStackTrace();
		}
	}

	public static void save_properties(){
		try {
			properties.store(new FileOutputStream("config.properties"), null);
		} catch(FileNotFoundException e){
			e.printStackTrace();
		} catch(IOException e){
			e.printStackTrace();
		}
	}

	public static long last_alert_time(){
		if(properties.getProperty("last_alert") != null){
			return Long.valueOf(properties.getProperty("last_alert"));
		}

		return 0;
	}

}
