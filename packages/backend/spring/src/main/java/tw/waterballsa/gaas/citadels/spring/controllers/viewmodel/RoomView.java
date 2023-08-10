package tw.waterballsa.gaas.citadels.spring.controllers.viewmodel;

import lombok.AllArgsConstructor;
import lombok.Data;
import tw.waterballsa.gaas.citadels.domain.Room;
import tw.waterballsa.gaas.citadels.domain.User;

import java.util.List;

import static java.lang.String.valueOf;
import static java.util.List.of;

@Data
@AllArgsConstructor
public class RoomView {
     String roomId;
     String roomName;
     String holderId;
     String holderName;
     List<UserView> users;
     Room.Status status;
     String totalUsers;

    public static RoomView toViewModel(Room room) {
        User holder = room.getUsers().get(room.getHolderId());
        List<UserView> plainViews = of(UserView.toViewModel(holder));
        return new RoomView(room.getId(),room.getName(), holder.getId(),holder.getName(), plainViews,room.getStatus(), valueOf(room.getUsers().size()));
    }
}
