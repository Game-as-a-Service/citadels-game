package tw.waterballsa.gaas.citadels.spring.controllers;

import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MvcResult;
import tw.waterballsa.gaas.citadels.spring.CitadelsSpringBootTest;
import tw.waterballsa.gaas.citadels.spring.repositories.dao.BuildingCardDAO;
import tw.waterballsa.gaas.citadels.spring.repositories.data.BuildingCardSetting;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class GetBuildingCardTest extends CitadelsSpringBootTest {

    @Autowired
    private BuildingCardDAO buildingCardDAO;

    @Test
    public void getBuildingCardTest() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get(API_PREFIX + "/building-cards")
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        List<BuildingCardSetting> buildingCardSettings = objectMapper.readValue(content, new TypeReference<List<BuildingCardSetting>>() {});
        List<BuildingCardSetting> expectedBuildingCardSetting = buildingCardDAO.findAll();
        assert buildingCardSettings.equals(expectedBuildingCardSetting);
    }
}
