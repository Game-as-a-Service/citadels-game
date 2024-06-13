package tw.waterballsa.gaas.citadels.app.usecases;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import tw.waterballsa.gaas.citadels.app.repositories.GameRepository;
import tw.waterballsa.gaas.citadels.domain.RoleCard;
import tw.waterballsa.gaas.citadels.exceptions.NotFoundException;

import javax.inject.Named;
import java.util.List;


@Named
@RequiredArgsConstructor
public class SelectRoleCardsUseCase {

    private final GameRepository gameRepository;

    public void execute(SelectRoleCardsUseCase.Request request, Presenter presenter) {
        var lessRoleCards = getNotSelectRoleCards(request.getGameId());
        presenter.present(lessRoleCards);
    }

    private List<RoleCard> getNotSelectRoleCards(String gameId) {
        var game = gameRepository.findGameById(gameId).orElseThrow(() -> new NotFoundException("CAN NOT FIND GAME, ID=" + gameId));
        var selections = game.getSelections();
        return selections.getNotSelected(game.getRoleCards());
    }

    @Data
    @AllArgsConstructor
    public static class Request {
        private String gameId;
        private String playerId;
        private String roleCardId;
    }

    public interface Presenter {
        void present(List<RoleCard> roleCards);
    }
}
