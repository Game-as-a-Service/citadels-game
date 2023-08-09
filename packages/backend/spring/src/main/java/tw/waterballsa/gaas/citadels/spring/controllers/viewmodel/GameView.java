package tw.waterballsa.gaas.citadels.spring.controllers.viewmodel;

import lombok.AllArgsConstructor;
import lombok.Data;
import tw.waterballsa.gaas.citadels.domain.Game;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
public class GameView {
    public String createTime;
    public String status;
    public String msg;
    public RoomView roomView;

    public static GameView toViewModel(Game game){
        List<PlayerView> playerViews = game.getPlayers().stream()
                                        .map(player -> new PlayerView(player.getPlayerId(), player.getPlayerName(), player.getPlayerImage()))
                                        .collect(Collectors.toList());

        RoomView roomView = new RoomView(game.getId(),game.getGameName(),
                                 game.getHolderId(),game.getHolderName(),
                                 game.getStatus(),game.getPlayers().size(),
                                 game.getCreateTime().toString(), playerViews);
        return new GameView(LocalDateTime.now().toString(),"OK","", roomView);
    }
}
