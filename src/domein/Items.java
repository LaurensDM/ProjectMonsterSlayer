package domein;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The type Items.
 */
public class Items {

	/**
	 * The Name.
	 */
	protected String name;
	/**
	 * The Grade.
	 */
// grade 0 = damaged, grade 1 = uncommon, grade 2 = rare, grade 3 = epic, grade
	// 4
	// = legendary, grade 5 = mythical
	protected int grade;

	/**
	 * The constant ITEMS.
	 */
	public final static List<String> ITEMS = new ArrayList<>(
			Arrays.asList("Mana Potion", "Power Potion", "Armor", "Weapon"));

	/**
	 * Instantiates a new Items.
	 *
	 * @param name  the name
	 * @param grade the grade
	 */
	public Items(String name, int grade) {
		setName(name);
		setGrade(grade);
	}

	/**
	 * Gets name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets name.
	 *
	 * @param name the name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets grade.
	 *
	 * @return the grade
	 */
	public int getGrade() {
		return grade;
	}

	/**
	 * Sets grade.
	 *
	 * @param grade the grade
	 */
	public void setGrade(int grade) {
		if (grade > 6 || grade < 0) {
			throw new IllegalArgumentException("Invalid item grade");
		}
		this.grade = grade;
	}

	public String toString() {
		return String.format("%s %s", grade == 0 ? "Damaged"
				: grade == 1 ? "Uncommon"
						: grade == 2 ? "Rare"
								: grade == 3 ? "Epic" : grade == 4 ? "Legendary" : grade == 5 ? "Mythical" : "Unknown",
				name);
	}
}
