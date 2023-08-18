package tw.waterballsa.gaas.citadels.spring.controllers;

import org.junit.jupiter.api.Test;
import tw.waterballsa.gaas.citadels.domain.CitadelsGame;
import tw.waterballsa.gaas.citadels.domain.Player;
import tw.waterballsa.gaas.citadels.spring.CitadelsApplicationTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

public class JoinGameTest extends CitadelsApplicationTest {

    @Test
    public void playerJoinsGameSuccessfully() throws Exception {

        // init game
        Player A = new Player("A");
        Player B = new Player("B");
        CitadelsGame game = givenGameStarted(A, B);

        // join game
        Player C = new Player("C");
        String jsonBody = "{\"playerName\" : \"" + C.getName() + "\"}";
        mockMvc.perform(post("/games/{gameId}:join", game.getId())
                    .contentType(APPLICATION_JSON)
                    .content(jsonBody))
                    .andExpect(status().isOk());

        // check result: nums of player & each name, id
        CitadelsGame actualGame = findById(game.getId());

        assertEquals(actualGame.getPlayers().size(), 3);
        assertTrue(actualGame.getPlayer("A").equals(A));
        assertTrue(actualGame.getPlayer("B").equals(B));
//        assertTrue(actualGame.getPlayer("C").equals(C));
    }

    @Test
    public void playerJoinsGameUnsuccessfully() throws Exception {

        Player A = new Player("A");
        Player B = new Player("B");
        Player C = new Player("C");
        Player D = new Player("D");
        Player E = new Player("E");
        Player F = new Player("F");
        Player G = new Player("G");
        CitadelsGame game = givenGameStarted(A, B, C, D, E, F, G);

        // join game
        Player H = new Player("H");
        String jsonBody = "{\"playerName\" : \"" + H.getName() + "\" }";
        mockMvc.perform(post("/games/{gameId}:join", game.getId())
                        .contentType(APPLICATION_JSON)
                        .content(jsonBody))
                        .andExpect(status().isBadRequest())
                        .andExpect(content().string("GAME IS FULL"));

        CitadelsGame actualGame = findById(game.getId());

        assertEquals(actualGame.getPlayers().size(), 7);
        assertTrue(actualGame.getPlayer("A").equals(A));
        assertTrue(actualGame.getPlayer("B").equals(B));
        assertTrue(actualGame.getPlayer("C").equals(C));
        assertTrue(actualGame.getPlayer("D").equals(D));
        assertTrue(actualGame.getPlayer("E").equals(E));
        assertTrue(actualGame.getPlayer("F").equals(F));
        assertTrue(actualGame.getPlayer("G").equals(G));
    }
}
