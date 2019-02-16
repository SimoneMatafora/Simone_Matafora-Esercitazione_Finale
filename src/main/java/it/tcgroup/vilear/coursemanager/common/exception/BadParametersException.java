package it.tcgroup.vilear.coursemanager.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_ACCEPTABLE, value = HttpStatus.NOT_ACCEPTABLE)
public class BadParametersException extends BadRequestException {

    public BadParametersException() {
        super();
    }

    public BadParametersException(String message) {
        super(message);
    }

    public BadParametersException(String message, Throwable e) {
        super(message, e);
    }
}
