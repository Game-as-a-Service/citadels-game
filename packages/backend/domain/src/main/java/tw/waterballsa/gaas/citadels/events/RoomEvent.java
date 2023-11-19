package tw.waterballsa.gaas.citadels.events;

import lombok.AllArgsConstructor;
import tw.waterballsa.gaas.citadels.domain.Room;

@AllArgsConstructor
public class RoomEvent extends DomainEvent {
    private Room updatedRoom;

    public Room getUpdatedRoom() {
        return updatedRoom;
    }
}
