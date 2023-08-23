package tw.waterballsa.gaas.citadels.spring.controllers.viewmodel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tw.waterballsa.gaas.citadels.domain.Room;
import tw.waterballsa.gaas.citadels.domain.User;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomView {
    private String createTime;
    private String roomId;
    private String roomName;
    private String holderId;
    private String holderName;
    private List<UserView> users;
    private Room.Status status;
    private int totalUsers;

    public static RoomView toViewModel(Room room) {
        User holder = room.findHolder();
        List<UserView> usersView = UserView.toViewModel(room.getUsers());
        return new RoomView(room.getCreateTime().toString(), room.getId(), room.getName(), holder.getId(), holder.getName(), usersView, room.getStatus(), room.getUsers().size());
    }
}