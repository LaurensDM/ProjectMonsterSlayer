package domein;

public class Quest {

	private String description;
	private double reward;
	private int progress;
	private final int MAX_PROGRESS;

	public Quest(String description, double reward, int mAX_PROGRESS) {
		this.description = description;
		this.reward = reward;
		MAX_PROGRESS = mAX_PROGRESS;
	}

	public String getDescription() {
		return description;
	}

	public double getReward() {
		return reward;
	}

	public void updateProgress() {
		progress++;
	}

	public int getProgress() {
		return progress;
	}

	public int getMAX_PROGRESS() {
		return MAX_PROGRESS;
	}

}
