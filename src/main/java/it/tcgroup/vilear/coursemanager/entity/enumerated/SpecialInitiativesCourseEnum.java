package it.tcgroup.vilear.coursemanager.entity.enumerated;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import it.tcgroup.vilear.coursemanager.common.exception.BadParametersException;

public enum SpecialInitiativesCourseEnum {
    SPECIAL_INITIATIVES_COURSE("special_initiatives_course");

    private String specialInitiativesCourse;

    SpecialInitiativesCourseEnum(String specialInitiativesCourse){
        this.specialInitiativesCourse = specialInitiativesCourse;
    }

    public String getValue() {
        return this.specialInitiativesCourse.toUpperCase();
    }

    @JsonCreator
    public static SpecialInitiativesCourseEnum create(String specialInitiativesCourse) {

        if (specialInitiativesCourse != null) {
            for (SpecialInitiativesCourseEnum val : SpecialInitiativesCourseEnum.values()) {
                if (specialInitiativesCourse.equalsIgnoreCase(val.getValue())) {
                    return val;
                }
            }
        }
        throw new BadParametersException("Bad parameters exception. Enum class " + SpecialInitiativesCourseEnum.class.getSimpleName() + " doesn't accept this value: " + specialInitiativesCourse);
    }

    @JsonValue
    public String toValue() {
        for (SpecialInitiativesCourseEnum val : SpecialInitiativesCourseEnum.values()) {
            if (this.getValue().equalsIgnoreCase(val.getValue())) {
                return val.getValue();
            }
        }
        return null;
    }
}
