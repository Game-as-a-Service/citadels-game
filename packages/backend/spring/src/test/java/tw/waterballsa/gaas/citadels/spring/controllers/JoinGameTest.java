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
        Player A = new Player("A", "imageName1");
        Player B = new Player("B", "imageName1");
        CitadelsGame game = givenGameStarted("room A", A, B);

        // join game
        Player C = new Player("C", "imageName2");
        String jsonBody = "{\"playerName\"  : \"" + C.getName()  + "\", " +
                           "\"playerImage\" : \"" + C.getImage() + "\"}";
        mockMvc.perform(post("/games/{gameId}:join", game.getId())
                    .contentType(APPLICATION_JSON)
                    .content(jsonBody))
                    .andExpect(status().isOk())
                    .andExpect(content().json("{" +
//                            "    \"joinTime\": \"2023-07-04T19:29:54.001Z\"," +
                            "    \"status\": \"OK\"," +
                            "    \"msg\": \"\"," +
                            "    \"room\": {" +
                            "       \"createTime\": \"" + game.getCreateTime() + "\"," +
                            "       \"gameId\": \"" + game.getId() + "\"," +
                            "       \"gameName\": \"" + game.getName() + "\"," +
                            "       \"holderId\": \"" + game.getHolder().getId() + "\"," +
                            "       \"holderName\": \"" + game.getHolder().getName() + "\"," +
                            "       \"players\": [" +
                            "           {" +
                            "               \"playerId\": \"" + A.getId() + "\"," +
                            "               \"playerName\": \"" + A.getName() + "\"," +
                            "               \"playerImage\":\"" + A.getImage() + "\"" +
                            "           }," +
                            "           {" +
                            "               \"playerId\": \"" + B.getId() + "\"," +
                            "               \"playerName\": \"" + B.getName() + "\"," +
                            "               \"playerImage\":\"" + B.getImage() + "\"" +
                            "           }" +
                            "       ]," +
                            "       \"status\": \"OPEN\"," +
                            "       \"totalPlayers\": 2" +
                            "       }" +
                            "}"));

        // check result: nums of player & each name, id
        CitadelsGame actualGame = findById(game.getId());

        assertEquals(3, actualGame.getPlayers().size());
        assertTrue(actualGame.findPlayerById(A.getId()).equals(A));
        assertTrue(actualGame.findPlayerById(B.getId()).equals(B));
//        assertTrue(actualGame.getPlayer("C").equals(C));
    }

    @Test
    public void playerJoinsGameUnsuccessfully() throws Exception {

        Player A = new Player("A", "imageName1");
        Player B = new Player("B", "imageName1");
        Player C = new Player("C", "imageName1");
        Player D = new Player("D", "imageName1");
        Player E = new Player("E", "imageName1");
        Player F = new Player("F", "imageName1");
        Player G = new Player("G", "imageName1");
        CitadelsGame game = givenGameStarted("room A", A, A, B, C, D, E, F, G);

        // join game
        Player H = new Player("H", "imageName1");
        String jsonBody = "{\"playerName\"  : \"" + H.getName()  + "\", " +
                           "\"playerImage\" : \"" + H.getImage() + "\"}";
        mockMvc.perform(post("/games/{gameId}:join", game.getId())
                        .contentType(APPLICATION_JSON)
                        .content(jsonBody))
                        .andExpect(status().isBadRequest())
                        .andExpect(content().json("{\"status\" :  \"FAIL\"," +
                                                   "\"msg\" :  \"GAME IS FULL\"}"));

        CitadelsGame actualGame = findById(game.getId());

        assertEquals(7, actualGame.getPlayers().size());
        assertTrue(actualGame.findPlayerById(A.getId()).equals(A));
        assertTrue(actualGame.findPlayerById(B.getId()).equals(B));
        assertTrue(actualGame.findPlayerById(C.getId()).equals(C));
        assertTrue(actualGame.findPlayerById(D.getId()).equals(D));
        assertTrue(actualGame.findPlayerById(E.getId()).equals(E));
        assertTrue(actualGame.findPlayerById(F.getId()).equals(F));
        assertTrue(actualGame.findPlayerById(G.getId()).equals(G));
    }
}
