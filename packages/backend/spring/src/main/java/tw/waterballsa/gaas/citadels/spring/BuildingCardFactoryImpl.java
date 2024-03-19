package tw.waterballsa.gaas.citadels.spring;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import tw.waterballsa.gaas.citadels.domain.BuildingCard.BuildingCard;
import tw.waterballsa.gaas.citadels.domain.BuildingCard.BuildingCardFactory;

import javax.inject.Named;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Named
public class BuildingCardFactoryImpl implements BuildingCardFactory {
    @SneakyThrows
    @Override
    public Optional<List<BuildingCard>> createBuildingCards() {
        List<BuildingCard> buildingCards = new ArrayList<>();
        Resource resource = new ClassPathResource("BuildingCardInfo.json");
        File file = resource.getFile();
        ObjectMapper mapper = new ObjectMapper();
        JsonNode nodes = mapper.readTree(file);
        for(JsonNode node : nodes) {
            for(int i = 0; i < node.get("number").asInt(); i++) {
                buildingCards.add(new BuildingCard(node.get("name").asText(), node.get("cost").asInt(), BuildingCard.Color.fromString(node.get("color").asText()).orElseThrow()));
            }
        }
        return Optional.ofNullable(buildingCards);
    }
}
