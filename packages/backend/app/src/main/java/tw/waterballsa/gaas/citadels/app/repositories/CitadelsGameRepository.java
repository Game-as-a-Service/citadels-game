package tw.waterballsa.gaas.citadels.app.repositories;

import tw.waterballsa.gaas.citadels.domain.Game;

public interface CitadelsGameRepository {

    boolean existsByName(String name);

    Game save(Game game);

    void deleteAll();

}
