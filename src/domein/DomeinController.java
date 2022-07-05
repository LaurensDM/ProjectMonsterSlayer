package domein;

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
	 */
	public void registerEnemy(String enemy) {
		game.registerEnemy(enemy);
	}

	/**
	 * attack an enemy
	 * 
	 * @param the element of the attack
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
	 * @return a String which contains information about damage dealt to your mana
	 *         shield
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
		game.useAllOutAttck();
	}
	
	public void useJudgement() {
		game.useJudgement();
	}

	public double getEnemyHealth() {
		return game.getEnemyHealth();
	}

	public String geefEnemyHealth() {
		return String.format("The enemy has %.2f health left%n", game.getEnemyHealth());
	}

	public boolean outOfMana() {
		return game.outOfMana();
	}

	public boolean isDefeated() {
		return game.isDefeated();
	}

	public double getManapool() {
		return game.getManapool();
	}

	public String getEnemy() {
		return game.getEnemy();
	}

	public String geefAffinity() {
		return "Your affinity is " + game.getAffinity();
	}

	/**
	 * select an existing player
	 * 
	 * @param the players name and password
	 */
	public void selectPlayer(String name, String password) {
		player = repo.selectPlayer(name, password);
	}

	/**
	 * create a new player
	 * 
	 * @param the players name, password and affinity
	 */
	public void registerPlayer(String name, String password, String affinity) {
		repo.registerPlayer(new Player(name, password, affinity));
	}

	/**
	 * add exp
	 * 
	 * @param how much experience was earned
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
	 */
	public String giveDetailsPlayer() {
		return player.toString();
	}

	public int getPlayerLevel() {
		return player.getLevel();
	}
	
	public String showDamage() {
		return game.getDamage();
	}

	/**
	 * add an item to the bag
	 * 
	 * @param the name of the item and its grade
	 */
	public void addItemtoBag(String itemName, int grade) {
		player.addItemToBag(new Items(itemName, grade));
	}

	public void addSkill(int skillLevel) {
		player.learnSkill(skillLevel);
	}

	public double showEfficiencyLevel() {
		return player.getSkills().getEfficiëncy();
	}

	public double showPowerLevel() {
		return player.getSkills().getPower();
	}

	public int showFullPowerStage() {
		return player.getSkills().getFullPowerStage();
	}

	public boolean reflectionAcquired() {
		return player.getSkills().isReflection();
	}

	public boolean trueMagicAqcuired() {
		return player.getSkills().isTrueMagic();
	}
	
	public boolean judgementAcquired() {
		return player.getSkills().isJudgement();
	}
}
