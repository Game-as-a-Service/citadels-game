package tw.waterballsa.gaas.citadels.app.repositories;

import tw.waterballsa.gaas.citadels.domain.Room;
import java.util.Optional;

public interface RoomRepository {
    Optional<Room> createRoom(Room room);
    Optional<Room> findRoomById(String gameId);
}
