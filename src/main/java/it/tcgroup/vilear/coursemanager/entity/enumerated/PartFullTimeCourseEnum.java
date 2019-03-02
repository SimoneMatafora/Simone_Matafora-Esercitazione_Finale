package it.tcgroup.vilear.coursemanager.entity.enumerated;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import it.tcgroup.vilear.coursemanager.common.exception.BadParametersException;

public enum PartFullTimeCourseEnum {
    PART_TIME("part_time"),
    FULL_TIME("full_time");

    private String partFullTime;

    PartFullTimeCourseEnum(String partFullTime){
        this.partFullTime = partFullTime;
    }

    public String getValue() {
        return this.partFullTime.toUpperCase();
    }

    @JsonCreator
    public static PartFullTimeCourseEnum create(String partFullTime) {

        if (partFullTime != null) {
            for (PartFullTimeCourseEnum val : PartFullTimeCourseEnum.values()) {
                if (partFullTime.equalsIgnoreCase(val.getValue())) {
                    return val;
                }
            }
        }
        throw new BadParametersException("Bad parameters exception. Enum class " + PartFullTimeCourseEnum.class.getSimpleName() + " doesn't accept this value: " + partFullTime);
    }

    @JsonValue
    public String toValue() {
        for (PartFullTimeCourseEnum val : PartFullTimeCourseEnum.values()) {
            if (this.getValue().equalsIgnoreCase(val.getValue())) {
                return val.getValue();
            }
        }
        return null;
    }
}
