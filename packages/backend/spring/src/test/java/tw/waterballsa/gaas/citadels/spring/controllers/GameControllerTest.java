package tw.waterballsa.gaas.citadels.spring.controllers;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import tw.waterballsa.gaas.citadels.domain.Game;
import tw.waterballsa.gaas.citadels.spring.CitadelsApplicationTest;
import tw.waterballsa.gaas.citadels.spring.view.GameView;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static tw.waterballsa.gaas.citadels.spring.view.GameView.toViewModel;

public class GameControllerTest extends CitadelsApplicationTest {
    private static final String GAME_ROOM_NAME = "gameRoom";
    private static final String GAME_PATH = "/api/games";

    @Test
    public void WhenCreateGameWithRoomNameAndHolderName_ShouldCreateSuccessfully() throws Exception {
//        GameView game = createGameAndGet();
        Game snowman = citadelsGameRepository.save(Game.builder()
                                                    .roomName("123")
                                                    .holderName("snowman").build());
        System.out.println(snowman.getId());
        System.out.println(snowman.getRoomName());
//        assertEquals(GAME_ROOM_NAME,game.roomName);
//        assertTrue(citadelsGameRepository.existsByName(GAME_ROOM_NAME));
    }

    private GameView createGameAndGet() {
        return toViewModel(Game.builder().build());
    }

    private void createGame(String gameName, String holderName) throws Exception {
//        CreateGameUseCase.Request request = new CreateGameUseCase.Request(gameName,holderName);
//        mockMvc.perform(post(GAME_PATH)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .body(toJson(request))
//                        .andExpect(status().isOk()));
    }

    private void getGame(int gameId) throws Exception {
        mockMvc.perform(get("/gameId"))
                .andExpect(status().isOk());
    }

    @SneakyThrows
    public String toJson(Object obj) {
        return objectMapper.writeValueAsString(obj);
    }

}
