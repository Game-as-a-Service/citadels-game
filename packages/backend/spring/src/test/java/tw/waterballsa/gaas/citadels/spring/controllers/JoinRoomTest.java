package tw.waterballsa.gaas.citadels.spring.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MvcResult;
import tw.waterballsa.gaas.citadels.domain.Room;
import tw.waterballsa.gaas.citadels.domain.User;
import tw.waterballsa.gaas.citadels.spring.CitadelsSpringBootTest;
import tw.waterballsa.gaas.citadels.spring.controllers.viewmodel.JoinRoomView;
import tw.waterballsa.gaas.citadels.spring.controllers.viewmodel.RoomView;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

public class JoinRoomTest extends CitadelsSpringBootTest {

    @Test
    public void givenRoomNotFull_whenUserJoinRoom_joinRoomSuccess() throws Exception {

        // init room
        User userA = new User("A", "imageName1");
        User userB = new User("B", "imageName1");
        Room room = givenRoomStarted("room A", userA.getId(), userA, userB);

        // join room
        User C = new User("C", "imageName2");
        String jsonBody = "{\"userName\"  : \"" + C.getName()  + "\", " +
                           "\"userImage\" : \"" + C.getImageName() + "\"}";
        MvcResult mvcResult = mockMvc.perform(post(API_PREFIX + "/rooms/{roomId}:join", room.getId())
                        .contentType(APPLICATION_JSON)
                        .content(jsonBody))
                        .andExpect(status().isOk())
                        .andReturn();

        // check result: nums of player & each name, id
        Room actualRoom = findRoomById(room.getId());

        // transfer json to view model
        String content = mvcResult.getResponse().getContentAsString();
        ObjectMapper objectMapper = new ObjectMapper();
        JoinRoomView joinRoomView = objectMapper.readValue(content, JoinRoomView.class);

        // test response body
        User actualUserC = findUserByName(actualRoom.getId(), "C");
        assertEquals("OK", joinRoomView.getStatus());
        assertEquals("", joinRoomView.getMsg());
        assertEquals(actualUserC.getId(), joinRoomView.getJoinedUserId());

        // test room
        RoomView roomView = joinRoomView.getRoomView();
        assertEquals(actualRoom.getCreateTime().toString(), roomView.getCreateTime());
        assertEquals(actualRoom.getId(), roomView.getRoomId());
        assertEquals(actualRoom.getName(), roomView.getRoomName());
        assertEquals(actualRoom.findHolder().getId(), roomView.getHolderId());
        assertEquals(actualRoom.findHolder().getName(), roomView.getHolderName());
        assertEquals(actualRoom.getStatus().toString(), roomView.getStatus().toString());
        assertEquals(actualRoom.getUsers().size(), roomView.getTotalUsers());

        // test user
        assertEquals(3, actualRoom.getUsers().size());
        assertEquals(actualRoom.findUserById(userA.getId()), userA);
        assertEquals(actualRoom.findUserById(userB.getId()), userB);
        assertEquals(actualRoom.findUserById(actualUserC.getId()), actualUserC);
    }

    @Test
    public void givenRoomIsFull_whenUserJoinRoom_joinRoomFailure() throws Exception {

        User userA = new User("A", "imageName1");
        User userB = new User("B", "imageName1");
        User userC = new User("C", "imageName1");
        User userD = new User("D", "imageName1");
        User userE = new User("E", "imageName1");
        User userF = new User("F", "imageName1");
        User userG = new User("G", "imageName1");
        Room room = givenRoomStarted("room A", userA.getId(), userA, userB, userC, userD, userE, userF, userG);

        // join room
        User H = new User("H", "imageName1");
        String jsonBody = "{\"userName\"  : \"" + H.getName()  + "\", " +
                           "\"userImage\" : \"" + H.getImageName() + "\"}";
        mockMvc.perform(post(API_PREFIX + "/rooms/{gameId}:join", room.getId())
                        .contentType(APPLICATION_JSON)
                        .content(jsonBody))
                        .andExpect(status().isBadRequest())
                        .andExpect(content().json("{\"status\" :  \"FAIL\"," +
                                                   "\"msg\" :  \"GAME IS FULL\"}"));

        Room actualGame = findRoomById(room.getId());

        assertEquals(7, actualGame.getUsers().size());
        assertEquals(actualGame.findUserById(userA.getId()), userA);
        assertEquals(actualGame.findUserById(userB.getId()), userB);
        assertEquals(actualGame.findUserById(userC.getId()), userC);
        assertEquals(actualGame.findUserById(userD.getId()), userD);
        assertEquals(actualGame.findUserById(userE.getId()), userE);
        assertEquals(actualGame.findUserById(userF.getId()), userF);
        assertEquals(actualGame.findUserById(userG.getId()), userG);
    }
}
