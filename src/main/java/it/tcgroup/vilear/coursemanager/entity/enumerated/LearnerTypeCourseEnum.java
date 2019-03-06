package it.tcgroup.vilear.coursemanager.entity.enumerated;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import it.tcgroup.vilear.coursemanager.common.exception.BadParametersException;

public enum LearnerTypeCourseEnum {
    DIPENDENTI("dipendenti"),
    DISOCCUPATI("disoccupati"),
    ENTRAMBI("entrambi");

    private String learnerType;

    LearnerTypeCourseEnum(String learnerType){
        this.learnerType = learnerType;
    }

    public String getValue() {
        return this.learnerType.toUpperCase();
    }

    @JsonCreator
    public static LearnerTypeCourseEnum create(String learnerType) {

        if (learnerType != null) {
            for (LearnerTypeCourseEnum val : LearnerTypeCourseEnum.values()) {
                if (learnerType.equalsIgnoreCase(val.getValue())) {
                    return val;
                }
            }
        }
        throw new BadParametersException("Bad parameters exception. Enum class " + LearnerTypeCourseEnum.class.getSimpleName() + " doesn't accept this value: " + learnerType);
    }

    @JsonValue
    public String toValue() {
        for (LearnerTypeCourseEnum val : LearnerTypeCourseEnum.values()) {
            if (this.getValue().equalsIgnoreCase(val.getValue())) {
                return val.getValue();
            }
        }
        return null;
    }
}