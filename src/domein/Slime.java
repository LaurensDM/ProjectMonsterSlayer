package domein;

public class Slime extends Enemy {

    public Slime() {
        super(sr.nextInt(50) + 50, 0, "");
    }

    @Override
    public double attackBack() {
        return 0.05 * getHealth();
    }

    @Override
    public void evolve() {
    }

    @Override
    protected int determineItemGrade(boolean fullpower) {
        if (fullpower) {
            return 0;
        }
        return 1;
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
