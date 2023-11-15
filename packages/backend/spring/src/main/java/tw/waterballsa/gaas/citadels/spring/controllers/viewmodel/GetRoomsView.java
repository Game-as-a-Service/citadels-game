package tw.waterballsa.gaas.citadels.spring.controllers.viewmodel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tw.waterballsa.gaas.citadels.domain.Room;
import tw.waterballsa.gaas.citadels.spring.util.ResponseStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetRoomsView {
    private LocalDateTime searchTime;
    private Integer totalRooms;
    private ResponseStatus status;
    private String msg;
    private List<RoomView> rooms = new ArrayList<>();

    public void addRoom(RoomView room) {
        rooms.add(room);
    }

    public static GetRoomsView toViewModel(List<Room> rooms) {
        GetRoomsView getRoomsView = new GetRoomsView();
        getRoomsView.setSearchTime(LocalDateTime.now());
        getRoomsView.setTotalRooms(rooms.size());
        getRoomsView.setStatus(ResponseStatus.OK);
        getRoomsView.setMsg("OK");
        for (Room room : rooms) {
            getRoomsView.addRoom(RoomView.toViewModel(room));
        }
        return getRoomsView;
    }
}
