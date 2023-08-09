package tw.waterballsa.gaas.citadels.spring.repositories.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import tw.waterballsa.gaas.citadels.domain.Game;
import tw.waterballsa.gaas.citadels.domain.Game.Status;

import java.time.LocalDateTime;
import java.util.List;

import static tw.waterballsa.gaas.citadels.spring.repositories.data.PlayerData.toDomains;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document("game")
public class GameData {
    @Id
    private String id;
    private String gameName;
    private String holderId;
    private String holderName;
    private LocalDateTime createTime;
    private Status status;
    private List<PlayerData> players;

    public static GameData toData(Game game) {
        return GameData.builder().gameName(game.getGameName())
                .players(PlayerData.toDatas(game.getPlayers()))
                .status(game.getStatus())
                .holderId(game.getHolderId())
                .holderName(game.getHolderName())
                .createTime(game.getCreateTime())
                .build();
    }

    public Game toEntity() {
        return Game.builder().id(id)
                .gameName(gameName)
                .holderName(holderName)
                .holderId(holderId)
                .status(status)
                .createTime(createTime)
                .players(toDomains(players))
                .build();
    }

}
