package it.tcgroup.vilear.coursemanager.entity.enumerated;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import it.tcgroup.vilear.coursemanager.common.exception.BadParametersException;

public enum ContentsAreaCourseEnum {
    AMMINISTRATIVO_CONTABILE("Amministrativo contabile"),
    FINANZA_MANAGEMENT("Finanza management"),
    IT("It"),
    LINGUE("Lingue"),
    LOGISTICA_E_MAGAZZINO("Logistica e magazzino"),
    MARKETING_COMUNICAZIONE("Marketing comunicazione"),
    QUALITÀ("Qualità"),
    RISORSE_UMANE("Risorse umane"),
    SERVIZI_SOCIO_EDUCATIVI("Servizi socio educativi"),
    SICUREZZA("Sicurezza");

    private String contestArea;

    ContentsAreaCourseEnum(String contestArea){
        this.contestArea = contestArea;
    }

    public String getValue() {
        return this.contestArea;
    }

    @JsonCreator
    public static ContentsAreaCourseEnum create(String contestArea) {

        if (contestArea != null) {
            for (ContentsAreaCourseEnum val : ContentsAreaCourseEnum.values()) {
                if (contestArea.equalsIgnoreCase(val.getValue())) {
                    return val;
                }
            }
        }
        throw new BadParametersException("Bad parameters exception. Enum class " + ContentsAreaCourseEnum.class.getSimpleName() + " doesn't accept this value: " + contestArea);
    }

    @JsonValue
    public String toValue() {
        for (ContentsAreaCourseEnum val : ContentsAreaCourseEnum.values()) {
            if (this.getValue().equalsIgnoreCase(val.getValue())) {
                return val.getValue();
            }
        }
        return null;
    }
}
