package tw.waterballsa.gaas.citadels.domain.BuildingCard;

import lombok.AllArgsConstructor;
import lombok.Data;
import tw.waterballsa.gaas.citadels.domain.BuildingCard.BuildingCardFactory.Color;


@Data
@AllArgsConstructor
public class BuildingCard {
    String name;
    int coins;
    Color color;
}
