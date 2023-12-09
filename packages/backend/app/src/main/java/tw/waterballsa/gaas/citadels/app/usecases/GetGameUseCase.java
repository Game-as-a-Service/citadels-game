package tw.waterballsa.gaas.citadels.app.usecases;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import tw.waterballsa.gaas.citadels.app.repositories.GameRepository;
import tw.waterballsa.gaas.citadels.domain.CitadelsGame;
import tw.waterballsa.gaas.citadels.exceptions.NotFoundException;

import javax.inject.Named;
import java.util.Optional;

@Named
@RequiredArgsConstructor
public class GetGameUseCase {

    private final GameRepository gameRepository;

    public void execute(String gameId, Presenter presenter) {
        CitadelsGame citadelsGame = findGameById(gameId);
        presenter.setGame(citadelsGame);
    }

    private CitadelsGame findGameById(String gameId) {
        Optional<CitadelsGame> game = gameRepository.findGameById(gameId);
        return game.orElseThrow(() -> new NotFoundException("CAN NOT FIND GAME, ID=" + gameId));
    }

    @Data
    @AllArgsConstructor
    public static class Request {
        private String gameId;
    }

    public interface Presenter {
        void setGame(CitadelsGame citadelsGame);
    }
}
