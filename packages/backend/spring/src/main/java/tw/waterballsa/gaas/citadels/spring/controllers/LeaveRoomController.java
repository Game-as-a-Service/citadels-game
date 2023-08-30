package tw.waterballsa.gaas.citadels.spring.controllers;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tw.waterballsa.gaas.citadels.domain.Room;
import tw.waterballsa.gaas.citadels.spring.repositories.RoomRepositoryImpl;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/api/citadels")
public class LeaveRoomController {


    @Autowired
    private RoomRepositoryImpl roomRepository;

    @PostMapping("/rooms/{roomId}:leave")
    public ResponseEntity<?> leaveRoom(@PathVariable String roomId, @RequestBody LeaveRoomRequest request) {
        Room room = roomRepository.findRoomById(roomId).orElseThrow();
        room.removeUserById(request.getUserId());
        if (room.isClose()) {
            roomRepository.deleteRoom(room.getId());
        } else {
            roomRepository.updateRoom(room);
        }
        return ResponseEntity.ok("");
    }

    @Data
    public static class LeaveRoomRequest {
        private String userId;
    }
}
