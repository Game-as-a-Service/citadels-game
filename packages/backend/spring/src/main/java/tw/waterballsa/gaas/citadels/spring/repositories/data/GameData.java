package tw.waterballsa.gaas.citadels.spring.repositories.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import tw.waterballsa.gaas.citadels.domain.CitadelsGame;

import java.util.List;

import static tw.waterballsa.gaas.citadels.spring.repositories.data.BuildingCardData.toDomains;
import static tw.waterballsa.gaas.citadels.spring.repositories.data.RoleCardData.toDomainList;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document("game")
public class GameData {
    @Id
    private String id;
    private List<PlayerData> players;
    private List<RoleCardData> roleCardsData;
    private List<BuildingCardData> buildingCardsData;

    public static GameData toData(CitadelsGame citadelsGame) {
        return GameData.builder().id(citadelsGame.getId())
                .players(PlayerData.toData(citadelsGame.getPlayers()))
                .roleCardsData(RoleCardData.toData(citadelsGame.getRoleCards()))
                .buildingCardsData(BuildingCardData.toData(citadelsGame.getBuildingCards()))
                .build();
    }

    public CitadelsGame toDomain() {
        return new CitadelsGame(id, PlayerData.toDomainList(players), toDomainList(roleCardsData), toDomains(buildingCardsData));
    }

}
