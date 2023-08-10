package tw.waterballsa.gaas.citadels.spring.controllers.viewmodel;

import lombok.AllArgsConstructor;
import lombok.Data;
import tw.waterballsa.gaas.citadels.domain.Room;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class CreateRoomView {
    public String createTime;
    public String status;
    public String msg;
    public RoomView room;

    public static CreateRoomView toViewModel(Room room){
        RoomView roomView = RoomView.toViewModel(room);
        return new CreateRoomView(LocalDateTime.now().toString(),"OK","", roomView);
    }
}
