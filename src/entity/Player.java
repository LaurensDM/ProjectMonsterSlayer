package entity;

import java.security.SecureRandom;

import gui.GamePanel;
import gui.ScreenController;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Rectangle;

/**
 * The type Player.
 */
public class Player extends Entity {

	/**
	 * The Screen x.
	 */
	public final int screenX;
	/**
	 * The Screen y.
	 */
	public final int screenY;
	/**
	 * The Number of item.
	 */
	int numberOfItem = 0;
	/**
	 * The Encounter.
	 */
	public boolean encounter = false;
	/**
	 * The Counter.
	 */
	public int counter = 0;
	/**
	 * The Sr.
	 */
	SecureRandom sr;

	/**
	 * Instantiates a new Player.
	 *
	 * @param gp the gp
	 * @param x  the x
	 * @param y  the y
	 */
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
		sr = new SecureRandom();
	}

	/**
	 * Sprint.
	 */
	public void sprint() {
		speed = 6;
		sprite.sprint();
	}

	/**
	 * Walk.
	 */
	public void walk() {
		speed = 4;
		sprite.walk();
	}

	/**
	 * Update.
	 *
	 * @param up    the up
	 * @param down  the down
	 * @param left  the left
	 * @param right the right
	 */
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
		encounter = false;
		if (up || down || left || right) {
			gp.collision.checkTile(this);

			int objectIndex = gp.collision.checkObject(this, true);
			pickUpObject(objectIndex);

			int npcIndex = gp.collision.checkEntity(this, gp.npc);
			interactNPC(npcIndex);

			gp.interact = false;

			if (encounter) {
				counter++;
				if (counter == 10) {
					encounter();
					counter = 0;
				}
			}

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

	/**
	 * Pick up object.
	 *
	 * @param index the index
	 */
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

	/**
	 * Interact npc.
	 *
	 * @param index the index
	 */
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

	/**
	 * Stop moving.
	 */
	public void stopMoving() {
		sprite.stopMovement();
	}

	/**
	 * Draw player.
	 *
	 * @param gc the GraphicsContext
	 */
	public void drawPlayer(GraphicsContext gc) {
		gc.drawImage(sprite.getFrame(), screenX, screenY, GamePanel.TILESIZE, GamePanel.TILESIZE);
	}

	private void encounter() {
		if (sr.nextInt(20) == 0) {
			gp.gameloop.stop();
			gp.rs.stopMusic();
			ScreenController.changeToSpelScherm(gp, gp.rs, gp.dc, worldX, worldY);
		}
	}
}
