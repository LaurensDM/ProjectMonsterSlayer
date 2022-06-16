package gui;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.Tooltip;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;
import resources.ResourceController;

public class SettingScreen extends GridPane{

	private Slider volume;
	private Button back;
	private ResourceController rs;
	private Label title;
	private Button quit;
	public final static List<KeyCode> AZERTY = new ArrayList<>(Arrays.asList(KeyCode.Z,KeyCode.S,KeyCode.Q,KeyCode.D));
	public final static List<KeyCode> QWERTY = new ArrayList<>(Arrays.asList(KeyCode.W,KeyCode.S,KeyCode.A,KeyCode.D));
	public static List<KeyCode> keyCodes = AZERTY; 
	
	public SettingScreen(ResourceController resource, boolean game) {
		this.rs = resource;
		this.setAlignment(Pos.CENTER);
		this.setHgap(25);
		this.setVgap(50);
		
		title = new Label("Settings");
		title.setId("title");
		GridPane.setMargin(title, new Insets(0,0,100,0));
		GridPane.setColumnSpan(title, 2);
		GridPane.setHalignment(title, HPos.CENTER);
		
		back = new Button("Return");
		GridPane.setColumnSpan(back, 2);
		GridPane.setHalignment(back, HPos.CENTER);
		
		Label lblVolume = new Label("Change volume");
		
		volume = new Slider();
		volume.setValue(rs.getCurrentVolume()*100);
		
		Tooltip tooltip = new Tooltip(String.format("Volume is %.0f", volume.getValue()));
		tooltip.setShowDelay(Duration.seconds(0.1));
		tooltip.setHideDelay(Duration.ZERO);
		volume.setTooltip(tooltip);
		
		Button nextSong = new Button("Next Song");
		GridPane.setColumnSpan(nextSong, 2);
		GridPane.setHalignment(nextSong, HPos.CENTER);
		
		Button pause = new Button(rs.isPaused()?"Resume Song":"Pause Song");
		GridPane.setColumnSpan(pause, 2);
		GridPane.setHalignment(pause, HPos.CENTER);
		
		Label keys = new Label("Choose keyboard settings");
		ComboBox<String> keyList = new ComboBox<>(FXCollections.observableArrayList(new ArrayList<>(Arrays.asList("azerty","qwerty"))));
		keyList.setId("keyChoice");
		
		keyList.setOnAction(evt -> {
			int index = keyList.getSelectionModel().getSelectedIndex();
			switch (index) {
			case 0:
				keyCodes = AZERTY;
				break;

			case 1: 
				keyCodes = QWERTY;
				break;
			}
		});
		
		quit = new Button("Quit");
		GridPane.setColumnSpan(quit, 2);
		GridPane.setHalignment(quit, HPos.CENTER);
		
		quit.setOnAction(evt -> System.exit(0));
		
		volume.setOnMouseReleased(etv -> {
			rs.changeVolume(volume.getValue()/100);
			tooltip.setText(String.format("Volume is %.0f", volume.getValue()));
			volume.setTooltip(tooltip);
		});
		
		back.setOnAction(evt -> {
			if (game) {
				this.setVisible(false);
			} else {
				ScreenController.changeToWelcomeScreen(this, rs);
			}
			
		});
		
		nextSong.setOnAction(evt -> {
			rs.nextSong();
		});
		
		pause.setOnAction(evt -> {
			if (!rs.isPaused()) {
				rs.pauseMusic();
				pause.setText("Resume Song");
			} else {
				rs.unPauseMusic();
				pause.setText("Pause Song");
			}
		});
		
		this.add(title, 0, 0);
		this.add(volume, 1, 1);
		this.add(lblVolume,0, 1);
		this.add(pause, 0, 2);
		this.add(nextSong, 0, 3);
		this.add(keys, 0, 4);
		this.add(keyList, 1, 4);
		this.add(back, 0, 5);
		this.add(quit, 0, 6);
	}

}
