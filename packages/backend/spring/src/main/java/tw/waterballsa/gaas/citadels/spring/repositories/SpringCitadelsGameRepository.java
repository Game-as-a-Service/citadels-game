package tw.waterballsa.gaas.citadels.spring.repositories;

import org.springframework.stereotype.Component;
import tw.waterballsa.gaas.citadels.app.repositories.CitadelsGameRepository;
import tw.waterballsa.gaas.citadels.domain.Game;
import tw.waterballsa.gaas.citadels.spring.repositories.dao.CitadelsGameDAO;
import tw.waterballsa.gaas.citadels.spring.repositories.data.CitadelsGameData;

@Component
public class SpringCitadelsGameRepository implements CitadelsGameRepository {
    private CitadelsGameDAO citadelsGameDAO;

    public SpringCitadelsGameRepository(CitadelsGameDAO citadelsGameDAO) {
        this.citadelsGameDAO = citadelsGameDAO;
    }

    @Override
    public boolean existsByName(String name) {
        return citadelsGameDAO.existsByName(name);
    }

    @Override
    public Game save(Game game) {
        return citadelsGameDAO.save(CitadelsGameData.toData(game)).toEntity();
    }

    @Override
    public void deleteAll() {
        citadelsGameDAO.deleteAll();
    }
}
