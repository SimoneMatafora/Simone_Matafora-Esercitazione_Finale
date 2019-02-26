package it.tcgroup.vilear.coursemanager.entity.enumerated;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import it.tcgroup.vilear.coursemanager.common.exception.BadParametersException;

public enum TypeAddressPartnerEnum {
    FATTURAZIONE("fatturazione"),
    OPERATIVO("operativo");

    private String addressType;

    TypeAddressPartnerEnum(String addressType){
        this.addressType = addressType;
    }

    public String getValue() {
        return this.addressType.toUpperCase();
    }

    @JsonCreator
    public static TypeAddressPartnerEnum create(String addressType) {

        if (addressType != null) {
            for (TypeAddressPartnerEnum val : TypeAddressPartnerEnum.values()) {
                if (addressType.equalsIgnoreCase(val.getValue())) {
                    return val;
                }
            }
        }
        throw new BadParametersException("Bad parameters exception. Enum class " + TypeAddressPartnerEnum.class.getSimpleName() + " doesn't accept this value: " + addressType);
    }

    @JsonValue
    public String toValue() {
        for (TypeAddressPartnerEnum val : TypeAddressPartnerEnum.values()) {
            if (this.getValue().equalsIgnoreCase(val.getValue())) {
                return val.getValue();
            }
        }
        return null;
    }

}
