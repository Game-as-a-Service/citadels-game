package tw.waterballsa.gaas.citadels.app.usecases;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import tw.waterballsa.gaas.citadels.app.repositories.RoomRepository;
import tw.waterballsa.gaas.citadels.domain.Room;
import tw.waterballsa.gaas.citadels.domain.User;

import javax.inject.Named;
import java.util.Optional;


@Named
@RequiredArgsConstructor
public class JoinRoomUseCase {

    private final RoomRepository roomRepository;

    public void execute(Request request, Presenter presenter) {
        Room room = findRoomById(request.getRoomId());
        User user = new User(request.getUserName(), request.getUserImage());
        room.joinUser(user);
        roomRepository.createRoom(room);
        presenter.present(room);
    }

    private Room findRoomById(String roomId) {
        Optional<Room> room = roomRepository.findRoomById(roomId);
        return room.orElseThrow(() -> new NullPointerException("CAN NOT FIND GAME, ID=" + roomId));
    }

    @Data
    @AllArgsConstructor
    public static class Request {
        private String roomId;
        private String userName;
        private String userImage;
    }

    public interface Presenter {
        void present(Room room);
    }
}
