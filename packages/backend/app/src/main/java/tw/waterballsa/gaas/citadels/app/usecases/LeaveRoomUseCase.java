package tw.waterballsa.gaas.citadels.app.usecases;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import tw.waterballsa.gaas.citadels.app.outport.EventBus;
import tw.waterballsa.gaas.citadels.app.outport.RoomRepository;
import tw.waterballsa.gaas.citadels.domain.Room;
import tw.waterballsa.gaas.citadels.events.RoomEvent;
import tw.waterballsa.gaas.citadels.exceptions.NotFoundException;

import javax.inject.Named;
import java.util.List;

@Named
@RequiredArgsConstructor
public class LeaveRoomUseCase {

    private final RoomRepository roomRepository;

    private final EventBus eventBus;

    public void execute(Request request) {
        Room room = findRoomById(request.getRoomId());
        room.removeUserById(request.getUserId());
        if (room.isClose()) {
            roomRepository.deleteRoom(room.getId());
        } else {
            Room updatedRoom = roomRepository.updateRoom(room);
            eventBus.broadcast(List.of(new RoomEvent(updatedRoom)));
        }
    }

    private Room findRoomById(String roomId) {
        return roomRepository.findRoomById(roomId).orElseThrow(() -> new NotFoundException("CAN NOT FIND GAME, ID=" + roomId));
    }

    @Data
    @AllArgsConstructor
    public static class Request {
        private String roomId;
        private String userId;
    }
}
