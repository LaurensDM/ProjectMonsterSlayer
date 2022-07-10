package domein;

/**
 * The type True magic.
 */
public class True_Magic extends Elements {

	/**
	 * Instantiates a new True magic.
	 *
	 * @param manapool     the manapool
	 * @param playerSkills the player skills
	 * @param maxMana      the max mana
	 */
	public True_Magic(double manapool, Skills playerSkills,double maxMana) {
		super(manapool, playerSkills,maxMana);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected double attack() {

		return super.attack(2);
	}

}
