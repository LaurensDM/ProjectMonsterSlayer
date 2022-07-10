package domein;

/**
 * The type Quest.
 */
public class Quest {

	private String description;
	private double reward;
	private int progress;
	private final int MAX_PROGRESS;

	/**
	 * Instantiates a new Quest.
	 *
	 * @param description  the description
	 * @param reward       the reward
	 * @param maxProgress the max progress
	 */
	public Quest(String description, double reward, int maxProgress) {
		this.description = description;
		this.reward = reward;
		MAX_PROGRESS = maxProgress;
	}

	/**
	 * Gets description.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Gets reward.
	 *
	 * @return the reward
	 */
	public double getReward() {
		return reward;
	}

	/**
	 * Update progress.
	 */
	public void updateProgress() {
		progress++;
	}

	/**
	 * Gets progress.
	 *
	 * @return the progress
	 */
	public int getProgress() {
		return progress;
	}

	/**
	 * Gets max progress.
	 *
	 * @return the max progress
	 */
	public int getMAX_PROGRESS() {
		return MAX_PROGRESS;
	}

}
