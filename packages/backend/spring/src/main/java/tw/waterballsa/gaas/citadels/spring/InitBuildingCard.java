package tw.waterballsa.gaas.citadels.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import org.bson.Document;
import java.nio.file.Files;
import java.nio.file.Paths;

@Component
public class InitBuildingCard implements CommandLineRunner {

    private final MongoTemplate mongoTemplate;

    @Autowired
    public InitBuildingCard(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public void run(String... args) throws Exception {
        Resource resource = new ClassPathResource("BuildingCardInfo.json");
        String jsonContent = new String(Files.readAllBytes(Paths.get(resource.getURI())));
        Document document = Document.parse(jsonContent);
        mongoTemplate.save(document, "BuildingCards");
    }

}
