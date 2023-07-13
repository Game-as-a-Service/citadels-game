package tw.waterballsa.gaas.citadels.domain;

import java.util.List;
import java.util.UUID;

public class Player {
    private UUID uuid;
    private String avatar;
    private String name;
    private int joinSeq;
    private int coins;
    private Character identity;

    private List<AreaCard> handCards;
    private List<AreaCard> buildings;

    private Game game;

    // Getter and Setter methods
    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getJoinSeq() {
        return joinSeq;
    }

    public void setJoinSeq(int joinSeq) {
        this.joinSeq = joinSeq;
    }

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public Character getIdentity() {
        return identity;
    }

    public void setIdentity(Character identity) {
        this.identity = identity;
    }

    public List<AreaCard> getHandCards() {
        return handCards;
    }

    public void setHandCards(List<AreaCard> handCards) {
        this.handCards = handCards;
    }

    public List<AreaCard> getBuildings() {
        return buildings;
    }

    public void setBuildings(List<AreaCard> buildings) {
        this.buildings = buildings;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }
}
