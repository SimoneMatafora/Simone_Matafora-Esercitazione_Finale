package it.tcgroup.vilear.coursemanager.entity.enumerated;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import it.tcgroup.vilear.coursemanager.common.exception.BadParametersException;

public enum AcronymTradeUninoEnum {
    CGL("cgl"),
    CISL("cisl"),
    UIL("uil");

    private String acronymTradeUnino;

    AcronymTradeUninoEnum(String acronymTradeUnino){
        this.acronymTradeUnino = acronymTradeUnino;
    }

    public String getValue() {
        return this.acronymTradeUnino.toUpperCase();
    }

    @JsonCreator
    public static AcronymTradeUninoEnum create(String acronymTradeUnino) {

        if (acronymTradeUnino != null) {
            for (AcronymTradeUninoEnum val : AcronymTradeUninoEnum.values()) {
                if (acronymTradeUnino.equalsIgnoreCase(val.getValue())) {
                    return val;
                }
            }
        }
        throw new BadParametersException("Bad parameters exception. Enum class " + AcronymTradeUninoEnum.class.getSimpleName() + " doesn't accept this value: " + acronymTradeUnino);
    }

    @JsonValue
    public String toValue() {
        for (AcronymTradeUninoEnum val : AcronymTradeUninoEnum.values()) {
            if (this.getValue().equalsIgnoreCase(val.getValue())) {
                return val.getValue();
            }
        }
        return null;
    }
}
