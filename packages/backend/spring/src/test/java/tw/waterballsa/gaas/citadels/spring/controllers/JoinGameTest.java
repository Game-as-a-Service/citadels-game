package tw.waterballsa.gaas.citadels.spring.controllers;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import tw.waterballsa.gaas.citadels.app.repositories.CitadelsGameRepository;
import tw.waterballsa.gaas.citadels.domain.CitadelsGame;
import tw.waterballsa.gaas.citadels.domain.Player;
import tw.waterballsa.gaas.citadels.spring.repositories.dao.CitadelsGameDAO;

import static org.assertj.core.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.test.web.servlet.MockMvc;


@SpringBootTest
@AutoConfigureMockMvc
public class JoinGameTest {

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
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CitadelsGameRepository gameRepository;

    private CitadelsGame givenGameStarted(Player ...players) {
        return gameRepository.save(new CitadelsGame(asList(players)));
    }

    @Test
    void playerJoinsGameSuccessfully() {
        // init game
        Player A = new Player("A");
        Player B = new Player("B");
        CitadelsGame game = givenGameStarted(A, B);

        // join game
        Player C = new Player("C");
        game.joinPlayer(C);

        // check result
        assertEquals(game.getPlayers().size(), 3);
        assertEquals(game.getPlayer("C").getName(), "C");
    }


    @Test
    void playerJoinsGameUnsuccessfully() {
        Player A = new Player("A");
        Player B = new Player("B");
        CitadelsGame game = givenGameStarted(A, B);
        Player C = new Player("C");
    }
}
