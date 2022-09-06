package domein;

/**
 * The type Mana potion.
 */
public class Mana_Potion extends Potion {

	/**
	 * Instantiates a new Mana potion.
	 *
	 * @param name  the name
	 * @param grade the grade
	 */
	public Mana_Potion(String name, int grade) {
		super(name, grade);
		// TODO Auto-generated constructor stub
	}

	@Override
	public double effect() {
		double effect = 0;

		switch (grade) {
		case 0:
			effect = -100;
			break;
		case 1:
            effect = 300;
			break;
		case 2:
			effect = 0.25;
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
