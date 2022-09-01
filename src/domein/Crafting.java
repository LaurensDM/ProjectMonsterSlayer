package domein;

public class Crafting {

    public Items craftItem(Component component1, Magic_Stone component2) {
        if (component1.getItemClass() == 0)
            return craftManaPotion(component1, component2);
        if (component1.getItemClass() == 1)
            return craftPowerPotion(component1, component2);
        if (component1.getItemClass() == 2)
            return craftWeapon(component1, component2);
        if (component1.getItemClass() == 3)
            return craftArmor(component1, component2);
        return null;
    }

    private Items craftWeapon(Component component1, Magic_Stone component2) {
        int componentGrade = 0;
        String key = component1.toString();

        if (key.contains("Goblin")) componentGrade = 1;
        if (key.contains("Hob") || key.contains("Troll")) componentGrade = 2;
        if (key.contains("Rock") || key.contains("Demon")) componentGrade = 3;
        if (key.contains("Arch") || key.contains("Golem")) componentGrade = 4;
        if (key.contains("Ancient") || key.contains("Dragon")) componentGrade = 5;

        int totalGrade = componentGrade + component2.getGrade();
        Items weapon = null;
        if (totalGrade <= 2) weapon = new Weapon("Novice Staff", 1);
        if (totalGrade > 2 && totalGrade <= 4) weapon = new Weapon("Beginner Staff", 2);
        if (totalGrade > 4 && totalGrade <= 6) weapon = new Weapon("Intermediate Staff", 3);
        if (totalGrade > 6 && totalGrade <= 8) weapon = new Weapon("Advanced Staff", 4);
        if (totalGrade > 8 && totalGrade < 12) weapon = new Weapon("Staff of power", 5);
        if (totalGrade >= 12) weapon = new Weapon("Staff of the Archmagi", 6);

        return weapon;
    }

    private Items craftPowerPotion(Component component1, Magic_Stone component2) {
        int componentGrade = 0;
        String key = component1.toString();

        if (key.contains("Goblin")) componentGrade = 1;
        if (key.contains("Hob") || key.contains("Troll")) componentGrade = 2;
        if (key.contains("Rock") || key.contains("Demon")) componentGrade = 3;
        if (key.contains("Arch") || key.contains("Golem")) componentGrade = 4;
        if (key.contains("Ancient") || key.contains("Dragon")) componentGrade = 5;

        int totalGrade = componentGrade + component2.getGrade();
        Items powerPotion = null;
        if (totalGrade <= 2) powerPotion = new Power_Potion("Novice Power Potion", 1);
        if (totalGrade > 2 && totalGrade <= 4) powerPotion = new Power_Potion("Beginner Power Potion", 2);
        if (totalGrade > 4 && totalGrade <= 6) powerPotion = new Power_Potion("Intermediate Power Potion", 3);
        if (totalGrade > 6 && totalGrade <= 8) powerPotion = new Power_Potion("Advanced Power Potion", 4);
        if (totalGrade > 8 && totalGrade < 12) powerPotion = new Power_Potion("Greater Power Potion", 5);
        if (totalGrade >= 12) powerPotion = new Power_Potion("Ancient Power Potion", 6);

        return powerPotion;
    }

    private Items craftManaPotion(Component component1, Magic_Stone component2) {
        int componentGrade = 0;
        String key = component1.toString();

        if (key.contains("Goblin")) componentGrade = 1;
        if (key.contains("Hob") || key.contains("Troll")) componentGrade = 2;
        if (key.contains("Rock") || key.contains("Demon")) componentGrade = 3;
        if (key.contains("Arch") || key.contains("Golem")) componentGrade = 4;
        if (key.contains("Ancient") || key.contains("Dragon")) componentGrade = 5;

        int totalGrade = componentGrade + component2.getGrade();
        Items manaPotion = null;
        if (totalGrade <= 2) manaPotion = new Mana_Potion("Novice Mana Potion", 1);
        if (totalGrade > 2 && totalGrade <= 4) manaPotion = new Mana_Potion("Beginner Mana_Potion", 2);
        if (totalGrade > 4 && totalGrade <= 6) manaPotion = new Mana_Potion("Intermediate Mana_Potion", 3);
        if (totalGrade > 6 && totalGrade <= 8) manaPotion = new Mana_Potion("Advanced Mana_Potion", 4);
        if (totalGrade > 8 && totalGrade < 12) manaPotion = new Mana_Potion("Greater Mana_Potion", 5);
        if (totalGrade >= 12) manaPotion = new Mana_Potion("Arcane Mana_Potion", 6);

        return manaPotion;
    }

    private Items craftArmor(Component component1, Magic_Stone component2) {
        int componentGrade = 0;
        String key = component1.toString();

        if (key.contains("Goblin")) componentGrade = 1;
        if (key.contains("Hob") || key.contains("Troll")) componentGrade = 2;
        if (key.contains("Rock") || key.contains("Demon")) componentGrade = 3;
        if (key.contains("Arch") || key.contains("Golem")) componentGrade = 4;
        if (key.contains("Ancient") || key.contains("Dragon")) componentGrade = 5;

        int totalGrade = componentGrade + component2.getGrade();
        Items armor = null;
        if (totalGrade <= 2) armor = new Mana_Potion("Novice Armor", 1);
        if (totalGrade > 2 && totalGrade <= 4) armor = new Mana_Potion("Beginner Armor", 2);
        if (totalGrade > 4 && totalGrade <= 6) armor = new Mana_Potion("Intermediate Armor", 3);
        if (totalGrade > 6 && totalGrade <= 8) armor = new Mana_Potion("Advanced Armor", 4);
        if (totalGrade > 8 && totalGrade < 12) armor = new Mana_Potion("Robe of Life", 5);
        if (totalGrade >= 12) armor = new Mana_Potion("Robe of the Archmagi", 6);

        return armor;
    }

}
