package domein;

import java.math.BigDecimal;
import java.security.SecureRandom;

public class Water extends Elements {
	
	public Water(double manapool) {
		super(manapool);
		// TODO Auto-generated constructor stub
	}

	@Override
	public double attack() {
		return super.attack(1);
	}


}
