package tw.waterballsa.gaas.citadels.spring.controllers.viewmodel;

import lombok.AllArgsConstructor;
import lombok.Data;
import tw.waterballsa.gaas.citadels.domain.BuildingCard;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
public class BuildingCardView {
    private String name;
    private int coins;
    private BuildingCard.Color color;

    public static List<BuildingCardView> toViewModels(List<BuildingCard> buildingCards) {
        return buildingCards.stream().map(BuildingCardView::toViewModel).collect(Collectors.toList());
    }

    public static BuildingCardView toViewModel(BuildingCard buildingCard) {
        return new BuildingCardView(buildingCard.getName(), buildingCard.getCoins(), buildingCard.getColor());
    }

}
