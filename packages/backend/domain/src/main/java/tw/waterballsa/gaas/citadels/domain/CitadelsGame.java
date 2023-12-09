package tw.waterballsa.gaas.citadels.domain;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static java.util.UUID.randomUUID;

public class CitadelsGame {
    private final String id;
    private final List<Player> players;
    private final List<RoleCard> roleCards;
    private final List<BuildingCard> buildingCards;
    public static final Integer DEFAULT_COINS = 2;
    public static final Integer DEFAULT_CARD_QUANTITY = 2;

    public CitadelsGame(List<Player> players, List<RoleCard> roleCards, List<BuildingCard> buildingCards) {
        this.id = randomUUID().toString();
        this.players = players;
        this.roleCards = roleCards;
        this.buildingCards = buildingCards;
    }

    public CitadelsGame(String id, List<Player> players, List<RoleCard> roleCards, List<BuildingCard> buildingCards) {
        this.id = id;
        this.players = players;
        this.roleCards = roleCards;
        this.buildingCards = buildingCards;
    }

    public String getId() {
        return id;
    }

    public List<Player> getPlayers() {
        return List.copyOf(players);
    }

    public List<BuildingCard> getBuildingCards() {
        return List.copyOf(buildingCards);
    }

    public List<RoleCard> getRoleCards() {
        return List.copyOf(roleCards);
    }

    public void randomlyAwardCrownToOnePlayer() {
        Collections.shuffle(players);
        Player kingPlayer = players.get(0);
        kingPlayer.acquireCrown();
    }

    public void start() {
        randomlyAwardCrownToOnePlayer();
        distributingCardsAndCoinsToEachPlayer();
    }

    private void distributingCardsAndCoinsToEachPlayer() {
        players.forEach(player -> {
            player.plusCards(getTwoCards());
            player.plusCoins(2);
        });

    }

    public List<BuildingCard> getTwoCards() {
        return Arrays.asList(new BuildingCard("test", 3, BuildingCard.Color.BLUE), new BuildingCard("test", 2, BuildingCard.Color.BLUE));
    }

}
