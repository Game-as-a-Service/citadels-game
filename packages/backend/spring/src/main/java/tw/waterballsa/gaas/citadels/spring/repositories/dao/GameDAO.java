package tw.waterballsa.gaas.citadels.spring.repositories.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import tw.waterballsa.gaas.citadels.domain.Game;
import tw.waterballsa.gaas.citadels.spring.repositories.data.GameData;
import tw.waterballsa.gaas.citadels.spring.repositories.data.RoomData;

@Repository
public interface GameDAO extends MongoRepository<GameData, String> {

}
