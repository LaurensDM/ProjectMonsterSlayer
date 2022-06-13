package gui;



import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;
import resources.ResourceController;

public class SettingScreen extends GridPane{

	private Slider volume;
	private Button back;
	private ResourceController rs;
	private Label title;
	
	public SettingScreen(ResourceController resource) {
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
		volume.setOnMouseReleased(etv -> {
			rs.changeVolume(volume.getValue()/100);
			tooltip.setText(String.format("Volume is %.0f", volume.getValue()));
			volume.setTooltip(tooltip);
		});
		back.setOnAction(evt -> {
			ScreenController.changeToWelcomeScreen(this, rs);
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
		this.add(back, 0, 4);
	}

}
