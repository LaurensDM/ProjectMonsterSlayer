package application;

import gui.GamePanel;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class UI {
	
	GamePanel gp;
	Font arial = new Font("Times New Roman",40);

	public UI(GamePanel gp) {
		this.gp = gp;
	}
	
	public void draw(GraphicsContext gc) {
		gc.setFont(arial);
		gc.setFill(Color.WHITE);
		gc.fillText("Hello there", 0, 100,200);
	}

}
