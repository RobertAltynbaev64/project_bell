package project.altynbaev.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import project.altynbaev.view.ErrorView;

import java.sql.SQLException;

@RestControllerAdvice
public class ExceptionHandlerAdvice extends ResponseEntityExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionHandlerAdvice.class);

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorView handleBadRequestException(BadRequestException ex) {
        LOGGER.error("Ошибка в запросе: [{}]", ex.getMessage());
        return new ErrorView(ex.getMessage());
    }

    @ExceptionHandler(HttpServerErrorException.InternalServerError.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorView handleMInternalServerError(HttpServerErrorException.InternalServerError ex) {
        LOGGER.error("Внутренняя ошибка: [{}]", ex.getMessage());
        return new ErrorView(ex.getMessage());
    }

    @ExceptionHandler(SQLException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorView handleSQLException(SQLException ex) {
        LOGGER.error("SQLException: [{}]", ex.getMessage());
        return new ErrorView(ex.getMessage());
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorView handleNotFoundException(NotFoundException ex) {
        LOGGER.error("Объект не найден: [{}]", ex.getMessage());
        return new ErrorView(ex.getMessage());
    }
}
