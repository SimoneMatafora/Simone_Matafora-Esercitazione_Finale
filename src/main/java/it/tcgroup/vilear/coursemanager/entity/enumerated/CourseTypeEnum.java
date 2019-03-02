package it.tcgroup.vilear.coursemanager.entity.enumerated;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import it.tcgroup.vilear.coursemanager.common.exception.BadParametersException;

public enum CourseTypeEnum {
    BASE("base"),
    FONDI_INTERPROFESSIONALI("fondi_interprofessionali"),
    ON_THE_JOB("on_the_job"),
    PAL("pal"),
    PROFESSIONALE("professionale");

    private String courseType;

    CourseTypeEnum (String courseType){
        this.courseType = courseType;
    }

    public String getValue() {
        return this.courseType.toUpperCase();
    }

    @JsonCreator
    public static CourseTypeEnum  create(String courseType) {

        if (courseType != null) {
            for (CourseTypeEnum  val : CourseTypeEnum .values()) {
                if (courseType.equalsIgnoreCase(val.getValue())) {
                    return val;
                }
            }
        }
        throw new BadParametersException("Bad parameters exception. Enum class " + CourseTypeEnum .class.getSimpleName() + " doesn't accept this value: " + courseType);
    }

    @JsonValue
    public String toValue() {
        for (CourseTypeEnum  val : CourseTypeEnum .values()) {
            if (this.getValue().equalsIgnoreCase(val.getValue())) {
                return val.getValue();
            }
        }
        return null;
    }
}
