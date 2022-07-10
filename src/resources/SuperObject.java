package resources;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;
import gui.GamePanel;

/**
 * The type Super object.
 */
//super class for every object
public class SuperObject {

	/**
	 * The Image.
	 */
	public Image image;
	/**
	 * The Name.
	 */
	public String name;
	/**
	 * The Collision.
	 */
	public boolean collision = false;
	/**
	 * The World x.
	 */
	public int worldX, /**
	 * The World y.
	 */
	worldY;
	/**
	 * The Solid area.
	 */
	public Rectangle solidArea = new Rectangle(0,0,GamePanel.TILESIZE,GamePanel.TILESIZE);
	/**
	 * The Solid area default x.
	 */
	public int solidAreaDefaultX = 0;
	/**
	 * The Solid area default y.
	 */
	public int solidAreaDefaultY = 0;

	/**
	 * Draw.
	 *
	 * @param gc the GraphicsContext
	 * @param gp the GamePanel
	 */
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
