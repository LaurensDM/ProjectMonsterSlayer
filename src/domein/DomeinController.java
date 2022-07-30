package domein;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Domein controller.
 */
public class DomeinController {

	private Game game;
	private PlayerRepo repo;
	private Player player;

	/**
	 * create an instance of PlayerRepository
	 */
	public DomeinController() {
		repo = new PlayerRepo();
	}

	/**
	 * starts a new game
	 */
	public void startGame() {
		game = new Game(player);
	}

	/**
	 * creates an enemy
	 *
	 * @param enemy the enemy
	 */
	public void registerEnemy(String enemy) {
		game.registerEnemy(enemy);
	}

	/**
	 * attack an enemy
	 *
	 * @param element element of the attack
	 * @return a String which contains information about the damage dealt
	 */
	public String attack(String element) {
		double damage = game.attack(element);
		if (damage == 0) {
			return "Your attack failed!\n";
		}
		return String.format("%sYour attack did %.2f damage!%n", game.isCriticalHit() ? "Critical hit!\n" : "", damage);
	}

	/**
	 * The enemy attacks back at your shield
	 *
	 * @return a String which contains information about damage dealt to your mana shield
	 */
	public String attackBack() {
		double damage = game.attackBack();
		if (damage == 0) {
			return "The enemy is frozen\n";
		}
		return String.format("Your shield withstood %.2f damage!%n", damage);
	}

	/**
	 * use a full power attack
	 */
	public void useAllOutAttack() {
		game.useAllOutAttack();
	}

	/**
	 * Use judgement.
	 */
	public void useJudgement() {
		game.useJudgement();
	}

	/**
	 * Gets enemy health.
	 *
	 * @return the enemy health
	 */
	public double getEnemyHealth() {
		return game.getEnemyHealth();
	}

	/**
	 * Geef enemy health string.
	 *
	 * @return the string
	 */
	public String geefEnemyHealth() {
		return String.format("The enemy has %.2f health left%n", game.getEnemyHealth());
	}

	/**
	 * Out of mana boolean.
	 *
	 * @return boolean whether you've run out of mana
	 */
	public boolean outOfMana() {
		return game.outOfMana();
	}

	/**
	 * Is defeated boolean.
	 *
	 * @return boolean whether you've defeated the enemy
	 */
	public boolean isDefeated() {
		return game.isDefeated();
	}

	/**
	 * Gets manapool.
	 *
	 * @return the manapool
	 */
	public double getManapool() {
		return game.getManapool();
	}

	/**
	 * Gets enemy.
	 *
	 * @return the enemy
	 */
	public String getEnemy() {
		return game.getEnemy();
	}

	/**
	 * Geef affinity string.
	 *
	 * @return your affinity
	 */
	public String geefAffinity() {
		return "Your affinity is " + game.getAffinity();
	}

	/**
	 * select an existing player
	 *
	 * @param name     the name
	 * @param password the password
	 */
	public void selectPlayer(String name, String password) {
		player = repo.selectPlayer(name, password);
	}

	public List<String> giveBag(){
		List<String> items = new ArrayList<>();
		for (Items item: player.getBag()) {
			items.add(item.toString());
		}
		return items;
	}

	/**
	 * create a new player
	 *
	 * @param name     the name
	 * @param password the password
	 * @param salt     the salt
	 * @param affinity the affinity
	 */
	public void registerPlayer(String name, String password,String salt, String affinity) {
		repo.registerPlayer(new Player(name, password,salt, affinity));
	}

	/**
	 * add exp
	 *
	 * @param value exp
	 */
	public void gainExp(int value) {
		player.gainExp(0);
	}

	/**
	 * if the amount of exp reaches a threshold, the player gains 1 level, exp gets
	 * reset
	 */
	public void levelUp() {
		player.levelUp();
	}

	/**
	 * turns magic stones into money
	 */
	public void convertStonesToMoney() {
		int value = 0;
		player.addMoney(value);
	}

	/**
	 * gives the details of the current player
	 *
	 * @return a players details
	 */
	public String giveDetailsPlayer() {
		return player.toString();
	}

	/**
	 * Gets player level.
	 *
	 * @return the player level
	 */
	public int getPlayerLevel() {
		return player.getLevel();
	}

	/**
	 * Show damage string.
	 *
	 * @return damage
	 */
	public String showDamage() {
		return game.getDamage();
	}

	/**
	 * add an item to the bag
	 *
	 * @param itemName the item name
	 * @param grade    the grade
	 */
	public void addItemtoBag(String itemName, int grade) {
		player.addItemToBag(new Items(itemName, grade));
	}

	/**
	 * Add skill.
	 *
	 * @param skillLevel the skill level
	 */
	public void addSkill(int skillLevel) {
		player.learnSkill(skillLevel);
	}

	/**
	 * Show efficiency level double.
	 *
	 * @return efficiency level
	 */
	public double showEfficiencyLevel() {
		return player.getSkills().getEfficiency();
	}

	/**
	 * Show power level double.
	 *
	 * @return power
	 */
	public double showPowerLevel() {
		return player.getSkills().getPower();
	}

	/**
	 * Show full power stage int.
	 *
	 * @return stage of full power
	 */
	public int showFullPowerStage() {
		return player.getSkills().getFullPowerStage();
	}

	/**
	 * Reflection acquired boolean.
	 *
	 * @return whether reflection is acquired
	 */
	public boolean reflectionAcquired() {
		return player.getSkills().isReflection();
	}

	/**
	 * True magic acquired boolean.
	 *
	 * @return whether true magic is acquired
	 */
	public boolean trueMagicAqcuired() {
		return player.getSkills().isTrueMagic();
	}

	/**
	 * Judgement acquired boolean.
	 *
	 * @return whether judgement is acquired
	 */
	public boolean judgementAcquired() {
		return player.getSkills().isJudgement();
	}
}
