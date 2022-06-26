package testen;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import domein.Player;

class PlayerTest {
	
	private final String NAAMOK = "guy";
	private final String WACHTWOORDOK = "Ditpwd956";
	
	private Player player = new Player(NAAMOK, WACHTWOORDOK, "");

	@ParameterizedTest
	@ValueSource(strings = {"Een123","eEn123","1En23"})
	void testPassword(String value) {
		Assertions.assertDoesNotThrow(() -> new Player(NAAMOK, value, ""));
	}

}
