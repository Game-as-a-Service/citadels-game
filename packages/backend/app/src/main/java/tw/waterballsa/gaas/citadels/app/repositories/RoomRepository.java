package tw.waterballsa.gaas.citadels.app.repositories;

import tw.waterballsa.gaas.citadels.domain.Room;

import java.util.List;
import java.util.Optional;

public interface RoomRepository {
    Room createRoom(Room room);
    Room updateRoom(Room room);
    Optional<Room> findRoomById(String gameId);
    void deleteRoom(String roomId);
    List<Room> getRooms();

    void deleteAll();
}
