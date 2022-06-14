package resources;

import gui.GamePanel;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class CollisionChecker {

	private GamePanel gp;

	public CollisionChecker(GamePanel gp) {
		// TODO Auto-generated constructor stub
		this.gp = gp;
	}

	public void checkTile(Entity entity) {

		int leftWorldX = (int) (entity.worldX + entity.solidArea.getX());
		int rightWorldX = (int) (entity.worldX + entity.solidArea.getX() + entity.solidArea.getWidth());
		int topWorldY = (int) (entity.worldY + entity.solidArea.getY());
		int bottomWorldY = (int) (entity.worldY + entity.solidArea.getY() + entity.solidArea.getHeight());

		int leftCol = leftWorldX / GamePanel.TILESIZE;
		int rightCol = rightWorldX / GamePanel.TILESIZE;
		int topRow = topWorldY / GamePanel.TILESIZE;
		int bottomRow = bottomWorldY / GamePanel.TILESIZE;

		int tileNum1, tileNum2;

		switch (entity.direction) {
		case "up":
			topRow = (topWorldY - entity.speed) / GamePanel.TILESIZE;
			tileNum1 = gp.tileM.map[leftCol][topRow];
			tileNum2 = gp.tileM.map[rightCol][topRow];
			if (gp.tileM.tiles[tileNum1].isCollision() || gp.tileM.tiles[tileNum2].isCollision()) {
				entity.collisionOn = true;
			}
			break;
		case "down":
			bottomRow = (bottomWorldY + entity.speed) / GamePanel.TILESIZE;
			tileNum1 = gp.tileM.map[leftCol][bottomRow];
			tileNum2 = gp.tileM.map[rightCol][bottomRow];
			if (gp.tileM.tiles[tileNum1].isCollision() || gp.tileM.tiles[tileNum2].isCollision()) {
				entity.collisionOn = true;
			}
			break;
		case "left":
			leftCol = (leftWorldX - entity.speed) / GamePanel.TILESIZE;
			tileNum1 = gp.tileM.map[leftCol][topRow];
			tileNum2 = gp.tileM.map[leftCol][bottomRow];
			if (gp.tileM.tiles[tileNum1].isCollision() || gp.tileM.tiles[tileNum2].isCollision()) {
				entity.collisionOn = true;
			}
			break;
		case "right":
			rightCol = (rightWorldX + entity.speed) / GamePanel.TILESIZE;
			tileNum1 = gp.tileM.map[rightCol][topRow];
			tileNum2 = gp.tileM.map[rightCol][bottomRow];
			if (gp.tileM.tiles[tileNum1].isCollision() || gp.tileM.tiles[tileNum2].isCollision()) {
				entity.collisionOn = true;
			}
			break;
		}
	}

	public int checkObject(Entity entity, boolean player) {
		int index = 999;

		for (int i = 0; i < gp.obj.length; i++) {
			if (gp.obj[i] != null) {
				entity.solidArea.setX(entity.worldX + entity.solidArea.getX());
				entity.solidArea.setY(entity.worldY + entity.solidArea.getY());

				gp.obj[i].solidArea.setX(gp.obj[i].worldX + gp.obj[i].solidArea.getX());
				gp.obj[i].solidArea.setY(gp.obj[i].worldY + gp.obj[i].solidArea.getY());

				switch (entity.direction) {

				case "up":
					entity.solidArea.setY(entity.solidArea.getY() - entity.speed);

					if (entity.solidArea.getBoundsInParent().intersects(gp.obj[i].solidArea.getBoundsInParent())) {
						if (gp.obj[i].collision == true) {
							entity.collisionOn = true;
						}
						if (player) {
							index = i;
						}
					}
					break;
				case "down":
					entity.solidArea.setY(entity.solidArea.getY() + entity.speed);

					if (entity.solidArea.getBoundsInParent().intersects(gp.obj[i].solidArea.getBoundsInParent())) {
						if (gp.obj[i].collision == true) {
							entity.collisionOn = true;
						}
						if (player) {
							index = i;
						}
					}
					break;
				case "left":
					entity.solidArea.setX(entity.solidArea.getX() - entity.speed);

					if (entity.solidArea.getBoundsInParent().intersects(gp.obj[i].solidArea.getBoundsInParent())) {
						if (gp.obj[i].collision == true) {
							entity.collisionOn = true;
						}
						if (player) {
							index = i;
						}
					}
					break;
				case "right":
					entity.solidArea.setX(entity.solidArea.getX() + entity.speed);

					if (entity.solidArea.getBoundsInParent().intersects(gp.obj[i].solidArea.getBoundsInParent())) {
						if (gp.obj[i].collision == true) {
							entity.collisionOn = true;
						}
						if (player) {
							index = i;
						}
					}
					break;
				}

				entity.solidArea.setX(entity.solidAreaDefaultX);
				entity.solidArea.setY(entity.solidAreaDefaultY);
				gp.obj[i].solidArea.setX(gp.obj[i].solidAreaDefaultX);
				gp.obj[i].solidArea.setY(gp.obj[i].solidAreaDefaultY);
			}
		}

		return index;
	}

}
