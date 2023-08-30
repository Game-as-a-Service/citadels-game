package tw.waterballsa.gaas.citadels.spring.controllers;

import org.junit.jupiter.api.Test;
import tw.waterballsa.gaas.citadels.domain.Room;
import tw.waterballsa.gaas.citadels.domain.User;
import tw.waterballsa.gaas.citadels.spring.CitadelsSpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class LeaveRoomTest extends CitadelsSpringBootTest {

    @Test
    public void givenRoomAHave3Users_whenUserCLeaveRoomA_thenRoomAHaveNotUserC() throws Exception {
        User userA = new User("userA", "image");
        User userB = new User("userB", "image");
        User userC = new User("userC", "image");
        String roomAId = givenRoomStarted("roomA", userA.getId(), userA, userB, userC).getId();

        mockMvc.perform(post(API_PREFIX + "/rooms/{roomId}:leave", roomAId)
                        .contentType(APPLICATION_JSON)
                        .content("{\"userId\": \"" + userC.getId() + "\"}"))
                .andExpect(status().isOk());

        Room roomA = roomRepository.findRoomById(roomAId).orElseThrow();
        assertFalse(roomA.findUserById(userC.getId()).isPresent());
    }

    @Test
    public void givenRoomAHave1User_whenUserALeaveRoomA_thenRoomAIsClose() throws Exception {
        User userA = new User("userA", "image");
        String roomAId = givenRoomStarted("roomA", userA.getId(), userA).getId();

        mockMvc.perform(post(API_PREFIX + "/rooms/{roomId}:leave", roomAId)
                        .contentType(APPLICATION_JSON)
                        .content("{\"userId\": \"" + userA.getId() + "\"}"))
                .andExpect(status().isOk());

        Optional<Room> roomA = roomRepository.findRoomById(roomAId);
        assertFalse(roomA.isPresent());
    }

    @Test
    public void givenRoomAHaveUserAIsHolderAndTheOther2Users_whenUserALeaveRoomA_thenHolderWillChanged() throws Exception {
        User userA = new User("userA", "image");
        User userB = new User("userB", "image");
        User userC = new User("userC", "image");
        String roomAId = givenRoomStarted("roomA", userA.getId(), userA, userB, userC).getId();

        mockMvc.perform(post(API_PREFIX + "/rooms/{roomId}:leave", roomAId)
                        .contentType(APPLICATION_JSON)
                        .content("{\"userId\": \"" + userA.getId() + "\"}"))
                .andExpect(status().isOk());

        Room roomA = roomRepository.findRoomById(roomAId).orElseThrow();
        assertNotNull(roomA.findHolder());
        assertNotEquals(userA, roomA.findHolder());
    }
}
