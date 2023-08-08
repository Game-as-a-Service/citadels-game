package tw.waterballsa.gaas.citadels.app.usecases;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import tw.waterballsa.gaas.citadels.app.repositories.CitadelsGameRepository;
import tw.waterballsa.gaas.citadels.domain.CitadelsGame;
import tw.waterballsa.gaas.citadels.domain.Player;

import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Named
@RequiredArgsConstructor
public class JoinGameUsecase {

    private final CitadelsGameRepository citadelsGameRepository;

    public void execute(Request request, Presenter presenter) {
        CitadelsGame game = findGame(request);
        Player player = new Player(request.getPlayerName(), request.getPlayerImage());
        game.joinGame(player);
        citadelsGameRepository.save(game);
        presenter.present(game);
    }

    private CitadelsGame findGame(Request request) {
        Optional<CitadelsGame> game = citadelsGameRepository.findById(request.getGameId());
        CitadelsGame actualGame = game.orElseThrow(() -> new NullPointerException("CAN NOT FIND GAME, ID=" + request.getGameId()));
        return actualGame;
    }

    @Data
    @AllArgsConstructor
    public static class Request {
        private String gameId;
        private String playerName;
        private String playerImage;
    }

    public interface Presenter {
        void present(CitadelsGame game);
    }
}
