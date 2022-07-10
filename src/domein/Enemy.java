package domein;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.List;

/**
 * The type Enemy.
 */
public abstract class Enemy {

	/**
	 * The constant sr.
	 */
	protected static SecureRandom sr = new SecureRandom();
	private double health;
	private double defence;
	private String type;
	private double tickDamage = 0;
	private int freeze = 0;
	private boolean frozen = false;
	/**
	 * The Evolved.
	 */
	protected boolean evolved = false;
	/**
	 * The Enemies.
	 */
	public final static List<String> ENEMIES = Arrays.asList("Dragon", "Troll", "Goblin");
	public final static List<String> STAGE_0 = Arrays.asList("Slime");
	public final static List<String> STAGE_1 = Arrays.asList("Slime","Goblin");
	public final static List<String> STAGE_2 = Arrays.asList("Troll","Goblin");
	public final static List<String> STAGE_3 = Arrays.asList("Goblin","Troll","Golem");
	public final static List<String> STAGE_4 = Arrays.asList("Troll","Golem");
	public final static List<String> STAGE_5 = Arrays.asList("Golem","Dragon");


	/**
	 * Instantiates a new Enemy.
	 *
	 * @param health  the health
	 * @param defence the defence
	 * @param type    the type
	 */
	public Enemy(double health, double defence, String type) {
		setHealth(health);
		setDefence(defence);
		setType(type);
	}

	/**
	 * Is strong against boolean.
	 *
	 * @param damageType the damage element
	 * @return the boolean
	 */
	public boolean isStrongAgainst(String damageType) {
		if (type.equals("Fire") && damageType.equals("Wind")) {
			return true;
		}
		if (type.equals("Wind") && damageType.equals("Earth")) {
			return true;
		}
		if (type.equals("Earth") && damageType.equals("Lightning")) {
			return true;
		}
		if (type.equals("Lightning") && damageType.equals("Water")) {
			return true;
		}
		if (type.equals("Water") && damageType.equals("Fire")) {
			return true;
		}
		return false;
	}

	/**
	 * Is weakness boolean.
	 *
	 * @param damageType the damage element
	 * @return the boolean
	 */
	public boolean isWeakness(String damageType) {

		if (type.equals("Fire") && damageType.equals("Water")) {
			return true;
		}
		if (type.equals("Wind") && damageType.equals("Fire")) {
			return true;
		}
		if (type.equals("Earth") && damageType.equals("Wind")) {
			return true;
		}
		if (type.equals("Lightning") && damageType.equals("Earth")) {
			return true;
		}
		if (type.equals("Water") && damageType.equals("Lightning")) {
			return true;
		}
		if (damageType.equals("True Magic")) {
			return true;
		}

		return false;
	}

	/**
	 * Gets type.
	 *
	 * @return the type (element)
	 */
	public String getType() {
		return type;
	}

	/**
	 * Sets type.
	 *
	 * @param type the type (element)
	 */
	protected void setType(String type) {
		this.type = type;
	}

	/**
	 * Gets health.
	 *
	 * @return the health
	 */
	public double getHealth() {
		return health;
	}

	/**
	 * Register damage.
	 *
	 * @param totalDamage the total damage
	 */
	protected void registerDamage(double totalDamage) {
		health -= totalDamage;
	}

	/**
	 * Attack back.
	 *
	 * @return the damage
	 */
	public abstract double attackBack();

	/**
	 * Evolve.
	 */
	public abstract void evolve();

	/**
	 * Determine item grade int.
	 *
	 * @param fullpower whether full power is activated
	 * @return the item grade
	 */
	protected abstract int determineItemGrade(boolean fullpower);

	/**
	 * Drop items.
	 *
	 * @param fullpower whether full power was activated
	 * @return the items
	 */
	public Items[] dropItem(boolean fullpower) {

		Items[] drops = new Items[sr.nextInt(4) + 1];
		int grade;
		String name;
		String item;

		grade = determineItemGrade(fullpower);
		drops[0] = new Magic_Stone(getType() + this.getClass().getSimpleName() + " Stone", grade);

		for (int i = 1; i < drops.length; i++) {

			item = Items.ITEMS.get(sr.nextInt(Items.ITEMS.size()));
			grade = determineItemGrade(fullpower);

			switch (item) {
			
			case "Mana Potion":
				name = getType() + getClass().getSimpleName() + " Essence Potion";
				if (grade == 5)
					name = "Greater Mana Potion";
				if (grade == 6)
					name = "Arcana Potion";
				drops[i] = new Mana_Potion(name, grade);
				break;
				
			case "Power Potion":
				name = getType() + getClass().getSimpleName() + " Blood Potion";
				if (grade == 5)
					name = "Greater Power Potion";
				if (grade == 6)
					name = "Ancient Power Potion";
				drops[i] = new Power_Potion(name, grade);
				break;
				
			case "Armor":
				name = getType() + getClass().getSimpleName();
				if (getClass().getSimpleName().equals("Dragon"))
					name += " Scale Armor";
				if (getClass().getSimpleName().equals("Golem"))
					name += " Rock Armor";
				if (getClass().getSimpleName().equals("Troll"))
					name += " Hide Armor";
				if (grade == 5)
					name = "Robe of Power";
				if (grade == 6)
					name = "Robe of the Archmagi";
				drops[i] = new Armor(name, grade);
				break;
				
			case "Weapon":
				name = getType() + getClass().getSimpleName();
				if (getClass().getSimpleName().equals("Dragon"))
					name += " Bone Staff";
				if (getClass().getSimpleName().equals("Golem"))
					name += " Core Staff";
				if (getClass().getSimpleName().equals("Troll"))
					name += " Bone Staff";
				if (grade == 5)
					name = "Staff of Power";
				if (grade == 6)
					name = "Staff of the Archmagi";
				drops[i] = new Weapon(name, grade);
				break;
			}
		}

		return drops;
	}

	/**
	 * Lower defence.
	 *
	 * @param shred by how much the defense is lowered, usually by 5%
	 */
	protected void lowerDefence(double shred) {
		if (defence - shred > 0) {
			defence -= shred;
			return;
		}
		defence = 0;
	}

	/**
	 * Sets health.
	 *
	 * @param health the health
	 */
	protected void setHealth(double health) {
		if (health < 100) {
			throw new IllegalArgumentException("A monster always has at least 100 health!");
		}
		this.health = health;
	}

	/**
	 * Gets defence.
	 *
	 * @return the defence
	 */
	public double getDefence() {
		return defence;
	}

	/**
	 * Gets tick damage.
	 *
	 * @return the tick damage
	 */
	public double getTickDamage() {
		return tickDamage;
	}

	/**
	 * Sets defence.
	 *
	 * @param defence the defence
	 */
	protected void setDefence(double defence) {
		this.defence = defence;
	}

	/**
	 * Take damage double.
	 *
	 * @param damageType the damage type
	 * @param damage     the damage
	 * @return how much damage was taken
	 */
	public double takeDamage(String damageType, double damage) {
		if (damageType.equals(type)) {
			registerDamage(damage * 0.5);
			return damage * 0.5;
		}
		if (damageType.equals("Water") && !type.equals("Water")) {
			freeze++;
			if (freeze == 3) {
				freeze = 0;
				frozen = true;
			}
		}

		if (damageType.equals("Earth") && !type.equals("Earth")) {
			lowerDefence(0.05);
		}
		double totalDamage = (damage - damage * getDefence()) + tickDamage;
		registerDamage(totalDamage);

		if (damageType.equals("Lightning") && !type.equals("Lightning")) {
			tickDamage = damage * 0.25;

		}

		if (damageType.equals("True Magic")) {
			totalDamage *= 2;
			lowerDefence(1);
		}

		return totalDamage;
	}

	public String toString() {
		return String.format("%s %s", type, this.getClass().getSimpleName());
	}

	/**
	 * Is frozen boolean.
	 *
	 * @return the boolean
	 */
	public boolean isFrozen() {
		return frozen;
	}

	/**
	 * Break frozen.
	 */
	public void breakFrozen() {
		frozen = false;
	}

}
