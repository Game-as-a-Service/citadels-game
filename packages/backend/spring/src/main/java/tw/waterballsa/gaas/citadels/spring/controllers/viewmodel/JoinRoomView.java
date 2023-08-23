package tw.waterballsa.gaas.citadels.spring.controllers.viewmodel;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JoinRoomView {
    private String joinTime;
    private String status;
    private String msg;
    private String joinedUserId;
    @JsonProperty("room")
    private RoomView roomView;
}
