package it.tcgroup.vilear.coursemanager.entity.enumerated;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import it.tcgroup.vilear.coursemanager.common.exception.BadParametersException;

public enum RecipientTypeLearnerCourseEnum {
    A("A"),
    B("B"),
    B1("B1"),
    B2("B2"),
    B3("B3"),
    C("C"),
    C1("C1"),
    C2("C2"),
    C3("C3"),
    D("D"),
    E("E"),
    F1("F1"),
    F2("F2"),
    F3("F3"),
    F4("F4"),
    F5("F5"),
    F6("F6"),
    FIORISA_BRUZZESE("Fiorisa bruzzese"),
    IA("Ia"),
    IB("Ib"),
    IC("Ic"),
    ID("Id"),
    IE("Ie"),
    IF("If"),
    O1("O1"),
    OG("Og"),
    PA("PA"),
    PB("PB"),
    PC("PC"),
    PD("PD"),
    PE("PE"),
    PF("PF"),
    TI("TI");

    private String recipientCode;

    RecipientTypeLearnerCourseEnum(String recipientCode){
        this.recipientCode = recipientCode;
    }

    public String getValue() {
        return this.recipientCode;
    }

    @JsonCreator
    public static RecipientTypeLearnerCourseEnum create(String recipientCode) {

        if (recipientCode != null) {
            for (RecipientTypeLearnerCourseEnum val : RecipientTypeLearnerCourseEnum.values()) {
                if (recipientCode.equalsIgnoreCase(val.getValue())) {
                    return val;
                }
            }
        }
        throw new BadParametersException("Bad parameters exception. Enum class " + RecipientTypeLearnerCourseEnum.class.getSimpleName() + " doesn't accept this value: " + recipientCode);
    }

    @JsonValue
    public String toValue() {
        for (RecipientTypeLearnerCourseEnum val : RecipientTypeLearnerCourseEnum.values()) {
            if (this.getValue().equalsIgnoreCase(val.getValue())) {
                return val.getValue();
            }
        }
        return null;
    }

}
