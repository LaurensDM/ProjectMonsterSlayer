package entity;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import gui.GamePanel;

public class NPC_Sage extends Entity {

	SecureRandom sr = new SecureRandom();
	int random;
	List<String> directions = new ArrayList<>(Arrays.asList("up", "down", "left", "right"));

	public NPC_Sage(GamePanel gp) {
		super(gp);

		direction = directions.get(sr.nextInt(4));
		speed = 1;
		sprite = new Sprite("oldman");
	}

	@Override
	public void setAction() {
		random = sr.nextInt(4);

		actionLockCounter++;

		if (actionLockCounter == 120) {

			if (random == 0) {
				direction = "up";
			}
			if (random == 1) {
				direction = "down";
			}
			if (random == 2) {
				direction = "left";
			}
			if (random == 3) {
				direction = "right";
			}

			actionLockCounter = 0;
		}
	}
}
