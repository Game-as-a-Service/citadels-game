package tw.waterballsa.gaas.citadels.spring.aspects;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import tw.waterballsa.gaas.citadels.exceptions.PlatformException;

@RestControllerAdvice
public class PlatformExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(PlatformException.class)
    public String badRequest(PlatformException exception) {
        return exception.getMessage();
    }
}
