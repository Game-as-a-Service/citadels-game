package tw.waterballsa.gaas.citadels.spring.controllers;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import tw.waterballsa.gaas.citadels.domain.Room;
import tw.waterballsa.gaas.citadels.domain.User;
import tw.waterballsa.gaas.citadels.spring.CitadelsSpringBootTest;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class SSETest extends CitadelsSpringBootTest {

    @SneakyThrows
    @Test
    public void GiveSubscriptionRoomWhenJoinRoomThanGetNewMessage() {
        // init room
        User userA = new User("A", "imageName1");
        User userB = new User("B", "imageName1");
        Room room = givenRoomStarted("room A", userA.getId(), userA, userB);

        // 訂閱房間資訊
        String roomId = room.getId();
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/event/citadels/rooms/{roomId}", roomId)
                        .accept(MediaType.TEXT_EVENT_STREAM))
                .andExpect(status().isOk())
                .andReturn();


//        SseEmitter subscriptionEmitter = (SseEmitter) mvcResult.getAsyncResult();
//        CountDownLatch subscriptionLatch = new CountDownLatch(1);
//        subscriptionEmitter.onCompletion(subscriptionLatch::countDown);
//        assertTrue(subscriptionLatch.await(5, TimeUnit.SECONDS));

        // 等待异步结果
        String responseBody = mvcResult.getAsyncResult(60000).toString();
        System.out.println("responseBody:" + responseBody);
        assertNotNull(responseBody);

        // join room
        User userC = new User("C", "imageName2");
        String jsonBody = "{\"userName\"  : \"" + userC.getName() + "\", " +
                "\"userImage\" : \"" + userC.getImageName() + "\"}";
        ResultActions resultActions = mockMvc.perform(post(API_PREFIX + "/rooms/{roomId}:join", room.getId())
                        .contentType(APPLICATION_JSON)
                        .content(jsonBody))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }


    @SneakyThrows
    @Test
    public void GiveSubscriptionRoomWhenJoinRoomThanGetNewMessageA() {
        // init room
        User userA = new User("A", "imageName1");
        User userB = new User("B", "imageName1");
        Room room = givenRoomStarted("room A", userA.getId(), userA, userB);

        // 訂閱房間資訊
        String roomId = room.getId();
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/event/citadels/rooms/{roomId}", roomId)
                        .accept(MediaType.TEXT_EVENT_STREAM))
                .andExpect(status().isOk())
                .andReturn();

        SseEmitter subscriptionEmitter = (SseEmitter) mvcResult.getAsyncResult();
        CountDownLatch subscriptionLatch = new CountDownLatch(1);
        subscriptionEmitter.onCompletion(subscriptionLatch::countDown);
        assertTrue(subscriptionLatch.await(5, TimeUnit.SECONDS));

        // join room
        User userC = new User("C", "imageName2");
        String jsonBody = "{\"userName\"  : \"" + userC.getName() + "\", " +
                "\"userImage\" : \"" + userC.getImageName() + "\"}";
        ResultActions resultActions = mockMvc.perform(post(API_PREFIX + "/rooms/{roomId}:join", room.getId())
                        .contentType(APPLICATION_JSON)
                        .content(jsonBody))
                .andExpect(status().isOk());

        CountDownLatch latch = new CountDownLatch(5);

        subscriptionEmitter.onCompletion(latch::countDown);

        subscriptionEmitter.onTimeout(() -> {
            try {
                subscriptionEmitter.send(SseEmitter.event().data("Timeout event"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        assertTrue(latch.await(10, TimeUnit.SECONDS));

        String responseBody = mvcResult.getResponse().getContentAsString();
        System.out.println("responseBody: " + responseBody);

    }
}
