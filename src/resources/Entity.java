package resources;

import javafx.scene.shape.Rectangle;

public class Entity {
		int worldX,worldY;
		int speed;
		Rectangle solidArea;
		public int solidAreaDefaultX, solidAreaDefaultY;
		public boolean collisionOn = false;
		String direction;
		
//		public void registerCollision() {
//			collisionOn = true;
//		}
}
