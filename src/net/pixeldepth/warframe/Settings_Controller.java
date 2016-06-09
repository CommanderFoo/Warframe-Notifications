package net.pixeldepth.warframe;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.*;

public class Settings_Controller implements Initializable {

	@FXML
	private BorderPane root_node;

	@FXML
	private GridPane grid_auras;

	@FXML
	private CheckBox corrosive_projection;

	@FXML
	private CheckBox dead_eye;

	@FXML
	private CheckBox emp_aura;

	@FXML
	private CheckBox loot_detector;

	@FXML
	private CheckBox physique;

	@FXML
	private CheckBox rejuvenation;

	@FXML
	private CheckBox rifle_amp;

	@FXML
	private CheckBox enemy_radar;

	@FXML
	private CheckBox pistol_scavenger;

	@FXML
	private CheckBox rifle_scavenger;

	@FXML
	private CheckBox sniper_scavenger;

	@FXML
	private CheckBox shotgun_scavenger;

	@FXML
	private CheckBox shield_disruption;

	@FXML
	private CheckBox speed_holster;

	@FXML
	private CheckBox sprint_boost;

	@FXML
	private CheckBox steel_charge;

	@FXML
	private CheckBox enemy_sense;

	@FXML
	private CheckBox energy_siphon;

	@FXML
	private CheckBox infested_impedance;

	@FXML
	private CheckBox theifs_wit;

	@FXML
	private GridPane grid_blueprints;

	@FXML
	private CheckBox orokin_catalyst;

	@FXML
	private CheckBox orokin_reactor;

	@FXML
	private CheckBox forma;

	@FXML
	private CheckBox exilus_adapter;

	@FXML
	private GridPane grid_misc;

	@FXML
	private CheckBox fusion;

	@FXML
	private CheckBox kubrow_egg;

	@FXML
	private GridPane grid_mods;

	@FXML
	private CheckBox accelerated_blast;

	@FXML
	private CheckBox animal_instinct;

	@FXML
	private CheckBox armored_agility;

	@FXML
	private CheckBox blaze;

	@FXML
	private CheckBox constitution;

	@FXML
	private CheckBox focus_energy;

	@FXML
	private CheckBox fortitude;

	@FXML
	private CheckBox hammer_shot;

	@FXML
	private CheckBox ice_storm;

	@FXML
	private CheckBox lethal_torrent;

	@FXML
	private CheckBox rendeng_strike;

	@FXML
	private CheckBox seeking_fury;

	@FXML
	private CheckBox shred;

	@FXML
	private CheckBox stunning_speed;

	@FXML
	private CheckBox vigor;

	@FXML
	private CheckBox wildfire;

	@FXML
	private GridPane grid_resources;

	@FXML
	private CheckBox alloy_plate;

	@FXML
	private CheckBox circuits;

	@FXML
	private CheckBox control_module;

	@FXML
	private CheckBox ferrite;

	@FXML
	private CheckBox gallium;

	@FXML
	private CheckBox morphics;

	@FXML
	private CheckBox neural_sensors;

	@FXML
	private CheckBox neurodes;

	@FXML
	private CheckBox nitain;

	@FXML
	private CheckBox orokin_cell;

	@FXML
	private CheckBox rubedo;

	@FXML
	private CheckBox salvage;

	@FXML
	private CheckBox plastids;

	@FXML
	private CheckBox polymer_bundle;

	@FXML
	private CheckBox argon_crystal;

	@FXML
	private CheckBox cryotic;

	@FXML
	private CheckBox oxium;

	@FXML
	private CheckBox tellurium;

	@FXML
	private GridPane grid_vauban;

	@FXML
	private CheckBox vauban_helmet;

	@FXML
	private CheckBox vauban_chassis;

	@FXML
	private CheckBox vauban_systems;

	@FXML
	private GridPane grid_void_keys;

	@FXML
	private CheckBox capture_i;

	@FXML
	private CheckBox capture_ii;

	@FXML
	private CheckBox capture_iii;

	@FXML
	private CheckBox capture_iv;

	@FXML
	private CheckBox defense_i;

	@FXML
	private CheckBox defense_ii;

	@FXML
	private CheckBox defense_iii;

	@FXML
	private CheckBox defense_iv;

	@FXML
	private CheckBox exterminate_i;

	@FXML
	private CheckBox exterminate_ii;

	@FXML
	private CheckBox exterminate_iii;

	@FXML
	private CheckBox exterminate_iv;

	@FXML
	private CheckBox mobile_defense_i;

	@FXML
	private CheckBox mobile_defense_ii;

	@FXML
	private CheckBox mobile_defense_iii;

	@FXML
	private CheckBox mobile_defense_iv;

	@FXML
	private CheckBox sabotage_i;

	@FXML
	private CheckBox sabotage_ii;

	@FXML
	private CheckBox sabotage_iii;

	@FXML
	private CheckBox sabotage_iv;

	@FXML
	private CheckBox interception_i;

	@FXML
	private CheckBox interception_ii;

	@FXML
	private CheckBox interception_iii;

	@FXML
	private CheckBox interception_iv;

	@FXML
	private CheckBox survival_i;

	@FXML
	private CheckBox survival_ii;

	@FXML
	private CheckBox survival_iii;

	@FXML
	private CheckBox survival_iv;

	@FXML
	private GridPane grid_weapons;

	@FXML
	private CheckBox ceramic_dagger;

	@FXML
	private CheckBox dark_dagger;

	@FXML
	private CheckBox dark_sword;

	@FXML
	private CheckBox glaive;

	@FXML
	private CheckBox heat_dagger;

	@FXML
	private CheckBox heat_sword;

	@FXML
	private CheckBox jaw_sword;

	@FXML
	private CheckBox pangolin_sword;

	@FXML
	private CheckBox plasma_sword;

	@FXML
	private Button save;

	@FXML
	private Button close;

	@FXML
	private RadioButton platform_ps4;

	@FXML
	private ToggleGroup platform;

	@FXML
	private RadioButton platform_xbox;

	@FXML
	private RadioButton platform_pc;

	@FXML
	void close_settings(ActionEvent event){
		Settings_Application.settings_stage.close();
	}

	private List<ObservableList<Node>> combined_lists;

	@FXML
	void save_settings(ActionEvent event){
		HashMap<String, Boolean> settings_map = new HashMap<String, Boolean>();

		for(ObservableList<Node> nodes : this.combined_lists){
			nodes.forEach(n -> {
				CheckBox box = (CheckBox) n;
				String key = n.getId();

				settings_map.put(key, box.isSelected());
			});
		}

		Settings_Application.save_settings(settings_map, this);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources){
		this.combined_lists = Arrays.asList(

			this.grid_auras.getChildren(),
			this.grid_blueprints.getChildren(),
			this.grid_misc.getChildren(),
			this.grid_mods.getChildren(),
			this.grid_resources.getChildren(),
			this.grid_vauban.getChildren(),
			this.grid_void_keys.getChildren(),
			this.grid_weapons.getChildren()

		);

		StringBuilder match_against = new StringBuilder();

		for(ObservableList<Node> nodes : this.combined_lists){
			nodes.forEach(n -> {
				CheckBox box = (CheckBox) n;
				String key = n.getId();

				String prop = Settings_Application.properties.getProperty(key);

				if(prop != null && prop.equals("1")){
					box.setSelected(true);

					if(match_against.length() > 0){
						match_against.append("|");
					}

					match_against.append(key);
				}

				box.selectedProperty().addListener(o -> {
					this.save.setStyle("-fx-font-weight: bold");
				});
			});
		}

		//String ps4 = Settings_Application.properties.getProperty("platform_ps4");
		String xbox = Settings_Application.properties.getProperty("platform_xbox");
		String pc = Settings_Application.properties.getProperty("platform_pc");

		if(pc != null && pc.equals("1")){
			this.platform.selectToggle(this.platform_pc);
		} else if(xbox != null && xbox.equals("1")){
			this.platform.selectToggle(this.platform_xbox);
		} else {
			this.platform.selectToggle(this.platform_ps4);
		}

		this.platform.selectedToggleProperty().addListener((o, ov, nv) -> {
			RadioButton old_radio = (RadioButton) ov;
			RadioButton new_radio = (RadioButton) nv;

			String old_setting = old_radio.getId();
			String new_setting = new_radio.getId();

			Settings_Application.properties.setProperty(old_setting, "0");
			Settings_Application.properties.setProperty(new_setting, "1");

			this.save.setStyle("-fx-font-weight: bold");
		});

		Warframe_Notifications.run_task(match_against);
	}

	public void reset_save_button(){
		this.save.setStyle("-fx-font-weight: normal");
	}


}
