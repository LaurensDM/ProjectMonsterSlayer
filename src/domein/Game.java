package domein;

import java.security.SecureRandom;

public class Game {

	SecureRandom sr = new SecureRandom();
	private Player player;
	private Enemy enemy;
	private Elements el;
	private boolean fullpower = false;
	private boolean judgement = false;
	private int critChance = 0;
	private boolean criticalHit = false;
	private String damage = "";

	protected double manapool;

	public Game(Player player) {
		this.player = player;
		manapool = player.getLevel() * 100;
	}

	public void registerEnemy(String enemy) {
		switch (enemy.toLowerCase()) {
		case "dragon":
			this.enemy = new Dragon();
			return;

		case "troll":
			this.enemy = new Troll();
			return;

		case "goblin":
			this.enemy = new Goblin();
			return;
		}
	}

	public double attack(String element) {
		double weaponDamage;
		if (player.getWeapon() == null) {
			weaponDamage = 1;
		} else
			weaponDamage = player.getWeapon().getDamage();

		if (element.equals("Fire")) {
			el = new Fire(manapool, player.getSkills(), player.getWeapon(), player.getArmor());
		} else if (element.equals("Water")) {
			el = new Water(manapool, player.getSkills(), player.getWeapon(), player.getArmor());
		} else if (element.equals("Lightning")) {
			el = new Lightning(manapool, player.getSkills(), player.getWeapon(), player.getArmor());
		} else if (element.equals("Wind")) {
			el = new Wind(manapool, player.getSkills(), player.getWeapon(), player.getArmor());
		} else {
			el = new Earth(manapool, player.getSkills(), player.getWeapon(), player.getArmor());
		}
		if (element.equals("True Magic")) {
			el = new True_Magic(manapool, player.getSkills(), player.getWeapon(), player.getArmor());
		}

		if (fullpower) {
			el.goAllOut();
			fullpower = false;
		}
		
		if (judgement) {
			el = new True_Magic(manapool, player.getSkills(), player.getWeapon(), player.getArmor());
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

	public void useAllOutAttck() {
		fullpower = true;
	}

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

	public boolean isCriticalHit() {
		return criticalHit;
	}

	public boolean outOfMana() {
		if (manapool <= 1) {
			return true;
		}
		return false;
	}

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

	public double getManapool() {
		return manapool;
	}

	public String getEnemy() {
		return enemy.toString();
	}

	public String getAffinity() {
		return player.getAffinity();
	}

	public String getDamage() {
		return damage;
	}

	public void useJudgement() {
		// TODO Auto-generated method stub
		judgement = true;
	}

}
