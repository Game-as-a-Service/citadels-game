package tw.waterballsa.gaas.citadels.spring.controllers.viewmodel;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetSpecificRoomView {
    private String searchTime;
    private String status;
    private String msg;
    @JsonProperty("room")
    private RoomView roomView;
}
