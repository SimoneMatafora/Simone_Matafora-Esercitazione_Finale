package it.tcgroup.vilear.coursemanager.entity.enumerated;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import it.tcgroup.vilear.coursemanager.common.exception.BadParametersException;

public enum SecurityExonerateLearnerCourseEnum {
    ZERO("0"),
    QUATTRO("4"),
    OTTO("8"),
    DODICI("12");

    private String securityExonerate;

    SecurityExonerateLearnerCourseEnum(String securityExonerate){
        this.securityExonerate = securityExonerate;
    }

    public String getValue() {
        return this.securityExonerate;
    }

    @JsonCreator
    public static SecurityExonerateLearnerCourseEnum create(String securityExonerate) {

        if (securityExonerate != null) {
            for (SecurityExonerateLearnerCourseEnum val : SecurityExonerateLearnerCourseEnum.values()) {
                if (securityExonerate.equalsIgnoreCase(val.getValue())) {
                    return val;
                }
            }
        }
        throw new BadParametersException("Bad parameters exception. Enum class " + SecurityExonerateLearnerCourseEnum.class.getSimpleName() + " doesn't accept this value: " + securityExonerate);
    }

    @JsonValue
    public String toValue() {
        for (SecurityExonerateLearnerCourseEnum val : SecurityExonerateLearnerCourseEnum.values()) {
            if (this.getValue().equalsIgnoreCase(val.getValue())) {
                return val.getValue();
            }
        }
        return null;
    }

}
