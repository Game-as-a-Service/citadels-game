package tw.waterballsa.gaas.citadels.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import tw.waterballsa.gaas.citadels.domain.BuildingCard.BuildingCard;

import java.util.*;


@Getter
@AllArgsConstructor
public class Player {
    private String id;
    private String name;
    private String imageName;
    private Integer coins;
    private List<BuildingCard> buildingCards;
    private RoleCard roleCard;
    private Boolean hasCrown;
    static final int DEFAULT_COIN = 0;

    public Player(String id, String name, String image) {
        this(id, name, image, DEFAULT_COIN, new ArrayList<>(), null, null);
    }

    public void removeCrown() {
        hasCrown = false;
    }

    public void acquireCrown() {
        hasCrown = true;
    }

    public void plusCoins(Integer coins) {
        this.coins = this.coins + coins;
    }

    public void plusCards(List<BuildingCard> buildingCardList) {
        buildingCards.addAll(buildingCardList);
    }
}