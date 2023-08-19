<<<<<<<< HEAD:packages/backend/spring/src/main/java/tw/waterballsa/gaas/citadels/spring/controllers/CitadelsGameController.java
========
package tw.waterballsa.gaas.citadels.spring.controllers;

import lombok.RequiredArgsConstructor;
import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tw.waterballsa.gaas.citadels.app.usecases.JoinRoomUseCase;
import tw.waterballsa.gaas.citadels.domain.Room;
import tw.waterballsa.gaas.citadels.spring.controllers.viewmodel.JoinRoomView;
import tw.waterballsa.gaas.citadels.spring.controllers.viewmodel.RoomView;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

import static org.springframework.http.ResponseEntity.status;

@RestController
@RequiredArgsConstructor
public class RoomController {

    private final JoinRoomUseCase joinRoomUsecase;

    @PostMapping("/games/{roomId}:join")
    public ResponseEntity<?> joinRoom(@PathVariable String roomId,
                                      @Valid @RequestBody JoinRoomRequest request) {
        var present = new JoinRoomPresenter();
        joinRoomUsecase.execute(request.toRequest(roomId), present);
        return status(HttpStatus.OK).body(present.getViewModel());
    }

    @Value
    public static class JoinRoomRequest {

        @NotBlank
        private String userName;

        @NotBlank
        private String userImage;

        public JoinRoomUseCase.Request toRequest(String roomId) {
            return new JoinRoomUseCase.Request(roomId, userName, userImage);
        }
    }

    class JoinRoomPresenter implements JoinRoomUseCase.Presenter {

        private Room room;

        @Override
        public void present(Room room) {
            this.room = room;
        }

        public JoinRoomView getViewModel() {
            return new JoinRoomView(LocalDateTime.now().toString(), "OK", "", RoomView.toViewModel(room));
        }
    }
}
>>>>>>>> ee19b2a (Rename RoomController JoinRoomUseCase):packages/backend/spring/src/main/java/tw/waterballsa/gaas/citadels/spring/controllers/RoomController.java
