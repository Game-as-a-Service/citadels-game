package tw.waterballsa.gaas.citadels.spring.repositories.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import tw.waterballsa.gaas.citadels.domain.BuildingCard.BuildingCard;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
public class BuildingCardData {
    private String name;
    private int coins;
    private BuildingCard.Color color;

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
