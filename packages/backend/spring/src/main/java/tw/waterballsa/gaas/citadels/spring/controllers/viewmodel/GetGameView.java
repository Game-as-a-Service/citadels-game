package tw.waterballsa.gaas.citadels.spring.controllers.viewmodel;

import lombok.AllArgsConstructor;
import lombok.Data;
import tw.waterballsa.gaas.citadels.domain.CitadelsGame;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class GetGameView {
    private String createTime;
    private String status;
    private String msg;
    private GameView gameView;

    public static GetGameView toViewModel(CitadelsGame citadelsGame) {
        return new GetGameView(LocalDateTime.now().toString(), "OK", "", GameView.toViewModel(citadelsGame));
    }
}
