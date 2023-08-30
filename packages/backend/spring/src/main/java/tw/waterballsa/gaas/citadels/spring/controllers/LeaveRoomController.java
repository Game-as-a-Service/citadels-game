package tw.waterballsa.gaas.citadels.spring.controllers;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tw.waterballsa.gaas.citadels.app.usecases.LeaveRoomUseCase;

import javax.validation.constraints.NotNull;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/api/citadels")
public class LeaveRoomController {

    private final LeaveRoomUseCase leaveRoomUseCase;

    @PostMapping("/rooms/{roomId}:leave")
    public ResponseEntity<?> leaveRoom(@PathVariable String roomId, @RequestBody LeaveRoomRequest request) {
        leaveRoomUseCase.execute(new LeaveRoomUseCase.Request(roomId, request.getUserId()));
        return ResponseEntity.ok().build();
    }

    @Data
    public static class LeaveRoomRequest {
        @NotNull(message = "The userId must not be null.")
        private String userId;
    }
}
