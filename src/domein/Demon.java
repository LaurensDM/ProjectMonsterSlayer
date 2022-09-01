package domein;

public class Demon extends Enemy {
    /**
     * Instantiates a new Enemy.
     */
    public Demon() {
        super(sr.nextInt(2000) + 2000, sr.nextDouble(0.15) + 0.15, "Hell");
        if (sr.nextInt(4) == 0) {
            evolve();
        }
    }

    public Demon(String name, boolean evolved) {
        this();
        super.evolved = evolved;
        if (evolved) evolve();
        applyNamedPower();
        setName(name);
    }

    /**
     * @return
     */
    @Override
    public double attackBack() {
        if (isFrozen()) {
            super.breakFrozen();
            return 0;
        }
        int power = sr.nextInt(75) + 50;
        if (super.evolved == true) {
            power = sr.nextInt(5) + 125;
        }
        if (super.named == true) {
            power *= 1.5;
        }
        return power;
    }

    /**
     *
     */
    @Override
    public void evolve() {
        super.setHealth(5000);
        super.setDefence(0.3);
        super.setType("Arch");
        super.evolved = true;
    }

    /**
     *
     */
    @Override
    public void applyNamedPower() {
        super.setHealth(getHealth() * 1.5);
        super.setDefence(getDefence() * 1.25);
        super.named = true;
    }

    /**
     * @param fullpower whether full power is activated
     * @return
     */
    @Override
    protected int determineItemGrade(boolean fullpower) {
        int grade;
        if (evolved) {
            grade = 3;
        } else
            grade = sr.nextInt(2) + 2;

        if (fullpower) {
            if (sr.nextInt(3) == 0) {
                grade = 0;
            }
        }
        return grade;
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
