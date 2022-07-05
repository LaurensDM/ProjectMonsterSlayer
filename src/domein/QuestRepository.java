package domein;

import java.util.ArrayList;
import java.util.List;

public class QuestRepository {
	
	private List<Quest> questBord;

	public QuestRepository() {
		
	}
	
	private List <Quest> fillQuestBord(){
		List<Quest> quests = new ArrayList<>();
		
		quests.add(new Quest("Defeat 10 goblins", 100, 10));
		
		return quests;
	}

}
