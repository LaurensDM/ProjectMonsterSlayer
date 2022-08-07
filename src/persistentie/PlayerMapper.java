package persistentie;

import domein.*;
import resources.HashClass;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Player mapper.
 */
public class PlayerMapper {

    /*public PlayerMapper() {
        players = new ArrayList<>();
        List<Items> items = new ArrayList<>();
        Collections.addAll(items, new Mana_Potion("Arcane Potion", 6), new Power_Potion("Ancient Power Potion", 6));
        players.add(new Player("Talos", "80209f3c062a027724686f9e6638e23ea001bcce41af3754b104059fd567f8dc560acd37a1f0379794edaf25ce5191aaa452370d7387bb2bbbe015a1ed1c257a", "qL6vVvRyV0K7w6YWwnb3ZA", "True Magic", 100, 0, 5000000, items,
                new Skills(3, 0.002, 20, true, true, true), new Weapon("Staff of the Archmagi", 6),
                new Armor("Robe of the Archmagi", 6), "LR"));
        players.add(new Player("Guy1", "b38ae7bcaf61a7b3533b5b6a79b6eb06a2cb16476e2917f06ffd3c3291e9e6d20a158c035f37c6a6f443154871f979848f5598fd204685baa14cf327661a7b8f", "tJtHpgMn4YOVHLxe8bWd2A", "Fire", 50, 0, 5000, new ArrayList<>(),
                new Skills(3, 0.8, 2, true, true, false), null, null, "S"));
        players.add(new Player("Guy2", "dcc8406b481b68730456174c7355a2b555f85f547c6d2e2cd260bea9c30489a0146ef5d2a465a8c02026ad678d21655edf53f832f070dd0a5bcf8651865b37e1", "zsjwz90zIED4Rz1E5T/8UA", "Water", 40, 400, 4000, new ArrayList<>(),
                new Skills(2, 0.2, 1.5, true, true, false), null, null, "A"));
        players.add(new Player("Guy3", "f0b049ebb40e9f4deea51abb0f2c56541bf4167bfa5e8aff13c83a750364288a6ee0012a0dc806f889a3aeca8df7d43f02fe7b96bd2a50beb97fa2876acaf354", "cFLpCX+HJL0+H2xotF14jw", "Lightning", 30, 300, 3000, new ArrayList<>(), new Skills(),
                null, null, "C"));
        players.add(new Player("Guy4", "f8c9227a875ab0843f3e166b60a80ac689e8a740cd19d4de9340dc07387eb87f147359c971036edd50a919fe6c360232aafea511d9d9832f3efc1f6fbc3c5aa5", "yAq3DrWcB18FRAXckTLc9w", "Wind"));
        players.add(new Player("Guy5", "18c7b08e02d18f2edc41f8bb86de0f539498f241460565072978da8ec954cca96539bcc2c60b56bd7d4fe5c19d5bbd5f1f9aa8b6324115b3ba321552a059bdd4", "njZi2hpw4PMMpgWPJ9SwLg", "Earth"));
    }*/

    /**
     * Save player.
     *
     * @param player the player
     */
    public void savePlayer(Player player) {
        try (Connection con = DriverManager.getConnection(JDBConnection.JDBC);
             PreparedStatement query1 = con.prepareStatement("UPDATE monsterslayer.Player SET level=?, exp= ?, money=?, adventureRank=? WHERE name=?");
             PreparedStatement query2 = con.prepareStatement("INSERT INTO monsterslayer.items (name,grade,className,itemName, durability) + VALUES(?,?,?,?,?)");
             PreparedStatement query3 = con.prepareStatement("UPDATE monsterslayer.Skills SET fullPowerStage=?,efficiency=?,power=?,reflection=?,trueMagic=?,judgement=? WHERE name=?");
             PreparedStatement query4 = con.prepareStatement("UPDATE monsterslayer.Weapon SET weaponName=?,weaponGrade=?,weaponDurability=? WHERE name=?");
             PreparedStatement query5 = con.prepareStatement("UPDATE monsterslayer.Armor SET armorName=?,armorGrade=?,armorDurability=? WHERE name=?")) {

            query1.setInt(1, player.getLevel());
            query1.setInt(2, player.getExp());
            query1.setInt(3, player.getMoney());
            query1.setString(4, player.getAdventureRank());
            query1.setString(5, player.getName());
            query1.executeUpdate();

            PreparedStatement itemQuery = con.prepareStatement("DELETE FROM monsterslayer.items WHERE name=?");
            itemQuery.setString(1, player.getName());
            itemQuery.executeUpdate();


            for (Items item : player.getBag()) {
                query2.setString(1, player.getName());
                query2.setInt(2, item.getGrade());
                query2.setString(3, item.getClass().getSimpleName());
                query2.setString(4, item.getName());
                System.err.println("Before the weapons and armor");
                query2.setDouble(5, 0);
                if (item instanceof Weapon) {
                    query2.setDouble(5, ((Weapon) item).getDurability());
                }
                if (item instanceof Armor) {
                    query2.setDouble(5, ((Armor) item).getDurability());
                }
                query2.executeUpdate();
            }

            System.err.println("Past all items");

            query3.setInt(1, player.getSkills().getFullPowerStage());
            query3.setDouble(2, player.getSkills().getEfficiency());
            query3.setDouble(3, player.getSkills().getPower());
            query3.setBoolean(4, player.getSkills().isReflection());
            query3.setBoolean(5, player.getSkills().isTrueMagic());
            query3.setBoolean(6, player.getSkills().isJudgement());
            query3.setString(7, player.getName());
            query3.executeUpdate();

            if (player.getWeapon() != null) {
                query4.setString(1, player.getWeapon().getName());
                query4.setInt(2, player.getWeapon().getGrade());
                query4.setDouble(3, player.getWeapon().getDurability());
                query4.setString(4, player.getName());
                query4.executeUpdate();
            }

            if (player.getArmor() != null) {
                query5.setString(1, player.getArmor().getName());
                query5.setInt(2, player.getArmor().getGrade());
                query5.setDouble(3, player.getArmor().getDurability());
                query5.setString(4, player.getName());
                query5.executeUpdate();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * Add player.
     *
     * @param player the player
     */
    public void addPlayer(Player player) {
        try (Connection con = DriverManager.getConnection(JDBConnection.JDBC);
             PreparedStatement query1 = con.prepareStatement("INSERT INTO monsterslayer.Player (name,password,salt,affinity,level,exp,money,adventureRank) VALUES(?,?,?,?,?,?,?,?)");
             PreparedStatement query2 = con.prepareStatement("INSERT INTO monsterslayer.items (name,grade,className,itemName, durability) VALUES(?,?,?,?,?)");
             PreparedStatement query3 = con.prepareStatement("INSERT INTO monsterslayer.Skills (name,fullPowerStage,efficiency,power,reflection,trueMagic,judgement) VALUES(?,?,?,?,?,?,?)");
             PreparedStatement query4 = con.prepareStatement("INSERT INTO monsterslayer.Weapon (name,weaponName,weaponGrade,weaponDurability) VALUES(?,?,?,?)");
             PreparedStatement query5 = con.prepareStatement("INSERT INTO monsterslayer.Armor (name,armorName,armorGrade,armorDurability) VALUES(?,?,?,?)")) {

            query1.setString(1, player.getName());
            query1.setString(2, player.getPasswordHash());
            query1.setString(3, player.getSalt());
            query1.setString(4, player.getAffinity());
            query1.setInt(5, player.getLevel());
            query1.setInt(6, player.getExp());
            query1.setInt(7, player.getMoney());
            query1.setString(8, player.getAdventureRank());
            query1.executeUpdate();

            for (Items item : player.getBag()) {
                query2.setString(1, player.getName());
                query2.setInt(2, item.getGrade());
                query2.setString(3, item.getClass().getSimpleName());
                query2.setString(4, item.getName());
                if (item instanceof Weapon) {
                    query2.setDouble(5, ((Weapon) item).getDurability());
                }
                if (item instanceof Armor) {
                    query2.setDouble(5, ((Armor) item).getDurability());
                }
                query2.executeUpdate();
            }

            query3.setString(1, player.getName());
            query3.setInt(2, player.getSkills().getFullPowerStage());
            query3.setDouble(3, player.getSkills().getEfficiency());
            query3.setDouble(4, player.getSkills().getPower());
            query3.setBoolean(5, player.getSkills().isReflection());
            query3.setBoolean(6, player.getSkills().isTrueMagic());
            query3.setBoolean(7, player.getSkills().isJudgement());
            query3.executeUpdate();

            if (player.getWeapon() != null) {
                query4.setString(1, player.getName());
                query4.setString(2, player.getWeapon().getName());
                query4.setInt(3, player.getWeapon().getGrade());
                query4.setDouble(4, player.getWeapon().getDurability());
                query4.executeUpdate();
            }

            if (player.getArmor() != null) {
                query5.setString(1, player.getName());
                query5.setString(2, player.getArmor().getName());
                query5.setInt(3, player.getArmor().getGrade());
                query5.setDouble(4, player.getArmor().getDurability());
                query5.executeUpdate();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Return player.
     *
     * @param name     the name
     * @param password the password
     * @return the player
     */
    public Player returnPlayer(String name, String password) {

        try (Connection con = DriverManager.getConnection(JDBConnection.JDBC);
             PreparedStatement query1 = con.prepareStatement("SELECT * FROM monsterslayer.Player WHERE name=?");
             PreparedStatement query2 = con.prepareStatement("SELECT * FROM monsterslayer.Items WHERE name=?");
             PreparedStatement query3 = con.prepareStatement("SELECT * FROM monsterslayer.Skills WHERE name=?");
             PreparedStatement query4 = con.prepareStatement("SELECT * FROM monsterslayer.Weapon WHERE name=?");
             PreparedStatement query5 = con.prepareStatement("SELECT * FROM monsterslayer.Armor WHERE name=?")) {
            query1.setString(1, name);
            query2.setString(1, name);
            query3.setString(1, name);
            query4.setString(1, name);
            query5.setString(1, name);

            String passwordHash = "", salt = "", affinity = "", adventureRank = "";
            int level = 0, exp = 0, money = 0;

            ResultSet rs1 = query1.executeQuery();
            if (rs1.next()) {
                passwordHash = rs1.getString("password");
                salt = rs1.getString("salt");
                affinity = rs1.getString("affinity");
                level = rs1.getInt("level");
                exp = rs1.getInt("exp");
                money = rs1.getInt("money");
            }

            List<Items> bag = new ArrayList<>();
            int grade = 0;
            String className = "", itemName = "";
            double durability = 0;

            ResultSet rs2 = query2.executeQuery();
            while (rs2.next()) {
                grade = rs2.getInt("grade");
                className = rs2.getString("className");
                itemName = rs2.getString("itemName");
                durability = rs2.getDouble("durability");
                bag.add(determineItemClass(grade, itemName, className, durability));

            }

            int fullPowerStage = 0;
            double efficiency = 0, power = 0;
            boolean reflection = false, trueMagic = false, judgement = false;

            ResultSet rs3 = query3.executeQuery();
            if (rs3.next()) {
                fullPowerStage = rs3.getInt("fullPowerStage");
                efficiency = rs3.getDouble("efficiency");
                power = rs3.getDouble("power");
                reflection = rs3.getBoolean("reflection");
                trueMagic = rs3.getBoolean("trueMagic");
                judgement = rs3.getBoolean("judgement");
            }

            String weaponName = "";
            int weaponGrade = 0;
            double weaponDurability = 0;
            Weapon weapon = null;

            ResultSet rs4 = query4.executeQuery();
            if (rs4.next()) {
                weaponName = rs4.getString("weaponName");
                weaponGrade = rs4.getInt("weaponGrade");
                weaponDurability = rs4.getDouble("weaponDurability");
                if (weaponDurability != 0) weapon = new Weapon(weaponName, weaponGrade, weaponDurability);
            }

            String armorName = "";
            int armorGrade = 0;
            double armorDurability = 0;
            Armor armor = null;

            ResultSet rs5 = query5.executeQuery();
            if (rs5.next()) {
                armorName = rs5.getString("armorName");
                armorGrade = rs5.getInt("armorGrade");
                armorDurability = rs5.getDouble("armorDurability");
                if (armorDurability != 0) armor = new Armor(armorName, armorGrade, armorDurability);
            }

            if (passwordHash.equals(HashClass.securePassword(password, salt))) {

                Player selectedPlayer = new Player(name, passwordHash, salt, affinity, level, exp, money, bag, new Skills(fullPowerStage, efficiency, power, reflection, trueMagic, judgement), weapon, armor, adventureRank);
                return selectedPlayer;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    private Items determineItemClass(int grade, String itemName, String className, double durability) {
        Items item = null;
        switch (className) {
            case "Mana_Potion" -> item = new Mana_Potion(itemName, grade);
            case "Power_Potion" -> item = new Power_Potion(itemName, grade);
            case "Weapon" -> item = new Weapon(itemName, grade, durability);
            case "Armor" -> item = new Armor(itemName, grade, durability);
        }
        return item;
    }

}
