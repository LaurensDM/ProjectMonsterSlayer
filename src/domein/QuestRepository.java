package domein;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Quest repository.
 */
public class QuestRepository {
	
	private List<Quest> questBord;

	/**
	 * Instantiates a new Quest repository.
	 */
	public QuestRepository() {
		
	}
	
	private List <Quest> fillQuestBord(){
		List<Quest> quests = new ArrayList<>();
		
		quests.add(new Quest("Defeat 10 goblins", 100, 10));
		
		return quests;
	}

}
