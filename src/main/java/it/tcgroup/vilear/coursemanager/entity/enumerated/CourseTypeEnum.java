package it.tcgroup.vilear.coursemanager.entity.enumerated;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import it.tcgroup.vilear.coursemanager.common.exception.BadParametersException;

public enum CourseTypeEnum {
    BASE("Base"),
    BASE_FAD("Base - FAD"),
    CONTNUA_A_CAT_FAD("Continua a cat. - FAD"),
    CONTINUA_A_CATALOGO("Continua a catalogo"),
    CONTINUA_A_VOUCHER("Continua a voucher"),
    CONTINUA_A_VOUCHER_FAD("Continua a voucher - FAD"),
    FONDI_INTERPROFESSIONALI("Fondi interprofessionali"),
    FONDI_REGIONALI("Fondi reigonali"),
    ON_THE_JOB("On the Job"),
    ORIENTAMENTO("Orientamento"),
    PROFESSIONALE("Professionale"),
    PROFESSIONALE_FAD("Professionale - FAD"),
    TD_CONTINUA_AULA("TD Continua (Aula)"),
    TD_CONTINUA_INDIVIDUALE("TD Continua (Individuale)"),
    TD_PAL_POLITICHE_ATTIVE_LAVORO("TD PAL (Politiche Attive Lavoro)"),
    TI_QUALIFICAZIONE("TI - Qualificazione"),
    TI_QUALIFICAZIONE_AULA_MIX("TI - Qualificazione (Aula mix)"),
    TI_QUALIFICAZIONE_FAD("TI - Qualificazione (FAD)"),
    TI_QUALIFICAZIONE_INDIV_MAX("TI - Qualificazione (Indiv. Max 2/4)"),
    TI_QUALIFICAZIONE_OTJ("TI - Qualificazione (OTJ)"),
    TI_RIQUALIFICAZIONE("TI - Riqualificazione"),
    TI_RIQUALIFICAZIONE_AULA_MIX("TI - Riqualificazione (Aula mix)"),
    TI_RIQUALIFICAZIONE_FAD("TI - Riqualificazione (FAD)"),
    TI_RIQUALIFICAZIONE_INDIV_MAX("TI - Riqualificazione (Indiv. Max 2/4)"),
    TI_FORMAZ_ACC_PAL_POST("TI Formaz. Acc. PAL/Post"),
    TI_FORMAZ_ACC_PAL_PRE("TI Formaz. Acc. PAL/Pre"),
    TI_FORMAZ_OCCUPABILITA("TI Formaz. Occupabilità"),
    TI_FORMAZ_PROFESSIONALIZZANTE("TI Formaz. Professionalizzante"),
    TI_PAI_POLITICHE_ATTIVE_LAVORO("TI PAI (Politiche Attive Lavoro)");

    private String courseType;

    CourseTypeEnum (String courseType){
        this.courseType = courseType;
    }

    public String getValue() {
        return this.courseType;
    }

    @JsonCreator
    public static CourseTypeEnum  create(String courseType) {

        if (courseType != null) {
            for (CourseTypeEnum  val : CourseTypeEnum .values()) {
                if (courseType.equalsIgnoreCase(val.getValue())) {
                    return val;
                }
            }
        }
        throw new BadParametersException("Bad parameters exception. Enum class " + CourseTypeEnum .class.getSimpleName() + " doesn't accept this value: " + courseType);
    }

    @JsonValue
    public String toValue() {
        for (CourseTypeEnum  val : CourseTypeEnum .values()) {
            if (this.getValue().equalsIgnoreCase(val.getValue())) {
                return val.getValue();
            }
        }
        return null;
    }
}
