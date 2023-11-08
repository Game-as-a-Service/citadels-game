package tw.waterballsa.gaas.citadels.domain;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static java.util.UUID.randomUUID;
import static tw.waterballsa.gaas.citadels.domain.Game.Status.PLAYING;


@Getter
public class Game {
    private final String id;
    private final String roomId;
    private final String name;
    private final Status status;
    private final LocalDateTime createTime;
    private final List<Player> players;
    private final List<CharacterCard> characterCards;
    private final List<BuildCard> buildCards;
    public static final Integer DEFAULT_COINS = 2;
    public static final Integer DEFAULT_CARD_QUANTITY = 2;

    public Game(String roomId, String name, List<Player> players, List<CharacterCard> characterCards, List<BuildCard> buildCards) {
        this.id = randomUUID().toString();
        this.roomId = roomId;
        this.name = name;
        this.createTime = LocalDateTime.now();
        this.players = players;
        this.status = PLAYING;
        this.characterCards = characterCards;
        this.buildCards = buildCards;
    }

    public Game(String id, String roomId, String name, Status status,
                LocalDateTime createTime, List<Player> players, List<CharacterCard> characterCards,
                List<BuildCard> buildCards) {
        this.id = id;
        this.roomId = roomId;
        this.name = name;
        this.status = status;
        this.createTime = createTime;
        this.players = players;
        this.characterCards = characterCards;
        this.buildCards = buildCards;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getRoomId() {
        return roomId;
    }

    public Status getStatus() {
        return status;
    }

    public List<Player> getPlayers() {
        return players;
    }


    public LocalDateTime getCreateTime() {
        return createTime;
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

    public List<BuildCard> getTwoCards(){
        return Arrays.asList(new BuildCard("test",3, BuildCard.Color.BLUE)
                                    ,new BuildCard("test",2, BuildCard.Color.BLUE));
    }

    public enum Status {
        PLAYING,
        CLOSE
    }

    @Override
    public String toString() {
        return "Game{" +
                "id='" + id + '\'' +
                ", roomId='" + roomId + '\'' +
                ", name='" + name + '\'' +
                ", status=" + status +
                ", createTime=" + createTime +
                ", players=" + players +
                ", districtCards=" + buildCards +
                '}';
    }
}


