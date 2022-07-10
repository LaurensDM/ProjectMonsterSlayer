package domein;

/**
 * The type Earth.
 */
public class Earth extends Elements {

	/**
	 * Instantiates a new Earth.
	 *
	 * @param manapool     the manapool
	 * @param playerSkills the player skills
	 * @param maxMana      the max mana
	 */
	public Earth(double manapool, Skills playerSkills,double maxMana) {
		super(manapool, playerSkills,maxMana);
		// TODO Auto-generated constructor stub
	}

	@Override
	public double attack() {
		return super.attack(1);
	}

}
