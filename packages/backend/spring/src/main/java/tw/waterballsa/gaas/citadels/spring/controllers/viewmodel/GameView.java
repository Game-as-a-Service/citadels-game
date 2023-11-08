package tw.waterballsa.gaas.citadels.spring.controllers.viewmodel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tw.waterballsa.gaas.citadels.domain.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GameView {
    private String id;
    private String roomId;
    private String name;
    private Game.Status status;
    private LocalDateTime createTime;
    private List<PlayerView> playerViews;
    private List<CharacterCardView> characterCardViews;
    private List<BuildCardView> buildCardViews;

    public static GameView toViewModel(Game game) {
        List<PlayerView> playerViews = PlayerView.toViewModels(game.getPlayers());
        List<CharacterCardView> characterCardViews = CharacterCardView.toViewModels(game.getCharacterCards());
        List<BuildCardView> buildCardViews = BuildCardView.toViewModels(game.getBuildCards());
        return new GameView(game.getId(), game.getRoomId(), game.getName(),game.getStatus(),game.getCreateTime(),playerViews,characterCardViews,buildCardViews);
    }
}