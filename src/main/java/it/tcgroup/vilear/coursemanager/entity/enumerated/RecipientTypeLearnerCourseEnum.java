package it.tcgroup.vilear.coursemanager.entity.enumerated;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import it.tcgroup.vilear.coursemanager.common.exception.BadParametersException;

public enum RecipientTypeLearnerCourseEnum {
    A("a"),
    B("b"),
    B1("b1"),
    B2("b2"),
    B3("b3"),
    C("c"),
    C1("c1"),
    C2("c2"),
    C3("c3"),
    D("d"),
    E("e"),
    F1("f1"),
    F2("f2"),
    F3("f3"),
    F4("f4"),
    F5("f5"),
    F6("f6"),
    FIORISA_BRUZZESE("fiorisa_bruzzese"),
    IA("ia"),
    IB("ib"),
    IC("ic"),
    ID("id"),
    IE("ie"),
    IF("if"),
    O1("o1"),
    OG("og"),
    PA("pa"),
    PB("pb"),
    PC("pc"),
    PD("pd"),
    PE("pe"),
    PF("pf"),
    TI("ti");

    private String recipientCode;

    RecipientTypeLearnerCourseEnum(String recipientCode){
        this.recipientCode = recipientCode;
    }

    public String getValue() {
        return this.recipientCode.toUpperCase();
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
