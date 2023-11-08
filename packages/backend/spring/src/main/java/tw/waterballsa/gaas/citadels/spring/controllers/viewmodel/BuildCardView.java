package tw.waterballsa.gaas.citadels.spring.controllers.viewmodel;

import lombok.AllArgsConstructor;
import lombok.Data;
import tw.waterballsa.gaas.citadels.domain.BuildCard;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
public class BuildCardView {
    private String name;
    private int coins;
    private BuildCard.Color color;

    public static List<BuildCardView> toViewModels(List<BuildCard> buildCards) {
        return buildCards.stream().map(BuildCardView::toViewModel).collect(Collectors.toList());
    }

    public static BuildCardView toViewModel(BuildCard buildCard) {
        return new BuildCardView(buildCard.getName(), buildCard.getCoins(), buildCard.getColor());
    }

}
