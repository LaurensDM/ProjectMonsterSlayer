package domein;

public class Mana_Potion extends Potion {

	public Mana_Potion(String name, int grade) {
		super(name, grade);
		// TODO Auto-generated constructor stub
	}

	@Override
	public double effect() {
		double effect = 0;

		switch (grade) {
		case 0:
			effect = -0.1;
			break;
		case 1:
			effect = 0.1;
			break;
		case 2:
			effect = 0.2;
			break;
		case 3:
			effect = 0.35;
			break;
		case 4:
			effect = 0.5;
			break;
		case 5:
			effect = 0.8;
			break;
		case 6:
			effect = 1;
			break;
		}
		
		return effect;
	}
}
