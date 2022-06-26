package domein;

import java.math.BigDecimal;
import java.security.SecureRandom;

public class Water extends Elements {
	
	public Water(double manapool, Skills playerSkills) {
		super(manapool, playerSkills);
		// TODO Auto-generated constructor stub
	}

	@Override
	public double attack() {
		return super.attack(1);
	}


}
