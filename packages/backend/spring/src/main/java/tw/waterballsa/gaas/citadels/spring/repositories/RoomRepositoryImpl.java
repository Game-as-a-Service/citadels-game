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
    public Optional<Room> save(Room room) {
        RoomData data = RoomData.toData(room);
        return Optional.ofNullable(roomDAO.save(data).toDomain());
    }

    @Override
    public Optional<Room> findById(String gameId) {
        return roomDAO.findById(gameId).map(RoomData::toDomain);
    }
}
