package tw.waterballsa.gaas.citadels.spring.controllers;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.ResultActions;
import tw.waterballsa.gaas.citadels.spring.CitadelsSpringBootTest;
import tw.waterballsa.gaas.citadels.spring.controllers.viewmodel.CreateRoomView;
import tw.waterballsa.gaas.citadels.spring.controllers.viewmodel.RoomView;
import tw.waterballsa.gaas.citadels.spring.repositories.data.RoomData;
import tw.waterballsa.gaas.citadels.spring.repositories.data.UserData;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class RoomControllerTest extends CitadelsSpringBootTest {
    private static final String API_PREFIX = "/api/citadels";

    @Test
    public void whenUserACreateRoom_ShouldCreateSuccessfully() {
        RoomController.CreateRoomRequest createRoomRequest = new RoomController.CreateRoomRequest("RoomA", "userA", "user.png");
        CreateRoomView createRoomView = getBody(createRoom(createRoomRequest), CreateRoomView.class);
        Optional<RoomData> roomData = RoomDAO.findById(createRoomView.getRoom().getRoomId());

        assertTrue(roomData.isPresent());
        RoomData room = roomData.get();
        UserData userData = room.getUsers().get(0);
        RoomView roomView = createRoomView.getRoom();
        assertEquals(roomView.getRoomName(), room.getName());
        assertEquals(roomView.getHolderName(), userData.getName());
    }

    @SneakyThrows
    private ResultActions createRoom(RoomController.CreateRoomRequest createRoomRequest) {
        return mockMvc.perform(post(API_PREFIX + "/room")
                        .contentType(APPLICATION_JSON)
                        .content(toJson(createRoomRequest)))
                .andExpect(status().isOk());
    }
}
