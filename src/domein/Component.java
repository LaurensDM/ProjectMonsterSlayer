package domein;

public class Component extends Items {
    //Represents which item can be created with this component, mana potion is 0, power potion is 1, weapon is 2, armor is 3
    private int itemClass;

    /**
     * Instantiates a new Component.
     *
     * @param name the name
     */
    public Component(String name, int grade, int itemClass) {
        super(name, grade);
        this.itemClass = itemClass;
    }

    public int getItemClass() {
        return itemClass;
    }

    @Override
    public String toString() {
        return name;
    }
}
