package domein;

import java.security.SecureRandom;
import java.util.Collections;

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
    private final double MAX_MANA;

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
            judgement = false;
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
                return item + " equipped!";
            }

            if (item instanceof Armor) {
                if (inGame == true) throw new IllegalArgumentException("Armor cannot be equipped mid battle!");
                player.equipArmor((Armor) item);
                return item + " equipped!";
            }
            //TODO: potion effects still to be implemented
            if (item instanceof Power_Potion) {
                if (inGame == false) throw new IllegalArgumentException("Potions shouldn't be used outside of battle!");
                return item + " used!";
            }
            if (item instanceof Mana_Potion) {
                if (inGame == false) throw new IllegalArgumentException("Potions shouldn't be used outside of battle!");
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
        player.levelUp();
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

    public double getMAX_MANA() {
        return MAX_MANA;
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

    public void restoreMana(double restoration) {
        manapool += MAX_MANA * restoration;
    }

    public void regenerateMana() {
        if (manapool < MAX_MANA) {
            manapool += MAX_MANA * 0.001;
        }
        if (manapool + MAX_MANA * 0.001 > getMAX_MANA() && manapool < getMAX_MANA()) {
            manapool = getMAX_MANA();
        }
    }

}
