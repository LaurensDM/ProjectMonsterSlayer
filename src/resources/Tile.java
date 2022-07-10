package resources;

import javafx.scene.image.Image;

/**
 * The type Tile.
 */
public class Tile {

	private Image image;
	private boolean collision;
	private boolean encounter;

	/**
	 * Instantiates a new Tile.
	 *
	 * @param image     the image
	 * @param collision the collision
	 * @param encounter the encounter
	 */
	public Tile(Image image, boolean collision, boolean encounter) {
		this.image = image;
		this.collision = collision;
		this.encounter = encounter;
	}

	/**
	 * Gets image.
	 *
	 * @return the image
	 */
	public Image getImage() {
		return image;
	}

	/**
	 * Is collision boolean.
	 *
	 * @return the boolean
	 */
	public boolean isCollision() {
		return collision;
	}

	/**
	 * Is encounter boolean.
	 *
	 * @return the boolean
	 */
	public boolean isEncounter() {
		return encounter;
	}
	
	

}
