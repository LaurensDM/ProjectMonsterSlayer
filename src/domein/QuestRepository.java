package domein;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Quest repository.
 */
public class QuestRepository {

    private List<Quest> questBord;
    private int playerLevel;

    /**
     * Instantiates a new Quest repository.
     */
    public QuestRepository(int level) {
        playerLevel = level;
        fillQuestBord();
    }

    private List<Quest> fillQuestBord() {
        List<Quest> quests = new ArrayList<>();


        return quests;
    }

    private List<Quest> stage0Quests() {
        List<Quest> quests = new ArrayList<>();
        quests.add(new Quest("Gather 10 medical flower", 10, 100, 10));
        quests.add(new Quest("Defeat 5 slimes", 50, 250, 5));
        quests.add(new Quest("Defeat 10 slimes", 100, 500, 10));
        return quests;
    }

    private List<Quest> stage1Quests() {
        List<Quest> quests = new ArrayList<>();
        quests.add(new Quest("Defeat 50 slimes", 500, 2500, 50));
        quests.add(new Quest("Defeat 10 goblins", 1000, 1000, 10));
        quests.add(new Quest("Defeat 1 HobGoblin", 2000, 2000, 1));
        return quests;
    }

    private List<Quest> stage2Quests() {
        List<Quest> quests = new ArrayList<>();

        return quests;
    }

    private List<Quest> stage3Quests() {
        List<Quest> quests = new ArrayList<>();

        return quests;
    }

    private List<Quest> stage4Quests() {
        List<Quest> quests = new ArrayList<>();

        return quests;
    }

    private List<Quest> stage5Quests() {
        List<Quest> quests = new ArrayList<>();

        return quests;
    }

}
