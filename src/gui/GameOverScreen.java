package gui;



import domein.DomeinController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import resources.ResourceController;

/**
 * The type Game over screen.
 */
public class GameOverScreen extends VBox {

	/**
	 * Instantiates a new Game over screen.
	 *
	 * @param dc the DomeinController
	 * @param rs the ResourceController
	 * @param x  the worldX
	 * @param y  the worldY
	 */
	public GameOverScreen(DomeinController dc,ResourceController rs, int x, int y) {
		this.setAlignment(Pos.BOTTOM_CENTER);
		this.setId("game-over");
		rs.gameOver();
		Button tryAgain = new Button("Restart");
		tryAgain.setId("restart");
		tryAgain.setOnAction(evt -> {
			ScreenController.changeToSpelScherm(this, rs, dc,x,y);

			
		});
		VBox.setMargin(tryAgain, new Insets(0, 0, 100, 0));
		this.getChildren().add(tryAgain);
	}

}
