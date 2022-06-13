package domein;

import java.security.SecureRandom;

public class Dragon extends Enemy {

	public Dragon() {
		super(sr.nextInt(301) + 500, sr.nextDouble(0.46) + 0.35, Elements.ELEMENTS.get(sr.nextInt(Elements.ELEMENTS.size())));
		
		if (sr.nextInt(100)==0) {
			evolve();
		}
	}

	@Override
	public double takeDamage(String damageType, double damage) {
		// TODO Auto-generated method stub
		double totalDamage = (damage - damage * super.getDefence()) + super.getTickDamage();
		if (super.isWeakness(damageType)) {
			super.registerDamage(totalDamage * 2);
			return totalDamage*2;
		}
		if (super.isStrongAgainst(damageType)) {
			super.registerDamage(totalDamage*0.5);
			return totalDamage*0.5;
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
			attack = sr.nextInt(150)+75;
		}
		return attack;
	}

	@Override
	public void evolve() {
		super.setHealth(800);
		super.setDefence(0.8);
		super.setType("Golden");
		super.evolved=true;
	}

}
