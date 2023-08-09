package tw.waterballsa.gaas.citadels.spring.repositories;

import org.springframework.stereotype.Component;
import tw.waterballsa.gaas.citadels.app.repositories.CitadelsGameRepository;
import tw.waterballsa.gaas.citadels.domain.Game;
import tw.waterballsa.gaas.citadels.spring.repositories.dao.CitadelsGameDAO;
import tw.waterballsa.gaas.citadels.spring.repositories.data.GameData;

@Component
public class SpringCitadelsGameRepository implements CitadelsGameRepository {
    private final CitadelsGameDAO citadelsGameDAO;

    public SpringCitadelsGameRepository(CitadelsGameDAO citadelsGameDAO) {
        this.citadelsGameDAO = citadelsGameDAO;
    }

    @Override
    public Game save(Game game) {
        return citadelsGameDAO.save(GameData.toData(game)).toEntity();
    }

}
