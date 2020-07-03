package it.tcgroup.vilear.coursemanager.common.validation;

import it.tcgroup.vilear.coursemanager.common.exception.Code;

public enum MessageCode implements Code {
    // E00X has to be replaced with a micro-service number identifier (ex. E002)
    E00X_1000("Bad Request");

    private String value;

    MessageCode(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }
}