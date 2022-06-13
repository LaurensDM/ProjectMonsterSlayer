package resources;

import gui.GamePanel;

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
		
		int leftCol = leftWorldX/GamePanel.TILESIZE;
		int rightCol = rightWorldX/GamePanel.TILESIZE;
		int topRow = topWorldY/GamePanel.TILESIZE;
		int bottomRow = bottomWorldY/GamePanel.TILESIZE;
		
		int tileNum1, tileNum2;
		
		switch (entity.direction) {
		case "up": 
			topRow = (topWorldY - entity.speed)/GamePanel.TILESIZE;
			tileNum1 = gp.tileM.map[leftCol][topRow];
			tileNum2 = gp.tileM.map[rightCol][topRow];
			if (gp.tileM.tiles[tileNum1].isCollision() || gp.tileM.tiles[tileNum2].isCollision()) {
				entity.collisionOn=true;
			}
			break;
		case "down":
			bottomRow = (bottomWorldY + entity.speed)/GamePanel.TILESIZE;
			tileNum1 = gp.tileM.map[leftCol][bottomRow];
			tileNum2 = gp.tileM.map[rightCol][bottomRow];
			if (gp.tileM.tiles[tileNum1].isCollision() || gp.tileM.tiles[tileNum2].isCollision()) {
				entity.collisionOn=true;
			}
			break;
		case "left":
			leftCol = (leftWorldX - entity.speed)/GamePanel.TILESIZE;
			tileNum1 = gp.tileM.map[leftCol][topRow];
			tileNum2 = gp.tileM.map[leftCol][bottomRow];
			if (gp.tileM.tiles[tileNum1].isCollision() || gp.tileM.tiles[tileNum2].isCollision()) {
				entity.collisionOn=true;
			}
			break;
		case "right": 
			rightCol = (rightWorldX + entity.speed)/GamePanel.TILESIZE;
			tileNum1 = gp.tileM.map[rightCol][topRow];
			tileNum2 = gp.tileM.map[rightCol][bottomRow];
			if (gp.tileM.tiles[tileNum1].isCollision() || gp.tileM.tiles[tileNum2].isCollision()) {
				entity.collisionOn=true;
			}
			break;
		}
	}

}
