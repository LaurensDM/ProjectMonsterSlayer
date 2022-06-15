package entity;

import gui.GamePanel;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Rectangle;

public class Player extends Entity {

	public final int screenX;
	public final int screenY;
	int numberOfItem = 0;

	public Player(GamePanel gp, int x, int y) {
		super(gp);
		sprite = new Sprite("wizard");

		screenX = gp.screenWidth / 2 - (GamePanel.TILESIZE / 2);
		screenY = gp.screenHeight / 2 - (GamePanel.TILESIZE / 2);

		speed = 4;
		this.worldX = x;
		this.worldY = y;
		double newSize = GamePanel.TILESIZE / 1.5;
		solidArea = new Rectangle((GamePanel.TILESIZE - newSize) / 2, GamePanel.TILESIZE - newSize, newSize, newSize);
		solidAreaDefaultX = (int) solidArea.getX();
		solidAreaDefaultY = (int) solidArea.getY();
	}

	public void sprint() {
		speed = 6;
		sprite.sprint();
	}

	public void walk() {
		speed = 4;
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

			int objectIndex = gp.collision.checkObject(this, true);
			pickUpObject(objectIndex);

			int npcIndex = gp.collision.checkEntity(this, gp.npc);
			interactNPC(npcIndex);
			
			gp.interact = false;

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

	public void pickUpObject(int index) {

		if (index != 999) {

			String name = gp.obj[index].name;

			switch (name) {
			case "":
				numberOfItem++;
				gp.obj[index] = null;
				break;

			}

		}
	}

	public void interactNPC(int index) {
		if (index != 999) {
			gp.showNotification("Press E to interact with NPC.");
			if (gp.interact) {
				switch (direction) {
				case "up":
					gp.npc[index].direction = "down";
					gp.npc[index].sprite.moveDown();
					break;
				case "down":
					gp.npc[index].direction = "up";
					gp.npc[index].sprite.moveUp();
					break;
				case "left":
					gp.npc[index].direction = "right";
					gp.npc[index].sprite.moveRight();
					break;
				case "right":
					gp.npc[index].direction = "left";
					gp.npc[index].sprite.moveLeft();
					break;
				}
				gp.showDialogue("A good day to you!");
			}
			
		}
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