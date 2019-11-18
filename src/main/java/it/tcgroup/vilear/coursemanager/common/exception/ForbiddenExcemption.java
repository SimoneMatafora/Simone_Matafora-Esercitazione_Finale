package it.tcgroup.vilear.coursemanager.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.FORBIDDEN, value = HttpStatus.FORBIDDEN)
public class ForbiddenExcemption extends AbstractException{

    public ForbiddenExcemption() {
        super();
    }

    public ForbiddenExcemption(String message) {
        super(message);
    }

    public ForbiddenExcemption(String message, Throwable e) {
        super(message, e);
    }

    public ForbiddenExcemption(Code messageCode) {
        super(messageCode);
    }

    public ForbiddenExcemption(String message, Code messageCode) {
        super(message, messageCode);
    }

    public ForbiddenExcemption(Code messageCode, Throwable e) {
        super(messageCode, e);
    }
}
