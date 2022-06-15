package resources;

import entity.NPC_Sage;
import gui.GamePanel;

public class AssetSetter {

	GamePanel gp;

	public AssetSetter(GamePanel gp) {
		this.gp = gp;
	}

	public void setObject() {

		// Create an object and place it on the map
//		gp.obj[0] = new Object();
//		gp.obj[0].worldX = 23*GamePanel.TILESIZE;
//		gp.obj[0].worldY = 7 *GamePanel.TILESIZE;

	}

	public void setNPC() {
		gp.npc[0] = new NPC_Sage(gp);
		gp.npc[0].worldX = GamePanel.TILESIZE * 21;
		gp.npc[0].worldY = GamePanel.TILESIZE * 21;
	}

}
