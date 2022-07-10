package domein;

/**
 * The type Skills.
 */
public class Skills {

	// full power has 4 stages, stage 0 disables full power mode, stage 1 enables
	// full power, stage 2, stage 3 decreases chance to fail
	private int fullPowerStage;
	private double efficiency;
	private double power;
	private boolean reflection;
	private boolean trueMagic;
	private boolean judgement;

	/**
	 * Instantiates a new Skills.
	 *
	 * @param fullPowerStage the full power stage
	 * @param efficiency     the efficiency
	 * @param power          the power
	 * @param reflection     the reflection
	 * @param trueMagic      the true magic
	 * @param judgement      the judgement
	 */
	public Skills(int fullPowerStage, double efficiency, double power, boolean reflection, boolean trueMagic,
			boolean judgement) {
		this.fullPowerStage = fullPowerStage;
		this.efficiency = efficiency;
		this.power = power;
		this.reflection = reflection;
		this.trueMagic = trueMagic;
		this.judgement=judgement;
	}

	/**
	 * Instantiates a new Skills.
	 */
	public Skills() {
		this(0, 1, 1, false, false, false);
	}

	/**
	 * Activate skills.
	 *
	 * @param skill the skill
	 */
	public void activateSkills(int skill) {
		switch (skill) {
		case 1:
			efficiency = 0.8;
			break;
		case 5:
			power = 1.125;
			break;
		case 10:
			fullPowerStage = 1;
			break;
		case 15:
			efficiency = 0.4;
			break;
		case 20:
			power = 1.5;
			break;
		case 25:
			fullPowerStage = 2;
			break;
		case 30:
			reflection = true;
			break;
		case 35:
			efficiency = 0.2;
			break;
		case 40:
			power = 2;
			break;
		case 45:
			fullPowerStage = 3;
			break;
		case 50:
			trueMagic = true;
			break;
		case 100:
			power = 20;
			efficiency = 0.002;
			judgement = true;
			break;
		}
	}

	/**
	 * Gets full power stage.
	 *
	 * @return the full power stage
	 */
	public int getFullPowerStage() {
		return fullPowerStage;
	}

	/**
	 * Get efficiency.
	 *
	 * @return the efficiency
	 */
	public double getEfficiency() {
		return efficiency;
	}

	/**
	 * Gets power.
	 *
	 * @return the power
	 */
	public double getPower() {
		return power;
	}

	/**
	 * Is reflection boolean.
	 *
	 * @return the boolean
	 */
	public boolean isReflection() {
		return reflection;
	}

	/**
	 * Is true magic boolean.
	 *
	 * @return the boolean
	 */
	public boolean isTrueMagic() {
		return trueMagic;
	}

	/**
	 * Is judgement boolean.
	 *
	 * @return the boolean
	 */
	public boolean isJudgement() {
		return judgement;
	}

}
