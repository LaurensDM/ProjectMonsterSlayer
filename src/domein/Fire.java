package domein;

public class Fire extends Elements {

	public Fire(double manapool, Skills playerSkills,Weapon weapon,Armor armor) {
		super(manapool, playerSkills,weapon,armor);
		// TODO Auto-generated constructor stub
	}

	@Override
	public double attack() {
		return super.attack(1.25);
	}

}
