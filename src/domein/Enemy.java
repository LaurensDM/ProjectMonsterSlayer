package domein;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.List;

public abstract class Enemy {

	protected static SecureRandom sr = new SecureRandom();
	private double health;
	private double defence;
	private String type;
	private double tickDamage = 0;
	private int freeze = 0;
	private boolean frozen = false;
	protected boolean evolved = false;
	public final static List<String> ENEMIES = Arrays.asList("Dragon", "Troll", "Goblin");

	public Enemy(double health, double defence, String type) {
		setHealth(health);
		setDefence(defence);
		setType(type);
	}

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

	public String getType() {
		return type;
	}

	protected void setType(String type) {
		this.type = type;
	}

	public double getHealth() {
		return health;
	}

	protected void registerDamage(double totalDamage) {
		health -= totalDamage;
	}

	public abstract double attackBack();

	public abstract void evolve();

	protected abstract int determineItemGrade(boolean fullpower);

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

	protected void lowerDefence(double shred) {
		if (defence - shred > 0) {
			defence -= shred;
			return;
		}
		defence = 0;
	}

	protected void setHealth(double health) {
		if (health < 100) {
			throw new IllegalArgumentException("A monster always has at least 100 health!");
		}
		this.health = health;
	}

	public double getDefence() {
		return defence;
	}

	public double getTickDamage() {
		return tickDamage;
	}

	protected void setDefence(double defence) {
		this.defence = defence;
	}

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

	public boolean isFrozen() {
		return frozen;
	}

	public void breakFrozen() {
		frozen = false;
	}

}
