package tw.waterballsa.gaas.citadels.spring.repositories;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import tw.waterballsa.gaas.citadels.app.repositories.RoomRepository;
import tw.waterballsa.gaas.citadels.domain.Room;
import tw.waterballsa.gaas.citadels.spring.repositories.dao.RoomDAO;
import tw.waterballsa.gaas.citadels.spring.repositories.data.RoomData;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class RoomRepositoryImpl implements RoomRepository {

    private final RoomDAO roomDAO;

    @Override
    public Optional<Room> createRoom(Room room) {
        RoomData data = RoomData.toData(room);
        return Optional.ofNullable(roomDAO.save(data).toDomain());
    }

    @Override
    public Optional<Room> findRoomById(String roomId) {
        Optional<RoomData> roomData = roomDAO.findById(roomId);
        Optional<Room> room = roomData.map(RoomData::toDomain);
        return roomDAO.findById(roomId).map(RoomData::toDomain);
    }
}
