package domein;

/**
 * The type Troll.
 */
public class Troll extends Enemy {

	/**
	 * Instantiates a new Troll.
	 */
	public Troll() {
		super(sr.nextInt(1200) + 800, sr.nextDouble(0.15) + 0.1, "");
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
			power = sr.nextInt(5) + 75;
		}
		return power;
	}

	@Override
	public void evolve() {
		super.setHealth(2000);
		super.setDefence(0.25);
		super.setType("Rock");
		super.evolved = true;
	}

	/**
	 *
	 */
	@Override
	public void applyNamedPower() {

	}

	@Override
	public int determineItemGrade(boolean fullpower) {
		int grade = 1;
		if (getType().equals("Rock")) {
			grade = sr.nextInt(2) + 2;
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
	protected Component determineComponent() {
		String itemClass = Items.ITEMS.get(sr.nextInt(Items.ITEMS.size()));

		int grade;
		String evolvedType;
		if (evolved) {
			evolvedType = "Rock ";
			grade = 3;
		} else {
			evolvedType = "";
			grade = 2;
		}

		Component component = null;

		switch (itemClass) {
			case "Mana Potion" -> component = new Component(evolvedType + "Troll Essence", grade, 0);
			case "Power Potion" -> component = new Component(evolvedType + "Troll Blood", grade, 1);
			case "Weapon" -> component = new Component(evolvedType + "Troll Bone", grade, 2);
			case "Armor" -> component = new Component(evolvedType + "Troll Hide", grade, 3);
		}

		return component;
	}

	/**
	 * @return
	 */
	@Override
	public int dropExp() {
		int powerLevel = (int) (MAX_HEALTH + MAX_HEALTH * MAX_DEFENCE);
		return (int) (powerLevel * 0.5);
	}

}
