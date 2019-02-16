package it.tcgroup.vilear.coursemanager.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, value = HttpStatus.BAD_REQUEST)
public class BadRequestException extends AbstractException {

    public BadRequestException() {
        super();
    }

    public BadRequestException(String message) {
        super(message);
    }

    public BadRequestException(String message, Throwable e) {
        super(message, e);
    }

    public BadRequestException(Code messageCode) {
        super(messageCode);
    }

    public BadRequestException(String message, Code messageCode) {
        super(message, messageCode);
    }

    public BadRequestException(Code messageCode, Throwable e) {
        super(messageCode, e);
    }
}
