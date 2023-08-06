package tw.waterballsa.gaas.citadels.spring.controllers;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tw.waterballsa.gaas.citadels.app.usecases.CreateGameUseCase;
import tw.waterballsa.gaas.citadels.domain.Game;
import tw.waterballsa.gaas.citadels.spring.controllers.viewmodel.GameView;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.ResponseEntity.noContent;
import static org.springframework.http.ResponseEntity.status;


@RestController
@RequestMapping(value = "/api/citadels")
@AllArgsConstructor
public class CitadelsGameController {
    private final CreateGameUseCase createGameUseCase;

    @PostMapping("/game")
    public ResponseEntity<Object> createGame(@RequestBody CreateGameUseCase.Request request) {
        CreateGamePresenter presenter = new CreateGamePresenter();
        createGameUseCase.execute(request, presenter);
        if (presenter.getGameView() != null){
            return status(OK).body(presenter.getGameView());
        }else{
            return noContent().build();
        }
    }

    @RequiredArgsConstructor
    class CreateGamePresenter implements CreateGameUseCase.Presenter{
        private Game game;
        @Override
        public void setGame(Game game) {
            this.game = game;
        }

        public GameView getGameView(){
            return GameView.toViewModel(game);
        }
    }


}
