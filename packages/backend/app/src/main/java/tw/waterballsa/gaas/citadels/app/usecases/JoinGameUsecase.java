package tw.waterballsa.gaas.citadels.app.usecases;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import tw.waterballsa.gaas.citadels.app.repositories.CitadelsGameRepository;
import tw.waterballsa.gaas.citadels.domain.CitadelsGame;
import tw.waterballsa.gaas.citadels.domain.Player;

import javax.inject.Named;


@Named
@RequiredArgsConstructor
public class JoinGameUsecase {

    private final CitadelsGameRepository citadelsGameRepository;

    public void execute(Request request) {
        CitadelsGame game = findGameById(request.getGameId());
        Player player = new Player(request.getPlayerName(), request.getPlayerImage());
        game.joinGame(player);
        citadelsGameRepository.save(game);
    }

    private CitadelsGame findGameById(String gameId) {
        CitadelsGame game = citadelsGameRepository.findById(gameId);
        return game;
    }

    @Data
    @AllArgsConstructor
    public static class Request {
        private String gameId;
        private String playerName;
        private String playerImage;
    }
}
