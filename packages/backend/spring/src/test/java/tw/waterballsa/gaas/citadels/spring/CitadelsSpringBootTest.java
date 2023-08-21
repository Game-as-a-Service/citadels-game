package tw.waterballsa.gaas.citadels.spring;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import tw.waterballsa.gaas.citadels.app.repositories.RoomRepository;
import tw.waterballsa.gaas.citadels.domain.Room;
import tw.waterballsa.gaas.citadels.domain.User;
import tw.waterballsa.gaas.citadels.exceptions.NotFoundException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public abstract class CitadelsSpringBootTest {
    @Autowired
    protected MockMvc mockMvc;
    @Autowired
    protected tw.waterballsa.gaas.citadels.spring.repositories.dao.RoomDAO RoomDAO;
    @Autowired
    protected ObjectMapper objectMapper;
    @Autowired
    protected RoomRepository roomRepository;

    protected static final String API_PREFIX = "/api/citadels";

    @Test
    public void testMongoDB() {
//        GameData citadelsGameData = new GameData();
//        citadelsGameData = citadelsGameDAO.save(citadelsGameData);
//
//        String gameId = citadelsGameData.getId();
//        System.out.println("citadelsGameData id is " + gameId);
//        Assertions.assertNotNull(citadelsGameDAO.findById(gameId));
    }

    @SneakyThrows
    public <T> T fromJson(String json, Class<T> type) {
        return objectMapper.readValue(json, type);
    }

    @SneakyThrows
    public String toJson(Object obj) {
        return objectMapper.writeValueAsString(obj);
    }
    public <T> T toObject(byte[] content, Class<T> toValueType){
        return objectMapper.convertValue(content, toValueType);
    }

    protected <T> T getBody(ResultActions actions, Class<T> type) {
        return fromJson(getContentAsString(actions), type);
    }

    @SneakyThrows
    protected String getContentAsString(ResultActions actions) {
        return actions.andReturn()
                .getResponse()
                .getContentAsString();
    }

    protected Room givenRoomStarted(String name, String holderId, User... users) {
        return roomRepository.createRoom(new Room(name, holderId, Stream.of(users).collect(Collectors.toSet())));
    }

    protected Room findRoomById(String roomId) {
        Room room = roomRepository.findRoomById(roomId).orElseThrow(() -> new NotFoundException("CAN NOT FIND ROOM ID=" + roomId));
        return room;
    }

    protected User findUserByName(String roomId, String name) {
        Room room = findRoomById(roomId);
        return room.getUsers().stream()
                .filter(user1 -> name.equals(user1.getName()))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("CAN NOT FIND USER NAME=" + name));
    }
}
