package project.altynbaev;

import project.altynbaev.dto.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

@ControllerAdvice
public class DefaultAdvice {

    private static Logger log = Logger.getLogger(DefaultAdvice.class.getName());

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ResponseDto> handleNullPointerException(Exception e) {
        UUID id1 = UUID.randomUUID();
        log.log(Level.INFO, id1 + " Exception: ", e);
        ResponseDto response = new ResponseDto(id1 + " " + e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseDto> handleAllException(Exception e) {
        UUID id1 = UUID.randomUUID();
        log.log(Level.INFO, id1 + " Exception: ", e);
        ResponseDto response = new ResponseDto(id1 + " " + e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseDto> handleHttpClientErrorException(Exception e) {
        UUID id1 = UUID.randomUUID();
        log.log(Level.INFO, id1 + " Exception: ", e);
        ResponseDto response = new ResponseDto(id1 + " " + e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

}
