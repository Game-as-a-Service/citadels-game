package tw.waterballsa.gaas.citadels.spring.controllers;

import org.junit.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import tw.waterballsa.gaas.citadels.domain.CitadelsGame;
import tw.waterballsa.gaas.citadels.domain.Player;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
public class PlayerLeaveGameTest {

    @Test
    public void testPlayerLeaveGameSuccess() {
        Player a = new Player("A");
        Player b = new Player("B");
        Player c = new Player("C");

        CitadelsGame game = new CitadelsGame(new ArrayList<>(Arrays.asList(a, b, c)));

        assertEquals(game.getPlayers().size(), 3);
        assertTrue(game.removePlayer(c));
        assertEquals(game.getPlayers().size(), 2);
        assertEquals(game.getPlayerByName("A"), a);
        assertEquals(game.getPlayerByName("B"), b);
        assertNull(game.getPlayerByName("C"));
    }

    @Test
    public void testPlayerLeaveGameFail() {
        Player a = new Player("A");
        Player b = new Player("B");
        Player c = new Player("C");

        CitadelsGame game = new CitadelsGame(new ArrayList<>(Arrays.asList(a, b)));

        assertEquals(game.getPlayers().size(), 2);
        assertFalse(game.removePlayer(c));
        assertEquals(game.getPlayers().size(), 2);
        assertEquals(game.getPlayerByName("A"), a);
        assertEquals(game.getPlayerByName("B"), b);
        assertNull(game.getPlayerByName("C"));
    }
}
