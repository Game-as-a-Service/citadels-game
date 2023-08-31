package tw.waterballsa.gaas.citadels.spring.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.ResultActions;
import tw.waterballsa.gaas.citadels.domain.Room;
import tw.waterballsa.gaas.citadels.domain.User;
import tw.waterballsa.gaas.citadels.spring.CitadelsSpringBootTest;
import tw.waterballsa.gaas.citadels.spring.controllers.viewmodel.RoomView;
import tw.waterballsa.gaas.citadels.spring.controllers.viewmodel.GetSpecificRoomView;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class GetSpecificRoomTest extends CitadelsSpringBootTest {

    @Test
    public void UserAInRoomA_RequestsUpdateRoomStatus_ReceiveSuccess() throws Exception {
        // init room
        User userA = new User("A", "imageName1");
        User userB = new User("B", "imageName1");
        Room room = givenRoomStarted("room A", userA.getId(), userA, userB);
        ResultActions mvcResult = mockMvc.perform(get(API_PREFIX + "/rooms/{roomId}", room.getId()))
                .andExpect(status().isOk());
        GetSpecificRoomView getSpecificRoomView = getBody(mvcResult, GetSpecificRoomView.class);
        assertEquals("OK", getSpecificRoomView.getStatus());
        assertEquals("", getSpecificRoomView.getMsg());

        // test room
        RoomView roomView = getSpecificRoomView.getRoomView();
        Room actualRoom = findRoomById(room.getId());
        assertEquals(actualRoom.getCreateTime().toString(), roomView.getCreateTime());
        assertEquals(actualRoom.getId(), roomView.getRoomId());
        assertEquals(actualRoom.getName(), roomView.getRoomName());

        User holder = actualRoom.findHolder();
        assertEquals(holder.getId(), roomView.getHolderId());
        assertEquals(holder.getName(), roomView.getHolderName());
        assertEquals(actualRoom.getStatus().toString(), roomView.getStatus().toString());
        assertEquals(actualRoom.getUsers().size(), roomView.getTotalUsers());

        // test user
        assertEquals(2, actualRoom.getUsers().size());
        assertEquals(actualRoom.findUserById(userA.getId()), userA);
        assertEquals(actualRoom.findUserById(userB.getId()), userB);
    }
}
