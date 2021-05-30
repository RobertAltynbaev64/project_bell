package project.altynbaev;

import project.altynbaev.dto.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import project.altynbaev.exception.NotFoundException;

@ControllerAdvice
public class DefaultAdvice {

    static private Logger log = LoggerFactory.getLogger(DefaultAdvice.class);

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseDto> handleAllException(Exception e) {
        UUID id1 = UUID.randomUUID();
        if (e.getClass() == NotFoundException.class || e.getClass() == NoHandlerFoundException.class) {
            log.info(id1 + " Exception: ", e);
            ResponseDto response = new ResponseDto(id1 + " " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        if (e.getClass() == MethodArgumentNotValidException.class) {
            log.info(id1 + " Exception: ", e);
            ResponseDto response = new ResponseDto(id1 + " " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } else {
            log.error(id1 + " Exception: ", e);
            ResponseDto response = new ResponseDto(id1 + "внутренняя ошибка сервера ");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
