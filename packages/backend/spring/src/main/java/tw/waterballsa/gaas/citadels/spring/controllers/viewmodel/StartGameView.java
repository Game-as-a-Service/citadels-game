package tw.waterballsa.gaas.citadels.spring.controllers.viewmodel;

import lombok.AllArgsConstructor;
import lombok.Data;
import tw.waterballsa.gaas.citadels.domain.Game;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class StartGameView {
    private String createTime;
    private String status;
    private String msg;
    private String gameId;

    public static StartGameView toViewModel(Game game) {
        return new StartGameView(LocalDateTime.now().toString(), "OK", "", game.getId());
    }
}
