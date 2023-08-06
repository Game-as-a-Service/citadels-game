package tw.waterballsa.gaas.citadels.spring.repositories.data;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import tw.waterballsa.gaas.citadels.domain.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Document
@AllArgsConstructor
@NoArgsConstructor
public class PlayerData {
    @Id
    private String id;
    private String playerName;

    public static PlayerData toData(Player player) {
        return new PlayerData(player.getId(), player.getName());
    }

    public static List<PlayerData> toDatas(List<Player> players) {
        return players.stream().map(PlayerData::toData)
                               .collect(Collectors.toList());
    }

    public static Player toDomain(PlayerData playerData) {
        return  new Player(playerData.id,playerData.playerName);
    }

    public static List<Player> toDomains(List<PlayerData> players) {
        return players.stream().map(PlayerData::toDomain)
                                .collect(Collectors.toList());
    }
}
