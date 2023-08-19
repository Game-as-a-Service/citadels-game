package tw.waterballsa.gaas.citadels.app.usecases;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import tw.waterballsa.gaas.citadels.app.repositories.RoomRepository;
import tw.waterballsa.gaas.citadels.domain.Room;
import tw.waterballsa.gaas.citadels.domain.User;

import javax.inject.Named;


@Named
@RequiredArgsConstructor
public class CreateRoomUseCase {
    private final RoomRepository roomRepository;

    public void execute(Request request, Presenter presenter){
        Room room = createRoom(request);
        presenter.setRoom(roomRepository.createRoom(room).orElse(null));
    }

    private Room createRoom(Request request) {
        User holder = new User(request.getUserName(), request.getUserImage());
        return new Room(request.roomName, holder);
    }

    public interface Presenter {
        void setRoom(Room room);
    }

    @Data
    @Builder
    @AllArgsConstructor
    public static class Request {
        public String roomName;
        public String userName;
        public String userImage;
    }

}
