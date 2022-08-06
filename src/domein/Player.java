package domein;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * The type Player.
 */
public class Player {

	private String name;
	private String passwordHash;
	private final String salt;
	private String affinity;
	private int level;
	private int exp;
	private int money;
	private List<Items> bag;
	private Skills skills;
	private Weapon weapon;
	private Armor armor;
	//rank from F to S
	private String adventureRank;

	/**
	 * Instantiates a new Player.
	 *
	 * @param name         the name
	 * @param passwordHash the password hash
	 * @param salt         the salt
	 * @param affinity     the affinity
	 * @param level        the level
	 * @param exp          the exp
	 * @param money        the money
	 * @param bag          the bag
	 * @param skills       the skills
	 * @param weapon       the weapon
	 * @param armor        the armor
	 */
	public Player(String name, String passwordHash,String salt, String affinity, int level, int exp, int money, List<Items> bag,
			Skills skills, Weapon weapon, Armor armor, String rank) {
		setName(name);
		this.passwordHash=passwordHash;
		this.salt = salt;
		setAffinity(affinity);
		setLevel(level);
		setExp(exp);
		setMoney(money);
		this.bag = bag;
		this.skills = skills;
		this.weapon = weapon;
		this.armor = armor;
		this.adventureRank = rank;
	}

	/**
	 * Instantiates a new Player.
	 *
	 * @param name       the name
	 * @param passwordHash the passwordHash
	 * @param salt       the salt
	 * @param affinity   the affinity
	 */
	public Player(String name, String passwordHash,String salt, String affinity) {
		this(name, passwordHash, salt,affinity, 1, 0, 100, new ArrayList<>(), new Skills(), null, null,"unranked");
	}

	/**
	 * Gets name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	private void setName(String name) {
		if (name == null || name.isBlank() || name.length() < 3) {
			throw new IllegalArgumentException("Your username must have more than 2 characters!");
		}
		this.name = name;
	}

	/**
	 * Gets wachtwoord.
	 *
	 * @return the wachtwoord
	 */
	public String getPasswordHash() {
		return passwordHash;
	}

	/*private void setWachtwoord(String wachtwoord) {
		if (wachtwoord == null || wachtwoord.length() < 3 || !wachtwoord.matches("(\\w*\\d{1}){3,}")) {
			throw new IllegalArgumentException(
					"Uw wachtwoord moet uit minstens 3 characters bestaan en minstens 2 cijfers bevatten!");
		}
		this.passwordHash = wachtwoord;
	}*/

	public String getSalt() {
		return salt;
	}

	/**
	 * Gets affinity.
	 *
	 * @return the affinity
	 */
	public String getAffinity() {
		return affinity;
	}

	private void setAffinity(String affinity) {
		this.affinity = affinity;
	}

	/**
	 * Gets level.
	 *
	 * @return the level
	 */
	public int getLevel() {
		return level;
	}

	private void setLevel(int level) {
		this.level = level;
	}

	/**
	 * Gets exp.
	 *
	 * @return the exp
	 */
	public int getExp() {
		return exp;
	}

	private void setExp(int exp) {
		this.exp = exp;
	}

	/**
	 * Gets money.
	 *
	 * @return the money
	 */
	public int getMoney() {
		return money;
	}

	private void setMoney(int money) {
		this.money = money;
	}

	/**
	 * Gets bag.
	 *
	 * @return the bag
	 */
	public List<Items> getBag() {
		return bag;
	}

	/**
	 * Gets skills.
	 *
	 * @return the skills
	 */
	public Skills getSkills() {
		return skills;
	}

	/**
	 * Gets weapon.
	 *
	 * @return the weapon
	 */
	public Weapon getWeapon() {
		return weapon;
	}

	/**
	 * Gets armor.
	 *
	 * @return the armor
	 */
	public Armor getArmor() {
		return armor;
	}

	/**
	 * Level up.
	 */
	public void levelUp() {

		if (level < 50 && exp >= level * 1.5) {
			exp -= level * 1.5;
			level++;
		}
	}

	/**
	 * Add money.
	 *
	 * @param value the value
	 */
	protected void addMoney(int value) {
		money += value;
	}

	/**
	 * Gain exp.
	 *
	 * @param value the value
	 */
	protected void gainExp(int value) {
		if (level < 50) {
			exp += value;
		}

	}

	/**
	 * Add item to bag.
	 *
	 * @param item the item
	 */
	protected void addItemToBag(Items item) {
		bag.add(item);
	}

	/**
	 * Equip weapon.
	 *
	 * @param weapon the weapon
	 */
	public void equipWeapon(Weapon weapon) {
		this.weapon = weapon;
	}

	/**
	 * Equip armor.
	 *
	 * @param armor the armor
	 */
	public void equipArmor(Armor armor) {
		this.armor = armor;
	}

	/**
	 * Learn skill.
	 *
	 * @param skillLevel the skill level
	 */
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

	public String getAdventureRank() {
		return adventureRank;
	}
}
