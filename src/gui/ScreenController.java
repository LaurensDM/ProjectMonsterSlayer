package gui;

import domein.DomeinController;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import resources.ResourceController;

public class ScreenController {
	
	public static int screenWidth;
	

	public static void changeToGamePanel(Parent screen, ResourceController rs,int x,int y) {
		screenWidth = (int) screen.getScene().getWidth();
		GamePanel panel = new GamePanel(screen.getScene().getWidth(),screen.getScene().getHeight(),x,y,rs);
		Scene scene = new Scene(panel, screen.getScene().getWidth(), screen.getScene().getHeight());
//		Scene scene = new Scene(panel, 48*16, 48*9);
		scene.getStylesheets().add(screen.getClass().getResource("/css/application.css").toExternalForm());
		Stage stage = (Stage) (screen.getScene().getWindow());
		stage.setScene(scene);
		stage.setTitle("Game");
		stage.setFullScreenExitHint("");
		stage.setFullScreen(true);
		stage.show();
		rs.worldMusic();
		MediaView mediaview = new MediaView(rs.getMediaplayer());
		((GamePanel) scene.getRoot()).getChildren().add(mediaview);
	}
	
	public static void changeToSpelScherm(Parent screen, ResourceController rs, DomeinController dc, int x, int y) {
		AnimationScreen spel = new AnimationScreen(dc, rs,x,y );
		Scene scene = new Scene(spel, screen.getScene().getWidth(), screen.getScene().getHeight());
		scene.getStylesheets().add(screen.getClass().getResource("/css/game.css").toExternalForm());
		Image img = new Image(spel.getClass().getResourceAsStream("/images/background2.gif"));
		BackgroundImage bImg = new BackgroundImage(img, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(100, 100, true, true, false, true));
		Background b = new Background(bImg);
		spel.setBackground(b);
		Stage stage = (Stage) (screen.getScene().getWindow());
		stage.setScene(scene);
		stage.setTitle("MonsterSlayer");
		stage.setFullScreenExitHint("");
		stage.setFullScreen(true);
		stage.show();
		rs.playMusic(false);
		MediaView mediaview = new MediaView(rs.getMediaplayer());
		((AnimationScreen) scene.getRoot()).getChildren().add(mediaview);
	}

	public static void changeToSettingScreen(WelcomeScreen screen, ResourceController rs) {
		SettingScreen setting = new SettingScreen(rs);
		Scene scene = new Scene(setting, screen.getScene().getWidth(), screen.getScene().getHeight());
		scene.getStylesheets().add(screen.getClass().getResource("/css/application.css").toExternalForm());
		Stage stage = (Stage) (screen.getScene().getWindow());
		stage.setScene(scene);
		stage.setTitle("Settings");
		stage.show();
		MediaView mediaview = new MediaView(rs.getMediaplayer());
		((SettingScreen) scene.getRoot()).getChildren().add(mediaview);
	}

	public static void changeToWelcomeScreen(Parent screen, ResourceController rs) {
		WelcomeScreen root = new WelcomeScreen(rs);
		Scene scene = new Scene(root, screen.getScene().getWidth(), screen.getScene().getHeight());
		scene.getStylesheets().add(screen.getClass().getResource("/css/application.css").toExternalForm());
		Stage stage = (Stage) (screen.getScene().getWindow());
		stage.setScene(scene);
		stage.setTitle("Home");
		stage.show();
		MediaView mediaview = new MediaView(rs.getMediaplayer());
		((WelcomeScreen) scene.getRoot()).getChildren().add(mediaview);
	}

	public static void changeToGameOver(SpelScherm screen, ResourceController rs, DomeinController dc) {
		GameOverScreen gameOver = new GameOverScreen(dc, rs);
		Scene scene = new Scene(gameOver, screen.getScene().getWidth(), screen.getScene().getHeight());
		scene.getStylesheets().add(screen.getClass().getResource("/css/game.css").toExternalForm());
		Image img = new Image(gameOver.getClass().getResourceAsStream("/images/gameOver.gif"));
		BackgroundImage bImg = new BackgroundImage(img, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(100, 100, true, true, true, false));
		Background b = new Background(bImg);
		gameOver.setBackground(b);
		Stage stage = (Stage) (screen.getScene().getWindow());
		stage.setScene(scene);
		stage.setTitle("Game Over");
		stage.setFullScreenExitHint("");
		stage.setFullScreen(true);
		stage.show();
		MediaView mediaview = new MediaView(rs.getMediaplayer());
		((GameOverScreen) scene.getRoot()).getChildren().add(mediaview);
		
	}
	
	public static void changeToSelectScreen(Parent screen, ResourceController rs, DomeinController dc) {
		SelectScreen setting = new SelectScreen(dc,rs);
		Scene scene = new Scene(setting, screen.getScene().getWidth(), screen.getScene().getHeight());
		scene.getStylesheets().add(screen.getClass().getResource("/css/application.css").toExternalForm());
		Stage stage = (Stage) (screen.getScene().getWindow());
		stage.setScene(scene);
		stage.setTitle("Settings");
		stage.show();
		MediaView mediaview = new MediaView(rs.getMediaplayer());
		((SelectScreen) scene.getRoot()).getChildren().add(mediaview);
	}
}
