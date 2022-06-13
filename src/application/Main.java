package application;
	
import java.security.SecureRandom;

import domein.DomeinController;
import gui.ScreenController;
import gui.SpelScherm;
import gui.WelcomeScreen;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import resources.ResourceController;




public class Main extends Application {
	public static double rowMultiplier;
	
	@Override
	public void start(Stage primaryStage) {
		try {
		primaryStage.setOnCloseRequest(evt -> System.exit(0));
		SecureRandom sr = new SecureRandom();
		DomeinController dc = new DomeinController();
		ResourceController resources = new ResourceController();
		WelcomeScreen screen = new WelcomeScreen(resources);
		Scene scene = new Scene(screen);
		scene.getStylesheets().add(getClass().getResource("/css/application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.setTitle("Monster Slayer");
		primaryStage.setMaximized(true);
		primaryStage.show();
		resources.playMusic(false);
		ScreenController.screenWidth = (int) screen.getScene().getWidth();
		rowMultiplier = screen.getScene().getWidth()/screen.getScene().getHeight();
		MediaView mediaview = new MediaView(resources.getMediaplayer());
		((WelcomeScreen) scene.getRoot()).getChildren().add(mediaview);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
