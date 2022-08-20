package persistentie;

import domein.*;
import resources.HashClass;

import java.sql.*;
import java.util.HashMap;
import java.util.Map.Entry;

/**
 * The type Player mapper.
 */
public class PlayerMapper {

    /**
     * Save player.
     *
     * @param player the player
     */
    public void savePlayer(Player player) {
        try (Connection con = DriverManager.getConnection(JDBConnection.JDBC);
             PreparedStatement query1 = con.prepareStatement("UPDATE monsterslayer.Player SET level=?, exp= ?, money=?, adventureRank=? WHERE name=?");
             PreparedStatement query2 = con.prepareStatement("INSERT INTO monsterslayer.items (name,grade,className,itemName, durability, amount) VALUES(?,?,?,?,?,?)");
             PreparedStatement query3 = con.prepareStatement("UPDATE monsterslayer.Skills SET fullPowerStage=?,efficiency=?,power=?,reflection=?,trueMagic=?,judgement=? WHERE name=?");
             PreparedStatement query4 = con.prepareStatement("INSERT INTO monsterslayer.Weapon (name,weaponName,weaponGrade,weaponDurability) VALUES(?,?,?,?)");
             PreparedStatement query5 = con.prepareStatement("INSERT INTO monsterslayer.Armor (name,armorName,armorGrade,armorDurability) VALUES(?,?,?,?)")) {

            query1.setInt(1, player.getLevel());
            query1.setInt(2, player.getExp());
            query1.setInt(3, player.getMoney());
            query1.setString(4, player.getAdventureRank());
            query1.setString(5, player.getName());
            query1.executeUpdate();

            PreparedStatement itemQuery = con.prepareStatement("DELETE FROM monsterslayer.items WHERE name=?");
            itemQuery.setString(1, player.getName());
            itemQuery.executeUpdate();


            Items item;
            for (Entry<Items, Integer> entry : player.getBag().entrySet()) {
                item = entry.getKey();
                query2.setString(1, player.getName());
                query2.setInt(2, item.getGrade());
                query2.setString(3, item.getClass().getSimpleName());
                query2.setString(4, item.getName());
                if (!(item instanceof Weapon) && !(item instanceof Armor)) {
                    query2.setDouble(5, 0);
                }
                if (item instanceof Weapon) {
                    query2.setDouble(5, ((Weapon) item).getDurability());
                }
                if (item instanceof Armor) {
                    query2.setDouble(5, ((Armor) item).getDurability());
                }
                query2.setInt(6, entry.getValue());
                query2.executeUpdate();
            }


            query3.setInt(1, player.getSkills().getFullPowerStage());
            query3.setDouble(2, player.getSkills().getEfficiency());
            query3.setDouble(3, player.getSkills().getPower());
            query3.setBoolean(4, player.getSkills().isReflection());
            query3.setBoolean(5, player.getSkills().isTrueMagic());
            query3.setBoolean(6, player.getSkills().isJudgement());
            query3.setString(7, player.getName());
            query3.executeUpdate();


            PreparedStatement weaponQuery = con.prepareStatement("DELETE FROM monsterslayer.weapon WHERE name=?");
            weaponQuery.setString(1, player.getName());
            weaponQuery.executeUpdate();

            if (player.getWeapon() != null) {
                query4.setString(1, player.getName());
                query4.setString(2, player.getWeapon().getName());
                query4.setInt(3, player.getWeapon().getGrade());
                query4.setDouble(4, player.getWeapon().getDurability());

                query4.executeUpdate();
            }

            PreparedStatement armorQuery = con.prepareStatement("DELETE FROM monsterslayer.armor WHERE name=?");
            armorQuery.setString(1, player.getName());
            armorQuery.executeUpdate();

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
     * Add player.
     *
     * @param player the player
     */
    public void addPlayer(Player player) {
        try (Connection con = DriverManager.getConnection(JDBConnection.JDBC);
             PreparedStatement query1 = con.prepareStatement("INSERT INTO monsterslayer.Player (name,password,salt,affinity,level,exp,money,adventureRank) VALUES(?,?,?,?,?,?,?,?)");
             PreparedStatement query2 = con.prepareStatement("INSERT INTO monsterslayer.items (name,grade,className,itemName, durability,amount) VALUES(?,?,?,?,?,?)");
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

            Items item;
            for (Entry<Items, Integer> entry : player.getBag().entrySet()) {
                item = entry.getKey();
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
                query2.setInt(6, entry.getValue());
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
                adventureRank = rs1.getString("adventureRank");
            }

            HashMap<Items, Integer> bag = new HashMap<>();
            int grade = 0;
            String className = "", itemName = "";
            double durability = 0;
            int amount = 0;

            ResultSet rs2 = query2.executeQuery();
            while (rs2.next()) {
                grade = rs2.getInt("grade");
                className = rs2.getString("className");
                itemName = rs2.getString("itemName");
                durability = rs2.getDouble("durability");
                amount = rs2.getInt("amount");
                bag.put(determineItemClass(grade, itemName, className, durability), amount);

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
            case "Magic_Stone" -> item = new Magic_Stone(itemName, grade);
        }
        return item;
    }

}
