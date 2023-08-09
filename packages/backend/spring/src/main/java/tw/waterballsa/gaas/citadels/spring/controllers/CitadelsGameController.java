package tw.waterballsa.gaas.citadels.spring.controllers;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tw.waterballsa.gaas.citadels.app.usecases.CreateGameUseCase;
import tw.waterballsa.gaas.citadels.domain.Game;
import tw.waterballsa.gaas.citadels.spring.controllers.viewmodel.GameView;

import javax.validation.constraints.NotNull;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.ResponseEntity.status;
import static tw.waterballsa.gaas.citadels.spring.controllers.viewmodel.GameView.toViewModel;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/api/citadels")
public class CitadelsGameController {
    private final CreateGameUseCase createGameUseCase;

    @PostMapping("/game")
    public ResponseEntity<GameView> createGame(@RequestBody CreateGameRequest request) {
        CreateGamePresenter presenter = new CreateGamePresenter();
        createGameUseCase.execute(request.toRequest(), presenter);
        return status(OK).body(presenter.getGameView());
    }

    @RequiredArgsConstructor
    class CreateGamePresenter implements CreateGameUseCase.Presenter {
        private Game game;

        @Override
        public void setGame(Game game) {
            this.game = game;
        }

        public GameView getGameView() {
            return toViewModel(game);
        }
    }

    @AllArgsConstructor
    @Value
    static class CreateGameRequest {
        @NotNull(message = "The gameName must not be null.")
        String gameName;
        @NotNull(message = "The holderName must not be null.")
        String holderName;
        @NotNull(message = "The playerImage must not be null.")
        String playerImage;

        public CreateGameUseCase.Request toRequest() {
            return new CreateGameUseCase.Request(gameName, holderName, playerImage);
        }
    }

}
