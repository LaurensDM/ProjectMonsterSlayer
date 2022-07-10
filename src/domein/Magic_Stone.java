package domein;

/**
 * The type Magic stone.
 */
public class Magic_Stone extends Items {

	/**
	 * Instantiates a new Magic stone.
	 *
	 * @param name  the name
	 * @param grade the grade
	 */
	public Magic_Stone(String name, int grade) {
		super(name, grade);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Gets value.
	 *
	 * @return the value
	 */
	public double getValue() {
		int totalValue = 0;
		int monsterValue = 0;

		if (name.toLowerCase().contains("goblin")) {
			monsterValue = 10;
		}
		if (name.toLowerCase().contains("troll")) {
			monsterValue = 25;
		}
		if (name.toLowerCase().contains("golem")) {
			monsterValue = 50;
		}
		if (name.toLowerCase().contains("dragon")) {
			monsterValue = 100;
		}

		switch (grade) {
		case 0:
			totalValue = (int) Math.round(monsterValue * 0.75);
			break;

		case 1:
			totalValue = (int) Math.round(monsterValue * 2);
			break;

		case 2:
			totalValue = (int) Math.round(monsterValue * 5);
			break;

		case 3:
			totalValue = (int) Math.round(monsterValue * 10);
			break;

		case 4:
			totalValue = (int) Math.round(monsterValue * 100);
			break;
		case 5:
			totalValue = (int) Math.round(monsterValue * 1000);
			break;

		}
		return totalValue;
	}
}
