package domein;

public class True_Magic extends Elements {

	public True_Magic(double manapool, Skills playerSkills,double maxMana) {
		super(manapool, playerSkills,maxMana);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected double attack() {

		return super.attack(2);
	}

}
