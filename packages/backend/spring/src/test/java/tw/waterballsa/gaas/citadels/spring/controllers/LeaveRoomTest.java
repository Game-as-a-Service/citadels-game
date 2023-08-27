package tw.waterballsa.gaas.citadels.spring.controllers;

import org.junit.jupiter.api.Test;
import tw.waterballsa.gaas.citadels.domain.Room;
import tw.waterballsa.gaas.citadels.domain.User;
import tw.waterballsa.gaas.citadels.spring.CitadelsSpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class LeaveRoomTest extends CitadelsSpringBootTest {

    @Test
    public void givenRoomAHave3Users_whenUserCLeaveRoomA_thenRoomAHaveNotUserC() throws Exception {
        User userA = new User("userA", "image");
        User userB = new User("userB", "image");
        User userC = new User("userC", "image");
        String roomAId = givenRoomStarted("roomA", userA.getId(), userA, userB, userC).getId();

        mockMvc.perform(post(API_PREFIX + "/rooms/{roomId}:leave", roomAId).content("{\"userId\": \"" + userC.getId() + "\"}"))
                .andExpect(status().isOk());

        Room roomA = roomRepository.findRoomById(roomAId).orElseThrow();
        assertNull(roomA.findUserById(userC.getId()));
    }

    @Test
    public void givenRoomAHave1User_whenUserALeaveRoomA_thenRoomAIsClose() throws Exception {
        User userA = new User("userA", "image");
        String roomAId = givenRoomStarted("roomA", userA.getId(), userA).getId();

        mockMvc.perform(post(API_PREFIX + "/rooms/{roomId}:leave", roomAId).content("{\"userId\": \"" + userA.getId() + "\"}"))
                .andExpect(status().isOk());

        Room roomA = roomRepository.findRoomById(roomAId).orElseThrow();
        assertEquals(Room.Status.CLOSE, roomA.getStatus());
    }
}
