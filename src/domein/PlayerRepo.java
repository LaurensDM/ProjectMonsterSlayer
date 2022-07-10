package domein;

import java.util.ArrayList;
import java.util.List;

import persistentie.PlayerMapper;

/**
 * The type Player repo.
 */
public class PlayerRepo {

	/**
	 * The Players.
	 */
	List<Player> players;
	private PlayerMapper mapper;

	/**
	 * Instantiates a new Player repo.
	 */
	public PlayerRepo() {
		mapper = new PlayerMapper();
		players = mapper.returnList();
	}

	/**
	 * Save player.
	 *
	 * @param player the player
	 */
	public void savePlayer(Player player) {
		
	}

	/**
	 * Register player.
	 *
	 * @param player the player
	 */
	public void registerPlayer(Player player) {
		mapper.addPlayer(player);
	}

	/**
	 * Select player.
	 *
	 * @param name     the name
	 * @param password the password
	 * @return the player
	 */
	public Player selectPlayer(String name, String password) {
		Player player = mapper.returnPlayer(name, password);
		if (player==null){
			throw new IllegalArgumentException("Login error, check username and password");
		}

		return player;
	}
	

}
