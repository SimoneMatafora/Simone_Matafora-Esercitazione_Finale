package it.tcgroup.vilear.coursemanager.entity.enumerated;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import it.tcgroup.vilear.coursemanager.common.exception.BadParametersException;

public enum SupplyModalityCourseEnum {
    GRATUITA("gratuita"),
    PAGAMENTO("pagamento");

    private String supplyModality;

    SupplyModalityCourseEnum(String supplyModality){
        this.supplyModality = supplyModality;
    }

    public String getValue() {
        return this.supplyModality.toUpperCase();
    }

    @JsonCreator
    public static SupplyModalityCourseEnum create(String supplyModality) {

        if (supplyModality != null) {
            for (SupplyModalityCourseEnum val : SupplyModalityCourseEnum.values()) {
                if (supplyModality.equalsIgnoreCase(val.getValue())) {
                    return val;
                }
            }
        }
        throw new BadParametersException("Bad parameters exception. Enum class " + SupplyModalityCourseEnum.class.getSimpleName() + " doesn't accept this value: " + supplyModality);
    }

    @JsonValue
    public String toValue() {
        for (SupplyModalityCourseEnum val : SupplyModalityCourseEnum.values()) {
            if (this.getValue().equalsIgnoreCase(val.getValue())) {
                return val.getValue();
            }
        }
        return null;
    }
}
