package tw.waterballsa.gaas.citadels.spring;

import org.bson.BsonArray;
import org.bson.BsonValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import org.bson.Document;
import java.nio.file.Files;
import java.nio.file.Paths;

@Component
public class InitBuildingCards implements CommandLineRunner {

    private final MongoTemplate mongoTemplate;

    @Autowired
    public InitBuildingCards(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public void run(String... args) throws Exception {
        if(mongoTemplate.collectionExists("buildingCards")) {
            return;
        }
        ClassPathResource resource = new ClassPathResource("BuildingCardInfo.json");
        String jsonContent = new String(Files.readAllBytes(Paths.get(resource.getURI())));
        BsonArray bsonArray = BsonArray.parse(jsonContent);
        for(BsonValue bsonValue : bsonArray) {
            Document doc = Document.parse(bsonValue.asDocument().toJson());
            mongoTemplate.save(doc, "buildingCards");
        }
    }
}
