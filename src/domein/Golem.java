package domein;

public class Golem extends Enemy {

    public Golem() {
        super(sr.nextInt(5000) + 5000, sr.nextDouble(0.25) + 0.25, Elements.ELEMENTS.get(sr.nextInt(Elements.ELEMENTS.size())));

        if (sr.nextInt(20) == 0) {
            evolve();
        }
    }

    public Golem(String name, boolean evolved) {
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
        int power = sr.nextInt(100) + 125;
        if (super.evolved == true) {
            power = sr.nextInt(5) + 225;
        }
        if (named) power *= 1.5;
        return power;
    }

    @Override
    public void evolve() {
        setHealth(10000);
        setDefence(0.5);
        setType("Ancient");
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
    protected int determineItemGrade(boolean fullpower) {
        if (fullpower) {
            if (sr.nextInt(10) == 0) return 0;
        }
        if (getType().equals("Ancient")) return 4;
        return sr.nextInt(2) + 2;
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
            evolvedType = "Ancient ";
            grade = 5;
        } else {
            evolvedType = "";
            grade = 4;
        }

        Component component = null;

        switch (itemClass) {
            case "Mana Potion" -> component = new Component(evolvedType + "Golem Essence", grade, 0);
            case "Power Potion" -> component = new Component(evolvedType + "Golem Energy", grade, 1);
            case "Weapon" -> component = new Component(evolvedType + "Golem Conduit", grade, 2);
            case "Armor" -> component = new Component(evolvedType + "Golem Ore", grade, 3);
        }

        return component;
    }

    /**
     * @return
     */
    @Override
    public int dropExp() {
        int powerLevel = (int) (MAX_HEALTH + MAX_HEALTH * MAX_DEFENCE);
        return (int) (powerLevel * 0.5);
    }
}
