package domein;

import java.math.BigDecimal;
import java.security.SecureRandom;

/**
 * The type Water.
 */
public class Water extends Elements {

	/**
	 * Instantiates new Water.
	 *
	 * @param manapool     the manapool
	 * @param playerSkills the player skills
	 * @param maxMana      the max mana
	 */
	public Water(double manapool, Skills playerSkills,double maxMana) {
		super(manapool, playerSkills,maxMana);
		// TODO Auto-generated constructor stub
	}

	@Override
	public double attack() {
		return super.attack(1);
	}


}
