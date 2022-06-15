package entity;

import gui.GamePanel;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Rectangle;

public class Entity {
	public int worldX;
	public int worldY;
	public int speed;
	public Rectangle solidArea = new Rectangle(GamePanel.TILESIZE * 0.02, GamePanel.TILESIZE * 0.02,
			GamePanel.TILESIZE - GamePanel.TILESIZE * 0.04, GamePanel.TILESIZE - GamePanel.TILESIZE * 0.04);
	public int solidAreaDefaultX, solidAreaDefaultY;
	public boolean collisionOn = false;
	public String direction;
	Sprite sprite;
	GamePanel gp;
	public int actionLockCounter = 0;

	public Entity(GamePanel gp) {
		this.gp = gp;
	}

	public void setAction() {
	}

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
