package domein;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

    /**
     * Instantiates a new Game.
     *
     * @param player the player
     */
    public Game(Player player) {
        this.player = player;
        manapool = player.getLevel() * 100;
        this.maxMana = manapool;
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
            judgement = false;
        }

        if (el instanceof Wind || el instanceof True_Magic) {
            critChance++;
        }

        double damage = el.attack() * activateCrit() * powerBoost;

        if (element.equals(player.getAffinity())) {
            damage *= 2;
        }

        damage *= player.getSkills().getPower() * weaponDamage;
        double totalDamage = enemy.takeDamage(element, damage);
        manapool = el.getMana();
        if (player.getWeapon() != null) {
            player.getWeapon().lowerDurability(fullpower);
            if (player.getWeapon().getDurability() <= 0) {
                player.destroyWeapon();
            }
        }

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
        for (Items it : player.getBag()) {
            if (it.toString().equals(itemDesc)) {
                item = it;
            }
        }

        if (item != null) {
            if (item instanceof Weapon) {
                if (inGame == true) throw new IllegalArgumentException("Weapons cannot be equipped mid battle!");
                player.equipWeapon((Weapon) item);
                player.getBag().remove(item);
                return item + " equipped!";
            }

            if (item instanceof Armor) {
                if (inGame == true) throw new IllegalArgumentException("Armor cannot be equipped mid battle!");
                player.equipArmor((Armor) item);
                player.getBag().remove(item);
                return item + " equipped!";
            }
            //TODO: potion effects still to be implemented
            if (item instanceof Power_Potion) {
                if (inGame == false) throw new IllegalArgumentException("Potions shouldn't be used outside of battle!");
                activatePowerBoost(((Power_Potion) item).effect());
                player.getBag().remove(item);
                return item + " used!";
            }
            if (item instanceof Mana_Potion) {
                if (inGame == false) throw new IllegalArgumentException("Potions shouldn't be used outside of battle!");
                restoreMana(((Mana_Potion) item).effect());
                player.getBag().remove(item);
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
            maxExp -= player.getLevel() * 100;
        }
        if (player.getLevel() < 20 && player.getLevel() >= 10) {
            maxExp -= player.getLevel() * 200;
        }
        if (player.getLevel() < 30 && player.getLevel() >= 20) {
            maxExp -= player.getLevel() * 400;
        }
        if (player.getLevel() < 40 && player.getLevel() >= 30) {
            maxExp -= player.getLevel() * 800;
        }
        if (player.getLevel() < 50 && player.getLevel() >= 40) {
            maxExp -= player.getLevel() * 1600;
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
        if (manapool + maxMana * 0.001 <= maxMana) {
            manapool += maxMana * 0.001;
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
        String grade = item.split(" ")[0];
        SecureRandom sr = new SecureRandom();

        switch (grade.toLowerCase()) {
            case "damaged" -> price = 0;
            case "common" -> price = sr.nextInt(300) + 200;
            case "uncommon" -> price = sr.nextInt(300) + 700;
            case "rare" -> price = sr.nextInt(300) + 1700;
            case "epic" -> price = sr.nextInt(300) + 14700;
            case "legendary" -> price = sr.nextInt(4000) + 196000;
            case "mythical" -> price = 5000000;
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

    public void buyItem(String item) {
        Items soldItem = null;

        int price = (int) (getPriceItem(item) * 0.5);

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
        for (Items item : player.getBag()) {
            if (item instanceof Magic_Stone) {
                stones.add(item);
            }
        }

        for (Items stone : stones) {
            player.removeItemFromBag(stone);
        }

        int value = 0;

        for (Items stone : stones) {
            switch (stone.getGrade()) {
                case 0 -> value += 0;
                case 1 -> value += sr.nextInt(10) + 40;
                case 2 -> value += sr.nextInt(50) + 200;
                case 3 -> value += sr.nextInt(50) + 800;
                case 4 -> value += sr.nextInt(50) + 6400;
                case 5 -> value += sr.nextInt(400) + 80000;
                case 6 -> value += 5000000;
            }
        }
        player.addMoney(value);
    }

}
