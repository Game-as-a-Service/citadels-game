package tw.waterballsa.gaas.citadels.spring.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tw.waterballsa.gaas.citadels.spring.repositories.dao.BuildingCardDAO;

import static org.springframework.http.ResponseEntity.status;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/api/citadels")
public class GetBuildingCardsSettingController {

    BuildingCardDAO buildingCardDAO;

    @GetMapping(value = "/building-cards")
    public ResponseEntity<?> getBuildingCardsSetting() {
        return status(HttpStatus.OK)
                .header("Content-Type", MediaType.APPLICATION_JSON_UTF8_VALUE)
                .body(buildingCardDAO.findAll());
    }
}
