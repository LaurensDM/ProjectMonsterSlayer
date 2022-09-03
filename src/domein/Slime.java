package domein;

public class Slime extends Enemy {

    public Slime() {
        super(sr.nextInt(50) + 50, 0, "");
    }

    @Override
    public double attackBack() {
        if (isFrozen()) {
            super.breakFrozen();
            return 0;
        }
        int power = 10;

        return power;
    }

    @Override
    public void evolve() {
    }

    /**
     *
     */
    @Override
    public void applyNamedPower() {

    }

    @Override
    protected int determineItemGrade(boolean fullpower) {
        if (fullpower) {
            return 0;
        }
        return 1;
    }

    /**
     * @return
     */
    @Override
    protected Component determineComponent() {
        return null;
    }

    /**
     * @return exp
     */
    @Override
    public int dropExp() {
        int value = (int) (MAX_HEALTH * 0.5);
        return value;
    }
}
