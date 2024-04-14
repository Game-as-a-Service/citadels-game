package tw.waterballsa.gaas.citadels.spring.repositories.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import tw.waterballsa.gaas.citadels.spring.repositories.data.BuildingCardData;
@Repository
public interface BuildingCardDAO extends MongoRepository<BuildingCardData, String> {
}
