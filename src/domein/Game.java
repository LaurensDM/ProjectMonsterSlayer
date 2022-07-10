package domein;

import java.security.SecureRandom;

/**
 * The type Game.
 */
public class Game {

	/**
	 * The Sr.
	 */
	SecureRandom sr = new SecureRandom();
	private final Player player;
	private Enemy enemy;
	private Elements el;
	private boolean fullpower = false;
	private boolean judgement = false;
	private int critChance = 0;
	private boolean criticalHit = false;
	private String damage = "";

	/**
	 * The Manapool.
	 */
	protected double manapool;

	/**
	 * The Max mana.
	 */
	public final double MAX_MANA;

	/**
	 * Instantiates a new Game.
	 *
	 * @param player the player
	 */
	public Game(Player player) {
		this.player = player;
		manapool = player.getLevel() * 100;
		this.MAX_MANA = manapool;
	}

	/**
	 * Register enemy.
	 *
	 * @param enemy the enemy
	 */
	public void registerEnemy(String enemy) {
		switch (enemy.toLowerCase()) {
			case "dragon" -> this.enemy = new Dragon();

			case "troll" -> this.enemy = new Troll();

			case "goblin" -> this.enemy = new Goblin();
		}
	}

	/**
	 * Attack.
	 *
	 * @param element the element
	 * @return damage
	 */
	public double attack(String element) {
		double weaponDamage;
		if (player.getWeapon() == null) {
			weaponDamage = 1;
		} else
			weaponDamage = player.getWeapon().getDamage();

		switch (element) {
			case "Fire" -> el = new Fire(manapool, player.getSkills(), MAX_MANA);
			case "Water" -> el = new Water(manapool, player.getSkills(), MAX_MANA);
			case "Lightning" -> el = new Lightning(manapool, player.getSkills(), MAX_MANA);
			case "Wind" -> el = new Wind(manapool, player.getSkills(), MAX_MANA);
			case "Earth" -> el = new Earth(manapool, player.getSkills(), MAX_MANA);
			case "True Magic" -> el = new True_Magic(manapool, player.getSkills(), MAX_MANA);
		}

		if (fullpower) {
			el.goAllOut();
			fullpower = false;
		}
		
		if (judgement) {
			el = new True_Magic(manapool, player.getSkills(), MAX_MANA);
			el.deliverJudgement();
		}

		if (el instanceof Wind || el instanceof True_Magic) {
			critChance++;
		}

		double damage = el.attack() * activateCrit();

		if (element.equals(player.getAffinity())) {
			damage *= 2;
		}

		damage *= player.getSkills().getPower() * weaponDamage;
		double totalDamage = enemy.takeDamage(element, damage);
		manapool = el.getMana();
		if (player.getWeapon() != null) {
			player.getWeapon().lowerDurability(totalDamage);
		}

		this.damage = "You did " + totalDamage + " damage!";
		return totalDamage;
	}

	/**
	 * Attack back.
	 *
	 * @return damage
	 */
	public double attackBack() {
		double damage = enemy.attackBack();

		if (damage == 0) {
			return 0;
		}

		if (player.getSkills().isReflection()) {
			if (sr.nextInt(5) == 0) {
				enemy.takeDamage(enemy.getType(), damage);
				damage = 0;
			}
		}
		if (player.getArmor() != null && enemy.getType().equals(player.getArmor().getResistance())) {
			damage *= 0.5;
		}
		double damageReduction;
		if (player.getArmor() == null) {
			damageReduction = 1;
		} else
			damageReduction = player.getArmor().getDamageReduction();
		el.activateShield(damage * damageReduction);
		manapool = el.getMana();
		if (player.getArmor() != null) {
			player.getArmor().lowerDurability(damage * player.getArmor().getDamageReduction());
		}

		return damage;
	}

	/**
	 * Use all out attack.
	 */
	public void useAllOutAttack() {
		fullpower = true;
	}

	/**
	 * Gets enemy health.
	 *
	 * @return the enemy health
	 */
	public double getEnemyHealth() {
		return enemy.getHealth();
	}

	private int activateCrit() {
		criticalHit = false;
		if (critChance == 9) {
			critChance = 0;
			criticalHit = true;
			return 2;
		}
		int chance = sr.nextInt(10 - critChance);
		if (chance == 1) {
			criticalHit = true;
			return 2;
		}

		return 1;
	}

	/**
	 * Is critical hit.
	 *
	 * @return the boolean
	 */
	public boolean isCriticalHit() {
		return criticalHit;
	}

	/**
	 * Out of mana boolean.
	 *
	 * @return the boolean
	 */
	public boolean outOfMana() {
		return manapool <= 1;
	}

	/**
	 * Is defeated boolean.
	 *
	 * @return the boolean
	 */
	public boolean isDefeated() {
		if (enemy.getHealth() <= 0) {
			dropItems();
			return true;
		}
		return false;
	}

	private void dropItems() {
		Items[] items = enemy.dropItem(fullpower);
		for (Items item : items) {
			player.addItemToBag(item);
		}

	}

	/**
	 * Gets manapool.
	 *
	 * @return the manapool
	 */
	public double getManapool() {
		return manapool;
	}

	/**
	 * Gets enemy.
	 *
	 * @return the enemy
	 */
	public String getEnemy() {
		return enemy.toString();
	}

	/**
	 * Gets affinity.
	 *
	 * @return the affinity
	 */
	public String getAffinity() {
		return player.getAffinity();
	}

	/**
	 * Gets damage.
	 *
	 * @return the damage
	 */
	public String getDamage() {
		return damage;
	}

	/**
	 * Use judgement.
	 */
	public void useJudgement() {
		// TODO Auto-generated method stub
		judgement = true;
	}

}
