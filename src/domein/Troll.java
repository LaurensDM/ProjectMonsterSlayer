package domein;

/**
 * The type Troll.
 */
public class Troll extends Enemy {

	/**
	 * Instantiates a new Troll.
	 */
	public Troll() {
		super(sr.nextInt(200) + 200, sr.nextDouble(0.15) + 0.1, "");
		if (sr.nextInt(4) == 1) {
			evolve();
		}
	}

	@Override
	public double attackBack() {
		if (isFrozen()) {
			super.breakFrozen();
			return 0;
		}
		int power = sr.nextInt(51) + 25;
		if (super.evolved == true) {
			power = sr.nextInt(76) + 50;
		}
		return power;
	}

	@Override
	public void evolve() {
		super.setHealth(400);
		super.setDefence(0.25);
		super.setType("Rock");
		super.evolved = true;
	}

	@Override
	public int determineItemGrade(boolean fullpower) {
		int grade = 1;
		if (getType().equals("Rock")) {
			grade = sr.nextInt(3) + 1;
		} else
			grade = sr.nextInt(2) + 1;

		if (fullpower) {
			if (sr.nextInt(2) == 0) {
				grade = 0;
			}
		}
		return grade;
	}

	/**
	 * @return
	 */
	@Override
	public int dropExp() {
		int powerLevel = (int) (getHealth() + getHealth() * getDefence());
		return (int) (powerLevel * 0.5);
	}

}
