package domein;

/**
 * The type Weapon.
 */
public class Weapon extends Items {
	private double damage;
	private boolean destroyable = true;
	private final double MAX_DURABILITY;
	private double durability;
	private String affinity;

	/**
	 * Instantiates a new Weapon.
	 *
	 * @param name  the name
	 * @param grade the grade
	 */
	public Weapon(String name, int grade) {
		super(name, grade);
		switch (grade) {
		case 0:
			damage = 0.75;
			MAX_DURABILITY = 1;
			break;
		case 1:
			damage = 1.10;
			MAX_DURABILITY = 100;
			break;
		case 2:
			damage = 1.25;
			MAX_DURABILITY = 200;
			break;
		case 3:
			damage = 1.5;
			MAX_DURABILITY = 400;
			break;
		case 4:
			damage = 2;
			MAX_DURABILITY = 1000;
			destroyable = false;
			break;
		case 5:
			damage = 5;
			MAX_DURABILITY = 5000;
			destroyable = false;
			break;
		case 6:
			damage = 50;
			MAX_DURABILITY=500000;
			destroyable = false;
			break;
		default:
			MAX_DURABILITY = 100;
			break;
		}
		durability = MAX_DURABILITY;

		determineAffinity();
	}

	private void determineAffinity() {
		if (name.contains("Fire")) {
			affinity = "Fire";
			return;
		}
		if (name.contains("Water")) {
			affinity = "Water";
			return;
		}
		if (name.contains("Lightning")) {
			affinity = "Lightning";
			return;
		}
		if (name.contains("Earth")) {
			affinity = "Earth";
			return;
		}
		if (name.contains("Wind")) {
			affinity = "Wind";
			return;
		}
		affinity = "";
	}

	/**
	 * Lower durability.
	 *
	 * @param value the value
	 */
	public void lowerDurability(double value) {
		durability -= value * 0.8;
	}

	/**
	 * Repair.
	 */
	public void repair() {
		if (durability + 100 > MAX_DURABILITY) {
			durability = MAX_DURABILITY;
			return;
		}
		durability += 100;
	}

	/**
	 * Gets damage.
	 *
	 * @return the damage
	 */
	public double getDamage() {
		return damage;
	}

	/**
	 * Is destroyable boolean.
	 *
	 * @return the boolean
	 */
	public boolean isDestroyable() {
		return destroyable;
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
	 * Gets durability.
	 *
	 * @return the durability
	 */
	public double getDurability() {
		return durability;
	}

	/**
	 * Gets affinity.
	 *
	 * @return the affinity
	 */
	public String getAffinity() {
		return affinity;
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
