import net.pixeldepth.warframe.Settings_Application;
import net.pixeldepth.warframe.Warframe_Notifications;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Warframe extends Application {

	public static Properties props;

	public static void main(String[] args){
		props = new Properties();
		File f = new File("config.properties");


		try {
			if(!f.exists()){
				f.createNewFile();
			}

			try {
				props.load(new FileInputStream("config.properties"));
			} catch(FileNotFoundException e){
				e.printStackTrace();
			} catch(IOException e){
				e.printStackTrace();
			}
		} catch(IOException ex){
			ex.printStackTrace();
		}

		Application.launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		Warframe_Notifications.init(stage);
		Settings_Application.launch(stage, props);
	}

}
