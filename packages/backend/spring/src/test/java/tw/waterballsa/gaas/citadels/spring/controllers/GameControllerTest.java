package tw.waterballsa.gaas.citadels.spring.controllers;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.ResultActions;
import tw.waterballsa.gaas.citadels.app.usecases.CreateGameUseCase;
import tw.waterballsa.gaas.citadels.spring.CitadelsSpringBootTest;
import tw.waterballsa.gaas.citadels.spring.controllers.viewmodel.GameView;
import tw.waterballsa.gaas.citadels.spring.controllers.viewmodel.RoomView;
import tw.waterballsa.gaas.citadels.spring.repositories.data.GameData;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class GameControllerTest extends CitadelsSpringBootTest {
    private static final String API_PREFIX = "/api/citadels";

    @Test
    public void whenPlayerACreatGame_ShouldCreateSuccessfullyAndBecameGameHolder() {
        CitadelsGameController.CreateGameRequest createGameRequest = new CitadelsGameController.CreateGameRequest("RoomA", "playerA","playerA.png");
        GameView gameView = getBody(createGame(createGameRequest.toRequest()), GameView.class);
        Optional<GameData> gameData = citadelsGameDAO.findById(gameView.getRoomView().getGameId());

        assertTrue(gameData.isPresent());
        GameData game = gameData.get();
        RoomView roomView = gameView.getRoomView();
        assertEquals(roomView.getGameName(),game.getGameName());
        assertEquals(roomView.getHolderName(),game.getHolderName());
    }


    @SneakyThrows
    private ResultActions createGame(CreateGameUseCase.Request createGameRequest) {
        return mockMvc.perform(post(API_PREFIX + "/game")
                        .contentType(APPLICATION_JSON)
                        .content(toJson(createGameRequest)))
                                .andExpect(status().isOk());
        }
}
