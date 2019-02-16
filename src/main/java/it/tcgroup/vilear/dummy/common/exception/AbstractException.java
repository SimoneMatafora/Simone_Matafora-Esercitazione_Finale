package it.tcgroup.vilear.dummy.common.exception;

public abstract class AbstractException extends RuntimeException {

    private String code;

    public AbstractException() {
        super();
    }

    public AbstractException(String message) {
        super(message);
    }

    public AbstractException(Code messageCode) {
        this(messageCode.value());
        this.code = messageCode.name().substring(messageCode.name().lastIndexOf("_") != -1 ? messageCode.name().lastIndexOf("_") + 1 : 0);
    }

    public AbstractException(String message, Code messageCode) {
        this(message);
        this.code = messageCode.name().substring(messageCode.name().lastIndexOf("_") != -1 ? messageCode.name().lastIndexOf("_") + 1 : 0);
    }

    public AbstractException(String message, Throwable e) {
        super(message, e);
    }

    public AbstractException(Code messageCode, Throwable e) {
        super(messageCode.value(), e);
        this.code = messageCode.name().substring(messageCode.name().lastIndexOf("_") != -1 ? messageCode.name().lastIndexOf("_") + 1 : 0);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
