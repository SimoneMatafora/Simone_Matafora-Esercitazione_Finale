package it.tcgroup.vilear.coursemanager.entity.enumerated;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import it.tcgroup.vilear.coursemanager.common.exception.BadParametersException;

public enum PaymentModalityEnum {
    BONIFICO("bonifico"),
    CONTANTI("contanti");

    private String paymentModality;

    PaymentModalityEnum(String paymentModality){
        this.paymentModality = paymentModality;
    }

    public String getValue() {
        return this.paymentModality.toUpperCase();
    }

    @JsonCreator
    public static PaymentModalityEnum create(String paymentModality) {

        if (paymentModality != null) {
            for (PaymentModalityEnum val : PaymentModalityEnum.values()) {
                if (paymentModality.equalsIgnoreCase(val.getValue())) {
                    return val;
                }
            }
        }
        throw new BadParametersException("Bad parameters exception. Enum class " + PaymentModalityEnum.class.getSimpleName() + " doesn't accept this value: " + paymentModality);
    }

    @JsonValue
    public String toValue() {
        for (PaymentModalityEnum val : PaymentModalityEnum.values()) {
            if (this.getValue().equalsIgnoreCase(val.getValue())) {
                return val.getValue();
            }
        }
        return null;
    }
}
