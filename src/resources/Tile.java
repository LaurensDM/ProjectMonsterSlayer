package resources;

import javafx.scene.image.Image;

public class Tile {

	private Image image;
	private boolean collision=false;
	private boolean encounter = false;
	
	public Tile(Image image, boolean collision, boolean encounter) {
		this.image = image;
		this.collision = collision;
		this.encounter = encounter;
	}

	public Image getImage() {
		return image;
	}

	public boolean isCollision() {
		return collision;
	}

	public boolean isEncounter() {
		return encounter;
	}
	
	

}
