package resources;

import entity.NPC_Sage;
import gui.GamePanel;

/**
 * The type Asset setter.
 */
//CREATE OBJECTS AND NPC'S
public class AssetSetter {

	/**
	 * The Gp.
	 */
	GamePanel gp;

	/**
	 * Instantiates a new Asset setter.
	 *
	 * @param gp the GamePanel
	 */
	public AssetSetter(GamePanel gp) {
		this.gp = gp;
	}

	/**
	 * Sets object.
	 */
	public void setObject() {

		// Create an object and place it on the map
//		gp.obj[0] = new Object();
//		gp.obj[0].worldX = 23*GamePanel.TILESIZE;
//		gp.obj[0].worldY = 7 *GamePanel.TILESIZE;

	}

	/**
	 * Sets npc.
	 */
	public void setNPC() {
		gp.npc[0] = new NPC_Sage(gp);
		gp.npc[0].worldX = GamePanel.TILESIZE * 21;
		gp.npc[0].worldY = GamePanel.TILESIZE * 21;
	}

}
