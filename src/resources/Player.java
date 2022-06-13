package resources;

import gui.GamePanel;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Rectangle;

public class Player extends Entity {

	Sprite sprite;
	GamePanel gp;

	public final int screenX;
	public final int screenY;

	public Player(GamePanel gp, int x, int y) {
		this.gp = gp;
		sprite = new Sprite();

		screenX = gp.screenWidth / 2 - (GamePanel.TILESIZE/2);
		screenY = gp.screenHeight / 2 - (GamePanel.TILESIZE/2);
		System.err.println("width: "+gp.screenWidth+" height: "+gp.screenHeight);
		System.err.println(screenX +" "+screenY);
		
		speed = 4;
		this.worldX = x;
		this.worldY = y;
		double newSize = GamePanel.TILESIZE / 1.5;
		solidArea = new Rectangle((GamePanel.TILESIZE - newSize) / 2, GamePanel.TILESIZE - newSize, newSize, newSize);
	}

	public void sprint() {
		speed = 6;
		sprite.sprint();
	}
	
	public void walk() {
		speed=4;
		sprite.walk();
	}
	
	public void update(boolean up, boolean down, boolean left, boolean right) {

		if (up) {
			direction = "up";
			sprite.moveUp();

		}
		if (down) {
			direction = "down";
			sprite.moveDown();

		}
		if (left) {
			direction = "left";
			sprite.moveLeft();

		}
		if (right) {
			direction = "right";
			sprite.moveRight();

		}

		collisionOn = false;
		if (up || down || left || right) {
			gp.collision.checkTile(this);

			if (collisionOn == false) {
				switch (direction) {
				case "up":
					worldY -= speed;
					break;
				case "down":
					worldY += speed;
					break;
				case "left":
					worldX -= speed;
					break;
				case "right":
					worldX += speed;
					break;
				}
			}
		}
		sprite.updateSpriteCounter();
	}

	public void stopMoving() {
		sprite.stopMovement();
	}

	public void drawPlayer(GraphicsContext gc) {
		gc.drawImage(sprite.getFrame(), screenX, screenY, GamePanel.TILESIZE, GamePanel.TILESIZE);
	}

//	private void encounter() {
//		
//	}
}
