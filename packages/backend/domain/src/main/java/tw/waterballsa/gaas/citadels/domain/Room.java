package tw.waterballsa.gaas.citadels.domain;

import lombok.AllArgsConstructor;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;

import lombok.NoArgsConstructor;
import tw.waterballsa.gaas.citadels.exceptions.JoinRoomException;
import tw.waterballsa.gaas.citadels.exceptions.NotFoundException;

import java.time.LocalDateTime;
import java.util.*;

import static java.util.UUID.randomUUID;
import static tw.waterballsa.gaas.citadels.domain.Room.Status.CLOSE;
import static tw.waterballsa.gaas.citadels.domain.Room.Status.OPEN;

@AllArgsConstructor
@NoArgsConstructor
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

    public Room(String name, String holderId, Set<User> userSet) {
        this.id = randomUUID().toString();
        this.name = name;
        this.holderId = holderId;
        this.status = OPEN;
        this.createTime = LocalDateTime.now();
        for (User user : userSet) {
            userIdToUser.put(user.getId(), user);
        }
    }

    public User findHolder() {
        return findUserById(this.holderId).orElseThrow(() -> new NotFoundException("HOLDER IS NOT FOUND"));
    }

    public boolean isClose() {
        return this.status.equals(CLOSE) || userIdToUser.isEmpty();
    }

    public Optional<User> findUserById(String userId) {
        return Optional.ofNullable(userIdToUser.get(userId));
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

    public Integer getTotalUser() {
        return userIdToUser.size();
    }

    public void joinUser(User user) {
        if (isFull()) {
            throw new JoinRoomException("GAME IS FULL");
        }
        userIdToUser.put(user.getId(), user);
    }

    public boolean isFull() {
        return userIdToUser.size() >= MAXIMUM_USERS;
    }

    public void removeUserById(String userId) {
        userIdToUser.remove(userId);
        if (userIdToUser.isEmpty()) {
            this.status = CLOSE;
            return;
        }
        assignNewHostIfCurrentHostLeave(userId);
    }

    private void assignNewHostIfCurrentHostLeave(String userId) {
        if (holderId.equals(userId)) {
            User newHolder = userIdToUser.values().stream().findAny().orElseThrow(() -> new NotFoundException("THE ROOM HAVE NOT ANY USERS"));
            this.holderId = newHolder.getId();
        }
    }

    public enum Status {
        OPEN,
        PLAYING,
        CLOSE
    }
}


