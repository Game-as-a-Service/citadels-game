package tw.waterballsa.gaas.citadels.spring.repositories;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import tw.waterballsa.gaas.citadels.app.repositories.GameRepository;
import tw.waterballsa.gaas.citadels.app.repositories.RoomRepository;
import tw.waterballsa.gaas.citadels.domain.Game;
import tw.waterballsa.gaas.citadels.domain.Room;
import tw.waterballsa.gaas.citadels.spring.repositories.dao.GameDAO;
import tw.waterballsa.gaas.citadels.spring.repositories.dao.RoomDAO;
import tw.waterballsa.gaas.citadels.spring.repositories.data.GameData;
import tw.waterballsa.gaas.citadels.spring.repositories.data.RoomData;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class GameRepositoryImpl implements GameRepository {

    private final GameDAO gameDAO;

    @Override
    public Game createGame(Game game) {
        GameData data = GameData.toData(game);
        return gameDAO.save(data).toDomain();
    }

    @Override
    public Optional<Game> findGameById(String gameId) {
        return gameDAO.findById(gameId).map(GameData::toDomain);
    }
}
