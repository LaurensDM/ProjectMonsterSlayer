package domein;

/**
 * The type Fire.
 */
public class Fire extends Elements {

	/**
	 * Instantiates a new Fire.
	 *
	 * @param manapool     the manapool
	 * @param playerSkills the player skills
	 * @param maxMana      the max mana
	 */
	public Fire(double manapool, Skills playerSkills,double maxMana) {
		super(manapool, playerSkills,maxMana);
		// TODO Auto-generated constructor stub
	}

	@Override
	public double attack() {
		return super.attack(1.25);
	}

}
