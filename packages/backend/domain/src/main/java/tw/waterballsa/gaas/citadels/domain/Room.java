package tw.waterballsa.gaas.citadels.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

import static tw.waterballsa.gaas.citadels.domain.Room.Status.OPEN;

@Data
@AllArgsConstructor
@Builder
public class Room {
    private String id;
    private String name;
    private String holderId;

    private Status status = OPEN;
    private LocalDateTime createTime;
    private Map<String,User> users = new LinkedHashMap<>();

    public Room(String name,User user) {
        this.name = name;
        this.holderId = user.getId();
        this.users.put(user.getId(),user);
        this.createTime = LocalDateTime.now();
    }

    public enum Status {
        OPEN, PLAYING
    }

}
