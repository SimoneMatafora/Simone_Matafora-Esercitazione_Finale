package it.tcgroup.vilear.coursemanager.entity.enumerated;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import it.tcgroup.vilear.coursemanager.common.exception.BadParametersException;

public enum ContentsAreaCourseEnum {
    AMMINISTRATIVO_CONTABILE("amministrativo_contabile"),
    FINANZA_MANAGEMENT("finanza_management"),
    IT("it"),
    LINGUE("lingue"),
    LOGISTICA_E_MAGAZZINO("logistica_e_magazzino"),
    MARKETING_COMUNICAZIONE("marketing_comunicazione"),
    QUALITÀ("qualità"),
    RISORSE_UMANE("risorse_umane"),
    SERVIZI_SOCIO_EDUCATIVI("servizi_socio_educativi"),
    SICUREZZA("sicurezza");

    private String contestArea;

    ContentsAreaCourseEnum(String contestArea){
        this.contestArea = contestArea;
    }

    public String getValue() {
        return this.contestArea.toUpperCase();
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
