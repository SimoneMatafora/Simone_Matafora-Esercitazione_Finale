package it.tcgroup.vilear.coursemanager.entity.enumerated;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import it.tcgroup.vilear.coursemanager.common.exception.BadParametersException;

public enum ReasonWithdrawnLearnerCourseEnum {
    INIZIO_RAPPORTO_LAVORO("Inizio rapporto lavoro"),
    MALATTIA("Malattia");

    private String reasonWithdrawn;

    ReasonWithdrawnLearnerCourseEnum(String reasonWithdrawn){
        this.reasonWithdrawn = reasonWithdrawn;
    }

    public String getValue() {
        return this.reasonWithdrawn;
    }

    @JsonCreator
    public static ReasonWithdrawnLearnerCourseEnum create(String reasonWithdrawn) {

        if (reasonWithdrawn != null) {
            for (ReasonWithdrawnLearnerCourseEnum val : ReasonWithdrawnLearnerCourseEnum.values()) {
                if (reasonWithdrawn.equalsIgnoreCase(val.getValue())) {
                    return val;
                }
            }
        }
        throw new BadParametersException("Bad parameters exception. Enum class " + ReasonWithdrawnLearnerCourseEnum.class.getSimpleName() + " doesn't accept this value: " + reasonWithdrawn);
    }

    @JsonValue
    public String toValue() {
        for (ReasonWithdrawnLearnerCourseEnum val : ReasonWithdrawnLearnerCourseEnum.values()) {
            if (this.getValue().equalsIgnoreCase(val.getValue())) {
                return val.getValue();
            }
        }
        return null;
    }

}
