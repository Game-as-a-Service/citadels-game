package tw.waterballsa.gaas.citadels.spring.controllers;

import lombok.AllArgsConstructor;
import lombok.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tw.waterballsa.gaas.citadels.app.usecases.CreateRoomUseCase;
import tw.waterballsa.gaas.citadels.domain.Room;
import tw.waterballsa.gaas.citadels.spring.controllers.viewmodel.CreateRoomView;

import javax.validation.constraints.NotNull;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.ResponseEntity.status;
import static tw.waterballsa.gaas.citadels.spring.controllers.viewmodel.CreateRoomView.toViewModel;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/api/citadels")
public class RoomController {
    private final CreateRoomUseCase createRoomUseCase;

    @PostMapping("/room")
    public ResponseEntity<CreateRoomView> createGame(@RequestBody CreateRoomRequest request) {
        CreateRoomPresenter presenter = new CreateRoomPresenter();
        createRoomUseCase.execute(request.toRequest(), presenter);
        return status(OK).body(presenter.getCreateRoomView());
    }

    static class CreateRoomPresenter implements CreateRoomUseCase.Presenter {
        private Room room;

        @Override
        public void setRoom(Room room) {
            this.room = room;
        }

        public CreateRoomView getCreateRoomView() {
            return toViewModel(room);
        }
    }

    @AllArgsConstructor
    @Value
    static class CreateRoomRequest {
        @NotNull(message = "The roomName must not be null.")
        String roomName;
        @NotNull(message = "The userName must not be null.")
        String userName;
        @NotNull(message = "The userImage must not be null.")
        String userImage;

        public CreateRoomUseCase.Request toRequest() {
            return new CreateRoomUseCase.Request(roomName, userName, userImage);
        }
    }
}
