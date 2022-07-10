package domein;

/**
 * The type Wind.
 */
public class Wind extends Elements {

	/**
	 * Instantiates a new Wind.
	 *
	 * @param manapool     the manapool
	 * @param playerSkills the player skills
	 * @param maxMana      the max mana
	 */
	public Wind(double manapool, Skills playerSkills,double maxMana) {
		super(manapool, playerSkills,maxMana);
		// TODO Auto-generated constructor stub
	}

	@Override
	public double attack() {
		return super.attack(1);
	}

}
