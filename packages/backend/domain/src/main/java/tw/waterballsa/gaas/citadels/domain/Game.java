package tw.waterballsa.gaas.citadels.domain;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Game {
    private String id;
    private String gameName;
    private String holderName;
    private LocalDateTime createTime;
    private String status;
    private String message;
    private List<Player> players;

}
