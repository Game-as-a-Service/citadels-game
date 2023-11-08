package tw.waterballsa.gaas.citadels.spring.repositories.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import tw.waterballsa.gaas.citadels.domain.BuildCard;
import tw.waterballsa.gaas.citadels.domain.CharacterCard;
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
    private List<BuildCardData> buildCardDataList;
    private CharacterCardData characterCardData;
    private Boolean hasCrown;

    public static PlayerData toData(Player player) {
        return new PlayerData(player.getId(), player.getName(), player.getImageName(), player.getCoins(),
                BuildCardData.toData(player.getBuildCards()), CharacterCardData.toData(player.getCharacterCard()), player.getHasCrown());
    }

    public static List<PlayerData> toData(List<Player> players) {
        return players.stream().map(PlayerData::toData).collect(Collectors.toList());
    }

    public static Player toDomain(PlayerData playerData) {
        List<BuildCard> buildCards = BuildCardData.toDomains(playerData.getBuildCardDataList());
        CharacterCard characterCard = CharacterCardData.toDomain(playerData.getCharacterCardData());
        return new Player(playerData.getId(), playerData.getName(), playerData.getImageName(), playerData.getCoins(), buildCards, characterCard, playerData.getHasCrown());
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
