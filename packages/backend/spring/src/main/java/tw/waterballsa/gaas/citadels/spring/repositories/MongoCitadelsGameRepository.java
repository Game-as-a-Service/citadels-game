package tw.waterballsa.gaas.citadels.spring.repositories;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;
import tw.waterballsa.gaas.citadels.app.repositories.CitadelsGameRepository;
import tw.waterballsa.gaas.citadels.domain.Game;
import tw.waterballsa.gaas.citadels.spring.repositories.data.CitadelsGameData;

@Component
public class MongoCitadelsGameRepository implements CitadelsGameRepository {
    private final MongoTemplate mongoTemplate;

    public MongoCitadelsGameRepository(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public boolean existsByName(String name) {
        return false;
    }

    @Override
    public Game save(Game game) {
        return mongoTemplate.save(CitadelsGameData.toData(game)).toEntity();
    }

    @Override
    public void deleteAll() {

    }
}
