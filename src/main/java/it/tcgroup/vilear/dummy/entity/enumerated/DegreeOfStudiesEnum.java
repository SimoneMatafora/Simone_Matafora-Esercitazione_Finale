package it.tcgroup.vilear.dummy.entity.enumerated;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import it.tcgroup.vilear.dummy.common.exception.BadParametersException;

public enum DegreeOfStudiesEnum {
    ELEMENTARI("elementari"),
    MEDIE("medie"),
    DIPLOMA("diploma"),
    LAUREA_PRIMO_LIVELLO("laurea_primo_livello"),
    LAUREA_MAGISTRALE("laurea_magistrale"),
    DOTTORATO("dottorato"),
    MASTER("master");

    private String tipoDiStudio;

    DegreeOfStudiesEnum(String tipoDiStudio){ this.tipoDiStudio = tipoDiStudio;}

    public String getValue() {
        return this.tipoDiStudio.toUpperCase();
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
