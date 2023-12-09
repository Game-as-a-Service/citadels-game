package tw.waterballsa.gaas.citadels.app.repositories;

import tw.waterballsa.gaas.citadels.domain.CitadelsGame;

import java.util.Optional;

public interface GameRepository {

    CitadelsGame createGame(CitadelsGame citadelsGame);

    Optional<CitadelsGame> findGameById(String gameId);
}
