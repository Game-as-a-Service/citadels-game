package tw.waterballsa.gaas.citadels.spring.controllers;

import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tw.waterballsa.gaas.citadels.app.usecases.JoinRoomUseCase;
import tw.waterballsa.gaas.citadels.domain.Room;
import tw.waterballsa.gaas.citadels.domain.User;
import tw.waterballsa.gaas.citadels.spring.controllers.viewmodel.JoinRoomView;
import tw.waterballsa.gaas.citadels.spring.controllers.viewmodel.RoomView;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

import static org.springframework.http.ResponseEntity.status;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/api/citadels")
public class JoinRoomController {

    private final JoinRoomUseCase joinRoomUsecase;

    @PostMapping("/rooms/{roomId}:join")
    public ResponseEntity<?> joinRoom(@PathVariable String roomId,
                                      @Valid @RequestBody JoinRoomRequest request) {
        JoinRoomPresenter present = new JoinRoomPresenter();
        joinRoomUsecase.execute(request.toRequest(roomId), present);
        return status(HttpStatus.OK).body(present.getJoinRoomView());
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
        private User joinedUser;

        @Override
        public void present(Room room, User joinedUser) {
            this.room = room;
            this.joinedUser = joinedUser;
        }

        public JoinRoomView getJoinRoomView() {
            return new JoinRoomView(LocalDateTime.now().toString(), "OK", "", joinedUser.getId(), RoomView.toViewModel(room));
        }
    }
}
