package tw.waterballsa.gaas.citadels.spring.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tw.waterballsa.gaas.citadels.app.usecases.GetRoomsUseCase;
import tw.waterballsa.gaas.citadels.domain.Room;
import tw.waterballsa.gaas.citadels.spring.controllers.viewmodel.GetRoomsView;
import tw.waterballsa.gaas.citadels.spring.util.ResponseStatus;

import java.time.LocalDateTime;
import java.util.List;

import static org.springframework.http.ResponseEntity.status;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/api/citadels")
public class GetRoomsController {

    private final GetRoomsUseCase getRoomsUseCase;

    @GetMapping("/rooms")
    public ResponseEntity<GetRoomsView> getRooms() {

        GetRoomsPresenter presenter = new GetRoomsPresenter();
        getRoomsUseCase.execute(presenter);
        return  status(HttpStatus.OK).body(presenter.getRoomsView());
    }

    class GetRoomsPresenter implements GetRoomsUseCase.Presenter {

        private List<Room> rooms;
        @Override
        public void present(List<Room> rooms) {
            this.rooms = rooms;
        }

        public GetRoomsView getRoomsView() {
            return new GetRoomsView(LocalDateTime.now(), rooms.size(), ResponseStatus.OK, "OK",
                    GetRoomsView.toViewModel(rooms).getRooms());
        }
    }
}