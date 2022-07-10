package domein;

/**
 * The type Potion.
 */
public class Potion extends Items {

	/**
	 * Instantiates a new Potion.
	 *
	 * @param name  the name
	 * @param grade the grade
	 */
	public Potion(String name, int grade) {
		super(name, grade);
	}

	/**
	 * Effect.
	 *
	 * @return effect of the potion
	 */
	public double effect() {
		return 0;
	}

}
