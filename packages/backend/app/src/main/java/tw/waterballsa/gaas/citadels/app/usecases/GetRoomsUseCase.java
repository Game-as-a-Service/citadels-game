package tw.waterballsa.gaas.citadels.app.usecases;

import lombok.RequiredArgsConstructor;
import tw.waterballsa.gaas.citadels.app.repositories.RoomRepository;
import tw.waterballsa.gaas.citadels.domain.Room;

import javax.inject.Named;
import java.util.List;


@Named
@RequiredArgsConstructor
public class GetRoomsUseCase {

    private final RoomRepository roomRepository;

    public void execute(Presenter presenter) {
        List<Room> rooms = roomRepository.getRooms();
        presenter.present(rooms);
    }

    public interface Presenter {
        void present(List<Room> rooms);
    }
}
