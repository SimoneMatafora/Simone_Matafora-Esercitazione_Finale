package it.tcgroup.vilear.coursemanager.entity.enumerated;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import it.tcgroup.vilear.coursemanager.common.exception.BadParametersException;

public enum PaymentModalityTradeUnionEnum {
    CON_DELEGA_DI_INCASSO("con_delega_di_incasso"),
    SENZA_CON_DELEGA_DI_INCASSO("senza_con_delega_di_incasso");

    private String paymentModalityTradeUnion;

    PaymentModalityTradeUnionEnum(String paymentModalityTradeUnion){
        this.paymentModalityTradeUnion = paymentModalityTradeUnion;
    }

    public String getValue() {
        return this.paymentModalityTradeUnion.toUpperCase();
    }

    @JsonCreator
    public static PaymentModalityTradeUnionEnum create(String paymentModalityTradeUnion) {

        if (paymentModalityTradeUnion != null) {
            for (PaymentModalityTradeUnionEnum val : PaymentModalityTradeUnionEnum.values()) {
                if (paymentModalityTradeUnion.equalsIgnoreCase(val.getValue())) {
                    return val;
                }
            }
        }
        throw new BadParametersException("Bad parameters exception. Enum class " + PaymentModalityTradeUnionEnum.class.getSimpleName() + " doesn't accept this value: " + paymentModalityTradeUnion);
    }

    @JsonValue
    public String toValue() {
        for (PaymentModalityTradeUnionEnum val : PaymentModalityTradeUnionEnum.values()) {
            if (this.getValue().equalsIgnoreCase(val.getValue())) {
                return val.getValue();
            }
        }
        return null;
    }
}
