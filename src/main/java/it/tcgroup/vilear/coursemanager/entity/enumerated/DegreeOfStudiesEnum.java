package it.tcgroup.vilear.coursemanager.entity.enumerated;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import it.tcgroup.vilear.coursemanager.common.exception.BadParametersException;

public enum DegreeOfStudiesEnum {
    ELEMENTARI("Elementari"),
    MEDIE("Medie"),
    DIPLOMA("Diploma"),
    LAUREA_PRIMO_LIVELLO("Laurea primo livello"),
    LAUREA_MAGISTRALE("Laurea magistrale"),
    DOTTORATO("Dottorato"),
    MASTER("Master");

    private String tipoDiStudio;

    DegreeOfStudiesEnum(String tipoDiStudio){ this.tipoDiStudio = tipoDiStudio;}

    public String getValue() {
        return this.tipoDiStudio;
    }

    @JsonCreator
    public static DegreeOfStudiesEnum create(String tipoDiStudio) {

        tipoDiStudio  = tipoDiStudio.replace('-','_');
        tipoDiStudio  = tipoDiStudio.replace(' ','_');

        if (tipoDiStudio != null) {
            for (DegreeOfStudiesEnum val : DegreeOfStudiesEnum.values()) {
                if (tipoDiStudio.equalsIgnoreCase(val.getValue())) {
                    return val;
                }
            }
        }
        throw new BadParametersException("Bad parameters exception. Enum class " + DegreeOfStudiesEnum.class.getSimpleName() + " doesn't accept this value: " + tipoDiStudio);
    }

    @JsonValue
    public String toValue() {
        for (DegreeOfStudiesEnum val : DegreeOfStudiesEnum.values()) {
            if (this.getValue().equalsIgnoreCase(val.getValue())) {
                return val.getValue();
            }
        }
        return null;
    }
}
