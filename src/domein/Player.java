package domein;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Player {

	private String name;
	private String wachtwoord;
	private String affinity;
	private int level;
	private int exp;
	private int money;
	private List<Items> bag;
	private Skills skills;
	private Weapon weapon;
	private Armor armor;

	public Player(String name, String wachtwoord, String affinity, int level, int exp, int money, List<Items> bag,
			Skills skills, Weapon weapon, Armor armor) {
		setName(name);
		setWachtwoord(wachtwoord);
		setAffinity(affinity);
		setLevel(level);
		setExp(exp);
		setMoney(money);
		this.bag = bag;
		this.skills = skills;
		this.weapon = weapon;
		this.armor = armor;
	}

	public Player(String name, String wachtwoord, String affinity) {
		this(name, wachtwoord, affinity, 1, 0, 100, new ArrayList<>(), new Skills(), null, null);
	}

	public String getName() {
		return name;
	}

	private void setName(String name) {
		if (name == null || name.isBlank() || name.length() < 3) {
			throw new IllegalArgumentException("Your username must have more than 2 characters!");
		}
		this.name = name;
	}

	public String getWachtwoord() {
		return wachtwoord;
	}

	private void setWachtwoord(String wachtwoord) {
		if (wachtwoord == null || wachtwoord.length() < 3 || !wachtwoord.matches("(\\w*\\d{1}){3,}")) {
			throw new IllegalArgumentException(
					"Uw wachtwoord moet uit minstens 3 characters bestaan en minstens 2 cijfers bevatten!");
		}
		this.wachtwoord = wachtwoord;
	}

	public String getAffinity() {
		return affinity;
	}

	private void setAffinity(String affinity) {
		this.affinity = affinity;
	}

	public int getLevel() {
		return level;
	}

	private void setLevel(int level) {
		this.level = level;
	}

	public int getExp() {
		return exp;
	}

	private void setExp(int exp) {
		this.exp = exp;
	}

	public int getMoney() {
		return money;
	}

	private void setMoney(int money) {
		this.money = money;
	}

	public List<Items> getBag() {
		return bag;
	}

	public Skills getSkills() {
		return skills;
	}

	public Weapon getWeapon() {
		return weapon;
	}

	public Armor getArmor() {
		return armor;
	}

	public void levelUp() {

		if (level < 50 && exp >= level * 1.5) {
			exp -= level * 1.5;
			level++;
		}
	}

	protected void addMoney(int value) {
		money += value;
	}

	protected void gainExp(int value) {
		if (level < 50) {
			exp += value;
		}

	}

	protected void addItemToBag(Items item) {
		bag.add(item);
	}
	
	public void equipWeapon(Weapon weapon) {
		this.weapon = weapon;
	}
	
	public void equipArmor(Armor armor) {
		this.armor = armor;
	}

	protected void learnSkill(int skillLevel) {
		skills.activateSkills(skillLevel);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Player other = (Player) obj;
		return Objects.equals(name, other.name);
	}

	@Override
	public String toString() {
		return "Player [name=" + name + ", affinity=" + affinity + "]";
	}

}
