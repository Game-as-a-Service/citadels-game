package tw.waterballsa.gaas.citadels.app.usecases;

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
        CitadelsGame game = findById(request.getGameId());
        Player player = new Player(request.getPlayerId(), request.getPlayerName());
        game.newPlayer(player);
        citadelsGameRepository.save(game);
    }

    private CitadelsGame findById(String gameId) {
        CitadelsGame game = citadelsGameRepository.findById(gameId);
        return game;
    }

    @Data
    public static class Request {
        private String playerId;
        private String playerName;
        private String gameId;
        public Request(String gameId, String playerName, String playerId) {
            this.gameId = gameId;
            this.playerName = playerName;
            this.playerId = playerId;
        }
    }

    @Data
    public static class Response {

    }
}
