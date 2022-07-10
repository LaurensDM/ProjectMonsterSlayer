package domein;

/**
 * The type Armor.
 */
public class Armor extends Items {

	private final double MAX_DURABILITY;
	private double durability;
	private double damageReduction = 1;
	private String resistance;

	/**
	 * Instantiates a new Armor.
	 *
	 * @param name  the name
	 * @param grade the grade
	 */
	public Armor(String name, int grade) {
		super(name, grade);
		switch (grade) {
		case 0:
			MAX_DURABILITY = 1;
			damageReduction = 0.999;
			break;
		case 1:
			MAX_DURABILITY = 100;
			damageReduction = 0.9;
			break;
		case 2:
			MAX_DURABILITY = 200;
			damageReduction = 0.8;
			break;
		case 3:
			MAX_DURABILITY = 400;
			damageReduction = 0.7;
			break;
		case 4:
			MAX_DURABILITY = 1000;
			damageReduction = 0.5;
			break;
		case 5:
			MAX_DURABILITY = 5000;
			damageReduction = 0.2;
			break;
		case 6:
			MAX_DURABILITY = 500000;
			damageReduction = 0.002;
			break;
		default:
			MAX_DURABILITY = 100;
			break;
		}
	}

	/**
	 * Determine resistance.
	 *
	 * @param name element of the armor
	 */
	public void determineResistance(String name) {
		if (name.contains("Fire")) {
			resistance = "Fire";
			return;
		}
		if (name.contains("Water")) {
			resistance = "Water";
			return;
		}
		if (name.contains("Lightning")) {
			resistance = "Lightning";
			return;
		}
		if (name.contains("Earth")) {
			resistance = "Earth";
			return;
		}
		if (name.contains("Wind")) {
			resistance = "Wind";
			return;
		}
		resistance = "";
	}

	/**
	 * Lower durability.
	 *
	 * @param value value by which durability lowers
	 */
	public void lowerDurability(double value) {

		durability -= value * damageReduction;

	}

	/**
	 * Repair armor.
	 */
	public void repairArmor() {
		if (durability + 100 > MAX_DURABILITY) {
			durability = MAX_DURABILITY;
			return;
		}
		durability += 100;
	}

	/**
	 * Gets durability.
	 *
	 * @return the durability
	 */
	public double getDurability() {
		return MAX_DURABILITY;
	}


	/**
	 * Gets max durability.
	 *
	 * @return the max durability
	 */
	public double getMAX_DURABILITY() {
		return MAX_DURABILITY;
	}

	/**
	 * Gets damage reduction.
	 *
	 * @return the damage reduction
	 */
	public double getDamageReduction() {
		return damageReduction;
	}

	/**
	 * Gets resistance.
	 *
	 * @return the resistance
	 */
	public String getResistance() {
		return resistance;
	}

	/**
	 * Destroyed boolean.
	 *
	 * @return the boolean
	 */
	public boolean destroyed() {
		if (durability <= 0) {
			return true;
		}
		return false;
	}

}
