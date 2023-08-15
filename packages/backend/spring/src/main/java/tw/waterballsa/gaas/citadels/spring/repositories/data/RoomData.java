package tw.waterballsa.gaas.citadels.spring.repositories.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import tw.waterballsa.gaas.citadels.domain.Room;
import tw.waterballsa.gaas.citadels.domain.Room.Status;
import tw.waterballsa.gaas.citadels.domain.User;

import java.time.LocalDateTime;
import java.util.List;

import static tw.waterballsa.gaas.citadels.spring.repositories.data.UserData.toDomains;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document("room")
public class RoomData {
    @Id
    private String id;
    private String name;
    private String holderId;
    private LocalDateTime createTime;
    private Status status;
    private List<UserData> users;

    public static RoomData toData(Room room) {
        User holder = room.findHolder();
        return RoomData.builder().name(room.getName())
                .users(UserData.toData(room.getUsers()))
                .status(room.getStatus())
                .holderId(holder.getId())
                .createTime(room.getCreateTime())
                .build();
    }

    public Room toDomain() {
        return new Room(id, name, holderId, status, createTime, toDomains(users));
    }

}
