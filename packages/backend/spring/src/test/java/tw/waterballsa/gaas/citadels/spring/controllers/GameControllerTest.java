package tw.waterballsa.gaas.citadels.spring.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.shaded.com.fasterxml.jackson.core.type.TypeReference;
import tw.waterballsa.gaas.citadels.app.repositories.CitadelsGameRepository;
import tw.waterballsa.gaas.citadels.app.usecases.CreateGameUseCase;
import tw.waterballsa.gaas.citadels.domain.Game;
import tw.waterballsa.gaas.citadels.spring.CitadelsApplication;
import tw.waterballsa.gaas.citadels.spring.view.GameView;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class GameControllerTest {
    private static final String GAME_ROOM_NAME = "gameRoom";
    private static final String GAME_PATH = "/api/games";
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private CitadelsGameRepository citadelsGameRepository;

    @AfterEach
    void clean() {
        citadelsGameRepository.deleteAll();
    }

    @Test
    public void WhenCreateGameWithRoomNameAndHolderName_ShouldCreateSuccessfully() throws Exception {
//        GameView game = createGameAndGet();
        Game snowman = citadelsGameRepository.save(Game.builder().roomName("123").holderName("snowman").build());
        System.out.println(snowman.getId());
        System.out.println(snowman.getRoomName());
//        assertEquals(GAME_ROOM_NAME,game.roomName);
//        assertTrue(citadelsGameRepository.existsByName(GAME_ROOM_NAME));
    }

    private GameView createGameAndGet() {
        return GameView.toViewModel(Game.builder().build());
    }

    private void createGame(String gameName,String holderName) throws Exception {
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
