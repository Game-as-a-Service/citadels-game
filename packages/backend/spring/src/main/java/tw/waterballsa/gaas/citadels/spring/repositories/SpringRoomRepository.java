package tw.waterballsa.gaas.citadels.spring.repositories;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import tw.waterballsa.gaas.citadels.app.repositories.RoomRepository;
import tw.waterballsa.gaas.citadels.domain.Room;
import tw.waterballsa.gaas.citadels.spring.repositories.dao.RoomDAO;
import tw.waterballsa.gaas.citadels.spring.repositories.data.RoomData;

@Component
@RequiredArgsConstructor
public class SpringRoomRepository implements RoomRepository {
    private final RoomDAO roomDAO;

    @Override
    public Room save(Room room) {
        return roomDAO.save(RoomData.toData(room)).toDomain();
    }

}
