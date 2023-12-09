package tw.waterballsa.gaas.citadels.spring.repositories;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import tw.waterballsa.gaas.citadels.app.repositories.GameRepository;
import tw.waterballsa.gaas.citadels.domain.CitadelsGame;
import tw.waterballsa.gaas.citadels.spring.repositories.dao.GameDAO;
import tw.waterballsa.gaas.citadels.spring.repositories.data.GameData;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class GameRepositoryImpl implements GameRepository {

    private final GameDAO gameDAO;

    @Override
    public CitadelsGame createGame(CitadelsGame citadelsGame) {
        GameData data = GameData.toData(citadelsGame);
        return gameDAO.save(data).toDomain();
    }

    @Override
    public Optional<CitadelsGame> findGameById(String gameId) {
        return gameDAO.findById(gameId).map(GameData::toDomain);
    }
}
