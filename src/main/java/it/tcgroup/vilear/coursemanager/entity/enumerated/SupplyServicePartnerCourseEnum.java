package it.tcgroup.vilear.coursemanager.entity.enumerated;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import it.tcgroup.vilear.coursemanager.common.exception.BadParametersException;

public enum SupplyServicePartnerCourseEnum {
    DOCENZA("Docenza"),
    AULA("Aula"),
    MANUALE_DIDATTICO("Manuale didattico"),
    PROGETTAZIONE("Progettazione"),
    PREPARAZIONE("Preparazione"),
    SEGRETERIA("Segreteria"),
    TUTORAGGIO("Tutoraggio"),
    MONITORAGGIO("Monitoraggio");

    private String supplyService;

    SupplyServicePartnerCourseEnum(String supplyService){
        this.supplyService = supplyService;
    }

    public String getValue() {
        return this.supplyService;
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
