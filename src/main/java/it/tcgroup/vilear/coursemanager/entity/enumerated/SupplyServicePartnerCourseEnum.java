package it.tcgroup.vilear.coursemanager.entity.enumerated;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import it.tcgroup.vilear.coursemanager.common.exception.BadParametersException;

public enum SupplyServicePartnerCourseEnum {
    DOCENZA("docenza"),
    AULA("aula"),
    MANUALE_DIDATTICO("manuale_didattico"),
    PROGETTAZIONE("progettazione"),
    PREPARAZIONE("preparazione"),
    SEGRETERIA("segreteria"),
    TUTORAGGIO("tutoraggio"),
    MONITORAGGIO("monitoraggio");

    private String supplyService;

    SupplyServicePartnerCourseEnum(String supplyService){
        this.supplyService = supplyService;
    }

    public String getValue() {
        return this.supplyService.toUpperCase();
    }

    @JsonCreator
    public static SupplyServicePartnerCourseEnum create(String supplyService) {

        if (supplyService != null) {
            for (SupplyServicePartnerCourseEnum val : SupplyServicePartnerCourseEnum.values()) {
                if (supplyService.equalsIgnoreCase(val.getValue())) {
                    return val;
                }
            }
        }
        throw new BadParametersException("Bad parameters exception. Enum class " + SupplyServicePartnerCourseEnum.class.getSimpleName() + " doesn't accept this value: " + supplyService);
    }

    @JsonValue
    public String toValue() {
        for (SupplyServicePartnerCourseEnum val : SupplyServicePartnerCourseEnum.values()) {
            if (this.getValue().equalsIgnoreCase(val.getValue())) {
                return val.getValue();
            }
        }
        return null;
    }

}
