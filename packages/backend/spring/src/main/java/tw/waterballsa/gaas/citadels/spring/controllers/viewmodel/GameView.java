package tw.waterballsa.gaas.citadels.spring.controllers.viewmodel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tw.waterballsa.gaas.citadels.domain.*;

import java.util.List;

import static tw.waterballsa.gaas.citadels.spring.controllers.viewmodel.PlayerView.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GameView {
    private String id;
    private List<PlayerView> playerViews;

    public static GameView toViewModel(CitadelsGame citadelsGame) {
        return new GameView(citadelsGame.getId(), toViewModels(citadelsGame.getPlayers()));
    }
}