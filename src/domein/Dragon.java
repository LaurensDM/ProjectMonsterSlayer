package domein;

/**
 * The type Dragon.
 */
public class Dragon extends Enemy {

	/**
	 * Instantiates a new Dragon.
	 */
	public Dragon() {
		super(sr.nextInt(10000) + 10000, sr.nextDouble(0.46) + 0.25,
				Elements.ELEMENTS.get(sr.nextInt(Elements.ELEMENTS.size())));

		if (sr.nextInt(100) == 0) {
			evolve();
		}
	}

	public Dragon(String name, boolean evolved) {
		this();
		super.evolved = evolved;
		if (evolved) evolve();
		applyNamedPower();
		setName(name);
	}

	@Override
	public double attackBack() {
		if (isFrozen()) {
			super.breakFrozen();
			return 0;
		}
		double attack = sr.nextInt(125) + 225;
		if (evolved) {
			attack = 500;
		}
		if (named) attack *= 1.5;
		return attack;
	}

	@Override
	public void evolve() {
		super.setHealth(100000);
		super.setDefence(0.8);
		super.setType("Golden");
		super.evolved = true;
	}

	/**
	 *
	 */
	@Override
	public void applyNamedPower() {
		setHealth(getHealth() * 1.5);
		setDefence(getDefence() * 1.24);
		super.named = true;
	}

	@Override
	public int determineItemGrade(boolean fullpower) {
		int grade;

		if (getType().equals("Golden")) {
			grade = sr.nextInt(2) + 5;
		} else
			grade = sr.nextInt(3) + 3;
		if (fullpower) {
			if (sr.nextInt(10) == 0) {
				grade = 0;
			}
		}

		return grade;
	}

	/**
	 * @return exp
	 */
	@Override
	public int dropExp() {
		int powerLevel = (int) (MAX_HEALTH + MAX_HEALTH * MAX_DEFENCE);
		return (int) (powerLevel * 0.5);
	}

}
