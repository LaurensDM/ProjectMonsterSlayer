package domein;

import java.security.SecureRandom;

/**
 * The type Dragon.
 */
public class Dragon extends Enemy {

	/**
	 * Instantiates a new Dragon.
	 */
	public Dragon() {
		super(sr.nextInt(10000) + 10000, sr.nextDouble(0.46) + 0.35,
				Elements.ELEMENTS.get(sr.nextInt(Elements.ELEMENTS.size())));

		if (sr.nextInt(100) == 0) {
			evolve();
		}
	}

	@Override
	public double takeDamage(String damageType, double damage) {
		// TODO Auto-generated method stub
		double totalDamage = (damage - damage * super.getDefence()) + super.getTickDamage();
		if (super.isWeakness(damageType)) {
			super.registerDamage(totalDamage * 2);
			return totalDamage * 2;
		}
		if (super.isStrongAgainst(damageType)) {
			super.registerDamage(totalDamage * 0.5);
			return totalDamage * 0.5;
		}
		return super.takeDamage(damageType, damage);
	}

	@Override
	public double attackBack() {
		if (isFrozen()) {
			super.breakFrozen();
			return 0;
		}
		double attack = sr.nextInt(101) + 50;
		if (evolved) {
			attack = sr.nextInt(150) + 75;
		}
		return attack;
	}

	@Override
	public void evolve() {
		super.setHealth(100000);
		super.setDefence(0.8);
		super.setType("Golden");
		super.evolved = true;
	}

	@Override
	public int determineItemGrade(boolean fullpower) {
		int grade;

		if (getType().equals("Golden")) {
			grade = sr.nextInt(2) + 4;
		} else
			grade = sr.nextInt(3) + 2;
		if (fullpower) {
			if (sr.nextInt(4) == 0) {
				grade = 0;
			}
		}

		return grade;
	}

}
