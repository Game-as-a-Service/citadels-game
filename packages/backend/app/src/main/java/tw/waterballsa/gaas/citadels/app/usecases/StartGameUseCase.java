package tw.waterballsa.gaas.citadels.app.usecases;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import tw.waterballsa.gaas.citadels.app.repositories.GameRepository;
import tw.waterballsa.gaas.citadels.domain.BuildingCard.BuildingCard;
import tw.waterballsa.gaas.citadels.domain.BuildingCard.BuildingCardFactory;
import tw.waterballsa.gaas.citadels.domain.RoleCard;
import tw.waterballsa.gaas.citadels.domain.CitadelsGame;
import tw.waterballsa.gaas.citadels.domain.Player;

import javax.inject.Named;
import java.util.*;

import static java.util.stream.Collectors.*;


@Named
@RequiredArgsConstructor
public class StartGameUseCase {

    private final GameRepository gameRepository;
    private final BuildingCardFactory factory;

    public void execute(Request request, Presenter presenter) {
        CitadelsGame citadelsGame = createGame(request);
        presenter.setGame(gameRepository.createGame(citadelsGame));
    }

    private CitadelsGame createGame(Request request) {
        List<Player> players = getPlayers(request.getPlayers());
        List<RoleCard> roleCards = getRoleCards();
        List<BuildingCard> buildingCards = factory.createBuildingCards().orElseThrow();
        CitadelsGame citadelsGame = new CitadelsGame(players, roleCards, buildingCards);
        citadelsGame.start();
        return citadelsGame;
    }

    private List<RoleCard> getRoleCards() {
        return Arrays.asList(new RoleCard(1, "刺客"),
                new RoleCard(2, "小偷"),
                new RoleCard(3, "魔術師"),
                new RoleCard(4, "國王"),
                new RoleCard(5, "住持"),
                new RoleCard(6, "商人"),
                new RoleCard(7, "建築師"),
                new RoleCard(8, "領主"));
    }

    private List<Player> getPlayers(List<UserRequest> playerIds) {
        return playerIds.stream()
                .map(user -> new Player(user.getId(), user.getName(), user.getImageName()))
                .collect(toList());
    }

    public interface Presenter {
        void setGame(CitadelsGame citadelsGame);
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
}
