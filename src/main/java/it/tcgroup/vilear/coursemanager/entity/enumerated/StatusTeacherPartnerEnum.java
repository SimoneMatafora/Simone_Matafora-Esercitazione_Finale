package it.tcgroup.vilear.coursemanager.entity.enumerated;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import it.tcgroup.vilear.coursemanager.common.exception.BadParametersException;

public enum StatusTeacherPartnerEnum {
    DIPENDENTE("Dipendente"),
    COLLABORATORE("Collaboratore"),
    SOMMINISTRATO("Somministrato");

    private String status;

    StatusTeacherPartnerEnum(String status){
        this.status = status;
    }

    public String getValue() {
        return this.status;
    }

    @JsonCreator
    public static StatusTeacherPartnerEnum create(String status) {

        if (status != null) {
            for (StatusTeacherPartnerEnum val : StatusTeacherPartnerEnum.values()) {
                if (status.equalsIgnoreCase(val.getValue())) {
                    return val;
                }
            }
        }
        throw new BadParametersException("Bad parameters exception. Enum class " + StatusTeacherPartnerEnum.class.getSimpleName() + " doesn't accept this value: " + status);
    }

    @JsonValue
    public String toValue() {
        for (StatusTeacherPartnerEnum val : StatusTeacherPartnerEnum.values()) {
            if (this.getValue().equalsIgnoreCase(val.getValue())) {
                return val.getValue();
            }
        }
        return null;
    }
}
