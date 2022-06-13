package domein;

public class Wind extends Elements {

	public Wind(double manapool) {
		super(manapool);
	}

	@Override
	public double attack() {
		return super.attack(1);
	}

}
