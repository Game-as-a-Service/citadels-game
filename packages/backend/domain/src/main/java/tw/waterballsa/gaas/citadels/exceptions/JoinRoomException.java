package tw.waterballsa.gaas.citadels.exceptions;

import lombok.Data;

@Data
public class JoinRoomException extends RuntimeException {
    private String msg;
    public JoinRoomException(String msg) {
        this.msg = msg;
    }
}
