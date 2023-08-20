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
    public void playerJoinsGameSuccessfully() throws Exception {

        // init room
        User A = new User("A", "imageName1");
        User B = new User("B", "imageName1");
        Room room = givenGameStarted("room A", A, A, B);

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
        assertEquals("OK", joinRoomView.getStatus());
        assertEquals("", joinRoomView.getMsg());

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
        assertEquals(actualRoom.findUserById(A.getId()), A);
        assertEquals(actualRoom.findUserById(B.getId()), B);
        User actualUserC = findUserByName(actualRoom.getId(), "C");
        assertEquals(actualRoom.findUserById(actualUserC.getId()), actualUserC);
    }

    @Test
    public void playerJoinsRoomUnsuccessfully() throws Exception {

        User A = new User("A", "imageName1");
        User B = new User("B", "imageName1");
        User C = new User("C", "imageName1");
        User D = new User("D", "imageName1");
        User E = new User("E", "imageName1");
        User F = new User("F", "imageName1");
        User G = new User("G", "imageName1");
        Room room = givenGameStarted("room A", A, A, B, C, D, E, F, G);

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
        assertEquals(actualGame.findUserById(A.getId()), A);
        assertEquals(actualGame.findUserById(B.getId()), B);
        assertEquals(actualGame.findUserById(C.getId()), C);
        assertEquals(actualGame.findUserById(D.getId()), D);
        assertEquals(actualGame.findUserById(E.getId()), E);
        assertEquals(actualGame.findUserById(F.getId()), F);
        assertEquals(actualGame.findUserById(G.getId()), G);
    }
}
