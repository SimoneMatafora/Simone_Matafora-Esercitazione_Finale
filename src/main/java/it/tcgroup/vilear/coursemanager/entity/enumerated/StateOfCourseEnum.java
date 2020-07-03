package it.tcgroup.vilear.coursemanager.entity.enumerated;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import it.tcgroup.vilear.coursemanager.common.exception.BadParametersException;

public enum StateOfCourseEnum {

    INSERTED("inserted"),
    IN_PROGRESS("in_progress"),
    TERMINATED("terminated");

    private String stateOfTheCourse;

    StateOfCourseEnum(String stateOfTheCourse) {
        this.stateOfTheCourse = stateOfTheCourse;
    }

    public void setStateOfTheCourse(String stateOfTheCourse) {
        this.stateOfTheCourse = stateOfTheCourse;
    }

    public String getStateOfTheCourse() {
        return stateOfTheCourse;
    }

    @JsonCreator
    public static StateOfCourseEnum create(String stateOfTheCourse) {

        stateOfTheCourse  = stateOfTheCourse.replace('-','_');
        stateOfTheCourse  = stateOfTheCourse.replace(' ','_');

        if (stateOfTheCourse != null) {
            for (StateOfCourseEnum val : StateOfCourseEnum.values()) {
                if (stateOfTheCourse.equalsIgnoreCase(val.getStateOfTheCourse())) {
                    return val;
                }
            }
        }
        throw new BadParametersException("Bad parameters exception. Enum class " + DegreeOfStudiesEnum.class.getSimpleName() + " doesn't accept this value: " + stateOfTheCourse);
    }

    @JsonValue
    public String toValue() {
        for (StateOfCourseEnum val : StateOfCourseEnum.values()) {
            if (this.getStateOfTheCourse().equalsIgnoreCase(val.getStateOfTheCourse())) {
                return val.getStateOfTheCourse();
            }
        }
        return null;
    }

}
