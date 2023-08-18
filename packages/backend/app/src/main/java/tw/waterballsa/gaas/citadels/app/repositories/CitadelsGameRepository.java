package tw.waterballsa.gaas.citadels.app.repositories;

import tw.waterballsa.gaas.citadels.domain.CitadelsGame;
import java.util.Optional;

public interface CitadelsGameRepository {
    Optional<CitadelsGame> save(CitadelsGame citadelsGame);
    Optional<CitadelsGame> findById(String gameId);
}
