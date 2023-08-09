package tw.waterballsa.gaas.citadels.app.usecases;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import tw.waterballsa.gaas.citadels.app.repositories.CitadelsGameRepository;
import tw.waterballsa.gaas.citadels.domain.Game;
import tw.waterballsa.gaas.citadels.domain.Player;

import javax.inject.Named;
import java.time.LocalDateTime;

import static java.util.Collections.singletonList;


@Named
@RequiredArgsConstructor
public class CreateGameUseCase {
    private final CitadelsGameRepository citadelsGameRepository;

    public void execute(Request request, Presenter presenter){
        Game game = createGame(request);
        presenter.setGame(citadelsGameRepository.save(game));
    }

    private static Game createGame(Request request) {
        Player holderPlayer = new Player(request.getHolderName(), request.getPlayerImage());
        return Game.builder().gameName(request.gameName)
                             .holderId(holderPlayer.getPlayerId())
                             .holderName(request.holderName)
                             .playerImage(request.playerImage)
                             .players(singletonList(holderPlayer))
                             .status(Game.Status.WAITING)
                             .createTime(LocalDateTime.now())
                             .build();
    }

    public interface Presenter {
        void setGame(Game game);
    }

    @Data
    @Builder
    @AllArgsConstructor
    public static class Request {
        public String gameName;
        public String holderName;
        public String playerImage;

    }
}
