package domein;

public class Goblin extends Enemy {
	

	public Goblin() {
		super(sr.nextInt(100)+100,sr.nextDouble(0.10),"");
		if (sr.nextInt(2)==1) {
			evolve();
		}
	}

	@Override
	public double attackBack() {
		if (isFrozen()) {
			super.breakFrozen();
			return 0;
		}
		int power = sr.nextInt(41)+10;
		if (super.evolved==true) {
			power= sr.nextInt(61)+20;
		}
		return  power;
	}

	@Override
	public void evolve() {
		super.setHealth(200);
		super.setDefence(0.10);
		super.setType("Hob");
		super.evolved=true;
	}
	
	

}
