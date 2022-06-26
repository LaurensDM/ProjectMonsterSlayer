package domein;

public class Wind extends Elements {

	public Wind(double manapool, Skills playerSkills) {
		super(manapool, playerSkills);
	}

	@Override
	public double attack() {
		return super.attack(1);
	}

}
