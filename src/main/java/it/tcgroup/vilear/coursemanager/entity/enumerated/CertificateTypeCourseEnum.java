package it.tcgroup.vilear.coursemanager.entity.enumerated;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import it.tcgroup.vilear.coursemanager.common.exception.BadParametersException;

public enum CertificateTypeCourseEnum {
    ATTESTATO_DI_FREQUENZA("Attestato di frequenza"),
    ATTESTATO_REGIONALE("Attestato regionale");

    private String certificateType;

    CertificateTypeCourseEnum(String certificateType){
        this.certificateType = certificateType;
    }

    public String getValue() {
        return this.certificateType;
    }

    @JsonCreator
    public static CertificateTypeCourseEnum create(String certificateType) {

        if (certificateType != null) {
            for (CertificateTypeCourseEnum val : CertificateTypeCourseEnum.values()) {
                if (certificateType.equalsIgnoreCase(val.getValue())) {
                    return val;
                }
            }
        }
        throw new BadParametersException("Bad parameters exception. Enum class " + CertificateTypeCourseEnum.class.getSimpleName() + " doesn't accept this value: " + certificateType);
    }

    @JsonValue
    public String toValue() {
        for (CertificateTypeCourseEnum val : CertificateTypeCourseEnum.values()) {
            if (this.getValue().equalsIgnoreCase(val.getValue())) {
                return val.getValue();
            }
        }
        return null;
    }
}
