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
public class JoinRoomUsecase {

    private final RoomRepository roomRepository;

    public void execute(Request request, Presenter presenter) {
        Room room = findGame(request);
        User user = new User(request.getUserName(), request.getUserImage());
        room.joinGame(user);
        roomRepository.save(room);
        presenter.present(room);
    }

    private Room findGame(Request request) {
        Optional<Room> room = roomRepository.findById(request.getRoomId());
        Room actualRoom = room.orElseThrow(() -> new NullPointerException("CAN NOT FIND GAME, ID=" + request.getRoomId()));
        return actualRoom;
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
