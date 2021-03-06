package by.bsuir.currency_project.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class ConflictException extends RuntimeException {

    public ConflictException(){
        super();
    }

    public ConflictException(String message){
        super(message);
    }

    public ConflictException(Exception e){
        super(e);
    }

    public ConflictException(String message, Exception e){
        super(message, e);
    }

}
