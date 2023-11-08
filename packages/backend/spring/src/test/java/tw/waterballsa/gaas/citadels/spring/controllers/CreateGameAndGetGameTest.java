package tw.waterballsa.gaas.citadels.spring.controllers;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.ResultActions;
import tw.waterballsa.gaas.citadels.spring.CitadelsSpringBootTest;
import tw.waterballsa.gaas.citadels.spring.controllers.viewmodel.GameView;
import tw.waterballsa.gaas.citadels.spring.controllers.viewmodel.GetGameView;
import tw.waterballsa.gaas.citadels.spring.controllers.viewmodel.PlayerView;
import tw.waterballsa.gaas.citadels.spring.controllers.viewmodel.StartGameView;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static tw.waterballsa.gaas.citadels.domain.Game.DEFAULT_CARD_QUANTITY;
import static tw.waterballsa.gaas.citadels.domain.Game.DEFAULT_COINS;
import static tw.waterballsa.gaas.citadels.domain.Game.Status.PLAYING;

public class CreateGameAndGetGameTest extends CitadelsSpringBootTest {

    @Test
    public void whenRoomReady_ShouldGetStartGameSuccessfully() {
        GameController.StartGameRequest request = getTestGame();
        StartGameView startGameView = createGame(request);

        GetGameView getGameViewResponse = getGame(startGameView.getGameId());
        GameView gameView = getGameViewResponse.getGameView();

        assertNotNull(gameView);
        assertEquals(PLAYING,gameView.getStatus());
        assertEquals(request.getRoomId(), gameView.getRoomId());
        assertEquals(startGameView.getGameId(), gameView.getId());
        List<PlayerView> playerViews = gameView.getPlayerViews();
        assertEquals(request.getPlayers().size(), playerViews.size());
        playerViews.forEach(player -> {
            assertEquals(DEFAULT_COINS, player.getCoins());
            assertEquals(DEFAULT_CARD_QUANTITY, player.getBuildCards().size());
        });
    }


    @SneakyThrows
    private GetGameView getGame(String gameId) {
        ResultActions mvcResult = mockMvc.perform(get(API_PREFIX + "/games/{gameId}", gameId))
                .andExpect(status().isOk());
        return getBody(mvcResult, GetGameView.class);
    }


    private GameController.StartGameRequest getTestGame() {
        List<GameController.UserRequest> players = Arrays.asList(
                new GameController.UserRequest("1", "p1", "1"),
                new GameController.UserRequest("2", "p2", "2"),
                new GameController.UserRequest("3", "p3", "3"),
                new GameController.UserRequest("4", "p4", "4"),
                new GameController.UserRequest("5", "p5", "5")
        );
        return new GameController.StartGameRequest("1", "roomA", "1", players);
    }

    @SneakyThrows
    private StartGameView createGame(GameController.StartGameRequest startGameRequest) {
        ResultActions resultActions = mockMvc.perform(post(API_PREFIX + "/games")
                        .contentType(APPLICATION_JSON)
                        .content(toJson(startGameRequest)))
                .andExpect(status().isOk());
        return getBody(resultActions, StartGameView.class);
    }
}
