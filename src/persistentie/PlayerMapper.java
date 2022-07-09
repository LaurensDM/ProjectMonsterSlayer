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
		players.add(new Player("Talos", "80209f3c062a027724686f9e6638e23ea001bcce41af3754b104059fd567f8dc560acd37a1f0379794edaf25ce5191aaa452370d7387bb2bbbe015a1ed1c257a","qL6vVvRyV0K7w6YWwnb3ZA", "True Magic", 100, 0, 5000000, items,
				new Skills(3, 0.002, 20, true, true, true), new Weapon("Staff of the Archmagi", 6),
				new Armor("Robe of the Archmagi", 6)));
		players.add(new Player("Guy1", "b38ae7bcaf61a7b3533b5b6a79b6eb06a2cb16476e2917f06ffd3c3291e9e6d20a158c035f37c6a6f443154871f979848f5598fd204685baa14cf327661a7b8f","tJtHpgMn4YOVHLxe8bWd2A", "Fire", 50, 0, 5000, new ArrayList<>(),
				new Skills(3, 0.8, 2, true, true, false), null, null));
		players.add(new Player("Guy2", "dcc8406b481b68730456174c7355a2b555f85f547c6d2e2cd260bea9c30489a0146ef5d2a465a8c02026ad678d21655edf53f832f070dd0a5bcf8651865b37e1","zsjwz90zIED4Rz1E5T/8UA", "Water", 40, 400, 4000, new ArrayList<>(),
				new Skills(2, 0.2, 1.5, true, true, false), null, null));
		players.add(new Player("Guy3", "f0b049ebb40e9f4deea51abb0f2c56541bf4167bfa5e8aff13c83a750364288a6ee0012a0dc806f889a3aeca8df7d43f02fe7b96bd2a50beb97fa2876acaf354","cFLpCX+HJL0+H2xotF14jw", "Lightning", 30, 300, 3000, new ArrayList<>(), new Skills(),
				null, null));
		players.add(new Player("Guy4", "f8c9227a875ab0843f3e166b60a80ac689e8a740cd19d4de9340dc07387eb87f147359c971036edd50a919fe6c360232aafea511d9d9832f3efc1f6fbc3c5aa5","yAq3DrWcB18FRAXckTLc9w", "Wind"));
		players.add(new Player("Guy5", "18c7b08e02d18f2edc41f8bb86de0f539498f241460565072978da8ec954cca96539bcc2c60b56bd7d4fe5c19d5bbd5f1f9aa8b6324115b3ba321552a059bdd4","njZi2hpw4PMMpgWPJ9SwLg", "Earth"));
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
