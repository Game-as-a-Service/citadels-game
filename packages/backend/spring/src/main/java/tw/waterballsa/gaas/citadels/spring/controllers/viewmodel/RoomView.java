package tw.waterballsa.gaas.citadels.spring.controllers.viewmodel;

import lombok.AllArgsConstructor;
import lombok.Data;
import tw.waterballsa.gaas.citadels.domain.Game.Status;

import java.util.List;

@Data
@AllArgsConstructor
public class RoomView {
    public String gameId;
    public String gameName;
    public String holderId;
    public String holderName;
    public Status status;
    public Integer totalPlayers;
    public String creatTime;
    public List<PlayerView> players;

}
