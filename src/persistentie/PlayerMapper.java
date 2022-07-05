package persistentie;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import domein.Armor;
import domein.Items;
import domein.Mana_Potion;
import domein.Player;
import domein.Power_Potion;
import domein.Skills;
import domein.Weapon;

public class PlayerMapper {
	private List<Player> players;

	public PlayerMapper() {
		players = new ArrayList<>();
		List<Items> items = new ArrayList<>();
		Collections.addAll(items, new Mana_Potion("Arcane Potion", 6), new Power_Potion("Ancient Power Potion", 6));
		players.add(new Player("Talos", "oi123", "True Magic", 100, 0, 5000000, items,
				new Skills(3, 0.002, 20, true, true, true), new Weapon("Staff of the Archmagi", 6),
				new Armor("Robe of the Archmagi", 6)));
		players.add(new Player("Guy1", "EerstePwd123", "Fire", 50, 0, 5000, new ArrayList<>(),
				new Skills(3, 0.8, 2, true, true, false), null, null));
		players.add(new Player("Guy2", "EerstePwd123", "Water", 40, 400, 4000, new ArrayList<>(),
				new Skills(2, 0.2, 1.5, true, true, false), null, null));
		players.add(new Player("Guy3", "EerstePwd123", "Lightning", 30, 300, 3000, new ArrayList<>(), new Skills(),
				null, null));
		players.add(new Player("Guy4", "EerstePwd123", "Wind"));
		players.add(new Player("Guy5", "EerstePwd123", "Earth"));
	}

	public void savePlayer(Player player) {

	}

	public List<Player> returnList() {
		return players;
	}

	public void addPlayer(Player player) {
		players.add(player);
	}

	public Player returnPlayer(String name, String password) {
		for (Player player : players) {
			if (player.getName().equals(name) && player.getWachtwoord().equals(password)) {
				return player;
			}
		}
		return null;
	}

}
