package tw.waterballsa.gaas.citadels.app.usecases;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import tw.waterballsa.gaas.citadels.app.outport.RoomRepository;
import tw.waterballsa.gaas.citadels.domain.Room;
import tw.waterballsa.gaas.citadels.exceptions.NotFoundException;

import javax.inject.Named;
import java.util.Optional;

@Named
@RequiredArgsConstructor
public class GetSpecificRoomUseCase {
    private final RoomRepository roomRepository;
    public void execute(String roomId, Presenter presenter) {
        Room room = findRoomById(roomId);
        presenter.present(room);
    }

    private Room findRoomById(String roomId) {
        Optional<Room> room = roomRepository.findRoomById(roomId);
        return room.orElseThrow(() -> new NotFoundException("CAN NOT FIND GAME, ID=" + roomId));
    }

    @Data
    @AllArgsConstructor
    public static class Request {
        private String roomId;
    }

    public interface Presenter{
        void present(Room room);
    }
}
