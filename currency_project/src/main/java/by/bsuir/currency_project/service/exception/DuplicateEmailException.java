package by.bsuir.currency_project.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class DuplicateEmailException extends RuntimeException {

    public DuplicateEmailException(){
        super();
    }

    public DuplicateEmailException(String message){
        super(message);
    }

    public DuplicateEmailException(Exception e){
        super(e);
    }

    public DuplicateEmailException(String message, Exception e){
        super(message, e);
    }

}
