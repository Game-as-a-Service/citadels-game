package tw.waterballsa.gaas.citadels.spring.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import tw.waterballsa.gaas.citadels.app.repositories.RoomRepository;
import tw.waterballsa.gaas.citadels.domain.Room;
import tw.waterballsa.gaas.citadels.domain.User;
import tw.waterballsa.gaas.citadels.spring.CitadelsSpringBootTest;
import tw.waterballsa.gaas.citadels.spring.controllers.viewmodel.GetRoomsView;
import tw.waterballsa.gaas.citadels.spring.util.ResponseStatus;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class GetRoomsTest extends CitadelsSpringBootTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private RoomRepository roomRepository;

    @BeforeEach
    public void setUp() {
        roomRepository.deleteAll();
    }

    @Test
    public void givenOneRoom_whenUserGetRooms_thenGetOneRoom() throws Exception {

        // givenOneRoom
        User userA = new User("A", "imageName1");
        User userB = new User("B", "imageName1");
        User userC = new User("C", "imageName1");
        User userD = new User("D", "imageName1");
        User userE = new User("E", "imageName1");
        User userF = new User("F", "imageName1");
        User userG = new User("G", "imageName1");
        Room room = givenRoomStarted("room A", userA.getId(), userA, userB, userC, userD, userE, userF, userG);

        // whenUserGetRooms
        MvcResult mvcResult = mockMvc.perform(get(API_PREFIX + "/rooms")
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();

        String content = mvcResult.getResponse().getContentAsString();
        GetRoomsView getRoomsView = fromJson(content, GetRoomsView.class);

        // thenGetOneRoom
        assertEquals(ResponseStatus.OK, getRoomsView.getStatus());
        assertEquals(1, getRoomsView.getTotalRooms());
        assertEquals(room.getId(), getRoomsView.getRooms().get(0).getRoomId());

    }
}
