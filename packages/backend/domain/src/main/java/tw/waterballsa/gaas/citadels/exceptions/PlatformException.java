package tw.waterballsa.gaas.citadels.exceptions;

import lombok.Data;

@Data
public class PlatformException extends RuntimeException {
    private String message;
    public PlatformException(String message) {
        this.message = message;
    }
}
