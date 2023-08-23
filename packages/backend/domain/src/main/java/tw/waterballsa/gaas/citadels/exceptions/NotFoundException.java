package tw.waterballsa.gaas.citadels.exceptions;

import lombok.Data;

@Data
public class NotFoundException extends RuntimeException{
    private String msg;
    public NotFoundException(String msg) {
        this.msg = msg;
    }
}
