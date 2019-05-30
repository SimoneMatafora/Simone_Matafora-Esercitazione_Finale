package it.tcgroup.vilear.coursemanager.entity.enumerated;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import it.tcgroup.vilear.coursemanager.common.exception.BadParametersException;

public enum WorkingPositionEnum {
    DIPENDENTE("fatturazione"),
    COLLABORATORE("collaboratore");

    private String workingPositionType;

    WorkingPositionEnum(String workingPositionType){
        this.workingPositionType = workingPositionType;
    }

    public String getValue() {
        return this.workingPositionType.toUpperCase();
    }

    @JsonCreator
    public static WorkingPositionEnum create(String workingPositionType) {

        if (workingPositionType != null) {
            for (WorkingPositionEnum val : WorkingPositionEnum.values()) {
                if (workingPositionType.equalsIgnoreCase(val.getValue())) {
                    return val;
                }
            }
        }
        throw new BadParametersException("Bad parameters exception. Enum class " + WorkingPositionEnum.class.getSimpleName() + " doesn't accept this value: " + workingPositionType);
    }

    @JsonValue
    public String toValue() {
        for (WorkingPositionEnum val : WorkingPositionEnum.values()) {
            if (this.getValue().equalsIgnoreCase(val.getValue())) {
                return val.getValue();
            }
        }
        return null;
    }
}
