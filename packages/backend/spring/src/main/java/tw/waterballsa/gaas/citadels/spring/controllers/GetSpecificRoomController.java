package tw.waterballsa.gaas.citadels.spring.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tw.waterballsa.gaas.citadels.app.usecases.GetSpecificRoomUseCase;
import tw.waterballsa.gaas.citadels.domain.Room;
import tw.waterballsa.gaas.citadels.spring.controllers.viewmodel.GetSpecificRoomView;
import tw.waterballsa.gaas.citadels.spring.controllers.viewmodel.RoomView;

import java.time.LocalDateTime;

import static org.springframework.http.ResponseEntity.status;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/api/citadels")
public class GetSpecificRoomController {

    private final GetSpecificRoomUseCase getSpecificRoomUseCase;

    @GetMapping("/rooms/{roomId}")
    public ResponseEntity<?> getSpecificRoom(@PathVariable String roomId) {
        GetSpecificRoomPresenter presenter = new GetSpecificRoomPresenter();
        getSpecificRoomUseCase.execute(roomId, presenter);
        return status(HttpStatus.OK).body(presenter.getSpecificRoomView());
    }

    class GetSpecificRoomPresenter implements GetSpecificRoomUseCase.Presenter {

        private Room room;

        @Override
        public void present(Room room) {
            this.room = room;
        }

        public GetSpecificRoomView getSpecificRoomView() {
            return new GetSpecificRoomView(LocalDateTime.now().toString(), "OK", "", RoomView.toViewModel(room));
        }
    }
}
