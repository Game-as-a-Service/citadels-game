package tw.waterballsa.gaas.citadels.spring.repositories;

import lombok.RequiredArgsConstructor;
import tw.waterballsa.gaas.citadels.domain.BuildingCard.BuildingCard;
import tw.waterballsa.gaas.citadels.domain.BuildingCard.BuildingCardFactory;
import tw.waterballsa.gaas.citadels.spring.repositories.dao.BuildingCardDAO;
import tw.waterballsa.gaas.citadels.spring.repositories.data.BuildingCardData;
import tw.waterballsa.gaas.citadels.spring.repositories.data.BuildingCardSetting;

import javax.inject.Named;
import java.util.List;
import java.util.Optional;

@Named
@RequiredArgsConstructor
public class BuildingCardFactoryImpl implements BuildingCardFactory {

    private final BuildingCardDAO buildingCardDAO;

    @Override
    public Optional<List<BuildingCard>> createBuildingCards() {
        return  Optional.of(BuildingCardSetting.toDomains(buildingCardDAO.findAll()));
    }
}
