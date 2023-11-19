package tw.waterballsa.gaas.citadels.spring.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import tw.waterballsa.gaas.citadels.app.usecases.GetSpecificRoomUseCase;
import tw.waterballsa.gaas.citadels.domain.Room;
import tw.waterballsa.gaas.citadels.spring.controllers.viewmodel.GetSpecificRoomView;
import tw.waterballsa.gaas.citadels.spring.controllers.viewmodel.RoomView;
import tw.waterballsa.gaas.citadels.spring.outport.SSeEventBusService;

import java.time.LocalDateTime;

import static org.springframework.http.ResponseEntity.status;

@RestController
@AllArgsConstructor
public class GetSpecificRoomController {

    private final GetSpecificRoomUseCase getSpecificRoomUseCase;

    private final SSeEventBusService sseEventBusService;

    @GetMapping("/api/citadels/rooms/{roomId}")
    public ResponseEntity<GetSpecificRoomView> getSpecificRoom(@PathVariable String roomId) {
        GetSpecificRoomPresenter presenter = new GetSpecificRoomPresenter();
        getSpecificRoomUseCase.execute(roomId, presenter);
        return status(HttpStatus.OK).body(presenter.getSpecificRoomView());
    }

    @GetMapping("/event/citadels/rooms/{roomId}")
    public SseEmitter subScriptRoom(@PathVariable String roomId) {
        // subscript rooms events by room id
        // backend store emitter to roomIdToClientMap(roomId, List(client's SseEmitter))
        // when user add room or leave room then emit event room info to every client
        return sseEventBusService.subscript(roomId,  "room_event");
    }

    static class GetSpecificRoomPresenter implements GetSpecificRoomUseCase.Presenter {

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
