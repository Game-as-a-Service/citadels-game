package tw.waterballsa.gaas.citadels.app.usecases;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import tw.waterballsa.gaas.citadels.app.repositories.RoomRepository;
import tw.waterballsa.gaas.citadels.domain.Room;
import tw.waterballsa.gaas.citadels.exceptions.NotFoundException;

import javax.inject.Named;
import java.util.Optional;

@Named
@RequiredArgsConstructor
public class LeaveRoomUseCase {

    private final RoomRepository roomRepository;

    public void execute(Request request) {
        Room room = findRoomById(request.getRoomId());
        room.removeUserById(request.getUserId());
        if (room.isClose()) {
            roomRepository.deleteRoom(room.getId());
        } else {
            roomRepository.updateRoom(room);
        }
    }

    private Room findRoomById(String roomId) {
        Optional<Room> room = roomRepository.findRoomById(roomId);
        return room.orElseThrow(() -> new NotFoundException("CAN NOT FIND GAME, ID=" + roomId));
    }

    @Data
    @AllArgsConstructor
    public static class Request {
        private String roomId;
        private String userId;
    }
}
