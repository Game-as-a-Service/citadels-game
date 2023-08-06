package tw.waterballsa.gaas.citadels.app.usecases;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tw.waterballsa.gaas.citadels.app.repositories.CitadelsGameRepository;
import tw.waterballsa.gaas.citadels.domain.Game;

import javax.inject.Named;
import java.util.Collections;


@Named
public class CreateGameUseCase {
    private final CitadelsGameRepository citadelsGameRepository;

    public CreateGameUseCase(CitadelsGameRepository citadelsGameRepository) {
        this.citadelsGameRepository = citadelsGameRepository;
    }

    public void execute(Request request, Presenter presenter){
        Game game = Game.builder().gameName(request.getGameName())
                                  .holderName(request.holderName)
                                  .players(Collections.emptyList())
                                  .build();
        Game save = citadelsGameRepository.save(game);
        presenter.setGame(save);
    }

    public interface Presenter {
        void setGame(Game game);
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Request {
        public String gameName;
        public String holderName;
    }
}
