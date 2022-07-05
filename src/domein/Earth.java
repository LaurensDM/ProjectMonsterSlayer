package domein;

public class Earth extends Elements {

	public Earth(double manapool, Skills playerSkills,Weapon weapon,Armor armor) {
		super(manapool, playerSkills,weapon,armor);
		// TODO Auto-generated constructor stub
	}

	@Override
	public double attack() {
		return super.attack(1);
	}

}
