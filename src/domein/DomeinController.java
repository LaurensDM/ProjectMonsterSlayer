package domein;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

/**
 * The type Domein controller.
 */
public class DomeinController {

    private Game game;
    private PlayerRepo repo;
    private Player player;
    private String craftingComponent1;
    private String craftingComponent2;


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
    public void registerEnemy() {
        game.registerEnemy();
    }

    /**
     * attack an enemy
     *
     * @param element element of the attack
     */
    public void attack(String element) {
        game.attack(element);

    }

    /**
     * The enemy attacks back at your shield
     */
    public void attackBack() {
        game.attackBack();
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

    public boolean bagIsFull() {
        return game.isBagFull();
    }

    /**
     * Gets manapool.
     *
     * @return the manapool
     */
    public double getManapool() {
        return game.getManapool();
    }

    public double getMaxMana() {
        return game.getMaxMana();
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
     * select an existing player
     *
     * @param name     the name
     * @param password the password
     */
    public void selectPlayer(String name, String password) {
        player = repo.selectPlayer(name, password);
    }

    public List<String> giveBag() {
        List<String> items = new ArrayList<>();
        for (Entry<Items, Integer> entry : player.getBag().entrySet()) {

            items.add(entry.getKey().toString() + " " + entry.getValue());
        }
        return items;
    }

    public String selectItem(String itemDesc, boolean inGame) {
        return game.selectItem(itemDesc, inGame);
    }

    public void unequipWeapon() {
        player.unequipWeapon();
    }

    public void unequipArmor() {
        player.unequipArmor();
    }

    /**
     * create a new player
     *
     * @param name     the name
     * @param password the password
     * @param salt     the salt
     * @param affinity the affinity
     */
    public void registerPlayer(String name, String password, String salt, String affinity) {
        repo.registerPlayer(new Player(name, password, salt, affinity));
    }

    /**
     * turns magic stones into money
     */
    public void convertStonesToMoney() {
        game.convertStonesToMoney();
    }

    public int getPriceItem(String item) {
        return game.getPriceItem(item);
    }

    public void buyItem(String item) {
        game.buyItem(item);
    }

    public void sellItem(String item) {
        game.sellItem(item);
    }

    public List<String> getMerchantStock() {
        List<String> stock = new ArrayList<>();
        for (Items item : game.merchantStock()) {
            stock.add(item.toString());
        }
        return stock;
    }

    /**
     * gives the details of the current player
     *
     * @return a players details
     */
    public String giveDetailsPlayer() {
        return player.toString();
    }

    public String getPlayerName() {
        return player.getName();
    }

    /**
     * Gets player level.
     *
     * @return the player level
     */
    public int getPlayerLevel() {
        return player.getLevel();
    }

    public int getPlayerExp() {
        return player.getExp();
    }

    public int getPlayerMaxExp() {
        return game.getPlayerMaxExp();
    }

    public int getPlayerMoney() {
        return player.getMoney();
    }

    public String getPlayerRank() {
        return player.getAdventureRank();
    }

    /**
     * Show damage string.
     *
     * @return damage
     */
    public String showDamage() {
        return game.getDamage();
    }

    public boolean isCriticalHit() {
        return game.isCriticalHit();
    }


    public String getWeaponDetails() {
        return player.getWeapon().toString();
    }

    public double getWeaponDurability() {
        return player.getWeapon().getDurability();
    }

    public String getArmorDetails() {
        return player.getArmor().toString();
    }

    public double getArmorDurability() {
        return player.getArmor().getDurability();
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

    public void savePlayer() {
        repo.savePlayer(player);
    }

    public void regenerateMana() {
        game.regenerateMana();
    }

    public void craftItem() {
        game.craftItem(craftingComponent1, craftingComponent2);
    }

    public void selectComponent1(String component) {
        craftingComponent1 = component;
    }

    public void selectComponent2(String component) {
        craftingComponent2 = component;
    }

    public int deselectComponent(String component) {
        if (craftingComponent1 != null && component.equals(craftingComponent1)) {
            craftingComponent1 = null;
            return 0;
        }
        if (craftingComponent2 != null && component.equals(craftingComponent2)) {
            craftingComponent2 = null;
            return 1;
        }
        return 2;
    }

    public String getCraftingComponent1() {
        if (craftingComponent1 == null) return "";
        return craftingComponent1;
    }

    public String getCraftingComponent2() {
        if (craftingComponent2 == null) return "";
        return craftingComponent2;
    }
}
