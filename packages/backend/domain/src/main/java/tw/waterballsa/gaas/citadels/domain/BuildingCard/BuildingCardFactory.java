package tw.waterballsa.gaas.citadels.domain.BuildingCard;

import java.util.List;
import java.util.Optional;

public interface BuildingCardFactory {
    public Optional<List<BuildingCard>> createBuildingCards();
}


