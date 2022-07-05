package domein;

public class Skills {

	// full power has 4 stages, stage 0 disables full power mode, stage 1 enables
	// full power, stage 2, stage 3 decreases chance to fail
	private int fullPowerStage;
	private double efficiëncy;
	private double power;
	private boolean reflection;
	private boolean trueMagic;
	private boolean judgement;

	public Skills(int fullPowerStage, double efficiëncy, double power, boolean reflection, boolean trueMagic,
			boolean judgement) {
		this.fullPowerStage = fullPowerStage;
		this.efficiëncy = efficiëncy;
		this.power = power;
		this.reflection = reflection;
		this.trueMagic = trueMagic;
		this.judgement=judgement;
	}

	public Skills() {
		this(0, 1, 1, false, false, false);
	}

	public void activateSkills(int skill) {
		switch (skill) {
		case 1:
			efficiëncy = 0.8;
			break;
		case 5:
			power = 1.125;
			break;
		case 10:
			fullPowerStage = 1;
			break;
		case 15:
			efficiëncy = 0.4;
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
			efficiëncy = 0.2;
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
			efficiëncy = 0.002;
			judgement = true;
			break;
		}
	}

	public int getFullPowerStage() {
		return fullPowerStage;
	}

	public double getEfficiëncy() {
		return efficiëncy;
	}

	public double getPower() {
		return power;
	}

	public boolean isReflection() {
		return reflection;
	}

	public boolean isTrueMagic() {
		return trueMagic;
	}

	public boolean isJudgement() {
		return judgement;
	}

}
