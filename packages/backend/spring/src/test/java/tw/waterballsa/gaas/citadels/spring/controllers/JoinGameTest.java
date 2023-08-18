package tw.waterballsa.gaas.citadels.spring.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import tw.waterballsa.gaas.citadels.app.repositories.CitadelsGameRepository;
import tw.waterballsa.gaas.citadels.domain.CitadelsGame;
import tw.waterballsa.gaas.citadels.domain.Player;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//    @Test Saboteur
//    public void 都好的硬要修() throws Exception {
//        Player A = defaultPlayer("A");
//        Player B = defaultPlayer("B");
//        B.addHandCard(new Repair(ToolName.PICK));
//
//        Player C = defaultPlayer("C");
//
//        SaboteurGame game = givenGameStarted(A, B, C);
//
//        mockMvc.perform(post("/api/games/{gameId}:playCard", game.getId())
//                        .contentType(APPLICATION_JSON)
//                        .content("""
//                                {   "cardType": "REPAIR",
//                                "playerId": "B",
//                                  "handIndex": 0,
//                                  "targetPlayerId": "A"
//                                }"""))
//                .andExpect(status().isBadRequest());
//
//        var actualGame = findGameById(game.getId());
//        Player actualA = actualGame.getPlayer("A");
//
//        assertTrue(actualA.getTool(ToolName.MINE_CART).isAvailable());
//        assertTrue(actualA.getTool(ToolName.LANTERN).isAvailable());
//        assertTrue(actualA.getTool(ToolName.PICK).isAvailable());
//    }

@SpringBootTest
@AutoConfigureMockMvc
public class JoinGameTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CitadelsGameRepository citadelsGameRepository;

    private CitadelsGame givenGameStarted(Player ...players) {
        return citadelsGameRepository.save(new CitadelsGame(asList(players)));
    }

    private CitadelsGame findById(String gameId) {
        return citadelsGameRepository.findById(gameId);
    }

    // 房間的狀態要不要考慮 ?
    @Test
    public void playerJoinsGameSuccessfully() throws Exception {

        // init game
        Player A = new Player("123", "A");
        Player B = new Player("456", "B");
        CitadelsGame game = givenGameStarted(A, B);

        // join game
        // post /games/{gameId}:join
        // body playerId, playerName
        Player C = new Player("789", "C");
        String jsonBody = "{\"playerName\" : \"" + C.getName() + "\", " +
                "\"playerId\" : \"" + C.getId() + "\" }";
        mockMvc.perform(post("/games/{gameId}:join", game.getId())
                    .contentType(APPLICATION_JSON)
                    .content(jsonBody))
            .andExpect(status().isOk());

        // check result: nums of player & each name, id
        CitadelsGame actualGame = findById(game.getId());
        assertEquals(actualGame.getPlayers().size(), 3);
        assertTrue(actualGame.getPlayer("A").equals(A));
        assertTrue(actualGame.getPlayer("B").equals(B));
        assertTrue(actualGame.getPlayer("C").equals(C));
    }



    @Test
    public void playerJoinsGameUnsuccessfully() throws Exception {
        Player A = new Player("123", "A");
        Player B = new Player("456", "B");
        Player C = new Player("789", "C");
        Player D = new Player("147", "D");
        Player E = new Player("258", "E");
        Player F = new Player("369", "F");
        Player G = new Player("159", "G");
        CitadelsGame game = givenGameStarted(A, B, C, D, E, F, G);

        // join game
        // post /games/{gameId}:join
        // body playerId, playerName
        Player H = new Player("789", "H");
        String jsonBody = "{\"playerName\" : \"" + H.getName() + "\", " +
                "\"playerId\" : \"" + H.getId() + "\" }";
        System.out.println(game.getId() + "-UnitTest");
        mockMvc.perform(post("/games/{gameId}:join", game.getId())
                        .contentType(APPLICATION_JSON)
                        .content(jsonBody))
                .andExpect(status().isServiceUnavailable());

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
