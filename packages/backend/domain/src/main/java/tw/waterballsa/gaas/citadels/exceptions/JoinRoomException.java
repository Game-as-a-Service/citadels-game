package tw.waterballsa.gaas.citadels.exceptions;

import lombok.Data;

@Data
public class JoinRoomException extends RuntimeException {
    private String message;
    public JoinRoomException(String message) {
        this.message = message;
    }
}
