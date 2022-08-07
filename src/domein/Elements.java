package domein;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The type Elements.
 */
public abstract class Elements {
	private static SecureRandom sr = new SecureRandom();

	/**
	 * The Manapool.
	 */
	protected double manapool;
	private double totalDamage = 0;
	private boolean allOut = false;
	private boolean judgement = false;
	private Skills playerSkills;
	private double maxMana;
	/**
	 * The constant ELEMENTS.
	 */
	public final static List<String> ELEMENTS = new ArrayList<>(
			Arrays.asList("Fire", "Water", "Lightning", "Earth", "Wind"));

	/**
	 * Instantiates a new Elements.
	 *
	 * @param manapool     the manapool
	 * @param playerSkills the player skills
	 * @param maxMana      the max mana
	 */
	public Elements(double manapool, Skills playerSkills, double maxMana) {
		this.manapool = manapool;
		this.playerSkills = playerSkills;
		this.maxMana = maxMana;
	}

	/**
	 * Attack
	 *
	 * @param value additional damage boost
	 * @return damage
	 */
	public double attack(double value) {
		if (allOut) {
			double damage = manapool * attackFails() * value;
			useAttack(manapool);
			return damage;
		} else {
			if (judgement) {
                if (manapool - maxMana * 0.25 >= 0) {
                    double damage = maxMana * 0.25 * attackFails() * value;
                    useAttack(maxMana * 0.25);
                    return damage;
                }
                judgement = false;
            }
			double power = power();
			useAttack(power);
			return power * attackFails() * value;
		}
	}


	/**
	 * calculates the power of the normal attack
	 *
	 * @return attack power
	 */
	public int power() {
		return (int) (sr.nextInt((int) Math.round(maxMana*0.05)) + maxMana*0.05);
	}

	/**
	 * Use attack.
	 *
	 * @param power attack power
	 */
	protected void useAttack(double power) {
		double attackPower = power;
		if (manapool - power <= 0) {
            attackPower = manapool;
            manapool = 0;
		}

		if (judgement) {
            manapool -= maxMana * 0.25;
            judgement = false;
			return;
		}

        if (allOut) {
			manapool = 0;
			allOut = false;
			return;
		}
		changeMana(attackPower * playerSkills.getEfficiency());
	}

	private int attackFails() {
		int chance;
		if (allOut) {
			if (playerSkills.getFullPowerStage() == 3) {
				chance = sr.nextInt(50);
			} else {
				chance = sr.nextInt(5);
			}
		} else {
			chance = sr.nextInt(100);
		}
		if (chance == 0) {
			return 0;
		}
		return 1;
	}

	/**
	 * Activate shield.
	 *
	 * @param damage the damage
	 */
	public void activateShield(double damage) {
		int chance = sr.nextInt(10);
		if (playerSkills.isReflection()) {
			chance = sr.nextInt(4);
			if (chance == 0) {
				changeMana(0);
				return;
			}

		}
		if (chance == 1) {
			changeMana(damage / 1.5);
		} else {
			changeMana(damage);
		}

	}

	/**
	 * Go all out.
	 */
	public void goAllOut() {
		allOut = true;
	}

	/**
	 * Deliver judgement.
	 */
	public void deliverJudgement() {
		judgement = true;
	}

	/**
	 * Is all out boolean.
	 *
	 * @return the boolean
	 */
	public boolean isAllOut() {
		return allOut;
	}

	private void changeMana(double value) {
		if (manapool - value > 0) {
			manapool -= value;
		} else {
			manapool = 0;
		}
	}

	/**
	 * Gets total damage.
	 *
	 * @return the total damage
	 */
	public double getTotalDamage() {
		return totalDamage;
	}

	/**
	 * Gets mana.
	 *
	 * @return the manapool
	 */
	public double getMana() {
		return manapool;
	}

	public String toString() {
		return String.format("Your element is %s", this.getClass().getSimpleName());
	}

	/**
	 * Attack.
	 *
	 * @return damage
	 */
	protected abstract double attack();

}
