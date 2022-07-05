package domein;

public class True_Magic extends Elements {

	public True_Magic(double manapool, Skills playerSkills,Weapon weapon,Armor armor) {
		super(manapool, playerSkills,weapon,armor);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected double attack() {

		return super.attack(2);
	}

}
