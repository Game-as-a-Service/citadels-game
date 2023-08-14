package tw.waterballsa.gaas.citadels.domain;

import lombok.AllArgsConstructor;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static java.util.UUID.randomUUID;
import static tw.waterballsa.gaas.citadels.domain.Room.Status.OPEN;

@AllArgsConstructor
public class Room {
    private String id;
    private String name;
    private String holderId;
    private Status status = OPEN;
    private LocalDateTime createTime;
    private Map<String, User> userIdToUser = new LinkedHashMap<>();

    public Room(String name, User holder) {
        this.id = randomUUID().toString();
        this.name = name;
        this.holderId = holder.getId();
        this.userIdToUser.put(holder.getId(), holder);
        this.createTime = LocalDateTime.now();
    }

    public User findHolder() {
        return findUserById(this.holderId);
    }

    public User findUserById(String userId) {
        return userIdToUser.get(userId);
    }

    public List<User> getUsers() {
        return List.copyOf(userIdToUser.values());
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Status getStatus() {
        return status;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public enum Status {
        OPEN, PLAYING
    }
}
