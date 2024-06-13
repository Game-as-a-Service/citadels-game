package tw.waterballsa.gaas.citadels.spring.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import tw.waterballsa.gaas.citadels.domain.BuildingCard.BuildingCard;
import tw.waterballsa.gaas.citadels.domain.BuildingCard.BuildingCardFactory;
import tw.waterballsa.gaas.citadels.spring.CitadelsSpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;

public class BuildingCardFactoryTest extends CitadelsSpringBootTest {
    @Autowired
    private BuildingCardFactory buildingCardFactory;

    @Test
    public void CreateBuildingCardTest() throws Exception {
        Optional<List<BuildingCard>> buildingCards = buildingCardFactory.createBuildingCards();
        assertTrue(isBuildingCardNumber(buildingCards.orElseThrow(), "TEMPLE", 3));
        assertTrue(isBuildingCardNumber(buildingCards.orElseThrow(), "CHURCH", 3));
        assertTrue(isBuildingCardNumber(buildingCards.orElseThrow(), "MONASTERY", 3));
        assertTrue(isBuildingCardNumber(buildingCards.orElseThrow(), "CATHEDRAL", 2));
        assertTrue(isBuildingCardNumber(buildingCards.orElseThrow(), "WATCHTOWER", 3));
        assertTrue(isBuildingCardNumber(buildingCards.orElseThrow(), "PRISON", 3));
        assertTrue(isBuildingCardNumber(buildingCards.orElseThrow(), "BATTLEFIELD", 3));
        assertTrue(isBuildingCardNumber(buildingCards.orElseThrow(), "FORTRESS", 2));
        assertTrue(isBuildingCardNumber(buildingCards.orElseThrow(), "MANOR", 5));
        assertTrue(isBuildingCardNumber(buildingCards.orElseThrow(), "CASTLE", 4));
        assertTrue(isBuildingCardNumber(buildingCards.orElseThrow(), "PALACE", 3));
        assertTrue(isBuildingCardNumber(buildingCards.orElseThrow(), "TAVERN", 5));
        assertTrue(isBuildingCardNumber(buildingCards.orElseThrow(), "INN", 3));
        assertTrue(isBuildingCardNumber(buildingCards.orElseThrow(), "MARKET", 4));
        assertTrue(isBuildingCardNumber(buildingCards.orElseThrow(), "BOATHOUSE", 3));
        assertTrue(isBuildingCardNumber(buildingCards.orElseThrow(), "HARBOR", 3));
        assertTrue(isBuildingCardNumber(buildingCards.orElseThrow(), "TOWN_HALL", 2));
        assertTrue(isBuildingCardNumber(buildingCards.orElseThrow(), "LIBRARY", 1));
        assertTrue(isBuildingCardNumber(buildingCards.orElseThrow(), "DRAGON_GATE", 1));
        assertTrue(isBuildingCardNumber(buildingCards.orElseThrow(), "GRAVEYARD", 1));
        assertTrue(isBuildingCardNumber(buildingCards.orElseThrow(), "GHOST_TOWN", 1));
        assertTrue(isBuildingCardNumber(buildingCards.orElseThrow(), "SCHOOL_OF_MAGIC", 1));
        assertTrue(isBuildingCardNumber(buildingCards.orElseThrow(), "LABORATORY", 1));
        assertTrue(isBuildingCardNumber(buildingCards.orElseThrow(), "SMITHY", 1));
        assertTrue(isBuildingCardNumber(buildingCards.orElseThrow(), "OBSERVATORY", 1));
        assertTrue(isBuildingCardNumber(buildingCards.orElseThrow(), "UNIVERSITY", 1));
        assertTrue(isBuildingCardNumber(buildingCards.orElseThrow(), "BASTION", 1));
        assertTrue(isBuildingCardNumber(buildingCards.orElseThrow(), "GREAT_WALL", 1));
    }

    private boolean isBuildingCardNumber(List<BuildingCard> buildingCards, String name, int total) {
        int number = 0;
        for(BuildingCard buildingCard : buildingCards) {
            if(name.equals(buildingCard.getName())) number++;
        }
        return number == total;
    }
}
