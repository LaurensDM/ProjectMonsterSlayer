package domein;

/**
 * The type Goblin.
 */
public class Goblin extends Enemy {

	/**
	 * Instantiates a new Goblin.
	 */
	public Goblin() {
        super(sr.nextInt(301) + 300, sr.nextDouble(0.10), "");
        if (sr.nextInt(3) == 1) {
            evolve();
        }
    }

	@Override
	public double attackBack() {
		if (isFrozen()) {
			super.breakFrozen();
			return 0;
		}
		int power = sr.nextInt(41) + 10;
		if (super.evolved == true) {
			power = sr.nextInt(61) + 20;
		}
		return power;
	}

	@Override
	public void evolve() {
        super.setHealth(600);
        super.setDefence(0.10);
		super.setType("Hob");
		super.evolved = true;
	}

	@Override
	public int determineItemGrade(boolean fullpower) {
		int grade=1;

		if (getType().equals("Hob")) {
			grade = sr.nextInt(2) + 1;
		}
		if (fullpower) {
			grade = 0;
		}
		return grade;
	}

	@Override
	public int dropExp() {
		int powerLevel = (int) (MAX_HEALTH + MAX_HEALTH * MAX_DEFENCE);
		return (int) (powerLevel * 0.5);
	}

}
