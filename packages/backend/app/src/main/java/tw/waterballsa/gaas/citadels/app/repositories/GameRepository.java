package tw.waterballsa.gaas.citadels.app.repositories;

import tw.waterballsa.gaas.citadels.domain.Game;
import tw.waterballsa.gaas.citadels.domain.Room;

import java.util.Optional;

public interface GameRepository {

    Game createGame(Game game);

    Optional<Game> findGameById(String gameId);
}
