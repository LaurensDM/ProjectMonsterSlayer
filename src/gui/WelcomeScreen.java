package gui;

import domein.DomeinController;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import resources.ResourceController;

public class WelcomeScreen extends VBox {

	private Label title;
	private Button play;
	private Button settings;
	private ResourceController rs;
	public static double rowMultiplier;
	
	public WelcomeScreen(ResourceController rs) {
		this.rs = rs;
		this.setAlignment(Pos.CENTER);
		this.setSpacing(50);
		title = new Label("Monster Slayer");
		title.setId("title");
		play = new Button("Play");
		settings = new Button("Settings");
		
		play.setOnAction(evt -> {
			ScreenController.changeToSelectScreen(this, rs, new DomeinController());
			rowMultiplier = this.getWidth()/this.getHeight();
//			ScreenController.changeToGamePanel(this,rs,GamePanel.TILESIZE*23,GamePanel.TILESIZE*21);
			
		});
		settings.setOnAction(etv -> {
			ScreenController.changeToSettingScreen(this, rs);
		});
		
		this.getChildren().addAll(title,play,settings);
	}

}
