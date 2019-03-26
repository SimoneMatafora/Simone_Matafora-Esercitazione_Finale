package it.tcgroup.vilear.coursemanager.entity.enumerated;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import it.tcgroup.vilear.coursemanager.common.exception.BadParametersException;

public enum CourseStatusEnum {
    IN_ATTESA_DI_PUBBLICAZIONE("in_attesa_di_pubblicazione"),
    PUBBLICATO("pubblicato"),
    CANCELLATO("cancellato");

    private String courseType;

    CourseStatusEnum(String courseType){
        this.courseType = courseType;
    }

    public String getValue() {
        return this.courseType.toUpperCase();
    }

    @JsonCreator
    public static CourseStatusEnum create(String courseType) {

        if (courseType != null) {
            for (CourseStatusEnum val : CourseStatusEnum.values()) {
                if (courseType.equalsIgnoreCase(val.getValue())) {
                    return val;
                }
            }
        }
        throw new BadParametersException("Bad parameters exception. Enum class " + CourseStatusEnum.class.getSimpleName() + " doesn't accept this value: " + courseType);
    }

    @JsonValue
    public String toValue() {
        for (CourseStatusEnum val : CourseStatusEnum.values()) {
            if (this.getValue().equalsIgnoreCase(val.getValue())) {
                return val.getValue();
            }
        }
        return null;
    }
}
