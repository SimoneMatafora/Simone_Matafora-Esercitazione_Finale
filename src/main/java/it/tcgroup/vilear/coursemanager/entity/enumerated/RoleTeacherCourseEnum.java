package it.tcgroup.vilear.coursemanager.entity.enumerated;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import it.tcgroup.vilear.coursemanager.common.exception.BadParametersException;

public enum RoleTeacherCourseEnum {
    CODOCENTE("codocente"),
    DOCENTE("docente"),
    DOCENTE_SINDACALE("docente_sindacale"),
    DOCENZA_INTERNA("docenza_interna"),
    MENTOR("mentor"),
    TUTOR("tutor");

    private String role;

    RoleTeacherCourseEnum(String role){
        this.role = role;
    }

    public String getValue() {
        return this.role.toUpperCase();
    }

    @JsonCreator
    public static RoleTeacherCourseEnum create(String role) {

        if (role != null) {
            for (RoleTeacherCourseEnum val : RoleTeacherCourseEnum.values()) {
                if (role.equalsIgnoreCase(val.getValue())) {
                    return val;
                }
            }
        }
        throw new BadParametersException("Bad parameters exception. Enum class " + RoleTeacherCourseEnum.class.getSimpleName() + " doesn't accept this value: " + role);
    }

    @JsonValue
    public String toValue() {
        for (RoleTeacherCourseEnum val : RoleTeacherCourseEnum.values()) {
            if (this.getValue().equalsIgnoreCase(val.getValue())) {
                return val.getValue();
            }
        }
        return null;
    }
}
