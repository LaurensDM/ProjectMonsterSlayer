package domein;

public class Earth extends Elements {

	public Earth(double manapool) {
		super(manapool);
		// TODO Auto-generated constructor stub
	}

	@Override
	public double attack() {
		return super.attack(1);
	}

}
