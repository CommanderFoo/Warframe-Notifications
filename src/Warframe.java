import javafx.application.Application;
import javafx.stage.Stage;
import net.pixeldepth.warframe.Settings_Application;
import net.pixeldepth.warframe.Warframe_Notifications;

import java.io.IOException;
import java.util.Properties;

public class Warframe extends Application {

	public static void main(String[] args){
		Properties prop = new Properties();

		try {
			prop.load(Warframe.class.getResource("/resources/config.properties").openStream());

			System.out.println(prop.getProperty("apples"));
		} catch(IOException e){
			e.printStackTrace();
		}

		Application.launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		Warframe_Notifications.init(stage);
		Settings_Application.launch(stage);
	}
}
