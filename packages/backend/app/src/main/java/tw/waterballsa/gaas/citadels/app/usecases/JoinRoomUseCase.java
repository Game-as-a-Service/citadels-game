package tw.waterballsa.gaas.citadels.app.usecases;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import tw.waterballsa.gaas.citadels.app.outport.EventBus;
import tw.waterballsa.gaas.citadels.app.outport.RoomRepository;
import tw.waterballsa.gaas.citadels.domain.Room;
import tw.waterballsa.gaas.citadels.domain.User;
import tw.waterballsa.gaas.citadels.events.RoomEvent;
import tw.waterballsa.gaas.citadels.exceptions.NotFoundException;

import javax.inject.Named;
import java.util.List;
import java.util.Optional;


@Named
@RequiredArgsConstructor
public class JoinRoomUseCase {

    private final RoomRepository roomRepository;

    private final EventBus eventBus;

    public void execute(Request request, Presenter presenter) {
        Room room = findRoomById(request.getRoomId());
        User user = new User(request.getUserName(), request.getUserImage());
        room.joinUser(user);
        Room updatedRoom = roomRepository.updateRoom(room);
        eventBus.broadcast(List.of(new RoomEvent(updatedRoom)));
        presenter.present(updatedRoom, user);
    }

    private Room findRoomById(String roomId) {
        Optional<Room> room = roomRepository.findRoomById(roomId);
        return room.orElseThrow(() -> new NotFoundException("CAN NOT FIND GAME, ID=" + roomId));
    }

    @Data
    @AllArgsConstructor
    public static class Request {
        private String roomId;
        private String userName;
        private String userImage;
    }

    public interface Presenter {
        void present(Room room, User joinedUser);
    }
}
