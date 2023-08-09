package tw.waterballsa.gaas.citadels.spring.repositories.data;


import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import tw.waterballsa.gaas.citadels.domain.Player;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Document
@AllArgsConstructor
public class PlayerData {
    @Id
    private String id;
    private String playerName;
    private String playerImage;

    public static PlayerData toData(Player player) {
        return new PlayerData(player.getPlayerId(), player.getPlayerName(), player.getPlayerImage());
    }

    public static List<PlayerData> toDatas(List<Player> players) {
        return players.stream().map(PlayerData::toData)
                               .collect(Collectors.toList());
    }

    public static Player toDomain(PlayerData playerData) {
        return new Player(playerData.getId(),playerData.getPlayerName(), playerData.getPlayerImage());
    }

    public static List<Player> toDomains(List<PlayerData> players) {
        return players.stream().map(PlayerData::toDomain)
                                .collect(Collectors.toList());
    }
}
