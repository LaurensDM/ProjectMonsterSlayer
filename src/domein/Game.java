package domein;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map.Entry;

/**
 * The type Game.
 */
public class Game {

    private final Player player;
    /**
     * The Manapool.
     */
    protected double manapool;
    /**
     * The Sr.
     */
    SecureRandom sr = new SecureRandom();
    /**
     * The Max mana.
     */
    private double maxMana;
    private Enemy enemy;
    private Elements el;
    private boolean fullpower = false;
    private boolean judgement = false;
    private int critChance = 0;
    private boolean criticalHit = false;
    private String damage = "";
    private double powerBoost = 1;
    private Crafting craftingSystem;

    /**
     * Instantiates a new Game.
     *
     * @param player the player
     */
    public Game(Player player) {
        this.player = player;
        manapool = player.getLevel() * 100;
        this.maxMana = manapool;
        craftingSystem = new Crafting();
    }

    /**
     * Register enemy.
     */
    public void registerEnemy() {
        String enemy = "";
        if (player.getLevel() < 5) {
            enemy = "Slime";
        }
        if (player.getLevel() < 10 && player.getLevel() >= 5) {
            Collections.shuffle(Enemy.STAGE_1);
            enemy = Enemy.STAGE_1.get(0);
        }
        if (player.getLevel() < 20 && player.getLevel() >= 10) {
            Collections.shuffle(Enemy.STAGE_2);
            enemy = Enemy.STAGE_2.get(0);
        }
        if (player.getLevel() < 30 && player.getLevel() >= 20) {
            Collections.shuffle(Enemy.STAGE_3);
            enemy = Enemy.STAGE_3.get(0);
        }
        if (player.getLevel() < 40 && player.getLevel() >= 30) {
            Collections.shuffle(Enemy.STAGE_4);
            enemy = Enemy.STAGE_4.get(0);
        }
        if (player.getLevel() >= 40) {
            Collections.shuffle(Enemy.STAGE_5);
            enemy = Enemy.STAGE_5.get(0);
        }

        switch (enemy.toLowerCase()) {
            case "dragon" -> this.enemy = new Dragon();

            case "golem" -> this.enemy = new Golem();

            case "demon" -> this.enemy = new Demon();

            case "troll" -> this.enemy = new Troll();

            case "goblin" -> this.enemy = new Goblin();

            case "slime" -> this.enemy = new Slime();
        }
    }

    /**
     * Attack.
     *
     * @param element the element
     */
    public void attack(String element) {
        double weaponDamage;
        if (player.getWeapon() == null) {
            weaponDamage = 1;
        } else weaponDamage = player.getWeapon().getDamage();


        switch (element) {
            case "Fire" -> el = new Fire(manapool, player.getSkills(), maxMana);
            case "Water" -> el = new Water(manapool, player.getSkills(), maxMana);
            case "Lightning" -> el = new Lightning(manapool, player.getSkills(), maxMana);
            case "Wind" -> el = new Wind(manapool, player.getSkills(), maxMana);
            case "Earth" -> el = new Earth(manapool, player.getSkills(), maxMana);
            case "True Magic" -> el = new True_Magic(manapool, player.getSkills(), maxMana);
        }

        if (fullpower) {
            el.goAllOut();
        }

        if (judgement) {
            el = new True_Magic(manapool, player.getSkills(), maxMana);
            el.deliverJudgement();

        }

        if (el instanceof Wind || el instanceof True_Magic) {
            critChance++;
        }

        double damage = el.attack() * activateCrit() * powerBoost;

        if (element.equals(player.getAffinity())) {
            damage *= 2;
        }

        damage *= player.getSkills().getPower() * weaponDamage;
        double totalDamage = enemy.takeDamage(element, damage, judgement);
        manapool = el.getMana();
        if (player.getWeapon() != null) {
            player.getWeapon().lowerDurability(fullpower);
            if (player.getWeapon().getDurability() <= 0) {
                player.destroyWeapon();
            }
        }
        judgement = false;
        fullpower = false;
        this.damage = String.format("You did %.0f damage!", totalDamage);
    }

    /**
     * Attack back.
     */
    public void attackBack() {
        double damage = enemy.attackBack();


        if (player.getSkills().isReflection()) {
            if (sr.nextInt(5) == 0) {
                enemy.takeDamage(enemy.getType(), damage, false);
                damage = 0;
            }
        }
        if (player.getArmor() != null && enemy.getType().equals(player.getArmor().getResistance())) {
            damage *= 0.5;
        }
        double damageReduction;
        if (player.getArmor() == null) {
            damageReduction = 1;
        } else {
            damageReduction = player.getArmor().getDamageReduction();
            player.getArmor().lowerDurability();
            if (player.getArmor().getDurability() <= 0) {
                player.destroyArmor();
            }
        }

        el.activateShield(damage * damageReduction);
        manapool = el.getMana();
    }

    public String selectItem(String itemDesc, boolean inGame) {
        Items item = null;
        String[] array = itemDesc.split(" ");
        String itemString = "";

        for (int i = 0; i < array.length - 1; i++) {

            if (i == array.length - 2) itemString += array[i];
            else itemString += array[i] + " ";

        }

        for (Entry<Items, Integer> entry : player.getBag().entrySet()) {
            if (entry.getKey().toString().equals(itemString)) {
                item = entry.getKey();
            }
        }

        if (item != null) {
            if (item instanceof Weapon) {
                if (inGame == true) throw new IllegalArgumentException("Weapons cannot be equipped mid battle!");
                player.equipWeapon((Weapon) item);
                player.removeItemFromBag(item);
                return item + " equipped!";
            }

            if (item instanceof Armor) {
                if (inGame == true) throw new IllegalArgumentException("Armor cannot be equipped mid battle!");
                player.equipArmor((Armor) item);
                player.removeItemFromBag(item);
                return item + " equipped!";
            }

            if (item instanceof Power_Potion) {
                if (inGame == false) throw new IllegalArgumentException("Potions shouldn't be used outside of battle!");
                activatePowerBoost(((Power_Potion) item).effect());
                player.removeItemFromBag(item);
                return item + " used!";
            }
            if (item instanceof Mana_Potion) {
                if (inGame == false) throw new IllegalArgumentException("Potions shouldn't be used outside of battle!");
                restoreMana(((Mana_Potion) item).effect());
                player.removeItemFromBag(item);
                return item + " used!";
            }
        }

        return "No item was selected";
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
            critChance = 0;
            powerBoost = 1;
            dropItems();
            gainExp();
            levelUp();
            return true;
        }
        return false;
    }

    /**
     * add exp
     */
    private void gainExp() {
        player.gainExp(enemy.dropExp());
    }

    /**
     * if the amount of exp reaches a threshold, the player gains 1 level, exp gets
     * reset
     */
    private void levelUp() {
        if (player.levelUp()) {
            manapool = player.getLevel() * 100;
            maxMana = manapool;
        }
    }

    public int getPlayerMaxExp() {
        int maxExp = 100;

        if (player.getLevel() < 10) {
            maxExp = player.getLevel() * 100;
        }
        if (player.getLevel() < 20 && player.getLevel() >= 10) {
            maxExp = player.getLevel() * 200;
        }
        if (player.getLevel() < 30 && player.getLevel() >= 20) {
            maxExp = player.getLevel() * 400;
        }
        if (player.getLevel() < 40 && player.getLevel() >= 30) {
            maxExp = player.getLevel() * 800;
        }
        if (player.getLevel() < 50 && player.getLevel() >= 40) {
            maxExp = player.getLevel() * 1600;
        }

        return maxExp;
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

    public double getMaxMana() {
        return maxMana;
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

    public boolean isBagFull() {

        if (player.getBag().size() >= 34) {
            return true;
        }
        return false;
    }

    public void activatePowerBoost(double value) {
        powerBoost = value;
    }

    public void restoreMana(double restoration) {
        if (restoration >= 100 || restoration < 0) {
            if (manapool + restoration > maxMana) manapool = maxMana;
            else manapool += restoration;
        } else {
            if (manapool + maxMana * restoration > maxMana) manapool = maxMana;
            else manapool += maxMana * restoration;
        }


    }

    public void regenerateMana() {
        if (manapool + maxMana * 0.01 <= maxMana) {
            manapool += maxMana * 0.01;
        } else {
            manapool = maxMana;
        }

    }

    public List<Items> merchantStock() {
        List<Items> stock = new ArrayList<>();
        if (player.getLevel() < 10) {
            stock.add(new Weapon("Novice Staff", 1));
            stock.add(new Armor("Novice Robe", 1));
            stock.add(new Mana_Potion("Beginner Mana Potion", 1));
            stock.add(new Power_Potion("Beginner Power Potion", 1));
        }
        if (player.getLevel() < 20 && player.getLevel() >= 10) {
            stock.add(new Weapon("Beginner Staff", 2));
            stock.add(new Armor("Beginner Robe", 2));
            stock.add(new Mana_Potion("Beginner Mana Potion", 2));
            stock.add(new Power_Potion("Beginner Power Potion", 2));
        }
        if (player.getLevel() < 30 && player.getLevel() >= 20) {
            stock.add(new Weapon("Intermediate Staff", 3));
            stock.add(new Armor("Intermediate Robe", 3));
            stock.add(new Mana_Potion("Intermediate Mana Potion", 3));
            stock.add(new Power_Potion("Intermediate Power Potion", 3));
        }
        if (player.getLevel() < 40 && player.getLevel() >= 30) {
            stock.add(new Weapon("Advanced Staff", 4));
            stock.add(new Armor("Advanced Robe", 4));
            stock.add(new Mana_Potion("Advanced Mana Potion", 4));
            stock.add(new Power_Potion("Advanced Power Potion", 4));
        }
        if (player.getLevel() < 50 && player.getLevel() >= 40) {
            stock.add(new Weapon("Master Staff", 5));
            stock.add(new Armor("Master Robe", 5));
            stock.add(new Mana_Potion("Master Staff", 5));
            stock.add(new Power_Potion("Master Robe", 5));
        }
        return stock;
    }

    public int getPriceItem(String item) {
        double price = 0;

        Items itemObject = returnItem(item);
        if (itemObject instanceof Component) {
            price = getPriceComponent(itemObject);
        } else {
            price = getPriceNormalItem(itemObject);
        }

        switch (player.getAdventureRank()) {
            case "unranked" -> price += price * 0.1;
            case "F" -> price -= price * 0;
            case "E" -> price -= price * 0.01;
            case "D" -> price -= price * 0.05;
            case "C" -> price -= price * 0.1;
            case "B" -> price -= price * 0.15;
            case "A" -> price -= price * 0.2;
            case "S" -> price -= price * 0.3;
            case "S+" -> price -= price * 0.4;
            case "LR" -> price -= price * 0.8;
        }
        return (int) Math.round(price);
    }

    private double getPriceNormalItem(Items item) {
        double value = 0;
        switch (item.getGrade()) {
            case 0 -> value = 0;
            case 1 -> value += sr.nextInt(10) + 100;
            case 2 -> value += sr.nextInt(50) + 400;
            case 3 -> value += sr.nextInt(50) + 1600;
            case 4 -> value += sr.nextInt(50) + 6400;
            case 5 -> value += sr.nextInt(400) + 80000;
            case 6 -> value += 5000000;
        }

        if (item instanceof Mana_Potion || item instanceof Power_Potion) {
            value *= 0.75;
        }

        return value;
    }

    private double getPriceComponent(Items item) {
        double value = 0;
        switch (item.getGrade()) {
            case 0 -> value = 0;
            case 1 -> value += sr.nextInt(10) + 75;
            case 2 -> value += sr.nextInt(50) + 300;
            case 3 -> value += sr.nextInt(50) + 1000;
            case 4 -> value += sr.nextInt(50) + 5000;
            case 5 -> value += sr.nextInt(400) + 60000;
            case 6 -> value += 3000000;
        }

        return value;
    }

    public void buyItem(String item) {
        Items soldItem = null;

        int price = getPriceItem(item);

        for (Items it : merchantStock()) {

            if (it.toString().equals(item)) {
                soldItem = it;
            }

        }

        player.removeMoney(price);
        player.addItemToBag(soldItem);
    }

    public void convertStonesToMoney() {

        List<Items> stones = new ArrayList<>();
        for (Entry<Items, Integer> entry : player.getBag().entrySet()) {
            if (entry.getKey() instanceof Magic_Stone) {
                stones.add(entry.getKey());
            }
        }

        for (Items stone : stones) {
            player.removeItemFromBag(stone);
        }

        int value = 0;

        for (Items stone : stones) {
            value += (int) getPriceNormalItem(stone);
        }
        player.addMoney(value);
    }

    public void sellItem(String item) {
        Items soldItem = null;
        String transformedString = transformString(item);
        int price = (int) (getPriceItem(item) * 0.75);

        for (Items it : player.getBag().keySet()) {

            if (it.toString().equals(transformedString)) {
                soldItem = it;
            }

        }

        player.addMoney(price);
        player.removeItemFromBag(soldItem);
    }

    public void craftItem(String component, String manaStone) {
        String compTransformed = transformString(component);
        String stoneTransformed = transformString(manaStone);

        Magic_Stone stone = null;
        for (Entry<Items, Integer> entry : player.getBag().entrySet()) {
            if (entry.getKey() instanceof Magic_Stone && entry.getKey().toString().equals(stoneTransformed)) {
                stone = (Magic_Stone) entry.getKey();
            }
        }

        Component comp = null;
        for (Entry<Items, Integer> entry : player.getBag().entrySet()) {
            if (entry.getKey() instanceof Component && entry.getKey().toString().equals(compTransformed)) {
                comp = (Component) entry.getKey();
            }
        }

        Items craftedItem = craftingSystem.craftItem(comp, stone);
        if (craftedItem != null) {
            player.addItemToBag(craftedItem);
            player.removeItemFromBag(stone);
            player.removeItemFromBag(comp);
        } else {
            throw new IllegalArgumentException("Not a valid item combination!");
        }
    }

    private Items returnItem(String itemString) {
        Items item = null;
        for (Items it : player.getBag().keySet()) {
            if (it.toString().equals(transformString(itemString))) {
                item = it;
            }
        }

        if (item == null) {
            for (Items it : merchantStock()) {
                if (it.toString().equals(itemString)) item = it;
            }
        }
        Items itemExample = item;
        return item;
    }

    private String transformString(String string) {
        String transformedString = "";
        String[] array = string.split(" ");
        for (int i = 0; i < array.length - 1; i++) {

            if (i == array.length - 2) {
                transformedString += array[i];
            } else {
                transformedString += array[i] + " ";
            }
        }
        return transformedString;
    }
}
