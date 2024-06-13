package tw.waterballsa.gaas.citadels.spring.repositories.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import tw.waterballsa.gaas.citadels.domain.BuildingCard.BuildingCard;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Document("buildingCards")
@AllArgsConstructor
@NoArgsConstructor
public class BuildingCardSetting {
    private String name;
    private int coins;
    private BuildingCard.Color color;
    private int quantity;
    private String description;
    private String image;

    public static List<BuildingCard> toDomain(BuildingCardSetting buildingCardSetting) {
        int quantity = buildingCardSetting.getQuantity();
        List<BuildingCard> buildingCards = new ArrayList<>();
        for (int i = 0; i < quantity; i++) {
            BuildingCard buildingCard = new BuildingCard(buildingCardSetting.getName(), buildingCardSetting.getCoins(), buildingCardSetting.getColor());
            buildingCards.add(buildingCard);
        }
        return buildingCards;
    }

    public static List<BuildingCard> toDomains(List<BuildingCardSetting> buildingCardSettings) {
        return buildingCardSettings.stream().
                flatMap(setting -> toDomain(setting).stream())
                .collect(Collectors.toList());
    }
}
