package tw.waterballsa.gaas.citadels.spring.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MvcResult;
import tw.waterballsa.gaas.citadels.domain.Room;
import tw.waterballsa.gaas.citadels.domain.User;
import tw.waterballsa.gaas.citadels.spring.CitadelsApplicationTest;
import tw.waterballsa.gaas.citadels.spring.controllers.viewmodel.JoinRoomView;
import tw.waterballsa.gaas.citadels.spring.controllers.viewmodel.RoomView;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

public class JoinRoomTest extends CitadelsApplicationTest {

    @Test
    public void playerJoinsGameSuccessfully() throws Exception {

        // init room
        User A = new User("A", "imageName1");
        User B = new User("B", "imageName1");
        Room room = givenGameStarted("room A", A, A, B);

        // join room
        User C = new User("C", "imageName2");
        String jsonBody = "{\"userName\"  : \"" + C.getName()  + "\", " +
                           "\"userImage\" : \"" + C.getImage() + "\"}";
        MvcResult mvcResult = mockMvc.perform(post("/games/{roomId}:join", room.getId())
                        .contentType(APPLICATION_JSON)
                        .content(jsonBody))
                        .andExpect(status().isOk())
                        .andReturn();

        // check result: nums of player & each name, id
        Room actualRoom = findRoomById(room.getId());

        // test response body
        String content = mvcResult.getResponse().getContentAsString();
        System.out.println(content);
        ObjectMapper objectMapper = new ObjectMapper();
        JoinRoomView joinRoomView = objectMapper.readValue(content, JoinRoomView.class);

        // Question : join time 驗法
        // 可以驗收到 response 跟 join time 的時間間格不超過多少 ??
        assertEquals("OK", joinRoomView.getStatus());
        assertEquals("", joinRoomView.getMsg());

        RoomView roomView = joinRoomView.getRoomView();
        assertEquals(actualRoom.getCreateTime().toString(), roomView.getCreateTime());
        assertEquals(actualRoom.getId(), roomView.getRoomId());
        assertEquals(actualRoom.getName(), roomView.getRoomName());
        assertEquals(actualRoom.getHolder().getId(), roomView.getHolderId());
        assertEquals(actualRoom.getHolder().getName(), roomView.getHolderName());
        assertEquals(actualRoom.getStatus().toString(), roomView.getStatus());
        assertEquals(actualRoom.getUsers().size(), roomView.getTotalUsers());

        assertEquals(3, actualRoom.getUsers().size());
        assertTrue(actualRoom.findUserById(A.getId()).equals(A));
        assertTrue(actualRoom.findUserById(B.getId()).equals(B));
        // 怪怪的
        User actualUserC = findUserByName(actualRoom.getId(), "C");
        assertTrue(actualRoom.findUserById(actualUserC.getId()).equals(actualUserC));
    }

    @Test
    public void playerJoinsGameUnsuccessfully() throws Exception {

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
                           "\"userImage\" : \"" + H.getImage() + "\"}";
        mockMvc.perform(post("/games/{gameId}:join", room.getId())
                        .contentType(APPLICATION_JSON)
                        .content(jsonBody))
                        .andExpect(status().isBadRequest())
                        .andExpect(content().json("{\"status\" :  \"FAIL\"," +
                                                   "\"msg\" :  \"GAME IS FULL\"}"));

        Room actualGame = findRoomById(room.getId());

        assertEquals(7, actualGame.getUsers().size());
        assertTrue(actualGame.findUserById(A.getId()).equals(A));
        assertTrue(actualGame.findUserById(B.getId()).equals(B));
        assertTrue(actualGame.findUserById(C.getId()).equals(C));
        assertTrue(actualGame.findUserById(D.getId()).equals(D));
        assertTrue(actualGame.findUserById(E.getId()).equals(E));
        assertTrue(actualGame.findUserById(F.getId()).equals(F));
        assertTrue(actualGame.findUserById(G.getId()).equals(G));
    }
}
