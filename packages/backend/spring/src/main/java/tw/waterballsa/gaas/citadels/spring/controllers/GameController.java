package tw.waterballsa.gaas.citadels.spring.controllers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tw.waterballsa.gaas.citadels.app.usecases.GetGameUseCase;
import tw.waterballsa.gaas.citadels.app.usecases.GetSelectedRoleCardsUseCase;
import tw.waterballsa.gaas.citadels.app.usecases.StartGameUseCase;
import tw.waterballsa.gaas.citadels.domain.CitadelsGame;
import tw.waterballsa.gaas.citadels.domain.RoleCard;
import tw.waterballsa.gaas.citadels.spring.controllers.viewmodel.GetGameView;
import tw.waterballsa.gaas.citadels.spring.controllers.viewmodel.RoleCardView;
import tw.waterballsa.gaas.citadels.spring.controllers.viewmodel.StartGameView;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.ResponseEntity.status;
import static tw.waterballsa.gaas.citadels.app.usecases.StartGameUseCase.Presenter;
import static tw.waterballsa.gaas.citadels.app.usecases.StartGameUseCase.Request;
import static tw.waterballsa.gaas.citadels.spring.controllers.viewmodel.StartGameView.toViewModel;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/api/citadels")
public class GameController {

    private final StartGameUseCase startGameUseCase;
    private final GetGameUseCase getGameUseCase;
    private final GetSelectedRoleCardsUseCase getNotSelectedRoleCardsUseCase;

    @PostMapping("/games")
    public ResponseEntity<StartGameView> startGame(@RequestBody StartGameRequest request) {
        StartGamePresenter presenter = new StartGamePresenter();
        startGameUseCase.execute(request.toRequest(), presenter);
        return status(OK).body(presenter.getStartGameView());
    }

    @GetMapping("/games/{gameId}")
    public ResponseEntity<?> getGame(@PathVariable String gameId) {
        GetCitadelsGamePresenter presenter = new GetCitadelsGamePresenter();
        getGameUseCase.execute(gameId, presenter);
        return status(OK).body(presenter.getGameView());
    }

    @GetMapping("/games/{gameId}/rolecards")
    public ResponseEntity<?> getGameNotSelectedRoleCards(@PathVariable String gameId) {
        GetGameRoleCardsPresenter presenter = new GetGameRoleCardsPresenter();
        getNotSelectedRoleCardsUseCase.execute(new GetSelectedRoleCardsUseCase.Request(gameId), presenter);
        return status(OK).body(presenter.getRoleCards());
    }

    class GetCitadelsGamePresenter implements GetGameUseCase.Presenter {
        private CitadelsGame citadelsGame;

        public GetGameView getGameView() {
            return GetGameView.toViewModel(citadelsGame);
        }

        @Override
        public void setGame(CitadelsGame citadelsGame) {
            this.citadelsGame = citadelsGame;
        }
    }

    class GetGameRoleCardsPresenter implements GetSelectedRoleCardsUseCase.Presenter {
        private List<RoleCard> roleCards;

        @Override
        public void present(List<RoleCard> roleCards) {
            this.roleCards = roleCards;
        }

        public List<RoleCardView> getRoleCards() {
            return RoleCardView.toViewModels(this.roleCards);
        }
    }


    class StartGamePresenter implements Presenter {
        private CitadelsGame citadelsGame;

        @Override
        public void setGame(CitadelsGame citadelsGame) {
            this.citadelsGame = citadelsGame;
        }

        public StartGameView getStartGameView() {
            return toViewModel(citadelsGame);
        }
    }

    @AllArgsConstructor
    @Value
    static class StartGameRequest {
        @NotNull(message = "The roomId must not be null.")
        String roomId;
        @NotNull(message = "The roomRoom must not be null.")
        String roomName;
        @NotNull(message = "The holderId must not be null.")
        String holderId;
        List<GameController.UserRequest> players;

        public Request toRequest() {
            List<StartGameUseCase.UserRequest> userRequests = new ArrayList<>();
            players.forEach(player -> userRequests.add(new StartGameUseCase.UserRequest(player.getId(), player.name, player.getImageName())));
            return new Request(roomId, roomName, holderId, userRequests);
        }
    }

    @Data
    @AllArgsConstructor
    public static class UserRequest {
        public String id;
        public String name;
        public String imageName;
    }
}
