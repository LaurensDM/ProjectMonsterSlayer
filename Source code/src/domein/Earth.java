package domein;

public class Earth extends Elements {

	public Earth(double manapool, Skills playerSkills) {
		super(manapool, playerSkills);
		// TODO Auto-generated constructor stub
	}

	@Override
	public double attack() {
		return super.attack(1);
	}

}
