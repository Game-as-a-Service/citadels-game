package tw.waterballsa.gaas.citadels.domain;

import lombok.AllArgsConstructor;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import tw.waterballsa.gaas.citadels.exceptions.JoinRoomException;
import java.util.List;
import java.time.*;
import java.util.Map;
import java.util.stream.Collectors;

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
    private final int MAXIMUM_USERS = 7;

    public Room(String name, User holder) {
        this.id = randomUUID().toString();
        this.name = name;
        this.holderId = holder.getId();
        this.userIdToUser.put(holder.getId(), holder);
        this.createTime = LocalDateTime.now();
    }

    public Room(String name, User holder, List<User> users) {
        this.id = randomUUID().toString();
        this.name = name;
        this.holderId = holder.getId();
        this.status = OPEN;
        this.createTime = LocalDateTime.now();
        for(User user : users) {
            userIdToUser.put(user.getId(), user);
        }
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

    public void joinUser(User user) {
        if(isFull()) {
            throw new JoinRoomException("GAME IS FULL");
        }
        userIdToUser.put(user.getId(), user);
    }

    public boolean isFull() {
        return userIdToUser.size() >= MAXIMUM_USERS;
    }

    public enum Status {
        OPEN,
        PLAYING,
        CLOSE
    }
}


