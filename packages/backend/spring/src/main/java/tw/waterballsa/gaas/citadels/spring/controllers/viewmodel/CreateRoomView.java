package tw.waterballsa.gaas.citadels.spring.controllers.viewmodel;

import lombok.AllArgsConstructor;
import lombok.Data;
import tw.waterballsa.gaas.citadels.domain.Room;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class CreateRoomView {
    private String createTime;
    private String status;
    private String msg;
    private RoomView room;

    public static CreateRoomView toViewModel(Room room) {
        RoomView roomView = RoomView.toViewModel(room);
        return new CreateRoomView(LocalDateTime.now().toString(), "OK", "", roomView);
    }
}
