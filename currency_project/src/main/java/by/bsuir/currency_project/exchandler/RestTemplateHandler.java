package by.bsuir.currency_project.exchandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.ConnectException;

@RestControllerAdvice
public class RestTemplateHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(RestTemplateHandler.class);

    @ExceptionHandler(ConnectException.class)
    public ResponseEntity<Object> handle() {
        LOGGER.error("Problems with connection to NodeJS server!");
        return new ResponseEntity<Object>(new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
