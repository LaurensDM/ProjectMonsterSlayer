package entity;

import javafx.scene.image.Image;

/**
 * The type Sprite.
 */
public class Sprite {
	private Image[] images;
	private Image[] movement = new Image[2];
	/**
	 * The X.
	 */
	int x;
	/**
	 * The Y.
	 */
	int y;
	private boolean stop;
	/**
	 * The Previous index.
	 */
	int previousIndex = -1;
	private int spriteCounter = 0;
	private int spriteNum = 0;
	private int frameRate = 12;

	/**
	 * Instantiates a new Sprite.
	 *
	 * @param entity the entity
	 */
	public Sprite(String entity) {
		images = new Image[8];
		images[0] = new Image(getClass().getResourceAsStream("/images/sprite/" + entity + "_down.png"));
		images[1] = new Image(getClass().getResourceAsStream("/images/sprite/" + entity + "_down_2.png"));
		images[2] = new Image(getClass().getResourceAsStream("/images/sprite/" + entity + "_up_1.png"));
		images[3] = new Image(getClass().getResourceAsStream("/images/sprite/" + entity + "_up_2.png"));
		images[4] = new Image(getClass().getResourceAsStream("/images/sprite/" + entity + "_left_1.png"));
		images[5] = new Image(getClass().getResourceAsStream("/images/sprite/" + entity + "_left_2.png"));
		images[6] = new Image(getClass().getResourceAsStream("/images/sprite/" + entity + "_right_1.png"));
		images[7] = new Image(getClass().getResourceAsStream("/images/sprite/" + entity + "_right_2.png"));
		//default is down movement
		movement[0] = images[0];
		movement[1] = images[1];

	}

	/**
	 * Move up.
	 */
	public void moveUp() {
		stop = false;
		movement[0] = images[2];
		movement[1] = images[3];
	}

	/**
	 * Move down.
	 */
	public void moveDown() {
		stop = false;
		movement[0] = images[0];
		movement[1] = images[1];
	}

	/**
	 * Move left.
	 */
	public void moveLeft() {
		stop = false;
		movement[0] = images[4];
		movement[1] = images[5];
	}

	/**
	 * Move right.
	 */
	public void moveRight() {
		stop = false;
		movement[0] = images[6];
		movement[1] = images[7];
	}

	/**
	 * Update sprite counter.
	 */
	public void updateSpriteCounter() {
		spriteCounter++;

		if (spriteCounter > frameRate) {
			if (spriteNum == 0) {
				spriteNum = 1;
			} else if (spriteNum == 1) {
				spriteNum = 0;
			}

			spriteCounter = 0;
		}
	}

	/**
	 * Gets frame.
	 *
	 * @return the frame
	 */
	public Image getFrame() {
		if (stop) {
			return movement[0];
		}
		return movement[spriteNum];
	}

	/**
	 * Stop movement.
	 */
	public void stopMovement() {
		this.stop = true;
	}

	/**
	 * Sprint.
	 */
	public void sprint() {
		// TODO Auto-generated method stub
		frameRate = 8;
	}

	/**
	 * Walk.
	 */
	public void walk() {
		frameRate = 12;

	}

}
