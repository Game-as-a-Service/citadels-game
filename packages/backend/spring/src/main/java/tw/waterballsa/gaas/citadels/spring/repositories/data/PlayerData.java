package tw.waterballsa.gaas.citadels.spring.repositories.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import tw.waterballsa.gaas.citadels.domain.BuildingCard.BuildingCard;
import tw.waterballsa.gaas.citadels.domain.RoleCard;
import tw.waterballsa.gaas.citadels.domain.Player;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Data
@Document
@AllArgsConstructor
public class PlayerData {
    @Id
    private String id;
    private String name;
    private String imageName;
    private Integer coins;
    private List<BuildingCardData> buildingCardDataList;
    private RoleCardData characterCardData;
    private Boolean hasCrown;

    public static PlayerData toData(Player player) {
        return new PlayerData(player.getId(), player.getName(), player.getImageName(), player.getCoins(),
                BuildingCardData.toData(player.getBuildingCards()), RoleCardData.toData(player.getRoleCard()), player.getHasCrown());
    }

    public static List<PlayerData> toData(List<Player> players) {
        return players.stream().map(PlayerData::toData).collect(Collectors.toList());
    }

    public static Player toDomain(PlayerData playerData) {
        List<BuildingCard> buildingCards = BuildingCardData.toDomains(playerData.getBuildingCardDataList());
        RoleCard roleCard = RoleCardData.toDomain(playerData.getCharacterCardData());
        return new Player(playerData.getId(), playerData.getName(), playerData.getImageName(), playerData.getCoins(), buildingCards, roleCard, playerData.getHasCrown());
    }

    public static List<Player> toDomainList(List<PlayerData> playersDataList) {
        return playersDataList.stream().map(PlayerData::toDomain).collect(Collectors.toList());
    }

    public static Map<String, Player> toDomains(PlayerData... playersData) {
        return toDomains(Arrays.asList(playersData));
    }

    public static Map<String, Player> toDomains(List<PlayerData> playersData) {
        Map<String, Player> players = new LinkedHashMap<>();
        playersData.forEach(playerData -> players.put(playerData.getId(), toDomain(playerData)));
        return players;
    }

}
