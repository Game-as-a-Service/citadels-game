package tw.waterballsa.gaas.citadels.spring.aspects;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import tw.waterballsa.gaas.citadels.exceptions.JoinRoomException;
import tw.waterballsa.gaas.citadels.exceptions.NotFoundException;

@RestControllerAdvice
public class CitadelsGameExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(JoinRoomException.class)
    public ErrorResponse errorJoinRoomRequest(JoinRoomException exception) {
        return new ErrorResponse(Status.FAIL, exception.getMsg());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NotFoundException.class)
    public ErrorResponse ErrorNotFoundRequest(NotFoundException exception) {
        return new ErrorResponse(Status.FAIL, exception.getMsg());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(Exception ex, WebRequest request) {
        return new ResponseEntity<>(ex.getMessage(), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Data
    @AllArgsConstructor
    public static class ErrorResponse {
        private Status status;
        private String msg;
    }

    enum Status {
        OK,
        FAIL
    }
}
