package domein;

/**
 * The type Goblin.
 */
public class Goblin extends Enemy {

    /**
     * Instantiates a new Goblin.
     */
    public Goblin() {
        super(sr.nextInt(301) + 300, sr.nextDouble(0.10), "");
        if (sr.nextInt(3) == 1) {
            evolve();
        }
    }

    public Goblin(String name, boolean evolved) {
        this();
        super.evolved = evolved;
        if (evolved) evolve();
        applyNamedPower();
        setName(name);
    }

    @Override
    public double attackBack() {
        if (isFrozen()) {
            super.breakFrozen();
            return 0;
        }
        int power = sr.nextInt(26) + 50;
        if (super.evolved == true) {
            power = sr.nextInt(5) + 120;
        }
        if (named) power *= 1.5;
        return power;
    }

    @Override
    public void evolve() {
        super.setHealth(600);
        super.setDefence(0.10);
        super.setType("Hob");
        super.evolved = true;
    }

    /**
     *
     */
    @Override
    public void applyNamedPower() {
        setHealth(getHealth() * 1.5);
        setDefence(getDefence() * 1.25);
        super.named = true;
    }

    @Override
    public int determineItemGrade(boolean fullpower) {
        int grade = 1;

        if (getType().equals("Hob")) {
            grade = sr.nextInt(2) + 1;
        }
        if (fullpower) {
            grade = 0;
        }
        return grade;
    }

    /**
     * @return
     */
    @Override
    protected Component determineComponent() {
        String itemClass = Items.ITEMS.get(sr.nextInt(Items.ITEMS.size()));

        int grade;
        String evolvedType;
        if (evolved) {
            evolvedType = "Hob ";
            grade = 2;
        } else {
            evolvedType = "";
            grade = 1;
        }

        Component component = null;

        switch (itemClass) {
            case "Mana Potion" -> component = new Component(evolvedType + " Goblin Essence", grade, 0);
            case "Power Potion" -> component = new Component(evolvedType + " Goblin Blood", grade, 1);
            case "Weapon" -> component = new Component(evolvedType + " Wooden Staff", grade, 2);
            case "Armor" -> component = new Component(evolvedType + " Cloth Armor", grade, 3);
        }

        return component;
    }

    @Override
    public int dropExp() {
        int powerLevel = (int) (MAX_HEALTH + MAX_HEALTH * MAX_DEFENCE);
        return (int) (powerLevel * 0.5);
    }

}
