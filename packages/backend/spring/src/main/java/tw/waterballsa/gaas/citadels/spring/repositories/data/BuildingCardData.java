package tw.waterballsa.gaas.citadels.spring.repositories.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import tw.waterballsa.gaas.citadels.domain.BuildingCard.BuildingCard;
import tw.waterballsa.gaas.citadels.domain.BuildingCard.BuildingCardFactory;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Document
@AllArgsConstructor
public class BuildingCardData {
    @Id
    private String name;
    private int coins;
    private BuildingCardFactory.Color color;

    public static BuildingCardData toData(BuildingCard buildingCard) {
        return new BuildingCardData(buildingCard.getName(), buildingCard.getCoins(), buildingCard.getColor());
    }

    public static List<BuildingCardData> toData(List<BuildingCard> buildingCards) {
        return buildingCards.stream().map(BuildingCardData::toData).collect(Collectors.toList());
    }

    public static BuildingCard toDomain(BuildingCardData buildingCardData) {
        return new BuildingCard(buildingCardData.getName(), buildingCardData.getCoins(), buildingCardData.getColor());
    }

    public static List<BuildingCard> toDomains(List<BuildingCardData> buildingCardDataList) {
        return buildingCardDataList.stream().map(BuildingCardData::toDomain).collect(Collectors.toList());
    }
}
