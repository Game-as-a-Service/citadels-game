package tw.waterballsa.gaas.citadels.app.usecases;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import tw.waterballsa.gaas.citadels.app.repositories.CitadelsGameRepository;
import tw.waterballsa.gaas.citadels.domain.CitadelsGame;
import tw.waterballsa.gaas.citadels.domain.Player;
import tw.waterballsa.gaas.citadels.exceptions.PlatformException;

import javax.inject.Named;


@Named
@RequiredArgsConstructor
public class JoinGameUsecase {

    private final CitadelsGameRepository citadelsGameRepository;

    public void execute(Request request) {
        CitadelsGame game = findById(request.getGameId());
        Player player = new Player(request.getPlayerName());
        if(game.isFull()) {
            throw new PlatformException("GAME IS FULL");
        }
        game.joinGame(player);
        citadelsGameRepository.save(game);
    }

    private CitadelsGame findById(String gameId) {
        CitadelsGame game = citadelsGameRepository.findById(gameId);
        return game;
    }

    @Data
    public static class Request {
        private String playerName;
        private String gameId;
        public Request(String gameId, String playerName) {
            this.gameId = gameId;
            this.playerName = playerName;
        }
    }
}
