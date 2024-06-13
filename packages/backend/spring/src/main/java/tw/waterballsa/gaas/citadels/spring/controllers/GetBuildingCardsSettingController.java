package tw.waterballsa.gaas.citadels.spring.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tw.waterballsa.gaas.citadels.spring.repositories.dao.BuildingCardDAO;
import tw.waterballsa.gaas.citadels.spring.repositories.data.BuildingCardSetting;

import java.util.List;

import static org.springframework.http.ResponseEntity.status;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/api/citadels")
public class GetBuildingCardsSettingController {

    private final BuildingCardDAO buildingCardDAO;

    @GetMapping(value = "/building-cards")
    public ResponseEntity<List<BuildingCardSetting>> getBuildingCardsSetting() {
        return status(HttpStatus.OK)
                .header("Content-Type", MediaType.APPLICATION_JSON_UTF8_VALUE)
                .body(buildingCardDAO.findAll());
    }
}
