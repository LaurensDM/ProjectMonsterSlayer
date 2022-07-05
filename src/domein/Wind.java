package domein;

public class Wind extends Elements {

	public Wind(double manapool, Skills playerSkills,Weapon weapon,Armor armor) {
		super(manapool, playerSkills,weapon,armor);
		// TODO Auto-generated constructor stub
	}

	@Override
	public double attack() {
		return super.attack(1);
	}

}
