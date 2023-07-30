package tw.waterballsa.gaas.citadels.spring.repositories.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import tw.waterballsa.gaas.citadels.spring.repositories.data.CitadelsGameData;
@Repository
public interface CitadelsGameDAO extends MongoRepository<CitadelsGameData, String> {
    Boolean existsByName(String gameId);

}
