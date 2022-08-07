package gui;

import domein.DomeinController;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import resources.ResourceController;

/**
 * The type Screen controller.
 */
public class ScreenController {

	/**
	 * The constant screenWidth.
	 */
	public static int screenWidth;


	/**
	 * Change to game panel.
	 *
	 * @param screen the screen
	 * @param rs     the rs
	 * @param x      the x
	 * @param y      the y
	 * @param dc     the dc
	 */
	public static void changeToGamePanel(Parent screen, ResourceController rs,int x,int y, DomeinController dc) {
		screenWidth = (int) screen.getScene().getWidth();
		GamePanel panel = new GamePanel(screen.getScene().getWidth(),screen.getScene().getHeight(),x,y,rs, dc);
		Scene scene = new Scene(panel, screen.getScene().getWidth(), screen.getScene().getHeight());
//		Scene scene = new Scene(panel, 48*16, 48*9);
		scene.getStylesheets().add(screen.getClass().getResource("/css/game.css").toExternalForm());
		Stage stage = (Stage) (screen.getScene().getWindow());
		stage.setScene(scene);
		stage.setTitle("Game");
		stage.setFullScreenExitHint("");
		stage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
		stage.setFullScreen(true);
		stage.show();
		rs.worldMusic();
		MediaView mediaview = new MediaView(rs.getMediaplayer());
		((GamePanel) scene.getRoot()).getChildren().add(mediaview);
	}

	/**
	 * Change to spel scherm.
	 *
	 * @param screen the screen
	 * @param rs     the rs
	 * @param dc     the dc
	 * @param x      the x
	 * @param y      the y
	 */
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

	/**
	 * Change to setting screen.
	 *
	 * @param screen the screen
	 * @param rs     the rs
	 */
	public static void changeToSettingScreen(WelcomeScreen screen, DomeinController dc, ResourceController rs) {
		SettingScreen setting = new SettingScreen(dc, rs, false);
		Scene scene = new Scene(setting, screen.getScene().getWidth(), screen.getScene().getHeight());
		scene.getStylesheets().add(screen.getClass().getResource("/css/application.css").toExternalForm());
		Stage stage = (Stage) (screen.getScene().getWindow());
		stage.setScene(scene);
		stage.setTitle("Settings");
		stage.show();
		MediaView mediaview = new MediaView(rs.getMediaplayer());
		((SettingScreen) scene.getRoot()).getChildren().add(mediaview);
	}

	/**
	 * Change to welcome screen.
	 *
	 * @param screen the screen
	 * @param rs     the rs
	 */
	public static void changeToWelcomeScreen(Parent screen, DomeinController dc, ResourceController rs) {
		WelcomeScreen root = new WelcomeScreen(dc, rs);
		Scene scene = new Scene(root, screen.getScene().getWidth(), screen.getScene().getHeight());
		scene.getStylesheets().add(screen.getClass().getResource("/css/application.css").toExternalForm());
		Stage stage = (Stage) (screen.getScene().getWindow());
		stage.setScene(scene);
		stage.setTitle("Home");
		stage.show();
		MediaView mediaview = new MediaView(rs.getMediaplayer());
		((WelcomeScreen) scene.getRoot()).getChildren().add(mediaview);
	}

	/**
	 * Change to game over.
	 *
	 * @param screen the screen
	 * @param rs     the rs
	 * @param dc     the dc
	 * @param x      the x
	 * @param y      the y
	 */
	public static void changeToGameOver(SpelScherm screen, ResourceController rs, DomeinController dc, int x, int y) {
		GameOverScreen gameOver = new GameOverScreen(dc, rs,x,y);
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

	/**
	 * Change to select screen.
	 *
	 * @param screen the screen
	 * @param rs     the rs
	 * @param dc     the dc
	 */
	public static void changeToSelectScreen(Parent screen, ResourceController rs, DomeinController dc) {
		SelectScreen setting = new SelectScreen(dc, rs);
		Scene scene = new Scene(setting, screen.getScene().getWidth(), screen.getScene().getHeight());
		scene.getStylesheets().add(screen.getClass().getResource("/css/application.css").toExternalForm());
		Stage stage = (Stage) (screen.getScene().getWindow());
		stage.setScene(scene);
		stage.setTitle("Select a player");
		stage.show();
		MediaView mediaview = new MediaView(rs.getMediaplayer());
		((SelectScreen) scene.getRoot()).getChildren().add(mediaview);
	}

	public static void changeToRegisterScreen(SelectScreen screen, DomeinController dc, ResourceController rs) {
		RegisterScreen setting = new RegisterScreen(dc, rs);
		Scene scene = new Scene(setting, screen.getScene().getWidth(), screen.getScene().getHeight());
		scene.getStylesheets().add(screen.getClass().getResource("/css/application.css").toExternalForm());
		Stage stage = (Stage) (screen.getScene().getWindow());
		stage.setScene(scene);
		stage.setTitle("Create a new player");
		stage.show();
		MediaView mediaview = new MediaView(rs.getMediaplayer());
		((RegisterScreen) scene.getRoot()).getChildren().add(mediaview);
	}
}
