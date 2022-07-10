package entity;

import gui.GamePanel;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Rectangle;


/**
 * The type Entity.
 */
//superclass for any moving entity
public class Entity {
	/**
	 * The World x.
	 */
	public int worldX;
	/**
	 * The World y.
	 */
	public int worldY;
	/**
	 * The Speed.
	 */
	public int speed;
	/**
	 * The Solid area.
	 */
	public Rectangle solidArea = new Rectangle(GamePanel.TILESIZE * 0.02, GamePanel.TILESIZE * 0.02,
			GamePanel.TILESIZE - GamePanel.TILESIZE * 0.04, GamePanel.TILESIZE - GamePanel.TILESIZE * 0.04);
	/**
	 * The Solid area default x.
	 */
	public int solidAreaDefaultX, /**
	 * The Solid area default y.
	 */
	solidAreaDefaultY;
	/**
	 * The Collision on.
	 */
	public boolean collisionOn = false;
	/**
	 * The Direction.
	 */
	public String direction;
	/**
	 * The Sprite.
	 */
	Sprite sprite;
	/**
	 * The Gp.
	 */
	GamePanel gp;
	/**
	 * The Action lock counter.
	 */
	public int actionLockCounter = 0;

	/**
	 * Instantiates a new Entity.
	 *
	 * @param gp the gp
	 */
	public Entity(GamePanel gp) {
		this.gp = gp;
	}

	/**
	 * Sets action.
	 */
	public void setAction() {
	}

	/**
	 * Update.
	 */
	public void update() {

		setAction();

		collisionOn = false;
		gp.collision.checkTile(this);
		gp.collision.checkObject(this, false);
		gp.collision.checkPlayer(this);

		if (collisionOn == false) {
			switch (direction) {
			case "up":
				sprite.moveUp();
				worldY -= speed;
				break;
			case "down":
				sprite.moveDown();
				worldY += speed;
				break;
			case "left":
				sprite.moveLeft();
				worldX -= speed;
				break;
			case "right":
				sprite.moveRight();
				worldX += speed;
				break;
			}
		}

		sprite.updateSpriteCounter();

	}

	/**
	 * Draw.
	 *
	 * @param gc the GraphicsContext
	 */
	public void draw(GraphicsContext gc) {
		int screenX = worldX - gp.player.worldX + gp.player.screenX;
		int screenY = worldY - gp.player.worldY + gp.player.screenY;

		if (worldX + GamePanel.TILESIZE > gp.player.worldX - gp.player.screenX
				&& worldX - GamePanel.TILESIZE < gp.player.worldX + gp.player.screenX
				&& worldY + GamePanel.TILESIZE > gp.player.worldY - gp.player.screenY
				&& worldY - GamePanel.TILESIZE < gp.player.worldY + gp.player.screenY) {

			gc.drawImage(sprite.getFrame(), screenX, screenY, GamePanel.TILESIZE, GamePanel.TILESIZE);
		}
	}
//		public void registerCollision() {
//			collisionOn = true;
//		}
}
