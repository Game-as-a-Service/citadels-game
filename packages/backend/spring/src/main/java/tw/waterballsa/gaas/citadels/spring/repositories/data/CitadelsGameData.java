package tw.waterballsa.gaas.citadels.spring.repositories.data;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import tw.waterballsa.gaas.citadels.domain.Game;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document("game")
public class CitadelsGameData {
    @Id
    private String gameId;
    private String name;
    private String holderName;

    public static CitadelsGameData toData(Game game) {
        return CitadelsGameData.builder().name(game.getRoomName())
                .holderName(game.getHolderName())
                .build();
    }

    public Game toEntity() {
        return Game.builder().id(gameId)
                .roomName(name)
                .holderName(holderName)
                .build();
    }
}
