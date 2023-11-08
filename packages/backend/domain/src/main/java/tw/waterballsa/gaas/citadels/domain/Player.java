package tw.waterballsa.gaas.citadels.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.util.*;


@Getter
@AllArgsConstructor
public class Player {
    private String id;
    private String name;
    private String imageName;
    private Integer coins;
    private List<BuildCard> buildCards;
    private CharacterCard characterCard;
    private Boolean hasCrown;
    static final int DEFAULT_COIN = 0;

    public Player(String id, String name, String image) {
        this(id, name, image, DEFAULT_COIN, new ArrayList<>(), null, null);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Player user = (Player) obj;
        return Objects.equals(id, user.id) &&
                Objects.equals(name, user.name) &&
                Objects.equals(imageName, user.imageName);
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

    public void plusCards(List<BuildCard> buildCardList) {
        buildCards.addAll(buildCardList);
    }
}