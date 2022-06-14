package resources;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;
import gui.GamePanel;

public class SuperObject {
	
	public Image image;
	public String name;
	public boolean collision = false;
	public int worldX, worldY;
	public Rectangle solidArea = new Rectangle(0,0,GamePanel.TILESIZE,GamePanel.TILESIZE);
	public int solidAreaDefaultX = 0;
	public int solidAreaDefaultY = 0;

	public void draw(GraphicsContext gc, GamePanel gp) {
		
		int screenX = worldX - gp.player.worldX + gp.player.screenX;
		int screenY = worldY - gp.player.worldY + gp.player.screenY;

		if (worldX + GamePanel.TILESIZE > gp.player.worldX - gp.player.screenX
				&& worldX - GamePanel.TILESIZE < gp.player.worldX + gp.player.screenX
				&& worldY + GamePanel.TILESIZE > gp.player.worldY - gp.player.screenY
				&& worldY - GamePanel.TILESIZE < gp.player.worldY + gp.player.screenY) {
			
			gc.drawImage(image, screenX, screenY, GamePanel.TILESIZE, GamePanel.TILESIZE); 
		}
	}

}
