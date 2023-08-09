package tw.waterballsa.gaas.citadels.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

import static java.lang.String.valueOf;

@Builder
@AllArgsConstructor
@Setter
@Getter
public class Player {
    private final String playerId;
    private final String playerName;
    private final String playerImage;

    public Player(String playerName, String playerImage) {
        this.playerId = generateUniqueId();
        this.playerName = playerName;
        this.playerImage = playerImage;
    }

    private String generateUniqueId() {
        return valueOf(UUID.randomUUID());
    }
}
