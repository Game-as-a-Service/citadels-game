package tw.waterballsa.gaas.citadels.spring;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import tw.waterballsa.gaas.citadels.domain.BuildingCard.BuildingCard;
import tw.waterballsa.gaas.citadels.domain.BuildingCard.BuildingCardFactory;
import tw.waterballsa.gaas.citadels.exceptions.NotFoundException;

import javax.inject.Named;
import java.io.File;
import java.util.Optional;

@Named
public class BuildingCardFactoryImpl implements BuildingCardFactory {
    @SneakyThrows
    @Override
    public Optional<BuildingCard> createBuildingCard(Type type) {
        String path = "src/main/resources/CardInfo.json";
        ObjectMapper mapper = new ObjectMapper();
        JsonNode nodes = mapper.readTree(new File(path));
        BuildingCard buildingCard = null;
        for(JsonNode node : nodes) {
            if(type == BuildingCardFactory.Type.fromString(node.get("name").asText()).orElseThrow()) {
                buildingCard = new BuildingCard(node.get("name").asText(), node.get("cost").asInt(), BuildingCardFactory.Color.fromString(node.get("color").asText()).orElseThrow(() -> new NotFoundException("NO SUCH COLOR, COLOR=" + node.get("color").asText())));
            }
        }
        return Optional.ofNullable(buildingCard);
    }
}
