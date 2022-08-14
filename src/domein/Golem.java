package domein;

public class Golem extends Enemy{

    public Golem() {
        super(sr.nextInt(5000)+5000, sr.nextDouble(0.25)+0.25, Elements.ELEMENTS.get(sr.nextInt(Elements.ELEMENTS.size())));

        if (sr.nextInt(20) == 0) {
            evolve();
        }
    }

    @Override
    public double attackBack() {
        return 0;
    }

    @Override
    public void evolve() {
    setHealth(10000);
    setDefence(0.5);
    setType("Ancient");
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
    public int dropExp() {
        int powerLevel = (int) (MAX_HEALTH + MAX_HEALTH * MAX_DEFENCE);
        return (int) (powerLevel * 0.5);
    }
}
