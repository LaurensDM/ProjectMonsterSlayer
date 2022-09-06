package domein;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.List;

/**
 * The type Enemy.
 */
public abstract class Enemy {

    /**
     * The Enemies.
     */
    public final static List<String> ENEMIES = Arrays.asList("Dragon", "Troll", "Goblin");
    public final static List<String> STAGE_0 = Arrays.asList("Slime");
    public final static List<String> STAGE_1 = Arrays.asList("Slime", "Goblin");
    public final static List<String> STAGE_2 = Arrays.asList("Goblin", "Troll");
    public final static List<String> STAGE_3 = Arrays.asList("Troll", "Demon");
    public final static List<String> STAGE_4 = Arrays.asList("Demon", "Golem");
    public final static List<String> STAGE_5 = Arrays.asList("Golem", "Dragon");
    /**
     * The constant sr.
     */
    protected static SecureRandom sr = new SecureRandom();
    public final double MAX_HEALTH;
    public final double MAX_DEFENCE;
    /**
     * The Evolved.
     */
    protected boolean evolved = false;
    protected boolean named = false;
    private double health;
    private double defence;
    private String type;
    private double tickDamage = 0;
    private int freeze = 0;
    private boolean frozen = false;
    private String name;

    /**
     * Instantiates a new Enemy.
     *
     * @param health  the health
     * @param defence the defence
     * @param type    the type
     */
    public Enemy(double health, double defence, String type) {
        setHealth(health);
        setDefence(defence);
        setType(type);
        MAX_HEALTH = health;
        MAX_DEFENCE = defence;
    }

    /**
     * Is strong against boolean.
     *
     * @param damageType the damage element
     * @return the boolean
     */
    public boolean isStrongAgainst(String damageType) {
        if ((type.equals("Fire") || type.equals("Hell")) && damageType.equals("Wind")) {
            return true;
        }
        if (type.equals("Wind") && damageType.equals("Earth")) {
            return true;
        }
        if (type.equals("Earth") && damageType.equals("Lightning")) {
            return true;
        }
        if (type.equals("Lightning") && damageType.equals("Water")) {
            return true;
        }
        if (type.equals("Water") && damageType.equals("Fire")) {
            return true;
        }
        return false;
    }

    /**
     * Is weakness boolean.
     *
     * @param damageType the damage element
     * @return the boolean
     */
    public boolean isWeakness(String damageType) {

        if ((type.equals("Fire") || type.equals("Hell") || type.equals("Rock")) && damageType.equals("Water")) {
            return true;
        }
        if (type.equals("Wind") && damageType.equals("Fire")) {
            return true;
        }
        if (type.equals("Earth") && damageType.equals("Wind")) {
            return true;
        }
        if (type.equals("Lightning") && damageType.equals("Earth")) {
            return true;
        }
        if (type.equals("Water") && damageType.equals("Lightning")) {
            return true;
        }
        if (damageType.equals("True Magic")) {
            return true;
        }

        return false;
    }

    /**
     * Gets type.
     *
     * @return the type (element)
     */
    public String getType() {
        return type;
    }

    /**
     * Sets type.
     *
     * @param type the type (element)
     */
    protected void setType(String type) {
        this.type = type;
    }

    /**
     * Gets health.
     *
     * @return the health
     */
    public double getHealth() {
        return health;
    }

    /**
     * Sets health.
     *
     * @param health the health
     */
    protected void setHealth(double health) {

        this.health = health;
    }

    protected void setName(String name) {
        this.name = name;
    }

    /**
     * Register damage.
     *
     * @param totalDamage the total damage
     */
    protected void registerDamage(double totalDamage) {
        health -= totalDamage;
    }

    /**
     * Attack back.
     *
     * @return the damage
     */
    public abstract double attackBack();

    /**
     * Evolve.
     */
    public abstract void evolve();

    public abstract void applyNamedPower();

    /**
     * Determine item grade int.
     *
     * @param fullpower whether full power is activated
     * @return the item grade
     */
    protected abstract int determineItemGrade(boolean fullpower);

    protected abstract Component determineComponent();

    /**
     * Drop items.
     *
     * @param fullpower whether full power was activated
     * @return the items
     */
    public Items[] dropItem(boolean fullpower) {
        Items[] drops;
        if (getClass().getSimpleName().equals("Slime")) {
            drops = new Items[1];
        } else {
            drops = new Items[sr.nextInt(4) + 1];
        }

        int grade;
        String name;
        String item;

        grade = determineItemGrade(fullpower);
        drops[0] = new Magic_Stone(getType() + " " + this.getClass().getSimpleName() + " Stone", grade);

        for (int i = 1; i < drops.length; i++) {
            drops[i] = determineComponent();
        }



        return drops;
    }

    public abstract int dropExp();

    /**
     * Lower defence.
     *
     * @param shred by how much the defense is lowered, usually by 5%
     */
    protected void lowerDefence(double shred) {
        if (defence - shred >= 0) {
            defence -= shred;
            return;
        }
        defence = 0;
    }

    /**
     * Gets defence.
     *
     * @return the defence
     */
    public double getDefence() {
        return defence;
    }

    /**
     * Sets defence.
     *
     * @param defence the defence
     */
    protected void setDefence(double defence) {
        this.defence = defence;
    }

    /**
     * Gets tick damage.
     *
     * @return the tick damage
     */
    public double getTickDamage() {
        return tickDamage;
    }

    /**
     * Take damage double.
     *
     * @param damageType the damage type
     * @param damage     the damage
     * @return how much damage was taken
     */
    public double takeDamage(String damageType, double damage, boolean judgement) {
        double totalDamage = damage;

        if (judgement) {
            lowerDefence(1);
        }

        if (damageType.equals(type)) {
            registerDamage(damage * 0.5);
            return damage * 0.5;
        }
        if (damageType.equals("Water") && !type.equals("Water")) {
            freeze++;
            if (freeze == 3) {
                freeze = 0;
                frozen = true;
            }
        }

        if (damageType.equals("Earth") && !type.equals("Earth")) {
            lowerDefence(0.05);
        }

        if (damageType.equals("True Magic")) {
            totalDamage *= 2;
            lowerDefence(0.05);
        }


        if (isStrongAgainst(damageType)) {
            totalDamage *= 0.75;
        }
        if (isWeakness(damageType)) {
            totalDamage *= 1.5;
        }
        totalDamage += tickDamage;
        totalDamage -= damage * getDefence();

        registerDamage(totalDamage);

        if (damageType.equals("Lightning") && !type.equals("Lightning")) {
            tickDamage = damage * 0.25;

        }


        return totalDamage;
    }

    public String toString() {

        if (named) {
            return name;
        }
        return String.format("%s %s", type, this.getClass().getSimpleName());

    }

    /**
     * Is frozen boolean.
     *
     * @return the boolean
     */
    public boolean isFrozen() {
        return frozen;
    }

    /**
     * Break frozen.
     */
    public void breakFrozen() {
        frozen = false;
    }

}
