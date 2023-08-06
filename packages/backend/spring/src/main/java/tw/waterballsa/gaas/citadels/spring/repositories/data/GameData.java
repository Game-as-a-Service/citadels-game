package tw.waterballsa.gaas.citadels.spring.repositories.data;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import tw.waterballsa.gaas.citadels.domain.Game;
import tw.waterballsa.gaas.citadels.domain.Player;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document("game")
public class GameData {
    @Id
    private String id;
    private String gameName;
    private String holderName;
    private LocalDateTime createTime;
    private String status;
    private String message;
    private List<PlayerData> players;

    public static GameData toData(Game game) {
        return GameData.builder().gameName(game.getGameName())
                                        .message(game.getMessage())
                                        .players(PlayerData.toDatas(game.getPlayers()))
                                        .status(game.getStatus())
                                        .holderName(game.getHolderName())
                                        .createTime(game.getCreateTime())
                                        .build();
    }

    public Game toEntity() {
        return Game.builder().id(id)
                             .gameName(gameName)
                             .holderName(holderName)
                             .message(message)
                             .status(status)
                             .createTime(createTime)
                             .players(PlayerData.toDomains(players))
                             .build();
    }

}
