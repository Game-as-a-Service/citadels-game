package tw.waterballsa.gaas.citadels.spring.repositories.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import tw.waterballsa.gaas.citadels.domain.Game;

import java.time.LocalDateTime;
import java.util.List;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document("game")
public class GameData {
    @Id
    private String id;
    private String roomId;
    private String name;
    private LocalDateTime createTime;
    private Game.Status status;
    private List<PlayerData> players;
    private String kingPlayerId;
    private List<CharacterCardData> characterCards;
    private List<BuildCardData> buildCards;

    public static GameData toData(Game game) {
        return GameData.builder().id(game.getId())
                .roomId(game.getRoomId())
                .name(game.getName())
                .players(PlayerData.toData(game.getPlayers()))
                .status(game.getStatus())
                .characterCards(CharacterCardData.toData(game.getCharacterCards()))
                .buildCards(BuildCardData.toData(game.getBuildCards()))
                .createTime(game.getCreateTime())
                .build();
    }

    public Game toDomain() {
        return new Game(id, roomId, name, status, createTime, PlayerData.toDomainList(players),
                        CharacterCardData.toDomainList(characterCards), BuildCardData.toDomains(buildCards));
    }

}
