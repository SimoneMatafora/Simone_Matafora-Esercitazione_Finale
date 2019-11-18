package it.tcgroup.vilear.coursemanager.entity.enumerated;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import it.tcgroup.vilear.coursemanager.common.exception.BadParametersException;

public enum AlertTypeEnum {
    DATA_INIZIO_CORSO("data_inizio_corso"),
    DATA_FINE_CORSO("data_fine_corso"),
    RENDICONTAZIONE_ELETTRONICA("rendicontazione_elettronica"),
    INVIO_PLACEMENT("invio_placement"),
    DATA_CONSEGNA_PROGETTO("data_consegna_progetto");

    private String alertType;

    AlertTypeEnum(String alertType){
        this.alertType = alertType;
    }

    public String getValue() {
        return this.alertType;
    }

    @JsonCreator
    public static AlertTypeEnum create(String alertType) {

        if (alertType != null) {
            for (AlertTypeEnum val : AlertTypeEnum.values()) {
                if (alertType.equalsIgnoreCase(val.getValue())) {
                    return val;
                }
            }
        }
        throw new BadParametersException("Bad parameters exception. Enum class " + AlertTypeEnum.class.getSimpleName() + " doesn't accept this value: " + alertType);
    }

    @JsonValue
    public String toValue() {
        for (AlertTypeEnum val : AlertTypeEnum.values()) {
            if (this.getValue().equalsIgnoreCase(val.getValue())) {
                return val.getValue();
            }
        }
        return null;
    }
}
