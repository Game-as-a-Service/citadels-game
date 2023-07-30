package tw.waterballsa.gaas.citadels.spring;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import tw.waterballsa.gaas.citadels.app.repositories.CitadelsGameRepository;
import tw.waterballsa.gaas.citadels.spring.repositories.dao.CitadelsGameDAO;
import tw.waterballsa.gaas.citadels.spring.repositories.data.CitadelsGameData;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public abstract class CitadelsApplicationTest {
    @Autowired
    protected MockMvc mockMvc;
    @Autowired
    protected CitadelsGameDAO citadelsGameDAO;
    @Autowired
    protected ObjectMapper objectMapper;
    @Autowired
    protected CitadelsGameRepository citadelsGameRepository;

    @Test
    public void testMongoDB() {
        CitadelsGameData citadelsGameData = new CitadelsGameData();
        citadelsGameData = citadelsGameDAO.save(citadelsGameData);

        String gameId = citadelsGameData.getGameId();
        System.out.println("citadelsGameData id is " + gameId);
        Assertions.assertNotNull(citadelsGameDAO.findById(gameId));
    }

}
