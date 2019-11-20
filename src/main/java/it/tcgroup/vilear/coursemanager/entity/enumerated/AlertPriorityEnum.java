package it.tcgroup.vilear.coursemanager.entity.enumerated;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import it.tcgroup.vilear.coursemanager.common.exception.BadParametersException;

public enum AlertPriorityEnum {
    ALTA("alta"),
    MEDIA("media"),
    BASSA("bassa"),
    NESSUNA("nessuna");

    private String priority;

    AlertPriorityEnum(String priority){
        this.priority = priority;
    }

    public String getValue() {
        return this.priority;
    }

    @JsonCreator
    public static AlertPriorityEnum create(String priority) {

        if (priority != null) {
            for (AlertPriorityEnum val : AlertPriorityEnum.values()) {
                if (priority.equalsIgnoreCase(val.getValue())) {
                    return val;
                }
            }
        }
        throw new BadParametersException("Bad parameters exception. Enum class " + AlertPriorityEnum.class.getSimpleName() + " doesn't accept this value: " + priority);
    }

    @JsonValue
    public String toValue() {
        for (AlertPriorityEnum val : AlertPriorityEnum.values()) {
            if (this.getValue().equalsIgnoreCase(val.getValue())) {
                return val.getValue();
            }
        }
        return null;
    }
}
