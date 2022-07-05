package domein;

public class Power_Potion extends Potion {

	public Power_Potion(String name, int grade) {
		super(name, grade);
		// TODO Auto-generated constructor stub
	}

	public double effect() {
		double effect = 0;

		switch (grade) {
		case 0:
			effect = 0.75;
			break;
		case 1:
			effect = 1.10;
			break;
		case 2:
			effect = 1.25;
			break;
		case 3:
			effect = 1.5;
			break;
		case 4:
			effect = 2;
			break;
		case 5:
			effect = 5;
			break;
		case 6:
			effect = 50;
			break;
		}

		return effect;
	}

}
