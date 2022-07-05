package domein;

import java.math.BigDecimal;
import java.security.SecureRandom;

public class Water extends Elements {
	
	public Water(double manapool, Skills playerSkills,Weapon weapon,Armor armor) {
		super(manapool, playerSkills,weapon,armor);
		// TODO Auto-generated constructor stub
	}

	@Override
	public double attack() {
		return super.attack(1);
	}


}
