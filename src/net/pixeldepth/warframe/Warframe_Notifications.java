package net.pixeldepth.warframe;

import javafx.application.Platform;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class Warframe_Notifications {

	/**
	 * Polling time.  Default is to check every 180 seconds
	 *
	 * @property {Integer} interval
	 */

	public static int interval = 180000;

	/**
	 * A system tray icon is created so that we can exit and change settings.
	 * Here we set it to true so that we can continue on if the tray icon was
	 * succesfully created.
	 *
	 * @property {Boolean} system_tray_created
	 */

	private static boolean system_tray_created = false;

	/**
	 * We want to be able to display a notification when a new alert is available.
	 *
	 * Things to note:  I had to enable Windows 10 notifications in the control panel.
	 * It seems as if "balloons" are now "toasters" that slide out from the right.
	 *
	 * @property {TrayIcon} tray_icon
	 */

	public static TrayIcon tray_icon;

	private static Stage settings_stage;

	public static void init(Stage stage){
		settings_stage = stage;
		create_system_tray_icon();

		if(system_tray_created){
			//this.run_task();
		}
	}

	/**
	 * Creates a little icon in the system tray so we can exit and adjust settings.
	 */

	private static void create_system_tray_icon(){
		if(SystemTray.isSupported()){
			PopupMenu popup = new PopupMenu();
			Image image = create_image("/resources/images/warframe.png", "Warframe Notifications");

			if(image != null){
				tray_icon = new TrayIcon(image);
				SystemTray tray = SystemTray.getSystemTray();

				MenuItem settings = new MenuItem("Settings");
				MenuItem exit = new MenuItem("Exit");

				popup.add(settings);
				popup.add(exit);

				tray_icon.setPopupMenu(popup);
				tray_icon.setToolTip("Warframe Notifications");

				try {
					tray.add(tray_icon);
				} catch(AWTException e){
					e.printStackTrace();
					return;
				}

				exit.addActionListener((evt) -> {
					tray.remove(tray_icon);
					System.exit(0);
				});

				settings.addActionListener((evt) -> {
					Platform.runLater(new Runnable(){
						@Override
						public void run(){
							settings_stage.show();
							settings_stage.toFront();
						}
					});
				});

				system_tray_created = true;
			}
		}
	}

	/**
	 * Creates an image for the system tray.
	 *
	 * @param {String} path The path to the icon.
	 * @param {String} desc Description for the icon.
	 * @return {ImageIcon}
	 */

	private static Image create_image(String path, String desc){
		URL image_url = Warframe_Notifications.class.getResource(path);

		if(image_url != null){
			return (new ImageIcon(image_url, desc)).getImage();
		}

		return null;
	}

}
