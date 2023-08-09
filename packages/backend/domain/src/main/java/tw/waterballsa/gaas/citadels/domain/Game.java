package tw.waterballsa.gaas.citadels.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

import static tw.waterballsa.gaas.citadels.domain.Game.Status.WAITING;

@Data
@AllArgsConstructor
@Builder
public class Game {
    private String id;
    private String gameName;
    private String holderId;
    private String holderName;
    private String playerImage;
    private Status status = WAITING;
    private LocalDateTime createTime;
    private List<Player> players;

    public enum Status {
        WAITING, PLAYING
    }

}
