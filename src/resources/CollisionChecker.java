package resources;

import entity.Entity;
import entity.Player;
import gui.GamePanel;

/**
 * The type Collision checker.
 */
public class CollisionChecker {

	private GamePanel gp;

	/**
	 * Instantiates a new Collision checker.
	 *
	 * @param gp the GamePanel
	 */
	public CollisionChecker(GamePanel gp) {
		this.gp = gp;
	}

	/**
	 * Check tile.
	 *
	 * @param entity the entity
	 */
// collision with tile
	public void checkTile(Entity entity) {

		int leftWorldX = (int) (entity.worldX + entity.solidArea.getX());
		int rightWorldX = (int) (entity.worldX + entity.solidArea.getX() + entity.solidArea.getWidth());
		int topWorldY = (int) (entity.worldY + entity.solidArea.getY());
		int bottomWorldY = (int) (entity.worldY + entity.solidArea.getY() + entity.solidArea.getHeight()
				- GamePanel.TILESIZE * 0.01);

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
			if (gp.tileM.tiles[tileNum1].isEncounter() || gp.tileM.tiles[tileNum2].isEncounter()) {
				if (entity instanceof Player) {
					((Player) entity).encounter = true;
				}
			}
			break;
		case "down":
			bottomRow = (bottomWorldY + entity.speed) / GamePanel.TILESIZE;
			tileNum1 = gp.tileM.map[leftCol][bottomRow];
			tileNum2 = gp.tileM.map[rightCol][bottomRow];
			if (gp.tileM.tiles[tileNum1].isCollision() || gp.tileM.tiles[tileNum2].isCollision()) {
				entity.collisionOn = true;
			}
			if (gp.tileM.tiles[tileNum1].isEncounter() || gp.tileM.tiles[tileNum2].isEncounter()) {
				if (entity instanceof Player) {
					((Player) entity).encounter = true;
				}
			}
			break;
		case "left":
			leftCol = (leftWorldX - entity.speed) / GamePanel.TILESIZE;
			tileNum1 = gp.tileM.map[leftCol][topRow];
			tileNum2 = gp.tileM.map[leftCol][bottomRow];
			if (gp.tileM.tiles[tileNum1].isCollision() || gp.tileM.tiles[tileNum2].isCollision()) {
				entity.collisionOn = true;
			}
			if (gp.tileM.tiles[tileNum1].isEncounter() || gp.tileM.tiles[tileNum2].isEncounter()) {
				if (entity instanceof Player) {
					((Player) entity).encounter = true;
				}
			}
			break;
		case "right":
			rightCol = (rightWorldX + entity.speed) / GamePanel.TILESIZE;
			tileNum1 = gp.tileM.map[rightCol][topRow];
			tileNum2 = gp.tileM.map[rightCol][bottomRow];
			if (gp.tileM.tiles[tileNum1].isCollision() || gp.tileM.tiles[tileNum2].isCollision()) {
				entity.collisionOn = true;
			}
			if (gp.tileM.tiles[tileNum1].isEncounter() || gp.tileM.tiles[tileNum2].isEncounter()) {
				if (entity instanceof Player) {
					((Player) entity).encounter = true;
				}
			}
			break;
		}
	}

	/**
	 * Check object.
	 *
	 * @param entity the entity
	 * @param player the player
	 * @return index of which object was interacted with
	 */
// collision with object
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

	/**
	 * Check entity.
	 *
	 * @param entity the entity
	 * @param target the target
	 * @return the index of which entity was interacted with
	 */
// npc or monster collision
	public int checkEntity(Entity entity, Entity[] target) {
		int index = 999;

		for (int i = 0; i < target.length; i++) {

			if (target[i] != null) {

				entity.solidArea.setX(entity.worldX + entity.solidArea.getX());
				entity.solidArea.setY(entity.worldY + entity.solidArea.getY());

				target[i].solidArea.setX(target[i].worldX + target[i].solidArea.getX());
				target[i].solidArea.setY(target[i].worldY + target[i].solidArea.getY());

				switch (entity.direction) {

				case "up":
					entity.solidArea.setY(entity.solidArea.getY() - entity.speed);

					if (entity.solidArea.getBoundsInParent().intersects(target[i].solidArea.getBoundsInParent())) {
						entity.collisionOn = true;
						index = i;
					}
					break;

				case "down":
					entity.solidArea.setY(entity.solidArea.getY() + entity.speed);

					if (entity.solidArea.getBoundsInParent().intersects(target[i].solidArea.getBoundsInParent())) {
						entity.collisionOn = true;
						index = i;
					}
					break;

				case "left":
					entity.solidArea.setX(entity.solidArea.getX() - entity.speed);

					if (entity.solidArea.getBoundsInParent().intersects(target[i].solidArea.getBoundsInParent())) {
						entity.collisionOn = true;
						index = i;
					}
					break;

				case "right":
					entity.solidArea.setX(entity.solidArea.getX() + entity.speed);

					if (entity.solidArea.getBoundsInParent().intersects(target[i].solidArea.getBoundsInParent())) {
						entity.collisionOn = true;
						index = i;
					}
					break;
				}

				entity.solidArea.setX(entity.solidAreaDefaultX);
				entity.solidArea.setY(entity.solidAreaDefaultY);
				target[i].solidArea.setX(target[i].solidAreaDefaultX);
				target[i].solidArea.setY(target[i].solidAreaDefaultY);
			}
		}

		return index;
	}

	/**
	 * Check player.
	 *
	 * @param entity the entity
	 */
// NPC TO PLAYER COLLISION
	public void checkPlayer(Entity entity) {
		entity.solidArea.setX(entity.worldX + entity.solidArea.getX());
		entity.solidArea.setY(entity.worldY + entity.solidArea.getY());

		gp.player.solidArea.setX(gp.player.worldX + gp.player.solidArea.getX());
		gp.player.solidArea.setY(gp.player.worldY + gp.player.solidArea.getY());

		switch (entity.direction) {

		case "up":
			entity.solidArea.setY(entity.solidArea.getY() - entity.speed);

			if (entity.solidArea.getBoundsInParent().intersects(gp.player.solidArea.getBoundsInParent())) {
				entity.collisionOn = true;
			}
			break;

		case "down":
			entity.solidArea.setY(entity.solidArea.getY() + entity.speed);

			if (entity.solidArea.getBoundsInParent().intersects(gp.player.solidArea.getBoundsInParent())) {
				entity.collisionOn = true;
			}
			break;

		case "left":
			entity.solidArea.setX(entity.solidArea.getX() - entity.speed);

			if (entity.solidArea.getBoundsInParent().intersects(gp.player.solidArea.getBoundsInParent())) {
				entity.collisionOn = true;
			}
			break;

		case "right":
			entity.solidArea.setX(entity.solidArea.getX() + entity.speed);

			if (entity.solidArea.getBoundsInParent().intersects(gp.player.solidArea.getBoundsInParent())) {
				entity.collisionOn = true;
			}
			break;
		}

		entity.solidArea.setX(entity.solidAreaDefaultX);
		entity.solidArea.setY(entity.solidAreaDefaultY);
		gp.player.solidArea.setX(gp.player.solidAreaDefaultX);
		gp.player.solidArea.setY(gp.player.solidAreaDefaultY);
	}

}
