package tw.waterballsa.gaas.citadels.spring.repositories;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import tw.waterballsa.gaas.citadels.app.repositories.RoomRepository;
import tw.waterballsa.gaas.citadels.domain.Room;
import tw.waterballsa.gaas.citadels.spring.repositories.dao.RoomDAO;
import tw.waterballsa.gaas.citadels.spring.repositories.data.RoomData;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class RoomRepositoryImpl implements RoomRepository {

    private final RoomDAO roomDAO;

    @Override
    public Room createRoom(Room room) {
        return saveOrUpdateRoom(room);
    }

    @Override
    public Room updateRoom(Room room) {
        return saveOrUpdateRoom(room);
    }

    @Override
    public Optional<Room> findRoomById(String roomId) {
        return roomDAO.findById(roomId).map(RoomData::toDomain);
    }
    
    @Override
    public List<Room> getRooms() {
        return roomDAO.findAll().stream()
                .map(RoomData::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteAll() {
        roomDAO.deleteAll();
    }

    @Override
    public void deleteRoom(String roomId) {
        roomDAO.deleteById(roomId);
    }

    private Room saveOrUpdateRoom(Room room) {
        RoomData data = RoomData.toData(room);
        return roomDAO.save(data).toDomain();
    }
}
