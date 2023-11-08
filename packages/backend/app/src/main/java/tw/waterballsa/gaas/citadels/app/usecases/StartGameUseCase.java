package tw.waterballsa.gaas.citadels.app.usecases;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import tw.waterballsa.gaas.citadels.app.repositories.GameRepository;
import tw.waterballsa.gaas.citadels.domain.BuildCard;
import tw.waterballsa.gaas.citadels.domain.CharacterCard;
import tw.waterballsa.gaas.citadels.domain.Game;
import tw.waterballsa.gaas.citadels.domain.Player;

import javax.inject.Named;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;


@Named
@RequiredArgsConstructor
public class StartGameUseCase {

    private final GameRepository gameRepository;

    public void execute(Request request, Presenter presenter) {
        Game game = createGame(request);
        presenter.setGame(gameRepository.createGame(game));
    }

    private Game createGame(Request request) {
        List<Player> players = getPlayers(request.getPlayers());
        List<CharacterCard> characterCards = getCharacterCards();
        List<BuildCard> buildCards = getBuildCards();
        Game game = new Game(request.getRoomId(), request.getRoomName(), players, characterCards, buildCards);
        game.start();
        return game;
    }

    private List<CharacterCard> getCharacterCards() {
        return Arrays.asList(new CharacterCard(1, "刺客"),
                new CharacterCard(2, "小偷"),
                new CharacterCard(3, "魔術師"),
                new CharacterCard(4, "國王"),
                new CharacterCard(5, "住持"),
                new CharacterCard(6, "商人"),
                new CharacterCard(7, "建築師"),
                new CharacterCard(8, "領主"));
    }

    private List<Player> getPlayers(List<UserRequest> playerIds) {
        return playerIds.stream()
                .map(user -> new Player(user.getId(), user.getName(), user.getImageName()))
                .collect(Collectors.toList());
    }

    public interface Presenter {
        void setGame(Game game);
    }

    @Data
    @Builder
    @AllArgsConstructor
    public static class Request {
        public String roomId;
        public String roomName;
        public String holderId;
        public List<UserRequest> players;

    }

    @Data
    @AllArgsConstructor
    public static class UserRequest {
        public String id;
        public String name;
        public String imageName;
    }

    private List<BuildCard> getBuildCards() {
        List<BuildCard> cards = new ArrayList<>();
        Stream.of(
                        getBuildCards(BuildCard.Color.YELLOW, 12),
                        getBuildCards(BuildCard.Color.BLUE, 11),
                        getBuildCards(BuildCard.Color.GREEN, 11),
                        getBuildCards(BuildCard.Color.RED, 11),
                        getBuildCards(BuildCard.Color.PURPLE, 30)
                )
                .flatMap(Collection::stream)
                .forEach(cards::add);

        return cards;
    }

    private List<BuildCard> getBuildCards(BuildCard.Color color, int count) {
        return IntStream.range(0, count)
                .mapToObj(i -> new BuildCard("card name", 2, color))
                .collect(Collectors.toList());
    }

}
