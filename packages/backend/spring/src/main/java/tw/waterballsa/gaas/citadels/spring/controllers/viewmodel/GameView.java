package tw.waterballsa.gaas.citadels.spring.controllers.viewmodel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tw.waterballsa.gaas.citadels.domain.CitadelsGame;
import tw.waterballsa.gaas.citadels.domain.Player;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GameView {
    private String gameId;
    private String gameName;
    private String holderId;
    private String holderName;
    private List<Player> players;
    private CitadelsGame.Status status;
    private LocalDateTime createTime;
    private int totalPlayers;

    public static GameView toViewModel(CitadelsGame game){
        return new GameView(game.getId(),
                game.getName(),
                game.getHolder().getId(),
                game.getHolder().getName(),
                game.getPlayers(),
                game.getStatus(),
                game.getCreateTime(),
                game.getPlayers().size());
    }
}