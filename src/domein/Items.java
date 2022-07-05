package domein;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Items {

	protected String name;
	// grade 0 = damaged, grade 1 = uncommon, grade 2 = rare, grade 3 = epic, grade
	// 4
	// = legendary, grade 5 = mythical
	protected int grade;

	public final static List<String> ITEMS = new ArrayList<>(
			Arrays.asList("Mana Potion", "Power Potion", "Armor", "Weapon"));

	public Items(String name, int grade) {
		setName(name);
		setGrade(grade);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getGrade() {
		return grade;
	}

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
