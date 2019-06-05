package it.tcgroup.vilear.coursemanager.entity.enumerated;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import it.tcgroup.vilear.coursemanager.common.exception.BadParametersException;

public enum FoundsTypeCourseEnum {
    FONDI_EUROPEI("Fondi europei"),
    FONDI_INTERPROFESSIONALI("Fondi interprofessionali"),
    FONDI_PROVINCIALI("Fondi provinciali"),
    FONDI_REGIONALI("Fondi regionali"),
    FORMATEMP("Formatemp");

    private String foundsType;

    FoundsTypeCourseEnum(String foundsType){
        this.foundsType = foundsType;
    }

    public String getValue() {
        return this.foundsType;
    }

    @JsonCreator
    public static FoundsTypeCourseEnum create(String foundsType) {

        if (foundsType != null) {
            for (FoundsTypeCourseEnum val : FoundsTypeCourseEnum.values()) {
                if (foundsType.equalsIgnoreCase(val.getValue())) {
                    return val;
                }
            }
        }
        throw new BadParametersException("Bad parameters exception. Enum class " + FoundsTypeCourseEnum.class.getSimpleName() + " doesn't accept this value: " + foundsType);
    }

    @JsonValue
    public String toValue() {
        for (FoundsTypeCourseEnum val : FoundsTypeCourseEnum.values()) {
            if (this.getValue().equalsIgnoreCase(val.getValue())) {
                return val.getValue();
            }
        }
        return null;
    }
}
