package domein;

public class Troll extends Enemy {

	public Troll() {
		super(sr.nextInt(200)+200, sr.nextDouble(0.15)+0.1, "");
		if (sr.nextInt(4)==1) {
			evolve();
		}
	}

	@Override
	public double attackBack() {
		if (isFrozen()) {
			super.breakFrozen();
			return 0;
		}
		int power = sr.nextInt(51)+25;
		if (super.evolved==true) {
			power= sr.nextInt(76)+50;
		}
		return  power;
	}

	@Override
	public void evolve() {
		super.setHealth(400);
		super.setDefence(0.25);
		super.setType("Rock");
		super.evolved=true;
	}

}
